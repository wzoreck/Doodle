package doodle.bd;

import java.util.ArrayList;
import java.util.List;

public interface InterfaceDAO<T> {
	
	public void adicionar(T referencia, ArrayList<Integer> id);
	public List<T> listar(int aux);
	public void atualizar(T referencia);
	public void remover(T referencia);

}
