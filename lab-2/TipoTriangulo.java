import java.util.Scanner;

class TipoTriangulo {
    public static void main(String args[]){
        Scanner scan = new Scanner(System.in);
        int valueA = scan.nextInt();
        int valueB = scan.nextInt();
        int valueC = scan.nextInt();
        scan.close();
        String result = "isosceles";
        if( valueA+valueB > valueC && valueB+valueC > valueA && valueA+valueC> valueB && valueA > 0 && valueB > 0 && valueC > 0){
            if(valueA == valueB && valueA == valueC){
                result = "equilatero";
            }else{
                if(valueA != valueB && valueA != valueC){
                    result = "escaleno";
                }
            }
        }else{
            result = "invalido";
        }
        System.out.print(result);
    }
}
