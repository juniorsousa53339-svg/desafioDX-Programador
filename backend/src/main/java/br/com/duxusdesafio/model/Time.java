package br.com.duxusdesafio.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "time")
public class Time {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

    /**
     * Nome do Clube (associação esportiva)
     * a qual o time representa.
     * Exemplos:
     * Para Futebol - Palmeiras, Santos, etc;
     * Para Basquete - Pinheiros, Franca, etc.
     */
    @Column
    private String nomeDoClube;

    /**
     * Data em que esse time foi formado (composição do time firmada)
     * Lembrando: a formação da equipe pode mudar em momentos diferentes, por isso a data.
     */
	@Column
    private LocalDate data;

    /**
     * Elenco ou equipe - o conjunto de integrantes desse time
     */
	@OneToMany(mappedBy = "time", cascade = CascadeType.ALL)
	private List<ComposicaoTime> composicaoTime;

	public Time() {
	}

	public Time(String nomeDoClube, LocalDate data, List<ComposicaoTime> composicaoTime) {
		this.nomeDoClube = nomeDoClube;
        this.data = data;
		this.composicaoTime = composicaoTime;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

    public String getNomeDoClube() {
        return nomeDoClube;
    }

    public void setNomeDoClube(String nomeDoClube) {
        this.nomeDoClube = nomeDoClube;
    }

    public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public List<ComposicaoTime> getComposicaoTime() {
		return composicaoTime;
	}

	public void setComposicaoTime(List<ComposicaoTime> composicaoTime) {
		this.composicaoTime = composicaoTime;
	}


	@Override
	public final boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Time)) return false;
		Time time = (Time) o;
		return id == time.id && Objects.equals(nomeDoClube, time.nomeDoClube) && Objects.equals(data, time.data);
	}

	@Override
	public final int hashCode() {
		return Objects.hash(id, nomeDoClube, data);
	}

	@Override
	public String toString() {
		return "Time{" +
				"id=" + id +
                ", nome=" + nomeDoClube +
				", data=" + data +
				'}';
	}
}
