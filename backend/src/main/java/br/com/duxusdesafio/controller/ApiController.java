package br.com.duxusdesafio.controller;

import br.com.duxusdesafio.dto.TimeDaDataResponseDTO;
import br.com.duxusdesafio.dto.TimeRequestDTO;
import br.com.duxusdesafio.dto.TimeResponseDTO;
import br.com.duxusdesafio.model.ComposicaoTime;
import br.com.duxusdesafio.model.Time;


import br.com.duxusdesafio.repository.ComposicaoTimeRepository;
import br.com.duxusdesafio.repository.IntegranteRepository;
import br.com.duxusdesafio.repository.TimeRepository;
import br.com.duxusdesafio.service.ApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ApiController {

    private final ApiService apiService;

    private final TimeRepository timeRepository;
    private final IntegranteRepository integranteRepository;
    private final ComposicaoTimeRepository composicaoTimeRepository;

    @GetMapping("/{data}")
    public ResponseEntity<TimeDaDataResponseDTO> TimeDaData(
            @PathVariable
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate data) {

        var times =
                timeRepository.findAll();


        Time time = apiService.timeDaData(data, times);

        List<String> integrantes = new ArrayList<>();

        for (ComposicaoTime composicao : time.getComposicaoTime()) {
            integrantes.add(composicao.getIntegrante().getNome());
        }

        TimeDaDataResponseDTO response =

                new TimeDaDataResponseDTO(
                        time.getData(),
                        time.getNomeDoClube(),
                        integrantes
                );


        return ResponseEntity.ok(response);
    }
}
