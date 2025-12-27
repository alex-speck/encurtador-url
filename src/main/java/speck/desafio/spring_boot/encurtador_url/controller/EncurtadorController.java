package speck.desafio.spring_boot.encurtador_url.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import speck.desafio.spring_boot.encurtador_url.dto.ResponseUtil;
import speck.desafio.spring_boot.encurtador_url.dto.UrlDTO;
import speck.desafio.spring_boot.encurtador_url.service.EncurtadorService;

import java.net.URI;

@Controller
@RequestMapping("/")
public class EncurtadorController {

    private final EncurtadorService service;

    public EncurtadorController(EncurtadorService service) {
        this.service = service;
    }

    @PostMapping("/shorten-url")
    public ResponseEntity<?> encurtarUrl(@RequestBody UrlDTO dto){
        try {
            UrlDTO response = service.encurtarUrl(dto);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ResponseUtil(e.getLocalizedMessage()));
        }
    }

    @GetMapping("/{shortened-url}")
    public ResponseEntity<?> buscarUrl(@PathVariable("shortened-url") String url){
        try{
            UrlDTO urlOriginal = service.buscarUrl(url);

            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(new URI(urlOriginal.url()));

            return ResponseEntity.status(HttpStatus.FOUND).headers(headers).build();
        }   catch (NullPointerException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e){
            return ResponseEntity.badRequest().body(new ResponseUtil(e.getLocalizedMessage()));
        }
    }
}
