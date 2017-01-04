package ro.gov.ithub.base;

import java.io.Serializable;
import java.util.Collection;

/**
 * Created by mihnea.local on 08.12.2016.
 */
public abstract class BaseServiceImpl<E extends BaseEntity, I extends Serializable> implements BaseService<E, I> {

    protected BaseRepository baseRepository;

    protected BaseServiceImpl(BaseRepository baseRepo) {
        this.baseRepository = baseRepo;
    }

    @Override
    public Collection<E> findAll() {
        return this.baseRepository.findAll();
    }

    @Override
    public E findById(int id) {
        return (E) this.baseRepository.findOne(id);
    }

    @Override
    public void saveOrUpdate(E obj) {
        this.baseRepository.save(obj);
    }

    @Override
    public void delete(I obj) {
        this.baseRepository.delete(obj);
    }

}
