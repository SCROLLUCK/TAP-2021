import java.util.Scanner;
import java.util.ArrayList;

class AreaPoligono {

    public static void calculaArea() {

        String STR = "";
        boolean end = false;
        float value = 0;
        double result = 0.0;
        boolean x_full = false;
        Scanner scan = new Scanner(System.in);
        ArrayList<Float> X = new ArrayList<Float>();
        ArrayList<Float> Y = new ArrayList<Float>();

        while(!STR.equals("\n") && !end){

            STR = scan.next();
            value = Float.parseFloat(STR);
            if(value != -1){

                if(x_full == false) X.add(value);
                else Y.add(value);

            }else{
                x_full = true;
                if(X.size() == Y.size()){

                    X.add((float) X.get(0));
                    Y.add((float) Y.get(0));
                    end = true;
                    
                }
            }
        }

        for(int i = 0; i < X.size()-2;i++){
            result += ( (X.get((i+1)) + X.get(i)) * (Y.get((i+1)) - Y.get(i)) );
        }
        
        result = Math.abs(result);
        result = (1.0/2.0) * (result);

        System.out.printf("%.4f\n", result);
        scan.close();
    }

    public static void main(String args[]){
        calculaArea();
    }
}