package Projet;

public class ImageAIncruster extends Image {
	int x;//Largeur
	int y;//Hauteur

	public ImageAIncruster(String path,int x, int y) {
		super(path);
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

}