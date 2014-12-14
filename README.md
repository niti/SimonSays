## Simon Says ##
Team Members: Stephen Feldman, Niti Paudyal & Nithya Murali


Implemented the classic game “Simon Says” using an Android app and a Raspberry Pi.  “Simon Says” is a memory game requiring the player to memorize sequences of illuminated lights and recall them within limited time constraints.  The more difficult and lengthy the sequence, the more points are earned!


![alt tag](https://cloud.githubusercontent.com/assets/5305478/5426477/32ba1e74-8321-11e4-993d-f571035d9895.jpg)



Key Features: 
Uses device’s accelerometer for a shake-to-start-game function
Ability to connect with any Raspberry Pi and lights to display a series of lights for the user to follow
Uses Facebook API to post status updates and promote game 

How to use the game? 
Insert the URL of your Raspberry Pi 
Shake the device to start the game portrait side 
The game starts immediately after a successful shake
The object of the game is to memorize the sequence of colors displayed on the LED strip.  Players must memorize sequences of colors as they are displayed, and then must correctly recall them by pressing their corresponding buttons in the same order. As each level is successfully completed, more difficult sequences are presented.  *Sequences may include a single color shown repeatedly - the user must enter the same color the same number of times that it is shown.
When players fail to correctly recall the presented sequence or the timer runs out before they can finish, the game is over.  Players will see their score and have the option to promote and share their game experience on Facebook. 

Besides a URL that connects to a Raspberry Pi, no other username/passwords are required to operate the application.  However, the player must install the Facebook App and login in order to post to Facebook from the Simon Says App.

Links to our final APK: http://plato.cs.virginia.edu/~sjf5gf/rpiFinal/app-release.apk
