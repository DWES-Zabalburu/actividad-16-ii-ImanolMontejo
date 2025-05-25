/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package mis.proyectos.actividad16_2.dao;

import java.util.List;
import mis.proyectos.actividad16_2.modelo.Persona;

/**
 *
 * @author monte
 */
public interface PersonaDAO {
    Persona añadirPersona(Persona nueva);
    void eliminarPersona(int id);
    void modificarPersona(Persona modificar);
    List<Persona> getPersonas();
    Persona getPersona(int id);
    Persona getPersona(String dni);
}
