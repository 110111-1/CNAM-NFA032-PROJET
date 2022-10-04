package Projet;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

import tp3.Afficheur;

/**
 * @author François Barthélemy
 * Classe permettant de lire des fichiers contenant des images.
 */
public class ImageUtil {
	/** Lit une image au format JPEG ou au format PNG.
	 * 
	 * @param path chemin d'accès au fichier contenant l'image
	 * @return renvoie l'image sous forme d'un tableau à trois dimensions: 
	 *   colonne, ligne et composante de couleur ou d'opacité.
	 * @throws IOException 
	 */
	public static int[][][] readImage(String path){
		int[][][] res;
		int[] tab;
		//path = new ImageReader().getClass().getResource("/" + path).getFile();
		try {
			BufferedImage bufi = ImageIO.read(new File(path));
			int width = bufi.getWidth();
			int height = bufi.getHeight();
			tab = bufi.getRGB(0,0,width,height,null,0,width);
			res = new int[width][height][];
			for (int i=0; i<tab.length; i++){
				res[i%width][i/width] = explodePixel(tab[i]);
			}
			return res;
		}catch (IOException ioe) {
			throw new IllegalArgumentException(ioe.getMessage());
		}
	}

	
	public static Afficheur afficheImage(int[][][] tab){
	  	return new Afficheur(tab);
	  	
	}

	public static void writeImage(int[][][] tab, String typeFichier, String nomFich){
		try {
			BufferedImage bufi = new BufferedImage(tab.length,tab[0].length, BufferedImage.TYPE_INT_ARGB);
			int[] tabbis = packedFromNatural(tab);
			bufi.setRGB(0,0,tab.length,tab[0].length,tabbis,0,tab.length);
			ImageIO.write(bufi, typeFichier, new File(nomFich));
		}catch(IOException ioe) {
			throw new IllegalArgumentException(ioe.getMessage());
		}
	}

	public static void main(String[] args){
		int[][][] tab =  {{{255,0,0,255},{255,0,0,255},{255,0,0,255},{255,0,0,255}},
				{{255,0,0,255},{255,0,0,255},{255,0,0,255},{255,0,0,255}},
				{{255,0,0,255},{255,0,0,255},{255,0,0,255},{255,0,0,255}},
				{{255,0,0,255},{255,0,0,255},{255,0,0,255},{255,0,0,255}},
				{{255,0,0,255},{255,0,0,255},{255,0,0,255},{255,0,0,255}},
				{{255,0,0,255},{255,0,0,255},{255,0,0,255},{255,0,0,255}},
				{{0,0,255,255},{0,0,255,255},{0,0,255,255},{0,0,255,255}},
				{{0,0,255,255},{0,0,255,255},{0,0,255,255},{0,0,255,255}},
				{{0,0,255,255},{0,0,255,255},{0,0,255,255},{0,0,255,255}},
				{{0,0,255,255},{0,0,255,255},{0,0,255,255},{0,0,255,255}}};
		int[][][] perruches = readImage("images/perruche.png");
		afficheImage(tab);
		afficheImage(perruches);
		writeImage(tab,"png","images/exemple.png");
		writeImage(perruches,"png","images/perruche_bis.png");
	}

	private static int[] packedFromNatural(int [][][] tab){
		int[] res = new int[tab.length*tab[0].length];
		for (int col = 0; col<tab.length; col++){
			for (int lig=0; lig<tab[0].length; lig++){
				res[lig*tab.length+col] = packedFromArray(tab[col][lig]);
			}
		}
		return res;
	}

	private static int packedFromArray(int[] pix){
		int res = pix[3];
		res = (((res<<8) + pix[0]<< 8) + pix[1]);
		return (res<<8) + pix[2];
	}

	private static int[] explodePixel(int pix){
		int[] pt = new int[4];
		pt[3]= pix >> 24 & 0x000000FF;
		pt[0]= pix >> 16 & 0x000000FF;
		pt[1]= pix >> 8 & 0x000000FF;
		pt[2]= pix & 0x000000FF;
		return pt;
	}	 
}

