FROM tomcat8.5.37-jre8
COPY .angular/news/dist. usr/local/tomcat/webapps/news
COPY .service/news/targettshell-service.war usr/local/tomcat/webapps
