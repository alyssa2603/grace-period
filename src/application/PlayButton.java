package application;

public class PlayButton extends Buttons {

	private GameStage theGameStage;

	public PlayButton(int xPos, int yPos, String img, GameStage theGameStage){
		super(xPos, yPos, img);
		this.theGameStage = theGameStage;
	}

	@Override
	public void onClick(){
		//start game
		System.out.println("Starting the game");
		theGameStage.setGameState(GameStage.GameState.PLAYING);
		theGameStage.getGameState();
		theGameStage.setStage(theGameStage.stage);
	}

	public boolean collidesWith(double mouseX, double mouseY) {
		// TODO Auto-generated method stub
		return super.collidesWith(mouseX, mouseY);
	}

}
