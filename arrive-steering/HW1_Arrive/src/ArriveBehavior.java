import processing.core.PApplet;
import processing.core.PVector;

public class ArriveBehavior extends PApplet{

	Player_pointer player1;
	
	public static void main(String[] args) 
	{	
		PApplet.main("ArriveBehavior");
	}
	
	public void settings()
	{
		size(500,500);
    }

    public void setup()
    {
    	  frameRate(30);
    	  int radius=20;
		  PVector initial_position = new PVector(20, this.height-20);
		  PVector initial_velocity = new PVector(0,0);   // Velocity of player
		  PVector initial_acceleration = new PVector(0,0);   // Velocity of player
		  
		  float initial_orientation=0;		  
		  float initial_rotation=0;
		  float initial_angular_acceleration = 0;
		  
		  // 4 too small 5 too big 4.8 just exact 
		  float max_velocity = (float) 8;
		  float max_acceleration  = 1;
		  
		  float max_rotation=10;
		  float max_angular_acceleration=3;

	      player1 = new Player_pointer(this, radius, initial_position, initial_velocity, initial_acceleration, initial_orientation, initial_rotation, initial_angular_acceleration, max_velocity, max_acceleration, max_rotation, max_angular_acceleration);
    }

    public void draw()
    {
    	  background(100);
    	  
    	  if(arrive_flag ==1)
 	    	  arrive_flag = player1.arrive(target);
    	  
 	      player1.update(1);
 	      player1.display(frameCount);
 	      
 	      
    }
    
    int arrive_flag = 0; 
    PVector target = new PVector(0,0);
    int mouse_clicked = 0;
    public void mousePressed() 
    {
    	// get mouse x and y coordinate, save in target and call arrive
    	target.x = mouseX;
    	target.y = mouseY;
    	arrive_flag=1;
    }
    
}


