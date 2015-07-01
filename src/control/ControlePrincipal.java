package control;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import sun.misc.BASE64Encoder;
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
		//String strImg = dstImg.toString();
		String strImg = imageToString(dstImg);
	    Compressor cmp = new Compressor();
		int janela = Integer.parseInt(JOptionPane.showInputDialog(null,"Entre com o tamanho da janela (null ou vazio para tamanho Default: "));
		String compressedData = cmp.compress(strImg, janela);
		System.out.println(compressedData);
	}
	
	public String imageToString(BufferedImage bImage)   {
        String imageString = null;

        //image to bytes
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(bImage, "png", baos);
            baos.flush();
            byte[] imageAsRawBytes = baos.toByteArray();
            baos.close();

            //bytes to string
            BASE64Encoder b64enc = new BASE64Encoder();
            imageString = b64enc.encode(imageAsRawBytes);
            //don't do this!!!
            //imageString = new String(imageAsRawBytes);
        } catch (IOException ex) {
        	throw new RuntimeException("Não foi possível abrir o arquivo!");
        }

        return imageString;
    }
}
