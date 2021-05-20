package br.edu.ufam.icomp.lab_excecoes;

public class DigitoInvalidoException extends RoverCoordenadaException {
    public DigitoInvalidoException(){
        this("Digito da coordenada inválido");
    }
    public DigitoInvalidoException(String message){
        super(message);
    }
}
