package compass.cadastro.controller.form;

import java.sql.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import compass.cadastro.entidades.Partido;
import compass.cadastro.repository.PartidoRepository;
import compass.cadastro.util.ConverterData;

public class AtualizacaoPartidoForm {
	
	@NotNull @NotEmpty
	private String nome;
	
	@NotNull
	private String sigla;
	
	@NotNull @NotEmpty
	private String ideologia;
	
	@NotNull
	private String dataFundacao;


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



	public Partido atualizar(Long id, PartidoRepository partidoRepository) {
		Partido partido = partidoRepository.getOne(id);
		
		
		
		if (ideologia.equalsIgnoreCase("Centro") || ideologia.equalsIgnoreCase("Direita")
				|| ideologia.equalsIgnoreCase("Esquerda")){
			
		ConverterData converterData = new ConverterData();
			
		partido.setNome(this.nome);
		partido.setSigla(this.sigla);
		partido.setIdeologia(this.ideologia);
		partido.setDataFundacao(converterData.dataConvertida(dataFundacao));
		} else {throw new IllegalArgumentException("Ideologia Invalida");
		}
		
		
		return partido;
	}
	
}
