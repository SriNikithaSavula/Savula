package gaderning.example.savula.repository;
import gaderning.example.savula.model.Gardener;
import org.springframework.data.jpa.repository.JpaRepository;
public interface GardenerRespository extends JpaRepository<Gardener, Long> {
    
}
