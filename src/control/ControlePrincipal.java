package control;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
			aplicaCompressao();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Não foi possível salvar o arquivo filtrado!");
		}
	}
	
	public void aplicaCompressao() throws IOException{
		//String strImg = dstImg.toString();
		String strImg = imageToString(dstImg);
	    Compressor cmp = new Compressor();
		int janela = Integer.parseInt(JOptionPane.showInputDialog(null,"Entre com o tamanho da janela (null para tamanho Default: "));
		String compressedData = cmp.compress(strImg, janela);
		if(compressedData != null){
			JOptionPane.showMessageDialog(null, "Armazenou resultado!");
			File imgFiltrada = new File("ImagemComprimida.txt");
			FileOutputStream fos = new FileOutputStream(imgFiltrada);  // Perceba que estamos instanciando uma classe aqui. A FileOutputStream. Pesquise sobre ela!  
	        fos.write(compressedData.getBytes());            
	        fos.close();
		}
		//System.out.println(compressedData);
	}
	
	public String imageToString(BufferedImage bImage)   {
        String imageString = "";

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
