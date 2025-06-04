package application;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

public abstract class Sprite {
	protected
		Image img;
		int x, y;
		boolean visible;
		double width, height;
		Rectangle bounds = new Rectangle();

	// SPRITE CONSTRUCTOR
	public Sprite(int xPos, int yPos){
		this.x = xPos;
		this.y = yPos;
		this.visible = true;
	}

	//method to set the object's image
	protected void loadImage(Image img){
		try{
			this.img = img;
	        this.setSize();
		} catch(Exception e){}
	}

	//method that renders player on screen
	void render(GraphicsContext gc, int x, int y){
		gc.drawImage(this.img,x,y);
    }
	
	
	//method to set the object's width and height properties
	private void setSize(){
		this.width = this.img.getWidth();
	    this.height = this.img.getHeight();
	}

	//method that will check for collision of two sprites
	public boolean collidesWith(Sprite rect2)	{
		Rectangle2D rectangle1 = new Rectangle2D((this.x)-6, (this.y)-6, GameStage.TILE_SIZE-(GameStage.TILE_SIZE/3), GameStage.TILE_SIZE-(GameStage.TILE_SIZE/3));
		Rectangle2D rectangle2 = rect2.getBounds();
		return rectangle1.intersects(rectangle2);
	}
	
	//method that will return the bounds of an image
	private Rectangle2D getBounds(){
		return new Rectangle2D((this.x)-4, (this.y)-4, this.width, this.height);
	}

	//method to return the image
	Image getImage(){
		return this.img;
	}
	//getters
	public int getX() {
    	return this.x;
	}

	public int getY() {
    	return this.y;
	}

	public boolean getVisible(){
		return visible;
	}

	public void setVisible(boolean value){
		this.visible = value;
	}

	public boolean isVisible(){
		if(visible) return true;
		return false;
	}

	public boolean collidesWith(double x, double y) {
	    Rectangle2D rectangle1 = new Rectangle2D(this.x, this.y, this.width, this.height);
	    return rectangle1.contains(x, y);
	}
}