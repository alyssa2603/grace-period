package application;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class GameTimer extends AnimationTimer{
	private
		GraphicsContext gc; 
		Scene theScene; 
		Player myPlayer;
		TileBoard board;
		
		// Array of obstacles and Tokens
		ArrayList<Obstacle> obstacles;
		ArrayList<Token> tokens;
		int obstacleLoc[][]; 
		
		
		boolean despawnHold = true;
		boolean spawnHold = false;
		
		// Effect counters
		int coffeeCounter;
		int mapCounter;
		int phoneCounter; 
		int singkoCounter;
		// Game time counters
		int gameCounter; 
		int secondCounter;
		// Game element counters
		int obstacleCounter;
		int tokenCounter;
	
		
		float deleteToken;
		float spawnToken;
		float timeElapsed;
		String gameCounterText;
		
					// Game timer
		static int gameTime = 90, spriteCounter=0; 
		
	// CONSTRUCTOR
	public GameTimer(GraphicsContext gc, Scene theScene){
		this.gc = gc;
		this.theScene = theScene;
		this.board = new TileBoard();
		this.myPlayer = new Player("Isko",Player.worldX,Player.worldY); // Player Instance
		this.obstacles = new ArrayList<Obstacle>();
		this.tokens = new ArrayList<Token>();
		
		// Load map and obstacles
		try {this.board.loadMapFile();} catch (FileNotFoundException e){e.printStackTrace();}
		obstacleLoc = this.board.spawnObstacles(this.obstacles); // Spawn obstacles
		
		// Set counters
		this.obstacleCounter = 0;
		this.tokenCounter = 0;
		this.gameCounter = GameTimer.gameTime;
		this.gameCounterText = "1:00";
		this.timeElapsed = this.spawnToken = this.deleteToken = System.nanoTime();
	}

	@Override
	public void handle(long currentTime) {
		gameCountdown(currentTime);
		this.gc.clearRect(0, 0, GameStage.WINDOW_WIDTH,GameStage.WINDOW_HEIGHT);
		// Render map and Obstacles
		this.board.drawMap(this.gc, this.myPlayer);
		this.board.drawObstacles(this.gc, this.myPlayer, this.obstacles);
		
		// Spawn tokens
		if(!spawnHold) {this.spawnToken(currentTime);}
		// Render Tokens
		this.board.drawTokens(this.gc, myPlayer, tokens);
		// Render Player
		this.myPlayer.render(this.gc,this.myPlayer.screenX,this.myPlayer.screenY);
		this.board.drawOverlays(this.gc, this.myPlayer);
		
		// Check for collision
		this.collisionObstacles();
		this.collisionTokens();
		
		// Tracking token and obstacle durations
		this.gameTimers();
				
		// Deleting tokens
		if (!despawnHold){this.deleteTokens(currentTime);}
		
		// Check if player reached the finish point -- internally calls drawGameOver()
		this.checkFinish();
		 
		// If time runs out
		if(this.gameCounter <= 0) {
	       	this.stop();
	       	this.drawGameOver(); //Show game-over
	     }
		
		 // Render timer on screen
		 this.drawCounters();
		 
	 	// Calls method to handle key press event
		 this.handleKeyPressEvent(currentTime);
	}
	
	// Method listens and handles the key press events
	private void handleKeyPressEvent(long currentTime) {
		theScene.setOnKeyPressed(new EventHandler<KeyEvent>(){
			public void handle(KeyEvent e){
	           	KeyCode code = e.getCode();
	           	// Move player with appropriate key press
	               moveMyPlayer(code,currentTime);
			}
		});
   }
	// Background or "Player" Movement
	private void moveMyPlayer(KeyCode ke, long currentTime) {
		if(ke==KeyCode.UP|| ke==KeyCode.W){
			// Checks if player can move to the tile
			if (!this.myPlayer.checkCollision(Player.UP, this.board.MapNum)) {
				this.myPlayer.moveY(-myPlayer.speed);}this.myPlayer.loadImage(Player.IMG_BACK);}

		if(ke==KeyCode.LEFT|| ke==KeyCode.A){
			// Checks if player can move to the tile
			if (!this.myPlayer.checkCollision(Player.LEFT, this.board.MapNum)) {
				this.myPlayer.moveX(-myPlayer.speed);}this.myPlayer.loadImage(Player.IMG_LEFT);}
		
		if(ke==KeyCode.DOWN || ke==KeyCode.S){
			// Checks if player can move to the tile
			if (!this.myPlayer.checkCollision(Player.DOWN, this.board.MapNum)) {
				this.myPlayer.moveY(myPlayer.speed);}this.myPlayer.loadImage(Player.IMG_FRONT);}
		
		if(ke==KeyCode.RIGHT || ke==KeyCode.D){
			// Checks if player can move to the tile
			if (!this.myPlayer.checkCollision(Player.RIGHT, this.board.MapNum)) {
				this.myPlayer.moveX(this.myPlayer.speed);}this.myPlayer.loadImage(Player.IMG_RIGHT);} 
	}	
		
		
	// GAME-OVER SCENE
	private void drawGameOver(){ 
		this.gc.setFont(Font.font("Verdana", FontWeight.BOLD, 50));
		this.gc.setFill(Color.WHITE);
		this.gc.fillText("-- GAME OVER--\nYOU WIN!", (GameStage.WINDOW_WIDTH/4), GameStage.WINDOW_HEIGHT/3);
		this.gc.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
		
		// If the player reached the destination
		if(this.myPlayer.getWon()==true) {
			this.gc.fillText("Time left: " + (GameTimer.gameTime - this.secondCounter) + "\n Obstacle Count: " + obstacleCounter + "\tToken Count: " + tokenCounter, 
					(GameStage.WINDOW_WIDTH/4), (GameStage.WINDOW_HEIGHT/2));
			
		} else {this.gc.fillText("YOU LOSE!", (GameStage.WINDOW_WIDTH/4), (GameStage.WINDOW_HEIGHT/2));}
	}
		
	// Checks if player's x and y is on the destination
	private void checkFinish() {
		// Destination: row 7-10 and column 29-31
		int y1 = 6*GameStage.TILE_SIZE , y2 = 9*GameStage.TILE_SIZE, x1 = 28*GameStage.TILE_SIZE, x2 = 33*GameStage.TILE_SIZE, X = this.myPlayer.x, Y = this.myPlayer.y ;
		if( (X<=x2 && X>=x1) && (Y<=y2 && Y>=y1)) {
			this.myPlayer.setWon();
			this.stop();
		     	this.drawGameOver();
		}
	}
	
	// Draws
	private void drawCounters() {
		this.gc.setFont(Font.font("Arial", FontWeight.BOLD,16));
		this.gc.setFill(Color.WHITE);
		this.gc.fillText("Time: " + gameCounterText + "\t\tTokens Collected: " + tokenCounter + "   \t\tObstacles Encountered: " + obstacleCounter, 20, 20);
	}
	
	// Method that updates the game count down
	private void gameCountdown(long currentTime){
		if (((currentTime - this.timeElapsed)/1000000000) >= 1){ // After 1 second elapsed
			this.secondCounter++; // Increment second counter
			this.gameCounter = GameTimer.gameTime - this.secondCounter;	// Subtract second counter to game's initial time
			if (this.gameCounter >= 70) {
				this.gameCounterText = "01:" + (this.gameCounter-60); 
			} else if (this.gameCounter >= 60) {
				this.gameCounterText = "01:0" + (this.gameCounter-60); 
			}  else if (this.gameCounter >= 10) {
				this.gameCounterText = "0:" + this.gameCounter; 
			} else {
				this.gameCounterText = "0:0" + this.gameCounter;
			} this.timeElapsed = System.nanoTime(); // updates timeElapsed to current time
		} 
	}
	
	// Adds an int to game time
	public void addtoTime(int num) {
		GameTimer.gameTime += num ;
	}
		
	// Method that checks for obstacle collisions
	private void collisionObstacles() {
		for ( int i=0 ; i<this.obstacles.size() ; i++ ){
			Obstacle o = this.obstacles.get(i);
			// if obstacle is visible and there is collision with the player
			if(o.getVisible()!= false && o.collidesWith(this.myPlayer)) {
				// if phone(immune) is not active -- obstacle takes effect
				if(!this.myPlayer.immune){
					o.takeObstacleEffect(myPlayer, this);
					this.obstacleCounter++;
					
					// If the obstacle is singko -- start singko timer
					if (o.getName() == Obstacle.SINGKO) {this.singkoCounter = this.secondCounter;}		
					
				} else{System.out.println("Obstacle Ignored!"); ;o.setVisible(false);}
		}}
	}
	
	// Method that checks for token collisions
	private void collisionTokens() {
		for ( int i=0 ; i<this.tokens.size() ; i++ ){
			Token o = this.tokens.get(i);
			// If token is visible and collides with the player
			if(o.getVisible()!= false && o.collidesWith(this.myPlayer)) {
				// Token takes effect
				o.takeTokenEffect(myPlayer, this); 
				
				// Starts timer with respective token
				if (o.getName() == Token.COFFEE) {this.coffeeCounter = this.secondCounter;}
				if (o.getName() == Token.PHONE) {this.phoneCounter = this.secondCounter;}
				if (o.getName() == Token.MAP) {this.mapCounter = this.secondCounter;}
				this.tokenCounter++;		
			}
		}
	}
	
	// Spawns tokens
	private void spawnToken(long currentTime) {
		if (((currentTime-this.deleteToken)/1000000000) >= 10){ /// checks if 10 gameCountdown have elapsed after deleting
			this.board.spawnTokens(this.gc, this.myPlayer,obstacleLoc, this.tokens);
			this.spawnToken = System.nanoTime(); // Reset spawn time
			despawnHold = false; spawnHold = true;
		} 
	}
	
	private void deleteTokens(long currentTime) {
		if (((currentTime-this.spawnToken)/1000000000) >= 20){ // checks if 20 gameCountdown have elapsed after spawning
			for(int i = 0; i < this.tokens.size() ; i++){
				Token p = this.tokens.get(i);
				p.setVisible(false);
			} this.tokens.clear();
			System.out.println("Tokens depawned.");
			this.deleteToken = System.nanoTime(); // Reset despawn time
			despawnHold = true; spawnHold = false;
		} 
	}
	
	// Keeps track of obstacle and token durations and removes the effects
	private void gameTimers(){
		// render status and effects
		if (this.myPlayer.active_singko == true) {this.gc.drawImage( new Image("singko_effect.png",GameStage.WINDOW_WIDTH, GameStage.WINDOW_HEIGHT,false,false), 0, 0);}
		if (this.myPlayer.active_map == true) {this.gc.drawImage( new Image("map_effect.png",GameStage.WINDOW_WIDTH/2, GameStage.WINDOW_HEIGHT/2,false,false), GameStage.WINDOW_WIDTH/3, GameStage.WINDOW_HEIGHT/3);}
		if (this.myPlayer.active_coffee == true) { this.gc.drawImage( new Image("coffee_effect.png",64,64,false,false), 816, 10 );}
		if (this.myPlayer.immune == true) {this.gc.drawImage( new Image("phone_effect.png",64,64,false,false), 816, 85 );}		
		
		if((this.secondCounter - this.coffeeCounter >= 10) && this.myPlayer.active_coffee == true){ // after 10-second duration
			this.myPlayer.setSpeed(Player.INITIAL_SPEED); // resets player speed
			this.myPlayer.active_coffee = false;}
		
		if ((this.secondCounter - this.phoneCounter >= 10) && this.myPlayer.immune == true) { // after 10-second duration 
			this.myPlayer.setImmune(false);}  // removes immunity to obstacles
		
		if((this.secondCounter - this.mapCounter >= 3) && this.myPlayer.active_map == true) { // after 3-second duration
			this.myPlayer.active_map = false;} // remove active map 
		
		if((this.secondCounter - this.singkoCounter >= 5) && this.myPlayer.active_singko == true) { // after 5-second duration
			this.myPlayer.active_singko = false ;}// clear singko effect	
	}
}