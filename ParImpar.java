import java.util.Scanner;

class ParImpar {
    public static void main(String args[]){
        Scanner scan = new Scanner(System.in);
        int value = 0;
        while (value != -1){
            value = scan.nextInt();
            if(value != -1){
                if(value%2 == 0) System.out.println("PAR");
                else System.out.println("IMPAR");
            }
        }
        scan.close();
        
    }
}