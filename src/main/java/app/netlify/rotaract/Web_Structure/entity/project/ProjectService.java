package app.netlify.rotaract.Web_Structure.entity.project;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public Project getProject(int id) {
        return projectRepository.get(id);
    }

    public List<Project> getProjects() {
        return projectRepository.getAll();
    }

    public void saveProject(Project project) {
        projectRepository.save(project);
    }

    public void deleteProject(int id) {
        projectRepository.delete(id);
    }

    public void updateProject(Project project, int id){
        projectRepository.update(project, id);
    }
}
