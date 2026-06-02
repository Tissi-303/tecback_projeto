package br.uniesp.si.techback.service;

import br.uniesp.si.techback.dto.OmdbResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class OmdbService {

    private final RestTemplate restTemplate;

    // Chave gratuita para testes
    private final String API_KEY = "7ed66184";

    public OmdbResponseDTO buscarFilmeNoOmdb(String titulo) {
        String url = "http://www.omdbapi.com/?t=" + titulo + "&apikey=" + API_KEY;
        try {
            return restTemplate.getForObject(url, OmdbResponseDTO.class);
        } catch (Exception e) {
            return null; // Se a API falhar, retorna null para não travar o sistema
        }
    }
}