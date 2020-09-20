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

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.raketlabs.robinsonsbank.feedback.model.GenericResponse;

/**
 * @author Oral Hernandez
 */
@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler  {

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		
		if (status == HttpStatus.BAD_REQUEST) {
			return new ResponseEntity<Object>(new GenericResponse(status.value(), ex.getMessage()), status);
		}
		
		return super.handleExceptionInternal(ex, body, headers, status, request);
	}
}
