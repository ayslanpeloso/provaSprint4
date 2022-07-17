package compass.cadastro.entidades;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Partido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String sigla;
	private String ideologia;
	private LocalDate dataFundacao;

	public Partido() {
		
	}

	public Partido(String nome, String sigla, String ideologia, LocalDate dataFundacao) {

		if (ideologia.equalsIgnoreCase("Centro") || ideologia.equalsIgnoreCase("Direita")
				|| ideologia.equalsIgnoreCase("Esquerda")) {
			this.nome = nome;
			this.sigla = sigla;
			this.ideologia = ideologia;
			this.dataFundacao = dataFundacao;
		} else {
			throw new IllegalArgumentException("Ideologia Invalida");
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getIdeologia() {
		return ideologia;
	}

	public void setIdeologia(String ideologia) {
		this.ideologia = ideologia;
	}

	public LocalDate getDataFundacao() {
		return dataFundacao;
	}

	public void setDataFundacao(LocalDate dataFundacao) {
		this.dataFundacao = dataFundacao;
	}

}
