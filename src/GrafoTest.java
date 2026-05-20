import org.junit.Test;
import static org.junit.Assert.*;

public class GrafoTest {


    @Test
    public void testAgregarCiudad() {
        Grafo g = new Grafo(5);
        g.agregarCiudad("Guatemala");
        // Debe encontrar la ciudad recien agregada
        assertNotEquals(-1, g.buscarCiudad("Guatemala"));
    }

    @Test
    public void testCiudadNoExiste() {
        Grafo g = new Grafo(5);
        // Una ciudad que no fue agregada debe devolver -1
        assertEquals(-1, g.buscarCiudad("Xela"));
    }

    @Test
    public void testAgregarConexion() {
        Grafo g = new Grafo(5);
        g.agregarCiudad("Guatemala");
        g.agregarCiudad("Xela");
        g.agregarConexion("Guatemala", "Xela", 5);

        int i = g.buscarCiudad("Guatemala");
        int j = g.buscarCiudad("Xela");

        // La distancia de Guatemala a Xela debe ser 5
        assertEquals(5, g.getDistancia()[i][j]);
    }

    @Test
    public void testEliminarConexion() {
        Grafo g = new Grafo(5);
        g.agregarCiudad("Guatemala");
        g.agregarCiudad("Xela");
        g.agregarConexion("Guatemala", "Xela", 5);
        g.eliminarConexion("Guatemala", "Xela");

        int i = g.buscarCiudad("Guatemala");
        int j = g.buscarCiudad("Xela");

        // Despues de eliminar debe ser INFINITO
        assertEquals(Grafo.INFINITO, g.getDistancia()[i][j]);
    }

    @Test
    public void testFloydDistanciaDirecta() {
        Grafo g = new Grafo(5);
        g.agregarCiudad("A");
        g.agregarCiudad("B");
        g.agregarConexion("A", "B", 4);

        Floyd floyd = new Floyd(g);

        // La distancia minima de A a B debe ser 4
        assertEquals(4, floyd.getDistancia(0, 1));
    }

    @Test
    public void testFloydDistanciaIndirecta() {
        Grafo g = new Grafo(5);
        g.agregarCiudad("A");
        g.agregarCiudad("B");
        g.agregarCiudad("C");
        g.agregarConexion("A", "B", 3);
        g.agregarConexion("B", "C", 2);
        g.agregarConexion("A", "C", 10); // Directo es 10, por B es 5

        Floyd floyd = new Floyd(g);

        // Floyd debe encontrar que A->C por B es mas corto (3+2=5)
        assertEquals(5, floyd.getDistancia(0, 2));
    }
}