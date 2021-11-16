package fr.insy2s.testspring.controller;

import fr.insy2s.testspring.model.Competence;
import fr.insy2s.testspring.repository.CompetenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CompetenceController {

    @Autowired
    private CompetenceRepository competenceRepository;

    /**
     * Récup toute les Competences
     *
     * @return
     */
    @GetMapping("/competences")
    public List<Competence> getAllCompetences(){
        List<Competence> competence = competenceRepository.findAll();
        return competence;
    }

    /**
     * Récupérer une Competence selon son ID
     *
     * @param id
     * @return
     */
    @GetMapping("/competences/{id}")
    public Competence getCompetenceById(@PathVariable Long id){
        Competence competence = competenceRepository.findById(id).get();
        return competence;
    }

    /**
     * Enregistrer une Competence
     *
     * @param competence la Competence à enregistrer
     * @return La Competence enregistré
     *
     */
    @PostMapping("/competences")
    public Competence saveCompetence(@RequestBody Competence competence){
        if(competence.getId() == null) {
            competence = competenceRepository.save(competence);
            return competence;
        }
        return null;
    }

    /**
     * Modifier une Competence
     *
     * @param competence la Competence à modifier
     * @return la Competence modifiée
     */
    @PutMapping("/competences")
    public Competence updateCompetence(@RequestBody Competence competence){
        if(competence.getId() != null) {
            competence = competenceRepository.save(competence);
            return competence;
        }
        return null;
    }

    /**
     * Supprimer une Competence selon son ID
     *
     * @param id l'ID à supprimer
     *
     */
    @DeleteMapping("/competences/{id}")
    public void deleteCompetence(@PathVariable Long id){
        competenceRepository.deleteById(id);
    }

}
