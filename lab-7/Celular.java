package br.edu.ufam.icomp.lab_encapsulamento;
import java.util.*;

public class Celular implements Localizavel {
    private int codPais;
    private int codArea;
    private int numero;

    public Celular(int codPais, int codArea, int numero) {
        this.codPais = (codPais >= 1 && codPais <= 1999)? codPais : -1;
        this.codArea = (codArea >= 10 && codArea <= 99)? codArea : -1;
        this.numero = (numero >= 10000000 && numero <= 999999999)? numero : -1;
    }

    public final void setCodPais(int codPais){ 
        this.codPais = (codPais >= 1 && codPais <= 1999)? codPais : -1; 
    }
    public int getCodPais(){ return this.codPais; };

    public final void setCodArea(int codArea){ 
        this.codArea = (codArea >= 10 && codArea <= 99)? codArea : -1;
    }
    public int getCodArea(){ return this.codArea; };

    public final void setNumero(int numero){ 
        this.numero = (numero >= 10000000 && numero <= 999999999)? numero : -1;
    }
    public int getNumero(){ return this.numero; };

    public Posicao getPosicao(){
        
        Random r = new Random();
        
        double latitude = -3.160000 + (-2.960000 - -3.160000) * r.nextDouble();
        double longitude = -60.120000 + (-59.820000 - -60.120000) * r.nextDouble();
        double altitude = 15.0 + (100.0 - 15.0) * r.nextDouble();

        return new Posicao(latitude, longitude, altitude);
    }
    public double getErroLocalizacao(){ return 50.0; }

}
