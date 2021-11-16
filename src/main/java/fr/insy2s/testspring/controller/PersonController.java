package fr.insy2s.testspring.controller;

import fr.insy2s.testspring.model.Person;
import fr.insy2s.testspring.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * CONTROLLER SPRING PERMETTANT DE FAIRE DES REQUESTS
 *
 * @RestController permet de spécifier qu'il s'agit d'un controller pour une API, donc simple envoi de données brutes
 *
 */
@RestController
public class PersonController {

    /**
     * Le @Autowired est de l'INJECTION de DEPENDANCE. Spring relie automatiquement PersonController et PersonRepository
     */
    @Autowired
    private PersonRepository personRepository;

    /**
     * Récup tout le monde
     *
     * Requête du type GET sur l'URL http://localhost:8080/people
     *
     * @return List de Person venant de la BDD
     */
    @GetMapping("/people")
    public List<Person> getAllPeople(){
        List<Person> people = personRepository.findAll(); //methode native de JpaRepository pour récuperer toute la table en BDD
        return people;
    }

    /**
     * Récupère toute les personnes de la BDD ayant un nom de famille particulié
     *
     * Requête du type GET sur l'URL http://localhost:8080/people/last-name/:lastName
     * lastName étant un paramètre d'URL (ou paramêtre @PathVariable)
     *
     * @param lastName le lastName dont on cherche les Person
     * @return List de Person de la BDD ayant le lastName
     */
    @GetMapping("/people/last-name/{lastName}")
    public List<Person> getAllPeopleByLastName(@PathVariable String lastName){
        List<Person> people = personRepository.findByLastName(lastName); //methode non-native de JpaRepository. Donc a été créé à la main dans l'interface
        return people;
    }

    /**
     * Récupérer une personne selon son ID
     *
     * Requête du type GET sur l'URL http://localhost:8080/people/:id
     * id étant un paramètre d'URL (ou paramêtre @PathVariable)
     *
     * @param id l'id de la personne que l'on veut récupérer
     * @return la person venant de la BDD
     */
    @GetMapping("/people/{id}")
    public Person getPersonById(@PathVariable Long id){
        Person person = personRepository.findById(id).get(); //methode native de JpaRepository pour récuperer un objet selon son ID
        return person;
    }

    /**
     * Enregistrer une Person. On vérifie que la person
     *
     * Requête du type POST sur l'URL http://localhost:8080/people
     * Recupère la person en BODY  de request d'où le @RequestBody pour spécifier à Spring d'où on récupère l'objet Person
     *
     * @param person la personne à enregistrer
     * @return La personne enregistré
     *
     */
    @PostMapping("/people")
    public Person savePerson(@RequestBody Person person){
        if(person.getId() == null) {
            person = personRepository.save(person); //methode native de JpaRepository pour enregistrer en BDD (parce que l'ID est null, sinon cela serait un UPDATE)
            return person;
        }
        return null;
    }

    /**
     * Modifier une Person. On vérifie que la person envoyée a bien un id
     *
     * Requête du type PUT sur l'URL http://localhost:8080/people
     * Recupère la person en BODY  de request d'où le @RequestBody pour spécifier à Spring d'où on récupère l'objet Person
     *
     * @param person la personne à modifier
     * @return la personne modifiée
     */
    @PutMapping("/people")
    public Person updatePerson(@RequestBody Person person){
        if(person.getId() != null) {
            person = personRepository.save(person); //methode native de JpaRepository pour mettre à jour en BDD (parce que l'ID est non null, sinon cela serait un INSERT)
            return person;
        }
        return null;
    }

    /**
     * Supprimer une Person selon son ID
     *
     * Requête du type DELETE sur l'URL http://localhost:8080/people/:id
     * id étant un paramètre d'URL (ou paramêtre @PathVariable)
     *
     * @param id l'ID à supprimer
     *
     */
    @DeleteMapping("/people/{id}")
    public void deletePerson(@PathVariable Long id){
        personRepository.deleteById(id); //methode native de JpaRepository supprimer en bdd selon l'ID
    }

}