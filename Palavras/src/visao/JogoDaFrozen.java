package visao;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import controle.Memoria;

@SuppressWarnings("serial")
public class JogoDaFrozen extends JFrame{

	private final Color fundo = new Color(110,92,98);

	public JogoDaFrozen() {
		
		organizarLayout();
		setTitle("Termo Copiado");

		setSize(650, 550);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	private void organizarLayout() {

		setLayout(new BorderLayout());

	
		Tela tela = new Tela();



		JPanel c1 = new JPanel();
		int tam_palavra = Memoria.getInstancia().getPalavra_mestra().length();
		int tam_paineis_lat = (650 - (tam_palavra * 52))/2;
		c1.setPreferredSize(new Dimension(tam_paineis_lat-50, 350));
		c1.setBackground(fundo);
		add(tela, BorderLayout.CENTER);

		JPanel c2 = new JPanel();
		c2.setPreferredSize(new Dimension(tam_paineis_lat-50, 350));
		c2.setBackground(fundo);

		Teclado teclado = new Teclado();
		teclado.setPreferredSize(new Dimension(630, 200));
		add(c1, BorderLayout.WEST);
		add(c2, BorderLayout.EAST);
		add(teclado, BorderLayout.SOUTH);



	}

	public static void main(String[] args) {
		new JogoDaFrozen();

	}

}
