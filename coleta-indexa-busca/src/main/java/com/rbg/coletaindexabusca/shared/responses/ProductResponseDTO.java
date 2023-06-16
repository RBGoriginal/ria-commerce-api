package com.rbg.coletaindexabusca.shared.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ProductResponseDTO {
    private String produto;
    private String preco;
    private String link;
}
