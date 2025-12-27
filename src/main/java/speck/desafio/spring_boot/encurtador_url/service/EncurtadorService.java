package speck.desafio.spring_boot.encurtador_url.service;

import org.springframework.stereotype.Service;
import speck.desafio.spring_boot.encurtador_url.dto.UrlDTO;
import speck.desafio.spring_boot.encurtador_url.model.EnderecoUrl;
import speck.desafio.spring_boot.encurtador_url.repository.EnderecoUrlRepository;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.UUID;

@Service
public class EncurtadorService {

    private final EnderecoUrlRepository repository;
    private String domain = "http://localhost:8080/";


    public EncurtadorService(EnderecoUrlRepository repository) {
        this.repository = repository;
    }

    // metodos para criação dos horarios
    private OffsetDateTime agora() {
        return OffsetDateTime.now(ZoneId.of("America/Sao_Paulo"));
    }

    private OffsetDateTime expiraEm(){
        return OffsetDateTime.now(ZoneId.of("America/Sao_Paulo")).plusMinutes(5); // expíra em 5 minutos para teste
    }

    public UrlDTO encurtarUrl(UrlDTO dto){
        String urlEncurtada =
                UUID
                .fromString(dto.url())
                .toString()
                .substring(0, 5);

        if (repository.existsByUrlEncurtada(urlEncurtada)) throw new RuntimeException("Falha ao encurtar a URL");

        EnderecoUrl urlPersist = repository.save(new EnderecoUrl(dto.url(), domain + urlEncurtada, agora(), expiraEm()));
        return new UrlDTO(urlPersist.getUrlEncurtada());
    }

    public UrlDTO buscarUrl(String urlEncurtada){
        EnderecoUrl enderecoUrl = repository.findByUrlEncurtada(urlEncurtada)
                .orElseThrow(() -> new NullPointerException("Url informada não foi encontrada!"));

        if (enderecoUrl.getExpiraEm().isBefore(agora())) throw new RuntimeException("Url informada expirou em: " + enderecoUrl.getExpiraEm().toString());

        return new UrlDTO(enderecoUrl.getUrlOriginal());
    }
}
