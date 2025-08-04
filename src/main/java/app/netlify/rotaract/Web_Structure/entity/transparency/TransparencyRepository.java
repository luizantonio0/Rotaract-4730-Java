package app.netlify.rotaract.Web_Structure.entity.transparency;

import app.netlify.rotaract.Web_Structure.dbConnection.DataRepository;
import app.netlify.rotaract.Web_Structure.entity.RepositoryI;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.security.GeneralSecurityException;
import java.util.List;

@Repository
public class TransparencyRepository extends DataRepository<Transparency> implements RepositoryI<Transparency> {

    private final Class<Transparency> tClass = Transparency.class;
    private final String SPREADSHEET_NAME = "Transparency";
    private final String SPREADSHEET_ID = "1qt1Ql7xTzxc5SiRo3tB3-lpBc7zBM_4l0vXqwLPCnSs";

    @Override
    public List<Transparency> getAll(){
        try {
            return this.getAllInSpreadSheet(SPREADSHEET_ID, SPREADSHEET_NAME, tClass);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (GeneralSecurityException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Transparency transparency, int id) {
        this.updateInSpreadSheet(transparency, id, SPREADSHEET_ID, SPREADSHEET_NAME);
    }

    @Override
    public Transparency get(int id){
        try {
            return this.getInSpreadSheet(id, SPREADSHEET_ID, SPREADSHEET_NAME, tClass);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (GeneralSecurityException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(Transparency transparency){
        this.saveInSpreadSheet(transparency, SPREADSHEET_ID, SPREADSHEET_NAME);
    }

    @Override
    public void delete(int id){
        this.deleteInSpreadSheet(id);
    }
}
