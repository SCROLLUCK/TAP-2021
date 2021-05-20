import java.util.Scanner;
import java.util.ArrayList;

class SomaColecoes {

    public static void Soma() {

        String STR = "";
        boolean end = false;
        int value = 0;
        int soma = 0;
        Scanner scan = new Scanner(System.in);
        ArrayList<Integer> values = new ArrayList<Integer>();

        while(!STR.equals('\n') && !end){

            STR = scan.next();
            value = Integer.parseInt(STR);
            if(value != -1){

                soma += value;
                values.add(value);

            }else{

                if(values.size() > 0){
                    System.out.printf("%d\n", soma);
                    soma = 0;            
                    values = new ArrayList<Integer>();
                }else {
                    end = true;
                }

            }
        }
        scan.close();
    }

    public static void main(String args[]){
        Soma();
    }
}