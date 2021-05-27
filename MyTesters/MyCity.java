package MyTesters;

import Operaciones.Ciudad;
import Operaciones.Ciudades_con_rutas;
import Operaciones.InvalidCityException;
import TDALista.PositionList;

public class MyCity {
	
	protected static Ciudades_con_rutas map;
	protected static Ciudad [] c = new Ciudad[10];

	public static void main(String[] args) {
		crearMapa();
		map.mostrarRutas();
		test_caminos();
	}

	private static void test_caminos() {
		int x, y, z;
		Ciudad c1, c2, c3;
		
		try {
			x = 1; y = 2; z = 3;
			c1 = c[x-1];
			c2 = c[y-1];
			c3 = c[z-1];
			System.out.print("Camino " + c1.getName() + " - " + c2.getName() + " - " + c3.getName() + " >> ");
			printCamino(map.camino(c1, c2, c3));
			System.out.println("Cantidad de caminos : " + map.cantidad_caminos(c1, c3));
			
			x = 1; y = 4; z = 2;
			c1 = c[x-1];
			c2 = c[y-1];
			c3 = c[z-1];
			System.out.print("Camino " + c1.getName() + " - " + c2.getName() + " - " + c3.getName() + " >> ");
			printCamino(map.camino(c1, c2, c3));
			System.out.println("Cantidad de caminos : " + map.cantidad_caminos(c1, c3));
			
			x = 1; y = 2; z = 8;
			c1 = c[x-1];
			c2 = c[y-1];
			c3 = c[z-1];
			System.out.print("Camino " + c1.getName() + " - " + c2.getName() + " - " + c3.getName() + " >> ");
			printCamino(map.camino(c1, c2, c3));
			System.out.println("Cantidad de caminos : " + map.cantidad_caminos(c1, c3));
			
			x = 1; y = 2; z = 9;
			c1 = c[x-1];
			c2 = c[y-1];
			c3 = c[z-1];
			System.out.print("Camino " + c1.getName() + " - " + c2.getName() + " - " + c3.getName() + " >> ");
			printCamino(map.camino(c1, c2, c3));
			System.out.println("Cantidad de caminos : " + map.cantidad_caminos(c1, c3));
			
			x = 1; y = 3; z = 1;
			c1 = c[x-1];
			c2 = c[y-1];
			c3 = c[z-1];
			System.out.print("Camino " + c1.getName() + " - " + c2.getName() + " - " + c3.getName() + " >> ");
			printCamino(map.camino(c1, c2, c3));
			System.out.println("Cantidad de caminos : " + map.cantidad_caminos(c1, c3));
			
			
		} catch (InvalidCityException e) {
			e.printStackTrace();
		}
	}

	private static void printCamino(PositionList<Ciudad> camino) {
		for (Ciudad c : camino)
			System.out.print(c.getName() + "->");
		System.out.println();
	}

	private static void crearMapa() {
		map = new Ciudades_con_rutas();
		
		//Crear ciudades:
		for (int i = 0; i < 9; i++) {
			c[i] = new Ciudad("c"+(i+1));
			map.insertarCiudad(c[i]);
		}
		
		//Crear rutas:
		int a, b;
		float dist;
		
		try {
			a = 1; b = 2; dist = 63;
			map.insertarRuta(c[a-1], c[b-1], dist);
			a = 1; b = 4; dist = 36;
			map.insertarRuta(c[a-1], c[b-1], dist);
			a = 1; b = 3; dist = 530;
			map.insertarRuta(c[a-1], c[b-1], dist);
			a = 4; b = 5; dist = 22;
			map.insertarRuta(c[a-1], c[b-1], dist);
			a = 5; b = 2; dist = 69;
			map.insertarRuta(c[a-1], c[b-1], dist);
			a = 5; b = 1; dist = 296;
			map.insertarRuta(c[a-1], c[b-1], dist);
			a = 2; b = 6; dist = 56;
			map.insertarRuta(c[a-1], c[b-1], dist);
			a = 2; b = 3; dist = 89;
			map.insertarRuta(c[a-1], c[b-1], dist);
			a = 6; b = 7; dist = 135;
			map.insertarRuta(c[a-1], c[b-1], dist);
			a = 6; b = 3; dist = 42;
			map.insertarRuta(c[a-1], c[b-1], dist);
			a = 3; b = 8; dist = 209;
			map.insertarRuta(c[a-1], c[b-1], dist);
			a = 3; b = 5; dist = 122;
			map.insertarRuta(c[a-1], c[b-1], dist);
		} catch (InvalidCityException e) {e.printStackTrace();}
	}

}
