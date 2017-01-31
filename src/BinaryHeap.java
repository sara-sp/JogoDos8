import java.util.ArrayList;
import java.util.List;

public class BinaryHeap {
    List<TabNo> heapMin;

    public BinaryHeap() {
        this.heapMin = new ArrayList<>();
    }

    private int pai(int i) {
        return (i-1) / 2;
    }

    private int esquerda(int i) {
        return 2*i + 1;
    }

    private int direita(int i) {
        return 2*i + 2;
    }

    private void heapify(int index) {
        if (index != 0) {
            int paiIndex = pai(index);
            if (this.heapMin.get(index).getDistancia()<=this.heapMin.get(paiIndex).getDistancia()) {
                TabNo temp = this.heapMin.get(paiIndex);
                this.heapMin.set(paiIndex, this.heapMin.get(index));
                this.heapMin.set(index, temp);
                heapify(paiIndex);
            }
        }
    }

    private void topDownHeapify(int index) {
        int esquerdaIndex = esquerda(index);
        int direitaIndex = direita(index);
        int min = index;
        if (esquerdaIndex<this.heapMin.size() && this.heapMin.get(esquerdaIndex).getDistancia()<this.heapMin.get(min).getDistancia())
            min = esquerdaIndex;
        
        if (direitaIndex<this.heapMin.size() && this.heapMin.get(direitaIndex).getDistancia()<this.heapMin.get(min).getDistancia())
            min = direitaIndex;
        
        if (min!=index) {
            TabNo temp = this.heapMin.get(index);
            this.heapMin.set(index, this.heapMin.get(min));
            this.heapMin.set(min, temp);
            topDownHeapify(min);
        }
    }

    public void insere(TabNo node) {
        this.heapMin.add(node);
        heapify(this.heapMin.size()-1);
    }

    public boolean isEmpty() {
        return this.heapMin.isEmpty();
    }

    public TabNo retornaMin(){
        TabNo node;
        if (this.heapMin.size()==1)
            node = this.heapMin.remove(0);

        else {
            node = this.heapMin.get(0);
            TabNo temp = this.heapMin.remove(this.heapMin.size()-1);
            this.heapMin.set(0, temp);
            topDownHeapify(0);
        }
        return node;
    }
}