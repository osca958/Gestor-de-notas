import org.junit.jupiter.api.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GestorTest {
    static Gestor miGestor;

    @BeforeEach
    public void configuracionTest() {
        miGestor = new Gestor();
        String nombre = "Oscar";
        ArrayList<Float> notas = new ArrayList<>();
        notas.add(10f);
        notas.add(10f);
        notas.add(10f);
        miGestor.añadirAlumno(nombre, notas);
    }

    @Test
    public void menuTest(){
        String menu = "Escoge una opción: \n" +
                "1. Añadir alumno. \n" +
                "2. Eliminar alumno\n" +
                "3. Añadir una nota. \n" +
                "4. Consultar notas alumno. \n" +
                "5. Salir.";
        assertEquals(menu, miGestor.menu());
    }

    @Test
    public void añadirAlumnoCorrectamenteTest(){
        String resultado = "Alumno Miguel añadido con éxito.";
        String nombre = "Miguel";
        ArrayList<Float> notas = new ArrayList<>();
        notas.add(8f); //Pongo la f para que lo interprete como un float
        notas.add(7f);
        notas.add(6f);
        assertEquals(resultado, miGestor.añadirAlumno(nombre, notas));
    }

    @Test
    public void añadirAlumnoRepetidoTest(){
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
    public void añadirNotaCorrectamenteTest(){
        String nombre = "Oscar";
        miGestor.añadirNota(nombre, 10f);
        assertTrue(miGestor.notasAlumnos.get(nombre).size() == 4);
    }

    @Test
    public void mostrarNotasAlumnoTest(){
        String resultado = "Notas del alumno Oscar : 10.0 10.0 10.0";
        assertEquals(resultado, miGestor.mostrarNotas("Oscar"));
    }
    @Test
    public void eliminarAlumnoCorrectamenteTest() {
        String resultado = "Alumno Oscar eliminado correctamente.";
        assertEquals(resultado, miGestor.eliminarAlumno("Oscar"));
    }

    @AfterEach
    public void cerrarConfiguracionesTest(){
        miGestor.notasAlumnos.clear();
    }
}