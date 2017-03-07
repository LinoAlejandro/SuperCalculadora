package Controlers;

import Models.ListaDoble;
import Models.NodoDoble;
import Models.Operaciones;

public class OperacionesImpl implements Operaciones{

	public ListaDoble pasarLista(ListaDoble lista){
		ListaDoble listaDoble = new ListaDoble();
		NodoDoble nodo = lista.getInicio();
		while(nodo != null){
			listaDoble.insertarFinal(nodo.getDato());
			nodo = nodo.getSiguiente();
		}
		
		return listaDoble;
	}
	
	public ListaDoble<Integer> extenderLista(ListaDoble listaDoble, int cantidadRecorrido){
		int c = 0;
		while(c < cantidadRecorrido){
			listaDoble.insertarInicio(0);
			c++;
		}
		return listaDoble;
	}
	@Override
	public ListaDoble<ListaDoble<Integer>> ordenarListas(ListaDoble<Integer> lista1, ListaDoble<Integer> lista2) {
		// TODO Auto-generated method stub
		// se trasladan las listas para no modificar el valor de los parametros
		ListaDoble<Integer> listaAux1 = this.pasarLista(lista1);
		ListaDoble<Integer> listaAux2 = this.pasarLista(lista2);
		
		ListaDoble<ListaDoble<Integer>> listaResultado = new ListaDoble<ListaDoble<Integer>>();
		
		if(listaAux1.count() <= listaAux2.count()){
			//1 lista1 mayor, 2 lista2 Mayor, 0 equilibrio
			int situacion = 0;
			int cantidad = listaAux2.count() - listaAux1.count();
			listaAux1 = this.extenderLista(listaAux1, cantidad);
			
			NodoDoble<Integer> nodo1 = listaAux1.getInicio();
			NodoDoble<Integer> nodo2 = listaAux2.getInicio();
			
			while(nodo1 != null && situacion == 0){
				if(nodo1.getDato() < nodo2.getDato()){
					situacion = 2;
				} else if(nodo1.getDato() > nodo2.getDato()){
					situacion = 1;
				}
				nodo1 = nodo1.getSiguiente();
				nodo2 = nodo2.getSiguiente();
			}
			
			if(situacion == 2){
				listaResultado.insertarFinal(listaAux2);
				listaResultado.insertarFinal(listaAux1);
			} else {
				listaResultado.insertarFinal(listaAux1);
				listaResultado.insertarFinal(listaAux2);
			}
		} else {
			int cantidad = listaAux1.count() - listaAux2.count();
			this.extenderLista(listaAux2, cantidad);
			listaResultado.insertarFinal(listaAux1);
			listaResultado.insertarFinal(listaAux2);
		}
		//siempre se agrega primero la lista mas grande
		return listaResultado;
	}
	
	@Override
	public int[] sumar(Integer dato1, Integer dato2) {
		// TODO Auto-generated method stub
		int resultado = dato1 + dato2;
		
		if(resultado > 9){
			resultado = resultado % 10;
			int[] resultados = {resultado, 1};
			return resultados;
		} else {
			int[] resultados = {resultado};
			return resultados;
		}
	}
	
	@Override
	public ListaDoble<Integer> sumarListas(ListaDoble<Integer> lista1, ListaDoble<Integer> lista2) {
		
		ListaDoble<ListaDoble<Integer>> listasCorregidas = this.ordenarListas(lista1, lista2);
		
		ListaDoble<Integer> listaAux1 = listasCorregidas.get(0);
		ListaDoble<Integer> listaAux2 = listasCorregidas.get(1);
		ListaDoble<Integer> listaResultado = new ListaDoble<Integer>();
		
		NodoDoble<Integer> nodo1 = listaAux1.getUltimo();
		NodoDoble<Integer> nodo2 = listaAux2.getUltimo();
		
		int residuoSuma = 0;
		while(nodo1 != null){
			int[] total = null;
			if(residuoSuma != 0){
				 total = this.sumar(nodo1.getDato(), nodo2.getDato() + 1);
				 residuoSuma = 0;
			} else {
				total = this.sumar(nodo1.getDato(), nodo2.getDato());
			}
			
			if(total.length == 2){
				residuoSuma = total[1];
			}
			 
			listaResultado.insertarInicio(total[0]);
			
			nodo1 = nodo1.getAnterior();
			nodo2 = nodo2.getAnterior();
		}
		if(residuoSuma != 0){
			listaResultado.insertarInicio(1);
		}
		return listaResultado;
	}

