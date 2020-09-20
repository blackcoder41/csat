/*!
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

/*!
 * @author Oral Hernandez
 */

(function ($) {
	$(function () {
		
		// UI Tweaks
	    document.getElementById("SplashMessage").style.backgroundColor="rgba(255,255,255,0.85)";
	    document.getElementById("loginDiv").style.backgroundColor="rgba(255,255,255,0.85)";
	    document.getElementById("loginDiv").nextElementSibling.nextElementSibling.style.backgroundColor="rgba(255,255,255,0.85)";
	    
	    // Cookie Consent
	    window.cookieconsent.initialise({
	    	  "palette": {
	    	    "popup": {
	    	      "background": "#000000",
	    	      "text": "#ffffff"
	    	    },
	    	    "button": {
	    	      "background": "#71be43",
	    	      "text": "#ffffff"
	    	    }
	    	  },
	    	  "position": "bottom-left",
	    	  "content": {
	    	    "message": "We use cookies to ensure you get the best experience at Robinsonsbank.com.ph. With your continued use, you agree to our privacy policy and accept the secured use of such cookies. Find out more",
	    	    "dismiss": "I AGREE",
	    	    "link": "here.",
	    	    "href": "https://www.robinsonsbank.com.ph/cookie-policy"
	    	  }
	    	});

	    // Add close button to cookie consent box
	    var btnClose = jQuery("<img>")
	    .attr("src", "https://www.robinsonsbank.com.ph/assets/custom/images/close-button-white.png")
	    .css("width", "16px")
	    .css("height", "16px")
	    .css("position", "absolute")
	    .css("top", "15px")
	    .css("right", "10px")
	    .css("cursor", "pointer")
	    ;

	    $(".cc-window").append(btnClose);

	    $(btnClose).click(function() {
	    	$(".cc-window").hide();
	    });

	});
})(jQuery);	