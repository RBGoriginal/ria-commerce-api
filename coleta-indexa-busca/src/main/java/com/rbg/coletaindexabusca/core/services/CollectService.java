package com.rbg.coletaindexabusca.core.services;

import com.rbg.coletaindexabusca.core.ports.ICollectService;
import com.rbg.coletaindexabusca.shared.enums.ProductEnum;
import com.rbg.coletaindexabusca.shared.enums.StoreEnum;
import org.springframework.stereotype.Service;

@Service
public class CollectService implements ICollectService {
    private static final String CSV_FILE = "path/to/csv/";

    @Override
    public void findAndSaveAndFilter() {

    }

    @Override
    public void filterData() {

    }

    public static String getDirectoryCollect(StoreEnum store, ProductEnum product) {

        return CSV_FILE + "/" + store.getLoja() + "/" + product + "-" + store.getColeta() + ".csv";
    }
}
