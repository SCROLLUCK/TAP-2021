import java.util.Scanner;

class Desconto {
    public static void main(String args[]){
        Scanner scan = new Scanner(System.in);
        float value = scan.nextFloat();
        scan.close();
        float desconto = (value*5)/100;
        if (value > 200.00) System.out.printf("%.2f",value-desconto);
        else System.out.printf("%.2f",value);
    }
}