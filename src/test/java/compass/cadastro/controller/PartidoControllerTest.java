package compass.cadastro.controller;

import static org.junit.Assert.*;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PartidoControllerTest {
	
	@Autowired
	private MockMvc mockMvc;

	@org.junit.jupiter.api.Test
	public void deveriaCadastrarNovoPartido() throws Exception{
		URI uri = new URI("/partidos");
		String json = "{\"nome\":\"Partido do Sétimo Dia\","
				+ "\"sigla\":\"PSD\","
				+ "\"ideologia\":\"Direita\","
				+ "\"dataFundacao\":\"14/07/2022\"}";
		
		mockMvc
		.perform(MockMvcRequestBuilders
				.post(uri)
				.content(json)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers
				.status()
				.is(201));
		}

	@org.junit.jupiter.api.Test
	public void deveriaAtualizarAssociado() throws Exception{
			URI uri = new URI("/partidos/1");
			String json = "{\"nome\":\"Primeiro Partido\","
					+ "\"sigla\":\"PP\","
					+ "\"ideologia\":\"Direita\","
					+ "\"dataFundacao\":\"14/07/2022\"}";
			
			mockMvc
			.perform(MockMvcRequestBuilders
					.put(uri)
					.content(json)
					.contentType(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers
					.status()
					.is(200));
			}
	
	  @org.junit.jupiter.api.Test public void deveriaDeletarPartidoPorId() throws
	  Exception { URI uri = new URI("/partidos/2"); 
	  
	  mockMvc.perform(MockMvcRequestBuilders.delete(uri))
	  .andExpect(MockMvcResultMatchers.status().is(200));
			}
	  
	  @org.junit.jupiter.api.Test

		public void deveriaBuscarPartidoPorId() throws Exception {
			URI uri = new URI("/partidos/3");
			mockMvc.perform(MockMvcRequestBuilders.get(uri)).andExpect(MockMvcResultMatchers.status().is(200));
		}
	  
	  @org.junit.jupiter.api.Test

			public void deveriaBuscarTodosPartidos() throws Exception {
				URI uri = new URI("/partidos");
				mockMvc.perform(MockMvcRequestBuilders.get(uri)).andExpect(MockMvcResultMatchers.status().is(200));
			}
	  
}


