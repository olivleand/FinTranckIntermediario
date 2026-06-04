package repository;

import java.util.List;

public interface RepositorioGeneric<T> {

    void salvar(T objeto);

    List<T> listar();

}
