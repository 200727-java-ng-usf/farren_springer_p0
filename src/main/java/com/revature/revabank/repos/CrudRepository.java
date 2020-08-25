package com.revature.revabank.repos;

import java.util.Optional;
import java.util.Set;

/**
 * CREATE READ UPDATE DELETE Repository.
 * @param <T>
 */
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
//    boolean update(T t); // made individual updates for separate fields for now
    // TODO one method for all updates in each repository. Then, user service
    //  would call this method after using a setter on the object for different fields of AppUser.

    /**
     * DELETE
     * @param id
     * @return
     */
    boolean deleteById(Integer id);


}
