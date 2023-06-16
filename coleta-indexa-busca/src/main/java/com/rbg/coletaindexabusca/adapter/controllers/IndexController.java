package com.rbg.coletaindexabusca.adapter.controllers;

import com.rbg.coletaindexabusca.core.ports.IIndexService;
import com.rbg.coletaindexabusca.shared.enums.ProductEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/index", produces = MediaType.APPLICATION_JSON_VALUE)
public class IndexController {
    private final IIndexService service;

    @PostMapping()
    public ResponseEntity<Object> index(@Valid ProductEnum filter) {
        service.indexProduct(filter);
        return ResponseEntity.ok("Produto " + filter + " indexado com sucesso!");
    }

}
