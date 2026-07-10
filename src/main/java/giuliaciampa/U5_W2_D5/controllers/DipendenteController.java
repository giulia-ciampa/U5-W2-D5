package giuliaciampa.U5_W2_D5.controllers;

import giuliaciampa.U5_W2_D5.DTO.DipendenteRequestDTO;
import giuliaciampa.U5_W2_D5.DTO.DipendenteResponseDTO;
import giuliaciampa.U5_W2_D5.entities.Dipendente;
import giuliaciampa.U5_W2_D5.exceptions.ValidationException;
import giuliaciampa.U5_W2_D5.services.DipendenteService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/dipendenti")

public class DipendenteController {

    DipendenteService dipendenteService;

    DipendenteController(DipendenteService dipendenteService) {
        this.dipendenteService = dipendenteService;
    }

    //1.POST --> http://localhost:3001/dipendenti + richiesta payload
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DipendenteResponseDTO saveNewDipendente(@RequestBody @Validated DipendenteRequestDTO payload, BindingResult validationResult) {
        if (validationResult.hasErrors()) {

            List<String> errorsList = validationResult.getFieldErrors().stream().map(fieldError -> fieldError.getDefaultMessage()).toList();

            throw new ValidationException(errorsList);

        }

        return this.dipendenteService.saveNewDipendente(payload);
    }

    //2. GET --> FINDBYID --> http://localhost:3001/dipendenti/id
    @GetMapping("/{id}")
    public Dipendente findById(@PathVariable UUID id) {
        return this.dipendenteService.findById(id);
    }

    //3. PATCH PER L'IMMAGINE --> http://localhost:3001/dipendenti/{id}/imgProfilo
    @PatchMapping("/{id}/imgProfilo")
    @ResponseStatus(HttpStatus.OK)
    public String uploadAvatar(@PathVariable UUID id, @RequestParam("uploadImgProfilo") MultipartFile file) {
        return this.dipendenteService.uploadImgProfilo(id, file);
    }


}
