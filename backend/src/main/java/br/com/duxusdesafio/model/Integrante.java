package br.com.duxusdesafio.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "integrante")
public class Integrante {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotNull
	@Column
	private String nome;
	
	@NotNull
	@Column
	private String funcao;
	
	@OneToMany(mappedBy = "integrante")
	private List<ComposicaoTime> composicaoTime;

    public Integrante(String michaelJordan, String ala, List<ComposicaoTime> composicaoTime1994E1995) {
        this.nome = michaelJordan;
        this.funcao = ala;
        this.composicaoTime = composicaoTime1994E1995;
    }

    @Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Integrante)) return false;
		Integrante that = (Integrante) o;
		return id == that.id && Objects.equals(nome, that.nome) && Objects.equals(funcao, that.funcao);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, nome, funcao);
	}

	@Override
	public String toString() {
		return "Integrante{" +
				"id=" + id +
				", nome='" + nome + '\'' +
				", funcao='" + funcao + '\'' +
				'}';
	}
}
