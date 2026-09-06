package br.com.duxusdesafio.controller;

import br.com.duxusdesafio.dto.IntegranteRequestDTO;
import br.com.duxusdesafio.dto.IntegranteResponseDTO;
import br.com.duxusdesafio.service.IntegranteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/integrantes")
@RequiredArgsConstructor
public class IntegranteController {

    private final IntegranteService integranteService;

    @PostMapping
    public ResponseEntity<IntegranteResponseDTO> salvar(
            @RequestBody @Valid IntegranteRequestDTO request){

        IntegranteResponseDTO resposta =
                integranteService.salvar(request);

        return ResponseEntity.ok(resposta);
    }
}
