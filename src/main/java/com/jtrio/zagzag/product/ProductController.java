package com.jtrio.zagzag.product;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public Page<ProductDto.CreateProductDto> getProducts(
            @RequestParam(value = "category", required = true) Long categoryId,
            @PageableDefault Pageable pageable
//            @RequestParam(value = "page", defaultValue = "1") Integer page,
//            @RequestParam(value = "size", defaultValue = "5") Integer size
    ) {
        //클라이언트에서 보내는 page는 1부터
        return productService.getProducts(categoryId, pageable);
    }

    @PostMapping
    public ProductDto.CreateProductDto createProduct(@RequestBody ProductCommand.CreateProduct product) {
        return productService.createProduct(product);
    }

}
