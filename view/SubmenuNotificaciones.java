package view;

import java.util.Scanner;

import controller.SistemaEmergencias;
import model.services.Ambulancia;
import model.services.Bomberos;
import model.services.Policia;
import utils.Agencias;

public class SubmenuNotificaciones {
    public void mostrarSubmenuNotificaciones(SistemaEmergencias sistema, Scanner sc) {
        boolean salir = false;

        do {
            // Mostrar opciones del submenú de notificaciones
            System.out.println("\n-----SUBMENÚ NOTIFICACIONES EMERGENCIAS-----");
            System.out.println("1. Suscribirse a las notificaciones");
            System.out.println("2. Eliminar suscripción");
            System.out.println("3. Recibir notificaciones");
            System.out.println("4. Volver");
            System.out.print("Digite la opción deseada: ");
            int opcion = 0;

            try {
                // Leer la opción seleccionada por el usuario
                opcion = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Digitó una opción inválida, intente nuevamente");
                continue;
            }

            switch (opcion) {
                case 1:
                    // Submenú para suscribirse a notificaciones
                    System.out.println("\n----Submenú suscripción----");
                    for (Agencias a : Agencias.values()) {
                        System.out.println(a.ordinal() + 1 + ". " + a.name());
                    }
                    System.out.println("4. Volver");
                    System.out.print("Seleccione una opción: ");
                    int opcionSuscripcion = 0;

                    try {
                        opcionSuscripcion = Integer.parseInt(sc.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Digitó una opción inválida");
                        continue;
                    }

                    switch (opcionSuscripcion) {
                        case 1:
                            // Suscribirse a notificaciones de Policía
                            sistema.agregarObserver(new Policia(Agencias.Policia));
                            break;
                        case 2:
                            // Suscribirse a notificaciones de Bomberos
                            sistema.agregarObserver(new Bomberos(Agencias.Bomberos));
                            break;
                        case 3:
                            // Suscribirse a notificaciones de Ambulancia
                            sistema.agregarObserver(new Ambulancia(Agencias.Ambulancia));
                            break;
                        case 4:
                            // Volver al menú anterior
                            break;
                        default:
                            System.out.println("Digitó una opción inválida");
                            break;
                    }
                    break;
                case 2:
                    // Submenú para eliminar suscripciones
                    System.out.println("\n----Submenú eliminar suscripción");
                    for (Agencias a : Agencias.values()) {
                        System.out.println(a.ordinal() + 1 + ". " + a.name());
                    }
                    System.out.println("4. Volver");
                    System.out.print("Digite la opción deseada: ");
                    int opcionEliminarSuscripcion = 0;

                    try {
                        opcionEliminarSuscripcion = Integer.parseInt(sc.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Digitó una opción inválida");
                        continue;
                    }

                    switch (opcionEliminarSuscripcion) {
                        case 1:
                            // Eliminar suscripción de Policía
                            sistema.eliminarObserver(new Policia(Agencias.Policia));
                            break;
                        case 2:
                            // Eliminar suscripción de Bomberos
                            sistema.eliminarObserver(new Bomberos(Agencias.Bomberos));
                            break;
                        case 3:
                            // Eliminar suscripción de Ambulancia
                            sistema.eliminarObserver(new Ambulancia(Agencias.Ambulancia));
                            break;
                        case 4:
                            // Volver al menú anterior
                            break;
                        default:
                            System.out.println("Digitó una opción inválida");
                            break;
                    }
                    break;
                case 3:
                    // Submenú para recibir notificaciones
                    System.out.println("\n----Submenú notificaciones de emergencia----");
                    for (Agencias a : Agencias.values()) {
                        int index = a.ordinal() - 1;
                        int notificaciones = sistema.getNotificacionesPendientes()[index + 1];

                        // Mostrar notificaciones pendientes por agencia
                        System.out.println(a.ordinal() + 1 + ". " + a.name() + " " 
                            + "Tienes " + "(" + notificaciones + ")" + " "
                            + (notificaciones == 1 ? "notificación pendiente" : "notificaciones pendientes"));
                    }
                    System.out.println("4. Volver");
                    System.out.print("Digite la opción deseada: ");
                    int opcionNotificaciones = 0;

                    try {
                        opcionNotificaciones = Integer.parseInt(sc.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Digitó una opción inválida");
                    }

                    switch (opcionNotificaciones) {
                        case 1: // Policía
                            // Verificar si Policía está suscrito
                            boolean policiaSuscrito = sistema.getObservadores().stream()
                                .anyMatch(observer -> observer instanceof Policia);

                            if (!policiaSuscrito) {
                                System.out.println("Policía debe realizar primero la suscripción a emergencias.");
                                return;
                            }
                            // Notificar emergencias a Policía
                            sistema.notificarEmergencias(sistema.getEmergenciasPolicia());
                            sistema.getNotificacionesPendientes()[0] = 0;
                            break;
                        case 2: // Bomberos
                            // Verificar si Bomberos está suscrito
                            boolean bomberosSuscrito = sistema.getObservadores().stream()
                                .anyMatch(observer -> observer instanceof Bomberos);

                            if (!bomberosSuscrito) {
                                System.out.println("Bomberos debe realizar primero la suscripción a emergencias.");
                                return;
                            }
                            // Notificar emergencias a Bomberos
                            sistema.notificarEmergencias(sistema.getEmergenciasBomberos());
                            sistema.getNotificacionesPendientes()[1] = 0;
                            break;
                        case 3: // Ambulancia
                            // Verificar si Ambulancia está suscrita
                            boolean ambulanciaSuscrito = sistema.getObservadores().stream()
                                .anyMatch(observer -> observer instanceof Bomberos);

                            if (!ambulanciaSuscrito) {
                                System.out.println("Ambulancia debe realizar primero la suscripción a emergencias.");
                                return;
                            }
                            // Notificar emergencias a Ambulancia
                            sistema.notificarEmergencias(sistema.getEmergenciasAmbulancia());
                            sistema.getNotificacionesPendientes()[2] = 0;
                            break;
                        case 4: // Volver
                            break;
                        default:
                            System.out.println("Digitó una opción inválida");
                            break;
                    }

                    break;
                case 4:
                    // Salir del submenú
                    salir = true;
                    break;
                default:
                    System.out.println("Digitó una opción inválida, intente nuevamente");
            }
        } while (!salir);
    }
}
