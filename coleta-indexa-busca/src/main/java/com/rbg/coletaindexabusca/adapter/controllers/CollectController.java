package com.rbg.coletaindexabusca.adapter.controllers;

import com.rbg.coletaindexabusca.core.ports.ICollectService;
import com.rbg.coletaindexabusca.shared.enums.StoreEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/collect", produces = MediaType.APPLICATION_JSON_VALUE)
public class CollectController {
    private final ICollectService service;

    @GetMapping()
    public ResponseEntity<Object> collectAndFilter(@Valid StoreEnum store) {
        service.findAndSaveAndFilter();
        return ResponseEntity.ok("Foi coletado todos os hardwares da loja " + store + ", filtrado e salvo com sucesso!");
    }

    @PostMapping
    public ResponseEntity<Object> filter(@Valid StoreEnum store) {
        service.filterData();
        return ResponseEntity.ok("Foi filtrado todos os produtos da " + store + " e salvo com sucesso!");
    }

}
