package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class TileBoard{
	public
		static Tile overlays[];
		static Tile tiles[];
		int MapNum[][];
		static int n;
	
	
	// TILEBOARD CONSTRUCTOR
	public TileBoard(){
		tiles = new Tile[50]; // Array of 50 tiles
		overlays = new Tile[50]; // Array of 50 overlay tiles
		this.MapNum = new int[GameStage.WORLD_ROWS][GameStage.WORLD_ROWS]; // Map array
		getTileImages(); // Get images
	}
	
	// Method that gets tile images
	private void getTileImages(){
		TileBoard.tiles[0] = new Tile(new Image("0.png",GameStage.TILE_SIZE,GameStage.TILE_SIZE,false,false), true);	
		TileBoard.tiles[1] = new Tile(new Image("1.png",GameStage.TILE_SIZE,GameStage.TILE_SIZE,false,false), true);
		TileBoard.tiles[2] = new Tile(new Image("2.png",GameStage.TILE_SIZE,GameStage.TILE_SIZE,false,false), true);
		TileBoard.tiles[3] = new Tile(new Image("3.png",GameStage.TILE_SIZE,GameStage.TILE_SIZE,false,false), true);
		TileBoard.tiles[4] = new Tile(new Image("4.png",GameStage.TILE_SIZE,GameStage.TILE_SIZE,false,false), true);
		TileBoard.tiles[5] = new Tile(new Image("5.png",GameStage.TILE_SIZE,GameStage.TILE_SIZE,false,false), true);
		TileBoard.tiles[6] = new Tile(new Image("6.png",GameStage.TILE_SIZE,GameStage.TILE_SIZE,false,false), true);
		TileBoard.tiles[7] = new Tile(new Image("7.png",GameStage.TILE_SIZE,GameStage.TILE_SIZE,false,false), true);
		TileBoard.tiles[8] = new Tile(new Image("8.png",GameStage.TILE_SIZE,GameStage.TILE_SIZE,false,false), true);
		TileBoard.tiles[9] = new Tile(new Image("9.png",GameStage.TILE_SIZE,GameStage.TILE_SIZE,false,false), true);
		TileBoard.tiles[10] = new Tile(new Image("10.png",GameStage.TILE_SIZE,GameStage.TILE_SIZE,false,false), true);
		TileBoard.tiles[11] = new Tile(new Image("11.png",GameStage.TILE_SIZE,GameStage.TILE_SIZE,false,false), true);
		TileBoard.tiles[12] = new Tile(new Image("12.png",GameStage.TILE_SIZE,GameStage.TILE_SIZE,false,false), true);
		TileBoard.tiles[13] = new Tile(new Image("13.png",GameStage.TILE_SIZE,GameStage.TILE_SIZE,false,false), true);
		TileBoard.tiles[14] = new Tile(new Image("14.png",GameStage.TILE_SIZE,GameStage.TILE_SIZE,false,false), true);
		TileBoard.tiles[15] = new Tile(new Image("15.png",GameStage.TILE_SIZE,GameStage.TILE_SIZE,false,false), true);
		TileBoard.tiles[16] = new Tile(new Image("16.png",GameStage.TILE_SIZE,GameStage.TILE_SIZE,false,false), true);
		TileBoard.tiles[17] = new Tile(new Image("17.png",GameStage.TILE_SIZE,GameStage.TILE_SIZE,false,false), false); // Path tile
		TileBoard.tiles[18] = new Tile(new Image("18.png",GameStage.TILE_SIZE,GameStage.TILE_SIZE,false,false), true);
		TileBoard.tiles[19] = new Tile(new Image("19.png",GameStage.TILE_SIZE,GameStage.TILE_SIZE,false,false), true);
		TileBoard.tiles[20] = new Tile(new Image("20.png",GameStage.TILE_SIZE,GameStage.TILE_SIZE,false,false), true);
		TileBoard.tiles[21] = new Tile(new Image("21.png",GameStage.TILE_SIZE,GameStage.TILE_SIZE,false,false), true);
		TileBoard.tiles[22] = new Tile(new Image("22.png",GameStage.TILE_SIZE,GameStage.TILE_SIZE,false,false), true);
		TileBoard.tiles[23] = new Tile(new Image("23.png",GameStage.TILE_SIZE,GameStage.TILE_SIZE,false,false), true);
		TileBoard.tiles[24] = new Tile(new Image("24.png",GameStage.TILE_SIZE,GameStage.TILE_SIZE,false,false), true);
		TileBoard.tiles[25] = new Tile(new Image("25.png",GameStage.TILE_SIZE,GameStage.TILE_SIZE,false,false), true);
		TileBoard.tiles[26] = new Tile(new Image("26.png",GameStage.TILE_SIZE,GameStage.TILE_SIZE,false,false), true);
		TileBoard.tiles[27] = new Tile(new Image("27.png",GameStage.TILE_SIZE,GameStage.TILE_SIZE,false,false), true);
		TileBoard.tiles[28] = new Tile(new Image("28.png",GameStage.TILE_SIZE,GameStage.TILE_SIZE,false,false), true);
		TileBoard.tiles[29] = new Tile(new Image("29.png",GameStage.TILE_SIZE,GameStage.TILE_SIZE,false,false), true);
		
		n = 7 ;
		TileBoard.overlays[0] = new Tile(new Image("stapler.png",GameStage.TILE_SIZE*5,GameStage.TILE_SIZE*4,false,false), false, 10,22);
		TileBoard.overlays[1] = new Tile(new Image("pond2_3x4.png",GameStage.TILE_SIZE*5,GameStage.TILE_SIZE*3,false,false), false, 20,20);
		TileBoard.overlays[2] = new Tile(new Image("pond1_3x5.png",GameStage.TILE_SIZE*5,GameStage.TILE_SIZE*3,false,false), false, 10,8);
		TileBoard.overlays[3] = new Tile(new Image("sign.png",GameStage.TILE_SIZE*6,GameStage.TILE_SIZE*4,false,false), false, 26,32);
		TileBoard.overlays[4] = new Tile(new Image("oble.png",GameStage.TILE_SIZE*3,GameStage.TILE_SIZE*4,false,false), false, 30,11);
		TileBoard.overlays[5] = new Tile(new Image("carillon.png",GameStage.TILE_SIZE*2,GameStage.TILE_SIZE*4,false,false), false, 34,28);
		TileBoard.overlays[6] = new Tile(new Image("cas.png",GameStage.TILE_SIZE*7,GameStage.TILE_SIZE*5,false,false), false, 28,6);
	}
	
	// Method loads map file
	public void loadMapFile() throws FileNotFoundException{
		Scanner sc = new Scanner(new File("map.csv"));
		while(sc.hasNextLine()) {
			for (int i = 0 ; i < this.MapNum.length ; i++) {
				String[] line = sc.nextLine().trim().split(","); // Split lines per space
				for(int j =0 ; j < line.length; j++ ) {
					this.MapNum[i][j]= Integer.parseInt(line[j]); // Append int to array of tile info
				}
			}
		}
	}
	
	// Method that renders tiles/background
	public void drawMap(GraphicsContext gc, Player player) {
			// background tiles
			int worldCol=0, worldRow=0;
			while (worldCol<GameStage.WORLD_ROWS && worldRow < GameStage.WORLD_ROWS){
				int tileNum = this.MapNum[worldRow][worldCol];
				int worldX = worldCol*GameStage.TILE_SIZE; 
				int worldY = worldRow*GameStage.TILE_SIZE;
				int screenX = worldX - player.x + player.screenX;
				int screenY = worldY - player.y + player.screenY;
				gc.drawImage(tiles[tileNum].image, screenX, screenY, GameStage.TILE_SIZE,GameStage.TILE_SIZE); 
				worldCol++;
				 if (worldCol == GameStage.WORLD_ROWS){
					 worldCol = 0; worldRow++;
				 }}} 
	
	public void drawOverlays(GraphicsContext gc, Player player) {
		for (int i=0 ; i<n ; i++ ){
			int screenX = TileBoard.overlays[i].x - player.x + player.screenX;
			int screenY = TileBoard.overlays[i].y - player.y + player.screenY;
			gc.drawImage(TileBoard.overlays[i].image,screenX, screenY,TileBoard.overlays[i].image.getWidth(),TileBoard.overlays[i].image.getHeight());
		}	
	}

	
	public int[][] spawnObstacles( ArrayList<Obstacle> obs) {
		int obstacleLoc[][] = new int[GameStage.WORLD_ROWS][GameStage.WORLD_ROWS];
		Random r = new Random(); int i = 0, rand = r.nextInt(Obstacle.MIN_OBSTACLES, Obstacle.MAX_OBSTACLES+1);
		Obstacle o;
		System.out.println("--- "+ rand + " obstacles on the map.");
		// Randomize number of obstacles from 5-10
		while(i < rand){
			// randomize 2 -- numbers x and y													// Randomize obstacle type
			int row = r.nextInt(GameStage.WORLD_ROWS), col = r.nextInt(GameStage.WORLD_ROWS), x = r.nextInt(3);
			// if the tile in map [x][y] is a walkable tile
			if( tiles[this.MapNum[row][col]].collision== false){
				obstacleLoc[row][col] = 1; // to avoid tokens spawning at the same spot of obstacles
				// Randomize type of obstacles
				if(x == 0){o = new Obstacle(Obstacle.CAT,col*GameStage.TILE_SIZE,row*GameStage.TILE_SIZE);obs.add(o);}
				else if(x == 1){o = new Obstacle(Obstacle.FRIEND,col*GameStage.TILE_SIZE,row*GameStage.TILE_SIZE); obs.add(o);}
				else if(x == 2){o = new Obstacle(Obstacle.SINGKO,col*GameStage.TILE_SIZE,row*GameStage.TILE_SIZE); obs.add(o);
				} i++;
			}
		} return obstacleLoc;
	}
	
	public void spawnTokens(GraphicsContext gc, Player myPlayer, int[][] ostacleLoc, ArrayList<Token> tokens) {
		Random r = new Random(); int i = 0;
		Token t;
		System.out.println("--- 5 tokens spawned.");
		// spawn 5 tokens
		while(i < 5){
			// randomize 2 numbers-- x and y												// Randomize token type
			int row = r.nextInt(GameStage.WORLD_ROWS), col = r.nextInt(GameStage.WORLD_ROWS), x = r.nextInt(4);
			// if the tile in map [x][y] is the walkable the tile
			if(tiles[this.MapNum[row][col]].collision== false && ostacleLoc[row][col]!= 1){
				if(x == 0){t = new Token(Token.BLUEBOOK,col*GameStage.TILE_SIZE,row*GameStage.TILE_SIZE);tokens.add(t);}
				else if(x == 1){t = new Token(Token.COFFEE,col*GameStage.TILE_SIZE,row*GameStage.TILE_SIZE);tokens.add(t);}
				else if(x == 2){t = new Token(Token.PHONE,col*GameStage.TILE_SIZE,row*GameStage.TILE_SIZE);tokens.add(t);}
				else if(x == 3){t = new Token(Token.MAP,col*GameStage.TILE_SIZE,row*GameStage.TILE_SIZE);tokens.add(t);
				} i++; 
			}
		}
	} 
	
	public void drawObstacles(GraphicsContext gc, Player player, ArrayList<Obstacle> obs) {
		for ( int i=0 ; i<obs.size() ; i++ ){
			Obstacle o = obs.get(i);
			if(o.getVisible()!= false) {
				int screenX = o.x - player.x + player.screenX;
				int screenY = o.y - player.y + player.screenY;
				gc.drawImage(o.getImage(),screenX, screenY,Obstacle.SIZE, Obstacle.SIZE);				
			}
		}
	}
	
	public void drawTokens(GraphicsContext gc, Player player, ArrayList<Token> tok) {
		if ( tok.size() == 0) {return;} // If the token array is empty
		for ( int i=0 ; i<tok.size() ; i++ ){
			Token o = tok.get(i);
			if(o.getVisible()!= false) {
				int screenX = o.x - player.x + player.screenX;
				int screenY = o.y - player.y + player.screenY;
				gc.drawImage(o.getImage(),screenX, screenY,Token.SIZE, Token.SIZE);
			}
		}
	}
} 