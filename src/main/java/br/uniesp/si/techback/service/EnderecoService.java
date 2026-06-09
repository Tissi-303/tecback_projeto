package br.uniesp.si.techback.service;

import br.uniesp.si.techback.dto.ViaCepResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
@Slf4j
public class EnderecoService {

    private final RestTemplate restTemplate;

    public ViaCepResponseDTO buscarEnderecoPorCep(String cep) {
        log.info("Buscando endereço para o CEP: {}", cep);

        String cepLimpo = cep.replaceAll("\\D", "");
        String url = "https://viacep.com.br/ws/" + cepLimpo + "/json/";

        try {
            ViaCepResponseDTO resposta = restTemplate.getForObject(url, ViaCepResponseDTO.class);

            if (resposta == null || (resposta.getErro() != null && resposta.getErro())) {
                log.warn("CEP {} não encontrado na base do ViaCEP.", cep);
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "CEP inválido ou não encontrado.");
            }

            return resposta;
        } catch (ResponseStatusException e) {
            throw e; // Repassa o erro do CEP não encontrado
        } catch (Exception e) {
            log.error("Erro ao conectar com a API do ViaCEP: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "Falha ao integrar com o serviço de CEP externo.");
        }
    }
}