package giuliaciampa.U5_W2_D5.controllers;


import giuliaciampa.U5_W2_D5.DTO.PrenotazioneRequestDTO;
import giuliaciampa.U5_W2_D5.DTO.PrenotazioneResponseDTO;
import giuliaciampa.U5_W2_D5.entities.Prenotazione;
import giuliaciampa.U5_W2_D5.exceptions.ValidationException;
import giuliaciampa.U5_W2_D5.services.PrenotazioneService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/prenotazioni")

public class PrenotazioneController {
    private final PrenotazioneService prenotazioneService;

    public PrenotazioneController(PrenotazioneService prenotazioneService) {
        this.prenotazioneService = prenotazioneService;
    }

    //POST--> http://localhost:3001/prenotazioni + richiesta payload
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PrenotazioneResponseDTO saveNewPrenotazione(@RequestBody @Validated PrenotazioneRequestDTO payload, BindingResult validationResult) {
        if (validationResult.hasErrors()) {

            List<String> errorsList = validationResult.getFieldErrors().stream().map(fieldError -> fieldError.getDefaultMessage()).toList();

            throw new ValidationException(errorsList);

        }

        return this.prenotazioneService.save(payload);
    }

    //GET --> findById
    @GetMapping(("/{id}"))
    public Prenotazione findById(@PathVariable UUID id) {
        return this.prenotazioneService.findById(id);
    }

}
