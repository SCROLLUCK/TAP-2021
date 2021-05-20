package br.edu.ufam.icomp.lab_excecoes;

public class RoverMain {
    public static void main(String args[]){
        
        Caminho caminho = new Caminho(3);
        try{
            caminho.addCoordenada(new Coordenada(32,30,2));
            caminho.addCoordenada(new Coordenada(-1,40,2));
            caminho.addCoordenada(new Coordenada(38,30,2));
            caminho.addCoordenada(new Coordenada(30,36,3));
            
        }catch(RoverException e){
            e.getMessage();
            caminho.reset();
        }


    }
}
