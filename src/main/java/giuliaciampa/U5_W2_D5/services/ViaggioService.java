package giuliaciampa.U5_W2_D5.services;

import giuliaciampa.U5_W2_D5.DTO.ViaggioRequestDTO;
import giuliaciampa.U5_W2_D5.DTO.ViaggioResponseDTO;
import giuliaciampa.U5_W2_D5.entities.StatoViaggio;
import giuliaciampa.U5_W2_D5.entities.Viaggio;
import giuliaciampa.U5_W2_D5.exceptions.BadRequestException;
import giuliaciampa.U5_W2_D5.repositories.ViaggioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
public class ViaggioService {
    private final ViaggioRepository viaggioRepository;

    public ViaggioService(ViaggioRepository viaggioRepository) {
        this.viaggioRepository = viaggioRepository;
    }

    //METODI

    //SAVE

    public ViaggioResponseDTO saveNewViaggio(ViaggioRequestDTO payload) {
        if (this.viaggioRepository.existsByData(payload.data())) {
            throw new BadRequestException("Attenzione! C'è già un viaggio pianificato per la data " + payload.data() + ". Scegli un altro giorno.");
        }


        Viaggio viaggioSalvato = new Viaggio(payload.destinazione(), payload.data());

        this.viaggioRepository.save(viaggioSalvato);

        return new ViaggioResponseDTO(viaggioSalvato.getId());

    }

    //FIND BY ID
    public Viaggio findViaggioById(UUID id) {
        return this.viaggioRepository.findById(id).orElseThrow(() -> new BadRequestException("il viaggio con id " + id + " non è stato trovato"));
    }

    //MODIFICA STATO VIAGGIO
    public Viaggio updateStatoViaggio(UUID id, StatoViaggio stato) {
        Viaggio viaggioTrovato = this.findViaggioById(id);

        if (stato == StatoViaggio.COMPLETATO) {
            if (viaggioTrovato.getData().isAfter(LocalDate.now())) {
                throw new BadRequestException("Non puoi impostare un viaggio come COMPLETATO se la data è nel futuro!");
            }
        }

        if (viaggioTrovato.getStatoViaggio() == stato) {
            return viaggioTrovato;
        }

        viaggioTrovato.setStatoViaggio(stato);
        return this.viaggioRepository.save(viaggioTrovato);
    }

    //CANCELLA VIAGGIO
    public void deleteViaggio(UUID id) {
        this.viaggioRepository.deleteById(id);
    }

}