	@Override
	public ListaDoble<Integer> restarListas(ListaDoble<Integer> minuendo, ListaDoble<Integer> sustraendo) {
		// TODO Auto-generated method stub
		ListaDoble<ListaDoble<Integer>> listasOrdenadas = this.ordenarListas(minuendo, sustraendo);
		
		ListaDoble<Integer> listaMinuendo = listasOrdenadas.get(0);
		ListaDoble<Integer> listaSustraendo = listasOrdenadas.get(1);
		
		ListaDoble<Integer> listaResultado = new ListaDoble<Integer>();
 		
		NodoDoble<Integer> nodoAuxMinuendo = listaMinuendo.getUltimo();
		NodoDoble<Integer> nodoAuxSustraendo = listaSustraendo.getUltimo();
		
		int resultado = 0;
		
		while(nodoAuxMinuendo != null) {
			
				resultado = nodoAuxMinuendo.getDato() - nodoAuxSustraendo.getDato();
				
			if(resultado < 0){
				resultado += 10;
				nodoAuxMinuendo.getAnterior().setDato(nodoAuxMinuendo.getAnterior().getDato() - 1);
				listaResultado.insertarInicio(resultado);
			} else {
				listaResultado.insertarInicio(resultado);
			}
			nodoAuxMinuendo = nodoAuxMinuendo.getAnterior();
			nodoAuxSustraendo = nodoAuxSustraendo.getAnterior();
		}
		this.eliminarCerosAlInicio(listaResultado);
		return listaResultado;
	}

	@Override
	public ListaDoble<Integer> multiplicarListas(ListaDoble<Integer> multiplicando, ListaDoble<Integer> multiplicador) {
		// TODO Auto-generated method stub
		ListaDoble<ListaDoble<Integer>> listasOrdenadas = this.ordenarListas(multiplicando, multiplicador);
		
		ListaDoble<Integer> multiplicandoAux = listasOrdenadas.get(0);
		ListaDoble<Integer> multiplicadorAux = listasOrdenadas.get(1);
		
		
		this.eliminarCerosAlInicio(multiplicadorAux);
		ListaDoble<ListaDoble<Integer>> listasASumar = new ListaDoble<ListaDoble<Integer>>();
		
		NodoDoble<Integer> nodoMultiplicando = multiplicandoAux.getUltimo();
		NodoDoble<Integer> nodoMultiplicador = multiplicadorAux.getUltimo();
		int e = 0;
		
		while(nodoMultiplicador != null){
			ListaDoble<Integer> listaAuxiliar = new ListaDoble<Integer>();
			for(int c = 0; c < nodoMultiplicador.getDato(); c++){
				listaAuxiliar = this.sumarListas(listaAuxiliar, multiplicandoAux);
				
			}
			
			for(int d = 0; d < e; d++){
				listaAuxiliar.insertarFinal(0);
			}
			
			e++;
			
			listasASumar.insertarInicio(listaAuxiliar);
			listaAuxiliar = new ListaDoble<Integer>();
			nodoMultiplicador = nodoMultiplicador.getAnterior();
		}
		
		ListaDoble<Integer> resultado = new ListaDoble<Integer>();
		
		for(int c = 0; c < listasASumar.count(); c++){
			resultado = this.sumarListas(resultado, listasASumar.get(c));
		}
		
		return resultado;
	}
	
