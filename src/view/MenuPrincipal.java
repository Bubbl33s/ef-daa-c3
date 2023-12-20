/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.util.Scanner;
import controller.Vendedor;

/**
 *
 * @author maste
 */
public class MenuPrincipal {
    Scanner scanner = new Scanner(System.in);
    Vendedor vendedor;
    
    public MenuPrincipal(Vendedor vendedor) {
        this.vendedor = vendedor;
    }
    
    public void mostrarMenu() {
        int opcion;
        
        System.out.printf("\nBienvenid@ %s %s - %s\n", this.vendedor.getNombres(),
                this.vendedor.getApellidos(), this.vendedor.getId());
        
        do {
            System.out.println("\nMENÚ PRINCIPAL");
            System.out.println("==============");
            System.out.println("1. Vender Productos");
            System.out.println("2. Anular Comprobante de Pago");
            System.out.println("3. Gestionar Inventario");
            System.out.println("4. Gestionar Clientes");
            System.out.println("5. Gestionar Vendedores");
            System.out.println("6. Emitir Reportes");
            System.out.println("7. Salir\n");
            System.out.print("Ingrese la opción deseada: ");

            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    VenderProductos.iniciarVenta(this.vendedor.getId());
                    break;
                case 2:
                    // Lógica para anular comprobante de pago
                    break;
                case 3:
                    // Lógica para gestionar inventario
                    break;
                case 4:
                    // Lógica para gestionar clientes
                    break;
                case 5:
                    // Lógica para gestionar vendedores
                    break;
                case 6:
                    // Lógica para emitir reportes
                    break;
                case 7:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
                    break;
            }

        } while (opcion != 7);
    }
}
