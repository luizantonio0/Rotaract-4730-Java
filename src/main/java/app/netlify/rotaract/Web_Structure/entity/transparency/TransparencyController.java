package app.netlify.rotaract.Web_Structure.entity.transparency;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transparency")
@CrossOrigin(origins = "https://rotaract-parque-barigui.netlify.app")
public class TransparencyController {
    private final TransparencyService transparencyService;

    public TransparencyController(TransparencyService transparencyService) {
        this.transparencyService = transparencyService;
    }

    @GetMapping
    public List<Transparency> getTransparencies() {
        return transparencyService.getTransparencies();
    }

    @GetMapping("/{id}")
    public Transparency getTransparency(@PathVariable int id) {
        return transparencyService.getTransparency(id);
    }

    @PostMapping
    public void saveTransparency(@RequestBody Transparency transparency) {
        transparencyService.saveTransparency(transparency);
    }

    @DeleteMapping("/{id}")
    public void deleteTransparency(@PathVariable int id) {
        transparencyService.deleteTransparency(id);
    }

    @PutMapping("/{id}")
    public void updateTransparency(@RequestBody Transparency transparency, @PathVariable int id) {
        transparencyService.updateTransparency(transparency, id);
    }

    @PatchMapping("/{id}")
    public void patchTransparency(@RequestBody Transparency transparency, @PathVariable int id) {
        transparencyService.updateTransparency(transparency, id);
    }
}
