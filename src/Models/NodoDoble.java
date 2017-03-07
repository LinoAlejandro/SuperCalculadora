package Models;

public class NodoDoble<T> {
	private NodoDoble<T> siguiente;
	private NodoDoble<T> anterior;
	private T dato;
	
	public NodoDoble(T dato) {
		this.siguiente = null;
		this.anterior = null;
		this.dato = dato; 
	}
	
	public NodoDoble<T> getSiguiente() {
		return siguiente;
	}
	
	public void setSiguiente(NodoDoble<T> siguiente) {
		this.siguiente = siguiente;
	}
	
	public NodoDoble<T> getAnterior() {
		return anterior;
	}
	
	public void setAnterior(NodoDoble<T> nodo) {
		this.anterior = nodo;
	}
	public T getDato() {
		return dato;
	}
	
	public void setDato(T dato) {
		this.dato = dato;
	}
}
