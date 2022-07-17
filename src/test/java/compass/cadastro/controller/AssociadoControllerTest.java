package compass.cadastro.controller;

import static org.junit.Assert.*;

import java.net.URI;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AssociadoControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@org.junit.jupiter.api.Test
	public void deveriaCadastrarNovoAssociado() throws Exception {
		URI uri = new URI("/associados");
		String json = "{\"nome\":\"Associado Teste\"," + "\"cargoPolitico\":\"Deputado Estadual\","
				+ "\"dataNascimento\":\"31/12/1960\"," + "\"sexo\":\"masculino\"}";

		mockMvc.perform(MockMvcRequestBuilders.post(uri).content(json).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(201));
	}

	@org.junit.jupiter.api.Test
	public void deveriaAtualizarAssociado() throws Exception {
		URI uri = new URI("/associados/1");
		String json = "{\"nome\":\"Associado Teste\"," + "\"cargoPolitico\":\"Deputado Estadual\","
				+ "\"dataNascimento\":\"31/12/1960\"," + "\"sexo\":\"masculino\"}";

		mockMvc.perform(MockMvcRequestBuilders.put(uri).content(json).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(200));
	}

	@org.junit.jupiter.api.Test
	public void deveriaDeletarAssociadoPorId() throws Exception {
		URI uri = new URI("/associados/2");

		mockMvc.perform(MockMvcRequestBuilders.delete(uri)).andExpect(MockMvcResultMatchers.status().is(200));
	}

	@org.junit.jupiter.api.Test

	public void deveriaBuscarAssociadoPorId() throws Exception {
		URI uri = new URI("/associados/3");
		mockMvc.perform(MockMvcRequestBuilders.get(uri)).andExpect(MockMvcResultMatchers.status().is(200));
	}
	
	@org.junit.jupiter.api.Test

	public void deveriaBuscarTodosAssociados() throws Exception {
		URI uri = new URI("/associados");
		mockMvc.perform(MockMvcRequestBuilders.get(uri)).andExpect(MockMvcResultMatchers.status().is(200));
	}

}
