package fr.insy2s.testspring.controller;

import fr.insy2s.testspring.model.Address;
import fr.insy2s.testspring.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AddressController {

    @Autowired
    private AddressRepository addressRepository;


    /**
     * Récup toute les Addresses
     *
     * @return
     */
    @GetMapping("/addresses")
    public List<Address> getAllAddresses(){
        List<Address> addresses = addressRepository.findAll();
        return addresses;
    }

    /**
     * Récupérer une Address selon son ID
     *
     * @param id
     * @return
     */
    @GetMapping("/addresses/{id}")
    public Address getAddressById(@PathVariable Long id){
        Address address = addressRepository.findById(id).get();
        return address;
    }

    /**
     * Enregistrer une Address
     *
     * @param address la Address à enregistrer
     * @return La Address enregistré
     *
     */
    @PostMapping("/addresses")
    public Address saveAddress(@RequestBody Address address){
        if(address.getId() == null) {
            address = addressRepository.save(address);
            return address;
        }
        return null;
    }

    /**
     * Modifier une Address
     *
     * @param address la Address à modifier
     * @return la Address modifiée
     */
    @PutMapping("/addresses")
    public Address updateAddress(@RequestBody Address address){
        if(address.getId() != null) {
            address = addressRepository.save(address);
            return address;
        }
        return null;
    }

    /**
     * Supprimer une Address selon son ID
     *
     * @param id l'ID à supprimer
     *
     */
    @DeleteMapping("/addresses/{id}")
    public void deleteAddress(@PathVariable Long id){
        addressRepository.deleteById(id);
    }

}
