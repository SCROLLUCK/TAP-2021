import java.util.Scanner;
import java.util.ArrayList;

class TimeFutebol {

    public static void calculaTorneio() {

        String STR = "";
        boolean end = false;
        int value = 0;
        
        boolean x_full = false;
        Scanner scan = new Scanner(System.in);
        int vitorias = 0;
        int empates = 0;
        int derrotas = 0;
        ArrayList<Integer> X = new ArrayList<Integer>();
        ArrayList<Integer> Y = new ArrayList<Integer>();

        while(!end){

            value = scan.nextInt();
            if(value != -1){
                if(x_full == false) X.add(value);
                else Y.add(value);
            }else{
                x_full = true;
                if(X.size() == Y.size()){
                    end = true;
                }
            }
        }
        scan.close();
        for(int i = 0; i < X.size();i++){
            if(X.get(i) > Y.get(i)){
                vitorias++;
            }else if(X.get(i) == Y.get(i)){
                empates++;
            }else{
                derrotas++;
            }
        }        
        System.out.printf("%d %d %d",vitorias, empates, derrotas );
    }

    public static void main(String args[]){
        calculaTorneio();
    }
}