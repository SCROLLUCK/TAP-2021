import java.util.Scanner;

class SomaDigitos {
    public static void main(String args[]){
        Scanner scan = new Scanner(System.in);
        String value = scan.next();
        int soma = 0;
        for (char ch: value.toCharArray()){
            soma += Integer.parseInt(ch+"");
        }
        System.out.print(soma);
    }
}