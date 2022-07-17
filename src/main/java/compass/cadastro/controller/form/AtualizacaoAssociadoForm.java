package compass.cadastro.controller.form;

import java.sql.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import compass.cadastro.entidades.Associado;
import compass.cadastro.entidades.Partido;
import compass.cadastro.repository.AssociadoRepository;
import compass.cadastro.repository.PartidoRepository;
import compass.cadastro.util.ConverterData;

public class AtualizacaoAssociadoForm {

	@NotNull
	@NotEmpty
	private String nome;

	@NotNull
	private String cargoPolitico;

	@NotNull
	private String dataNascimento;

	@NotNull
	private String sexo;

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

	public Associado atualizar(Long id, AssociadoRepository associadoRepository) {
		Associado associado = associadoRepository.getOne(id);
		
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
				
				ConverterData converterData = new ConverterData();
				
				associado.setNome(this.nome);
				associado.setCargoPolitico(this.cargoPolitico);
				associado.setDataNascimento(converterData.dataConvertida(dataNascimento));
				associado.setSexo(this.sexo);
			} else {

				throw new IllegalArgumentException("Sexo Inválido");
			}
		} else {
			throw new IllegalArgumentException("Cargo Político Inválido");
		}

		return associado;

	}
}