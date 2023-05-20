package controle;

import java.util.ArrayList;

public interface MemoriaObservadorLetra {
	void posicaoAlterada(int x, int y); 
	void trocarLetra(int posx, int posy, String string);
	int informaPosX();
	void atualizaLinhaLetras(int posy, ArrayList<String> refs, String palavra_mestra);
	void mudarLinha(int posy);

}
