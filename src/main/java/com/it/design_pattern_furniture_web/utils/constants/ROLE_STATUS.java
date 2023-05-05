package com.it.design_pattern_furniture_web.utils.constants;

import java.util.HashMap;

public class ROLE_STATUS {
    public static final int  ACTIVE = 1;
    public static final int  IN_ACTIVE = 0;

    public static HashMap<String, Integer> Status = new HashMap<String, Integer>(){
        {
            put("Đang dùng", ACTIVE);
            put("Đã xoá", IN_ACTIVE);
        }
    };
}
