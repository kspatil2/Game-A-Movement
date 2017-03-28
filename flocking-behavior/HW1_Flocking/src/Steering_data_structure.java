import java.util.Random;

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
		  acceleration.limit(max_acceleration);
		  position = PVector.add(position,PVector.mult(velocity, delta_time));
		  orientation += rotation * delta_time;
		  
		  if(orientation >= 360)
			  orientation -=360;
		  
		  velocity = PVector.add(velocity,PVector.mult(acceleration, delta_time));
		  adjust_orientation_in_velocity_direction();
		  
		  
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
		  
		  if(position.x >= parent.width)
			  position.x = 0;
		  else if(position.x < 0)
			  position.x = parent.width;
		  
		  if(position.y >= parent.height)
			  position.y = 0;
		  else if(position.y < 0)
			  position.y = parent.height;
		  
		//  System.out.println(position);
		  acceleration.mult(0);
	  }
	  
	  
	  public void adjust_orientation_in_velocity_direction()
	  {
		  
//		  orientation = velocity.heading()+PApplet.PI/2;
		  float angle;
		  float rad_of_angle = 20;
		  float rad_of_satangle = 4;  
		  // Remember the y becomes positive as we move down the screen
		  if(velocity.x>0)
		  {
			  angle = rad_to_degree(java.lang.Math.atan(-velocity.y/velocity.x));
			  if(angle < 0)
				  angle = angle + 360;
		  }
		  else
			  angle = 180 + rad_to_degree(java.lang.Math.atan(-velocity.y/velocity.x));

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
		  else if(dist > rad_of_sat)
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

	  public void wander()
	  {
		
		  Random rn = new Random();
		  float rand1 = rn.nextFloat();
		  float rand2 = rn.nextFloat();
		  //		  System.out.println(rn.nextDouble()*2-1);
		  double rand_num = rand1-rand2;
		  rotation = (float) (max_rotation*rand_num);
		  
		  
		  velocity.x = (float) (max_velocity*java.lang.Math.cos(PApplet.radians(360-orientation)));
		  velocity.y = (float) (max_velocity*java.lang.Math.sin(PApplet.radians(360-orientation)));
//		  System.out.println(velocity);
//		  System.out.println(orientation);
	  }
	  
	  
	  public PVector separate1(Player_pointer[] players,int index, int flock_dist)
	  {
		  int count=0;
		  
		  PVector steer= new PVector(0,0);
		  float distance1 = PVector.sub(players[index].position, players[0].position).mag();
		  float distance2 = PVector.sub(players[index].position, players[1].position).mag();
		  PVector temp_dist;
			  if(distance1 < distance2)
			  {
				  System.out.println(0+" "+index);
				  temp_dist = PVector.sub(players[index].position, players[0].position);
				  temp_dist.normalize();
			      temp_dist.div(distance1);        // Weight by distance
			        
			  }
			  else
			  {
				  System.out.println(1+" "+index);
				    temp_dist = PVector.sub(players[index].position, players[1].position);
			        temp_dist.normalize();
			        temp_dist.div(distance2);        // Weight by distance
			        
			                   // Keep track of how many 
			  }
		  
			  
			  steer.set(temp_dist);
			  
			  if (steer.mag() > 0) 
			  {
			      steer.normalize();
			      steer.mult(max_velocity);
			      steer.sub(velocity);
			      steer.limit(max_acceleration);
			      return steer;
			  }
			  else
			    	return new PVector(0,0);
		  
		
		  
	  }

	  public PVector velocityMatch1(Player_pointer[] players,int index, int flock_dist)
	  {
		  int count=0;
		  PVector sum= new PVector(0,0);
		  float distance1 = PVector.sub(players[index].position, players[0].position).mag();
		  float distance2 = PVector.sub(players[index].position, players[1].position).mag();
		  PVector temp_dist;
			  if(distance1 < distance2)
			  {
				  sum.set(players[0].velocity);
			  }
			  else
			  {
			        sum.set(players[1].velocity);
			    
			  }
			  
			      sum.normalize();
			      sum.mult(max_velocity);
			      PVector steer = PVector.sub(sum,velocity);
			      steer.limit(max_acceleration);
			      return steer;
		}
	  
	  	
	  public PVector Cohesion1(Player_pointer[] players,int index, int flock_dist)
	  {
		  int count=0;
		  PVector sum= new PVector(0,0);
		  float distance1 = PVector.sub(players[index].position, players[0].position).mag();
		  float distance2 = PVector.sub(players[index].position, players[1].position).mag();
		  PVector temp_dist;
			  if(distance1 < distance2)
			  {
				  sum.set(players[0].position);
			  }
			  else
			  {
			        sum.set(players[1].position);
			    
			  }
		  
		  
			      //sum.div((float)count);
			      return seek1(sum);
			
}

	  PVector seek1(PVector target) 
	  {
		    PVector desired = PVector.sub(target, position);  

		    desired.normalize();
		    desired.mult(max_velocity);

		    PVector steer = PVector.sub(desired, velocity);
		    steer.limit(max_acceleration);  // Limit to maximum steering force
		    return steer;
		}
}

