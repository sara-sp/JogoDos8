import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Pesquisa extends TabNo{
    Map<Integer, Object> hashMap = new HashMap<>();

    Pesquisa(int[] conf, int vazio) {
        super(conf, vazio);
    }

    TabNo geral(TabNo tab, int configFin[], int remove){

        boolean flag=true;
        int totalNos = 1;
        BinaryHeap heap = new BinaryHeap();
        LinkedList<TabNo> lista = new LinkedList<>();

        if(remove==1 || remove==2) {
            tab.setNumNo(totalNos);
            lista.addFirst(tab);
            hashMap.put(tab.getId(), tab);
        }

        if(remove==4 || remove==5)
            tab.setNumNo(totalNos);

        int verifica = tab.contaPecas(configFin);                   //verificacao

        if (verifica==0)                                            //so um no
            return tab;

        if(remove==4||remove==5){
            tab.calcDist(configFin);
            heap.insere(tab);
            if(remove==4)
                hashMap.put(tab.getId(), tab.getDistancia());
        }

        if(remove==3){                                              //profundidadeIte
            for (int i = 1; flag; i++) {
                tab.setNumNo(totalNos);
                tab.reiniciaMovimento();
                lista.addFirst(tab);
                while (!lista.isEmpty()) {
                    TabNo node = lista.removeFirst();
                    if (node.getProfundidade() < i) {
                        while (node.podeMover()) {
                            TabNo filho = node.getProxFilho();
                            if (filho != null) {
                                totalNos ++;
                                filho.setNumNo(totalNos);
                                lista.addFirst(filho);
                                verifica = filho.contaPecas(configFin);
                                if (verifica==0)
                                    return filho;
                            }
                        }
                    }
                }
            }
        }

        else if(remove==4||remove==5){
            while (!heap.isEmpty()) {
                TabNo node = heap.retornaMin();
                if(remove==5)
                    hashMap.put(node.getId(), node);
                while (node.podeMover()) {
                    TabNo filho = node.getProxFilho();
                    if (filho != null && !hashMap.containsKey(filho.getId())) {
                        totalNos ++;
                        filho.setNumNo(totalNos);
                        filho.calcDist(configFin);
                        if(remove==4)
                            hashMap.put(filho.getId(), filho);
                        if(remove==5)
                            filho.calcHeuristica();
                        heap.insere(filho);
                        verifica = filho.contaPecas(configFin);
                        if (verifica==0) {
                            return filho;
                        }
                    }
                }
            }
        }

        else {
            while (!lista.isEmpty()) {
                TabNo no;
                if (remove == 1)
                    no = lista.removeLast();
                else
                    no = lista.removeFirst();
                while (no.podeMover()) {
                    TabNo filho = no.getProxFilho();

                    if (filho != null && !hashMap.containsKey(filho.getId())) {
                        totalNos++;

                        filho.setNumNo(totalNos);
                        lista.addFirst(filho);

                        hashMap.put(filho.getId(), filho);

                        verifica = filho.contaPecas(configFin);

                        if (verifica == 0)
                            return filho;
                    }
                }
            }
        }

        return null;
    }

    TabNo largura(TabNo tab, int configFin[]){
        return geral(tab, configFin, 1);
    }

    TabNo profundidade(TabNo tab, int configFin[]){
        return geral(tab, configFin, 2);
    }

    TabNo profundidadeIte(TabNo tab, int configFin[]){
        return geral(tab, configFin, 3);
    }

    TabNo gulosa(TabNo tab, int configFin[]){
        return geral(tab, configFin, 4);
    }

    TabNo aEstrela(TabNo tab, int configFin[]){
        return geral(tab, configFin, 5);
    }
}
