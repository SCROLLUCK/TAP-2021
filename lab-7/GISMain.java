package br.edu.ufam.icomp.lab_encapsulamento;

public class GISMain {
    public static void main(String args[]){
        
        Localizavel vetorLocalizaveis[] = new Localizavel[3];
        vetorLocalizaveis[0] = new CarroLuxuoso("SNK777");
        vetorLocalizaveis[1] = new Celular(55,92,93522155);
        vetorLocalizaveis[2] = new CarroLuxuoso("MOBP100");
        
        for (int i = 0; i < vetorLocalizaveis.length; i++){
            System.out.println(vetorLocalizaveis[i].getPosicao());
        }
    }
}
