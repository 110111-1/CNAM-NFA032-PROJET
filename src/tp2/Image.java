package tp2;


public class Image {
	//TP1
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

	private int[][][] export() {
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
	//TP2

	public Image imageNB(){// TP 2 Question 1
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
	public void toNB(){//TP2 Question 2
		for (int col = 0; col <= largeur-1; col++) {
			for (int ligne = 0; ligne <= hauteur-1; ligne++) {
				int valNB=(tabPixel[col][ligne].getRouge()+tabPixel[col][ligne].getVert()+tabPixel[col][ligne].getBleu())/3;
				tabPixel[col][ligne].setPixel(valNB, valNB, valNB, tabPixel[col][ligne].getAlpha());
			}
		}
	}
	public void modifierComposante(char composante,int variation){ //TP2 Question 3
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

public static void main(String[] args) {
	Image perruche = new Image("images/perruche.png");
	//Question 1
	perruche.imageNB().afficheImage();
	perruche.afficheImage();

	//Question 2 
	perruche.toNB();
	perruche.afficheImage();

	//Question 3
	perruche.afficheImage();
	perruche.modifierComposante('R',25);
	perruche.afficheImage();

	//Question 4
	perruche.elargir();
	perruche.afficheImage();
}
}