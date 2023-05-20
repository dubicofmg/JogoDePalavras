package visao;



import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import controle.Memoria;


@SuppressWarnings("serial")
public class Tela extends JPanel   {
	
	
	private final Color fundo = new Color(110,92,98);
	private final Color linha_palavra_bloq = new Color(97,84,88);
	private final Color letra_disp = new Color(76,67,71);
	
		
	public Tela() {
		
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		try {
			Memoria.getInstancia().setPalavra_mestra();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		int tam_palavra = Memoria.getInstancia().getPalavra_mestra().length();
		setLayout(layout);
		setPreferredSize(new Dimension(52*tam_palavra, 300));
		setSize(new Dimension(52*tam_palavra, 300));
		this.setBackground(fundo);
		
		
		c.weightx = 1;
		c.weighty = 1;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(2,2,2,2);
		
//		
		//Linha 1
				c.gridwidth = 1;
				for (int i = 0; i < tam_palavra; i++) {
					String a = " ";
					adicionarLetraBorda(a, fundo, c, i, 0);
				}
		//Linha 2
				for (int i = 0; i < tam_palavra; i++) {
					String a = " ";
					adicionarLetra(a, linha_palavra_bloq, c, i, 1);
				}
		//Linha 3
				for (int i = 0; i < tam_palavra; i++) {
					String a = " ";
					adicionarLetra(a, linha_palavra_bloq, c, i, 2);
				}
		//Linha 4
				for (int i = 0; i < tam_palavra; i++) {
					String a = " ";
					adicionarLetra(a, linha_palavra_bloq, c, i, 3);
				}
		//Linha 5
				for (int i = 0; i < tam_palavra; i++) {
					String a = " ";
					adicionarLetra(a, linha_palavra_bloq, c, i, 4);
				}
		//Linha 6
				for (int i = 0; i < tam_palavra; i++) {
					String a = " ";
					adicionarLetra(a, linha_palavra_bloq, c, i, 5);
				}
				c.fill = GridBagConstraints.NONE;	
				setPreferredSize(new Dimension(52*tam_palavra, 300));
				setSize(new Dimension(52*tam_palavra, 300));
	iniciarJogo();
	}	

	private void iniciarJogo() {
		
		controle.Memoria.getInstancia().setPosx(0);		
		controle.Memoria.getInstancia().setPosy(0);		
		
	}

	

	private void adicionarLetraBorda(String texto, Color cor, GridBagConstraints c, int x, int y) {
		c.gridx = x;
		c.gridy = y;
		Letra letra = new Letra(texto, cor, x, y);
		
		letra.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, letra_disp));
		letra.setHorizontalAlignment(SwingConstants.CENTER);
		letra.setVerticalAlignment(SwingConstants.CENTER);
		add(letra, c);
		
	}

	private void adicionarLetra(String texto, Color cor, GridBagConstraints c, int x, int y) {
		c.gridx = x;
		c.gridy = y;
		Letra letra = new Letra(texto, cor, x, y);
		letra.setHorizontalAlignment(SwingConstants.CENTER);
		letra.setVerticalAlignment(SwingConstants.CENTER);
		add(letra, c);
		
	}
	
	
	
	
}

