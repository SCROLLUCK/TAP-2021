package model;

public class Opcao {

    private int id;
    private Float preco;
    private String nome;

    public Opcao(int id, String nome, Float preco){
        this.id = id;
        this.nome = nome;
        this.preco = preco;
    }

    public Float getPreco() { return preco; }
    public int getId() { return id; }
    public String getNome() { return nome; }

    public void setPreco(Float preco) { this.preco = preco; }
    public void setNome(String nome) { this.nome = nome; }

    public String toString() {
        String result = "Opcao \n Nome: "+nome+"\n Preço: "+preco+"\n Número: "+id+"\n";
        System.out.println(result);
        return result;
    }

    public String showSelect() { return nome+", Preço: "+preco; }


}
