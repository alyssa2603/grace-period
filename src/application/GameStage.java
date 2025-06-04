package application;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class GameStage {

	public
	// Screen Attributes
		static final int TILE_SIZE = 68; // Tile size (pixels)
		static final int TILE_COLUMN = 14; 
		static final int TILE_ROW = 10;
		static final int WINDOW_HEIGHT = GameStage.TILE_SIZE*GameStage.TILE_ROW;
		static final int WINDOW_WIDTH = GameStage.TILE_SIZE*GameStage.TILE_COLUMN;

	// Map Attributes
		static final int WORLD_ROWS = 48; // 48x48 map
		static final int WORLD_WIDTH = GameStage.WORLD_ROWS*GameStage.TILE_SIZE; // Map width (pixels)

	private
		Scene scene; Stage stage;
		// Group layout
		Group root; Canvas canvas;
		GraphicsContext graphicsContext;
		GameTimer gametimer;
		TileBoard tileboard;
		Menu menu;

	public enum GameState{
		MENU, PLAYING, GAMEOVER;
	}

	private GameState gameState;

	//CONSTRUCTOR
	public GameStage() {
		this.root = new Group();
		this.scene = new Scene(root, GameStage.WINDOW_WIDTH,GameStage.WINDOW_HEIGHT,Color.LIGHTGREEN);
		this.canvas = new Canvas(GameStage.WINDOW_WIDTH,GameStage.WINDOW_HEIGHT);
		this.graphicsContext = canvas.getGraphicsContext2D();
		this.gametimer = new GameTimer(this.graphicsContext,this.scene);

		this.menu = new Menu(this.graphicsContext, this.scene, this);
		this.gameState = getGameState();
	}

	public void setStage(Stage stage) {
		this.stage = stage;
		if (!root.getChildren().contains(canvas)){
			this.root.getChildren().add(canvas); // Set stage elements
		}

		this.stage.setTitle("Grace Period!"); // Game Title
		this.stage.setScene(this.scene);
		this.stage.setResizable(false); // Make the screen not resizable

		switch (this.gameState) {
			case MENU:
				showMenu();
				break;
			case PLAYING:
				startGame();
				break;
			default:
				break;
		}

		this.stage.show();	// Show stage
	}

	private void showMenu(){
		menu.render();
		menu.setupMouseClickListener();
	}

	private void startGame(){
		gametimer.start();
    	scene.setOnMouseClicked(null);
	}

	public Scene getScene(){
		return this.scene;
	}

	public GameState getGameState() {
        return gameState;
    }

	public void setGameState(GameState gameState) {
        this.gameState = gameState;

    }

}


