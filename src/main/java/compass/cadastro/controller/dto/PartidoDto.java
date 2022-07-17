package compass.cadastro.controller.dto;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

import compass.cadastro.entidades.Partido;
import compass.cadastro.util.ConverterData;

public class PartidoDto {
	
	private Long id;
	private String nome;
	private String sigla;
	private String ideologia;
	private String dataFundacao;
	
	
	public PartidoDto(Partido partido) {
		ConverterData converterData = new ConverterData();
		this.nome = partido.getNome();
		this.id = partido.getId();
		this.sigla = partido.getSigla();
		this.ideologia = partido.getIdeologia();
		this.dataFundacao = converterData.dataBrasileira(partido.getDataFundacao());
	}
	
	public String getNome() {
		return nome;
	}
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSigla() {
		return sigla;
	}
	public String getIdeologia() {
		return ideologia;
	}
	public String getDataFundacao() {
		return dataFundacao;
	}
	
	public static List<PartidoDto> converter(List<Partido> partidos) {
		return partidos.stream().map(PartidoDto::new).collect(Collectors.toList());
	}
	
	

}
