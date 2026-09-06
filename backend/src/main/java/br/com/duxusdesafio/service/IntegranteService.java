package br.com.duxusdesafio.service;

import br.com.duxusdesafio.dto.IntegranteRequestDTO;
import br.com.duxusdesafio.dto.IntegranteResponseDTO;
import br.com.duxusdesafio.mapper.IntegranteMapper;
import br.com.duxusdesafio.model.Integrante;
import br.com.duxusdesafio.repository.IntegranteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IntegranteService {

    private final IntegranteRepository integranteRepository;
    private final IntegranteMapper integranteMapper;

    public IntegranteResponseDTO salvar(IntegranteRequestDTO request) {

        Integrante integrante =
                integranteMapper.toEntity(request);

        Integrante salvo =
                integranteRepository.save(integrante);

        return integranteMapper.toResponse(salvo);
    }
}
