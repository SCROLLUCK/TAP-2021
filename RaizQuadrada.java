import java.util.Scanner;

class RaizQuadrada {
    public static void main(String args[]){
        Scanner scan = new Scanner(System.in);
        double raiz = Math.sqrt(scan.nextFloat());
        System.out.printf("%.4f\n", raiz);
    }
}