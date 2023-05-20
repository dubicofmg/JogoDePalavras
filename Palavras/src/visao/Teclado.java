package visao;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import controle.Memoria;



@SuppressWarnings("serial")
public class Teclado extends JPanel  implements ActionListener {
	
	private final Color fundo = new Color(110,92,98);
	private final Color letra_disp = new Color(76,67,71);
	public Teclado() {
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		
		setLayout(layout);
		setPreferredSize(new Dimension(630, 200));
		setBackground(fundo);
		
		c.weightx = 1;
		c.weighty = 1;
		c.fill = GridBagConstraints.BOTH;
		
		//Linha 1
		c.gridwidth = 1;
		adicionarBotao("Q", letra_disp, c, 0, 0);
		adicionarBotao("W", letra_disp, c, 1, 0);
		adicionarBotao("E", letra_disp, c, 2, 0);
		adicionarBotao("R", letra_disp, c, 3, 0);
		adicionarBotao("T", letra_disp, c, 4, 0);
		adicionarBotao("Y", letra_disp, c, 5, 0);
		adicionarBotao("U", letra_disp, c, 6, 0);
		adicionarBotao("I", letra_disp, c, 7, 0);
		adicionarBotao("O", letra_disp, c, 8, 0);
		adicionarBotao("P", letra_disp, c, 9, 0);
		
		//Linha 2
		c.gridwidth = 1;
		adicionarBotao("A", letra_disp, c, 0, 1);
		adicionarBotao("S", letra_disp, c, 1, 1);
		adicionarBotao("D", letra_disp, c, 2, 1);
		adicionarBotao("F", letra_disp, c, 3, 1);
		adicionarBotao("G", letra_disp, c, 4, 1);
		adicionarBotao("H", letra_disp, c, 5, 1);
		adicionarBotao("J", letra_disp, c, 6, 1);
		adicionarBotao("K", letra_disp, c, 7, 1);
		adicionarBotao("L", letra_disp, c, 8, 1);
		c.gridwidth = 1;
		c.gridx = 9;
		c.gridy = 1;
		Botao botao_del = new Botao("", letra_disp);
		botao_del.addActionListener(this);
		botao_del.setIcon ( new ImageIcon ("C:/Users/55479/Desktop/jogo da frozen/Icone_Apagar.png") );
		add(botao_del, c);
		
		//linha3
		adicionarBotao("Z", letra_disp, c, 0, 2);
		adicionarBotao("X", letra_disp, c, 1, 2);
		adicionarBotao("C", letra_disp, c, 2, 2);
		adicionarBotao("V", letra_disp, c, 3, 2);
		adicionarBotao("B", letra_disp, c, 4, 2);
		adicionarBotao("N", letra_disp, c, 5, 2);
		adicionarBotao("M", letra_disp, c, 6, 2);
		c.gridwidth = 3;
		adicionarBotao("ENTER", letra_disp, c, 7, 2);
		
		
		}

	private void adicionarBotao(String texto, Color cor, GridBagConstraints c, int x, int y) {
		c.gridx = x;
		c.gridy = y;
		Botao botao = new Botao(texto, cor);
		botao.addActionListener(this);
		add(botao, c);
		
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() instanceof JButton) {
			JButton botao = (JButton) e.getSource();
			int tam_palavra_at = Memoria.getInstancia().getPalavra_atual().length();
			int tam_palavra_cha = Memoria.getInstancia().getPalavra_mestra().length();
			
			if(botao.getText().equals("")) {
				Memoria.getInstancia().apagarPosAtual();
			} else if (botao.getText().equals("ENTER") ) {
				if(tam_palavra_at == tam_palavra_cha) {
					Memoria.getInstancia().testarPalavra();				
				}
			} else {
				Memoria.getInstancia().inserirLetraPosAtual(botao.getText());
			}
		}
	}
}
