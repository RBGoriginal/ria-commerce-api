package com.rbg.coletaindexabusca.shared.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StoreEnum {
    KABUM("Kabum","KabumColeta",  "KabumIndex"),
    AMAZON("Amazon","AmazonColeta","AmazonIndex");
    
    private final String loja;
    private final String coleta;
    private final String index;
}
