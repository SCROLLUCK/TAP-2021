import java.util.Scanner;

class FolhaPagamento {
    public static void main(String args[]){

        Scanner scan = new Scanner(System.in);
        double hora = scan.nextFloat();
        int quantidadeHoras = scan.nextInt();
        scan.close();
        double salarioBruto = hora*quantidadeHoras;
        double IR = (11*salarioBruto)/100;
        double INSS = (8*salarioBruto)/100;
        double totaldoRoubo = (19*salarioBruto)/100;

        System.out.printf("Salario bruto: R$%.2f\n",salarioBruto);
        System.out.printf("IR: R$%.2f\n",IR);
        System.out.printf("INSS: R$%.2f\n",INSS);
        System.out.printf("Total de descontos: R$%.2f\n",totaldoRoubo);
        System.out.printf("Salario liquido: R$%.2f\n",salarioBruto-totaldoRoubo);

    }
}