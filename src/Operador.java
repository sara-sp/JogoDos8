public enum Operador {
    CIMA, BAIXO, DIREITA, ESQUERDA;
    static Operador translate(int i) {
        return Operador.values()[i];
    }
    Operador opposite() {
        switch (this) {
            case CIMA:
                return Operador.BAIXO;
            case BAIXO:
                return Operador.CIMA;
            case DIREITA:
                return Operador.ESQUERDA;
            case ESQUERDA:
                return Operador.DIREITA;
            default:
                return null;
        }
    }
}
