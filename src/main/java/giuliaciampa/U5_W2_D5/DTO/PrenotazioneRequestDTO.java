package giuliaciampa.U5_W2_D5.DTO;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record PrenotazioneRequestDTO(
        @NotNull(message = "l'id del viaggio è obbligatorio")
        UUID viaggioId,

        String notaPreferenze,

        @NotNull(message = "L'id del dipendente è obbligatorio")
        UUID dipendenteId) {
}
