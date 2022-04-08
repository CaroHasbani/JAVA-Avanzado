package com.example.demo.generic;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

public class GenericController<T extends GenericEntity<T, ID>, ID> {
    private final GenericService<T, ID> genService;

    protected GenericController(GenericService<T, ID> genService) {
        this.genService = genService;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('SCOPE_ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public Page<T> get(){
        return this.genService.get();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('SCOPE_ROLE_USER')")
    @ResponseStatus(HttpStatus.OK)
    public T getById(@PathVariable ID id){
        return this.genService.getById(id);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('SCOPE_ROLE_USER')")
    @ResponseStatus(HttpStatus.CREATED)
    public T create(@RequestBody T newT){
        return this.genService.create(newT);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('SCOPE_ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public T update(@RequestBody T updatedT){
        return this.genService.update(updatedT);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('SCOPE_ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public void delete(@PathVariable ID id){
        this.genService.delete(id);
    }
}