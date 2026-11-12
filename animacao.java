import java.util.Random;
import java.util.Scanner;
import java.lang.Thread;

public class animacao {
    static Random r = new Random();
    static Scanner READ = new Scanner(System.in);

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            int numero = r.nextInt(6);
            
            if (numero >= 0 && numero <= 5) {
                switch (numero) {
                    case 0:
                        System.out.println(DADO_1);
                        break;
                    case 1:
                        System.out.println(DADO_2);
                        break;
                    case 2:
                        System.out.println(DADO_3);
                        break;
                    case 3:
                        System.out.println(DADO_4);
                        break;
                    case 4:
                        System.out.println(DADO_5);
                        break;
                    case 5:
                        System.out.println(DADO_6);
                        break;
                }
            }

            limparTela(i);
            try {
                Thread.sleep(500);
            } catch (Exception e) {
                System.out.println("Não deu pra esperar");
            }
        }
    }

    public static String AMARELO = "\u001B[93m";
    public static String ROXO = "\u001B[95m";
    public static String VERMELHO = "\u001B[91m";
    public static String VERDE = "\u001B[92m";
    public static String RESET = "\u001B[0m";

    public static void limparTela(int jogador) {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

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
            "   / o         /|\n" +
            "  / o       o / |\n" +
            " / o       o /  |\n" +
            "+-----------+   +\n" +
            "| o       o |  / \n" +
            "| o       o | /  \n" +
            "| o       o |/   \n" +
            "+-----------+    ";

    public static final String DADO_3 = "    +-----------+\n" +
            "  /           / |\n" +
            " /         o /  |\n" +
            "+-----------+   +\n" +
            "| o         |  / \n" +
            "|           | /  \n" +
            "|         o |/   \n" +
            "+-----------+    ";

    public static final String DADO_4 = "    +-----------+\n" +
            "   / o         /|\n" +
            "  /     o     / |\n" +
            " /         o /  |\n" +
            "+-----------+   +\n" +
            "| o         |  / \n" +
            "|     o     | /  \n" +
            "|         o |/   \n" +
            "+-----------+    ";

    public static final String DADO_5 = "    +-----------+\n" +
            "   / o       o /|\n" +
            "  /           / |\n" +
            " / o       o /  |\n" +
            "+-----------+   +\n" +
            "| o       o |  / \n" +
            "|           | /  \n" +
            "| o       o |/   \n" +
            "+-----------+    ";

    public static final String DADO_6 = "    +-----------+\n" +
            "   / o       o /|\n" +
            "  /     o     / |\n" +
            " / o       o /  |\n" +
            "+-----------+   +\n" +
            "| o       o |  / \n" +
            "|     o     | /  \n" +
            "| o       o |/   \n" +
            "+-----------+    ";

    public static final String DADO_7 = "    +-----------+\n" +
            "   / o       o /|\n" +
            "  / o       o / |\n" +
            " / o       o /  |\n" +
            "+-----------+   +\n" +
            "| o       o |  / \n" +
            "| o       o | /  \n" +
            "| o       o |/   \n" +
            "+-----------+    ";
}
