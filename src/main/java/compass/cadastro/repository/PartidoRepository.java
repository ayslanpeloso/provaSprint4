package compass.cadastro.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import compass.cadastro.entidades.Partido;

public interface PartidoRepository extends JpaRepository<Partido, Long>{

	List<Partido> findByIdeologia(String partido);

}
