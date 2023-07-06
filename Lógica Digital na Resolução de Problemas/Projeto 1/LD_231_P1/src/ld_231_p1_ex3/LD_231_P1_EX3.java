
package ld_231_p1_ex3;

import java.util.Scanner;

public class LD_231_P1_EX3 {
    
    public static void main(String[] args) {
        
        int maisVotos, totalVotos=0, candidato1=0, candidato2=0, candidato3=0, candidato4=0;
        String candidato;
          
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Digite a quantidade de votos de cada candidato: ");
        candidato1 = sc.nextInt();
            maisVotos = candidato1;
            candidato = "Candidato 1";
        candidato2 = sc.nextInt();
            if (candidato2 > maisVotos){
                maisVotos = candidato2;
                candidato = "Candidato 2";
            }
        candidato3 = sc.nextInt();
            if (candidato3 > maisVotos) {
                maisVotos = candidato3;
                candidato = "Candidato 3";
            }
        candidato4 = sc.nextInt();
            if (candidato4 > maisVotos) {
                maisVotos = candidato4;
                candidato = "Candidato 4";
            }
            
        totalVotos = candidato1 + candidato2 + candidato3 + candidato4;
            if (maisVotos > (totalVotos/2)) {
                System.out.println("Resultado: O " + candidato + " vence em primeiro turno.");  
            } else {
                System.out.println("Haverá segundo turno.");         
            }
    }
}
