package modelo;

public class Palavra {
	String palavra ;
	String palavra_norm="";
	
	public String getPalavra_norm() {
		return palavra_norm;
	}

	public void setPalavra_norm(String palavra_norm) {
		this.palavra_norm = palavra_norm;
	}

	public int tamanho;
	
	public Palavra(String entrada) {
		palavra = entrada;
		tamanho = entrada.length();
		normalizarPalavra();
		
	}

	public void normalizarPalavra() {
		for(int i = 0; i < palavra.length();i++) {
			String letra = palavra.substring(i, i+1);
			
			if(letra.equals("A") || letra.equals("�") || letra.equals("�") || letra.equals("�") ) {
				palavra_norm += "A";
			}
			
			else if(letra.equals("E") || letra.equals("�") || letra.equals("�")  ) {
				palavra_norm += "E";				
			}
			
			else if	(letra.equals("O") || letra.equals("�") || letra.equals("�") || letra.equals("�") ) {
				palavra_norm += "O";
			}
			
			else if (letra.equals("I") || letra.equals("�") || letra.equals("�") ) {
				palavra_norm += "I";
			}
			
			else if (letra.equals("U") || letra.equals("�") || letra.equals("�") ) {
				palavra_norm += "U";
			}
			
			else if (letra.equals("C") || letra.equals("�") ) {
				palavra_norm += "C";
				
			}
			
			else {
				palavra_norm += letra;
			}
			
		}
		
		
	}
	
	public String getPalavra() {
		return palavra;
	}
	
	
	
}
