package br.edu.ufam.icomp.lab_excecoes;

public class Caminho {
    private Coordenada caminho [];
    private int tamanho;

    public Caminho(int maxTam){
        caminho = new Coordenada[maxTam];
        tamanho = 0;
    }
    public int tamanho(){ return tamanho; }
    public void addCoordenada(Coordenada coordenada) throws TamanhoMaximoExcedidoException, DistanciaEntrePontosExcedidaException { 
        if(tamanho == caminho.length){
            throw new TamanhoMaximoExcedidoException();
        }else{
            if(tamanho != 0){
                if(caminho[tamanho-1].distancia(coordenada) > 15){ 
                    throw new DistanciaEntrePontosExcedidaException();  
                }else {
                    caminho[tamanho] = coordenada;
                    tamanho+=1;
                }
            }else {
                caminho[tamanho] = coordenada;    
                tamanho+=1;
            }
        }
    }

    public void reset(){
        caminho = new Coordenada[caminho.length];
        tamanho = 0;
    }

    public String toString(){
        String result = "Dados do caminho:\n";
        result += "  - Quantidade de pontos: "+tamanho+"\n";
        result += "  - Pontos:\n";
        for(int i=0; i<tamanho; i++){
            result += "  ->"+caminho[i].toString()+"\n";
        }
        return result;
    }
}
