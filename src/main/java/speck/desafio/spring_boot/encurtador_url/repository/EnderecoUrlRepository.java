package speck.desafio.spring_boot.encurtador_url.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import speck.desafio.spring_boot.encurtador_url.model.EnderecoUrl;

import java.util.Optional;

@Repository
public interface EnderecoUrlRepository extends JpaRepository<EnderecoUrl, Long> {
    Optional<EnderecoUrl> findByUrlEncurtada(String urlEncurtada);
    boolean existsByUrlEncurtada(String urlEncurtada);
}
