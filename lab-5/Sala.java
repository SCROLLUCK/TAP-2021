class Sala {
    int bloco;
    int sala;
    int capacidade;
    boolean acessivel;

    Sala(){
        this(0,0,0,false);
    }
    Sala(int bloco, int sala,int capacidade, boolean acessivel){
        this.bloco = bloco;
        this.sala = sala;
        this.capacidade = capacidade;
        this.acessivel = acessivel;
    }
    String getDescricao(){
        return "Bloco "+bloco+", Sala "+sala+ " ("+capacidade+" lugares, "+(acessivel? "acessível)": "não acessível)");
    }
}
