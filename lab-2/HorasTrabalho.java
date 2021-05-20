import java.util.Scanner;

class HorasTrabalho {

    public static void CalculaHoras() {

        boolean end = false;
        int value = 0;
        int soma = 0;
        Scanner scan = new Scanner(System.in);
        int i = 0;
        while(!end){
            value = scan.nextInt();
            if(value != -1){
                if(i == 6){
                    System.out.println(soma+value);
                    soma = 0;
                    i=0; 
                }else {
                    soma += value;
                    i++;
                }
            }else{
                end = true;
            }
            
            
        }
        scan.close();
    }

    public static void main(String args[]){
        CalculaHoras();
    }
}