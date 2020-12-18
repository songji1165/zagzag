package com.jtrio.zagzag.product;

import com.jtrio.zagzag.category.CategoryRepository;
import com.jtrio.zagzag.exception.NotFoundException;
import com.jtrio.zagzag.model.Category;
import com.jtrio.zagzag.model.Product;
import com.jtrio.zagzag.model.Review;
import com.jtrio.zagzag.review.ReviewRepository;
import com.jtrio.zagzag.utils.common.CommonUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService  {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ReviewRepository reviewRepository;

    public List<ProductDto.CreateProductDto> getProducts(long categoryId){

        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new NotFoundException("해당 카테고리를 찾을 수 없습니다."));

        List<ProductDto.CreateProductDto> produtsDto = new ArrayList<>();
        List<Product> products = productRepository.findByCategory(category);

        for(Product product : products){
            produtsDto.add(product.toProductDto());
        }

        return produtsDto;
    }

    public ProductDto.CreateProductDto createProduct(ProductCommand.CreateProduct productCommand){
        Category category = categoryRepository.findById(productCommand.getCategoryId()).orElseThrow(() -> new NotFoundException("해당 카테고리를 찾을 수 없습니다."));

        Product product = productCommand.toProduct(category);
        productRepository.save(product);

        return product.toProductDto();
    }

    public void updateProductScore(Product product){
        List<Review> reviews = reviewRepository.findByProduct(product);

        CommonUtils commonUtils = new CommonUtils();
        byte score = commonUtils.averageScore(reviews);

        product.setProductScore(score);
    }

    public void updateDeliveryScore(Product product){
        List<Review> reviews = reviewRepository.findByProduct(product);

        CommonUtils commonUtils = new CommonUtils();
        byte score = commonUtils.averageScore(reviews);

        product.setDeliveryScore(score);
    }

}
