package uniandes.dpoo.estructuras.logica;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

/**
 * Esta clase tiene un conjunto de métodos para practicar operaciones sobre mapas.
 *
 * Todos los métodos deben operar sobre el atributo mapaCadenas que se declara como un Map.
 * 
 * En este mapa, las llaves serán cadenas y los valores serán también cadenas. La relación entre los dos será que cada llave será igual a la cadena del valor, pero invertida.
 * 
 * El objetivo de usar el tipo Map es que sólo puedan usarse métodos de esa interfaz y no métodos adicionales provistos por la implementación concreta (HashMap).
 * 
 * No pueden agregarse nuevos atributos.
 */
public class SandboxMapas {
    /**
     * Un mapa de cadenas para realizar varias de las siguientes operaciones.
     * 
     * Las llaves del mapa son cadenas, así como los valores.
     * 
     * Las llaves corresponden a invertir la cadena que aparece asociada a cada llave.
     */
    private Map<String, String> mapaCadenas;

    /**
     * Crea una nueva instancia de la clase con las dos listas inicializadas pero vacías
     */
    public SandboxMapas( ) {
        mapaCadenas = new HashMap<String, String>( );
    }

    /**
     * Retorna una lista con las cadenas del mapa (los valores) ordenadas lexicográficamente
     * @return Una lista ordenada con las cadenas que conforman los valores del mapa
     */
    public List<String> getValoresComoLista( ) {
    	Collection<String> values = mapaCadenas.values();
    	List<String> valores = new ArrayList<String>();
    	for (String val: values) {
    		valores.add(val);
    	}
    	valores.sort((c1, c2) -> c1.compareTo(c2));
        return valores;
    }

    /**
     * Retorna una lista con las llaves del mapa ordenadas lexicográficamente de mayor a menor
     * @return Una lista ordenada con las cadenas que conforman las llaves del mapa
     */
    public List<String> getLlavesComoListaInvertida( ) {
    	String[] llaves = mapaCadenas.keySet().toArray(new String[0]);
    	List<String> keys = new ArrayList<String>();
    	for (int i = llaves.length - 1; i >= 0; i--) {
    		keys.add(llaves[i]);
    	}
    	keys.sort((c1, c2) -> c2.compareTo(c1));
        return keys;
    }

    /**
     * Retorna la cadena que sea lexicográficamente menor dentro de las llaves del mapa .
     * 
     * Si el mapa está vacío, debe retornar null.
     * @return
     */
    public String getPrimera( ) {
    	if (getCantidadCadenasDiferentes() > 0) {
    		return getLlavesComoListaInvertida().get(getCantidadCadenasDiferentes() - 1);
    	}
        return null;
    }

    /**
     * Retorna la cadena que sea lexicográficamente mayor dentro de los valores del mapa
     * 
     * Si el conjunto está vacío, debe retornar null.
     * @return
     */
    public String getUltima( ) {
    	if (getCantidadCadenasDiferentes() > 0) {
    		return getLlavesComoListaInvertida().get(0);
    	}
        return null;
    }

    /**
     * Retorna una colección con las llaves del mapa, convertidas a mayúsculas.
     * 
     * El orden de las llaves retornadas no importa.
     * @return Una lista de cadenas donde todas las cadenas están en mayúsculas
     */
    public Collection<String> getLlaves( ) {
    	List<String> llaves = getLlavesComoListaInvertida();
    	Collection<String> keys = new ArrayList<String>();
    	for (int i = llaves.size() - 1; i >= 0; i--) {
    		keys.add(llaves.get(i).toUpperCase());
    	}
        return keys;
    }

    /**
     * Retorna la cantidad de *valores* diferentes en el mapa
     * @return
     */
    public int getCantidadCadenasDiferentes( ) {
        return mapaCadenas.size();
    }

    /**
     * Agrega un nuevo valor al mapa de cadenas: el valor será el recibido por parámetro, y la llave será la cadena invertida
     * 
     * Este método podría o no aumentar el tamaño del mapa, dependiendo de si ya existía la cadena en el mapa
     * 
     * @param cadena La cadena que se va a agregar al mapa
     */
    public void agregarCadena( String cadena ) {
    	String key = "";
    	char letra;
    	for (int i = 0; i < cadena.length(); i++) {
          letra = cadena.charAt(i);
          key = letra + key;
        }
    	mapaCadenas.put(key, cadena);
    }

    /**
     * Elimina una cadena del mapa, dada la llave
     * @param cadena La llave para identificar el valor que se debe eliminar
     */
    public void eliminarCadenaConLLave( String llave ) {
    	mapaCadenas.remove(llave);
    }

    /**
     * Elimina una cadena del mapa, dado el valor
     * @param cadena El valor que se debe eliminar
     */
    public void eliminarCadenaConValor( String valor ) {
    	if (mapaCadenas.containsValue(valor)) {
	    	List<String> values = getValoresComoLista();
	    	List<String> keys = getLlavesComoListaInvertida();
	    	mapaCadenas.remove(keys.get(keys.size() - values.indexOf(valor) - 1));
    	}
    }

    /**
     * Reinicia el mapa de cadenas con las representaciones como Strings de los objetos contenidos en la lista del parámetro 'objetos'.
     * 
     * Use el método toString para convertir los objetos a cadenas.
     * @param valores Una lista de objetos
     */
    public void reiniciarMapaCadenas( List<Object> objetos ) {
    	mapaCadenas.clear();
    	for (Object i: objetos) {
    		agregarCadena(i.toString());
    	}
    }

    /**
     * Modifica el mapa de cadenas reemplazando las llaves para que ahora todas estén en mayúsculas pero sigan conservando las mismas cadenas asociadas.
     */
    public void volverMayusculas( ) {
    	List<String> values = getValoresComoLista();
    	List<String> keys = getLlavesComoListaInvertida();
    	for (String valor: values) {
    		mapaCadenas.remove(keys.get(keys.size() - values.indexOf(valor) - 1));
    		mapaCadenas.put(keys.get(keys.size() - values.indexOf(valor) - 1).toUpperCase(), valor);
    	}
    }

    /**
     * Verifica si todos los elementos en el arreglo de cadenas del parámetro hacen parte del mapa de cadenas (de los valores)
     * @param otroArreglo El arreglo de enteros con el que se debe comparar
     * @return True si todos los elementos del arreglo están dentro de los valores del mapa
     */
    public boolean compararValores( String[] otroArreglo ) {
    	boolean igual = true;
		for (String str: otroArreglo) {
			if (!mapaCadenas.containsValue(str)) {
				igual = false;
			}
		}
        return igual;
    }

}
