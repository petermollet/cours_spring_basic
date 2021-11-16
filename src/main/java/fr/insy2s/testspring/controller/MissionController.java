package fr.insy2s.testspring.controller;

import fr.insy2s.testspring.model.Mission;
import fr.insy2s.testspring.repository.MissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MissionController {

    @Autowired
    private MissionRepository missionRepository;

    /**
     * Récup toute les Missions
     *
     * @return
     */
    @GetMapping("/missions")
    public List<Mission> getAllMissions(){
        List<Mission> missions = missionRepository.findAll();
        return missions;
    }

    /**
     * Récupérer une Mission selon son ID
     *
     * @param id
     * @return
     */
    @GetMapping("/missions/{id}")
    public Mission getMissionById(@PathVariable Long id){
        Mission mission = missionRepository.findById(id).get();
        return mission;
    }

    /**
     * Enregistrer une Mission
     *
     * @param mission la Mission à enregistrer
     * @return La Mission enregistré
     *
     */
    @PostMapping("/missions")
    public Mission saveMission(@RequestBody Mission mission){
        if(mission.getId() == null) {
            mission = missionRepository.save(mission);
            return mission;
        }
        return null;
    }

    /**
     * Modifier une Mission
     *
     * @param mission la Mission à modifier
     * @return la Mission modifiée
     */
    @PutMapping("/missions")
    public Mission updateMission(@RequestBody Mission mission){
        if(mission.getId() != null) {
            mission = missionRepository.save(mission);
            return mission;
        }
        return null;
    }

    /**
     * Supprimer une Mission selon son ID
     *
     * @param id l'ID à supprimer
     *
     */
    @DeleteMapping("/missions/{id}")
    public void deleteMission(@PathVariable Long id){
        missionRepository.deleteById(id);
    }

}
