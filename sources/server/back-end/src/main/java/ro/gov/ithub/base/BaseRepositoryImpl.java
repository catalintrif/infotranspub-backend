package ro.gov.ithub.base;

/**
 * Created by Mihnea on 11/23/16.
 */

import org.hibernate.Session;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import java.io.Serializable;

public class BaseRepositoryImpl<T extends BaseEntity, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements BaseRepository<T, ID> {

    private final EntityManager entityManager;
    private final JpaEntityInformation<T, ID> entityInformation;

    protected Class<T> type;

    public BaseRepositoryImpl(JpaEntityInformation<T, ID> entityInfo, EntityManager entityMngr) {
        super(entityInfo, entityMngr);
        // Keep the EntityManager around to used from the newly introduced methods.
        this.entityManager = entityMngr;
        this.entityInformation = entityInfo;
    }

    /**
     * See {@link EntityManager#getReference(Class, Object)}. Throws {@link EntityNotFoundException} if entity can't be found, when it's first accessed, e.g. by
     * .getAgencyId().
     */
    @Override
    public T getReference(ID id) {
        return entityManager.getReference(getDomainClass(), id);
    }

    @Override
    public T update(T entity) {
        if (exists(entityInformation.getId(entity))) {
            return entityManager.merge(entity);
        } else {
            throw new EntityNotFoundException();
        }
    }

    @Override
    public Session getSession() {
        return entityManager.unwrap(Session.class);
    }

}
