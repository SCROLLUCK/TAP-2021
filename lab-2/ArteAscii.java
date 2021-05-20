import java.util.Scanner;

class ArteAscii {
    public static void main(String args[]){

        Scanner scan = new Scanner(System.in);
        int value = scan.nextInt();
        scan.close();
        for(int i = value; i > 0; i-- ){
            for(int j = 0; j < i; j++ ) System.out.print("*");
            System.out.println();
        }
        for(int i = 1; i <= value; i++){
            for(int j = i; j > 0; j-- ) System.out.print("*");
            System.out.println();
        }

    }
}