import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class GestorTest {
    static Gestor miGestor;

    @BeforeEach
    public void configuracion() {
        miGestor = new Gestor();
        String nombre = "Oscar";
        ArrayList<Float> notas = new ArrayList<>();
        notas.add(10f);
        notas.add(10f);
        notas.add(10f);
        miGestor.añadirAlumno(nombre, notas);
    }

    @Test
    public void menu() {
        String menu = "Escoge una opción: \n" +
                "1. Añadir alumno. \n" +
                "2. Eliminar alumno\n" +
                "3. Añadir una nota. \n" +
                "4. Modificar una nota. \n" +
                "5. Eliminar una nota. \n" +
                "6. Consultar notas alumno. \n" +
                "7. Consultar media alumno. \n" +
                "8. Salir.";
        assertEquals(menu, miGestor.menu());
    }

    @Test
    public void añadirAlumnoCorrectamente() {
        String resultado = "Alumno Miguel añadido con éxito.";
        String nombre = "Miguel";
        ArrayList<Float> notas = new ArrayList<>();
        notas.add(8f); //Pongo la f para que lo interprete como un float
        notas.add(7f);
        notas.add(6f);
        assertEquals(resultado, miGestor.añadirAlumno(nombre, notas));
    }

    @Test
    public void añadirAlumnoRepetido() {
        String nombre = "Fran";
        ArrayList<Float> notas = new ArrayList<>();
        notas.add(10f); //Pongo la f para que lo interprete como un float
        notas.add(10f);
        notas.add(10f);
        miGestor.añadirAlumno(nombre, notas);
        assertThrows(IllegalArgumentException.class, () -> {
            miGestor.añadirAlumno(nombre, notas);
        }); //Esta función no la conocemos
    }

    @Test
    public void añadirNotaCorrectamente() {
        String nombre = "Oscar";
        miGestor.añadirNota(nombre, 10f);
        assertTrue(miGestor.notasAlumnos.get(nombre).size() == 4);
    }

    @Test
    public void mostrarNotasAlumnoNoExistente() {
        /*
        notasAlumnos = {
            "Oscar": [10.0 10.0 10.0]
        }
         */
        assertThrows(IllegalArgumentException.class, () -> miGestor.mostrarNotas("Marisa"));
    }

    @Test
    public void mostrarNotasAlumno() {
        String resultado = "Notas del alumno Oscar : 10.0 10.0 10.0";
        assertEquals(resultado, miGestor.mostrarNotas("Oscar"));
    }

    @Test
    public void eliminarAlumnoCorrectamente() {
        String resultado = "Alumno Oscar eliminado correctamente.";
        assertEquals(resultado, miGestor.eliminarAlumno("Oscar"));
    }

    @Test
    public void eliminarNotaAlumnoNoExistente() {
        float resultado = 10f;
        assertEquals(resultado, miGestor.eliminarNota("Oscar", 0));
    }

    @Test
    public void eliminarNotaNoExistente() {
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            miGestor.eliminarNota("Oscar", 3);
        });
    }

    @Test
    public void comrpobarMediasPorAlumno() {
        HashMap<String, Float> resultado = new HashMap<>();
        resultado.put("Oscar", 10f);
        assertEquals(resultado, miGestor.consultarMediasPorAlumno());
    }

    @AfterEach
    public void cerrarConfiguraciones() {
        miGestor.notasAlumnos.clear();
    }
}