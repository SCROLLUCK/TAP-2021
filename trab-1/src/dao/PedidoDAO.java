package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Mesa;
import model.Pedido;

public class PedidoDAO {
	
	 public ArrayList<Pedido> listarPedidos(Mesa mesa){
		  ArrayList<Pedido> pedidos = new ArrayList<Pedido>();
	    try{
			Connection conexao = new ConnectionFactory().getConnection();
			PreparedStatement stm = conexao.prepareStatement(""
				+ "SELECT *, pedidos.id as \"PEDIDOID\", cardapio.id as \"CARDAPIOID\""
				+ "FROM mesas, pedidos, cardapio "
				+ "WHERE pedidos.pedido_numero = cardapio.id AND pedidos.mesa_numero = mesas.id AND pedidos.status != 'pago' AND mesas.id ="+mesa.getId());
			ResultSet rs = stm.executeQuery();
		  
			while(rs.next()){
				Pedido pedido = new Pedido(rs.getInt("PEDIDOID"),rs.getString("nome"),rs.getInt("CARDAPIOID"),rs.getFloat("preco"), rs.getInt("quantidade"),rs.getString("status"));
				pedidos.add(pedido);
			}
		      
		    rs.close();
		    stm.close();
		    conexao.close();
		    return pedidos;
	    }catch(SQLException e){ 
	    	throw new RuntimeException(e);
	    }
	  }
	 
	 public ArrayList<Pedido> listarPedidosPendentes(){
		  ArrayList<Pedido> pedidos = new ArrayList<Pedido>();
	    try{
			Connection conexao = new ConnectionFactory().getConnection();
			PreparedStatement stm = conexao.prepareStatement(""
				+ "SELECT *, pedidos.id as \"PEDIDOID\", cardapio.id as \"CARDAPIOID\""
				+ "FROM mesas, pedidos, cardapio "
				+ "WHERE pedidos.pedido_numero = cardapio.id AND pedidos.mesa_numero = mesas.id AND pedidos.status != 'pago' AND pedidos.status != 'entregue'");
			ResultSet rs = stm.executeQuery();
		  
			while(rs.next()){
				Pedido pedido = new Pedido(rs.getInt("PEDIDOID"),rs.getString("nome"),rs.getInt("CARDAPIOID"),rs.getFloat("preco"), rs.getInt("quantidade"),rs.getString("status"));
				pedido.toString();
				pedidos.add(pedido);
			}
		      
		    rs.close();
		    stm.close();
		    conexao.close();
		    return pedidos;
	    }catch(SQLException e){ 
	    	throw new RuntimeException(e);
	    }
	  }

	  public boolean adicionarPedido(Mesa mesa, Pedido pedido){
	    try{
			Connection conexao = new ConnectionFactory().getConnection();
		    Statement stm = conexao.createStatement();
		    String query = "INSERT INTO pedidos (mesa_numero,pedido_numero,quantidade) VALUES("
				+mesa.getId()+", "
				+pedido.getNumero()+", "
				+pedido.getQuantidade()+")";
		    System.out.println(query);
		    stm.executeUpdate(query);
	      	return true;
	    }catch(SQLException e){
	    	throw new RuntimeException(e);
	    }
	  }
	  
	  public boolean removerPedido(Mesa mesa, Pedido pedido){
	    try{
			Connection conexao = new ConnectionFactory().getConnection();
		    Statement stm = conexao.createStatement();
		    String query = "DELETE FROM pedidos WHERE id="+pedido.getId();
		    System.out.println(query);
		    stm.executeUpdate(query);
	      	return true;
	    }catch(SQLException e){
	    	throw new RuntimeException(e);
	    }
	  }
	  
	  public boolean entregarPedido(Pedido pedido){
	    try{
			Connection conexao = new ConnectionFactory().getConnection();
		    Statement stm = conexao.createStatement();
		    String query = "UPDATE pedidos SET status='entregue' WHERE id="+pedido.getId();
		    System.out.println(query);
		    stm.executeUpdate(query);
	      	return true;
	    }catch(SQLException e){
	    	throw new RuntimeException(e);
	    }
	  }
	  
	  public boolean pagarPedido(Pedido pedido){
		    try{
				Connection conexao = new ConnectionFactory().getConnection();
			    Statement stm = conexao.createStatement();
			    String query = "UPDATE pedidos SET status='pago' WHERE id="+pedido.getId();
			    System.out.println(query);
			    stm.executeUpdate(query);
		      	return true;
		    }catch(SQLException e){
		    	throw new RuntimeException(e);
		    }
		  }
}
