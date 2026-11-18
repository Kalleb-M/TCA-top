import java.util.Scanner;
import java.util.Random;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Main {

    //Lê valores inteiros
    public static int Read(){
        int value = READ.nextInt();
        return value;
    }

    //Lê valores "char"
    public static char ReadC(){
        char value = READ.next().charAt(0);
        return value;
    }

    public static int[] ultmTent = new int[2];

    public static int naMesa = 0;

    public static String[][] ASCIIDados = {
        {
            "    +-----------+",
            "   /           /|",
            "  /     o     / |",
            " /           /  |",
            "+-----------+   +",
            "|           |  / ",
            "|     o     | /  ",
            "|           |/   ",
            "+-----------+    ",
        },
        {
            "    +-----------+",
            "   / o         /|",
            "  /           / |",
            " /         o /  |",
            "+-----------+   +",
            "| o         |  / ",
            "|           | /  ",
            "|         o |/   ",
            "+-----------+    ",
        },
        {
            "    +-----------+",
            "   / o         /|",
            "  /     o     / |",
            " /         o /  |",
            "+-----------+   +",
            "| o         |  / ",
            "|     o     | /  ",
            "|         o |/   ",
            "+-----------+    ",
        },
        {
            "    +-----------+",
            "   / o       o /|",
            "  /           / |",
            " / o       o /  |",
            "+-----------+   +",
            "| o       o |  / ",
            "|           | /  ",
            "| o       o |/   ",
            "+-----------+    ",
        },
        {
            "    +-----------+",
            "   / o       o /|",
            "  /     o     / |",
            " / o       o /  |",
            "+-----------+   +",
            "| o       o |  / ",
            "|     o     | /  ",
            "| o       o |/   ",
            "+-----------+    ",
        },
        {
            "    +-----------+",
            "   / o       o /|",
            "  / o       o / |",
            " / o       o /  |",
            "+-----------+   +",
            "| o       o |  / ",
            "| o       o | /  ",
            "| o       o |/   ",
            "+-----------+    ",
        },
    };

    //=====================================================================================================================
    //Recebe a quantidade de jogadores
    //=====================================================================================================================

    public static int QuantidadeJog() {
        int quantPlayers = Read();
        return quantPlayers;
    }

    //=====================================================================================================================
    //Define o valor dos dados
    //=====================================================================================================================

    public static int[][] Dados(int[][] quantDados, int quantPlayers) {

        Random dados = new Random();

        if (quantDados == null) {
            quantDados = new int[quantPlayers][3];

            for (int linhas = 0; linhas < quantDados.length; linhas++) {
                for (int colunas = 0; colunas < quantDados[linhas].length; colunas++) {
                    quantDados[linhas][colunas] = 1;
                }
            }
        }
        for (int linhas = 0; linhas < quantDados.length; linhas++) {
            for (int colunas = 0; colunas < quantDados[linhas].length; colunas++) {

                if (quantDados[linhas][colunas] != 0) {

                    quantDados[linhas][colunas] = dados.nextInt(6) + 1;

                }
            }
        }

        return quantDados;
    }

    //=====================================================================================================================
    //Imprime os dados de um jogador lado a lado em ASCII art
    //=====================================================================================================================

    public static void ImprimirDados(int[] dadosJogador) {

        String[] cores = {VERMELHO, AMARELO, VERDE};

        for (int linha = 0; linha < ASCIIDados[0].length; linha++) {
            for (int colunas = 0; colunas < dadosJogador.length; colunas++) {
                if (dadosJogador[colunas] != 0) {
                    System.out.print(cores[colunas] + ASCIIDados[dadosJogador[colunas] - 1][linha] + RESET + "   ");
                }
            }
            System.out.println();
        }
    }

    //=====================================================================================================================
    //Mostra para o jogador da vez: os próprios dados, os dados na mesa e a última tentativa
    //=====================================================================================================================

    public static void MostrarVez(int[][] quantDados, int jogador) {

        naMesa = 0;
        for (int linhas = 0; linhas < quantDados.length; linhas++) {
            for (int colunas = 0; colunas < quantDados[linhas].length; colunas++) {
                if (quantDados[linhas][colunas] != 0) {
                    naMesa++;
                }
            }
        }

        System.out.println("===== Vez do Jogador " + (jogador + 1) + " =====");

        System.out.println("Seus dados:");
        ImprimirDados(quantDados[jogador]);

        System.out.println("Dados na mesa: " + naMesa);

        if (ultmTent[0] == 0) {
            System.out.println("Última tentativa: nenhuma, você começa a rodada.");
        }
        else if (ultmTent[1] == 1) {
            System.out.println("Última tentativa: " + ultmTent[0] + " coringa(s)");
        }
        else {
            System.out.println("Última tentativa: " + ultmTent[0] + " dado(s) com a face " + ultmTent[1]);
        }
    }

    //=====================================================================================================================
    //Verifica se a jogada está correta
    //=====================================================================================================================

    public static char[] JogadaCouE(char[] tent){

        tent = new char[3];
        boolean x = false;

            while (true) {

                if (x == true) {
                    return tent;
                }

                tent = Mediador();

                if (tent[0] == 'D') {
                    if (ultmTent[0] != 0) {
                        x = true;
                    }
                }
                else if (Character.isDigit(tent[0]) && Character.isDigit(tent[1]) && Character.isDigit(tent[2])) {

                    int quant = Character.getNumericValue(tent[0]) * 10 + Character.getNumericValue(tent[1]);
                    int face = Character.getNumericValue(tent[2]);

                    if (quant >= 1 && quant <= naMesa && face >= 1 && face <= 6) {

                        if (ultmTent[0] == 0) {
                            x = true;
                        }
                        else if (ultmTent[1] != 1 && face != 1) {
                            if ((quant > ultmTent[0]) || (quant == ultmTent[0] && face > ultmTent[1])) {
                                x = true;
                            }
                        }
                        else if (ultmTent[1] != 1 && face == 1) {
                            if (quant >= ultmTent[0] / 2) {
                                x = true;
                            }
                        }
                        else if (ultmTent[1] == 1 && face == 1) {
                            if (quant > ultmTent[0]) {
                                x = true;
                            }
                        }
                        else if (ultmTent[1] == 1 && face != 1) {
                            if (quant >= ultmTent[0] * 2 + 1) {
                                x = true;
                            }
                        }
                    }

                    if (x == true) {
                        ultmTent[0] = quant;
                        ultmTent[1] = face;
                    }
                }

                if (x == false) {
                    System.out.println("Jogada inválida, tente novamente.");
                }
            }
        }

    //=====================================================================================================================
    //Soma a quantidade de dados e verifica a tentativa
    //=====================================================================================================================

    public static boolean Duvidar(int[][] dados) {

        int quant = ultmTent[0];
        int face = ultmTent[1];

        int[] value = {0, 0, 0, 0, 0, 0};

        boolean resp = false;

        for(int linhas = 0; linhas < dados.length; linhas++) {
            for(int colunas = 0; colunas < dados[linhas].length; colunas++) {

                switch (dados[linhas][colunas]) {
                    case 1:
                        value[0]++;
                        break;
                    case 2:
                        value[1]++;
                        break;
                    case 3:
                        value[2]++;
                        break;
                    case 4:
                        value[3]++;
                        break;
                    case 5:
                        value[4]++;
                        break;
                    case 6:
                        value[5]++;
                        break;

                }
            }
        }

        switch (face) {
            case 1:
            if (quant <= value[0]) {
                resp = true;
            }
            break;

            case 2:
            if (quant <= value[1] + value[0]) {
                resp = true;
            }
            break;

            case 3:
            if (quant <= value[2] + value[0]) {
                resp = true;
            }
            break;

            case 4:
            if (quant <= value[3] + value[0]) {
                resp = true;
            }
            break;

            case 5:
            if (quant <= value[4] + value[0]) {
                resp = true;
            }
            break;

            case 6:
            if (quant <= value[5] + value[0]) {
                resp = true;
            }
            break;
       }

       return resp;
    }

    //=====================================================================================================================
    //Adiciona a tentativa
    //=====================================================================================================================
    public static char[] Mediador() {

        char[] tent = new char[3];

            System.out.println("Digite sua tentativa: quantidade e face (ex.: 3 5), ou D para duvidar.");

            String entrada = READ.next();

            if (entrada.equalsIgnoreCase("D")) {
                tent[0] = 'D';
            }
            else {

                if (entrada.length() == 1) {
                    tent[0] = '0';
                    tent[1] = entrada.charAt(0);
                }
                else if (entrada.length() == 2) {
                    tent[0] = entrada.charAt(0);
                    tent[1] = entrada.charAt(1);
                }
                else {
                    tent[0] = 'X';
                }

                tent[2] = READ.next().charAt(0);
            }

    return tent;
    }

    //=====================================================================================================================
    //Esconde os dados do jogador anterior e espera o próximo jogador assumir o terminal
    //=====================================================================================================================

    public static void LimparTela(int jogador) {

        System.out.println("Aperte Enter para esconder a tela.");
        READ.nextLine();
        READ.nextLine();

        for (int i = 0; i < 50; i++) {
            System.out.println();
        }

        System.out.println("Passe para o Jogador " + (jogador + 1) + " e aperte Enter.");
        READ.nextLine();
    }

    //=====================================================================================================================
    //Revela os dados de todos os jogadores que ainda estão no jogo
    //=====================================================================================================================

    public static void MostrarTodos(int[][] quantDados) {

        System.out.println("===== Dados revelados =====");

        for (int linhas = 0; linhas < quantDados.length; linhas++) {
            if (JogadorAtivo(quantDados, linhas)) {
                System.out.println("Jogador " + (linhas + 1) + ":");
                ImprimirDados(quantDados[linhas]);
            }
        }
    }

    //=====================================================================================================================
    //Retira um dado do jogador, transformando o primeiro valor diferente de 0 da linha dele em 0
    //=====================================================================================================================

    public static void PerderDado(int[][] quantDados, int jogador) {

        for (int colunas = 0; colunas < quantDados[jogador].length; colunas++) {
            if (quantDados[jogador][colunas] != 0) {
                quantDados[jogador][colunas] = 0;
                return;
            }
        }
    }

    //=====================================================================================================================
    //Verifica se o jogador ainda tem pelo menos um dado (linha não está toda zerada)
    //=====================================================================================================================

    public static boolean JogadorAtivo(int[][] quantDados, int jogador) {

        for (int colunas = 0; colunas < quantDados[jogador].length; colunas++) {
            if (quantDados[jogador][colunas] != 0) {
                return true;
            }
        }
        return false;
    }

    //=====================================================================================================================
    //Conta quantos jogadores ainda têm dados
    //=====================================================================================================================

    public static int QuantidadeAtivos(int[][] quantDados) {

        int ativos = 0;
        for (int linhas = 0; linhas < quantDados.length; linhas++) {
            if (JogadorAtivo(quantDados, linhas)) {
                ativos++;
            }
        }
        return ativos;
    }

    //=====================================================================================================================
    //Retorna o próximo jogador que ainda tem dados, voltando ao primeiro depois do último
    //=====================================================================================================================

    public static int ProximoJogador(int[][] quantDados, int atual) {

        int proximo = atual;
        do {
            proximo = (proximo + 1) % quantDados.length;
        } while (!JogadorAtivo(quantDados, proximo));

        return proximo;
    }

    //=====================================================================================================================
    //Joga uma rodada inteira, do primeiro palpite até alguém duvidar, e retorna o jogador que perde o dado
    //=====================================================================================================================

    public static int Rodada(int[][] quantDados, int inicio) {

        ultmTent[0] = 0;
        ultmTent[1] = 0;

        int atual = inicio;
        int ultimoApostou = -1;

        while (true) {

            LimparTela(atual);
            MostrarVez(quantDados, atual);

            char[] tent = JogadaCouE(null);

            if (tent[0] == 'D') {

                System.out.println();
                System.out.println("Jogador " + (atual + 1) + " duvidou do Jogador " + (ultimoApostou + 1) + "!");
                MostrarTodos(quantDados);

                if (Duvidar(quantDados)) {
                    System.out.println("A tentativa estava certa! Jogador " + (atual + 1) + " perde um dado.");
                    return atual;
                }
                else {
                    System.out.println("A tentativa estava errada! Jogador " + (ultimoApostou + 1) + " perde um dado.");
                    return ultimoApostou;
                }
            }

            ultimoApostou = atual;
            atual = ProximoJogador(quantDados, atual);
        }
    }

    //=====================================================================================================================
    //Mostra quantos dados cada jogador ainda tem
    //=====================================================================================================================

    public static void Placar(int[][] quantDados) {

        System.out.println("===== Placar =====");

        for (int linhas = 0; linhas < quantDados.length; linhas++) {
            int quant = 0;
            for (int colunas = 0; colunas < quantDados[linhas].length; colunas++) {
                if (quantDados[linhas][colunas] != 0) {
                    quant++;
                }
            }
            System.out.println("Jogador " + (linhas + 1) + ": " + quant + " dado(s)");
        }
    }

    //=====================================================================================================================
    //Anuncia o único jogador que ainda tem dados
    //=====================================================================================================================

    public static void Vencedor(int[][] quantDados) {

        for (int linhas = 0; linhas < quantDados.length; linhas++) {
            if (JogadorAtivo(quantDados, linhas)) {
                System.out.println();
                System.out.println("***** Jogador " + (linhas + 1) + " venceu! *****");
            }
        }
    }

    public static String AMARELO = "\u001B[93m";
    public static String ROXO = "\u001B[95m";
    public static String VERMELHO = "\u001B[91m";
    public static String VERDE = "\u001B[92m";
    public static String RESET = "\u001B[0m";

    public static String[] ASCIITitulo = {
        " _____          _____ _____ _   _ _    _  ____  ",
        "|  __ \\   /\\   |  __ \\_   _| \\ | | |  | |/ __ \\ ",
        "| |  | | /  \\  | |  | || | |  \\| | |__| | |  | |",
        "| |  | |/ /\\ \\ | |  | || | | . ` |  __  | |  | |",
        "| |__| / ____ \\| |__| || |_| |\\  | |  | | |__| |",
        "|_____/_/    \\_\\_____/_____|_| \\_|_|  |_|\\____/ ",
    };

    //=====================================================================================================================
    //Empurra o conteúdo da tela para cima com linhas em branco
    //=====================================================================================================================

    public static void PularLinhas() {

        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

    //=====================================================================================================================
    //Desenha uma opção do menu: um quadrado com o número e, ao lado, um retângulo com o nome da opção
    //=====================================================================================================================

    public static void Opcao(char numero, String nome) {

        int largura = 17;
        int esquerda = (largura - nome.length()) / 2;
        int direita = largura - nome.length() - esquerda;
        String nomeCentralizado = " ".repeat(esquerda) + nome + " ".repeat(direita);

        System.out.println(ROXO + "              +---+   +" + "-".repeat(largura) + "+" + RESET);
        System.out.println(ROXO + "              | " + AMARELO + numero + ROXO + " |   |" + AMARELO + nomeCentralizado + ROXO + "|" + RESET);
        System.out.println(ROXO + "              +---+   +" + "-".repeat(largura) + "+" + RESET);
        System.out.println();
    }

    //=====================================================================================================================
    //Mostra o menu inicial e executa a opção escolhida (1 = jogar, 2 = tutorial, 3 = sair)
    //=====================================================================================================================

    public static void Menu() {

        while (true) {

            PularLinhas();

            for (int linhas = 0; linhas < ASCIITitulo.length; linhas++) {
                System.out.println(AMARELO + "   " + ASCIITitulo[linhas] + RESET);
            }
            System.out.println();
            System.out.println();

            Opcao('1', "JOGAR");
            Opcao('2', "TUTORIAL");
            Opcao('3', "SAIR");

            System.out.println("              Desenvolvido por: Lucas e Kaleb");
            System.out.println();

            System.out.println("Digite o número da opção.");
            char escolha = ReadC();

            while (escolha != '1' && escolha != '2' && escolha != '3') {
                System.out.println("Opção inválida. Digite 1, 2 ou 3.");
                escolha = ReadC();
            }

            if (escolha == '1') {
                Jogar();
            }
            else if (escolha == '2') {
                Tutorial();
            }
            else {
                System.out.println("Até a próxima!");
                return;
            }
        }
    }

    //=====================================================================================================================
    //Explica as regras e os comandos do jogo
    //=====================================================================================================================

    public static void Tutorial() {

        PularLinhas();

        System.out.println("=============================== TUTORIAL ===============================");
        System.out.println();
        System.out.println("OBJETIVO");
        System.out.println("  Ser o último jogador com dados na mesa.");
        System.out.println();
        System.out.println("COMO FUNCIONA");
        System.out.println("  Cada jogador começa com 3 dados e só vê os próprios dados.");
        System.out.println("  Na sua vez, você faz uma tentativa: quantos dados de uma face você");
        System.out.println("  acha que existem na mesa, somando os dados de TODOS os jogadores.");
        System.out.println();
        System.out.println("  O próximo jogador precisa fazer uma tentativa maior:");
        System.out.println("    - mesma quantidade com uma face maior (3 dados de 5 -> 3 dados de 6), ou");
        System.out.println("    - uma quantidade maior (3 dados de 5 -> 4 dados de 2).");
        System.out.println();
        System.out.println("CORINGA");
        System.out.println("  O 1 é coringa: ele conta junto com qualquer face.");
        System.out.println("  Também dá para apostar só nos coringas:");
        System.out.println("    - para ir para coringa: pelo menos a metade da última quantidade,");
        System.out.println("      arredondada para baixo (5 dados de 4 -> 2 coringas).");
        System.out.println("    - para sair do coringa: pelo menos o dobro + 1 (2 coringas -> 5 dados de 3).");
        System.out.println();
        System.out.println("DUVIDAR");
        System.out.println("  Se achar que a última tentativa é exagerada, digite D.");
        System.out.println("  Todos os dados são revelados e contados (com os coringas).");
        System.out.println("    - Se tiver PELO MENOS a quantidade apostada, quem duvidou perde um dado.");
        System.out.println("    - Se tiver menos, quem fez a tentativa perde um dado.");
        System.out.println("  Quem perdeu começa a próxima rodada. Sem dados, o jogador sai do jogo.");
        System.out.println();
        System.out.println("COMANDOS");
        System.out.println("  3 5  ->  \"acho que tem 3 dados com a face 5 na mesa\"");
        System.out.println("  2 1  ->  \"acho que tem 2 coringas na mesa\"");
        System.out.println("  D    ->  duvidar da última tentativa");
        System.out.println();
        System.out.println("  Na troca de jogador, a tela é escondida: passe o teclado e aperte Enter.");
        System.out.println();
        System.out.println("========================================================================");
        System.out.println();
        System.out.println("Aperte Enter para voltar ao menu.");

        READ.nextLine();
        READ.nextLine();
    }

    //=====================================================================================================================
    //Joga uma partida inteira, da escolha da quantidade de jogadores até o vencedor
    //=====================================================================================================================

    public static void Jogar() {

        System.out.println("Insira a quantidade de jogadores (2 a 6).");
        int quantJog = QuantidadeJog();

        while (quantJog < 2 || quantJog > 6) {
            System.out.println("Quantidade inválida. Insira de 2 a 6 jogadores.");
            quantJog = QuantidadeJog();
        }

        int[][] matriz = null;
        int inicio = 0;

        while (matriz == null || QuantidadeAtivos(matriz) > 1) {

            matriz = Dados(matriz, quantJog);

            int perdedor = Rodada(matriz, inicio);
            PerderDado(matriz, perdedor);
            if (JogadorAtivo(matriz, perdedor)) {
                inicio = perdedor;
            }
            else {
                System.out.println("Jogador " + (perdedor + 1) + " ficou sem dados e saiu do jogo!");
                if (QuantidadeAtivos(matriz) > 0) {
                    inicio = ProximoJogador(matriz, perdedor);
                }
            }

            System.out.println();
            Placar(matriz);
            System.out.println();
        }

        Vencedor(matriz);

        System.out.println();
        System.out.println("Aperte Enter para voltar ao menu.");

        READ.nextLine();
        READ.nextLine();

    }

    public static Scanner READ = new Scanner(System.in);
    public static void main(String[] args) {

        Menu();
    }
}
