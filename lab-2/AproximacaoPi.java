import java.util.Scanner;

class AproximacaoPi {
    public static void main(String args[]){
        double PI = 3.000000;
        Scanner scan = new Scanner(System.in);
        int value = scan.nextInt();
        System.out.printf("%.6f\n",PI);
        scan.close();
        for(int i = 0; i < value-1; i++){
            int aux = (2*i)+2;
            if(i%2 == 0){
                PI += 4.0/(aux * (aux+1) * (aux+2));
            }else{
                PI -= 4.0/(aux * (aux+1) * (aux+2));
            }
            System.out.printf("%.6f\n", PI);
    
        }
        
    }    
}
