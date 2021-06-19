package dao;
import java.sql.*;
import java.util.ArrayList;

import model.Mesa;
import model.Pedido;

public class MesaDAO extends ConnectionFactory{
  
  public ArrayList<Mesa> listarMesas(){
	  ArrayList<Mesa> mesas = new ArrayList<Mesa>();
    try{
		Connection conexao = new ConnectionFactory().getConnection();
		PreparedStatement stm = conexao.prepareStatement("SELECT * FROM mesas");
		ResultSet rs = stm.executeQuery();
	  
		while(rs.next()){
			ArrayList<Pedido> pedidos = new ArrayList<Pedido>();
			Mesa mesa = new Mesa(rs.getInt("id"),pedidos, rs.getInt("numeroClientes"), rs.getBoolean("ocupada"));
			mesa.toString();
			mesas.add(mesa);
		}
	      
	    rs.close();
	    stm.close();
	    conexao.close();
	    return mesas;
    }catch(SQLException e){ 
    	throw new RuntimeException(e);
    }
  }

  public boolean atualizarMesa(Mesa mesa){
    try{
		Connection conexao = new ConnectionFactory().getConnection();
	    Statement stm = conexao.createStatement();
	    String query = "UPDATE mesas SET "+ 
		"numeroClientes = "+mesa.getNumeroClientes()+", "+
		"ocupada = "+mesa.getOcupada()+" "+
		"WHERE id = "+mesa.getId();
	    System.out.println(query);
	    stm.executeUpdate(query);
      	return true;
    }catch(SQLException e){
    	throw new RuntimeException(e);
    }
  }
  
  public boolean adicionarMesa(Mesa mesa){
    try{
		Connection conexao = new ConnectionFactory().getConnection();
	    Statement stm = conexao.createStatement();
	    String query = "INSERT INTO mesas (numeroClientes, ocupada) VALUES ("+
		mesa.getNumeroClientes()+", "+
		mesa.getOcupada()+")";
	    System.out.println(query);
	    stm.executeUpdate(query);
      	return true;
    }catch(SQLException e){
    	throw new RuntimeException(e);
    }
  }
  
}

