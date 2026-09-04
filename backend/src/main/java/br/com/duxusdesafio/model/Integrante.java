package br.com.duxusdesafio.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

@Entity
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


	public Integrante() {
	}

	public Integrante(String nome, String funcao, List<ComposicaoTime> composicaoTime) {
		this.nome = nome;
		this.funcao = funcao;
		this.composicaoTime = composicaoTime;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getFuncao() {
		return funcao;
	}

	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}

	public List<ComposicaoTime> getComposicaoTime() {
		return composicaoTime;
	}

	public void setComposicaoTime(List<ComposicaoTime> composicaoTime) {
		this.composicaoTime = composicaoTime;
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
