package com.revature.revabank.repos;

import java.util.Optional;
import java.util.Set;

public interface CrudRepository<T> {

    /**
     * CREATE
     * @param t
     * @return
     */
    Optional<T> save(T t);

    /**
     * READ
     * @return
     */
    Set<Optional<T>> findAll();
    Optional<T> findById(Integer id);

    /**
     * UPDATE
     * @param t
     * @return
     */
//    boolean update(T t); // made individual updates for separate fields

    /**
     * DELETE
     * @param id
     * @return
     */
    boolean deleteById(Integer id);


}
