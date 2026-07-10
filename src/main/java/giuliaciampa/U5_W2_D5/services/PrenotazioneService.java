package giuliaciampa.U5_W2_D5.services;

import giuliaciampa.U5_W2_D5.DTO.PrenotazioneRequestDTO;
import giuliaciampa.U5_W2_D5.DTO.PrenotazioneResponseDTO;
import giuliaciampa.U5_W2_D5.entities.Dipendente;
import giuliaciampa.U5_W2_D5.entities.Prenotazione;
import giuliaciampa.U5_W2_D5.entities.Viaggio;
import giuliaciampa.U5_W2_D5.exceptions.BadRequestException;
import giuliaciampa.U5_W2_D5.exceptions.NotFoundException;
import giuliaciampa.U5_W2_D5.repositories.PrenotazioneRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
public class PrenotazioneService {
    private final PrenotazioneRepository prenotazioneRepository;
    private final DipendenteService dipendenteService;
    private final ViaggioService viaggioService;


    public PrenotazioneService(PrenotazioneRepository prenotazioneRepository, DipendenteService dipendenteService, ViaggioService viaggioService) {
        this.prenotazioneRepository = prenotazioneRepository;
        this.dipendenteService = dipendenteService;
        this.viaggioService = viaggioService;
    }

    //SAVE
    public PrenotazioneResponseDTO save(PrenotazioneRequestDTO payload) {
        if (prenotazioneRepository.existsByDipendenteIdAndViaggioId(payload.dipendenteId(), payload.viaggioId())) {
            throw new BadRequestException("Il dipendente ha già una prenotazione per questo viaggio!");
        }


        Dipendente dipendenteTrovato = dipendenteService.findById(payload.dipendenteId());
        Viaggio viaggioTrovato = viaggioService.findViaggioById(payload.viaggioId());


        Prenotazione nuovaPrenotazione = new Prenotazione();
        nuovaPrenotazione.setDipendente(dipendenteTrovato);
        nuovaPrenotazione.setViaggio(viaggioTrovato);
        nuovaPrenotazione.setNotaPreferenze(payload.notaPreferenze());
        nuovaPrenotazione.setDataRichiesta(LocalDate.now());


        this.prenotazioneRepository.save(nuovaPrenotazione);
        return new PrenotazioneResponseDTO(nuovaPrenotazione.getId());
    }

    //FINDBYID
    public Prenotazione findById(UUID id) {
        return this.prenotazioneRepository.findById(id).orElseThrow(() -> new NotFoundException("la prenotazione con id " + id + " non è stata trovata"));

    }


}
