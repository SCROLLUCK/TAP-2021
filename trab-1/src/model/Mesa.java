package model;

import java.util.*;

public class Mesa {
	private int id;
	private boolean ocupada;
	private ArrayList<Pedido> pedidos;
	private int numeroClientes;
	
	public Mesa(){
		this(0,null,0,false);
	}
	
	public Mesa(int id, ArrayList<Pedido> pedidos, int numeroClientes, boolean ocupada){
		this.id = id;
	    this.ocupada = ocupada;
	    this.pedidos = pedidos;
	    this.numeroClientes = numeroClientes;
	}
	
	public int getId(){ return id; }
	public ArrayList<Pedido> getPedidos() { return pedidos; }
	public int getNumeroClientes(){ return numeroClientes; }
	public boolean getOcupada() { return ocupada; }

	public void setPedidos(ArrayList<Pedido> pedidos){ this.pedidos = pedidos; }
	public void setNumeroClientes(int numeroClientes){ this.numeroClientes = numeroClientes; }
	public void setOcupada(boolean ocupada) { this.ocupada = ocupada; }
	
	public String toString() {
		String result = "Dados da Mesa: "+this.getId()+"\n";
		result += "status: "+this.getOcupada()+"\n";
		result += "numero de clientes: "+this.getNumeroClientes()+"\n";
		System.out.println(result);
		return result;
	}  
}
