import java.util.ArrayList;

public class Ruta {

    private Floyd floyd;
    private Grafo grafo;

    public Ruta(Floyd floyd, Grafo grafo) {
        this.floyd = floyd;
        this.grafo = grafo;
    }

    public ArrayList<String> obtenerCamino(String nombreOrigen, String nombreDestino) {
        ArrayList<String> camino = new ArrayList<>();

        int origen  = grafo.buscarCiudad(nombreOrigen);
        int destino = grafo.buscarCiudad(nombreDestino);

        if (origen == -1) {
            System.out.println("Ciudad origen '" + nombreOrigen + "' no encontrada.");
            return camino;
        }
        if (destino == -1) {
            System.out.println("Ciudad destino '" + nombreDestino + "' no encontrada.");
            return camino;
        }

        if (floyd.getDistancia(origen, destino) == Grafo.INFINITO) {
            System.out.println("No existe camino de " + nombreOrigen + " a " + nombreDestino);
            return camino;
        }

        int actual = origen;
        camino.add(grafo.getCiudad(actual)); 

        while (actual != destino) {
            actual = floyd.getSiguiente(actual, destino);

            if (actual == -1) {
                System.out.println("Error: camino interrumpido.");
                return new ArrayList<>();
            }

            camino.add(grafo.getCiudad(actual));
        }

        return camino;
    }

    public void mostrarRuta(String nombreOrigen, String nombreDestino) {
        int origen  = grafo.buscarCiudad(nombreOrigen);
        int destino = grafo.buscarCiudad(nombreDestino);

        if (origen == -1 || destino == -1) {
            System.out.println("Una de las ciudades no existe.");
            return;
        }

        int distancia = floyd.getDistancia(origen, destino);

        System.out.println("\n=== Ruta de " + nombreOrigen + " a " + nombreDestino + " ===");

        if (distancia == Grafo.INFINITO) {
            System.out.println("No existe ruta entre estas ciudades.");
            return;
        }

        System.out.println("Distancia minima: " + distancia);

        // Obtener y mostrar el camino
        ArrayList<String> camino = obtenerCamino(nombreOrigen, nombreDestino);

        System.out.print("Camino: ");
        for (int i = 0; i < camino.size(); i++) {
            System.out.print(camino.get(i));
            if (i < camino.size() - 1) {
                System.out.print(" -> ");
            }
        }
        System.out.println();

        mostrarIntermedias(camino);
    }

    private void mostrarIntermedias(ArrayList<String> camino) {
        if (camino.size() <= 2) {
            System.out.println("Sin ciudades intermedias (conexion directa).");
            return;
        }

        System.out.print("Ciudades intermedias: ");
        for (int i = 1; i < camino.size() - 1; i++) {
            System.out.print(camino.get(i));
            if (i < camino.size() - 2) {
                System.out.print(", ");
            }
        }
        System.out.println();
    }
}
