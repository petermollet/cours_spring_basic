package fr.insy2s.testspring.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Mission {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mission_seq")
    @SequenceGenerator(name = "mission_seq")
    private Long id;

    private String name;
    private LocalDate startedAt;
    private LocalDate finishedAt;

    @ManyToOne
    @JsonIgnoreProperties(value = "missions")
    private Person person;

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

    public LocalDate getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(LocalDate startedAt) {
        this.startedAt = startedAt;
    }

    public LocalDate getFinishedAt() {
        return finishedAt;
    }

    public void setFinishedAt(LocalDate finishedAt) {
        this.finishedAt = finishedAt;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return "MissionDAO{" + "id=" + id + ", name='" + name + '\'' + ", startedAt=" + startedAt + ", finishedAt=" + finishedAt + ", person=" + person + '}';
    }
}
