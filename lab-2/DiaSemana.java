import java.util.Scanner;
import java.util.ArrayList;

class DiaSemana {
    public static void main(String args[]){
        Scanner scan = new Scanner(System.in);
        ArrayList<ArrayList<Integer>> mat = new ArrayList<>();
        int line =0;
        int value = scan.nextInt();
        int maior = 0;
        int soma = 0;
        int dayMostWork =0;
        int[] frequency = new int[24];


        while(value != -1){
            mat.add(new ArrayList<>());
            mat.get(line).add(value);
            for(int i =1; i<7; i++){
                value = scan.nextInt();
                mat.get(line).add(value);
            }
            line++;
            value = scan.nextInt();
        }
        scan.close();

        for(int i=0;i < mat.get(0).size();i++){
            soma = 0;
            for(int j=0; j< line;j++){
                soma += mat.get(j).get(i);
            }
            if(soma > maior){
                maior = soma;
                dayMostWork = i;
            }
            
        }
        System.out.println(dayMostWork+1);
        
        for(int i=0;i < mat.get(0).size();i++){
            soma = 0;
            for(int j=0; j< line;j++){
                soma += mat.get(j).get(i);
            }
            if(soma == maior && i != dayMostWork){
                System.out.println(i+1);;
            }
            
        }
    }
}
