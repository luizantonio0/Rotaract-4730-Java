package app.netlify.rotaract.Web_Structure.entity.project;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/project")
@CrossOrigin(origins = "https://rotaract-parque-barigui.netlify.app")
public class ProjectController {
    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    public List<Project> getProjects() {
        return projectService.getProjects();
    }

    @GetMapping("/{id}")
    public Project getProject(@PathVariable int id) {
        return projectService.getProject(id);
    }

    @PostMapping
    public void saveProject(@RequestBody Project project) {
        projectService.saveProject(project);
    }

    @DeleteMapping("/{id}")
    public void deleteProject(@PathVariable int id) {
        projectService.deleteProject(id);
    }

    @PutMapping("/{id}")
    public void updateProject(@RequestBody Project project, @PathVariable int id) {
        projectService.updateProject(project, id);
    }

    @PatchMapping("/{id}")
    public void patchProject(@RequestBody Project project, @PathVariable int id) {
        projectService.updateProject(project, id);
    }
}
