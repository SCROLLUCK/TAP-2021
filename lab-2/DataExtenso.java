import java.util.Scanner;

class DataExtenso {
    public static void main (String args[]){
        Scanner scan = new Scanner(System.in);
        String data = scan.next();
        scan.close();
        //21102015
        String dia = data.substring(0,2);
        int mes = Integer.parseInt(data.substring(2,4));
        String ano = data.substring(4,8);
        String[] meses = {"janeiro","fevereiro","março","abril","maio","junho","julho","agosto","setembro","outubro","novembro","desenbro"};

        System.out.printf("%s de %s de %s",dia,meses[mes-1],ano);

    }
}