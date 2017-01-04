package ro.gov.ithub.base;

import java.io.Serializable;
import java.util.Collection;

/**
 * Created by mihnea.local on 08.12.2016.
 */
public interface BaseService<E extends BaseEntity, I extends Serializable> {

    Collection<E> findAll();

    E findById(int id);

    void saveOrUpdate(E obj);

    void delete(I obj);
}
