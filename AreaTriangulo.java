import java.util.Scanner;
class AreaTriangulo {

    public static void main(String args[]){
        
        Scanner scan = new Scanner(System.in);

        int valueA = scan.nextInt();
        int valueB = scan.nextInt();
        int valueC = scan.nextInt();

        scan.close();
        if( valueA+valueB > valueC && valueB+valueC > valueA && valueA+valueC> valueB){
            int S = (valueA + valueB + valueC)/2;
            double A = Math.sqrt( S* (S - valueA ) * ( S - valueB) * (S - valueC) );
            System.out.printf("%.2f",A);
        }else {
            System.out.print("Triangulo invalido");
        }

    }

}
