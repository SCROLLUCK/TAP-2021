import java.util.Scanner;
import java.util.ArrayList;

class PorcentagemAcerto {

    public static void calculaPorcentagem() {

        String STR = "";
        boolean end = false;
        int value = 0;
        double result = 0.0;
        boolean x_full = false;
        Scanner scan = new Scanner(System.in);
        ArrayList<Integer> Respostas = new ArrayList<Integer>();
        ArrayList<Integer> Gabarito = new ArrayList<Integer>();

        while(!STR.equals('\n') && !end){

            STR = scan.next();
            value = Integer.parseInt(STR);
            if(value != -1){

                if(x_full == false) Respostas.add(value);
                else Gabarito.add(value);

            }else{
                x_full = true;
                if(Respostas.size() == Gabarito.size()){
                    end = true;
                }
            }
        }
        scan.close();
        for(int i = 0; i < Respostas.size();i++){
            if (Respostas.get(i) == Gabarito.get(i)) result +=1;
        }

        System.out.printf("%.2f",( result/Respostas.size())*100 );
    }

    public static void main(String args[]){
        calculaPorcentagem();
    }
}