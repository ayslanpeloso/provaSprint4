package compass.cadastro.controller.form;

import java.sql.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import compass.cadastro.entidades.Partido;
import compass.cadastro.util.ConverterData;

public class PartidoForm {
	
	@NotNull @NotEmpty
	private String nome;
	
	@NotNull @NotEmpty
	private String sigla;
	
	@NotNull
	private String ideologia;
	
	@NotNull @NotEmpty
	private String dataFundacao;
	
	@NotNull
	

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
	public String getDataFundacao() {
		return dataFundacao;
	}
	public void setDataFundacao(String dataFundacao) {
		this.dataFundacao = dataFundacao;
	}

	public Partido converter() {
		ConverterData converterData = new ConverterData();
		return new Partido(nome, sigla, ideologia, converterData.dataConvertida(dataFundacao));
	}
	
}
