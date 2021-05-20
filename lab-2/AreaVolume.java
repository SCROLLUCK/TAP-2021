import java.util.Scanner;

class AreaVolume {
    public static void main(String args[]){
        Scanner scan = new Scanner(System.in);
        scan.close();
        double value = scan.nextDouble();

        double Area = Math.PI* Math.pow(value,2);
        double Volume = (4.0/3.0) * Math.PI * Math.pow(value,3);

        System.out.printf("Um circulo com raio de %.2f centimetros tem uma area de %.2f centimetros quadrados.\n",value,Area);
        System.out.printf("Uma esfera com raio de %.2f centimetros tem um volume de %.2f centimetros cubicos.",value,Volume);
    }
}