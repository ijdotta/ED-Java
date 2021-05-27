package Operaciones;

import java.util.Scanner;

import TDAGrafo.Edge;
import TDAGrafo.GrafoD_lista_adyacencia;
import TDAGrafo.GraphD;
import TDAGrafo.InvalidEdgeException;
import TDAGrafo.InvalidVertexException;
import TDAGrafo.Vertex;
import TDALista.DoubleLinkedList;
import TDALista.EmptyListException;
import TDALista.InvalidPositionException;
import TDALista.PositionList;

/**
 * Class Ciudades con rutas - Modela un conjunto de ciudades y las rutas que las conectan.
 * @author Ignacio Joaquín Dotta
 *
 */
public class Ciudades_con_rutas {
	
	protected GraphD<Ciudad, Float> graph;
	
	/**
	 * Crea un mapa de ciudades y rutas vacío.
	 */
	public Ciudades_con_rutas() {
		graph = new GrafoD_lista_adyacencia();
	}
	
	public void insertarCiudad(Ciudad c) {
		boolean esta = false;
		for (Vertex<Ciudad> v : graph.vertices())
			if (v.element() == c) {
				esta = true;
				break;
			}
		
		if (!esta)
			graph.insertVertex(c);
		else 
			System.out.println("Ciudad ya se encuentra en el mapa. ");
	}
	
	public void eliminarCiudad(Ciudad c) {
		try {
			for (Vertex<Ciudad> v : graph.vertices())
				if (v.element() == c) {
					graph.removeVertex(v);
					break;
				}
		} catch (InvalidVertexException e) {e.printStackTrace();}
				
	}
	
	public Iterable<Ciudad> ciudades() {
		PositionList<Ciudad> iterable = new DoubleLinkedList<Ciudad>();
		
		for (Vertex<Ciudad> v : graph.vertices())
			iterable.addLast(v.element());
		
		return iterable;
	}
	
	public void mostrarRutas() {
		try {
			for (Edge<Float> e : graph.edges())
				System.out.println("<" + graph.endvertices(e)[0].element().getName() + ">-[" + e.element()
						+ "]->" + graph.endvertices(e)[1].element().getName() + ">");
		} catch (InvalidEdgeException e) {
			e.printStackTrace();
		}
	}
	
	public void insertarRuta(Ciudad c1, Ciudad c2, float dist) throws InvalidCityException {
		Vertex<Ciudad> v1 = null, v2 = null;
		
		try {
			for (Vertex<Ciudad> v : graph.vertices())
				if (v.element() == c1)
					v1 = v;
				else if (v.element() == c2)
					v2 = v;
				else if (v1 != null && v2 != null)
					break;
			
			if (v1 == null || v2 == null)
				throw new InvalidCityException("Una o ambas ciudades inexistentes. ");
			
			graph.insertEdge(v1, v2, dist);
			
		} catch (InvalidVertexException e) {e.printStackTrace();}
			
	}
	
	public void eliminarRuta(Ciudad c1, Ciudad c2) throws InvalidCityException {
		Vertex<Ciudad> [] adj; 
		Edge<Float> road = null;
		
		try {
			for (Edge<Float> e : graph.edges()) {
				adj = graph.endvertices(e);
				if ( (adj[0].element() == c1 && adj[1].element() == c2) ||
						(adj[0].element() == c2 && adj[1].element() == c1) ) {
					road = e;
					break;
				}
			}
			
			if (road == null)
				throw new InvalidCityException("Una o ambas ciudades inexistentes. ");
			
			graph.removeEdge(road);
			
		} catch (InvalidEdgeException e) {e.printStackTrace();}
	}
	
