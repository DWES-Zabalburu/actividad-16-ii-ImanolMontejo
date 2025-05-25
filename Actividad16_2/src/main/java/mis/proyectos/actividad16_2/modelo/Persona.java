/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mis.proyectos.actividad16_2.modelo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author monte
 */
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Persona {
    private static int numPersonas = 0;
    @EqualsAndHashCode.Include
    private int id;
    private String nombre;
    private String apellidos;
    private String dni;
    private String password;
    private Date fechaNacimiento;
    private List<Evento> eventos = new ArrayList<>();

    public Persona() {
        Persona.numPersonas++;
        this.id = Persona.numPersonas;
    }

    public Persona(String nombre, String apellidos, String dni, String password, Date fechaNacimiento) {
        this();
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.password = password;
        this.fechaNacimiento = fechaNacimiento;
    }

    public boolean isMayorEdad(){
        GregorianCalendar gcHoy = new GregorianCalendar();
        GregorianCalendar gcFechaNacimiento = new GregorianCalendar();
        gcFechaNacimiento.setTime(this.fechaNacimiento);
        int añoActual = gcHoy.get(Calendar.YEAR);
        int añoNacimiento = gcFechaNacimiento.get(Calendar.YEAR);
        int edad = añoActual - añoNacimiento;
        // Si no ha cumplido el día actual en el añp será MENOR respecto al día de su nacimiento
        if (gcHoy.get(Calendar.DAY_OF_YEAR) < gcFechaNacimiento.get(Calendar.DAY_OF_YEAR)){
            edad--;
        }
        return edad >= 18;
    }

    @Override
    public String toString() {
        return "Persona{" + "id=" + id + ", nombre=" + nombre + ", apellidos=" + apellidos + ", dni=" + dni + ", password=" + password + ", fechaNacimiento=" + fechaNacimiento + '}';
    }
    
    
    
}
