package br.com.duxusdesafio.service;

import br.com.duxusdesafio.model.ComposicaoTime;
import br.com.duxusdesafio.model.Integrante;
import br.com.duxusdesafio.model.Time;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Service que possuirá as regras de negócio para o processamento dos dados
 * solicitados no desafio!
 * <p>
 * OBS ao candidato: PREFERENCIALMENTE, NÃO ALTERE AS ASSINATURAS DOS MÉTODOS!
 * Trabalhe com a proposta pura.
 *
 * @author carlosau
 */


@Service
@RequiredArgsConstructor
public class ApiService {

    /**
     * Vai retornar um Time, com a composição do time daquela data
     */
    public Time timeDaData(LocalDate data, List<Time> todosOsTimes) {
        // TODO Implementar método seguindo as instruções!

        for (Time time : todosOsTimes) {
            if (time.getData().equals(data)) {
                return time;
            }
        }
        return null;
    }

    /**
     * Vai retornar o integrante que estiver presente na maior quantidade de times
     * dentro do período
     */
    public Integrante integranteMaisUsado(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes) {
        // TODO Implementar método seguindo as instruções!

        List<Time> todosOsTimesNoPeriodo = new ArrayList<>();

        for (Time time : todosOsTimes) {

            if ((time.getData().equals(dataInicial) ||
                    time.getData().isAfter(dataInicial))
                    &&

                    (time.getData().equals(dataFinal) ||
                            time.getData().isBefore(dataFinal))
            ) {

                todosOsTimesNoPeriodo.add(time);
            }
        }

        Map<Integrante, Integer> map = new HashMap<>();
        int valorAtual = 0;

        for (Time time : todosOsTimesNoPeriodo) {
            for (ComposicaoTime composicao : time.getComposicaoTime()) {

                if (map.containsKey(composicao.getIntegrante())) {
                    valorAtual = map.get(composicao.getIntegrante());

                    map.put(composicao.getIntegrante(), valorAtual + 1);


                } else {
                    map.put(composicao.getIntegrante(), 1);
                }
            }
        }
        System.out.println(map);
        int maiorQuantidade = 0;
        Integrante integranteMaisUsado = null;

        for (Map.Entry<Integrante, Integer> entry : map.entrySet()) {

            if (entry.getValue() > maiorQuantidade) {
                maiorQuantidade = entry.getValue();
                integranteMaisUsado = entry.getKey();
            }
        }
        return integranteMaisUsado;

    }

    /**
     * Vai retornar uma lista com os nomes dos integrantes do time mais recorrente dentro do período.
     * OBS: Time é o clube + composição em determinada data
     */
    public List<String> integrantesDoTimeMaisRecorrente(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes) {
        // TODO Implementar método seguindo as instruções!
        return null;
    }

    /**
     * Vai retornar a função mais recorrente nos times dentro do período
     */
    public String funcaoMaisRecorrente(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes) {
        // TODO Implementar método seguindo as instruções!
        return null;
    }

    /**
     * Vai retornar o nome do Clube mais comum dentro do período
     */
    public String clubeMaisRecorrente(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes) {
        // TODO Implementar método seguindo as instruções!
        return null;
    }


    /**
     * Vai retornar o número (quantidade) de aparições de cada Clube participante no período
     */
    public Map<String, Long> contagemDeClubesNoPeriodo(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes) {
        // TODO Implementar método seguindo as instruções!
        return null;
    }

    /**
     * Vai retornar o número (quantidade) de Funções dentro do período.
     * Dica - pense sobre repetições!
     */
    public Map<String, Long> contagemPorFuncao(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes) {
        // TODO Implementar método seguindo as instruções!
        return null;
    }

}
