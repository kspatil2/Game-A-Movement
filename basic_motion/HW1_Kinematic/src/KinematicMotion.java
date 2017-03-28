import processing.core.PApplet;
import processing.core.PVector;

public class KinematicMotion extends PApplet{

	 
	  Player_pointer player1;
	  
	  public static void main(String[] args)
	  {
	      PApplet.main("KinematicMotion");
	  }

	  public void settings()
	  {
	      size(500,500);
	  }


	  public void setup() 
	  {
		  int radius=50;
		  PVector initial_position = new PVector(20, this.height-20);
		  PVector initial_velocity = new PVector(2,2);   // Velocity of player
		  PVector initial_max_velocity = new PVector(2,2);
		  
		  float initial_orientation=0;		  
		  float initial_rotation=2;
		  float initial_max_rotation=1;
		
	      player1 = new Player_pointer(this, radius, initial_position, initial_velocity, initial_max_velocity, initial_orientation, initial_rotation, initial_max_rotation );
	  }

	  public void draw() 
	  {
	      background(100);
	      player1.update();
	      player1.display();
	      player1.drawBreadCrumbs();
	  }
	  
	  

}