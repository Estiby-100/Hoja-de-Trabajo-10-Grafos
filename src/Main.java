public class Main {

    public static void main(String[] args) {

        Grafo grafo = new Grafo(50);

        
        boolean cargado = ArchivoUtil.cargarGrafo("grafo.txt", grafo);

        
        if (!cargado) {
            System.out.println("No se pudo cargar el archivo");
            return;
        }

        ConsolaUtil consola = new ConsolaUtil(grafo);
        consola.mostrarMenu();
    }
}
