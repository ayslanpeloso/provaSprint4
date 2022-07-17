package compass.cadastro.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import compass.cadastro.controller.dto.AssociadoDto;
import compass.cadastro.entidades.Associado;
import compass.cadastro.entidades.Partido;

public interface AssociadoRepository extends JpaRepository<Associado, Long>{

	List<Associado> findByCargoPolitico(String associado);

	 @Query("SELECT associado FROM Associado associado WHERE (:cargoPolitico IS NULL OR :cargoPolitico = associado.cargoPolitico)")
	List<Associado> findWithFilters(@Param("cargoPolitico") String cargoPolitico, Sort sort);

	List<Associado> findByPartido_id(Long id);
	
	

}
