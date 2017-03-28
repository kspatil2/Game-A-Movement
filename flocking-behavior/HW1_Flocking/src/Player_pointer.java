import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PVector;

public class Player_pointer extends Steering_data_structure
{
	ArrayList<Float> store_bread_positionx = new ArrayList<Float>();
	ArrayList<Float> store_bread_positiony = new ArrayList<Float>();
	int pointer_radius=10;
	
    Player_pointer(PApplet p, int pointerRadius, PVector initial_position,PVector initial_velocity,PVector initial_acceleration,float initial_orientation,float initial_rotation,float initial_angular_acceleration,float max_velocity, float max_acceleration, float max_rotation, float max_angular_acceleration) 
    {
    	parent = p;
    	pointer_radius = pointerRadius;
    	
    	position = initial_position;
    	orientation = initial_orientation;
    	
    	velocity = initial_velocity;
    	rotation = initial_rotation;
    	this.max_velocity = max_velocity;
    	this.max_rotation = max_rotation;
    	
    	acceleration = initial_acceleration;
    	angular_acceleration = initial_angular_acceleration;
    	this.max_acceleration = max_acceleration;
    	this.max_angular_acceleration = max_angular_acceleration;

  }

  // Draw stripe
  void display(int frameCount, int index) {
//		parent.fill(0,0,0);
	if(index <=1)
		parent.fill(255,255,255);
	else 
		parent.fill(0,0,0);
    parent.ellipse(position.x,position.y,2*pointer_radius,2*pointer_radius);  
    
    
    parent.pushMatrix();
    parent.translate(position.x,position.y);
    parent.rotate(PApplet.radians(360-orientation));
    parent.triangle(pointer_radius/4,-1*pointer_radius ,pointer_radius/4,pointer_radius,2*pointer_radius,0);
    parent.rotate(PApplet.radians(360-orientation));
    parent.popMatrix();
//    drawBreadCrumbs(frameCount);
  }

  
  void drawBreadCrumbs(int frameCount)
  {
	
	  if(frameCount % 2 ==0)
	  {
		  PVector temp = new PVector(position.x-5, position.y-5);
		  store_bread_positionx.add(temp.x);
		  store_bread_positiony.add(temp.y);
	  }
	
	  for(int i=0 ; i < store_bread_positionx.size(); i++)
	  {
    	  parent.fill(0,0,0);
		  parent.rect(store_bread_positionx.get(i)-1, store_bread_positiony.get(i)-1, 2, 2);
	  }
  }
  
  public PVector separate(Player_pointer[] players,int index, int flock_dist)
  {
	  int count=0;
	  PVector steer= new PVector(0,0);
	  for(int i=0; i <=index-1; i++)
	  {
		  if(index == 0)
			  break;
		  PVector temp_dist = PVector.sub(players[index].position, players[i].position);
		  float distance = temp_dist.mag();
		  if(distance < flock_dist)
		  {
		        temp_dist.normalize();
		        temp_dist.div(distance);        // Weight by distance
		        steer.add(temp_dist);
		        count++;            // Keep track of how many 
		  }
	  }
	  
	  for(int i=index+1; i < players.length; i++)
	  {
		  
		  PVector temp_dist = PVector.sub(players[index].position, players[i].position);
		  float distance = temp_dist.mag();
		  if(distance < flock_dist)
		  {
		        temp_dist.normalize();
		        temp_dist.div(distance);        // Weight by distance
		        steer.add(temp_dist);
		        count++;            // Keep track of how many 
		  }
	  }
		  
		  if (count > 0) 
		  {
		      steer.div((float)count);
		  }
		  
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

  public PVector velocityMatch(Player_pointer[] players,int index, int flock_dist)
  {
	  int count=0;
	  PVector sum= new PVector(0,0);
	  for(int i=0; i <=index-1; i++)
	  {
		  if(index == 0)
			  break;
		  PVector temp_dist = PVector.sub(players[index].position, players[i].position);
		  float distance = temp_dist.mag();
		  if(distance < flock_dist)
		  {
		        sum.add(players[i].velocity);
		        count++;            // Keep track of how many 
		  }
	  } 
	  
	  for(int i=index+1; i < players.length; i++)
	  {
		  
		  PVector temp_dist = PVector.sub(players[index].position, players[i].position);
		  float distance = temp_dist.mag();
		  if(distance < flock_dist)
		  {
		        sum.add(players[i].velocity);
		        count++;            // Keep track of how many 
		  }
	  } 
		
	  if (count > 0) 
		  {
		      sum.div((float)count);
		  }
		  
		  if (sum.mag() > 0) 
		  {
		      sum.normalize();
		      sum.mult(max_velocity);
		      PVector steer = PVector.sub(sum,velocity);
		      steer.limit(max_acceleration);
		      return steer;
		  }
		  else
			  return new PVector(0,0);
	}
  
  	
  public PVector Cohesion(Player_pointer[] players,int index, int flock_dist)
  {
	  int count=0;
	  PVector sum= new PVector(0,0);
	  for(int i=0; i <=index-1; i++)
	  {
		  if(index == 0)
			  break;
		  PVector temp_dist = PVector.sub(players[index].position, players[i].position);
		  float distance = temp_dist.mag();
		  if(distance < flock_dist)
		  {
		        sum.add(players[i].position);
		        count++;            // Keep track of how many 
		  }
	  } 
	  
	  for(int i=index+1; i < players.length; i++)
	  {
		  
		  PVector temp_dist = PVector.sub(players[index].position, players[i].position);
		  float distance = temp_dist.mag();
		  if(distance < flock_dist)
		  {
		        sum.add(players[i].position);
		        count++;            // Keep track of how many 
		  }
	  } 
		
	  if (count > 0) 
		  {
		      sum.div((float)count);
		      return seek(sum);
		  }
	  else
		  return new PVector(0,0);
  	}
  
  PVector seek(PVector target) 
  {
	    PVector desired = PVector.sub(target, position);  

	    desired.normalize();
	    desired.mult(max_velocity);

	    PVector steer = PVector.sub(desired, velocity);
	    steer.limit(max_acceleration);  // Limit to maximum steering force
	    return steer;
	}
  
  public void flock(Player_pointer[] players,int index,int flock_distance)
  {
	  
	  	PVector sep = separate(players, index, flock_distance);   // Separation
	    PVector velM = velocityMatch1(players, index, flock_distance);      // Alignment
	    PVector coh = Cohesion1(players, index, flock_distance);   // Cohesion
	    // Arbitrarily weight these forces
	    sep.mult((float) 2.5);
	    velM.mult((float) 1.2);
	    coh.mult((float) 1.5);
	    // Add the force vectors to acceleration
	    applyForce(sep);
	    applyForce(velM);
	    applyForce(coh);
 }
  
  void applyForce(PVector force) 
  {
	    // We could add mass here if we want A = F / M
	    acceleration.add(force);	  		  
		 
  }
	  
	  

  
  

}