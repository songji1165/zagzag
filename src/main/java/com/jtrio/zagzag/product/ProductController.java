package com.jtrio.zagzag.product;

import com.jtrio.zagzag.model.Category;
import com.jtrio.zagzag.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public List<ProductDto> getProducts(@RequestParam("category") Long categoryId){
        return productService.getProducts(categoryId);
    }

    /**
     *  상품저장
     *      : 카테고리 먼저 선택 후에 상품을 저장한다고 가정!
     *  1. 카테고리 존재 여부 확인
     *  2. 상품 저장 후, 저장된 상품 return
    **/
    @PostMapping
    public ProductDto addProduct(@RequestBody Category category, @RequestBody ProductCommand product){
        return productService.addProduct(category, product);
    }

}
