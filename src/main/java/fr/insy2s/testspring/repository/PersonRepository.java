package fr.insy2s.testspring.repository;

import fr.insy2s.testspring.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * REPOSITORY
 * Permet de faire la relation entre BACK et BDD (se connecte à la BDD grâce au parametrage du fichier application.properties
 *
 * INTERFACE: on ne crée que l'entête de la fonction
 */
@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    /**
     * Cherche en BDD toutes les personnes ayant un lastName particulier
     *
     *
     * @param lastName le lastName que l'on cherche en BDD
     * @return la liste de personne ayant le nom de famille
     */
    @Query(
        nativeQuery = true,                                         //nativeQuery spécifie qu'on va faire de la requete SQL
        value = "select * from person where last_name = :lastName"  //Requete SQL. :lastName spécifie qu'il s'agit d'une variable
    )
    List<Person> findByLastName(@Param("lastName") String lastName); //le @Param spécifie qu'il s'agit d'un paramêtre utilisé dans une requete SQL

}
