package ld_231_p1_ex2;

import java.util.Locale;
import java.util.Scanner;

public class LD_231_P1_EX2 {
    public static void main(String[] args) {     
        Locale.setDefault(Locale.US);       
        Scanner sc = new Scanner(System.in);
                
        double a, b, c, maior, menor, meio;
        
        System.out.print("Digite um número: ");
        a = sc.nextDouble();
        
        System.out.print("Digite outro número: ");
        b = sc.nextDouble();
          
        System.out.print("Digite o último número: ");
        c = sc.nextDouble();
        
        if (a >= b && a >= c) {
            maior = a;
                if (b >= c) {
                    meio = b;
                    menor = c;
                } else {
                    meio = c;
                    menor = b;
            }
        } else if (b >= a && b >= c) {
            maior = b;
                if (a >= c) {
                    meio = a;
                    menor = c;
            }   else {
                    meio = c;
                    menor = a;
            }
        } else {
            maior = c;
                if (a >= b) {
                    meio = a;
                    menor = b;
            }   else {
                    meio = b;
                    menor = a;
            }
        }
        
        int i;
        do {
            System.out.print("Digite um número de 1 a 3 (Crescente, Decrescente, Maior no meio): ");
            i = sc.nextInt();
        
            switch (i) {
                case 1:
                    System.out.println("Ordem Crescente:");
                    System.out.println(menor);
                    System.out.println(meio);
                    System.out.println(maior);
                    break;
                case 2:
                    System.out.println("Ordem Decrescente:");
                    System.out.println(maior);
                    System.out.println(meio);
                    System.out.println(menor);
                    break;
                case 3:
                    System.out.println("Maior no meio:");
                    System.out.println(menor);
                    System.out.println(maior);
                    System.out.println(meio);
                    break;
                default:
                    System.out.println("Número invalido, escolha de 1 a 3.");
            } 
        
        } while (i < 1 || i > 3);
   }   
 }
    