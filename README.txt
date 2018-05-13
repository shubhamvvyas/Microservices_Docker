The directory content can be confusing and hence I would like to explain what each subdirectory has.
1.
/lab5:
This is the servlet/client from which network calls are made to the services deployed on docker container.
That is, normal java web application with all the required dependencies and ant built WAR file.
It has a properties folder which has a file called lab5Vyas.properties. All the URLs required to make network
calls to the lab5map and lab5calc are specified here. Just change the port number and run the docker container on 
that port.
/lib directory has all the jars required to make WAR file. I have used APACHE CLIENT, hence the dependency.

2.
/lab5map
This is the microservice for mapping number grade to letter grade.
It has all the dependencies required to build a WAR file except Jersey jars. Please paste all the jars in /lib
 subdirectory. If you are not able to build the jar, please see mapJars png(image) file in the submission folder to check for
 missing jars.
*****It has a folder called containermap which has a Dockerfile. Just copy WAR file(lab5_map_svyas5.war) to containermap
 and build the docker image.*****
I have also pushed the image to Docker Hub which can be accessed and run on your machine by giving following command:
docker container run -d -p <port-you-want-to-map>:8080 shubhamvvyas/lab5_vyas_map
I have also pushed shubhamvvyas/submitvyasmap to be on the safe side. Use this if above image is not working.

sample URL: http://localhost:8085/lab5_map_svyas5/rest/lab5map?grade=99
If the port has been mapped to 8085

3.
/labcalc
This is the microservice for calculating the grade for subject and year parameters.
It has all the dependencies required to build a WAR file except Jersey jars. Please paste all the jars in /lib
 subdirectory. If you are not able to build the jar, please see calcJars png(image) file in the submission folder to check for
 missing jars.
*****It has a folder called containercalc which has a Dockerfile and required dependencies. 
Just copy WAR file(lab5_calc_svyas5.war) to containercalc
 and build the docker image.*****
I have also pushed the image to Docker Hub which can be accessed and run on your machine by giving following command:
docker container run -d -p <port-you-want-to-map>:8080 -p <port-you-want-to-map>:5432 shubhamvvyas/lab5_vyas_calc
I have also pushed shubhamvvyas/submitvyascalc to be on the safe side. Use this if above image is not working.

sample URL: http://localhost:8086/lab5_calc_svyas5/rest/lab5calc?year=2&subject=History
If the port has been mapped to 8086

4.
/lab5-ec.svyas5
This is for the extra credit part having two subdirectories. 
a. containersubjects
It has all the files and folder required to build an image for NodeJS application to get subjects.
Default port is 3000 here so the docker command would also change.
I have also pushed the image to Docker Hub which can be accessed and run on your machine by giving following command:
docker container run -d -p <port-you-want-to-map>:3000 -p <port-you-want-to-map>:5432 shubhamvvyas/vyaslab5subjects:latest1
Please notice the tag change(latest1).
I have also pushed shubhamvvyas/submitvyassubjects to be on the safe side. Use this if above image is not working.

sample URL: http://localhost:3005/api/subjects
If the port has been mapped to 3005

b.
/lab5ec
There has been a slight change in the servlet/client since we had to dynamically populate drop-down with subjects.
Also, the lib jars are same as /lab5/lib. I have deleted it from extra credit folder to keep the submission size low.
Copy these jars from lab5/lib to ../lab5ec/lib in case you want to build WAR file.
This part also uses properties file for getting URLs for network calls.

PS:Docker images for all the three microservices can be built using:
docker build <name_you_want_to_give>:<tag> .
Make sure you are in specific container folder before building the image.

I have tried my best to explain each and everything required to build and run the microservices and servlet/client.
Sorry in advance for any inconvenience.
Please drop me a mail in case of ambiguity.
