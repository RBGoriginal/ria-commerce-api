package com.rbg.coletaindexabusca.core.ports;

import com.rbg.coletaindexabusca.shared.filters.ProductFilterDTO;
import com.rbg.coletaindexabusca.shared.responses.ProductResponseDTO;

import java.util.List;

public interface ISearchService {
    List<ProductResponseDTO> findProduct(ProductFilterDTO filterDTO);
}
