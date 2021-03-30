import java.util.Scanner;

class Media {
    public static void main(String args[]){
        
        Scanner scan = new Scanner(System.in);
        float media = 0;
        float values[] = new float[3];
        for(int i = 0; i < values.length ;i++){
            values[i] = scan.nextFloat();
            media += values[i];
        }
        scan.close();
        System.out.printf("%.2f", media/values.length);
    }
}