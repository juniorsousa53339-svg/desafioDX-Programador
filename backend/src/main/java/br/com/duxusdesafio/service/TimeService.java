package br.com.duxusdesafio.service;


import br.com.duxusdesafio.dto.request.TimeRequestDTO;
import br.com.duxusdesafio.dto.response.TimeResponseDTO;
import br.com.duxusdesafio.mapper.TimeMapper;
import br.com.duxusdesafio.model.ComposicaoTime;
import br.com.duxusdesafio.model.Integrante;
import br.com.duxusdesafio.model.Time;
import br.com.duxusdesafio.repository.ComposicaoTimeRepository;
import br.com.duxusdesafio.repository.IntegranteRepository;
import br.com.duxusdesafio.repository.TimeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TimeService {

    private final TimeRepository timeRepository;
    private final IntegranteRepository integranteRepository;
    private final TimeMapper timeMapper;
    private final ComposicaoTimeRepository composicaoTimeRepository;


    public TimeResponseDTO salvar(TimeRequestDTO request) {

        Time time =
                timeMapper.toEntity(request);

        Time salvo =
                timeRepository.save(time);


        for (Long integranteId : request.integrantesIds()) {

            Integrante integrante = integranteRepository
                    .findById(integranteId)
                    .orElseThrow(() ->
                            new RuntimeException("Integrante não encontrado"));

            ComposicaoTime composicao = new ComposicaoTime();

            composicao.setTime(salvo);
            composicao.setIntegrante(integrante);

            composicaoTimeRepository.save(composicao);
        }


        return timeMapper.toResponse(salvo);
    }
}
