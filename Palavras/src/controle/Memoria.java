package controle;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JOptionPane;

import modelo.Palavra;



public class Memoria {
	
	private static final Memoria instancia = new Memoria();
	
	private Memoria() {
		
	}
	
	private int tentativas = 0;
	private List<String> letras_certas = new ArrayList<>();
	
	public List<String> getLetras_certas() {
		return letras_certas;
	}

	public void setLetras_certas(List<String> letras_certas) {
		this.letras_certas = letras_certas;
	}

	private List<String> letras_presentes = new ArrayList<>();
	private String palavra_mestra ;
	private String letra_atual = "";
	private String palavra_atual = " ";
	private int validador;
	private ArrayList<String> status_palavra = new ArrayList<>();// "C" - representa a letra correta na posição correta; 
	//"M" - representa letra certa em posição errada; "E" representa letra não presente na palavra-mestra
	public String getPalavra_atual() {
		return palavra_atual;
	}

	public void setPalavra_atual(String palavra_atual) {
		this.palavra_atual = palavra_atual;
	}

	private boolean final_jogo = false;
	private int posx; 
	private int posy; 
	private List<MemoriaObservadorLetra> observadores_letras = new ArrayList<>();
	private List<MemoriaObservadorBotao> observadores_botoes = new ArrayList<>();
	

	public int getPosx() {
		return posx;
	}

	public  void setPosx(int posx) {
		this.posx = posx;
	}

	public int getPosy() {
		return posy;
	}

	public  void setPosy(int posy) {
		this.posy = posy;
		observadores_letras.forEach(o -> o.posicaoAlterada(getPosx(), getPosy()));
	}

	public String getPalavra_mestra() {
		return palavra_mestra.toUpperCase();
	}

	public void setPalavra_mestra() throws IOException {
		String pal_sel = escolherpalavra();
		       
		this.palavra_mestra = pal_sel;
	}
	
	private String escolherpalavra() throws IOException{
			ArrayList<String> palavras = new ArrayList<>();
			FileInputStream stream;
			stream = new FileInputStream("C:/Users/55479/Desktop/jogo da frozen/palavras_selecionadas.txt");
			InputStreamReader reader = new InputStreamReader(stream, "UTF-8");
		    BufferedReader br = new BufferedReader(reader);
		    String palavra = br.readLine();
	        for(int i = 0; i < 550; i++) {
	        	    if(palavra!=null && palavra != "") {
	        		palavra = br.readLine().toUpperCase().trim();
		            palavras.add(palavra);
	        	}
	        }
	           
	        	
	        if(stream != null) {       	
	        	stream.close();

	        }
	        if(br != null) {
	        	br.close();       	
	        }
	        if(reader != null) {
	        	reader.close();
	        }
	        
	        
	        
	        Random gerador = new Random();
	        String palavra_selecionada = palavras.get(gerador.nextInt(550));
	        
	        return palavra_selecionada; 
		
	}

	public void adicionarObservadorLetra(MemoriaObservadorLetra observador) {
		observadores_letras.add(observador);
	}
	
	public void adicionarObservadorBotao(MemoriaObservadorBotao observador) {
		observadores_botoes.add(observador);
	}
	
	public static Memoria getInstancia() {
		return instancia;
	}

	

	public void testarPalavra() {
		Palavra palav_atual = new Palavra(palavra_atual);
		Palavra palav_mestr = new Palavra(palavra_mestra);
		if(tentativas == 0 ) {
			for (int i = 0; i < palavra_mestra.length(); i++) {
				status_palavra.add(i, "E");
			}
		}
				
		for (int i = 0; i < palavra_mestra.length(); i++) {
			
			
				if(palav_atual.getPalavra_norm().substring(i, i+1).equals(palav_mestr.getPalavra_norm().substring(i, i+1))) {
					status_palavra.set(i, "C");
					letras_certas.add(palav_atual.getPalavra_norm().substring(i, i+1));
				}
				else {
					status_palavra.set(i, "E");
				
				}
			
		}
		
		for (int i = 0; i < palavra_mestra.length(); i++) {
			
			if(!status_palavra.get(i).equals("C")) {
				if(palav_mestr.getPalavra_norm().contains(palav_atual.getPalavra_norm().substring(i, i+1)) && !letras_certas.contains(palav_atual.getPalavra_norm().substring(i, i+1)) ) {
					status_palavra.set(i, "M");
					letras_presentes.add(palav_atual.getPalavra_norm().substring(i, i+1));
				} 
			}		
			
		}
		
		observadores_letras.forEach(o -> o.atualizaLinhaLetras(getPosy(), status_palavra, palavra_mestra));
		observadores_botoes.forEach(o -> o.atualizarTeclado(status_palavra, palavra_atual));
		
		validador=0;
		for(int i =0;i<palavra_mestra.length();i++) {
			
			if(status_palavra.get(i).equals("C")) {
				validador++;
				
			} 
				
		}
		
		if(validador == palavra_mestra.length()) {
			final_jogo = true;	
		}
		
		tentativas++;
		
		if(final_jogo == true) {
			JOptionPane.showMessageDialog(null,"Parabéns! Você acertou a palavra "+palavra_mestra+" em "+tentativas+" tentativa(s)!");
		}
		else if(tentativas < 6){
			mudarLinhaJogo();
		} else {
			JOptionPane.showMessageDialog(null,"Que pena! Você não conseguiu acertar a palavra "+palavra_mestra+".");
		}
		//pendente: validação do final de jogo com mensagem na tela; zeragem de buffers; ajuste de posição/cor linha subsequente
		
	}

	private void mudarLinhaJogo() {
		palavra_atual="";
		setPosx(0);
		observadores_letras.forEach(o -> o.mudarLinha(getPosy()+1));
		setPosy(getPosy()+1);
	}

	public void inserirLetraPosAtual(String string) {
		
		letra_atual = string;
		
		
		observadores_letras.forEach(o -> o.trocarLetra(getPosx(), getPosy(), string));
		
		if(palavra_atual.length() == getPosx()+1) {				
			palavra_atual = palavra_atual.substring(0, (palavra_atual.length()-1))+letra_atual;
		}
		else {
				palavra_atual += letra_atual;
			}
	
			
		if(posx < palavra_mestra.length()-1) {
			setPosx(posx+1);
		}
		
		setPosy(posy);
		
	}
	
	public void apagarPosAtual() {
		
		
		if(palavra_atual.length()> 0) {
			palavra_atual = palavra_atual.substring(0, (palavra_atual.length()-1));
		}
			
		
		observadores_letras.forEach(o -> o.trocarLetra(getPosx(), getPosy(), ""));
		if(posx > 0 ) {
			setPosx(posx-1);
			
		}
		
		System.out.println(palavra_atual);
		setPosy(posy);
		
	}


	
	
	
}
