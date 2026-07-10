package giuliaciampa.U5_W2_D5.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "prenotazioni")
public class Prenotazione {

    //ATTRIBUTI

    @Id
    @GeneratedValue
    private UUID id;


    @ManyToOne
    @JoinColumn(name = "viaggio_id", nullable = false)
    private Viaggio viaggio;

    @Column(name = "data_richiesta", nullable = false)
    private LocalDate dataRichiesta;

    @Column(name = "nota_preferenze", length = 100)
    private String notaPreferenze;


    @ManyToOne
    @JoinColumn(name = "dipendente_id", nullable = false)
    private Dipendente dipendente;

    //COSTRUTTORE
    public Prenotazione(Viaggio viaggio, LocalDate dataRichiesta, String notaPreferenze, Dipendente dipendente) {
        this.viaggio = viaggio;
        this.dataRichiesta = dataRichiesta;
        this.notaPreferenze = notaPreferenze;
        this.dipendente = dipendente;
    }

}
