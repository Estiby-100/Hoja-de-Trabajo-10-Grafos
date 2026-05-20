import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ArchivoUtil {

    public static boolean cargarGrafo(String nombreArchivo, Grafo grafo) {
        BufferedReader lector = null;

        try {
            lector = new BufferedReader(new FileReader(nombreArchivo));
            String linea;
            int numeroLinea = 0;

            while ((linea = lector.readLine()) != null) {
                numeroLinea++;

                // Ignorar lineas vacias o comentarios (que empiecen con #)
                linea = linea.trim();
                if (linea.isEmpty() || linea.startsWith("#")) {
                    continue;
                }

                String[] partes = linea.split(",");
                
                if (partes.length != 3) {
                    System.out.println("Linea " + numeroLinea + " ignorada (formato incorrecto): " + linea);
                    continue;
                }

                String origen  = partes[0].trim();
                String destino = partes[1].trim();
                String pesoCrudo = partes[2].trim();

                
                int peso;
                try {
                    peso = Integer.parseInt(pesoCrudo);
                } catch (NumberFormatException e) {
                    System.out.println("Linea " + numeroLinea + " ignorada (peso invalido): " + linea);
                    continue;
                }

                if (grafo.buscarCiudad(origen) == -1) {
                    grafo.agregarCiudad(origen);
                }
                if (grafo.buscarCiudad(destino) == -1) {
                    grafo.agregarCiudad(destino);
                }

                grafo.agregarConexion(origen, destino, peso);
            }

            System.out.println("Archivo cargado correctamente: " + nombreArchivo);
            return true;

        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + nombreArchivo);
            System.out.println("Detalle: " + e.getMessage());
            return false;

        } finally {
            if (lector != null) {
                try {
                    lector.close();
                } catch (IOException e) {
                    System.out.println("Error al cerrar el archivo.");
                }
            }
        }
    }
}