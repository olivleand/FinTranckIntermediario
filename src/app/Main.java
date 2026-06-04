package app;

import controller.FinTracker;
import model.Transaction;
import utils.Colors;
import utils.Formatter;
import java.time.LocalDate;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        FinTracker finTracker = new FinTracker();

        int option = 0;

        while (option != 5) {

            System.out.println(Colors.CYAN + "\n===== FINTRACK - SEU CONTROLE FINANCEIRO =====" + Colors.RESET);

            System.out.println("1. Adicionar nova transação");
            System.out.println("2. Listar transações");
            System.out.println("3. Mostrar saldo atual");
            System.out.println("4. Remover transação");
            System.out.println("5. Sair");

            System.out.print("Escolha uma opção: ");

            try {
                option = Integer.parseInt(scanner.nextLine());

                switch (option) {
                    case 1:
                        System.out.print("Descrição: ");
                        String description = scanner.nextLine();

                        System.out.print("Valor: ");
                        double valor = Double.parseDouble(scanner.nextLine());

                        System.out.println("Tipo da transação: ");
                        System.out.println("1 - Receita");
                        System.out.println("2 - Despesa");

                        int tipo = Integer.parseInt(scanner.nextLine());
                        boolean ehReceita = (tipo == 1);

                        LocalDate data = LocalDate.now();

                        Transaction t = new Transaction(description, valor, ehReceita,  data);

                        finTracker.adicionarTransaction(t);

                        System.out.println(Colors.GREEN + "Trasação adicionada com sucesso!" + Colors.RESET);
                        break;

                    case 2:
                        finTracker.listarTransactions();
                        break;

                    case 3:
                        double saldo = finTracker.calcularSaldoTotal();

                        System.out.println("Saldo atual: " + Formatter.formatarValor(saldo));
                        break;

                    case 4:
                        System.out.print("Digite o índice da transação para remover: ");
                        int indice = Integer.parseInt(scanner.nextLine());

                        finTracker.removerTransaction(indice);
                        break;

                    case 5:
                        System.out.println("Encerrando o sistema...");
                        break;

                    default:
                        System.out.println(Colors.RED + "Opção Inválida!" + Colors.RESET);
                }

            } catch(NumberFormatException e) {
                System.out.println(Colors.YELLOW + "Erro: Digite um número válido." + Colors.RESET);
            } catch (Exception e) {
                System.out.println("Erro inesperado: " + e.getMessage());
            }
        }
        scanner.close();

    }
}
