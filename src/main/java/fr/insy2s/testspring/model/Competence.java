package fr.insy2s.testspring.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.List;

@Entity
public class Competence {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "competence_seq")
    @SequenceGenerator(name = "competence_seq")
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "competences")
    @JsonProperty(value = "people")
    private List<Person> people;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Person> getPeople() {
        return people;
    }

    public void setPeople(List<Person> people) {
        this.people = people;
    }

    @Override
    public String toString() {
        return "CompetenceDAO{" + "id=" + id + ", name='" + name + '\'' + '}';
    }
}
