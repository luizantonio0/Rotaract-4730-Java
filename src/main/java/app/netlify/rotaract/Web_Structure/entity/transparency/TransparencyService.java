package app.netlify.rotaract.Web_Structure.entity.transparency;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransparencyService {
    private final TransparencyRepository transparencyRepository;

    public TransparencyService(TransparencyRepository transparencyRepository) {
        this.transparencyRepository = transparencyRepository;
    }

    public Transparency getTransparency(int id) {
        return transparencyRepository.get(id);
    }

    public List<Transparency> getTransparencies() {
        return transparencyRepository.getAll();
    }

    public void saveTransparency(Transparency transparency) {
        transparencyRepository.save(transparency);
    }

    public void deleteTransparency(int id) {
        transparencyRepository.delete(id);
    }

    public void updateTransparency(Transparency transparency, int id){
        transparencyRepository.update(transparency, id);
    }
}
