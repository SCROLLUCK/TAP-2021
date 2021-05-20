package br.edu.icomp.ufam.lab_heranca;

abstract public class FormaGeometrica {
    public int posX;
    public int posY;

    public FormaGeometrica(int posX, int posY){
        this.posX = posX;
        this.posY = posY;
    }

    public abstract double getArea();
    public abstract double getPerimetro();
    public String getPosString(){
        return "posição ("+posX+","+posY+")";
    }
}
