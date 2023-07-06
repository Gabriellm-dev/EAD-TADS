package sistemaescolar;

import java.util.Locale;
import java.util.Scanner;

public class SistemaEscolar {
    
      private static final int MAX_ALUNOS = 5;
      private static final double NOTA_MIN_APROV = 6.0;
      private static final String MSG_TURMA_CHEIA = "A turma está cheia. Não é possível cadastrar mais alunos.";
      private static final String MSG_NENHUM_ALUNO_CADASTRADO = "Nenhum aluno cadastrado!";
      private static final String MSG_NUMERO_INVALIDO = "Número digitado inválido. Tente novamente.";
        
      private static String[] nomes = new String[MAX_ALUNOS];
      private static double[] nota1 = new double[MAX_ALUNOS];
      private static double[] nota2 = new double[MAX_ALUNOS];
      private static double[] medias = new double[MAX_ALUNOS];
                 
      private static int contarAlunos = 0;
    
      public static void main(String[] args) {
          Scanner sc = new Scanner(System.in);
          Locale.setDefault(Locale.US);
          
          int opc;
          
          do {
                exibirMenu();
                opc = sc.nextInt();
                sc.nextLine();
                    
                switch  (opc) {
                    case 0:
                        System.out.println("-----------------------------------");
                        System.out.println(" ");
                        System.out.println("Saindo do Sistema...");
                        break;
                    case 1: 
                        cadastrarAlunos();
                        break;
                    case 2:
                        mostrarDadosAlunos();
                        break;
                    case 3:
                        alunosAprovados();
                        break;
                    case 4:
                        alunosReprovados();
                        break;
                    case 5:
                        exibirInformacoes();
                        break;
                    default:
                        System.out.println("-----------------------------------");
                        System.out.println(" ");
                        System.out.println(MSG_NUMERO_INVALIDO);
                        System.out.println(" ");
                        break;              
                } 
                
          } while (opc != 0);  
          
          sc.close();
    }  

    private static void exibirMenu() {
        System.out.println("-----------------------------------");
        System.out.println("               Menu");
        System.out.println("-----------------------------------");
        System.out.println("1 - Cadastrar alunos");
        System.out.println("2 - Mostrar dados dos alunos");
        System.out.println("3 - Alunos aprovados");
        System.out.println("4 - Alunos reprovados");
        System.out.println("5 - Informações relevantes");
        System.out.println("0 - Sair");
        System.out.println("-----------------------------------");
        System.out.print("Digite a opção: ");
    }
      
    private static void cadastrarAlunos() {     
        Scanner sc = new Scanner(System.in); 
        Locale.setDefault(Locale.US);     
               
        if (contarAlunos >= MAX_ALUNOS) {
            System.out.println(" ");
            System.out.println("-----------------------------------");
            System.out.println(MSG_TURMA_CHEIA);
            return;
        }
        
        char opc2;     
        do {
            System.out.println("-----------------------------------");
            System.out.println(" ");
            System.out.println("Digite o nome do aluno " +(contarAlunos + 1) +  " e as notas (1 e 2)");          
            System.out.print("Nome: ");
                nomes[contarAlunos] = sc.nextLine();
           
            double nota;                
            do {               
                System.out.print("Nota 1: ");
                nota = sc.nextDouble();
                sc.nextLine();

                if (nota < 0 || nota > 10) {
                    System.out.println("A nota deve estar entre 0 e 10. Digite novamente.");
                }
            } while (nota < 0 || nota > 10);
            
            nota1[contarAlunos] = nota;

            do {
                System.out.print("Nota 2: ");
                nota = sc.nextDouble();
                sc.nextLine();

                if (nota < 0 || nota > 10) {
                    System.out.println("A nota deve estar entre 0 e 10. Digite novamente.");
                }
            } while (nota < 0 || nota > 10);
            
            nota2[contarAlunos] = nota;

            System.out.println(" ");
            medias[contarAlunos] = (nota1[contarAlunos] + nota2[contarAlunos]) / 2;

            contarAlunos++;
            
            if (contarAlunos < MAX_ALUNOS){               
                System.out.print("Deseja cadastrar mais um aluno ? (s / n): ");
                    opc2 = sc.nextLine().charAt(0);
                    System.out.println(" ");
            } else {
                opc2 = 'n';
            }
            
        } while (opc2 == 's' && contarAlunos < MAX_ALUNOS);      
    }
    
    
    public static void mostrarDadosAlunos(){
        
        if (contarAlunos == 0) {
            System.out.println("-----------------------------------");
            System.out.println(" ");
            System.out.println(MSG_NENHUM_ALUNO_CADASTRADO);
            System.out.println(" ");  
            
        } else {
            System.out.println("-----------------------------------");
            System.out.println(" ");
            System.out.println("Dados dos alunos cadastrados");
            System.out.println(" ");
            
            for (int i = 0; i < contarAlunos; i++) {
            System.out.println("Aluno: " + nomes[i]);
            System.out.println("    Nota 1: " + nota1[i]);
            System.out.println("    Nota 2: " + nota2[i]);
            System.out.printf("    Média: %.2f%n " ,medias[i]);
            
                if (medias[i] >= NOTA_MIN_APROV) {
                    System.out.println("   Aluno Aprovado");
                    System.out.println(" ");
                }else {
                    System.out.println("   Aluno Reprovado");
                    System.out.println(" ");
                }
            }
        }   
    }
    
    private static void imprimirAlunosPorStatus(boolean aprovados) {
        System.out.println("-----------------------------------");
        System.out.println(" ");
        System.out.println(aprovados ? "Alunos aprovados:" : "Alunos reprovados:");
        System.out.println(" ");

        boolean encontrouAluno = false;

        for (int i = 0; i < contarAlunos; i++) {
            if ((medias[i] >= NOTA_MIN_APROV && aprovados) || (medias[i] < NOTA_MIN_APROV && !aprovados)) {
                System.out.println("Nome: " + nomes[i]);
                System.out.printf("Média: %.2f%n ", medias[i]);
                System.out.println(" ");
                encontrouAluno = true;
            }
        }

        if (!encontrouAluno) {
            System.out.println(aprovados ? "Nenhum aluno aprovado!" : "Nenhum aluno reprovado!");
            System.out.println("");
        }
    }

    private static void alunosAprovados() {
         imprimirAlunosPorStatus(true);
     }

    private static void alunosReprovados() {
         imprimirAlunosPorStatus(false);
     }
    
    private static void exibirInformacoes(){
        System.out.println("-----------------------------------");
        System.out.println(" ");
        System.out.println("Informações Relevantes para o bom funcionamento do sistema");
        System.out.println(" ");
        System.out.println("1. Deve ser utilizado ponto, para o separador decimal.");
        System.out.println("2. O número maximo de alunos cadastrados é 5.");
        System.out.println("3. Média mínima para aprovação é 6.0.");
        System.out.println("4. A nota deve estar entre 0 e 10.");
        System.out.println("5. Deve ser utilizado letra minuscula para a função de cadastro de alunos: 's' ou 'n'.");
        System.out.println("");
    }        
}

