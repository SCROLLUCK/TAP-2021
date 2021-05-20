import java.util.Scanner;
public class ValorExpoente {
    public static void main(String args[]){
        Scanner scan = new Scanner(System.in);
        int value = scan.nextInt();
        scan.close();
        int result = 0;
        int i=1;
        while(result <= value){
            result += Math.pow(2,i);
            i++;
        }
        System.out.println(i-1);

    }
}
