import java.util.Scanner;

public class CifraCesar {

    public static boolean isAlph(Character c){
        if(c >= 'a' && c <= 'z'){
            return true;
        }
        return false;
    }
    public static void main(String args[]){
        Scanner scan = new Scanner(System.in);
        int desloc = scan.nextInt();
        String alfo = "abcdefghijklmnopqrstuvwxyz";
        String frase = scan.nextLine();

        for(int i=1; i < frase.length();i++){
            if(isAlph(frase.charAt(i))){
                System.out.print(Character.toUpperCase(alfo.charAt((alfo.indexOf(frase.charAt(i))+desloc)%26)));
            }else{
                System.out.print(frase.charAt(i));
            }
        }
        scan.close();
        System.out.println();
    }

}
