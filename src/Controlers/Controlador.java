
package Controlers;

import Models.ListaDoble;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Limbo
 */
public class Controlador {
    private ListaDoble<Integer> lista1;
    private ListaDoble<Integer> lista2;
    OperacionesImpl operaciones;
    
    public Controlador(){
        lista1 = new ListaDoble<Integer>();
        lista2 = new ListaDoble<Integer>();
        operaciones = new OperacionesImpl();
    }
    
    public ArrayList<String> obtenerStringsDesdeArchivo(File file){
        ArrayList<String> listas = new ArrayList<String>();
        
        Charset charset = Charset.forName("US-ASCII");
        Path path = Paths.get(file.getAbsolutePath());
        
        try (BufferedReader reader = Files.newBufferedReader(path, charset)) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                listas.add(line);
            }
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }        
        
        return listas;
    }
    
    public ListaDoble<Integer> separarElementosEnLista(String cadenaLista){
        String[] elementos = cadenaLista.split("");
        ListaDoble<Integer> lista = new ListaDoble<Integer>();
        
        for (String elemento : elementos) {
            lista.insertarFinal(Integer.parseInt(elemento));
        }
        return lista;
    }
    
    public String multiplicarListas(ListaDoble<Integer> lista1, ListaDoble<Integer> lista2){
        ListaDoble<Integer> producto = operaciones.multiplicarListas(lista1, lista2);
        return producto.toString();
    }
    
    public String sumarListas(ListaDoble<Integer> lista1, ListaDoble<Integer> lista2){
        ListaDoble<Integer> suma = operaciones.sumarListas(lista1, lista2);
        return suma.toString();
    }
    
    public String restarListas(ListaDoble<Integer> lista1, ListaDoble<Integer> lista2){
        ListaDoble<Integer> resta = operaciones.restarListas(lista1, lista2);
        return resta.toString();
    }
    
    public String[] dividirListas(ListaDoble<Integer> lista1, ListaDoble<Integer> lista2){
        ListaDoble<ListaDoble<Integer>> resultado = operaciones.dividirLista(lista1, lista2);
        String lista1String = resultado.get(0).toString();
        String lista2String = resultado.get(1).toString();
        String[] resultados = {lista1String, lista2String};
        return resultados;
    }
    public void setLista1(ListaDoble<Integer> lista1){
        this.lista1 = lista1;
    }
    
    public void setLista2(ListaDoble<Integer> lista2){
        this.lista2 = lista2;
    }
    
    public ListaDoble<Integer> getLista1(){
        return this.lista1;
    }
    
    public ListaDoble<Integer> getLista2(){
        return this.lista2;
    }

    public String getGreaterList(ListaDoble<Integer> lista1, ListaDoble<Integer> lista2) {
        return operaciones.getBiggerOrden(lista1, lista2);
    }
    
    public void crearArchivo(String[] cadenas) {
		
		File fout = new File("OUTPUT.txt");
		FileOutputStream fos;
		
		try {
			fos = new FileOutputStream(fout);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
			for (String cadena : cadenas) {
				bw.write(cadena);
				bw.newLine();
			}
			bw.close();
			JOptionPane.showMessageDialog(null, "Proceso Terminado");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}