import java.util.Scanner;
import java.util.ArrayList;

class Mediana {
    public static void main(String args[]){
        double mediana = 0.0;
        float value = 0;
        Scanner scan = new Scanner(System.in);
        ArrayList<Float> values = new ArrayList<Float>();
        while(value != -1 ){
            value = scan.nextFloat();
            if(value != -1){
                values.add(value);
            } 
        }
        scan.close();
        if(values.size()%2 == 0){
            float value1 = values.get((values.size()/2)-1);
            float value2 = values.get((values.size()/2));
            mediana = (value1+value2)/2;
        }else{
            mediana = values.get(values.size()/2);
        }

        System.out.printf("%.1f",mediana);
    }
}