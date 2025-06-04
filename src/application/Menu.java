package application;

import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Menu {

	private
		GraphicsContext gc;
		Scene menu;
		PlayButton playButton;
		QuitButton quitButton;
		GameStage theGameStage;

	public Menu(GraphicsContext gc, Scene menu, GameStage theGameStage){
		this.gc = gc;
		this.menu = menu;
		this.theGameStage = theGameStage;

		playButton = new PlayButton(630, 350, "play.png", theGameStage);
		quitButton = new QuitButton(630, 450, "quit.png");

	}
	public void render(){
		gc.clearRect(0, 0, GameStage.WINDOW_WIDTH, GameStage.WINDOW_HEIGHT);

		Image menuBG = new Image("temp-menu.png");
		gc.drawImage(menuBG, 0, 0, GameStage.WINDOW_WIDTH, GameStage.WINDOW_HEIGHT);

		playButton.draw(gc);
		quitButton.draw(gc);

	}

	public void setupMouseClickListener() {
        menu.setOnMouseClicked(event -> handleMouseClick(event.getX(), event.getY()));
    }

	public void handleMouseClick(double mouseX, double mouseY){
		if(playButton.collidesWith(mouseX, mouseY)){
			playButton.onClick();
		}else if(quitButton.collidesWith(mouseX, mouseY)){
			quitButton.onClick();
		}
	}

}
