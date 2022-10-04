package tp1;

public class Image {
	//TP1
	private Pixel[][] tabPixel;
	private int hauteur;
	private int largeur;

	public Image(String path){	
		this.largeur=ImageUtil.readImage(path).length; //nb lignes
		this.hauteur=ImageUtil.readImage(path)[0].length;//nb colonnes
		this.tabPixel = new Pixel[largeur][hauteur];
		int [][][] tab = ImageUtil.readImage(path);
		for(int col=0;col<=largeur-1;col++){
			for (int ligne=0;ligne<=hauteur-1;ligne++){
				tabPixel[col][ligne] = new Pixel(tab[col][ligne][0],tab[col][ligne][1],tab[col][ligne][2],tab[col][ligne][3]);
			}
		}
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

	public static void main(String[] args) {

		Image perruche = new Image("images/perruche.png");
		System.out.println(perruche);
		perruche.afficheImage();
		perruche.sauvegarde("Perruche_Copie", "png");
		
	}
}
