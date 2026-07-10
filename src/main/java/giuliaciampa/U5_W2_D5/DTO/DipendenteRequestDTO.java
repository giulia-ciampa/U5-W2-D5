package giuliaciampa.U5_W2_D5.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.URL;

public record DipendenteRequestDTO(

        @NotBlank(message = "l'username è obbligatorio")
        @Size(min = 4, max = 20)
        String username,

        @NotBlank(message = "il nome è obbligatorio")
        @Size(min = 2, max = 20)
        String nome,

        @NotBlank(message = "il cognome è obbligatorio")
        @Size(min = 2, max = 20)
        String cognome,

        @NotBlank(message = "l'email è obbligatoria")
        @Email(message = "l'email deve essere nel formato corretto")
        String email,
        
        @URL
        String imgProfilo) {
}
