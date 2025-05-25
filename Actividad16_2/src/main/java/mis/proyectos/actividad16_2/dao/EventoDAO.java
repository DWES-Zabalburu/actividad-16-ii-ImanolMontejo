/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package mis.proyectos.actividad16_2.dao;

import java.util.List;
import mis.proyectos.actividad16_2.modelo.Evento;

/**
 *
 * @author monte
 */
public interface EventoDAO {
    Evento añadirEvento(Evento nuevo);
    void eliminarEvento(int id);
    void modificarEvento(Evento evento);
    List<Evento> getEventos();
    Evento getEvento(int id);
}
