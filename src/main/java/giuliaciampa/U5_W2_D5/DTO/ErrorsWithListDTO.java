package giuliaciampa.U5_W2_D5.DTO;

import java.time.LocalDateTime;
import java.util.List;

public record ErrorsWithListDTO(String message, LocalDateTime time, List<String> errorsList) {
}
