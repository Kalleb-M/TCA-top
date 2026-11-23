import java.util.Scanner;
import java.util.Random;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Bahhhh {


    //=====================================================================================================================
    //Toca um som
    //=====================================================================================================================
    public static Clip tocarSom(String caminhoArquivoSom, boolean esperarTerminar){

        try {
            // Abrindo o arquivo de som
            File soundFile = new File(caminhoArquivoSom);
            if (!soundFile.exists()) {
                System.out.println("Arquivo de som não encontrado: " + caminhoArquivoSom);
                return null;
            }

            // Criando um AudioInputStream
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);

            // Obtendo as informações do formato de áudio
            AudioFormat format = audioStream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);

            // Criando o Clip e carregando o áudio
            Clip audioClip = (Clip) AudioSystem.getLine(info);
            audioClip.open(audioStream);

            // O áudio já foi carregado no Clip, então o arquivo pode ser fechado
            audioStream.close();

            // Quando o som chegar ao fim, libera os recursos automaticamente
            // (se ele só foi pausado no meio, continua aberto para poder voltar a tocar)
            audioClip.addLineListener(evento -> {
                if (evento.getType() == LineEvent.Type.STOP && audioClip.getFramePosition() >= audioClip.getFrameLength()) {
                    audioClip.close();
                }
            });

            // Tocando o áudio
            audioClip.start();

            // Se pediu para esperar, segura o programa enquanto o som toca
            if (esperarTerminar) {
                Thread.sleep(audioClip.getMicrosecondLength() / 1000);
            }

            // Devolvendo o som
            return audioClip;

        // Avisando qual foi o problema, se algo der errado
        } catch (UnsupportedAudioFileException e) {
            System.err.println("O formato de áudio não é suportado.");
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            System.err.println("Linha de áudio não disponível.");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo de áudio.");
            e.printStackTrace();
        } catch (InterruptedException e) {
            System.err.println("O programa foi interrompido.");
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            System.err.println("Nenhuma saída de som disponível para este áudio.");
        }

        // Se deu algum problema, não tem som para devolver
        return null;
    }

    //Lê valores inteiros
    public static int Read(){
        int value = READ.nextInt();
        return value;
    }

    // Guarda a música de fundo
    public static Clip somFundo;

    //=====================================================================================================================
    //Deixa a música de fundo em volume baixo e em loop
    //=====================================================================================================================
    public static void TocarFundo(String caminhoArquivoSom) {

        // Volume da música de fundo em decibéis
        float volumeFundo = -20.0f;

        // Toca a música
        somFundo = tocarSom(caminhoArquivoSom, false);

        // Se não deu para tocar, o jogo continua sem a música
        if (somFundo == null) {
            return;
        }

        // Abaixa o volume, sem passar do mínimo e do máximo que o computador aceita
        if (somFundo.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
            FloatControl volume = (FloatControl) somFundo.getControl(FloatControl.Type.MASTER_GAIN);
            volume.setValue(Math.max(volume.getMinimum(), Math.min(volumeFundo, volume.getMaximum())));
        }

        // Colocando a música em loop
        somFundo.loop(Clip.LOOP_CONTINUOUSLY);
    }

    //=====================================================================================================================
    //Pausa a música de fundo, guardando o ponto em que ela parou
    //=====================================================================================================================
    public static void PausarFundo() {

        // Só pausa se a música estiver carregada
        if (somFundo != null) {
            // O stop guarda a posição, então a música pode continuar dali depois
            somFundo.stop();
        }
    }

    //=====================================================================================================================
    //Continua a música de fundo do ponto em que ela foi pausada
    //=====================================================================================================================
    public static void ContinuarFundo() {

        // Só continua se a música estiver carregada
        if (somFundo != null) {
            // Voltando a tocar em loop a partir de onde parou
            somFundo.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    //=====================================================================================================================
    //Para a música de fundo de vez
    //=====================================================================================================================
    public static void PararFundo() {

        // Só para se a música estiver carregada
        if (somFundo != null) {
            // Para a música e libera o arquivo
            somFundo.stop();
            somFundo.close();

            // Marca que não tem mais música carregada
            somFundo = null;
        }
    }

    // Desenhos dos dados
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
        // Lendo quantos jogadores vão jogar
        int quantPlayers = Read();
        return quantPlayers;
    }

    //=====================================================================================================================
    //Define o valor dos dados
    //=====================================================================================================================
    public static int[][] Dados(int[][] quantDados, int quantPlayers) {

        // Criando o sorteador dos dados
        Random dados = new Random();

        // Na primeira rodada, cria a matriz com 3 dados por jogador e marca todos com 1 (dado em jogo)
        if (quantDados == null) {
            quantDados = new int[quantPlayers][3];

            for (int linhas = 0; linhas < quantDados.length; linhas++) {
                for (int colunas = 0; colunas < quantDados[linhas].length; colunas++) {
                    quantDados[linhas][colunas] = 1;
                }
            }
        }

        // Rolando os dados de todos os jogadores
        for (int linhas = 0; linhas < quantDados.length; linhas++) {
            for (int colunas = 0; colunas < quantDados[linhas].length; colunas++) {

                // Os dados perdidos (0) não são rolados
                if (quantDados[linhas][colunas] != 0) {

                    quantDados[linhas][colunas] = dados.nextInt(6) + 1;

                }
            }
        }

        // Devolvendo a matriz com os dados rolados
        return quantDados;
    }

    //=====================================================================================================================
    //Imprime os dados de um jogador em ASCII art
    //=====================================================================================================================
    public static void ImprimirDados(int[] dadosJogador) {

        // Uma cor para cada dado
        String[] cores = {VERMELHO, AMARELO, VERDE};

        // Imprimindo a linha 1 de todos os dados, depois a linha 2 de todos, e assim por diante (deixa os dados lado a lado)
        for (int linha = 0; linha < ASCIIDados[0].length; linha++) {
            for (int colunas = 0; colunas < dadosJogador.length; colunas++) {

                // Pulando os dados perdidos (0)
                if (dadosJogador[colunas] != 0) {
                    System.out.print(cores[colunas] + ASCIIDados[dadosJogador[colunas] - 1][linha] + RESET + "   ");
                }
            }
            System.out.println();
        }
    }

    //=====================================================================================================================
    //Mostra os próprios dados, os dados na mesa e a última tentativa
    //=====================================================================================================================

    public static void MostrarVez(int[][] quantDados, int jogador, int naMesa, int[] ultmTent) {

        // Mostrando de quem é a vez (o índice começa em 0, por isso o + 1)
        System.out.println("===== Vez do Jogador " + (jogador + 1) + " =====");

        // Mostrando só os dados do jogador da vez
        System.out.println("Seus dados:");
        ImprimirDados(quantDados[jogador]);

        // Mostrando quantos dados existem na mesa
        System.out.println("Dados na mesa: " + naMesa);

        // Mostrando a última tentativa (0 = ninguém apostou ainda nesta rodada)
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
    //Verifica se a jogada é válida
    //=====================================================================================================================

    public static char[] JogadaCouE(char[] tent, int naMesa, int[] ultmTent){

        // Cria o vetor da tentativa
        tent = new char[3];
        boolean x = false;

            // Repetr até o jogador fazer uma jogada válida
            while (true) {

                // Jogada válida = devolve a tentativa
                if (x == true) {
                    return tent;
                }

                // Lê a tentativa do jogador
                tent = Mediador();

                // Duvidar só vale se já existir uma tentativa na rodada
                if (tent[0] == 'D') {
                    if (ultmTent[0] != 0) {
                        x = true;
                    }
                }
                // Confere se a quantidade e a face são números
                else if (Character.isDigit(tent[0]) && Character.isDigit(tent[1]) && Character.isDigit(tent[2])) {

                    // Transforma os char em números
                    int quant = Character.getNumericValue(tent[0]) * 10 + Character.getNumericValue(tent[1]);
                    int face = Character.getNumericValue(tent[2]);

                    // A quantidade não pode passar dos dados na mesa, e a face vai de 1 a 6
                    if (quant >= 1 && quant <= naMesa && face >= 1 && face <= 6) {

                        // Primeira tentativa da rodada
                        if (ultmTent[0] == 0) {
                            x = true;
                        }
                        // Valida a tentativa (núm sem coringa para núm não coringa)
                        else if (ultmTent[1] != 1 && face != 1) {
                            if ((quant > ultmTent[0]) || (quant == ultmTent[0] && face > ultmTent[1])) {
                                x = true;
                            }
                        }
                        // Valida a tentativa (núm não coringa para coringa)
                        else if (ultmTent[1] != 1 && face == 1) {
                            if (quant >= ultmTent[0] / 2) {
                                x = true;
                            }
                        }
                        // Valida a tentativa (coringa para coringa)
                        else if (ultmTent[1] == 1 && face == 1) {
                            if (quant > ultmTent[0]) {
                                x = true;
                            }
                        }
                        // Valida a tentativa (coringa para núm não coringa)
                        else if (ultmTent[1] == 1 && face != 1) {
                            if (quant >= ultmTent[0] * 2 + 1) {
                                x = true;
                            }
                        }
                    }

                    // Guarda a tentativa se ela for válida
                    if (x == true) {
                        ultmTent[0] = quant;
                        ultmTent[1] = face;
                    }
                }

                // Avisa quando a jogada não vale
                if (x == false) {
                    System.out.println("Jogada inválida, tente novamente.");
                }
            }
        }

    //=====================================================================================================================
    //Soma a quantidade de dados e verifica a tentativa
    //=====================================================================================================================
    public static boolean Duvidar(int[][] dados, int[] ultmTent) {

        // Pega a última tentativa, que é a que está sendo duvidada
        int quant = ultmTent[0];
        int face = ultmTent[1];

        // Contador de cada face
        int[] value = {0, 0, 0, 0, 0, 0};

        boolean resp = false;

        // Contando quantos dados de cada face existem na mesa
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

        // A tentativa está certa se houver pelo menos a quantidade apostada
        // Coringa conta só os 1 e as outras faces somam os 1 junto
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

       // Devolvendo true se a tentativa estava certa e false se estava errada
       return resp;
    }

    //=====================================================================================================================
    //Adiciona a tentativa
    //=====================================================================================================================
    public static char[] Mediador() {

        // tent[0] e tent[1] guardam a quantidade (dezena e unidade) e tent[2] guarda a face
        char[] tent = new char[3];

            // Pedindo a tentativa
            System.out.println("Digite sua tentativa: quantidade e face (ex.: 3 5), ou D para duvidar.");

            // Lendo a quantidade, ou o D
            String entrada = READ.next();

            // Se digitou D, marca que quer duvidar
            if (entrada.equalsIgnoreCase("D")) {
                tent[0] = 'D';
            }
            else {

                // Quantidade com 1 dígito: completa a dezena com '0' (ex.: "5" vira '0' e '5')
                if (entrada.length() == 1) {
                    tent[0] = '0';
                    tent[1] = entrada.charAt(0);
                }
                // Quantidade com 2 dígitos (ex.: "12" vira '1' e '2')
                else if (entrada.length() == 2) {
                    tent[0] = entrada.charAt(0);
                    tent[1] = entrada.charAt(1);
                }
                // Mais de 2 dígitos: marca como inválida
                else {
                    tent[0] = 'X';
                }

                // Lendo a face
                tent[2] = READ.next().charAt(0);
            }

    // Devolvendo a tentativa
    return tent;
    }

    //=====================================================================================================================
    //Esconde os dados do jogador anterior e espera o próximo jogador assumir o terminal
    //=====================================================================================================================
    public static void LimparTela(int jogador) {

        // Esperando o jogador anterior apertar Enter
        // O primeiro nextLine descarta o Enter que sobrou da última leitura
        System.out.println("Aperte Enter para esconder a tela.");
        READ.nextLine();
        READ.nextLine();

        // Empurrando os dados do jogador anterior para fora da tela
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }

        // Esperando o próximo jogador assumir o teclado
        System.out.println("Passe para o Jogador " + (jogador + 1) + " e aperte Enter.");
        READ.nextLine();
    }

    //=====================================================================================================================
    //Revela os dados de todos os jogadores que ainda estão no jogo
    //=====================================================================================================================
    public static void MostrarTodos(int[][] quantDados) {

        System.out.println("===== Dados revelados =====");

        // Mostrando os dados de cada jogador que ainda está no jogo
        for (int linhas = 0; linhas < quantDados.length; linhas++) {
            if (JogadorAtivo(quantDados, linhas)) {
                System.out.println("Jogador " + (linhas + 1) + ":");
                ImprimirDados(quantDados[linhas]);
            }
        }
    }

    //=====================================================================================================================
    //Retira um dado do jogador
    //=====================================================================================================================
    public static void PerderDado(int[][] quantDados, int jogador) {

        // Procurando o primeiro dado que o jogador ainda tem
        for (int colunas = 0; colunas < quantDados[jogador].length; colunas++) {
            if (quantDados[jogador][colunas] != 0) {

                // Zerando esse dado e parando, para tirar só um
                quantDados[jogador][colunas] = 0;
                return;
            }
        }
    }

    //=====================================================================================================================
    //Verifica se o jogador ainda tem pelo menos um dado
    //=====================================================================================================================
    public static boolean JogadorAtivo(int[][] quantDados, int jogador) {

        // Se achar algum dado diferente de 0, o jogador ainda está no jogo
        for (int colunas = 0; colunas < quantDados[jogador].length; colunas++) {
            if (quantDados[jogador][colunas] != 0) {
                return true;
            }
        }

        // Todos os dados são 0: o jogador saiu
        return false;
    }

    //=====================================================================================================================
    //Conta quantos jogadores ainda têm dados
    //=====================================================================================================================
    public static int QuantidadeAtivos(int[][] quantDados) {

        // Contando os jogadores que ainda têm pelo menos um dado
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

        // Passando para o próximo jogador; o % volta para o primeiro depois do último
        // Repete enquanto cair em alguém que já saiu do jogo
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

        // Criando a última tentativa da rodada: [0] = quantidade, [1] = face (0 = ninguém apostou ainda)
        int[] ultmTent = new int[2];

        // Contando os dados que estão na mesa nesta rodada (os que não são 0)
        int naMesa = 0;
        for (int linhas = 0; linhas < quantDados.length; linhas++) {
            for (int colunas = 0; colunas < quantDados[linhas].length; colunas++) {
                if (quantDados[linhas][colunas] != 0) {
                    naMesa++;
                }
            }
        }

        // Guardando de quem é a vez e quem fez a última tentativa
        int atual = inicio;
        int ultimoApostou = -1;

        // Passando a vez de jogador em jogador até alguém duvidar
        while (true) {

            // Escondendo a tela e mostrando as informações do jogador da vez
            LimparTela(atual);
            MostrarVez(quantDados, atual, naMesa, ultmTent);

            // Lendo a jogada
            char[] tent = JogadaCouE(null, naMesa, ultmTent);

            // Alguém duvidou: revela os dados e decide quem perde
            if (tent[0] == 'D') {

                System.out.println();
                System.out.println("Jogador " + (atual + 1) + " duvidou do Jogador " + (ultimoApostou + 1) + "!");
                MostrarTodos(quantDados);

                // Tentativa certa: quem duvidou perde; tentativa errada: quem apostou perde
                if (Duvidar(quantDados, ultmTent)) {
                    System.out.println("A tentativa estava certa! Jogador " + (atual + 1) + " perde um dado.");
                    return atual;
                }
                else {
                    System.out.println("A tentativa estava errada! Jogador " + (ultimoApostou + 1) + " perde um dado.");
                    return ultimoApostou;
                }
            }

            // Ninguém duvidou: guarda quem apostou e passa a vez
            ultimoApostou = atual;
            atual = ProximoJogador(quantDados, atual);
        }
    }

    //=====================================================================================================================
    //Mostra quantos dados cada jogador ainda tem
    //=====================================================================================================================
    public static void Placar(int[][] quantDados) {

        System.out.println("===== Placar =====");

        // Contando e mostrando os dados de cada jogador
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

        // Procurando o único jogador que ainda tem dados
        for (int linhas = 0; linhas < quantDados.length; linhas++) {
            if (JogadorAtivo(quantDados, linhas)) {
                System.out.println();
                System.out.println("***** Jogador " + (linhas + 1) + " venceu! *****");
            }
        }
    }

    // Cores do terminal (códigos ANSI): o texto depois do código fica daquela cor, até o RESET
    public static String AMARELO = "\u001B[93m";
    public static String ROXO = "\u001B[95m";
    public static String VERMELHO = "\u001B[91m";
    public static String VERDE = "\u001B[92m";
    public static String RESET = "\u001B[0m";

    // Título do menu em ASCII art (cada \ do desenho é escrito como \\ dentro da String)
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

        // Imprimindo 50 linhas em branco
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

    //=====================================================================================================================
    //Desenha uma opção do menu
    //=====================================================================================================================
    public static void Opcao(char numero, String nome) {

        // Centralizando o nome dentro de um retângulo de 17 espaços
        int largura = 17;
        int esquerda = (largura - nome.length()) / 2;
        int direita = largura - nome.length() - esquerda;
        String nomeCentralizado = " ".repeat(esquerda) + nome + " ".repeat(direita);

        // Desenhando o quadrado e o retângulo em roxo, com o número e o nome em amarelo
        System.out.println(ROXO + "              +---+   +" + "-".repeat(largura) + "+" + RESET);
        System.out.println(ROXO + "              | " + AMARELO + numero + ROXO + " |   |" + AMARELO + nomeCentralizado + ROXO + "|" + RESET);
        System.out.println(ROXO + "              +---+   +" + "-".repeat(largura) + "+" + RESET);
        System.out.println();
    }

    //=====================================================================================================================
    //Mostra o menu inicial e executa a opção escolhida
    //=====================================================================================================================
    public static void Menu() {

        // Mostrando o menu de novo sempre que uma partida ou o tutorial terminar
        while (true) {

            PularLinhas();

            // Desenhando o título em amarelo
            for (int linhas = 0; linhas < ASCIITitulo.length; linhas++) {
                System.out.println(AMARELO + "   " + ASCIITitulo[linhas] + RESET);
            }
            System.out.println();
            System.out.println();

            // Desenhando as opções
            Opcao('1', "JOGAR");
            Opcao('2', "TUTORIAL");
            Opcao('3', "SAIR");

            // Créditos
            System.out.println("              Desenvolvido por: Lucas e Kaleb");
            System.out.println();

            // Lendo a opção escolhida
            System.out.println("Digite o número da opção.");
            char escolha = ReadC();

            // Repetindo a leitura até digitar 1, 2 ou 3
            while (escolha != '1' && escolha != '2' && escolha != '3') {
                System.out.println("Opção inválida. Digite 1, 2 ou 3.");
                escolha = ReadC();
            }

            // Executando a opção escolhida
            if (escolha == '1') {
                Jogar();
            }
            else if (escolha == '2') {
                Tutorial();
            }
            // Opção 3: sai do menu e o programa termina
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

        // Mostrando as regras
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
        // Esperando o Enter para voltar ao menu
        // O primeiro nextLine descarta o Enter que sobrou da escolha no menu
        System.out.println("Aperte Enter para voltar ao menu.");

        READ.nextLine();
        READ.nextLine();
    }

    //=====================================================================================================================
    //Roda uma partida
    //=====================================================================================================================
    public static void Jogar() {

        // Recomeçando a música de fundo do início a cada partida
        PararFundo();
        TocarFundo("fundo.wav");

        // Lendo a quantidade de jogadores
        System.out.println("Insira a quantidade de jogadores");
        int quantJog = QuantidadeJog();

        // Repetindo até ficar entre 2 e 6
        while (quantJog < 2 || quantJog > 6) {
            System.out.println("Quantidade inválida, insira de 2 a 6 jogadores");
            quantJog = QuantidadeJog();
        }

        // A matriz começa vazia (o método Dados cria ela) e o Jogador 1 começa
        int[][] matriz = null;
        int inicio = 0;

        // Jogando rodadas enquanto houver mais de um jogador com dados
        while (matriz == null || QuantidadeAtivos(matriz) > 1) {

            // Rolando os dados
            matriz = Dados(matriz, quantJog);

            // Jogando a rodada e tirando um dado de quem perdeu
            int perdedor = Rodada(matriz, inicio);
            PerderDado(matriz, perdedor);

            // Pausando a música, tocando o som de perder dado e continuando a música
            PausarFundo();
            tocarSom("morte.wav", true);
            ContinuarFundo();

            // Quem perdeu começa a próxima rodada; se saiu do jogo, começa o próximo que ainda tem dados
            if (JogadorAtivo(matriz, perdedor)) {
                inicio = perdedor;
            }
            else {
                System.out.println("Jogador " + (perdedor + 1) + " ficou sem dados e saiu do jogo!");
                if (QuantidadeAtivos(matriz) > 0) {
                    inicio = ProximoJogador(matriz, perdedor);
                }
            }

            // Mostrando quantos dados cada um tem
            System.out.println();
            Placar(matriz);
            System.out.println();
        }

        // Anunciando o vencedor, parando a música de fundo e tocando a de vitória
        Vencedor(matriz);
        PararFundo();
        tocarSom("vitoria.wav", true);

        // Esperando o Enter para voltar ao menu
        // O primeiro nextLine descarta o Enter que sobrou da última tentativa
        System.out.println();
        System.out.println("Aperte Enter para voltar ao menu.");

        READ.nextLine();
        READ.nextLine();
    }

    // Leitor do teclado usado no jogo inteiro
    public static Scanner READ = new Scanner(System.in);
       public static void main(String[] args) {

        // Ligando a música de fundo
        TocarFundo("fundo.wav");

        // Abrindo o menu
        Menu();
    }
}
