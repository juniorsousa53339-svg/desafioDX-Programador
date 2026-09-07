package br.com.duxusdesafio.controller;

import br.com.duxusdesafio.dto.request.TimeRequestDTO;
import br.com.duxusdesafio.dto.response.TimeResponseDTO;
import br.com.duxusdesafio.service.TimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/Times")
@RequiredArgsConstructor
public class TimeController {

    private final TimeService timeService;


    @PostMapping
    public ResponseEntity<TimeResponseDTO> salvar(
            @RequestBody @Valid TimeRequestDTO request) {

        TimeResponseDTO resposta =
                timeService.salvar(request);
        return ResponseEntity.ok(resposta);
    }

}
