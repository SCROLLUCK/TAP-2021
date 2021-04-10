import java.util.Scanner;
import java.util.ArrayList;

class MediaColecoes {

    public static void Media() {

        String STR = "";
        boolean end = false;
        float value = 0;
        float soma = 0;
        Scanner scan = new Scanner(System.in);
        ArrayList<Float> values = new ArrayList<Float>();

        while(!STR.equals('\n') && !end){

            STR = scan.next();
            value = Float.parseFloat(STR);
            if(value != -1){

                soma += value;
                values.add(value);

            }else{

                if(values.size() > 0){
                    System.out.printf("%.2f\n", soma/values.size());
                    soma = 0;            
                    values = new ArrayList<Float>();
                }else {
                    end = true;
                }

            }
        }
        scan.close();
    }

    public static void main(String args[]){
        Media();
    }
}