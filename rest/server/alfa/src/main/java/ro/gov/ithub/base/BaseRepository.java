package ro.gov.ithub.base;

/**
 * Created by Mihnea on 11/23/16.
 */

import org.hibernate.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface BaseRepository<T extends BaseEntity, ID extends Serializable> extends JpaRepository<T, ID> {

    T getReference(ID id);

    T update(T entity);

    Session getSession();

}
