package tp4;

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

	public static void main(String[] args) {  
		//TP4 Question 5 et 6
		Image ciel = new Image("images/ciel.jpg");
		Afficheur fond = new Afficheur(ciel.export());
		ImageEnMouvement avion = new ImageEnMouvement("images/avion.png",-350,20,50,0);
		boolean animation = true;
		while(animation){
			if(avion.getX()>ciel.getLargeur()||(avion.getY()>ciel.getHauteur())
					||avion.getX()+avion.getLargeur()<0||avion.getY()+avion.getHauteur()<0){
				animation = false;
			}
			else{
				fond.update(ciel.incruster(avion).export());
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				avion.setX(avion.getX()+avion.getVitesseX());
				avion.setY(avion.getY()+avion.getVitesseY());

			}
		}
	}
}



/*
		if(avion.getVitesseX()>=0){
			for(int i=0-avion.getLargeur();i<ciel.getLargeur();i=i+avion.getVitesseX()){
				try {
					avion.setX(i);
					avion.setY(20);
					fond.update(ciel.incruster(avion).export());
					Thread.sleep(150);
				}
				catch (InterruptedException e) {
					e.printStackTrace();
				}
			}	
			System.out.println("fin");
		}
		else{
			for(int i=ciel.getLargeur()+avion.getLargeur();i>0-avion.getLargeur();i=i+avion.getVitesseX()){
				try {
					avion.setX(i);
					fond.update(ciel.incruster(avion).export());
					Thread.sleep(150);
				}
				catch (InterruptedException e) {
					e.printStackTrace();
				}
			}	
			System.out.println("fin");
		}
 */




