package com.raketlabs.robinsonsbank.feedback;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Utils {
    
    public static void writeResponse (
        String type, 
        String content, 
        HttpServletResponse response) 
        throws IOException {
        
        response.setContentType(type);
        response.getWriter().write(content);
        response.flushBuffer();
    }
    
    public static <T> String toJSON (T object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }
    
    public static <T> String toJSONP (T object, String callback) throws JsonProcessingException {
        String json = toJSON(object);
        String jsonp = callback + " ( " + json + " );";
        return jsonp;
    }
}
