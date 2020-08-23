package doodle.bd;

import java.util.List;

public interface InterfaceDAO<T> {
	
	public void criar(T referencia);
	public List<T> consultar();
	public void atualizar(T referencia);
	public void remover(T referencia);

}
