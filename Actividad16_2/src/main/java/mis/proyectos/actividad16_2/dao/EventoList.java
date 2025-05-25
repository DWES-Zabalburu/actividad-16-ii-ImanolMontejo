/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mis.proyectos.actividad16_2.dao;

import java.util.ArrayList;
import java.util.List;
import mis.proyectos.actividad16_2.modelo.Evento;

/**
 *
 * @author monte
 */
public class EventoList implements EventoDAO {
    private static List<Evento> eventos = new ArrayList<>();

    @Override
    public Evento añadirEvento(Evento nuevo) {
        eventos.add(nuevo);
        return nuevo;
    }

    @Override
    public void eliminarEvento(int id) {
        Evento e = new Evento();
        e.setId(id);
        eventos.remove(e);
    }

    public void eliminarEvento(String descripcion){
        int i;
        for(i=0; i<eventos.size() && 
            !descripcion.equalsIgnoreCase(eventos.get(i).getDescripcion());i++);
        eventos.remove(i);
    }
    
    @Override
    public void modificarEvento(Evento evento) {
        int pos = eventos.indexOf(evento);
        if (pos != -1){
            eventos.set(pos, evento); 
        }
    }

    @Override
    public List<Evento> getEventos() {
        return eventos;
    }

    @Override
    public Evento getEvento(int id) {
        Evento evt = new Evento();
        evt.setId(id);
        int pos = eventos.indexOf(evt);
        if (pos != -1){
            return eventos.get(pos);
        } else {
            return null;
        }
    }
}
