package app.netlify.rotaract.Web_Structure.entity.member;

import app.netlify.rotaract.Web_Structure.dbConnection.DataRepository;
import app.netlify.rotaract.Web_Structure.entity.RepositoryI;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.security.GeneralSecurityException;
import java.util.List;

@Repository
public class MemberRepository extends DataRepository<Member> implements RepositoryI<Member> {

    private final Class<Member> tClass = Member.class;
    private final String SPREADSHEET_NAME = "Member";
    private final String SPREADSHEET_ID = "1qt1Ql7xTzxc5SiRo3tB3-lpBc7zBM_4l0vXqwLPCnSs";

    @Override
    public List<Member> getAll(){
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
    public void update(Member member, int id) {
        this.updateInSpreadSheet(member, id, SPREADSHEET_ID, SPREADSHEET_NAME);
    }

    @Override
    public Member get(int id){
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
    public void save(Member member){
        this.saveInSpreadSheet(member, SPREADSHEET_ID, SPREADSHEET_NAME);
    }
    @Override
    public void delete(int id){
        this.deleteInSpreadSheet(id);
    }
}
