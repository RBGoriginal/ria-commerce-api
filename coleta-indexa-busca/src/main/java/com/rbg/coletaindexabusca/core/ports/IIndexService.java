package com.rbg.coletaindexabusca.core.ports;

import com.rbg.coletaindexabusca.shared.enums.ProductEnum;

public interface IIndexService {
    void indexProduct(ProductEnum productFilter);
}
