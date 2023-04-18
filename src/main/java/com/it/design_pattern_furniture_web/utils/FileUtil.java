package com.it.design_pattern_furniture_web.utils;

import org.apache.commons.io.IOUtils;

import javax.servlet.http.Part;
import java.util.Base64;

public class FileUtil {

    public static String encodeBase64(Part file) {
        String encodedString = null;
        try{
            byte[] sourceBytes = IOUtils.toByteArray(file.getInputStream());
            encodedString = Base64.getEncoder().encodeToString(sourceBytes);
        }catch (Exception e){
            e.printStackTrace();
        }
        return encodedString;
    }


}
