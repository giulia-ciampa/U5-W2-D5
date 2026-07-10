package giuliaciampa.U5_W2_D5.repositories;

import giuliaciampa.U5_W2_D5.entities.Viaggio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.UUID;

@Repository
public interface ViaggioRepository extends JpaRepository<Viaggio, UUID> {
    boolean existsByData(LocalDate data);
}
