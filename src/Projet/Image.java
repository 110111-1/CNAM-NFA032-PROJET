package Projet;

import java.io.ObjectInputStream.GetField;
@SuppressWarnings("unused")

public class Image {
	
	private Pixel[][] tabPixel;
	private int hauteur;
	private int largeur;

	public Image(String path){	
		this.largeur=ImageUtil.readImage(path).length; 
		this.hauteur=ImageUtil.readImage(path)[0].length;
		this.tabPixel = new Pixel[largeur][hauteur];
		int [][][] tab = ImageUtil.readImage(path);
		for(int col=0;col<=largeur-1;col++){
			for (int ligne=0;ligne<=hauteur-1;ligne++){
				tabPixel[col][ligne] = new Pixel(tab[col][ligne][0],tab[col][ligne][1],tab[col][ligne][2],tab[col][ligne][3]);
			}
		}
	}
	public Image(){

	}

	public int getHauteur() {
		return hauteur;
	}

	public int getLargeur() {
		return largeur;
	}

	public Pixel[][] getTabPixel() {
		return tabPixel;
	}

	protected int[][][] export() {
		int[][][] tab = new int[tabPixel.length][tabPixel[0].length][4];
		for(int i=0;i<=tabPixel.length-1;i++) {
			for(int j=0;j<=tabPixel[0].length-1;j++) {
				tab[i][j][0]=tabPixel[i][j].getRouge();
				tab[i][j][1]=tabPixel[i][j].getVert();
				tab[i][j][2]=tabPixel[i][j].getBleu();
				tab[i][j][3]=tabPixel[i][j].getAlpha();
			}
		}
		return tab;
	}

	public void sauvegarde(String nomNouveauFichier, String type){
		ImageUtil.writeImage(export(),"png","images/"+nomNouveauFichier+"."+type);
	}

	public void afficheImage(){
		ImageUtil.afficheImage((export()));
	}

	public String toString() {
		return "Nombre de pixel de l'image : "+(getHauteur()*getLargeur())+"\nDimensions : hauteur "+getHauteur()+" pixels, Largeur : "+getLargeur()+" pixels" ;
	}

	public Image imageNB(){
		Image imageNB = new Image();
		imageNB.hauteur = getHauteur();
		imageNB.largeur = getLargeur();
		imageNB.tabPixel = new Pixel[largeur][hauteur];
		for(int col=0;col<=largeur-1;col++){
			for (int ligne=0;ligne<=hauteur-1;ligne++){
				int valNB=(tabPixel[col][ligne].getRouge()+tabPixel[col][ligne].getVert()+tabPixel[col][ligne].getBleu())/3;
				imageNB.tabPixel[col][ligne]= new Pixel(valNB, valNB, valNB, tabPixel[col][ligne].getAlpha());
			}
		}
		return imageNB;
	}

	public void toNB(){
		for (int col = 0; col <= largeur-1; col++) {
			for (int ligne = 0; ligne <= hauteur-1; ligne++) {
				int valNB=(tabPixel[col][ligne].getRouge()+tabPixel[col][ligne].getVert()+tabPixel[col][ligne].getBleu())/3;
				tabPixel[col][ligne].setPixel(valNB, valNB, valNB, tabPixel[col][ligne].getAlpha());
			}
		}
	}

	public void modifierComposante(char composante,int variation){ 
		switch (composante){
		case 'R' :
			for (int col = 0; col <= largeur-1; col++) {
				for (int ligne = 0; ligne <= hauteur-1; ligne++) {
					int var = tabPixel[col][ligne].getRouge()+((tabPixel[col][ligne].getRouge()*variation)/100);
					if(var<0){
						tabPixel[col][ligne].setRouge(0);	
					}
					else if(var>255){
						tabPixel[col][ligne].setRouge(255);
					}
					else{
						tabPixel[col][ligne].setRouge(var);
					}
				}
			}
			break;
		case 'V' :
			for (int col = 0; col <= largeur-1; col++) {
				for (int ligne = 0; ligne <= hauteur-1; ligne++) {
					int var = tabPixel[col][ligne].getVert()+((tabPixel[col][ligne].getVert()*variation)/100);
					if(var<0){
						tabPixel[col][ligne].setVert(0);	
					}
					else if(var>255){
						tabPixel[col][ligne].setVert(255);
					}
					else{
						tabPixel[col][ligne].setVert(var);
					}
				}
			}
			break;
		case 'B':
			for (int col = 0; col <= largeur-1; col++) {
				for (int ligne = 0; ligne <= hauteur-1; ligne++) {
					int var = tabPixel[col][ligne].getBleu()+((tabPixel[col][ligne].getBleu()*variation)/100);
					if(var<0){
						tabPixel[col][ligne].setBleu(0);	
					}
					else if(var>255){
						tabPixel[col][ligne].setBleu(255);
					}
					else{
						tabPixel[col][ligne].setBleu(var);
					}
				}
			}
			break;
		default : 
			System.out.println("Erreur dans le choix de composante");
		}
	}

