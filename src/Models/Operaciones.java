package Models;

public interface Operaciones {
	
	public ListaDoble<Integer> sumarListas(ListaDoble<Integer> lista1, ListaDoble<Integer> lista2);
	
	public ListaDoble<Integer> restarListas(ListaDoble<Integer> minuendo, ListaDoble<Integer> sustraendo);
	
	public ListaDoble<Integer> multiplicarListas(ListaDoble<Integer> lista1, ListaDoble<Integer> lista2);
	
	//regresa el numero del argumento mayor, ejemplo 1 = lista1 es mayor, 2 = lista2 es mayor, 0 = son identicas
	public ListaDoble<ListaDoble<Integer>> ordenarListas(ListaDoble<Integer> lista1, ListaDoble<Integer> lista2);
	
	public ListaDoble<ListaDoble<Integer>> dividirLista(ListaDoble<Integer> dividendo, ListaDoble<Integer> divisor);

	public int[] sumar(Integer dato1, Integer dato2);
	
}
