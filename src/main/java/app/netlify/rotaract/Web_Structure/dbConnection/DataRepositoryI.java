package app.netlify.rotaract.Web_Structure.dbConnection;

public interface DataRepositoryI<T> {
    void save(T t);
    void delete(int id);
    T get(String id);
    Iterable<T> getAll();
}
