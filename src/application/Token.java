package application;

import javafx.scene.image.Image;

public class Token extends Sprite{
	private 
		String name;
		Image img;
		final static Image BLUEBOOK_IMAGE = new Image("bluebook.png",GameStage.TILE_SIZE,GameStage.TILE_SIZE,false,false);
		final static Image COFFEE_IMAGE = new Image("coffee.png",GameStage.TILE_SIZE,GameStage.TILE_SIZE,false,false);	
		final static Image MAP_IMAGE = new Image("map.png",GameStage.TILE_SIZE,GameStage.TILE_SIZE,false,false);
		final static Image PHONE_IMAGE = new Image("phone.png",GameStage.TILE_SIZE,GameStage.TILE_SIZE,false,false);
	public 
		final static String BLUEBOOK = "bluebook";
		final static String COFFEE = "coffee";
		final static String MAP = "map";
		final static String PHONE ="phone";
		final static int SIZE = GameStage.TILE_SIZE;	
	
	// CONSTRUCTOR
	public Token(String name, int xPos, int yPos) {
		super(xPos, yPos);
		this.name = name;
		switch(this.name) {
			case Token.BLUEBOOK: this.img = Token.BLUEBOOK_IMAGE; break;
			case Token.COFFEE: this.img = Token.COFFEE_IMAGE; break;
			case Token.PHONE: this.img = Token.PHONE_IMAGE; break;
			case Token.MAP: this.img = Token.MAP_IMAGE; break;
		}
		// Obstacle bounds
		this.width = Token.SIZE -(GameStage.TILE_SIZE/4);
		this.height = Token.SIZE-(GameStage.TILE_SIZE/4);
		// Load image
		this.loadImage(this.img);
	}
	
	public void takeTokenEffect(Player player, GameTimer gt) {
		if (name == Token.BLUEBOOK) {
			// Add 5 seconds to game time
			gt.addtoTime(5);
			System.out.println("Isko grabs a bluebook! Extra 5 seconds.");
		} else if (name == Token.COFFEE) {
			 //Increase player speed
			player.setSpeed(Player.INITIAL_SPEED*2);
			System.out.println("Isko drinks coffee! Speed increased. Lasts for 10 seconds."); player.active_coffee = true;	
		} else if (name == Token.PHONE) {
			 //Immortality; Immune to obstacles
			player.setImmune(true);
			System.out.println("Isko uses Phone! Immune to obstacles for 10 seconds.");	
		} else if (name == Token.MAP) { 
			// Reveals map for 5 seconds
			gt.addtoTime(5); // add 5 seconds to "pause" game while map is being shown
			System.out.println("Isko finds the campus map! Revealed for 3 seconds and adds 5 seconds."); player.active_map = true;		
		} this.setVisible(false); // remove visibility of token
	}
	
	public String getName() {
		return this.name;
	}
}
