package application;

import javafx.scene.image.Image;

public class Tile {
	public
		int x,y;
		Image image;
		boolean collision = false;
		
	public Tile(Image image, boolean collision) {
		this.image = image;
		this.collision = collision; // If player is able to walk on tile
	}
	
	public Tile(Image image, boolean collision, int x, int y) {
		this.image = image;
		this.collision = collision; // If player is able to walk on tile
		this.x = x*GameStage.TILE_SIZE;
		this.y = y*GameStage.TILE_SIZE;
	}
}

