package giuliaciampa.U5_W2_D5.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@ToString
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "dipendenti")

public class Dipendente {

    //ATTRIBUTI
    @Id
    @GeneratedValue
    private UUID id;

    @Column(unique = true, nullable = false, length = 20)
    private String username;

    @Column(length = 20, nullable = false)
    private String nome;

    @Column(length = 20, nullable = false)
    private String cognome;

    @Column(length = 40, nullable = false, unique = true)
    private String email;

    private String imgProfilo;

    //COSTRUTTORE
    public Dipendente(String username, String nome, String cognome, String email, String imgProfilo) {
        this.username = username;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;

        if (imgProfilo == null || imgProfilo.isBlank()) {
            this.imgProfilo = "https://ui-avatars.com/api/?name=" + nome + "+" + cognome;
        } else {
            this.imgProfilo = imgProfilo;
        }
    }


}

