package guru.springframework.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

/**
 * @author jmsantiago on 09/04/2020
 */
@Data
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;

    @ManyToMany(mappedBy = "categories")
    private Set<Recipe> recipes;

}
