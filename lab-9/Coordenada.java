package br.edu.ufam.icomp.lab_excecoes;

public class Coordenada {
    private int posX;
    private int posY;
    private int digito;

    public Coordenada(int posX, int posY, int digito) throws CoordenadaNegativaException, CoordenadaForaDosLimitesException, DigitoInvalidoException {
        this.posX = posX;
        this.posY = posY;
        if(posX < 0 || posY < 0) throw new CoordenadaNegativaException();
        if(posX < 0 || posX > 30000) throw new CoordenadaForaDosLimitesException();
        if(posY < 0 || posY > 30000) throw new CoordenadaForaDosLimitesException();
        if(digito < 0 || digito > 9) throw new DigitoInvalidoException();
        if(digito != (posX+posY)%10) throw new DigitoInvalidoException();

        this.digito = digito;
    }

    public int getPosX(){ return posX; }
    public int getPosY(){ return posY; }
    public double distancia(Coordenada coordenada){
        return Math.sqrt(Math.pow((coordenada.getPosX()-posX),2) + Math.pow((coordenada.getPosY()-posY),2) );
    }
    public String toString(){ return getPosX()+", "+getPosY(); }   

}
