package speck.desafio.spring_boot.encurtador_url.service;

import org.springframework.stereotype.Service;
import speck.desafio.spring_boot.encurtador_url.dto.UrlDTO;
import speck.desafio.spring_boot.encurtador_url.exception.ExpiredUrlException;
import speck.desafio.spring_boot.encurtador_url.exception.UrlNotFoundException;
import speck.desafio.spring_boot.encurtador_url.model.EnderecoUrl;
import speck.desafio.spring_boot.encurtador_url.repository.EnderecoUrlRepository;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.UUID;

@Service
public class EncurtadorService {

    private final EnderecoUrlRepository repository;


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

    public UrlDTO encurtarUrl(UrlDTO dto) {

            String urlOriginal = isValid(dto.url());
            String urlEncurtada = UUID
                    .randomUUID()
                    .toString()
                    .replaceAll("^[a-zA-Z0-9]*$", "")
                    .substring(0, 5)
                    .toUpperCase();

            // só para garantir que não criou uma url repetida
            if (repository.existsByUrlEncurtada(urlEncurtada)) throw new RuntimeException("Falha ao encurtar a URL");

            EnderecoUrl urlPersist = repository.save(new EnderecoUrl(urlOriginal, urlEncurtada, agora(), expiraEm()));
            String domain = "http://localhost:8080/";
            return new UrlDTO(domain + urlPersist.getUrlEncurtada());
    }

    public UrlDTO buscarUrl(String urlEncurtada){
        EnderecoUrl enderecoUrl = repository.findByUrlEncurtada(urlEncurtada)
                .orElseThrow(() -> new UrlNotFoundException("Url informada não foi encontrada!"));

        if (enderecoUrl.getExpiraEm().isBefore(agora())) throw new ExpiredUrlException("Url informada expirou em: " + enderecoUrl.getExpiraEm().toString());

        return new UrlDTO(enderecoUrl.getUrlOriginal());
    }

    private String isValid(String url){
        try {
            return new URL(url).toString();
        } catch (MalformedURLException e) {
            throw new RuntimeException("'" + url + "' não é uma URL valida!");
        }
    }
}