	public void eliminarCerosAlInicio(ListaDoble<Integer> lista){
		NodoDoble<Integer> nodo = lista.getInicio();
		
		while(nodo != null && nodo.getDato() == 0){
			nodo = nodo.getSiguiente();
			lista.setInicio(nodo);
		}
	}
	
	//valoracion desde el 0 como primer digito, 1 como segundo, etc.
	public ListaDoble<Integer> getSubLista(int begin, int end, ListaDoble<Integer> lista){
		NodoDoble<Integer> nodo = lista.getInicio();
		ListaDoble<Integer> subLista = new ListaDoble<Integer>();
		int c = 0;
		while(nodo != null && c < end){
			if(c >= begin){
				subLista.insertarFinal(nodo.getDato());
			}
			c++;
			nodo = nodo.getSiguiente();
		}
		return subLista;
	}

	@Override
	public ListaDoble<ListaDoble<Integer>> dividirLista(ListaDoble<Integer> dividendo, ListaDoble<Integer> divisor) {
		
		ListaDoble<ListaDoble<Integer>> listas = this.ordenarListas(dividendo,divisor);

		ListaDoble<Integer> dividendoLista = listas.get(0);
		ListaDoble<Integer> divisorLista = listas.get(1);

		this.eliminarCerosAlInicio(dividendoLista);
		this.eliminarCerosAlInicio(divisorLista);
		
		ListaDoble<Integer> cociente = new ListaDoble<Integer>();
		
		NodoDoble<Integer> nodo = dividendoLista.getInicio();
		ListaDoble<Integer> listaParcial = new ListaDoble<Integer>();
		
		while(nodo != null){
			
			listaParcial.insertarFinal(nodo.getDato());
			
			if(listaParcial.compareTo(divisorLista) != -1){
				int cocienteNumero = 0;
				ListaDoble<Integer> listaSumasParcial = new ListaDoble<Integer>();
				
				do{
					cocienteNumero++;
					listaSumasParcial = this.sumarListas(listaSumasParcial, divisorLista);
				} while(listaSumasParcial.compareTo(listaParcial) != 1);
				
				listaSumasParcial = this.restarListas(listaSumasParcial, divisorLista);
				cocienteNumero--;
				cociente.insertarFinal(cocienteNumero);
				cocienteNumero = 0;
				listaParcial = this.restarListas(listaSumasParcial, listaParcial);
				
			} else {
				
				cociente.insertarFinal(0);
			}
			
			this.eliminarCerosAlInicio(listaParcial);
			nodo = nodo.getSiguiente();
		}
		
		ListaDoble<ListaDoble<Integer>> resultados = new ListaDoble<ListaDoble<Integer>>();
		this.eliminarCerosAlInicio(cociente);;
		resultados.insertarFinal(cociente);
		resultados.insertarFinal(listaParcial);
		return resultados;
	}

	public String getBiggerOrden(ListaDoble<Integer> lista1, ListaDoble<Integer> lista2){
        this.eliminarCerosAlInicio(lista1);
        this.eliminarCerosAlInicio(lista2);
        if(lista1.count() > lista2.count()){
            return "Lista Primera es Mayor";
        } else if(lista1.count() < lista2.count()){
            return "Lista Segunda es Mayor";
        } else {
            NodoDoble<Integer> nodo1 = lista1.getInicio();
            NodoDoble<Integer> nodo2 = lista2.getInicio();
            while(nodo1 != null && nodo1.getDato() == nodo2.getDato()){
                nodo1 = nodo1.getSiguiente();
                nodo2 = nodo2.getSiguiente();
            }
            
            if(nodo1 == null && nodo2 == null){
                return "Las Listas son Identicas";
            } else {
                if(nodo1.getDato() < nodo2.getDato()){
                    return "Lista Segunda es Mayor";
                } else {
                    return "Lista Primera es Mayor";
                }
            }
        }
    }
}
