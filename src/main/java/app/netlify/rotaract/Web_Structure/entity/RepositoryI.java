package app.netlify.rotaract.Web_Structure.entity;

public interface RepositoryI<T> {
    void save(T t);
    void delete(int id);
    T get(int id);
    Iterable<T> getAll();
    void update(T t, int id);

}
