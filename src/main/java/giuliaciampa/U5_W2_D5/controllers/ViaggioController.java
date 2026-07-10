package giuliaciampa.U5_W2_D5.controllers;

import giuliaciampa.U5_W2_D5.DTO.StatoViaggioDTO;
import giuliaciampa.U5_W2_D5.DTO.ViaggioRequestDTO;
import giuliaciampa.U5_W2_D5.DTO.ViaggioResponseDTO;
import giuliaciampa.U5_W2_D5.entities.Viaggio;
import giuliaciampa.U5_W2_D5.exceptions.ValidationException;
import giuliaciampa.U5_W2_D5.services.ViaggioService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/viaggi")

public class ViaggioController {

    private final ViaggioService viaggioService;

    public ViaggioController(ViaggioService viaggioService) {
        this.viaggioService = viaggioService;
    }

    //1. POST --> http://localhost:3001/viaggi + richiesta payload
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ViaggioResponseDTO saveNewViaggio(@RequestBody @Validated ViaggioRequestDTO payload, BindingResult validationResult) {
        if (validationResult.hasErrors()) {

            List<String> errorsList = validationResult.getFieldErrors().stream().map(fieldError -> fieldError.getDefaultMessage()).toList();

            throw new ValidationException(errorsList);

        }

        return this.viaggioService.saveNewViaggio(payload);
    }


    //2. GET --> FIND BY ID --> http://localhost:3001/viaggi/id
    @GetMapping("/{id}")
    public Viaggio findViaggioById(@PathVariable UUID id) {

        return this.viaggioService.findViaggioById(id);
    }

    //3. PATCH --> CAMBIO STATO VIAGGIO --> http://localhost:3001/viaggi/id
    @PatchMapping("/{id}")
    public Viaggio updateStatoViaggio(@PathVariable UUID id, @RequestBody StatoViaggioDTO payload) {
        return this.viaggioService.updateStatoViaggio(id, payload.stato());
    }

    //3.DELETE --> http://localhost:3001/viaggi/id
    @DeleteMapping("/{id}")
    public void deleteViaggio(@PathVariable UUID id) {
        this.viaggioService.deleteViaggio(id);
    }

}

