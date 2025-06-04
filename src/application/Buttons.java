package application;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class Buttons extends Sprite {

//	private Image buttonImage;

	public Buttons(int xPos, int yPos, String img){
		super(xPos,yPos);
		loadImage(new Image(img));
	}

	public abstract void onClick();

	public void draw(GraphicsContext gc){
		super.render(gc, x, y);
	}

}