import processing.core.PApplet;
import processing.core.PVector;

// Reference : https://processing.org/examples/flocking.html

public class Flocking_Behavior extends PApplet {

	Player_pointer[] players = new Player_pointer[100];
	
	public static void main(String[] args)
	{
		PApplet.main("Flocking_Behavior");
	}
	
	
	public void settings()
	{
		size(500,500);
    }

    public void setup()
    {
		  float angle = random(TWO_PI);
		//angle=angle*360/TWO_PI;
    	  frameRate(30);
    	  for(int i =0; i < players.length/2;i++)
    	  {

    		int radius=5;
    		PVector initial_position = new PVector(this.width/2, this.height/2);
		  	PVector initial_velocity = new PVector(cos(angle), sin(angle)); // Velocity of player
		  	PVector initial_acceleration = new PVector(0,0);   // Velocity of player
		    	
		  	float initial_orientation=360 - angle*360/TWO_PI;		
		  	//System.out.println(initial_orientation);
		  //	System.out.println(angle);
		  	float initial_rotation=0;
		  	float initial_angular_acceleration = 0;
		   
		  	float max_velocity = (float) 2;
		  	float max_acceleration  = 1;
		  
		  	float max_rotation=5;
		  	float max_angular_acceleration=1;

		  	if(i==0)
		  		initial_position = new PVector(0, this.height/2);
		  	if(i==1)
		  		initial_position = new PVector(this.width/2, this.height);
		  	players[i] = new Player_pointer(this, radius, initial_position, initial_velocity, initial_acceleration, initial_orientation, initial_rotation, initial_angular_acceleration, max_velocity, max_acceleration, max_rotation, max_angular_acceleration);
    	  }
    	  
    	  for(int i =players.length/2; i < players.length;i++)
    	  {
    		int radius=5;
    		PVector initial_position = new PVector(this.width/2, this.height/2);
		  	PVector initial_velocity = new PVector(cos(angle), sin(angle)); // Velocity of player
		  	PVector initial_acceleration = new PVector(0,0);   // Velocity of player
		    	
		  	float initial_orientation=360 - angle*360/TWO_PI;		
		  //	System.out.println(initial_orientation);
		  //	System.out.println(angle);
		  	float initial_rotation=0;
		  	float initial_angular_acceleration = 0;
		   
		  	float max_velocity = (float) 2;
		  	float max_acceleration  = 2;
		  
		  	float max_rotation=5;
		  	float max_angular_acceleration=1;

		  	players[i] = new Player_pointer(this, radius, initial_position, initial_velocity, initial_acceleration, initial_orientation, initial_rotation, initial_angular_acceleration, max_velocity, max_acceleration, max_rotation, max_angular_acceleration);
    	  }
    }

    public void draw()
    {
    	  background(100);
    	  
//    	  if(arrive_flag ==1)
// 	    	  arrive_flag = player1.arrive(target);
    	  
    	  
    	  int flock_distance = 20;
    	  for(int i =2 ; i < players.length; i++)
    	  {
    		  players[i].flock(players,i,flock_distance);
    		  players[i].update(1); // input : delta_time
    		  players[i].display(frameCount,i);
    	  }
 	      
    	  if(frameCount%4==0)
    		  players[0].wander();
		  players[0].update(1); // input : delta_time
		  players[0].display(frameCount,0);
		  
		  if(frameCount%4==0)
			  players[1].wander();
		  players[1].update(1); // input : delta_time
		  players[1].display(frameCount,1);
    	  
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
