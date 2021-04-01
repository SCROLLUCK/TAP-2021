import java.util.Scanner;

class Xadrez {
    public static void main(String args[]){

        Scanner scan = new Scanner(System.in);
        int value = scan.nextInt();

        for(int i = 0; i < value; i++ ){
            if(i%2 != 0) System.out.print(" ");
            for(int j = 0; j < value; j++ ){
                System.out.print("*");
                System.out.print(" ");
            } 
            System.out.println();
        }

    }
}