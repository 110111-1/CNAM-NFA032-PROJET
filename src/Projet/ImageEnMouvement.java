package Projet;

public class ImageEnMouvement extends ImageAIncruster {
	int vitesseX ;
	int vitesseY ;

	public ImageEnMouvement(String path, int x, int y, int vitesseX,int vitesseY) {
		super(path, x, y);
		this.vitesseX=vitesseX;
		this.vitesseY = vitesseY;
	}

	public int getVitesseX() {
		return vitesseX;
	}

	public void setVitesseX(int vitesseX) {
		this.vitesseX = vitesseX;
	}

	public int getVitesseY() {
		return vitesseY;
	}

	public void setVitesseY(int vitesseY) {
		this.vitesseY = vitesseY;
	}

	public void setPosition(){
		setX (getX()+getVitesseX());
		setY (getY()+getVitesseY());
	}
	
}