# Event-Manager-Web-Application
Event manager application for event Registration and Management.

A Web Application that allows users 
- To Login/Signup to the App. 
- Create/Modify/Delete an Event
- Participate in an event
- The User who has created the event will be able to view the participants for that event and will be able to Modify/Delete that event.
- On Any update/deletion made on that event, the application will send a notification to the particpants.
- Participants can mark the notification as read. In this case, the notiifcation will not be shown later for that particular user.

<h2>Technologies</h2>
<ul>
 <li>Angular</li>
 <li> Angular cli</li>
 <li>Java</li>
 <li>Spring boot</li>
 <li>Bootstrap</li>
 <li>JPA</li>
 <li>H2 database (in memory database)</li>
</ul>
 <h2>Running Demo</h2>
 
 <h3>Uploaded a Gif Event management application to view the demo</h3> 
 
 To run a demo Applcation
 Web application:
  - Install node
  - Install Angular cli
  - Download the project
  - Open the folder frontend and go to eventmanager
  - Run npm install to download all the  dependencies for your angular project.
  - Run ng serve 
  
Web server:
 - Open the folder backend
 - Run as java application
 
 By default, Web app runs in port 4200, and web server runs in port 8080.
 
<h2> About Application</h2>
<h3>Database architecture is provided in Database model for Event Management Web Application.docx</h3>
<ul>
  <li>Basic authentication and authorization</li>
  <li>Form validation in the UI</li>
  <li>Basic user validation in the server</li>
  <li>In memory database</li>
</ul>

<h4>Login Component</h4>
 <ul>
 <li> Uses basic Authentication with username and password and authorization is done based on the roles. By defualt all users who have  registered are provided with Role "USER" and granted authority for that role in server.</li>
 <li> If user doesn't have an account. Option to signup in the signup page to enter user details </li>
 <li> User password is encrypted and stored in database. </li>
 <li> Wrong credentials will show an error message in Application</li>
 <li> User name is unique for all user. If username already exists, error message will be displayed</li>
 <li> In front end, if user is not logged in. Other pages cannot be accessed. Once accessed directly through URL, It will again redriect to Login page.</li>
 </ul>

<h4>Dashboard</h4>
 <ul>
 <li> On login, User will be navigated to dashboard </li>
 <li> Contains section to create a new event with form validation</li>
 <li> Section showing list of event participated by the logged in user</li>
 <li> Button, which is used to navigate to event list page</li>
 </ul>

<h4>Event List</h4>
 <ul>
 <li>Shows list of all events created by all users who have resgitered to the application</li>
 <li>Shows Modify/Delete button for the events which are created by the logged in user, whihc will allow user to modify/delete the existing event
 <li>Shows show details button, which is used to view the details of events created by other users.
 <li>Register button to register for a particular event. If the user has already registered, it will show "Regsitered already"
 <li>If for a particular event, the number of participants level has reached. it will show Housefull and the user cannot participate in that event.(this validation is provided only in UI not handled in backend - to do)</li>
 <li>Participants button to view the list of users participated in the event created by the logged in user.
 </ul>

<h4>Notifications</h4>
  <ul>
 <li>If the User who created the event, modifies/deletes the event, All the users who has registered to the particualr event will receiev a notification on the notification tab.</li>
 <li>If the created user is also a participant for an event, he will not receive notification.</li>
 <li>Participants can go to notification tab and see the notifications and click on read.</li>
 <li>On click of read, notification will be removed for that particualr user.(but still other participants will be able to see the notification when they login)</li>
 <li>Notification conatins Event name and action performed on that event.</li>
 <li>On clicking the event name, it will show the updated event details.</li>
 </ul>

<h4>Logout</h4>
  - User will be logged out from application
 
 
 

 
  

 
 
