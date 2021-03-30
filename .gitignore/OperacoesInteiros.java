import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

class OperacoesInteiros {

    public static void Sequencia() {

        String STR = "";
        boolean end = false;
        float value = 0;
        int impar = 0;
        int par = 0;
        float soma = 0;
        float maior = 0;
        float menor = 0;
        Scanner scan = new Scanner(System.in);
        ArrayList<Float> values = new ArrayList<Float>();

        while(!STR.equals('\n') && !end){

            STR = scan.next();
            value = Float.parseFloat(STR);
            if(value != -1){

                int chato = (value%2 == 0) ? par++ : impar++;
                soma += value;
                values.add(value);

            }else{

                if(values.size() > 0){

                    Collections.sort(values);
                    menor = (float) values.get(0);
                    maior = (float) values.get((values.size()-1));
        
                    System.out.printf("%d\n", values.size());
                    System.out.printf("%d\n", par);
                    System.out.printf("%d\n", impar);
                    System.out.printf("%.0f\n", soma);
                    System.out.printf("%.2f\n", soma/values.size());
                    System.out.printf("%.0f\n",maior);
                    System.out.printf("%.0f\n",menor);
                    
                    impar = 0;
                    par = 0;
                    soma = 0;
                    maior = 0;
                    menor = 0;
                    values = new ArrayList<Float>();
                
                }else {
                    end = true;
                }

            }
        }
    }

    public static void main(String args[]){
        Sequencia();
    }
}