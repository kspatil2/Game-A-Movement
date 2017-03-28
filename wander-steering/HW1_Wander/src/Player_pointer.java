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
  void display(int frameCount) {
	  
    parent.fill(0,0,0);
    parent.ellipse(position.x,position.y,2*pointer_radius,2*pointer_radius);  
    
    
    parent.pushMatrix();
    parent.translate(position.x,position.y);
    parent.rotate(PApplet.radians(360-orientation));
    parent.triangle(pointer_radius/4,-1*pointer_radius ,pointer_radius/4,pointer_radius,2*pointer_radius,0);
    parent.rotate(PApplet.radians(360-orientation));
    parent.popMatrix();
    drawBreadCrumbs(frameCount);
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
  }}