package com.rbg.coletaindexabusca.shared.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ProductEnum {
    PLACA_DE_VIDEO("placa-de-video-vga", "3A16364811011"),
    PROCESSADOR("processadores","3A16364803011"),
    PLACA_MAE("placas-mae","3A16364815011"),
    FONTE("fontes",""),
    RAM("memoria-ram","3A16364809011"),
    SSD("ssd-2-5",""),
    HD("disco-rigido-hd","3A16364781011");
    
    private final String kabum;
    private final String amazon;
}
