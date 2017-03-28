import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PVector;

public class Player_pointer extends Kinematic_data_structure{
	ArrayList<Float> store_bread_positionx = new ArrayList<Float>();
	  ArrayList<Float> store_bread_positiony = new ArrayList<Float>();

  Player_pointer(PApplet p, int pointerRadius, PVector initial_position,PVector initial_velocity,PVector intial_max_velocity,float initial_orientation,float initial_rotation,float initial_max_rotation) {
    parent = p;
    
    position = initial_position;
    orientation = initial_orientation;
    
    velocity = initial_velocity;
    rotation = initial_rotation;
    
//    w = parent.random(10,30);
    mouse = false;
  }

  // Draw stripe
  void display() {
	  
    parent.fill(0,0,0);
    parent.ellipse(position.x,position.y,40,40);
    
    //    PVector triangle_pos = new PVector(position.x+5,position.y-20 , position.x+5); 
    parent.pushMatrix();
    parent.translate(position.x,position.y);
    parent.rotate(PApplet.radians(orientation));
    parent.triangle(5,-20 ,+5,20,40,0);
    parent.rotate(PApplet.radians(orientation));
    parent.popMatrix();
  }

  
  void drawBreadCrumbs()
  {
	
	  if(position.x%20==0 && position.y%20==0)
	  {
		  PVector temp = new PVector(position.x-5, position.y-5);
		  store_bread_positionx.add(temp.x);
		  store_bread_positiony.add(temp.y);
	  }
	  
	  for(int i=0 ; i < store_bread_positionx.size(); i++)
	  {
    	  parent.fill(0,0,0);
		  parent.rect(store_bread_positionx.get(i)-2, store_bread_positiony.get(i)-2, 4, 4);
	  }
  }
  // Move stripe
  void update() 
  { 
	  
	  		if(position.y == (parent.height-20) && position.x != (parent.width-20) && orientation == 0)
	  		{
	  			position.x += velocity.x;
	  		}
	  		else if(position.x == (parent.width-20) && orientation > -90)
	  		{
	  			orientation -= rotation;
	  		}
	  		else if(position.x == (parent.width-20) && orientation == -90 && position.y != 20)
	  		{
	  			position.y -= velocity.y;
	  		}
	  		else if(position.y == (20) && orientation > -180)
	  		{
	  			orientation -= rotation;
	  		}
	  		else if(position.y == (20) && orientation == -180 && position.x != (20))
	  		{
	  			position.x -= velocity.x;
	  		}
	  		else if(position.x == (20) && orientation > -270)
	  		{
	  			orientation -= rotation;
	  		}
	  		else if(position.x == (20) && orientation == -270 && (position.y != parent.height-20))
	  		{
	  			position.y += velocity.y;
	  		}
	  		else if(position.y == (parent.height-20) && orientation > -360)
	  		{
	  			orientation -= rotation;
	  			if(orientation == -360)
	  				orientation =0;
	  		}
  }	


}