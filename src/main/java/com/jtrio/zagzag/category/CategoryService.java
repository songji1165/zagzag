package com.jtrio.zagzag.category;

import com.jtrio.zagzag.model.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {
    CategoryRepository categoryRepository;

    public Category findCategory(Long id){
        return categoryRepository.findById(id).get();
    }
}
