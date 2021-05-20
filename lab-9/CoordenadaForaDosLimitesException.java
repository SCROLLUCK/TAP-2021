package br.edu.ufam.icomp.lab_excecoes;

public class CoordenadaForaDosLimitesException extends RoverCoordenadaException{
    public CoordenadaForaDosLimitesException(){
        this("Coordenada com valores fora dos limites");
    }
    public CoordenadaForaDosLimitesException(String message){
        super(message);
    }
}
