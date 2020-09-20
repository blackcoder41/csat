package com.raketlabs.util;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.support.RequestContextUtils;


public class ControllerUtils {

    public static <T> T retrieveFlashAttribute (HttpServletRequest request, String attribute) {
        
        Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);
        if (inputFlashMap != null) {
            T attributeObject = (T) inputFlashMap.get(attribute);
            return attributeObject;
        } else return null;
    }
}
