package br.com.duxusdesafio.controller;

import br.com.duxusdesafio.dto.*;
import br.com.duxusdesafio.model.ComposicaoTime;
import br.com.duxusdesafio.model.Integrante;
import br.com.duxusdesafio.model.Time;


import br.com.duxusdesafio.repository.ComposicaoTimeRepository;
import br.com.duxusdesafio.repository.IntegranteRepository;
import br.com.duxusdesafio.repository.TimeRepository;
import br.com.duxusdesafio.service.ApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/integrante-mais-usado")
    public ResponseEntity<IntegranteMaisUsadoResponseDTO> integranteMaisUsado(

            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate dataInicial,

            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate dataFinal
    ) {

        List<Time> todosOsTimes =
                timeRepository.findAll();

        Integrante integrante = apiService.integranteMaisUsado
                (dataInicial, dataFinal, todosOsTimes);


        IntegranteMaisUsadoResponseDTO response =
                new IntegranteMaisUsadoResponseDTO(
                        integrante.getNome(),
                        integrante.getFuncao()

                );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/integrantes-do-time-mais-recorrente")
    public ResponseEntity<IntegrantesDoTimeMaisRecorrenteResponseDTO> integrantesDoTimeMaisRecorrente(

            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate dataInicial,

            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate dataFinal

    ) {

        List<Time> todosOsTimes =
                timeRepository.findAll();

        List<String> integrantes =
                apiService.integrantesDoTimeMaisRecorrente(
                        dataInicial,
                        dataFinal,
                        todosOsTimes
                );

        IntegrantesDoTimeMaisRecorrenteResponseDTO response =
                new IntegrantesDoTimeMaisRecorrenteResponseDTO(

                        integrantes
                );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/funcao-mais-recorrente")
    public ResponseEntity<FuncaoMaisRecorrenteResponseDTO> funcaoMaisRecorrente(

            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate dataInicial,

            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate dataFinal

    ) {

        List<Time> todosOsTimes =
                timeRepository.findAll();

        var funcao = apiService.funcaoMaisRecorrente(
                dataInicial, dataFinal, todosOsTimes
        );

        FuncaoMaisRecorrenteResponseDTO response =
                new FuncaoMaisRecorrenteResponseDTO(funcao);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/clube-mais-recorrente")
    public ResponseEntity<ClubeMaisRecorrenteResponseDTO> clubeMaisRecorrente(

            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate dataInicial,

            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate dataFinal

    ){

        List<Time> todosOsTimes =
                timeRepository.findAll();

        var clube = apiService.clubeMaisRecorrente(
                dataInicial, dataFinal, todosOsTimes
        );

        ClubeMaisRecorrenteResponseDTO response =
                new ClubeMaisRecorrenteResponseDTO(clube);

        return ResponseEntity.ok(response);
    }
}
