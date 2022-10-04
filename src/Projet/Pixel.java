package Projet;
public class Pixel {
	private int rouge;
	private int vert;
	private int bleu;
	private int alpha;

	public Pixel(){
		
	}

	public Pixel(int rouge, int vert, int bleu, int alpha){
		this.rouge = verifValue(rouge);
		this.vert = verifValue(vert);
		this.bleu = verifValue(bleu);
		this.alpha = verifValue(alpha);
	}

	public void setPixel(int rouge, int vert, int bleu, int alpha){
		setRouge(rouge);
		setVert(vert);
		setBleu(bleu);
		setAlpha(alpha);
	}

	public int verifValue(int valeur){
		if(valeur<0||valeur>255){
			throw new IllegalArgumentException ();
		}
		return valeur;
	}

	public int getRouge() {
		return rouge;
	}

	public void setRouge(int rouge) {
		this.rouge = verifValue(rouge);
	}

	public int getVert() {
		return vert;
	}

	public void setVert(int vert) {	
		this.vert = verifValue(vert);

	}

	public int getBleu() {
		return bleu;
	}

	public void setBleu(int bleu) {
		this.bleu = verifValue(bleu);
	}

	public int getAlpha() {
		return alpha;
	}

	public void setAlpha(int alpha) {
		this.alpha = verifValue(alpha);
	}

	public String toString(){
		return "("+getRouge()+","+getVert()+","+getBleu()+","+getAlpha() +")";
	}

	public static void main(String[] args) {
		try{	
			Pixel p1 = new Pixel(150, 140, 200, 250);
			Pixel p2 = new Pixel(240,45,56,250);
			System.out.println(p1);
			System.out.println(p2);
			p1.setPixel(50, 50, 50, 50);
			p2.setPixel(100, 100, 100, 100);
			System.out.println(p1);
			System.out.println(p2);			
		}
		catch(IllegalArgumentException e){
			System.err.println("Erreur dans les paramètres du pixel");
		}
	}
}