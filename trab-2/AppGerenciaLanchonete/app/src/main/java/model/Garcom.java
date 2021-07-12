package model;
import java.text.*;
import java.util.Date;

public class Garcom {

    private int id;
    static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    private String nome;
    private String cpf;
    private String genero;
    private String nascimento;
    private String relatorio;

    public Garcom(String nome, String cpf, String genero, Date nascimento) throws ParseException{
        this(-1,nome,cpf,genero,nascimento);
    }

    public Garcom(int id, String nome, String cpf, String genero, Date nascimento){
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.genero = genero;
        this.nascimento = formatter.format(nascimento);
    }

    public int getId(){ return id; }
    public String getNome(){ return nome; }
    public String getCpf(){ return cpf; }
    public String getGenero(){ return genero; }
    public String getNascimento(){ return nascimento; }
    public String getRelatorio(){ return relatorio; }

    public void setNome(String nome){ this.nome = nome; }
    public void setCpf(String cpf){ this.cpf = cpf; }
    public void setGenero(String genero){ this.genero = genero; }
    public void setNascimento(Date nascimento){  this.nascimento = formatter.format(nascimento); }
    public void setRelatorio(String relatorio){ this.relatorio = relatorio; }

    public String toString(){
        String result = "- Dados Pessoais -\n";
        result += " Id: "+id+"\n";
        result += " Nome: "+nome+"\n";
        result += " CPF: "+cpf+"\n";
        result += " Genero: "+genero+"\n";
        result += " Data de nascimento: "+nascimento+"\n";
        result += " Relatorio: "+relatorio+"\n";

        System.out.println(result);
        return result;
    }
}
