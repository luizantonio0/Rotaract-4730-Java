package app.netlify.rotaract.Web_Structure.dbConnection;

import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.*;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

public class DataRepository<T> implements DataRepositoryI<T>{

    private final Serializer<T> serializer;
    private final Connection con;

    public DataRepository() {
        this.serializer = new Serializer<>();
        this.con = new Connection();
    }

    @Override
    public void save(T t){
        List<Object> row;
        try {
            row = serializer.serialize(t);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Error serializing object: object does not have fields");
        }
        ValueRange appendBody = new ValueRange()
                .setValues(List.of(row));

        try {
            var appendResult = con.getSheetsService().spreadsheets().values()
                    .append("1qt1Ql7xTzxc5SiRo3tB3-lpBc7zBM_4l0vXqwLPCnSs", "teste", appendBody )
                    .setValueInputOption("USER_ENTERED")
                    .setInsertDataOption("INSERT_ROWS")
                    .setIncludeValuesInResponse(true)
                    .execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (GeneralSecurityException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int rowId) {
        try {
            int startIndex = rowId - 1;

            DeleteDimensionRequest deleteDimensionRequest = new DeleteDimensionRequest()
                    .setRange(
                            new DimensionRange()
                                    .setDimension("ROWS")
                                    .setStartIndex(startIndex)
                                    .setEndIndex(rowId));

            List<Request> requests = new ArrayList<>();
            requests.add(new Request().setDeleteDimension(deleteDimensionRequest));

            BatchUpdateSpreadsheetRequest body = new BatchUpdateSpreadsheetRequest()
                    .setRequests(requests);

            con.getSheetsService().spreadsheets()
                    .batchUpdate("1qt1Ql7xTzxc5SiRo3tB3-lpBc7zBM_4l0vXqwLPCnSs", body)
                    .execute();

        } catch (IOException e) {
            throw new RuntimeException("Erro de I/O ao deletar linha: " + e.getMessage(), e);
        } catch (GeneralSecurityException e) {
            throw new RuntimeException("Erro de segurança ao deletar linha: " + e.getMessage(), e);
        }
    }


    @Override
    public T get(String id) {
        return null;
    }


    public T get(final int id, final String sheetsName, final String spreadSheetId, final Class<T> tClass) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, GeneralSecurityException, IOException {
        char maxColumn = (char) ('A' + tClass.getDeclaredFields().length - 1);
        var range = sheetsName + "!A%d:%c%d".formatted(id, maxColumn, id);

        Sheets service = this.con.getSheetsService();

        ValueRange response = service.spreadsheets().values()
                .get(spreadSheetId, range)
                .execute();
        List<List<Object>> values = response.getValues();

        if (values == null || values.isEmpty()) {
            throw new RuntimeException("No data found.");
        }
        List<Object> row = values.getFirst();

        return serializer.deserialize(row, tClass);
    }

    @Override
    public Iterable<T> getAll() {
        return null;
    }

    public List<T> getAll(final String spreadSheetId, final String sheetsName, final Class<T> tClass) throws IOException, GeneralSecurityException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        //Pegar a List<List<Object>> (Range = ex: A:E) e criar todos os objetos e depois mudar para lista de T e retornar
        char maxColumn = (char) ('A' + tClass.getDeclaredFields().length - 1);
        var range = sheetsName + "!A:" + maxColumn;
        List<T> list = new ArrayList<>();

        Sheets service = this.con.getSheetsService();

        ValueRange response = service.spreadsheets().values()
                .get(spreadSheetId, range)
                .execute();
        List<List<Object>> values = response.getValues();

        if (values == null || values.isEmpty()) {
            throw new RuntimeException("No data found.");
        }

        values = values.subList(1, values.size());

        for (List<Object> row : values) {
            list.add(serializer.deserialize(row, tClass));
        }
        return list;
    }

    public void update(T t, int id){
        List<Object> row;

        char maxColumn = (char) ('A' + t.getClass().getDeclaredFields().length - 1);
        var range = "!A%d:%c%d".formatted(id, maxColumn, id);

        try {
            row = serializer.serialize(t);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Error serializing object: object does not have fields");
        }
        ValueRange body = new ValueRange()
                .setValues(List.of(row));

        try {
            UpdateValuesResponse updateValuesResponse = con.getSheetsService().spreadsheets().values()
                    .update("1qt1Ql7xTzxc5SiRo3tB3-lpBc7zBM_4l0vXqwLPCnSs", range, body )
                    .setValueInputOption("RAW")
                    .execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (GeneralSecurityException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String... Args) throws GeneralSecurityException, IOException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        var Data = new DataRepository<Person>();
//        var p1 = new Person("Luiz Zabroski", 18, "Zaza", "zaza@mail.com");
//        Data.update(p1, 3);
        Data.delete(4);
        System.out.println(Data.getAll("1qt1Ql7xTzxc5SiRo3tB3-lpBc7zBM_4l0vXqwLPCnSs", "teste", Person.class));
//        var person = Data.get(2, "teste","1qt1Ql7xTzxc5SiRo3tB3-lpBc7zBM_4l0vXqwLPCnSs", Person.class);
//        System.out.println(person);

    }
}
class Person{
    private String name;
    private int age;
    private String nickName;
    private String email;
    public Person(String name, int age, String nickName, String email) {
        this.name = name;
        this.age = age;
        this.nickName = nickName;
        this.email = email;
    }
    public Person(){}
    @Override
    public String toString() {
        return "Person{" +
                "\nname='" + name + '\'' +
                ", \nage=" + age +
                ", \nnickName='" + nickName + '\'' +
                ", \nemail='" + email + '\'' +
                "\n}";
    }
}