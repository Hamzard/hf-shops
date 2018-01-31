# Information
Hidden founders web codding challenge.

# Tech
* Database : MongoDB
* Backend: Java/Spring
* Frontend: Vuejs

# Installation
### Backend
First, make sure your MongoDB server is runing.
Then install and run backend server as follows

```sh
$ cd backend
$ mvn clean install
$ mvn spring-boot:run
```
#### Notes
* The backend server runs on port 8088
* It expect the mongodb port to be the default 27017
* It looks for the database with the name ShopDB to use.

All these parameters can be changed in `/backend/src/main/resources/application.properties`
If you choose to change backend port, be sure to change it also in `/frontend/src/http/http-common.js` base URL.
### Frontend
Install dependencies and start the client as follows
```sh
$ cd frontend
$ npm install
$ npm start
```
#### Notes
* The frontend client runs on port 8080. If you wish to change it, be sure to also change it in `backend/src/main/java/../confing/CorsConfig.java` allowed origins.



