package com.jtrio.zagzag.category;

import com.jtrio.zagzag.model.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public Category findCategory(Long id){
        return categoryRepository.findById(id).get();
    }

    public List<Category> getCategory(){ return categoryRepository.findAll(); }
}
