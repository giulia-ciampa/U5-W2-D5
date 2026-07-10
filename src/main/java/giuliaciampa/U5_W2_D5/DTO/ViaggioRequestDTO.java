package giuliaciampa.U5_W2_D5.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record ViaggioRequestDTO(
        @NotBlank(message = "il campo non può essere vuoto")
        String destinazione,
        
        @Future(message = "la data deve essere una data futura")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        LocalDate data) {
}
