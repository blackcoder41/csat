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

(function() {

    var scripts = [];

    function loadScript (src) {
        scripts.push(src);
    }

    function loadScripts () {

        var src = scripts.shift();
        if (typeof src == 'undefined') return;

        var script = document.createElement('script');
        script.src = src;
        script.onload = function() {
            loadScripts();
        };
        document.body.appendChild(script);
    }

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

    if (typeof jQuery == 'undefined') {
        loadScript('https://code.jquery.com/jquery-2.2.4.min.js');
    }

    if (typeof jQuery == 'undefined' || typeof (jQuery.fn.popover) == 'undefined') {
        loadScript('https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js');
    }

    if (document.getElementById('modal-feedback') == null ) {
        loadScript('[BASE_URL]/feedback/js');
    }

    loadScripts();
    
    if (document.getElementById('modal-feedback') != null && getCookie("feedback_dialog") != "shown") {
        document.querySelector('.mdi-close-circle').click();
        jQuery("#modal-feedback").modal();
        setCookie("feedback_dialog", "shown", 3);
    }
})();