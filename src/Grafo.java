public class Grafo {

    // Constante que representa que no hay conexión
    public static final int INFINITO = 9999;

    
    private int numCiudades;
    private String[] ciudades;
    private int[][] distancia;
    private int contadorCiudades;

    public Grafo(int maxCiudades) {
        this.numCiudades = maxCiudades;
        this.ciudades = new String[maxCiudades];
        this.distancia = new int[maxCiudades][maxCiudades];
        this.contadorCiudades = 0;

        // Llenar la matriz
        for (int i = 0; i < maxCiudades; i++) {
            for (int j = 0; j < maxCiudades; j++) {
                if (i == j) {
                    distancia[i][j] = 0;       // La distancia de una ciudad a sí misma es 0
                } else {
                    distancia[i][j] = INFINITO;
                }
            }
        }
    }
    
    public int agregarCiudad(String nombre) {
        
        if (buscarCiudad(nombre) != -1) { // Verificar si la ciudad ya existe
            System.out.println("La ciudad '" + nombre + "' ya existe.");
            return -1; // Retorna -1 para indicar que no se agregó la ciudad
        }

        if (contadorCiudades >= numCiudades) {
            System.out.println("No hay espacio para más ciudades.");
            return -1;
        }

        ciudades[contadorCiudades] = nombre;
        contadorCiudades++;
        return contadorCiudades - 1;
    }

    public int buscarCiudad(String nombre) {
        for (int i = 0; i < contadorCiudades; i++) {
            if (ciudades[i].equalsIgnoreCase(nombre)) {
                return i;
            }
        }
        return -1;
    }

    public void agregarConexion(String origen, String destino, int peso) {
        int i = buscarCiudad(origen);
        int j = buscarCiudad(destino);

        if (i == -1) {
            System.out.println("Ciudad origen '" + origen + "' no encontrada.");
            return;
        }
        if (j == -1) {
            System.out.println("Ciudad destino '" + destino + "' no encontrada.");
            return;
        }
        if (peso <= 0) {
            System.out.println("El peso debe ser mayor a 0.");
            return;
        }

        distancia[i][j] = peso;
        System.out.println("Conexión agregada: " + origen + " -> " + destino + " (peso: " + peso + ")");
    }

    public void eliminarConexion(String origen, String destino) {
        int i = buscarCiudad(origen);
        int j = buscarCiudad(destino);

        if (i == -1 || j == -1) {
            System.out.println("Una de las ciudades no existe.");
            return;
        }

        distancia[i][j] = INFINITO;
        System.out.println("Conexión eliminada: " + origen + " -> " + destino);
    }


    public void mostrarMatriz() {
        System.out.println("Matriz de Adyacencia");

        // Encabezado con nombres de ciudades
        System.out.print("             ");
        for (int j = 0; j < contadorCiudades; j++) {
            System.out.print(ciudades[j] + "\t");
    }
    System.out.println();

        // Filas de la matriz
        for (int i = 0; i < contadorCiudades; i++) {
            System.out.print(ciudades[i] + "\t\t");
            for (int j = 0; j < contadorCiudades; j++) {
                if (distancia[i][j] == INFINITO) {
                    System.out.print("INF\t");
                } else {
                    System.out.print(distancia[i][j] + "\t");
                }
            }   
            System.out.println();
        }
    }

    public int getNumCiudades() {
        return contadorCiudades;
    }

    public String[] getCiudades() {
        return ciudades;
    }

    public int[][] getDistancia() {
        return distancia;
    }

    public String getCiudad(int indice) {
        if (indice >= 0 && indice < contadorCiudades) {
            return ciudades[indice];
        }
        return null;
    }
}
