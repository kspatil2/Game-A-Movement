import processing.core.PApplet;
import processing.core.PVector;


public class Kinematic_data_structure {
	
		
	  PVector position;		// Location of player
	  float orientation;
	  PVector velocity;   // Velocity of player
	  float rotation;
	  
	  PVector max_velocity;
	  float max_rotation;
	  
//	  PVector acceleration; // Acceleration of player
//	  PVector max_acceleration;
//	  float angular_acceleration;
//	  float max_angular_acceleration;
	  
//	  float w;       // width of stripe
	  boolean mouse; // state of stripe (mouse is over or not?)
	  PApplet parent; // The parent PApplet that we will render ourselves onto
	
}
