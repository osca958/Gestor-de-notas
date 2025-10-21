/**
 * Sistema gestor de notas
 *
 * @author Oscar Coque
 * @version 1.0.0
 */

/*
Sistema gestor de notas:
Tenemos un diccionario de (String[nombre alumno],Arraylist(double)[notas curso]).
Nuestro producto ha de permitir hacer lo siguiente:
* Añadir una nota a un alumno
* Añadir un alumno al diccionario
* Modificar una nota de un alumno
* Eliminar una nota de un alumno
* Eliminar un alumno del diccionario
* Listar las notas de un alumno
* Listar los alumnos con su nota media
* Salir

 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/*
notasAlumnos = {
"Jordi": [10.0 10.0 10.0]
"Oscar": [7.0 7.0 7.0]
"Marisa": [8.0 8.0 8.0]
}
 */

public class Gestor {
    HashMap<String, ArrayList<Float>> notasAlumnos;

    public Gestor() {
        notasAlumnos = new HashMap<>();
    }

    /**
     * Función que construye el menú de la aplicación.
     * @return String que contiene el menú de la aplicación a imprimir
     */
    public String menu() {
        String menu = "Escoge una opción: \n" +
                "1. Añadir alumno. \n" +
                "2. Eliminar alumno\n" +
                "3. Añadir una nota. \n" +
                "4. Modificar una nota. \n" +
                "5. Eliminar una nota. \n" +
                "6. Consultar notas alumno. \n" +
                "7. Consultar media alumno. \n" +
                "8. Salir.";
        return menu;
    }

    /**
     * Funcion que comprueba si existe alumno y si no existe lo añade al diccionario
     * @param nombre  Nombre del alumno
     * @param notas  Lista de notas del alummno
     * @throws IllegalArgumentException  El alumno ya existe en el sistema
     */
    public String añadirAlumno(String nombre, ArrayList<Float> notas) {
        if (notasAlumnos.containsKey(nombre)) {
            throw new IllegalArgumentException("El alumno " + nombre + " ya existe. ");
        }
        notasAlumnos.put(nombre, notas);
        return "Alumno " + nombre + " añadido con éxito.";
    }

    /**
     * Función que añade una nota para un alumno existente
     * @param nombre  Nombre del alumno
     * @param nota  Nota del alumno
     * @throws IllegalArgumentException  Alumno no existente en sistema
     */

    public String añadirNota(String nombre, Float nota) {
        if (!notasAlumnos.containsKey(nombre)) {
            throw new IllegalArgumentException("El alumno " + nombre + " no existe.");
        }
        notasAlumnos.get(nombre).add(nota);
        return "Nota " + nota + " añadida al alumno " + nombre + " con éxito";

    }

    /**
     * Función para mostrar las notas de un alumno
     * @param nombre  Nombre del alumno
     * @return Mensaje de confirmación
     * @throws IllegalArgumentException  Alumno no existente en el sistema
     */

    public String mostrarNotas(String nombre) {
        if (!notasAlumnos.containsKey(nombre)) {
            throw new IllegalArgumentException("El alumno " + nombre + " no existe.");
        }
        String resultado = "Notas del alumno " + nombre + " : ";
        for (int i = 0; i < notasAlumnos.get(nombre).size(); i++) {
            resultado += notasAlumnos.get(nombre).get(i) + " ";
        }
        resultado = resultado.stripTrailing();
        return resultado;
    }

    /**
     * Función para eliminar alumno del sistema
     * @param nombre
     * @return Mensaje de confirmación
     * @throws IllegalArgumentException  Alumno no existente en el sistema
     */

    public String eliminarAlumno(String nombre) {
        //Primero compruebo si el alumno existe en el notasAlumnos
        //Si no existe lanzo excepcion
        //Si existe elimino el alumno y devuelvo string de confirmacion
        if (notasAlumnos.remove(nombre) == null) {
            throw new IllegalArgumentException("El alumno " + nombre + " no existe en el sistema.");
        }
        return "Alumno " + nombre + " eliminado correctamente.";
    }

    /**
     * Función para eliminar una nota de un alumno
     * @param nombre Nombre del alumno
     * @param posicion Posición de la nota a eliminar
     * @return nota eliminada
     * @throws IllegalArgumentException Alumno no existente en el sistema
     * @throws ArrayIndexOutOfBoundsException Nota no existente en la lista del alumno
     */

    public float eliminarNota(String nombre, int posicion) {
        if (!notasAlumnos.containsKey(nombre)) {
            throw new IllegalArgumentException("El alumno " + nombre + " no existe en el sistema.");
        } else if (notasAlumnos.get(nombre).size() <= posicion) {
            throw new ArrayIndexOutOfBoundsException("No existe la posición " + posicion + " en la lista de notas.");
        }
        return notasAlumnos.get(nombre).remove(posicion);
    }

    /**
     * Función para modificar una nota de un alumno
     * @param nombre Nombre del alumno
     * @param posicion Posición de la nota a eliminar
     * @param nuevaNota nota para modificar la anterior
     * @return valor nota antes de modificar
     * @throws IllegalArgumentException Alumno no existente en el sistema
     * @throws ArrayIndexOutOfBoundsException Nota no existente en la lista del alumno
     */

    public float modificarNota(String nombre, int posicion, float nuevaNota) {
        if (!notasAlumnos.containsKey(nombre)) {
            throw new IllegalArgumentException("El alumno " + nombre + " no existe en el sistema.");
        } else if (notasAlumnos.get(nombre).size() <= posicion) {
            throw new ArrayIndexOutOfBoundsException("No existe la posición " + posicion + " en la lista de notas.");
        }
        return notasAlumnos.get(nombre).set(posicion, nuevaNota);
    }

    /**
     * Función para mostrar los alumnos con su nota media
     * @return Diccionario con los alumnos y la media de sus notas
     */

    public HashMap<String, Float> consultarMediasPorAlumno() {
        HashMap<String, Float> mediaNotas = new HashMap<>();
        for (String alumno : notasAlumnos.keySet()) {
            ArrayList<Float> notas = notasAlumnos.get(alumno);
            float total = 0;
            for (float nota : notas) {
                total += nota;
            }
            mediaNotas.put(alumno, total / notas.size());
        }
        return mediaNotas;
    }
}