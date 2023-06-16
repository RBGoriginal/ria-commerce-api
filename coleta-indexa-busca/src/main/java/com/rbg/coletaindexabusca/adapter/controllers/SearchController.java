package com.rbg.coletaindexabusca.adapter.controllers;

import com.rbg.coletaindexabusca.core.ports.ISearchService;
import com.rbg.coletaindexabusca.shared.filters.ProductFilterDTO;
import com.rbg.coletaindexabusca.shared.responses.ProductResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
public class SearchController {
    private final ISearchService service;

    @GetMapping()
    public ResponseEntity<Object> find(ProductFilterDTO filter) {
        List<ProductResponseDTO> result = service.findProduct(filter);
        return ResponseEntity.ok(result);
    }

}
