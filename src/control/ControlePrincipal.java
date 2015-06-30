package control;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import view.principalJanela;


public class ControlePrincipal {
	
	private BufferedImage dstImg;
	
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
		dstImg = filtro.filter(img, null);
		File dstFile = new File("imgFiltrada.png");
	    try {
			ImageIO.write(dstImg, "png", dstFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Não foi possível salvar o arquivo filtrado!");
		}
	}
	
	public void aplicaCompressao(){
		String strImg = dstImg.toString();
		Compressor cmp = new Compressor();
		int janela = Integer.parseInt(JOptionPane.showInputDialog(null,"Entre com o tamanho da janela (null ou vazio para tamanho Default: "));
		cmp.compress(strImg, janela);
	}
}
