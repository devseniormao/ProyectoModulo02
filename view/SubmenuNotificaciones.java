package view;

import java.util.Scanner;

import controller.SistemaEmergencias;
import model.services.Ambulancia;
import utils.Agencias;


public class SubmenuNotificaciones {
    public void mostrarSubmenuNotificaciones(SistemaEmergencias sistema, Scanner sc){
        boolean salir = false;

        do{
        System.out.println("\n-----SUBMENÚ NOTIFICACIONES EMERGENCIAS-----");
        System.out.println("1. Suscribirse a las notificaciones");
        System.out.println("2. Eliminar suscripción");
        System.out.println("3. Recibir notificaciones");
        System.out.println("4. Volver");
        System.out.print("Digite la opción deseada: ");
        int opcion = 0;

        try{
            opcion = Integer.parseInt(sc.nextLine());
        }catch(NumberFormatException e){
            System.out.println("Digitó una opción inválida, intente nuevamente");
            continue;
        }

        switch(opcion){
            case 1:
                for (Agencias a : Agencias.values()) {
                    System.out.println( (a+1) + ". " );
                }
                System.out.print("Seleccione una opción: ");
                int opcionSuscripcion = 0;

                try{
                    opcionSuscripcion = Integer.parseInt(sc.nextLine());
                }catch(NumberFormatException e){
                    System.out.println("Digitó una opción inválida");
                    continue;
                }

                switch (opcionSuscripcion) {
                    case 1:
                        sistema.agregarObserver(new Ambulancia(Agencias.Policia));
                        break;
                    case 2:
                        sistema.agregarObserver(new Ambulancia(Agencias.Ambulancia));
                        break;
                    case 3:
                        sistema.agregarObserver(new Ambulancia(Agencias.Bomberos));
                        break;
                    case 4:
                        break;    
                    default:
                        System.out.println("Digitó una opción inválida");
                        break;
                }
                break;
            case 2:
                
                break;
            case 3:
                break;
            case 4:
                salir = true;
                break;
            default:
                System.out.println("Digitó una opción inválida, intente nuevamente");
            }
        }while(!salir);
    }
}
