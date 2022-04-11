package com.example.demo.generic;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class GenericService<T extends GenericEntity<T, ID>, ID> {
    protected final GenericRepository<T, ID> genericRepository;

    public GenericService(GenericRepository<T, ID> genericRepository) {
        this.genericRepository = genericRepository;
    }

    public Page<T> get(){
        return this.genericRepository.findAll(Pageable.unpaged());
    }

    public Long count(){
        return this.genericRepository.count();
    }

    public void deleteById(ID id){
        T previous = this.genericRepository.findById(id).orElse(null);

        if(previous != null){
            this.genericRepository.deleteById(id);
        }
    }
}

    