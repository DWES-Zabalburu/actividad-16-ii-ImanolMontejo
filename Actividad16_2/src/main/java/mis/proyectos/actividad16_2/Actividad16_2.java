/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package mis.proyectos.actividad16_2;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import javax.security.auth.login.LoginException;
import javax.swing.JOptionPane;
import mis.proyectos.actividad16_2.modelo.Evento;
import mis.proyectos.actividad16_2.modelo.Persona;
import mis.proyectos.actividad16_2.servicio.EventoServicio;

/**
 *
 * @author monte
 */
public class Actividad16_2 {

    private static EventoServicio servicio = new EventoServicio();
    private static Persona usuario;
    private static DateFormat df = DateFormat.getDateInstance(DateFormat.LONG);
    private static NumberFormat nfMoneda = NumberFormat.getCurrencyInstance();

    public static void main(String[] args) {
        usuario = login();
        if (usuario != null) {
            int opc;
            do {
                String resp = JOptionPane.showInputDialog(
                        """
                    Gestión de Eventos de %s
                    ==============================
                    1. Ver Información del Usuario
                    2. Desapuntar de Evento
                    3. Ver Eventos Disponibles
                    4. Apuntar a Evento
                    5. Salir
                    """.formatted(usuario.getNombre() + " " + usuario.getApellidos())
                );
                opc = Integer.parseInt(resp);
                switch (opc) {
                    case 1:
                        verInfoUsuario();
                        break;
                    case 2:
                        desapuntarEvento();
                        break;
                    case 3:
                        verEventosDisponibles();
                        break;
                    case 4:
                        apuntarseAEvento();
                        break;
                }
            } while (opc != 5);

        }
    }

    private static Persona login() {
        String usuario = JOptionPane.showInputDialog("Usuario (DNI)");
        String password = JOptionPane.showInputDialog("Contraseña");
        return null;        
    }

    private static void verInfoUsuario() {
        String listado
                = """
            <html>
            <h1>Usuario : %s %s</h1>
            <h2>Datos Personales</h2>
            <table bgcolor="white" border = 1>
            <tr><td>Id.</td><td>%d</td></tr>
            <tr><td>D.N.I</td><td>%s</td></tr>
            <tr><td>Fecha Naciumiento</td><td>%s</td></tr>
            <tr><td>Mayor de Edad</td><td>%s</td></tr>
            </table>
            """.formatted(
                        usuario.getNombre(),
                        usuario.getApellidos(),
                        usuario.getId(),
                        usuario.getDni(),
                        df.format(usuario.getFechaNacimiento()),
                        usuario.isMayorEdad() ? "Sí" : "No"
                );
        List<Evento> eventos = usuario.getEventos();
        if (eventos.isEmpty()) {
            listado += "<h3>No está apuntado a ningún evento</h3>";
        } else {
            listado += """
                <h3>Eventos Apuntado</h3>
                <table bgcolor=white border=1>
                  <tr><td>Evento</td><td>Fecha</td><td>Lugar</td><td>Precio</td></tr>
                """;
            double costeTotal = 0;
            for (Evento e : eventos) {
                listado += """
                <tr><td>%s</td><td>%s</td><td>%s</td><td>%s</td></tr>           
                """.formatted(e.getDescripcion(),
                        df.format(e.getFecha()),
                        e.getLugar(),
                        nfMoneda.format(e.getCoste()));
                costeTotal += e.getCoste();
            }
            listado += """
                <tr><td colspan=3>Total</td><td>%s</td></tr>           
                </table>
                </html>
                """.formatted(nfMoneda.format(costeTotal));
        }
        JOptionPane.showMessageDialog(null,
                listado,
                "Datos Personales",
                JOptionPane.PLAIN_MESSAGE);
    }

