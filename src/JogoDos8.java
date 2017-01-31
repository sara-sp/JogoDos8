import java.util.*;

public class JogoDos8{

    public static void main (String args[]){
        Scanner inp = new Scanner (System.in);

        int configIni[] = new int [9];
        int configFin[] = new int [9];
        long tempoIni;

        System.out.println("Introduza a configuracao inicial");

        for(int i=0; i<9; i++)
            configIni[i] = inp.nextInt();


        //int configIni[] = {3, 4, 2, 5, 1, 7, 6, 0, 8};

        System.out.println();
        System.out.println("Introduza a configuracao final");

        for(int i=0; i<9; i++)
            configFin[i]= inp.nextInt();

        //int configFin[] = {1, 2, 3, 8, 0, 4, 7, 6, 5};

        int parIni = inversoes(configIni);
        int parFin = inversoes(configFin);

        if(parIni != parFin){
            System.out.println("\nNao ha solucao para chegar do seu estado inicial ao final.");
            return;
        }

        System.out.println();
        System.out.println("1 -> Pesquisa em Largura");
        System.out.println("2 -> Pesquisa em Profundidade");
        System.out.println("3 -> Pesquisa em Profundidade Iterativa");
        System.out.println("4 -> Pesquisa Gulosa");
        System.out.println("5 -> Pesquisa A*");
        System.out.println();

        System.out.println("Selecione o numero de pesquisa pretendida:");
        int pesqSel = inp.nextInt();
        System.out.println();

        tempoIni = System.currentTimeMillis();

        int espacoVazio=0;

        for(int i=0; i<9; i++)
            if(configIni[i]==0)
                espacoVazio = i;

        TabNo tab = new TabNo(configIni, espacoVazio);

        switch(pesqSel){
            case 1:
                System.out.println("Selecionou a Pesquisa em Largura (existe soluçao):\n");
                largura(configIni, configFin, tempoIni, tab, espacoVazio);
                break;
            case 2:
                System.out.println("Selecionou a Pesquisa em Profundidade (existe soluçao):\n");
                profundidade(configIni, configFin, tempoIni, tab, espacoVazio);
                break;
            case 3:
                System.out.println("Selecionou a Pesquisa em Profundidade Iterativa (existe soluçao):\n");
                profundidadeIte(configIni, configFin, tempoIni, tab, espacoVazio);
                break;
            case 4:
                System.out.println("Selecionou a Pesquisa Gulosa (existe soluçao):\n");
                gulosa(configIni, configFin, tempoIni, tab, espacoVazio);
                break;
            case 5:
                System.out.println("Selecionou a Pesquisa A* (existe soluçao):\n");
                aEstrela(configIni, configFin, tempoIni, tab, espacoVazio);
                break;
            default:
                System.out.println("ERRO: Numero de pesquisa invalido");
                break;
        }
    }

    static int inversoes(int config[]){
        int nInversoes=0, aux=1;

        int i = 0, configLength = config.length;
        while (i < configLength) {
            int aConfig = config[i];
            while (aux <= (config.length - 1)) {
                if (aConfig != 0 && config[aux] != 0 && aConfig > config[aux])
                    nInversoes++;

                aux++;
            }
            i++;
        }

        if(nInversoes%2==0)
            return 1;

        return 2;
    }

    static void largura(int configIni[], int configFin[], long tempoIni, TabNo tab, int espacoVazio){
        TabNo solucao = new Pesquisa(configIni, espacoVazio).largura(tab, configFin);
        imprime(configIni, configFin, tempoIni, solucao);
    }

    static void profundidade(int configIni[], int configFin[], long tempoIni, TabNo tab, int espacoVazio){
        TabNo solucao = new Pesquisa(configIni, espacoVazio).profundidade(tab, configFin);
        imprime(configIni, configFin, tempoIni, solucao);
    }

    static void profundidadeIte(int configIni[], int configFin[], long tempoIni, TabNo tab, int espacoVazio){
        TabNo solucao = new Pesquisa(configIni, espacoVazio).profundidadeIte(tab, configFin);
        imprime(configIni, configFin, tempoIni, solucao);
    }

    static void gulosa(int configIni[], int configFin[], long tempoIni, TabNo tab, int espacoVazio){
        TabNo solucao = new Pesquisa(configIni, espacoVazio).gulosa(tab, configFin);
        imprime(configIni, configFin, tempoIni, solucao);
    }

    static void aEstrela(int configIni[], int configFin[], long tempoIni, TabNo tab, int espacoVazio){
        TabNo solucao = new Pesquisa(configIni, espacoVazio).aEstrela(tab, configFin);
        imprime(configIni, configFin, tempoIni, solucao);
    }

    static void imprime(int configIni[], int configFin[], long tempoIni, TabNo solucao){

        double tempo = (double) (System.currentTimeMillis()-tempoIni)/1000;

        System.out.println("A sua configuracao inicial");
        imprimeTabConfig(configIni);

        System.out.println("A sua configuracao final");
        imprimeTabConfig(configFin);

        System.out.println("Numero de nos usados: " + solucao.getNumNo());  //espaco
        System.out.println("Profundidade: " + solucao.getProfundidade());
        System.out.println("Tempo de execucao: " + tempo + " segundos");
    }

    static void imprimeTabConfig(int config[]){
        //primeira linha
        for (int j = 0; j < 13; j++)
            System.out.print("-");
        System.out.println();

        for (int i = 0; i < 9; i++) {
            if(i==0)
                System.out.print("| ");
            if(config[i]==0)
                System.out.print("  | "); //espaco vazio
            else
                System.out.print(config[i] + " | ");

            if (i % 3 == 2) {
                System.out.println();

                for (int j = 0; j < 13; j++)
                    System.out.print("-");
                System.out.println();

                if(i!=8)
                    System.out.print("| ");
            }
        }
        System.out.println();
    }
}