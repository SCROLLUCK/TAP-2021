import java.util.Scanner;

class Fahrenheit {
    public static void main(String args[]){
        Scanner scan = new Scanner(System.in);
        float temp = scan.nextInt();
        temp = (( 9 * temp ) / 5 ) + 32;
        System.out.printf("%.1f",temp);
    }
}