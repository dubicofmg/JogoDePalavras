package visao;



import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

import controle.Memoria;
import controle.MemoriaObservadorLetra;
import modelo.Palavra;

@SuppressWarnings("serial")
public class Letra extends JLabel implements MemoriaObservadorLetra {
	
	private final Color fundo = new Color(110,92,98);
	private final Color letra_corr = new Color(58,163,148);
	private final Color letra_corr_pos_err_fundo = new Color(211,173,105);
	private final Color letra_disp = new Color(76,67,71);
	int refx;
	int refy;
	
	public Letra(String texto, Color cor, int x, int y) {
		setText(texto);
		setOpaque(true);
		setBackground(cor);
		
		
		setFont(new Font("mitr", Font.PLAIN, 20));
		setForeground(Color.white);
		setPreferredSize(new Dimension(48,48));
		this.setMinimumSize(this.getPreferredSize()); 
		this.setMaximumSize(this.getPreferredSize());
		
		
		this.refx = x;
		this.refy = y;
		controle.Memoria.getInstancia().adicionarObservadorLetra(this);
		
	}

	


	@Override
	public void posicaoAlterada(int x, int y) {
		if(this.refx == x && this.refy == y) {
			setBorder(BorderFactory.createMatteBorder(2, 2, 4, 2, letra_disp));
		} else if(this.refy == y) {
			setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, letra_disp));
		} else {
			setBorder(null);
		}
		
	}


	@Override
	public void trocarLetra(int x, int y, String string) {
		if(this.refx == x && this.refy == y) {
			setText(string);
			
		}
		
	}




	@Override
	public int informaPosX() {
		// TODO Auto-generated method stub
		return refx;
	}




	@Override
	public void atualizaLinhaLetras(int y, ArrayList<String> status_palavra, String palavra_mestra) {
		new Palavra(Memoria.getInstancia().getPalavra_mestra());
		for(int i = 0; i < Memoria.getInstancia().getPalavra_mestra().length();i++) {
			
			if(this.refy == y && status_palavra.get(i).equals("C") &&  i == this.refx ) {
				this.setBackground(letra_corr);
				this.setText(palavra_mestra.substring(i,i+1));
			} else if(this.refy == y && status_palavra.get(i).equals("M") && i == this.refx ) {
				this.setBackground(letra_corr_pos_err_fundo);
			
			} else if(this.refy == y && status_palavra.get(i).equals("E") && i == this.refx )  {
				this.setBackground(Color.black);
				
			}
			
		}
		
	}




	@Override
	public void mudarLinha(int posy) {
		if( this.refx == 0 && this.refy == posy) {
			setBorder(BorderFactory.createMatteBorder(2, 2, 4, 2, letra_disp));
			setBackground(fundo);
			
		} else if(this.refy == posy) {
			setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, letra_disp));
			setBackground(fundo);
		} else {
			setBorder(null);
		}
		
		
	}
	
	
}

