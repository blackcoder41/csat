package com.raketlabs.robinsonsbank.feedback;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Configuration
public class LoadResourceConfig {

    @Bean
    public String HTML_DIALOG_BOX () {
        return ResourceReader.readFileToString("/templates/feedback.html");
    }
    
    @Bean
    public String JS_CALLBACK () {
        return ResourceReader.readFileToString("/static/js/feedback.min.js");
    }

    @Bean
    public String JS_LICENSE () {
        return ResourceReader.readFileToString("/static/js/license.js");
    }
    
    @Bean
    public String JS_START () {
        return ResourceReader.readFileToString("/static/js/start.min.js");
    }
}