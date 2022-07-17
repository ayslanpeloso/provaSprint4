package compass.cadastro.service;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import compass.cadastro.controller.dto.AssociadoDto;
import compass.cadastro.controller.dto.PartidoDto;
import compass.cadastro.controller.form.AtualizacaoPartidoForm;
import compass.cadastro.controller.form.PartidoForm;
import compass.cadastro.entidades.Associado;
import compass.cadastro.entidades.Partido;
import compass.cadastro.repository.AssociadoRepository;
import compass.cadastro.repository.PartidoRepository;

@RestController
@RequestMapping("/partidos")
public class PartidoController {
	
	@Autowired
	private PartidoRepository partidoRepository;
	
	@Autowired
	private AssociadoRepository associadoRepository;
	
	@GetMapping
	public List<PartidoDto> lista(@RequestParam(required = false) String ideologia) {
		
		if (ideologia == null) {
			List<Partido> partidos = partidoRepository.findAll();
			return PartidoDto.converter(partidos);
		} else {
			List<Partido> partidos = partidoRepository.findByIdeologia(ideologia);
			return PartidoDto.converter(partidos);
			
		}
	}
	
	
	@PostMapping
	@Transactional
	public ResponseEntity<PartidoDto> cadastrar(@RequestBody @Valid PartidoForm form, UriComponentsBuilder uriBuilder) {
		Partido partido = form.converter();
		partidoRepository.save(partido);
		
		URI uri = uriBuilder.path("/partidos/{id}").buildAndExpand(partido.getId()).toUri();
		return ResponseEntity.created(uri).body(new PartidoDto(partido));
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PartidoDto> detalhar(@PathVariable Long id) {
		Optional<Partido> partido = partidoRepository.findById(id);
		if (partido.isPresent()) {
		return ResponseEntity.ok(new PartidoDto(partido.get()));
	}
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/{id}/associados")
	public ResponseEntity<List<AssociadoDto>> detalharAssociado(@PathVariable Long id) {
		
		List<Associado> listaAssociado = associadoRepository.findByPartido_id(id);
		
		List<AssociadoDto> associadosDto = listaAssociado.stream().map(AssociadoDto::new)
				.collect(Collectors.toList());
		return ResponseEntity.ok(associadosDto);
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<PartidoDto> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoPartidoForm form){
		Optional<Partido> optional = partidoRepository.findById(id);
		if (optional.isPresent()) {
			Partido partido = form.atualizar(id, partidoRepository);
			return ResponseEntity.ok(new PartidoDto(partido));
	}
		return ResponseEntity.notFound().build();
		
	}
	
	@DeleteMapping("/{id}")
	@Transactional	
	public ResponseEntity<?> remover(@PathVariable Long id){
		Optional<Partido> optional = partidoRepository.findById(id);
		if (optional.isPresent()) {
			partidoRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
}

