package control;

import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JOptionPane;

import view.principalJanela;

public class AbreImagem {

	private Image img;
	private String srcImg;
	private principalJanela pj = new principalJanela();

	public void abreImagem(){

		srcImg= pj.getImagem() ;          

		// Obtém o Toolkit padrão
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		setImagem(toolkit.getImage(srcImg));

		JOptionPane.showMessageDialog(null,"A imagem foi carregada");
	}

	public Image getImagem() {
		return img;
	}

	public void setImagem(Image imagem) {
		this.img = imagem;
	}
}
