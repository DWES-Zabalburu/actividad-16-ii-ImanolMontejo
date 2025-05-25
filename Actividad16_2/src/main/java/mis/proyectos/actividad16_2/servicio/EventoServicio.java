/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mis.proyectos.actividad16_2.servicio;

import java.util.GregorianCalendar;
import java.util.List;
import mis.proyectos.actividad16_2.dao.EventoDAO;
import mis.proyectos.actividad16_2.dao.EventoList;
import mis.proyectos.actividad16_2.dao.PersonaDAO;
import mis.proyectos.actividad16_2.dao.PersonaMatriz;
import mis.proyectos.actividad16_2.modelo.Evento;
import mis.proyectos.actividad16_2.modelo.Persona;

/**
 *
 * @author monte
 */
public class EventoServicio {
    private PersonaDAO personaDao = new PersonaMatriz();
    private EventoDAO eventoDao = new EventoList();
    
    public EventoServicio(){
        this.añadirPersona(new Persona("Juan","López","12345678A","juan", new GregorianCalendar(1990,2,1).getTime()));
        this.añadirPersona(new Persona("Ana","Marín","12345678B","ana", new GregorianCalendar(1993,7,19).getTime()));
        this.añadirPersona(new Persona("Carlos","Martín","12345678C","carlos", new GregorianCalendar(1990,1,15).getTime()));
        this.añadirPersona(new Persona("Eva","Sanz","12345678C","eva", new GregorianCalendar(1989,0,11).getTime()));
        this.añadirPersona(new Persona("Luisa","Simón","12345678C","luisa", new GregorianCalendar(1992,2,23).getTime()));
        this.añadirPersona(new Persona("Miguel","De Miguel","12345678F","miguel", new GregorianCalendar(1978,4,13).getTime()));
        this.añadirPersona(new Persona("Sonia","Lucas","12345678G","sonia", new GregorianCalendar(1995,11,21).getTime()));
        this.añadirEvento(new Evento("Evento Uno",new GregorianCalendar(2025,1,21).getTime() , "Bilbao", true, 25.56));
        this.añadirEvento(new Evento("Evento Dos",new GregorianCalendar(2025,2,2).getTime() , "Barakaldo", false, 40));
        this.añadirEvento(new Evento("Evento Tres",new GregorianCalendar(2025,0,31).getTime() , "Sestao", true, 80.5));
        this.añadirEvento(new Evento("Evento Cuatro",new GregorianCalendar(2025,1,25).getTime() , "Bilbao", false, 47));
        this.apuntarAEvento(1, 2);
        this.apuntarAEvento(1, 4);
        this.apuntarAEvento(3, 2);
        this.apuntarAEvento(5, 1);
    }
    
    public Evento añadirEvento(Evento nuevo){
        return eventoDao.añadirEvento(nuevo);
    }
    
    public void eliminarEvento(int id){
        eventoDao.eliminarEvento(id);
    }
    
    public void modificarEvento(Evento evento){
        eventoDao.modificarEvento(evento);
    }
    
    public List<Evento> getEventos(){
        return eventoDao.getEventos();
    }
    
    public Evento getEvento(int id){
        return eventoDao.getEvento(id);
    }
    
    public Persona añadirPersona(Persona nueva){
        return personaDao.añadirPersona(nueva);
    }
    
    public void eliminarPersona(int id){
        personaDao.eliminarPersona(id);
    }
    
    public void modificarPersona(Persona modificar){
        personaDao.modificarPersona(modificar);
    }
    
    public List<Persona> getPersonas(){
        return personaDao.getPersonas();
    }
    public Persona getPersona(int id){
        return personaDao.getPersona(id);
    }
 
    public Persona login(String dni, String password) {
        Persona p = personaDao.getPersona(dni);
        if (p != null && password.equals(p.getPassword())){
            return p;
        }
        return null;
    }
    
    public boolean apuntarAEvento(int idPersona, int idEvento){
        Persona p = personaDao.getPersona(idPersona);
        Evento e = eventoDao.getEvento(idEvento);
        if (p != null && e != null){
            e.getAsistentes().add(p); // Añadimos la persona a los asistentes del evento
            p.getEventos().add(e); // Añadimos el evento a la lista de eventos a los que va a ir la persona
            return true;
        }
        return false;
    }
    
    public void desapuntarAEvento(int idPersona, int idEvento){
        Persona p = personaDao.getPersona(idPersona);
        Evento e = eventoDao.getEvento(idEvento);
        if (e != null && p != null){
            e.getAsistentes().remove(p);
            p.getEventos().remove(e);
        }
    }
    
    public static void main(String[] args) {
        EventoServicio servicio = new EventoServicio();
        for (Persona p : servicio.getPersonas()){
            System.out.println(p);
        }
        for(Evento e : servicio.getEventos()){
            System.out.println(e);
        }
    }
}
