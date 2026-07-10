package giuliaciampa.U5_W2_D5.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.UUID;

@ToString
@Entity
@Table(name = "viaggi")
@NoArgsConstructor
@Getter
@Setter

public class Viaggio {
    //ATTRIBUTI
    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false, length = 40)
    private String destinazione;

    @Column(nullable = false)
    private LocalDate data;

    @Column(name = "stato_viaggio", nullable = false, length = 30)
    @Enumerated(EnumType.STRING)
    private StatoViaggio statoViaggio;

    //COSTRUTTORE
    public Viaggio(String destinazione, LocalDate data) {
        this.destinazione = destinazione;
        this.data = data;
        this.statoViaggio = StatoViaggio.IN_PROGRAMMA;
    }
}
