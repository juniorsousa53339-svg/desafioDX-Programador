package br.com.duxusdesafio.mapper;

import br.com.duxusdesafio.dto.TimeRequestDTO;
import br.com.duxusdesafio.dto.TimeResponseDTO;
import br.com.duxusdesafio.model.Time;
import org.springframework.stereotype.Component;

@Component
public class TimeMapper {

    public Time toEntity(TimeRequestDTO request) {

        Time time = new Time();

        time.setData(request.data());
        time.setNomeDoClube(request.nomeDoClube());

        return time;
    }

    public TimeResponseDTO toResponse(Time time) {

        return new TimeResponseDTO(
                time.getNomeDoClube(),
                time.getData(),
                time.getComposicaoTime()
        );
    }
}
