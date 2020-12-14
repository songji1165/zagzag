package com.jtrio.zagzag.product;

import com.jtrio.zagzag.category.CategoryRepository;
import com.jtrio.zagzag.exception.NotFoundException;
import com.jtrio.zagzag.model.Category;
import com.jtrio.zagzag.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService  {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public List<ProductDto> getProducts(long categoryId){

        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new NotFoundException("해당 카테고리를 찾을 수 없습니다."));

        List<ProductDto> produtsDto = new ArrayList<>();
        List<Product> products = productRepository.findByCategory(category);

        for(Product product : products){
            produtsDto.add(product.toProductDto());
        }

        return produtsDto;
    }

    public ProductDto addProduct(Long categoryId, ProductCommand productCommand){
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new NotFoundException("해당 카테고리를 찾을 수 없습니다."));

        Product product = productCommand.toProduct(category);
        productRepository.save(product);

        return product.toProductDto();
    }
}
