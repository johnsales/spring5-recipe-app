package guru.springframework.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * @author jmsantiago on 09/04/2020
 */
@Data
@Entity
public class Notes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Recipe recipe;

    @Lob
    private String recipeNotes;

}
