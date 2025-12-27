package speck.desafio.spring_boot.encurtador_url.model;

import jakarta.persistence.*;

import java.time.OffsetDateTime;

@Entity
public class EnderecoUrl {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "url_original")
    private String urlOriginal;
    @Column(nullable = false, name = "url_encurtada")
    private String urlEncurtada;
    @Column(nullable = false, name = "criado_em")
    private OffsetDateTime criadoEm;
    @Column(nullable = false, name = "expira_em")
    private OffsetDateTime expiraEm;

    public EnderecoUrl() {
    }

    public EnderecoUrl(String urlOriginal, String urlEncurtada, OffsetDateTime criadoEm, OffsetDateTime expiraEm) {
        this.urlOriginal = urlOriginal;
        this.urlEncurtada = urlEncurtada;
        this.criadoEm = criadoEm;
        this.expiraEm = expiraEm;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrlOriginal() {
        return urlOriginal;
    }

    public void setUrlOriginal(String urlOriginal) {
        this.urlOriginal = urlOriginal;
    }

    public String getUrlEncurtada() {
        return urlEncurtada;
    }

    public void setUrlEncurtada(String urlEncurtada) {
        this.urlEncurtada = urlEncurtada;
    }

    public OffsetDateTime getCriadoEm() {
        return criadoEm;
    }

    public void setCriadoEm(OffsetDateTime criadoEm) {
        this.criadoEm = criadoEm;
    }

    public OffsetDateTime getExpiraEm() {
        return expiraEm;
    }

    public void setExpiraEm(OffsetDateTime expiraEm) {
        this.expiraEm = expiraEm;
    }
}
