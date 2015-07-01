package view;

//IMPORTAÇÃO DE DEPENDENCIAS
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import control.ControlePrincipal;


public class principalJanela extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 123456;
	private JFileChooser chooser;
	private JPanel contentPane;
	private File imagem = null;
	public ControlePrincipal cp; 

	public principalJanela() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(800, 350, 366, 364);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));

		setContentPane(contentPane);

		JInternalFrame internalFrame = new JInternalFrame("TelaPrincipal");
		contentPane.add(internalFrame, BorderLayout.CENTER);

		JButton btnFiltrarImagem = new JButton("Filtrar Imagem...");
		internalFrame.getContentPane().add(btnFiltrarImagem, BorderLayout.SOUTH);

		JButton btnComprimirSalvar = new JButton("Comprimir e Salvar imagem!");
		contentPane.add(btnComprimirSalvar, BorderLayout.SOUTH);

		//Torna o botão visível
		btnComprimirSalvar.setVisible(true);
		btnComprimirSalvar.setEnabled(false);
		
		//Define acao do botão
		btnFiltrarImagem.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				//btnFiltrarImagem.setEnabled(false);;
				btnComprimirSalvar.setEnabled(true);
				escolheArquivo();
			}
		});

		btnComprimirSalvar.addActionListener(new ActionListener() {


			public void actionPerformed(ActionEvent arg0) {
				try {
					cp.aplicaCompressao();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 					
			}
		});

		//Torna a janela principal visível
		internalFrame.setVisible(true);
	}

	private void escolheArquivo(){

		chooser = new JFileChooser();				
		chooser.setCurrentDirectory(new java.io.File("."));
		chooser.setAcceptAllFileFilterUsed(false);
		FileFilter filter = new FileNameExtensionFilter(".PNG ","png");
		chooser.setFileFilter(filter);
		chooser.setDialogTitle("Seleção de imagem");


		if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
		{
			//imagemURL = chooser.getSelectedFile().getAbsolutePath();
			imagem = chooser.getSelectedFile();			
			cp = new ControlePrincipal();
			cp.escolheImagem(imagem);
			//Map<String, Integer> result = new HashMap<String, Integer>();
			//File directory = new File(chooser.getSelectedFile().getAbsolutePath()); //This is where you need to change.

			/*File[] files = directory.listFiles();
	        for (File file : files)
	        {
	            if (file.isFile())
	            {
	                Scanner scanner = null;
					try {
						scanner = new Scanner(new FileReader(file));
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						JOptionPane.showInternalMessageDialog(null, "Não foi possível abrir o arquivo selecionado!");
					}
	                int lineCount = 0;
	                try
	                {
	                    for (lineCount = 0; scanner.nextLine() != null; lineCount++)
	                        ;
	                } catch (NoSuchElementException e)
	                {
	                    result.put(file.getName(), lineCount);
	                }
	            }
	        }*/	        	       
		}			    	
	}
	
	

	public void setImagem(File imagem) {
		this.imagem = imagem;
	}

	public File getImagem() {
		return imagem;
	}

}
