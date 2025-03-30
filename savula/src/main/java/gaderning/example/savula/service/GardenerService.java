package gaderning.example.savula.service;
import gaderning.example.savula.model.Gardener;
import gaderning.example.savula.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GardenerService {
    private final GardenerRespository gardenerRespository;

    @Autowired
    public GardenerService(GardenerRespository gardenerRespository) {
        this.gardenerRespository = gardenerRespository;
    }

    // Create or Update Gardener
    public Gardener saveGardener(Gardener gardener) {
        return gardenerRespository.save(gardener);
    }

    // Get All Gardeners
    public List<Gardener> getAllGardeners() {
        return gardenerRespository.findAll();
    }

    // Get Gardener by ID
    public Optional<Gardener> getGardenerById(Long id) {
        return gardenerRespository.findById(id);
    }

    // Delete Gardener by ID
    public void deleteGardener(Long id) {
        gardenerRespository.deleteById(id);
    }

    // Update Gardener
    public Gardener updateGardener(Long id, Gardener gardenerDetails) {
        return gardenerRespository.findById(id).map(gardener -> {
            gardener.setName(gardenerDetails.getName());
            gardener.setSpecialization(gardenerDetails.getSpecialization());
            return gardenerRespository.save(gardener);
        }).orElseThrow(() -> new RuntimeException("Gardener not found with id: " + id));
    
    }
    }