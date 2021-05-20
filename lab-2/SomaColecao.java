import java.util.Scanner;

class SomaColecao {
    public static void main(String args[]){
        int soma = 0;
        int value = 0;
        Scanner scan = new Scanner(System.in);

        while(value != -1 ){
            value = scan.nextInt();
            if(value != -1) soma += value;
        }
        scan.close();
        
        System.out.printf("%d",soma);
    }
}