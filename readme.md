CSC 584/484 Spring 17 Homework 1: Movement

Overview
Your task for this assignment is to explore various movement algorithms. Using Processing, you will implement various dynamic movement algorithms and compare their performance. 

RUNNING THE CODE 
1. Open Eclipse and import the individual java project from the sub folder where this file resides.
2. Add core.jar of the processing library and add to build path
3. Run the code and use this procedure for all the subparts.

<p align="left">
<img src="https://raw.githubusercontent.com/Kshitij-Patil/Game-AI-Movement/master/images/kinematic.png" width="480" height="360" border="10">
 </p>

Kinematic Motion(5pts)
Create a data structure to hold your steering and kinematic variables like the one we discussed in class. Make sure you include methods for getting the orientation from direction of motion,getting the direction of motion from orientation, and for applying the standard update. Using kinematic motion,have your shape start at the bottom left corner of the screen,and traverse around the edge until it returns to its starting location. Make sure to leave breadcrumbs as your shape traverses the screen. Save your code and label it “basic-motion”. It is probably a good idea to take a screenshot of your shape and the breadcrumbs after it completes its tour for inclusion in your writeup.

<p align="left">
<img src="https://raw.githubusercontent.com/Kshitij-Patil/Game-AI-Movement/master/images/arrive.png" width="480" height="360" border="10">
 </p>

ArriveSteeringBehaviors(15pts)
Implement the arrive algorithm we covered in class. Have your shape arrive at the location of mouse clicks. You can implement this by using Processing’s mousePressed() method. Make sure your shape is oriented in the direction of travel. Recall that you can accomplish this by implementing an orientation matching steering behavior. Test out at least two different methods for implementing arriving (this may include, but is not limited two, two different sets of parameters for the arrive algorithm). Which looks better? Why? Which is more successful? Why? It is probably a good idea to take a series of screenshots to illustrate your results. Save and label this ﬁle as “arrive-steering”.

<p align="left">
<img src="https://raw.githubusercontent.com/Kshitij-Patil/Game-AI-Movement/master/images/wandering.png" width="480" height="360" border="10">
 </p>

WanderSteeringBehaviors(15pts)
Implement the wander algorithm we covered in class. Make sure your shape is oriented in the direction of travel and to handle boundary violations gracefully. Implement at least two different methods for changing orientation. Which one looks better? Why? It is probably a good idea to take a series of screenshots to illustrate your results. Save and label this ﬁle as “wander-steering”.

<p align="left">
<img src="https://raw.githubusercontent.com/Kshitij-Patil/Game-AI-Movement/master/images/flocking.png" width="480" height="360" border="10">
 </p>

FlockingBehaviorandBlending/Arbitration(25pts)
Using multiple independent shapes,implement ﬂocking behavior using the standard Boids algorithm(Reynolds 87). The Boids algorithm includes separation (avoid collisions with nearby neighbors), alignment (velocity match center of mass of nearby neighbors), and cohesion (position match center of mass of nearby neighbors). You will have to blend these behaviors intelligently, using parameters of your choosing. You may implement other steering behaviors of your choosing, or invent your own. Just make it look as good as you can. What tweaks did you make to the algorithms to make ﬂocking work? Are things easier or harder with more followers? (Note, you should probably run experiments using varying numbers of followers.) What happens if you have two wanders and followers follow the closest wanderer? Don’t forget to take screenshots to support your analysis. It is probably a good idea to take a series of screenshots to illustrate your results. Save and label this ﬁle as “ﬂocking-behavior”.


<p align="left">
<img src="https://raw.githubusercontent.com/Kshitij-Patil/Game-AI-Movement/master/images/flocking_seperation.png" width="480" height="360" border="10">
 </p>
 
 <p align="left">
<img src="https://raw.githubusercontent.com/Kshitij-Patil/Game-AI-Movement/master/images/flocking_velocity.png" width="480" height="360" border="10">
 </p>

<p align="left">
<img src="https://raw.githubusercontent.com/Kshitij-Patil/Game-AI-Movement/master/images/flocking_leaders.png" width="480" height="360" border="10">
 </p>
