package doodle.bd;

import java.util.List;

public interface InterfaceDAO<T> {
	
	public void adicionar(T referencia);
	public List<T> listar();
	public void atualizar(T referencia);
	public void remover(T referencia);

}
