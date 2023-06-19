FROM openjdk:11
RUN echo 'Now started creating Image'
MAINTAINER Anish Gupta<anishkumars.gupta@gmail.com>
COPY target/blogsapp.jar  usr/app/blogsapp.jar
WORKDIR usr/app/
EXPOSE 9090
RUN echo 'Started Executing Command'
ENTRYPOINT ["java","-jar","blogsapp.jar","datasource=jdbc:mysql://mysqlcontainer:3306/blog_app?createDatabaseIfNotExist=true"]
CMD echo 'Container Started'