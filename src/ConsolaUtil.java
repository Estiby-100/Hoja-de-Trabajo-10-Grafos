import java.util.Scanner;

public class ConsolaUtil {

    private Grafo grafo;
    private Floyd floyd;
    private Ruta ruta;
    private CentroGrafo centro;
    private Scanner scanner;

    private boolean necesitaRecalcular;


    public ConsolaUtil(Grafo grafo) {
        this.grafo = grafo;
        this.scanner = new Scanner(System.in);
        this.necesitaRecalcular = true;

        recalcular();
    }

    private void recalcular() {
        floyd  = new Floyd(grafo);
        ruta   = new Ruta(floyd, grafo);
        centro = new CentroGrafo(floyd, grafo);
        necesitaRecalcular = false;
        System.out.println("Rutas recalculadas correctamente.");
    }


    public void mostrarMenu() {
        int opcion = -1;

        while (opcion != 0) {
            System.out.println("   SISTEMA DE RUTAS GUATEMALA");
            System.out.println("1. Consultar ruta entre dos ciudades");
            System.out.println("2. Mostrar matriz de adyacencia");
            System.out.println("3. Mostrar matriz de distancias minimas");
            System.out.println("4. Agregar conexion");
            System.out.println("5. Eliminar conexion");
            System.out.println("6. Mostrar centro del grafo");
            System.out.println("7. Recalcular rutas");
            System.out.println("0. Salir");
            System.out.print("Selecciona una opcion: ");

            opcion = Integer.parseInt(scanner.nextLine().trim());

            switch (opcion) {
                case 1: consultarRuta();       break;
                case 2: grafo.mostrarMatriz(); break;
                case 3: floyd.mostrarDistancias(grafo.getCiudades()); break;
                case 4: agregarConexion();     break;
                case 5: eliminarConexion();    break;
                case 6: centro.mostrarCentro(); break;
                case 7: recalcular();          break;
                case 0: System.out.println("fin"); break;
                default: System.out.println("Opcion no valida."); break;
            }
        }
    }

    private void consultarRuta() {
        System.out.print("Ciudad de origen: ");
        String origen = scanner.nextLine().trim();

        System.out.print("Ciudad de destino: ");
        String destino = scanner.nextLine().trim();

        ruta.mostrarRuta(origen, destino);
    }


    private void agregarConexion() {
        System.out.print("Ciudad de origen: ");
        String origen = scanner.nextLine().trim();

        System.out.print("Ciudad de destino: ");
        String destino = scanner.nextLine().trim();

        System.out.print("Peso (distancia): ");
        int peso;
        try {
            peso = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Peso invalido.");
            return;
        }

        
        if (grafo.buscarCiudad(origen) == -1) {
            grafo.agregarCiudad(origen);
        }
        if (grafo.buscarCiudad(destino) == -1) {
            grafo.agregarCiudad(destino);
        }

        grafo.agregarConexion(origen, destino, peso);


        necesitaRecalcular = true;
        System.out.println("Recuerda recalcular rutas (opcion 7) para ver los cambios.");
    }

    private void eliminarConexion() {
        System.out.print("Ciudad de origen: ");
        String origen = scanner.nextLine().trim();

        System.out.print("Ciudad de destino: ");
        String destino = scanner.nextLine().trim();

        grafo.eliminarConexion(origen, destino);


        necesitaRecalcular = true;
        System.out.println("Recuerda recalcular rutas (opcion 7) para ver los cambios.");
    }
}