package compass.cadastro.controller.dto;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

import compass.cadastro.entidades.Associado;
import compass.cadastro.entidades.Partido;
import compass.cadastro.util.ConverterData;

public class AssociadoDto {
	
	private Long id;
	private String nome;
	private String cargoPolitico;
	private String dataNascimento;
	private String sexo;
	private Long idPartido;

	
	public AssociadoDto(Associado associado) {
		ConverterData converterData = new ConverterData();
		this.nome = associado.getNome();
		this.cargoPolitico = associado.getCargoPolitico();
		this.dataNascimento = converterData.dataBrasileira(associado.getDataNascimento());
		this.sexo = associado.getSexo();
		this.id = associado.getId();
		if (associado.getPartido() != null) {
			this.idPartido = associado.getPartido().getId();
		}
	}
	
	
	
	public String getNome() {
		return nome;
	}



	public String getCargoPolitico() {
		return cargoPolitico;
	}



	public String getDataNascimento() {
		return dataNascimento;
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getSexo() {
		return sexo;
	}
	
	



	public Long getIdPartido() {
		return idPartido;
	}



	public void setIdPartido(Long idPartido) {
		this.idPartido = idPartido;
	}



	public static List<AssociadoDto> converter(List<Associado> associados) {
		return associados.stream().map(AssociadoDto::new).collect(Collectors.toList());
	}

}
