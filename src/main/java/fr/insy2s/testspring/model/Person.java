package fr.insy2s.testspring.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.List;


/**
 * Entité représentant un objet de BDD
 *
 * Différents noms: DAO, Model, Entity, Bean, Domain
 *
 * . @Entity dit à spring que cette classe est une entité
 */
@Entity
public class Person {

    @Id //spécifie à spring que ce champ est une clé primaire de la table
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "person_seq") //spécifie que l'ID est généré automatiquement par la BDD
    @SequenceGenerator(name = "person_seq") //spécifie le nom de la sequence pour l'ID
    private Long id;

    private String lastName;
    private String firstName;

    //RELATION ADDRESS: OneToONe
    @OneToOne
    private Address address;


    //RELATION MISSION: OneToMAny
    @OneToMany(mappedBy = "person") //mappedBy référence l'attribut person dans l'entité Mission
    @JsonProperty("missions")       //Dit que l'on associe ce champ à une Propriété nommé "mission", qui pour être ignoré dans l'entité Mission
    private List<Mission> missions;


    //RELATION COMPETENCE: ManyToMany
    @ManyToMany
    @JoinTable(
        name = "people_competences",
        joinColumns = @JoinColumn(name = "person_id"),
        inverseJoinColumns = @JoinColumn(name = "competence_id")
    )
    @JsonIgnoreProperties(value = "people") //Dit que l'on ignore l'attribut ayant le @JsonPropertie("people") dans Competence
    private List<Competence> competences;


    //  GETTERS-SETTER

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Mission> getMissions() {
        return missions;
    }

    public void setMissions(List<Mission> missions) {
        this.missions = missions;
    }

    public List<Competence> getCompetences() {
        return competences;
    }

    public void setCompetences(List<Competence> competences) {
        this.competences = competences;
    }

    // TO STRING
    @Override
    public String toString() {
        return "Person{" + "id=" + id + ", lastName='" + lastName + '\'' + ", firstName='" + firstName + '\'' + '}';
    }


}
