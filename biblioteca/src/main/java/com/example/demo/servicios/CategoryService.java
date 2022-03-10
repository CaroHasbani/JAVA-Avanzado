package com.example.demo.servicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.entidades.Category;
import com.example.demo.repositorios.CategoryRepository;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public Iterable<Category> getCategorys(){return categoryRepository.findAll();}
    public void saveCategory(Category category){
        categoryRepository.save(category);
    }
}