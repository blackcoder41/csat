/**
 * Copyright 2019 Raket Labs
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.raketlabs.robinsonsbank.feedback.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.raketlabs.robinsonsbank.feedback.Utils;
import com.raketlabs.robinsonsbank.feedback.model.Feedback;
import com.raketlabs.robinsonsbank.feedback.model.GenericResponse;
import com.raketlabs.robinsonsbank.feedback.repository.QueryFeedback;

/**
 * @author Oral Hernandez
 */

@RestController
public class BackController {
	
    @Autowired private String HTML_DIALOG_BOX;
    
    @Autowired private String JS_CALLBACK;
    
    @Autowired private String JS_LICENSE;
    
    @Autowired private String JS_START;
    
    @Autowired private ServletContext servletContext;
    
	@Autowired QueryFeedback queryFeedback;

	
	@GetMapping("/useragent")
    public String getUserAgent(@RequestHeader(value = "User-Agent") String userAgent) {
        return userAgent;
    }
	
	
	@GetMapping("/referer")
	public String getReferer(HttpServletRequest request) {
	    return request.getHeader("referer");
	}
	
	
	@GetMapping(value="/cstring", produces="application/javascript")
	public String getCString () throws JsonProcessingException {
	    return Utils.toJSON("hello world");
	}

	
	public String uri (String path) {

	    return baseUrl() + path;
	}
	
	
	public String baseUrl () {
	    final String baseUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
	    return baseUrl;
	}
	
	
	@GetMapping(value="/feedback/js")
	public void getJS (HttpServletRequest request, HttpServletResponse response) throws IOException {
	    
	    String content = JS_CALLBACK.replace("[BASE_URL]", baseUrl());
	    Utils.writeResponse("application/javascript", content, response);
	}
	
	
	/**
	 * This loads jQuery, Boostrap and the JSONP for RBank Digital
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@GetMapping(value="/feedback/js-v2")
    public void start (HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        String content = JS_START.replace("[BASE_URL]", baseUrl());
        Utils.writeResponse("application/javascript", content, response);
    }
	
	
	@GetMapping(value="/feedback/jsonp")
    public void getDialog (HttpServletRequest request, HttpServletResponse response) throws IOException {
	    
        String content = HTML_DIALOG_BOX.replace("[BASE_URL]", baseUrl());
        
        GenericResponse genericResponse = new GenericResponse(200, content);
        String jsonp = Utils.toJSONP(genericResponse, "jsonp");
        jsonp = JS_LICENSE + jsonp;
        
        
        Utils.writeResponse("application/javascript", jsonp, response);
    }
	
	
	@PostMapping("/feedback")
    public ResponseEntity<Object> processSurvey (
            @RequestParam(name = "key", required = true) String key,
            @RequestHeader(value = "User-Agent") String userAgent,
            String rating,
            String comment,
            HttpServletRequest httpServetRequest) {
        
        String origin = httpServetRequest.getHeader("referer");
        
        Feedback feedback = new Feedback();
        feedback.setCreatedDate(new Date());
        feedback.setRating(rating);
        feedback.setComment(comment);
        feedback.setUserAgent(userAgent);
        feedback.setIpAddress(httpServetRequest.getRemoteAddr());
        feedback.setOrigin(origin);
        
        String validKey = DigestUtils.sha1Hex(origin.getBytes());
        
        System.out.println("ORIGIN: " + origin);
        System.out.println("KEY: " + key);
        System.out.println("VALID_KEY: " + validKey);
        
        if (origin != null && origin.contains("robinsonsbank.com.ph")) {
            queryFeedback.save(feedback);
            
            HttpStatus status = HttpStatus.OK;
            String message = "Thank you for your feedback.";
            
            return new ResponseEntity<Object>(new GenericResponse(status.value(), message), status);
        } else {
            
            HttpStatus status = HttpStatus.UNAUTHORIZED;
            String message = "Please contact system administrator";
            
            return new ResponseEntity<Object>(new GenericResponse(status.value(), message), status);
        }
    }
}
