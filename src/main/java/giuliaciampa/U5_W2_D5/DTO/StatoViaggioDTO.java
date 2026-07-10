package giuliaciampa.U5_W2_D5.DTO;

import giuliaciampa.U5_W2_D5.entities.StatoViaggio;
import jakarta.validation.constraints.NotNull;

public record StatoViaggioDTO(
        @NotNull(message = "Lo stato è obbligatorio")
        StatoViaggio stato) {
}
