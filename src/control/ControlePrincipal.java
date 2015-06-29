package control;

import java.awt.image.BufferedImage;
import java.io.File;

import view.principalJanela;

public class ControlePrincipal {
	public void startApp(){
		principalJanela frame = new principalJanela();		
		frame.setVisible(true);		
		
	}
	
	public void escolheImagem(File imagem){
		AbreImagem abreImg = new AbreImagem();
		abreImg.abreImagem(imagem);
	}
	
	public void aplicaFiltro(BufferedImage img){
		FiltroGaussiano filtro = new FiltroGaussiano();
		filtro.filter(img, null);
	}
}
