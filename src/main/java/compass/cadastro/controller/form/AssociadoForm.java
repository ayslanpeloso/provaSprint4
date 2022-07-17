package compass.cadastro.controller.form;

import java.sql.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import compass.cadastro.entidades.Associado;
import compass.cadastro.entidades.Partido;
import compass.cadastro.util.ConverterData;

public class AssociadoForm {
	
	@NotNull @NotEmpty
	private String nome;
	
	@NotNull @NotEmpty
	private String cargoPolitico;
	
	@NotNull
	private String dataNascimento;
	
	@NotNull @NotEmpty
	private String sexo;
	
	@NotNull
	
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

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	
	public Associado converter() {
		ConverterData converterData = new ConverterData();
		return new Associado(nome, cargoPolitico, converterData.dataConvertida(dataNascimento), sexo);
	}
	
}
