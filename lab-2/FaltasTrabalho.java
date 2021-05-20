import java.util.Scanner;

class FaltasTrabalho {
    public static void main(String args[]){
        int value = 0;
        Scanner scan = new Scanner(System.in);
        int[] FaltasSemana = {0,0,0,0,0,0,0};
        while(value != -1 ){
            value = scan.nextInt();
            if(value != -1){
                FaltasSemana[value-1]+=1;
                FaltasSemana[0]+=1;
            } 
        }       
        scan.close();
        for(int i=1;i <= 6; i++){
            System.out.printf("%.1f ", (FaltasSemana[i]*100.0)/FaltasSemana[0]);
        }
        
    }
}