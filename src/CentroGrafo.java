public class CentroGrafo {

    private Floyd floyd;
    private Grafo grafo;
    private int[] excentricidades;
    private int indiceCentro;

    public CentroGrafo(Floyd floyd, Grafo grafo) {
        this.floyd = floyd;
        this.grafo = grafo;

        int n = grafo.getNumCiudades();
        this.excentricidades = new int[n];
        this.indiceCentro = -1;

        calcularCentro();
    }

    private void calcularCentro() {
        int n = grafo.getNumCiudades();
        int[][] distMin = floyd.getDistMin();

        
        for (int i = 0; i < n; i++) {
            int maxColumna = 0;

            for (int w = 0; w < n; w++) {
                // Si hay un camino y es mayor al maximo actual, actualizar
                if (distMin[w][i] != Grafo.INFINITO) {
                    if (distMin[w][i] > maxColumna) {
                        maxColumna = distMin[w][i];
                    }
                } else {
                    maxColumna = Grafo.INFINITO;
                    break;
                }
            }

            excentricidades[i] = maxColumna;
        }

        int minExcentricidad = Grafo.INFINITO;

        for (int i = 0; i < n; i++) {
            if (excentricidades[i] < minExcentricidad) {
                minExcentricidad = excentricidades[i];
                indiceCentro = i;
            }
        }
    }

    public void mostrarCentro() {
        int n = grafo.getNumCiudades();
        String[] ciudades = grafo.getCiudades();

        System.out.println("Ciudad          Excentricidad");
        System.out.println("--------------------------------");

        for (int i = 0; i < n; i++) {
            String exc;
            if (excentricidades[i] == Grafo.INFINITO) {
              exc = "INF";
            } else {
                exc = String.valueOf(excentricidades[i]);
         }
            System.out.println(ciudades[i] + " - " + exc);
        }

        if (indiceCentro == -1) {
            System.out.println("No se pudo determinar el centro (grafo desconectado).");
        } else {
            System.out.println("Centro del grafo: " + ciudades[indiceCentro]);
            System.out.println("Excentricidad minima: " + excentricidades[indiceCentro]);
        }
    }

    public String getNombreCentro() {
        if (indiceCentro == -1) return "No determinado";
        return grafo.getCiudad(indiceCentro);
    }

    public int getIndiceCentro() {
        return indiceCentro;
    }

    public int[] getExcentricidades() {
        return excentricidades;
    }
}
