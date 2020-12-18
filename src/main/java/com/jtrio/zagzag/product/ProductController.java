package com.jtrio.zagzag.product;

import com.jtrio.zagzag.model.Category;
import com.jtrio.zagzag.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public List<ProductDto.CreateProductDto> getProducts(@RequestParam("category") Long categoryId){
        return productService.getProducts(categoryId);
    }

    /**
     *  상품저장
     *      : 카테고리 먼저 선택 후에 상품을 저장한다고 가정!
     *  1. 카테고리 존재 여부 확인
     *  2. 상품 저장 후, 저장된 상품 return
     *
     *  =>
     *   + 상품의 카테고리를 쿼리스트링을 받는게 좋다 ?
     *   + requestBody 멀티object로 받는게 좋다 ? {cateId : 1, product: {}} -> Map<String, object>
     *   + requestBody command로 받는게 좋다 .?
    **/
    @PostMapping
    public ProductDto.CreateProductDto createProduct(@RequestBody ProductCommand.CreateProduct product){
        return productService.createProduct(product);
    }

}
