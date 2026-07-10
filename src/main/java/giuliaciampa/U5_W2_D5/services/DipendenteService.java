package giuliaciampa.U5_W2_D5.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import giuliaciampa.U5_W2_D5.DTO.DipendenteRequestDTO;
import giuliaciampa.U5_W2_D5.DTO.DipendenteResponseDTO;
import giuliaciampa.U5_W2_D5.entities.Dipendente;
import giuliaciampa.U5_W2_D5.exceptions.BadRequestException;
import giuliaciampa.U5_W2_D5.exceptions.FileException;
import giuliaciampa.U5_W2_D5.exceptions.NotFoundException;
import giuliaciampa.U5_W2_D5.repositories.DipendenteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Service
@Slf4j
public class DipendenteService {

    private final DipendenteRepository dipendenteRepository;
    private final Cloudinary cloudinary;

    public DipendenteService(DipendenteRepository dipendenteRepository, Cloudinary cloudinary) {
        this.dipendenteRepository = dipendenteRepository;
        this.cloudinary = cloudinary;
    }

    //METODI

    //METODO SAVE

    public DipendenteResponseDTO saveNewDipendente(DipendenteRequestDTO payload) {
        if (this.dipendenteRepository.existsByEmail(payload.email())) {
            throw new BadRequestException("l'email inserita inserita esiste già");
        }

        if (this.dipendenteRepository.existsByUsername(payload.username())) {
            throw new BadRequestException("l'username inserito esiste già");
        }

        Dipendente dipendenteSalvato = new Dipendente(payload.username(), payload.nome(), payload.cognome(), payload.email(), payload.imgProfilo());
        this.dipendenteRepository.save(dipendenteSalvato);
        log.info("il dipendente con username " + dipendenteSalvato.getUsername() + " è stato salvato");

        return new DipendenteResponseDTO(dipendenteSalvato.getId());


    }

    //METODO FIND BY ID
    public Dipendente findById(UUID dipendenteId) {
        return this.dipendenteRepository.findById(dipendenteId)
                .orElseThrow(() -> new NotFoundException("Il dipendente con ID " + dipendenteId + " non è stato trovato"));
    }

    //METODO CARICA IMMAGINE
    public String uploadImgProfilo(UUID dipendenteId, MultipartFile file) {

        long maxSizeBytes = 5 * 1024 * 1024;
        if (file.getSize() > maxSizeBytes) {
            throw new BadRequestException("Il file è troppo grande! Il limite massimo è di 5MB.");
        }

        Dipendente dipendenteTrovato = this.findById(dipendenteId);

        try {
            Map result = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
            String url = (String) result.get("secure_url");

            dipendenteTrovato.setImgProfilo(url);
            this.dipendenteRepository.save(dipendenteTrovato);

            return url;

        } catch (IOException e) {
            throw new FileException(e.getMessage());
        }

    }
}




