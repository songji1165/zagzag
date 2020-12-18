package com.jtrio.zagzag.category;

import com.jtrio.zagzag.model.Category;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CategoryCommand {
    @NotBlank
    private Long id;
    @NotBlank(message = "카테고리를 입력해주세요.")
    private String name;
}
