package Projet;

public class Avion_Main {

	public static void main(String[] args) {
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
				avion.setPosition();
			}
		}
	}
}
