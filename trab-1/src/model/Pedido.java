package model;

public class Pedido {
	private int id;
	private String nome;
	private int numero;
	private Float preco;
	private int quantidade;
	private String status;
	private int numeroCliente;
	
	public Pedido(int numero,int quantidade) {
		this(-1,"",numero,(float) 0,quantidade,"");
	}
	
	public Pedido(int id,String nome, int numero, Float preco, int quantidade, String status) {
		this.id = id;
		this.nome = nome;
		this.numero = numero;
		this.preco = preco;
		this.quantidade = quantidade;
		this.status = status;
	}
	
	public int getId() { return id; }
	public Float getPreco() { return preco; }
	public int getNumero() { return numero; }
	public String getNome() { return nome; }
	public int getQuantidade() { return quantidade; }
	public String getStatus() { return status; }
	public int getNumeroCliente() { return numeroCliente; }
	
	public void setPreco(Float preco ) { this.preco = preco; }
	public void setNumero(int numero ) { this.numero = numero; }
	public void setNome(String nome ) {this.nome = nome; }
	public void setQuantidade(int quantidade) { this.quantidade = quantidade; }
	public void setStatus(String status) { this.status = status; }
	public void setNumeroCliente(int numeroCliente ) { this.numeroCliente = numeroCliente; }
	
	public String toString() {
		String result = "Pedido \n Nome: "+nome+"\n Pre�o: "+preco+"\n Numero: "+numero+"\n Quantidade: "+quantidade+"\n Status: "+status+"\n Numero Cliente: "+numeroCliente;
		System.out.println(result);
		return result;
	}
}
