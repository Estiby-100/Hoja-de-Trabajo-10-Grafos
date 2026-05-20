public class Floyd {

    private int[][] distMin;
    private int[][] siguiente;
    private int n;

    public Floyd(Grafo grafo) {
        this.n = grafo.getNumCiudades();
        this.distMin = new int[n][n];
        this.siguiente = new int[n][n];

        // Copiar la matriz original del grafo 
        int[][] original = grafo.getDistancia();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                distMin[i][j] = original[i][j];

                
                
                if (i != j && original[i][j] != Grafo.INFINITO) {
                    siguiente[i][j] = j;
                } else {
                    siguiente[i][j] = -1;
                }
            }
        }

        
        ejecutar();
    }


    private void ejecutar() {
        // k = ciudad intermedia que estamos probando
        for (int k = 0; k < n; k++) {
            // i = ciudad de origen
            for (int i = 0; i < n; i++) {
                // j = ciudad de destino
                for (int j = 0; j < n; j++) {

                    // calcular si hay camino de i a k y de k a j
                    if (distMin[i][k] != Grafo.INFINITO && distMin[k][j] != Grafo.INFINITO) {

                        int nuevaDistancia = distMin[i][k] + distMin[k][j];

                        // Si pasar por k es más corto, actualizamos
                        if (nuevaDistancia < distMin[i][j]) {
                            distMin[i][j] = nuevaDistancia;
                            siguiente[i][j] = siguiente[i][k];
                        }
                    }
                }
            }
        }
    }


    /**
     * Muestra la matriz de distancias mínimas en consola.
     */
    public void mostrarDistancias(String[] ciudades) {
        System.out.println("\n=== Matriz de Distancias Mínimas (Floyd-Warshall) ===");

        System.out.print("             ");
        for (int j = 0; j < n; j++) {
            System.out.print(ciudades[j] + "\t");
        }
        System.out.println();

        for (int i = 0; i < n; i++) {
            System.out.print(ciudades[i] + "\t\t");
            for (int j = 0; j < n; j++) {
                if (distMin[i][j] == Grafo.INFINITO) {
                    System.out.print("INF\t");
                } else {
                    System.out.print(distMin[i][j] + "\t");
                }
            }
            System.out.println();
        }
    }


    // ──────────────────────────────────────────
    // Getters
    // ──────────────────────────────────────────

    public int[][] getDistMin() {
        return distMin;
    }

    public int[][] getSiguiente() {
        return siguiente;
    }

    public int getDistancia(int i, int j) {
        return distMin[i][j];
    }

    public int getSiguiente(int i, int j) {
        return siguiente[i][j];
    }

    public int getN() {
        return n;
    }
}