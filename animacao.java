import java.util.Random;
import java.util.Scanner;
import java.lang.Thread;

public class animacao {
    static Random r = new Random();
    static Scanner READ = new Scanner(System.in);

    public static void animacaoCarregamento(){

        for (int i = 0; i < 100; i++) {

            limparTela();
            pularLinhas();

            System.out.println(AMARELO + "Preparando a partida..." + RESET);
            System.out.println("Carregando " + (i + 1) + "%");


            int numero = r.nextInt(6);

            switch (numero) {
                case 0:
                    System.out.println(VERMELHO + DADO_1 + RESET);
                    break;

                case 1:
                    System.out.println(AMARELO + DADO_2 + RESET);
                    break;

                case 2:
                    System.out.println(ROXO + DADO_3 + RESET);
                    break;

                case 3:
                    System.out.println(VERDE + DADO_4 + RESET);
                    break;

                case 4:
                    System.out.println(VERMELHO + DADO_5 + RESET);
                    break;

                case 5:
                    System.out.println(AMARELO + DADO_6 + RESET);
                    break;
            }

            pularLinhas();
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                System.out.println("Não deu pra esperar");
                break;
            }
        }
    }
            
    public static void pularLinhas() {
        for (int i = 0; i < 30; i++) {
            System.out.println();
        }
    }
    public static void limparTela() {
        System.out.print("\033[H\033[2J\033[3J");
        System.out.flush();
    }
    public static String AMARELO = "\u001B[93m";
    public static String ROXO = "\u001B[95m";
    public static String VERMELHO = "\u001B[91m";
    public static String VERDE = "\u001B[92m";
    public static String RESET = "\u001B[0m";

    public static final String DADO_1 = "    +-----------+\n" +
            "   /           /|\n" +
            "  /     o     / |\n" +
            " /           /  |\n" +
            "+-----------+   +\n" +
            "|           |  / \n" +
            "|     o     | /  \n" +
            "|           |/   \n" +
            "+-----------+    ";

    public static final String DADO_2 = "    +-----------+\n" +
            "   / o         / \n" +
            "  /           / |\n" +
            " /         o /  |\n" +
            "+-----------+   +\n" +
            "| o         |  / \n" +
            "|           | /  \n" +
            "|         o |/   \n" +
            "+-----------+    ";

    public static final String DADO_3 = "    +-----------+\n" +
            "   / o         /|\n" +
            "  /     o     / |\n" +
            " /         o /  |\n" +
            "+-----------+   +\n" +
            "| o         |  / \n" +
            "|     o     | /  \n" +
            "|         o |/   \n" +
            "+-----------+    ";

    public static final String DADO_4 = "    +-----------+\n" +
            "   / o       o /|\n" +
            "  /           / |\n" +
            " / o       o /  |\n" +
            "+-----------+   +\n" +
            "| o       o |  / \n" +
            "|           | /  \n" +
            "| o       o |/   \n" +
            "+-----------+    ";

    public static final String DADO_5 = "    +-----------+\n" +
            "   / o       o /|\n" +
            "  /     o     / |\n" +
            " / o       o /  |\n" +
            "+-----------+   +\n" +
            "| o       o |  / \n" +
            "|     o     | /  \n" +
            "| o       o |/   \n" +
            "+-----------+    ";

    public static final String DADO_6 = "    +-----------+\n" +
            "   / o       o /|\n" +
            "  / o       o / |\n" +
            " / o       o /  |\n" +
            "+-----------+   +\n" +
            "| o       o |  / \n" +
            "| o       o | /  \n" +
            "| o       o |/   \n" +
            "+-----------+    ";
}
