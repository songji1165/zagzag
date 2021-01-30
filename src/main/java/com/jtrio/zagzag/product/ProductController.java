package com.jtrio.zagzag.product;

import com.jtrio.zagzag.question.QuestionDto;
import com.jtrio.zagzag.question.QuestionService;
import com.jtrio.zagzag.security.SecurityUser;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final QuestionService questionService;

    @GetMapping
    public Page<ProductDto.CreateProductDto> getProducts(
            @RequestParam(value = "category", required = true) Long categoryId,
            @PageableDefault Pageable pageable) {
        //클라이언트에서 보내는 page는 1부터
        return productService.getProducts(categoryId, pageable);
    }

    @PostMapping
    public ProductDto.CreateProductDto createProduct(@RequestBody ProductCommand.CreateProduct product) {
        return productService.createProduct(product);
    }

    @GetMapping("/{id}/questions")
    public Page<QuestionDto> getProductQuestions(
            @PathVariable Long id,
            @AuthenticationPrincipal SecurityUser securityUser,
            @PageableDefault() Pageable pageable) {
        return questionService.getProductQuestions(id, securityUser, pageable);
    }

}
