package com.jtrio.zagzag.product;

import com.jtrio.zagzag.category.CategoryRepository;
import com.jtrio.zagzag.exception.NotFoundException;
import com.jtrio.zagzag.model.Category;
import com.jtrio.zagzag.model.Product;
import com.jtrio.zagzag.model.Review;
import com.jtrio.zagzag.review.ReviewRepository;
import com.jtrio.zagzag.utils.common.CommonUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.awt.print.Pageable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService  {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ReviewRepository reviewRepository;

    public Page<ProductDto.CreateProductDto> getProducts(long categoryId, Integer page, Integer size){

        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new NotFoundException("해당 카테고리를 찾을 수 없습니다."));

        PageRequest pageable = PageRequest.of(page, size);

        Page<Product> products = productRepository.findAllByCategory(category, pageable);

        Page<ProductDto.CreateProductDto> produtsDto = products.map(product -> ProductDto.CreateProductDto.toProductDto(product) );

        return produtsDto;
    }

    public ProductDto.CreateProductDto createProduct(ProductCommand.CreateProduct productCommand){
        Category category = categoryRepository.findById(productCommand.getCategoryId()).orElseThrow(() -> new NotFoundException("해당 카테고리를 찾을 수 없습니다."));

        Product product = productCommand.toProduct(category);
        productRepository.save(product);

        return ProductDto.CreateProductDto.toProductDto(product);
    }

    public void updateScore(Product product){
        List<Review> reviews = reviewRepository.findByProduct(product);

        CommonUtils commonUtils = new CommonUtils();
        HashMap<String, Byte> score = commonUtils.averageScore(reviews);

        product.setProductScore(score.get("product"));
        product.setDeliveryScore(score.get("delivery"));

        productRepository.save(product);
    }
}
