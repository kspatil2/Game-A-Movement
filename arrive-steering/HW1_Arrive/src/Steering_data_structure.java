import processing.core.PApplet;
import processing.core.PVector;



public class Steering_data_structure 
{
	  PVector position;		// Location of player
	  float orientation;
	  
	  PVector velocity;   // Velocity of player
	  float max_velocity;
	  float rotation;
	  float max_rotation;
	  
	  PVector acceleration; // Acceleration of player
	  float max_acceleration;
	  float angular_acceleration;
	  float max_angular_acceleration;	  

	  PApplet parent; // The parent PApplet that we will render ourselves onto
	  
	  public void update(int delta_time) // delta_time = time elapsed since last update 
	  {
		  position = PVector.add(position,PVector.mult(velocity, delta_time));
		  orientation += rotation * delta_time;
		  
		  if(orientation >= 360)
			  orientation -=360;
		  
		  velocity = PVector.add(velocity,PVector.mult(acceleration, delta_time));
		  rotation += angular_acceleration * delta_time;
		  
		  if(velocity.mag()> max_velocity)
		  {
			  velocity.normalize();
			  velocity = PVector.mult(velocity, max_velocity);
			  
		  }
		  else if(velocity.mag()<0)
			  velocity = new PVector(0,0);
		  
		  
		  if(java.lang.Math.abs(rotation) > max_rotation)
		  {
			  rotation = rotation*max_rotation/java.lang.Math.abs(rotation);
		  }
		  
	  }
	  
	  public int arrive(PVector target)
	  {
		  float dist;
		  float rad_of_dist = 30;
		  float rad_of_sat = 20;
		  if (position.x == target.x && position.y == target.y)
		  {
			  acceleration.set(new PVector(0,0));
			  return 0;
		  }
			 		  
		  PVector arrive_dir = new PVector();
		  
		  arrive_dir = PVector.sub(target, position);
		  dist = arrive_dir.mag();
		  
		  arrive_dir.normalize();
		  
//		  System.out.println(dist);
		  if(dist > rad_of_dist)
		  {
			  acceleration = PVector.mult(arrive_dir,max_acceleration);
		  }
		  else if(dist > (rad_of_dist+rad_of_sat)/2)
			  acceleration = PVector.mult(arrive_dir,-1*max_acceleration*(rad_of_dist-rad_of_sat)/rad_of_dist);
		  else if(dist > rad_of_sat )
			  acceleration = PVector.mult(arrive_dir,-1*max_acceleration*(rad_of_dist-rad_of_sat)/4*rad_of_dist);
		  else 
		  {
			  velocity = new PVector(0,0);
			  acceleration = new PVector(0,0);
		  }
		   		  
		  float angle;
		  float rad_of_angle = 20;
		  float rad_of_satangle = 4;  
		  // Remember the y becomes positive as we move down the screen
		  if(arrive_dir.x>0)
		  {
			  angle = rad_to_degree(java.lang.Math.atan(-arrive_dir.y/arrive_dir.x));
			  if(angle < 0)
				  angle = angle + 360;
		  }
		  else
			  angle = 180 + rad_to_degree(java.lang.Math.atan(-arrive_dir.y/arrive_dir.x));

		  float goalRotation = angle - orientation;
		  if(goalRotation > 180)
			  goalRotation -= 360;
		  else if(goalRotation < -180)
			  goalRotation =(goalRotation + 360);
		  
		  float rotation_dir= goalRotation/java.lang.Math.abs(goalRotation);
		  
		  if(java.lang.Math.abs(goalRotation) > rad_of_angle)
		  {
			  angular_acceleration = rotation_dir * max_angular_acceleration;
		  }
		  else if(java.lang.Math.abs(goalRotation) > rad_of_satangle)
		  {
			  angular_acceleration = max_angular_acceleration * goalRotation / rad_of_angle;
		  }
		  else
		  {
			  rotation = 0;
			  angular_acceleration = 0;
		  }
		  
//		  System.out.println("angle"+angle);
//		  System.out.println("orientation"+orientation);
//		  System.out.println("goalRotation"+goalRotation);
		  
		  return 1;
	  }
	  
	  // input lies between -pi/2 to pi/2
	  public float rad_to_degree(double d)
	  {
		  return (float) (d*180/java.lang.Math.PI);
	  }

}

