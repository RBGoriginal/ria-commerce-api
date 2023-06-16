package com.rbg.coletaindexabusca.shared.filters;

import com.rbg.coletaindexabusca.shared.enums.ProductEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class ProductFilterDTO {
    @NotNull
    private ProductEnum produto;
    private String nome;
}
