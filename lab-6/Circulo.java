package br.edu.icomp.ufam.lab_heranca;

public class Circulo extends FormaGeometrica {
    public double raio;

    public Circulo(int posX, int poxY, double raio) {
        super(posX, poxY);
        this.raio = raio;
    }

    public double getArea(){
        return Math.PI*(raio*raio);
    }
    public double getPerimetro(){
        return 2*Math.PI*raio;
    }
    public String toString(){ 
        return "Círculo na "+getPosString()+" com raio de "+raio+"cm (área="+getArea()+"cm2, perímetro="+getPerimetro()+"cm)";
    }
}
