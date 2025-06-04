package application;

import javafx.scene.image.Image;

public class Player extends Sprite{
	private 
		String name;
		boolean won, immune, active_singko = false, active_map = false, active_coffee = false;
		int speed, screenX, screenY;
		static final int INITIAL_SPEED = 15, WIDTH = GameStage.TILE_SIZE;
	public 
		final static Image IMG_FRONT = new Image("front.png",Player.WIDTH,Player.WIDTH,false,false);
		final static Image IMG_BACK = new Image("back.png",Player.WIDTH,Player.WIDTH,false,false);
		final static Image IMG_RIGHT = new Image("right.png",Player.WIDTH,Player.WIDTH,false,false);
		final static Image IMG_LEFT = new Image("left.png",Player.WIDTH,Player.WIDTH,false,false);		
		 
		// Player's spawn position on map 38 37
		static int worldX = 38*GameStage.TILE_SIZE , worldY = 37*GameStage.TILE_SIZE-(GameStage.TILE_SIZE/2); 
		static String UP = "up", DOWN = "down", LEFT = "left", RIGHT = "right";
		
		
	// PLAYER CONSTRUCTOR
	public Player(String name, int x, int y){
		super(x,y);	
		this.name = name;
		this.speed = Player.INITIAL_SPEED;
		this.img = Player.IMG_FRONT;
		this.won = false;
		this.immune = false;
		
		// Player bounds for map movement
		this.bounds.setX(23);
		this.bounds.setY(32);
		this.bounds.setWidth(Player.WIDTH-50);
		this.bounds.setHeight(Player.WIDTH-36);
		
		// Player bounds for obstacle and token collision
		this.width = 3;
		this.height = 3;
		
		// // Player's position on screen(fixed)
		this.screenX = GameStage.WINDOW_WIDTH/2;
		this.screenY = GameStage.WINDOW_HEIGHT/2;
		
		// Isko Image
		this.loadImage(this.img);
	}
	
	public void setWon(){
		this.won = true;
	}
	
	public boolean getWon() {
		return this.won;
	}
	
	public String getName(){
		return this.name;
	}
	
	public int getSpeed(){
		return this.speed;
	}
	
	public void setSpeed(int s) {
		this.speed = s;
	}
	
	public void moveX(int speed){
		this.x += speed;
	}
	
	public void moveY(int speed){
		this.y += speed;
	}
	
	public void setImmune(boolean immune) {
		this.immune = immune;
	}
	
	// Checks if there will be collision on map tiles
	public boolean checkCollision(String direction, int mapNum[][]) {
		// Player's Coordinates on map
		int playerLeftX = this.x + (int)this.bounds.getX();
		int playerRightX = this.x + (int) this.bounds.getX() + (int) this.bounds.getWidth();
		int playerTopY = this.y + (int) this.bounds.getY();
		int playerBottomY = this.y + (int) this.bounds.getY() + (int) this.bounds.getHeight();
		
		// Player's row and column locations
		int playerLCol = playerLeftX/GameStage.TILE_SIZE;
		int playerRCol = playerRightX/GameStage.TILE_SIZE;
		int playerTRow = playerTopY/GameStage.TILE_SIZE;
		int playerBRow = playerBottomY/GameStage.TILE_SIZE;
	
		int tileNum1, tileNum2;
		
		if (direction == Player.UP) {
			//Calculate location of tile in front
			playerTRow = (playerTopY - this.speed)/GameStage.TILE_SIZE;
			
			// Get tiles
			tileNum1 = mapNum[playerTRow][playerLCol];
			tileNum2 = mapNum[playerTRow][playerRCol];
			
			// Check if player is able to walk on tiles
			if(TileBoard.tiles[tileNum1].collision == true || TileBoard.tiles[tileNum2].collision == true) {
				return true;
			}
		} else if (direction == Player.DOWN) {
			//Calculate location of tile below
			playerBRow = (playerBottomY + this.speed)/GameStage.TILE_SIZE;
			
			// Get tiles
			tileNum1 = mapNum[playerBRow][playerLCol];
			tileNum2 = mapNum[playerBRow][playerRCol];
			
			// Check if player is able to walk on tiles
			if(TileBoard.tiles[tileNum1].collision == true || TileBoard.tiles[tileNum2].collision == true) {
				return true;
			}
			
		} else if (direction == Player.LEFT) {
			//Calculate location of tile on the left
			playerLCol = (playerLeftX - this.speed)/GameStage.TILE_SIZE;
			
			// Get tiles
			tileNum1 = mapNum[playerTRow][playerLCol];
			tileNum2 = mapNum[playerBRow][playerLCol];
			
			// Check if player is able to walk on tiles
			if(TileBoard.tiles[tileNum1].collision == true || TileBoard.tiles[tileNum2].collision == true) {
				return true;
			}
			
		} else if (direction == Player.RIGHT) {
			//Calculate location of tile on right
			playerRCol = (playerRightX + this.speed)/GameStage.TILE_SIZE;
			
			// Get tiles
			tileNum1 = mapNum[playerTRow][playerRCol];
			tileNum2 = mapNum[playerBRow][playerRCol];
			
			// Check if player is able to walk on tiles
			if(TileBoard.tiles[tileNum1].collision == true || TileBoard.tiles[tileNum2].collision == true) {
				return true;
			}
			
		} return false;
	}
}