package gaderning.example.savula.Controller;

import gaderning.example.savula.model.Gardener;
import gaderning.example.savula.service.GardenerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/gardeners")
public class GardenerController {

    private final GardenerService gardenerService;

    public GardenerController(GardenerService gardenerService) {
        this.gardenerService = gardenerService;
    }

    @GetMapping("/get")
    public List<Gardener> getAllGardeners() {
        return gardenerService.getAllGardeners();
    }

    @GetMapping("/get/{id}")
    public Optional<Gardener> getGardenerById(@PathVariable Long id) {
        return gardenerService.getGardenerById(id);
    }

    @PostMapping("/add")
    public Gardener createGardener(@RequestBody Gardener gardener) {
        return gardenerService.saveGardener(gardener);
    }

    @PutMapping("/put/{id}")
    public Gardener updateGardener(@PathVariable Long id, @RequestBody Gardener gardenerDetails) {
        return gardenerService.updateGardener(id, gardenerDetails);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteGardener(@PathVariable Long id) {
        gardenerService.deleteGardener(id);
    }
}
