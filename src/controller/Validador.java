/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author maste
 */
public class Validador implements IIntValidable, IDoubleValidable {
    private static Scanner scanner;

    public Validador(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public double validarDouble(String mensaje) {
        double valor = 0.0;
        boolean esValido;

        do {
            try {
                System.out.print(mensaje);
                valor = scanner.nextDouble();
                esValido = true;
            } catch (InputMismatchException e) {
                System.out.println("Error: Ingrese un número válido.");
                scanner.nextLine(); // Limpiar el buffer del scanner
                esValido = false;
            }
        } while (!esValido);

        return valor;
    }

    @Override
    public int validarEntero(String mensaje) {
        int valor = 0;
        boolean esValido;

        do {
            try {
                System.out.print(mensaje);
                valor = scanner.nextInt();
                esValido = true;
            } catch (InputMismatchException e) {
                System.out.println("Error: Ingrese un número entero válido.");
                scanner.nextLine(); // Limpiar el buffer del scanner
                esValido = false;
            }
        } while (!esValido);

        return valor;
    }
}
