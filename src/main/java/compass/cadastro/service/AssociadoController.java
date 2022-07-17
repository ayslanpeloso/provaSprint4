package compass.cadastro.service;

import java.net.URI;
import java.util.ArrayList;
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
import org.springframework.data.domain.Sort;
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
import compass.cadastro.controller.form.AssociadoForm;
import compass.cadastro.controller.form.AtualizacaoAssociadoForm;
import compass.cadastro.controller.form.AtualizacaoPartidoForm;
import compass.cadastro.controller.form.PartidoForm;
import compass.cadastro.controller.form.VincularAssociadoForm;
import compass.cadastro.entidades.Associado;
import compass.cadastro.entidades.Partido;
import compass.cadastro.repository.AssociadoRepository;
import compass.cadastro.repository.PartidoRepository;

@RestController
@RequestMapping("/associados")
public class AssociadoController {

	@Autowired
	private AssociadoRepository associadoRepository;
	@Autowired
	private PartidoRepository partidoRepository;

	@GetMapping
	public ResponseEntity<List<AssociadoDto>> get(@RequestParam(required = false) String cargoPolitico,
			@RequestParam(required = false, defaultValue = "id") String ordenacao,
			@RequestParam(required = false, defaultValue = "id") String sentido) {
		List<Associado> associadosEntity = new ArrayList<Associado>();
		if (sentido.equalsIgnoreCase("decrescente")) {
			associadosEntity = associadoRepository.findWithFilters(cargoPolitico,
					Sort.by(Sort.Direction.DESC, ordenacao));
		} else {
			associadosEntity = associadoRepository.findWithFilters(cargoPolitico,
					Sort.by(Sort.Direction.ASC, ordenacao));
		}

		List<AssociadoDto> associadosDto = associadosEntity.stream().map(AssociadoDto::new)
				.collect(Collectors.toList());
		return ResponseEntity.ok(associadosDto);

	}

	@GetMapping("/{id}")
	public ResponseEntity<AssociadoDto> detalhar(@PathVariable Long id) {
		Optional<Associado> associado = associadoRepository.findById(id);
		if (associado.isPresent()) {
			return ResponseEntity.ok(new AssociadoDto(associado.get()));
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@Transactional
	public ResponseEntity<AssociadoDto> cadastrar(@RequestBody @Valid AssociadoForm form,
			UriComponentsBuilder uriBuilder) {
		Associado associado = form.converter();
		associadoRepository.save(associado);

		URI uri = uriBuilder.path("/associados/{id}").buildAndExpand(associado.getId()).toUri();
		return ResponseEntity.created(uri).body(new AssociadoDto(associado));

	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Long id) {
		Optional<Associado> optional = associadoRepository.findById(id);
		if (optional.isPresent()) {
			associadoRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<AssociadoDto> atualizar(@PathVariable Long id,
			@RequestBody @Valid AtualizacaoAssociadoForm form) {
		Optional<Associado> optional = associadoRepository.findById(id);
		if (optional.isPresent()) {
			Associado associado = form.atualizar(id, associadoRepository);
			return ResponseEntity.ok(new AssociadoDto(associado));
		}
		return ResponseEntity.notFound().build();

	}

	@PostMapping("/partidos")
	@Transactional
	public ResponseEntity<AssociadoDto> vincular(@RequestBody VincularAssociadoForm form) {
		Long idPartido = form.getIdPartido();
		Long idAssociado = form.getIdAssociado();
		Optional<Associado> associadoOptional = associadoRepository.findById(idAssociado);
		Optional<Partido> partidoOptional = partidoRepository.findById(idPartido);
		if (associadoOptional.isPresent() || partidoOptional.isPresent()) {
			Partido partido = partidoOptional.get();
			Associado associado = associadoOptional.get();
			associado.setPartido(partido);

			return ResponseEntity.ok(new AssociadoDto(associado));
		}

		return ResponseEntity.notFound().build();

	}
	
	@DeleteMapping("/{id}/partidos/{idPartido}")
	@Transactional
	public ResponseEntity<AssociadoDto> vincular(@PathVariable Long id, @PathVariable Long idPartido) {
		Optional<Associado> associadoOptional = associadoRepository.findById(id);
		Optional<Partido> partidoOptional = partidoRepository.findById(idPartido);
		if (associadoOptional.isPresent() || partidoOptional.isPresent()) {
			Associado associado = associadoOptional.get();
			associado.setPartido(null);

			return ResponseEntity.ok(new AssociadoDto(associado));
		}

		return ResponseEntity.notFound().build();

	}
}	
