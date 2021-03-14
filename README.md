# JSFLoginProject

<p align="left">
  <img src="https://www.nicepng.com/png/full/854-8546612_java-ee-java-ee-logo-svg.png" width="150">
  <img src="http://logovectorseek.com/wp-content/uploads/2019/10/bootstrap-logo-vector.png" width="150">
  <img src="https://camo.githubusercontent.com/f85f882cb31eeaeee657ec955313015c30378e8f56c3dc2f06933b617a276cfd/68747470733a2f2f77372e706e6777696e672e636f6d2f706e67732f3734372f3739382f706e672d7472616e73706172656e742d6d7973716c2d6c6f676f2d6d7973716c2d64617461626173652d7765622d646576656c6f706d656e742d636f6d70757465722d736f6674776172652d646f6c7068696e2d6d6172696e652d6d616d6d616c2d616e696d616c732d746578742d7468756d626e61696c2e706e67" width="150">
</p>

A JSP login application in Java EE. 

* JSFs are used as the template engine.
* MySQL is the database type.
* The application will allow 2 different types of user to log in.
  * a normal user.
  * an admin.
* A welcome screen will be presented to user.
* An admin has the ability to add new user to the database directly from the UI.
* The application has security features to make sure a user cannot see the options an admin can. 
* Bootstrap and Facelets are used to beautify the UI.
* an MD5 Hashing algorithm is used to hash all passwords to ensure that even the admin doesn't know the user passwords.
* The database structure (<i>Entity Relationship Diagram</i>) is as follows: 
  <img src="https://github.com/MalikS789/JSFLoginProject/blob/master/src/main/resources/screenshots/ERD.bmp">
  * Note that in this instance the id is not auto incremented and thus the program handled assigning unique ids.

<h1>What it looks like:</h1>

When you reach the homepage, you are greeted with this screen:

 <img src="https://github.com/MalikS789/JSFLoginProject/blob/master/src/main/resources/screenshots/Untitled.bmp">

* When a <i> user </i> logs in, they will be taken to a welcome screen:
   <img src="https://github.com/MalikS789/JSFLoginProject/blob/master/src/main/resources/screenshots/Untitled2.bmp">
* When a <i> admin </i> logs in, they are taken to a page that offers them several options:
   <img src="https://github.com/MalikS789/JSFLoginProject/blob/master/src/main/resources/screenshots/Untitled3.bmp">
  * view all users
    <p align="left">
    <img src="https://github.com/MalikS789/JSFLoginProject/blob/master/src/main/resources/screenshots/Untitled4.bmp">
    </p>
  * Register a new user to the database
    <img src="https://github.com/MalikS789/JSFLoginProject/blob/master/src/main/resources/screenshots/Untitled5.bmp">
    <img src="https://github.com/MalikS789/JSFLoginProject/blob/master/src/main/resources/screenshots/Untitled6.bmp">
  * Change the user type of an existing user
    <img src="https://github.com/MalikS789/JSFLoginProject/blob/master/src/main/resources/screenshots/Untitled7.bmp">
