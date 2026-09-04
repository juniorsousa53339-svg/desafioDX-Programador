package br.com.duxusdesafio.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;


@Entity
@Table(name = "composicao_time")
@Getter
@Setter
@NoArgsConstructor
public class ComposicaoTime {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	private Time time;

	@ManyToOne
	private Integrante integrante;

	@Override
	public final boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof ComposicaoTime)) return false;
		ComposicaoTime that = (ComposicaoTime) o;
		return id == that.id && Objects.equals(time, that.time) && Objects.equals(integrante, that.integrante);
	}

	@Override
	public final int hashCode() {
		return Objects.hash(id, time, integrante);
	}

	@Override
	public String toString() {
		return "ComposicaoTime{" +
				"time=" + time +
				", integrante=" + integrante +
				'}';
	}
}
