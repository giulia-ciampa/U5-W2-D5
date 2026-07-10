package giuliaciampa.U5_W2_D5.repositories;

import giuliaciampa.U5_W2_D5.entities.Prenotazione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PrenotazioneRepository extends JpaRepository<Prenotazione, UUID> {
    boolean existsByDipendenteIdAndViaggioId(UUID dipendenteId, UUID viaggioId);
}