	/**
	 * Retorna el camino MÁS ECONÓMICO entre c1 y c3. En un mapa verdaderamente grande debería usarse otro algoritmo.
	 * Podría haberse implementado con un bfs + mapeo externo, pero tiene fallas con los VérticesDecorables
	 * @param c1 Ciudad de origen
	 * @param c2 Ciudad punto intermedio
	 * @param c3 Ciudad de destino
	 * @return Camino c1-c2-c3
	 * @throws InvalidCityException si alguna ciudad no pertenece al mapa.
	 */
	public PositionList<Ciudad> camino(Ciudad c1, Ciudad c2, Ciudad c3) throws InvalidCityException{
		Solucion<PositionList<Ciudad>> s;
		Solucion<Float> costo_minimo = new Solucion<Float>();
		PositionList<Ciudad> camino_c1_c2, camino_c2_c3;
		Vertex<Ciudad> v1, v2, v3;
		Object estado, visitado;
		
		estado = new Object();
		visitado = new Object();

		camino_c1_c2 = new DoubleLinkedList<Ciudad>();
		camino_c2_c3 = new DoubleLinkedList<Ciudad>();

		v1 = hallarVertice(c1);
		v2 = hallarVertice(c2);
		v3 = hallarVertice(c3);
		
		if (v1 == null || v2 == null || v3 == null)
			throw new InvalidCityException("Ciudad inexistente. ");
		
		s = new Solucion<PositionList<Ciudad>>();
		v3.put(estado, visitado); //Asumo c3 visitada para evitar caminos con ciclos.
		costo_minimo.setSolucion(Float.MAX_VALUE);
		hallarCamino(v1, v2, new DoubleLinkedList<Ciudad>(), 0f, s, costo_minimo, estado, visitado);
		camino_c1_c2 = s.getSolucion();
		v3.remove(estado);
		
		s = new Solucion<PositionList<Ciudad>>();
		v1.put(estado, visitado);
		costo_minimo.setSolucion(Float.MAX_VALUE);
		hallarCamino(v2, v3, new DoubleLinkedList<Ciudad>(), 0f, s, costo_minimo, estado, visitado);
		camino_c2_c3 = s.getSolucion();
		v1.remove(estado);
		
		try {
			if (camino_c1_c2 != null && camino_c2_c3 != null && !camino_c1_c2.isEmpty() && !camino_c2_c3.isEmpty()) {
				camino_c2_c3.remove(camino_c2_c3.first());
				while (!camino_c2_c3.isEmpty()) {
					camino_c1_c2.addLast(camino_c2_c3.remove(camino_c2_c3.first()));
				}
			}
		} catch (InvalidPositionException | EmptyListException e) {e.printStackTrace();}
		
		return camino_c1_c2;
	}

	private Vertex<Ciudad> hallarVertice(Ciudad c) {
		for (Vertex<Ciudad> v : graph.vertices())
			if (v.element() == c)
				return v;
		return null;
	}

	private void hallarCamino(Vertex<Ciudad> origen, Vertex<Ciudad> destino, PositionList<Ciudad> camino_actual, float costo_actual,
			Solucion<PositionList<Ciudad>> s, Solucion<Float> costo_minimo, Object estado, Object visitado) {
		PositionList<Ciudad> new_camino;
		Vertex<Ciudad> v;
		try {
			
			camino_actual.addLast(origen.element());
			origen.put(estado, visitado);
			
			if (origen == destino) {
				if (costo_actual < costo_minimo.getSolucion()) {
					costo_minimo.setSolucion(costo_actual);
					new_camino = new DoubleLinkedList<Ciudad>();
					for (Ciudad c : camino_actual)
						new_camino.addLast(c);
					s.setSolucion(new_camino);
				}
			}
			else {
				for (Edge<Float> e : graph.succesorEdges(origen)) {
					v = graph.opposite(origen, e);
					if (v.get(estado) == null)
						hallarCamino(v, destino, camino_actual, costo_actual+e.element(), s, costo_minimo, estado, visitado);
				}
			}
			
			camino_actual.remove(camino_actual.last());
			origen.remove(estado);
			
		} catch (InvalidPositionException | EmptyListException | InvalidVertexException | InvalidEdgeException e) {
			e.printStackTrace();
		}
	}
	
	public int cantidad_caminos(Ciudad c1, Ciudad c2) throws InvalidCityException {
		Vertex<Ciudad> v1, v2;
		Object estado, visitado;
		Solucion<Integer> s = new Solucion<Integer>();
		s.setSolucion(0);
		
		estado = new Object();
		visitado = new Object();
		
		v1 = hallarVertice(c1);
		v2 = hallarVertice(c2);
		
		if (v1 == null || v2 == null)
			throw new InvalidCityException("Una o ambas ciudades inexistentes. ");
		
		hallarCantidadCaminos(v1, v2, s, estado, visitado);
		
		return s.getSolucion();
	}

	private void hallarCantidadCaminos(Vertex<Ciudad> origen, Vertex<Ciudad> destino, Solucion<Integer> s, Object estado,
			Object visitado) {
		Vertex<Ciudad> v;
		
		origen.put(estado, visitado);
		
		try {
			if (origen == destino) {
				s.setSolucion(s.getSolucion() + 1);
			}
			else {
				for (Edge<Float> e : graph.succesorEdges(origen)) {
					v = graph.opposite(origen, e);
					if (v.get(estado) == null)
						hallarCantidadCaminos(v, destino, s, estado, visitado);
				}
			}
		} catch (InvalidVertexException | InvalidEdgeException e) {e.printStackTrace();}
		
		origen.remove(estado);
	}
	
}
