package compass.cadastro.entidades;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Associado {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String cargoPolitico;
	private LocalDate dataNascimento;
	private String sexo;
	@ManyToOne(optional = true, cascade = CascadeType.REFRESH)
	private Partido partido;

	public Associado() {

	}

	public Associado(String nome, String cargoPolitico, LocalDate dataNascimento, String sexo) {

		if (cargoPolitico.equalsIgnoreCase("Vereador")
				|| cargoPolitico.equalsIgnoreCase("Prefeito")
				|| cargoPolitico.equalsIgnoreCase("Deputado Estadual")
				|| cargoPolitico.equalsIgnoreCase("Deputado Federal")
				|| cargoPolitico.equalsIgnoreCase("Senador")
				|| cargoPolitico.equalsIgnoreCase("Governador")
				|| cargoPolitico.equalsIgnoreCase("Presidente")
				|| cargoPolitico.equalsIgnoreCase("nenhum")) {

			if (sexo.equalsIgnoreCase("Masculino")
					|| sexo.equalsIgnoreCase("Feminino")) {
				this.nome = nome;
				this.cargoPolitico = cargoPolitico;
				this.dataNascimento = dataNascimento;
				this.sexo = sexo;
			} else {

				throw new IllegalArgumentException("Sexo Inválido");
			}
		} else {
			throw new IllegalArgumentException("Cargo Político Inválido");
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

	public String getCargoPolitico() {
		return cargoPolitico;
	}

	public void setCargoPolitico(String cargoPolitico) {
		this.cargoPolitico = cargoPolitico;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public Partido getPartido() {
		return partido;
	}

	public void setPartido(Partido partido) {
		this.partido = partido;
	}
	
	
}
