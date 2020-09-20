export YUI=~/Downloads/yuicompressor-2.4.8.jar
export PROJECT_PATH=.

java -jar $YUI $PROJECT_PATH/src/main/resources/static/css/feedback.src.css -o $PROJECT_PATH/src/main/resources/static/css/feedback.min.css

java -jar $YUI $PROJECT_PATH/src/main/resources/static/js/feedback.src.js -o $PROJECT_PATH/src/main/resources/static/js/feedback.min.js
java -jar $YUI $PROJECT_PATH/src/main/resources/static/js/start.src.js -o $PROJECT_PATH/src/main/resources/static/js/start.min.js
java -jar $YUI $PROJECT_PATH/src/main/resources/static/js/consent.src.js -o $PROJECT_PATH/src/main/resources/static/js/consent.min.js
