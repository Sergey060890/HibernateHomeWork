package entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Entity class People.
 */

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "People")
@NamedQuery(name = "People", query = "SELECT p from People p")

public class People implements Serializable {
    /**
     * Id field.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;
    /**
     * Name field.
     */
    @Column
    private String name;
    /**
     * Surname field.
     */
    @Column
    private String surname;
    /**
     * Patronymic field.
     */
    @Column
    private String patronymic;
}
