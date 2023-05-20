package visao;



import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import controle.Memoria;
import controle.MemoriaObservadorBotao;

@SuppressWarnings("serial")
public class Botao extends JButton implements MemoriaObservadorBotao {
	
	private final Color fonte_padrao = new Color(250,250,255);
	private final Color fundo = new Color(110,92,98);
	private final Color letra_corr = new Color(58,163,148);
	private final Color letra_corr_pos_err_fundo = new Color(211,173,105);
	private final Color letra_err_tecl = new Color(89,75,79);
	
	
	public Botao(String texto, Color cor) {
		setText(texto);
		setOpaque(true);
		setBackground(cor);
		setBorder(BorderFactory.createLineBorder(fundo, 3));
		setFont(new Font("courier", Font.PLAIN, 40));
		setForeground(fonte_padrao);
		setPreferredSize(new Dimension(48,48));
		
		
		controle.Memoria.getInstancia().adicionarObservadorBotao(this);
		
	}


	@Override
	public void atualizarTeclado( ArrayList<String> status_palavra, String palavra_atual) {
for(int i = 0; i < Memoria.getInstancia().getPalavra_mestra().length();i++) {
			
			if(status_palavra.get(i).equals("C") && palavra_atual.substring(i,i+1).equals(this.getText()) ) {
				this.setBackground(letra_corr);
			} else if(status_palavra.get(i).equals("M") && palavra_atual.substring(i,i+1).equals(this.getText()) ) {
				this.setBackground(letra_corr_pos_err_fundo);
			} else if(status_palavra.get(i).equals("E") && palavra_atual.substring(i,i+1).equals(this.getText()) && !Memoria.getInstancia().getLetras_certas(). 
					contains(this.getText()) )  {
				this.setBackground(letra_err_tecl);
				this.setForeground(fundo);
			}
			
		}
		
	}


	
}

