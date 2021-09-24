package DAO;

public interface DAO<T> {
    T getById(int id);
    public boolean create(T objet);
    public boolean update(T objet);
    public boolean delete(T objet);

}
