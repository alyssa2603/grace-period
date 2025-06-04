package application;


import javafx.scene.image.Image;

public class Obstacle extends Sprite{
	private 
		String name;
		Image img;
		// Obstacle Images
		final static Image CAT_IMAGE = new Image("cat.png",GameStage.TILE_SIZE,GameStage.TILE_SIZE,false,false);
		final static Image SINGKO_IMAGE = new Image("singko.png",GameStage.TILE_SIZE,GameStage.TILE_SIZE,false,false);	
		final static Image FRIEND_IMAGE = new Image("friend.png",GameStage.TILE_SIZE,GameStage.TILE_SIZE,false,false);
	public 
		final static int SIZE = GameStage.TILE_SIZE;
		final static int MAX_OBSTACLES = 10;
		final static int MIN_OBSTACLES = 5;
		// Obstacle types
		final static String CAT="cat";
		final static String SINGKO = "singko";
		final static String FRIEND ="friend";
	
	// CONSTRUCTOR
	public Obstacle(String name,int xPos, int yPos) {
		super(xPos, yPos);
		this.name = name;
		switch(this.name) {
			case Obstacle.CAT: this.img = Obstacle.CAT_IMAGE; break;
			case Obstacle.SINGKO: this.img = Obstacle.SINGKO_IMAGE; break;
			case Obstacle.FRIEND: this.img = Obstacle.FRIEND_IMAGE; break;
		}

		this.width = Obstacle.SIZE-(GameStage.TILE_SIZE/4);
		this.height = Obstacle.SIZE-(GameStage.TILE_SIZE/4);
		this.loadImage(this.img);
	}
	
	
	public void takeObstacleEffect(Player myPlayer, GameTimer gt) {
		if (name == Obstacle.CAT) {
			System.out.println("Isko encounters a UPLB Cat and takes a picture! Stopped for 2 seconds.");
			gt.addtoTime(-2);
		} else if (name == Obstacle.SINGKO) {
			// Decrease vision
			myPlayer.active_singko = true;	// activate singko
			
		} else if (name == Obstacle.FRIEND) {
			System.out.println("Isko greets a Friend! Stopped for 3 seconds.");
			gt.addtoTime(-3);
		} this.setVisible(false); // remove visibility of obstacle
	}
	
	public String getName(){
		return this.name;
	}
}