	public void elargir(){
		Pixel[][] old = new Pixel[getLargeur()][getHauteur()];
		old = tabPixel;
		largeur=getLargeur()*2;
		tabPixel = new Pixel[getLargeur()][getHauteur()];
		for(int col=0;col<=tabPixel.length-1;col++) {
			for(int ligne=0;ligne<=tabPixel[0].length-1;ligne++) {
				if(col%2==0){
					tabPixel[col][ligne]=new Pixel();
					tabPixel[col][ligne]=old[col/2][ligne];
				}
				else{
					tabPixel[col][ligne]=tabPixel[col-1][ligne];
				}
			}
		}
	}
	
	public void incrusterCoinGaucheVoid(Image avPlan){
		for (int col = 0; col < avPlan.getTabPixel().length; col++) {
			for (int ligne = 0; ligne < avPlan.getTabPixel()[0].length; ligne++) {
				if(avPlan.tabPixel[col][ligne].getAlpha()!=0){
					tabPixel[col][ligne]=avPlan.tabPixel[col][ligne];
				}
			}
		}
	}
	
	public Image copyImage(){
		Image temp = new Image();
		temp.hauteur = getHauteur();
		temp.largeur = getLargeur();
		temp.tabPixel = new Pixel[getLargeur()][getHauteur()];

		for(int col=0;col<=temp.largeur-1;col++){
			for (int ligne=0;ligne<=temp.hauteur-1;ligne++){
				temp.tabPixel[col][ligne]= new Pixel();
				temp.tabPixel[col][ligne]=tabPixel[col][ligne];
			}
		}
		return temp;
	}
	
	public Image incrusterCoinGauche(Image avPlan){
		Image imageCG = new Image();
		imageCG = copyImage();
		for (int col = 0; col < avPlan.getTabPixel().length; col++) {
			for (int ligne = 0; ligne < avPlan.getTabPixel()[0].length; ligne++) {
				if(avPlan.tabPixel[col][ligne].getAlpha()!=0){
					imageCG.tabPixel[col][ligne]=avPlan.tabPixel[col][ligne];
				}
			}
		}
		return imageCG;
	}

	public Image incrusterCarre(){
		Image carre = new Image();
		carre.hauteur = 100;
		carre.largeur = 100;
		carre.tabPixel = new Pixel[carre.getLargeur()][carre.getHauteur()];
		for(int col=0;col<=carre.tabPixel.length-1;col++){
			for (int ligne=0;ligne<=carre.tabPixel[0].length-1;ligne++){
				carre.tabPixel[col][ligne]= new Pixel();
				carre.tabPixel[col][ligne].setPixel(255, 238, 0, 255);
			}
		}
		Image temp = new Image();
		temp = copyImage();
		for(int col = 100;col<=200;col++){
			for(int ligne = 50;ligne<=150;ligne++){
				temp.tabPixel[col][ligne]=carre.tabPixel[col-col][ligne-ligne];
			}
		}
		return temp;
	}

	public Image incruster(Image avPlan, int x, int y){
		///
		Image temp = new Image();
		///
		temp = copyImage();
		for(int col = 0 ; col < avPlan.largeur && col<temp.largeur-x; col++){
			if(col+x<0){
				col = col++;
			}
			else{
				for(int ligne = 0 ; ligne < avPlan.hauteur && ligne < temp.hauteur-y ; ligne++){
					if(ligne+y<0){
						ligne = ligne++;
					}
					else{
						if(avPlan.tabPixel[col][ligne].getAlpha()!=0) {	
							temp.tabPixel[col+x][ligne+y]=avPlan.tabPixel[col][ligne];
						}
					}
				}
			}
		}
		return temp;
	}

	public Image copie(){
		Image temp = new Image();
		temp.hauteur = getHauteur();
		temp.largeur = getLargeur();
		temp.tabPixel = new Pixel[getLargeur()][getHauteur()];

		for(int col=0;col<=temp.largeur-1;col++){
			for (int ligne=0;ligne<=temp.hauteur-1;ligne++){
				temp.tabPixel[col][ligne]= new Pixel();
				temp.tabPixel[col][ligne]=tabPixel[col][ligne];
			}
		}
		return temp;
	}

	public Image incruster(ImageAIncruster avPlan){
		Image temp = new Image();
		temp = copie();
		for(int col = 0 ; col < avPlan.getLargeur() && col<temp.largeur-avPlan.getX(); col++){
			if(col+avPlan.getX()<0){
				col = col++;
			}
			else{
				for(int ligne = 0 ; ligne < avPlan.getHauteur() && ligne < temp.hauteur-avPlan.getY() ; ligne++){
					if(ligne+avPlan.getY()<0){
						ligne = ligne++;
					}
					else{
						if(avPlan.getTabPixel()[col][ligne].getAlpha()!=0) {	
							temp.tabPixel[col+avPlan.getX()][ligne+avPlan.getY()]=avPlan.getTabPixel()[col][ligne];
						}
					}
				}
			}
		}
		return temp;
	}
}
