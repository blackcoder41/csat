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
		window.jsonp = function (data) {
			var div = document.createElement("div");
			div.innerHTML = data.message;
			document.body.appendChild(div);
			
	    	function setCookie(name,value,days) {
	            var expires = "";
	            if (days) {
	                var date = new Date();
	                date.setTime(date.getTime() + (days*24*60*60*1000));
	                expires = "; expires=" + date.toUTCString();
	            }
	            document.cookie = name + "=" + (value || "")  + expires + "; path=/";
	        }
	        
	        
	        function getCookie(a) {
	            var b = document.cookie.match('(^|[^;]+)\\s*' + a + '\\s*=\\s*([^;]+)');
	            return b ? b.pop() : '';
	        }
	        
	        
	        $(".smiley").click(function() {
	            $(".smiley").removeClass("active");
	            $(this).addClass("active");
	        });
	        
	        $("#btn-submit-feedback").click(function() {
	            $("#hidden-rating").val($(".smiley.active").attr("rating"));
	            $("#hidden-comment").val( $("#comment").val() );
	            $("#hidden-user-agent").val( navigator.userAgent );
	            $("#hidden-origin").val( location.origin );
	            $("#form-feedback").submit();
	            
	            $("#modal-feedback #div-feedback").slideUp();
	            $("#modal-feedback .modal-header").slideUp();
	            $("#modal-feedback .modal-footer").slideUp();
	            $("#modal-feedback #div-thank-you").fadeIn();
	            
	            setTimeout(function(){ $("#modal-feedback button.close").click(); }, 3000);
	        });

	        if (getCookie("JSESSIONID2") 
	            && getCookie("JSESSIONID") != getCookie("JSESSIONID2")
	            && getCookie("feedback_dialog") != "shown") {
	            jQuery("#modal-feedback").modal();
	            setCookie("JSESSIONID2", "");
	            setCookie("feedback_dialog", "shown", 3);
	        } else {
	            setCookie("JSESSIONID2", getCookie("JSESSIONID"), ((1/24/60)*15));
	        }
	        
	        // jQuery("#modal-feedback").modal();
	        
		}; // jsnop
		
		var s = document.createElement("script");
		s.src = "[BASE_URL]/feedback/jsonp";
		document.body.appendChild(s);

	});
})(jQuery);	
