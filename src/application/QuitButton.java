package application;

public class QuitButton extends Buttons {

    public QuitButton(int xPos, int yPos, String img) {
        super(xPos, yPos, img);
    }

    @Override
	public void onClick(){
		//quit game
		System.out.println("Qutting the game");
		System.exit(0);
	}

    public boolean collidesWith(double mouseX, double mouseY) {
		// TODO Auto-generated method stub
		return super.collidesWith(mouseX, mouseY);
	}

}
