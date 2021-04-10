import java.util.Scanner;
import java.util.ArrayList;

class MediaColecao {
    public static void main(String args[]){
        float media = 0;
        float value = 0;
        Scanner scan = new Scanner(System.in);
        ArrayList<Float> values = new ArrayList<Float>();
        while(value != -1 ){
            value = scan.nextFloat();
            if(value != -1){
                values.add(value);
                media += value;
            } 
        }
        scan.close();
        
        System.out.printf("%.2f", media/values.size());
    }
}