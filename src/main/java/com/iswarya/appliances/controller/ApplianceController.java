package com.iswarya.appliances.controller;

import com.iswarya.appliances.exceptions.OperationNotAllowed;
import com.iswarya.appliances.exceptions.ResourceNotFound;
import com.iswarya.appliances.model.Appliance;
import com.iswarya.appliances.repository.ApplianceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
// We need to specify just our frontend application url as origin to avoid CORS issues
@CrossOrigin(origins = "*")
public class ApplianceController {

    @Autowired
    private ApplianceRepository applianceRepository;

    @GetMapping("/appliances")
    public List<Appliance> getAllAppliances() {
         return  applianceRepository.findAll();
    }

    @PostMapping("/appliances")
    public Appliance createAppliance(@RequestBody Appliance appliance) {
        String serialNumber = appliance.getSerialNumber();
        String brand = appliance.getBrand();
        String model = appliance.getModel();
        List<Appliance> appliances = applianceRepository.findBySerialNumberBrandAndModel(serialNumber, brand, model);

        if(!appliances.isEmpty()) {
            throw new OperationNotAllowed("The appliance already exits.  ");

        }

        return  applianceRepository.save(appliance);
    }

    @GetMapping("/appliances/{id}")
    public Appliance getApplianceById( @PathVariable Long id) {
        Appliance appliance =  applianceRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("Appliance with" + id +" is not available"));
        return appliance;
    }
    @PutMapping("/appliances/{id}")
    public Appliance updateAppliance(@PathVariable Long id, @RequestBody Appliance appliance) {
        System.out.println("Update appliance called");
        Appliance applianceFound =  applianceRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("Appliance with" + id +" is not available"));
        applianceFound.setSerialNumber(appliance.getSerialNumber());
        applianceFound.setBrand(appliance.getBrand());
        applianceFound.setModel(appliance.getModel());
        applianceFound.setStatus(appliance.getStatus());
        applianceFound.setDate(appliance.getDate());
        System.out.println(appliance);
        return applianceRepository.save(applianceFound);
    }

    @DeleteMapping("/appliances/{id}")
    public boolean deleteAppliance(@PathVariable Long id) {
        Appliance applianceFound =  applianceRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("Appliance with" + id +" is not available"));
        applianceRepository.delete(applianceFound);
        return true;
    }

}
