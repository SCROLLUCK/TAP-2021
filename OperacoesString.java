import java.util.Scanner;

class OperacoesString {
    public static void main(String args[]){
        Scanner scan = new Scanner(System.in);
        String vogais = "aeiouAEIOU";
        int NumVogais = 0;
        String STR = scan.nextLine();
        scan.close();
        System.out.printf("%s\n", STR.length());
        System.out.printf("%c\n", STR.charAt(0));
        System.out.printf("%c\n", STR.charAt(STR.length()-1));
        System.out.printf("%s\n", STR.toUpperCase());
        System.out.printf("%s\n", STR.toLowerCase());
        System.out.printf("%s\n", STR.replace('a', 'e'));
        for(int i =0; i< STR.length(); i++){ 
            if(i%2 == 0){
                System.out.printf("%c", STR.charAt(i));
            }
        }
        System.out.println();
        for(int i =0; i< STR.length(); i++){
            if(vogais.indexOf(STR.charAt(i)) != -1){
                NumVogais +=1;
            }
        }
        System.out.printf("%d\n", NumVogais);

    }       
}
