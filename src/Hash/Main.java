package Hash;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HashTabelaEstatisticas tabela = new HashTabelaEstatisticas(5);

        while (true) {
            System.out.println("\n---------MENU---------");
            System.out.println("1 - Cadastrar Pessoa");
            System.out.println("2 - Buscar pessoa pelo CPF");
            System.out.println("3 - Mostrar todos cadastros");
            System.out.println("4 - Excluir cadastro");
            System.out.println("5 - Mostrar estatísticas");
            System.out.println("6 - Sair");
            System.out.print("Opção: ");

            int op = sc.nextInt();
            sc.nextLine(); // limpar buffer

            switch (op) {
                case 1: // Cadastrar Pessoa
                    System.out.print("Informe o nome: ");
                    String nome = sc.nextLine();

                    System.out.print("Informe o número do CPF: ");
                    String cpf = sc.nextLine();

                    System.out.print("Informe a idade: ");
                    int idade = sc.nextInt();
                    sc.nextLine(); // limpar buffer

                    Pessoa p = new Pessoa(nome, cpf, idade); // agora usa os 3 parâmetros
                    tabela.inserir(p);
                    System.out.println("Cadastro realizado!");
                    break;

                case 2: // Buscar
                    System.out.print("Qual número de CPF deseja buscar? ");
                    String cpfBusca = sc.nextLine();
                    Pessoa buscada = tabela.buscar(cpfBusca);
                    if (buscada != null) {
                        System.out.println("Cadastro encontrado: " + buscada);
                    } else {
                        System.out.println("Cadastro não encontrado.");
                    }
                    break;

                case 3: // Mostrar todos
                    tabela.mostrar();
                    break;

                case 4: // Excluir
                    System.out.print("Informe o número do CPF para exclusão: ");
                    String cpfExc = sc.nextLine();
                    tabela.excluir(cpfExc);
                    break;

                case 5: // Mostrar estatísticas
                    tabela.mostrarEstatisticas();
                    break;

                case 6: // Sair
                    System.out.println("Encerrado.");
                    sc.close();
                    return;

                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
}
