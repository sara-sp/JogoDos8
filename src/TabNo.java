public class TabNo{

    int config[] = new int [9];
    int espacoVazio, proxJogada;
    String aux;
    Operador jogada;
    TabNo pai;
    int profundidade, nNo, index, distancia;

    TabNo(int conf[], int vazio){
        config = conf;
        espacoVazio = vazio;
        jogada = null;
        proxJogada = 0;
        profundidade = 0;
        distancia = 0;

        aux = "";

        for (int aConf : conf) {
            aux += aConf;
        }

        aux = aux.trim();       //retira espacos

        index = Integer.parseInt(aux);

    }

    //Funcao que gera o proximo movimento possivel
    public TabNo getProxFilho() {
        //verifica validade do movimento
        int newEspacoVazio = tentaMover(Operador.translate(this.proxJogada));
        //Passa para o proximo movimento
        this.proxJogada += 1;
        //se movimento valido...
        if (newEspacoVazio != -1) {
            //gera novo estado
            int newConfig[] = fazMovimento(newEspacoVazio);
            //cria no filho com novo estado e posicao do branco
            TabNo filho = new TabNo(newConfig, newEspacoVazio);
            //guarda movimento que deu origem ao filho
            filho.setJogada(this.proxJogada - 1);
            //guarda profundidade do filho
            filho.setProfundidade(this.profundidade + 1);
            //guarda o pai
            filho.setPai(this);
            //retorna filho
            return filho;
            //se movimento invalido...
        } 
        else
            return null;
    }

    //retorna profundidade do no
    public int getProfundidade() {
        return this.profundidade;
    }

    //retorna numero de nos totais
    public int getNumNo() {
        return this.nNo;
    }

    //retorna numero de quadrados coincidentes com o final
    public int getDistancia() {
        return this.distancia;
    }

    //retorna id do no gerado
    public int getId() {
        return index;
    }

    //guarda numero total de nos
    public void setNumNo(int val) {
        this.nNo = val;
    }

    //verifica se e possivel realizar mais movimentos
    public boolean podeMover() {
        return this.proxJogada <= 3;
    }

    //reset ao movimento, usado no iddfs
    public void reiniciaMovimento() {
        this.proxJogada = 0;
    }

    //Heuristica
    public void calcDist(int finalState[]) {
        //pecas fora do sitio
        this.distancia = contaPecas(finalState);
    }

    //soma valor da heuristica a profundidade
    public void calcHeuristica() {
        this.distancia += this.profundidade;
        if (this.distancia < this.pai.getDistancia())
            this.distancia = this.pai.getDistancia();
    }

    //private functions
    //conta numero de casas iguais a config final
    public int contaPecas(int finalState[]) {
        int matches = 0;
        for (int i = 0; i < this.config.length; i++) {
            if (this.config[i] == finalState[i] && this.config[i] != 0)
                matches++;
        }
        return 8 - matches;
    }

    //Movimentos
    private int tentaMover(Operador m) {
        //se movimento contrario ao anterior retorna -1(invalida)
        if (jogada == m.opposite())
            return -1;
        int posX = this.espacoVazio % 3;
        int posY = this.espacoVazio / 3;
        if (m == Operador.BAIXO) {
            posY += 1;
            if (posY > 2)
                return -1;
        }
        if (m == Operador.CIMA) {
            posY -= 1;
            if (posY < 0)
                return -1;
        }
        if (m == Operador.ESQUERDA) {
            posX -= 1;
            if (posX < 0)
                return -1;
        }
        if (m == Operador.DIREITA) {
            posX += 1;
            if (posX > 2)
                return -1;
        }

        return posY * 3 + posX;
    }

    //guarda o ultimo movimento
    private void setJogada(int move) {
        this.jogada = Operador.translate(move);
    }

    //guarda o pai
    private void setPai(TabNo pai) {
        this.pai = pai;
    }

    //guarda a profundidade
    private void setProfundidade(int profundidade) {
        this.profundidade = profundidade;
    }

    //gera config apos movimento
    private int[] fazMovimento(int newEspacoVazio) {
        int newEstado[] = this.config.clone();
        newEstado[this.espacoVazio] = newEstado[newEspacoVazio];
        newEstado[newEspacoVazio] = 0;
        return newEstado;
    }
}
