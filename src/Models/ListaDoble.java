package Models;

public class ListaDoble<T> implements Comparable{
	
	private NodoDoble inicio;
	private NodoDoble ultimo;
	
	public ListaDoble(){
		this.inicio = null;
		this.ultimo = null;
	}
	
	public NodoDoble getInicio() {
		return inicio;
	}

	public void setInicio(NodoDoble inicio) {
		this.inicio = inicio;
	}

	public NodoDoble getUltimo() {
		return ultimo;
	}

	public void setUltimo(NodoDoble ultimo) {
		this.ultimo = ultimo;
	}

	public boolean isVacia(){
		return this.inicio == null;
	}
	
	public void insertarInicio(T dato) {
		NodoDoble<T> nodo = new NodoDoble<T>(dato);
		if(isVacia()){
			this.inicio = nodo;
			this.ultimo = nodo;
		} else {
			this.inicio.setAnterior(nodo);
			nodo.setSiguiente(this.inicio);
			this.inicio = nodo;
		}
	}
	
	public void insertarFinal(T dato){
		NodoDoble<T> nodo = new NodoDoble<T>(dato);
		if(isVacia()){
			this.inicio = nodo;
			this.ultimo = nodo;
		} else {
			nodo.setAnterior(this.ultimo);
			this.ultimo.setSiguiente(nodo);
			this.ultimo = nodo;
		}
	}
	
	public int count(){
		NodoDoble nodo = this.getInicio();
		int c = 0;
		while(nodo != null) {
			c++;
			nodo = nodo.getSiguiente();
		}
		return c;
	}
	
	public T get(int index){
		NodoDoble<T> nodo = this.getInicio();
		int c = 0;
		while(c < index && nodo != null){
			c++;
			nodo = nodo.getSiguiente();
		}
		return nodo.getDato();
	}
	
	public String toString(){
		NodoDoble nodo1 = this.getInicio();
		String datos = "";
		while(nodo1 != null){
			datos = datos + nodo1.getDato();
			nodo1 = nodo1.getSiguiente();
		}
		return datos;
	}
	
	@Override
	public int compareTo(Object listaDoble) {
		// TODO Auto-generated method stub
		ListaDoble<Integer> lista1 = (ListaDoble<Integer>) this;
		ListaDoble<Integer> lista2 = (ListaDoble<Integer>) listaDoble;
		if(lista1.count() > lista2.count()){
			return 1;
		} else if(lista1.count() < lista2.count()){
			return -1;
		} else {
			
			NodoDoble<Integer> nodo1 = lista1.getInicio();
			NodoDoble<Integer> nodo2 = lista2.getInicio();
			
			while(nodo1 != null && (nodo1.getDato() == nodo2.getDato())){
				nodo1 = nodo1.getSiguiente();
				nodo2 = nodo2.getSiguiente();
			}
			
			if(nodo1 == null && nodo2 == null){
				return 0;
			} else {
				if(nodo1.getDato() > nodo2.getDato()){
					return 1;
				} else {
					return -1;
				}
			}
		}
	}
}
