package control;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class AbreImagem {

	private BufferedImage img = null;
	private File srcImg;	
	public void abreImagem(File imagem) {
		
		srcImg = imagem;	
		
		try {
			if(srcImg.isFile()){
			img = ImageIO.read(srcImg);
			}
		} catch (IOException e) {
			throw new RuntimeException("Não foi possível abrir o arquivo!");
		}
		// Obtém o Toolkit padrão
		//Toolkit toolkit = Toolkit.getDefaultToolkit();
		//setImagem(toolkit.getImage(srcImg));
		JOptionPane.showMessageDialog(null,"A imagem foi carregada com sucesso!");
		ControlePrincipal cp = new ControlePrincipal();
		cp.aplicaFiltro(img);
	}

	public BufferedImage getImagem() {
		return img;
	}

	public void setImagem(BufferedImage imagem) {
		this.img = imagem;
	}
}