    private static void desapuntarEvento() {
        if (usuario.getEventos().isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "No está apuntado/a a ningún evento",
                    "No hay eventos",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            String pregunta = """
            <html>
                <h1>Desapuntarse a Evento</h1>
                <h3>Eventos</h3>
                <table bgcolor=white border=1>
                    <tr><td>Id</td><td>Evento</td><td>Fecha</td><td>Lugar</td><td>Precio</td></tr>     
            """;
            for (Evento e : usuario.getEventos()) {
                pregunta += """
                <tr><td>%d</td><td>%s</td><td>%s</td><td>%s</td><td>%s</td></tr>           
                """.formatted(
                        e.getId(),
                        e.getDescripcion(),
                        df.format(e.getFecha()),
                        e.getLugar(),
                        nfMoneda.format(e.getCoste()));
            }
            pregunta += """
            </table>
            <h3>Introduzca el ID del evento del que se quiere desapuntar</h3>
            """;
            String resp = JOptionPane.showInputDialog(pregunta);
            if (resp != null) {
                int id = Integer.parseInt(resp);
                Evento e = servicio.getEvento(id);
                if (e != null) {
                    if (JOptionPane.showConfirmDialog(null,
                            "Está seguro de desapuntarse de %s".formatted(e.getDescripcion()),
                            "Pregunta",
                            JOptionPane.YES_NO_OPTION)
                            == JOptionPane.YES_OPTION) {
                        servicio.desapuntarAEvento(usuario.getId(), id);
                        JOptionPane.showMessageDialog(null,
                                "Se ha desapuntado correctamente de %s".formatted(e.getDescripcion()),
                                "Desapuntado",
                                JOptionPane.INFORMATION_MESSAGE);
                    }

                } else {
                    JOptionPane.showMessageDialog(null,
                            "No hay ningún evento con ese ID",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    private static void verEventosDisponibles() {
        List<Evento> eventos = servicio.getEventos();
        if (eventos.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "No hay ningún evento",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        String listado = """
            <html>
                <h1>Listado de Eventos</h1>
                <h3>Eventos</h3>
                <table bgcolor=white border=1>
                    <tr><td>Id</td><td>Evento</td><td>Fecha</td><td>Lugar</td><td>Precio</td><td>Asistentes</td><td>Apuntado</td></tr>     
            """;
        int cont = 0;
        for (Evento e : eventos) {
            listado
                    += """
               <tr><td>%d</td><td>%s</td><td>%s</td><td>%s</td><td>%s</td><td>%d</td>
               """.formatted(
                            e.getId(),
                            e.getDescripcion(),
                            df.format(e.getFecha()),
                            e.getLugar(),
                            nfMoneda.format(e.getCoste()),
                            e.getAsistentes().size()
                    );
            if (e.getAsistentes().contains(usuario)) {
                listado += "<td>Sí</td></tr>";
                cont++;
            } else {
                listado += "<td>No</td></tr>";
            }
        }
        listado += """
            </table>
            <h3>Está apuntado a %d eventos</h3>
            </html>
            """.formatted(cont);
        JOptionPane.showMessageDialog(null,
                listado,
                "Listado de Eventos",
                JOptionPane.PLAIN_MESSAGE);
    }

    private static void apuntarseAEvento() {
        List<Evento> disponibles = new ArrayList<>();
        List<Evento> eventos = servicio.getEventos();
        for (Evento e : eventos) {
            if (!e.getAsistentes().contains(usuario)) {
                disponibles.add(e);
            }
        }
        if (disponibles.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay eventos disponibles");
        } else {
            String pregunta = """
            <html>
                <h1>Listado de Eventos Disponibles</h1>
                <table bgcolor=white border=1>
                    <tr><td>Id</td><td>Evento</td><td>Fecha</td><td>Lugar</td><td>Precio</td></tr>     
            """;
            for (Evento e : disponibles) {
                pregunta
                        += """
               <tr><td>%d</td><td>%s</td><td>%s</td><td>%s</td><td>%s</td></tr>
               """.formatted(
                                e.getId(),
                                e.getDescripcion(),
                                df.format(e.getFecha()),
                                e.getLugar(),
                                nfMoneda.format(e.getCoste()),
                                e.getAsistentes().size()
                        );
            }
            pregunta += """
                </table>
                <h3>Indique el ID del evento al que se quiere apuntar</h3>    
                </html>
                """;
            String resp = JOptionPane.showInputDialog(pregunta);
            int id = Integer.parseInt(resp);
            Evento e = servicio.getEvento(id);
            if (e == null) {
                JOptionPane.showMessageDialog(null,
                        "No existe ningún evento con ese ID",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                if (e.getAsistentes().contains(usuario)) {
                    JOptionPane.showMessageDialog(null,
                            "Ya está apuntado a ese evento",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    if (JOptionPane.showConfirmDialog(null,
                            "Está seguro de apuntarse de %s".formatted(e.getDescripcion()),
                            "Pregunta",
                            JOptionPane.YES_NO_OPTION)
                            == JOptionPane.YES_OPTION) {
                        servicio.apuntarAEvento(usuario.getId(), id);
                        JOptionPane.showMessageDialog(null,
                                "Se ha apuntado correctamente de %s".formatted(e.getDescripcion()),
                                "Desapuntado",
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        }

    }
    }

