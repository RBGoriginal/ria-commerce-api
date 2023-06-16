package com.rbg.coletaindexabusca.shared.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    public static String getDateToday(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        return formatter.format(new Date()).replace("/", "-");
    }
}
