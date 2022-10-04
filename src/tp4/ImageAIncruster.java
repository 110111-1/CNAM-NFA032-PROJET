package tp4;

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
	
	public static void main(String[] args) { 
		Image prairie = new Image("images/prairie.png");
		ImageAIncruster renard = new ImageAIncruster("images/renard.png", 720, 360);
		Afficheur fond = new Afficheur(prairie.export());
			fond.update(prairie.incruster(renard).export());
	}
}
