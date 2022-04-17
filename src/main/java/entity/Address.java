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
 * Entity class Address.
 */

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Adress")
@NamedQuery(name = "Address", query = "SELECT a from Address a")
public class Address implements Serializable {
    /**
     * Id field.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;
    /**
     * City field.
     */
    @Column
    private String city;
    /**
     * Street field.
     */
    @Column
    private String street;
    /**
     * House field.
     */
    @Column
    private Integer house;
}
