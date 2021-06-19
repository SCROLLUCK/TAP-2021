package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Opcao;

public class CardapioDAO {
	public ArrayList<Opcao> listarCardapio(){
		ArrayList<Opcao> cardapio = new ArrayList<Opcao>();
	    try{
			Connection conexao = new ConnectionFactory().getConnection();
			PreparedStatement stm = conexao.prepareStatement("SELECT * FROM cardapio");
			ResultSet rs = stm.executeQuery();
		  
			while(rs.next()){
				Opcao opcao = new Opcao(rs.getInt("id"),rs.getString("nome"),rs.getFloat("preco"));
				cardapio.add(opcao);
			}
		      
		    rs.close();
		    stm.close();
		    conexao.close();
		    return cardapio;
	    }catch(SQLException e){ 
	    	throw new RuntimeException(e);
	    }
	}
	
	public boolean adicionarOpcao(Opcao opcao){
	    try{
			Connection conexao = new ConnectionFactory().getConnection();
		    Statement stm = conexao.createStatement();
		    String query = "INSERT INTO cardapio (nome, preco) VALUES ('"
				+opcao.getNome()+"', "
				+opcao.getPreco()+")";
		    System.out.println(query);
		    stm.executeUpdate(query);
	      	return true;
	    }catch(SQLException e){
	    	throw new RuntimeException(e);
	    }
	}
	  
	public boolean removerOpcao(Opcao opcao){
	    try{
			Connection conexao = new ConnectionFactory().getConnection();
		    Statement stm = conexao.createStatement();
		    String query = "DELETE FROM cardapio WHERE id="+opcao.getId();
		    System.out.println(query);
		    stm.executeUpdate(query);
	      	return true;
	    }catch(SQLException e){
	    	throw new RuntimeException(e);
	    }
	}
	  
	public boolean atualizarOpcao(Opcao opcao){
		try{
			Connection conexao = new ConnectionFactory().getConnection();
		    Statement stm = conexao.createStatement();
		    String query = "UPDATE cardapio SET nome = "+"'"+opcao.getNome()+ "', preco = "+opcao.getPreco()+ " WHERE id="+opcao.getId();
		    System.out.println(query);
		    stm.executeUpdate(query);
	      	return true;
	    }catch(SQLException e){
	    	throw new RuntimeException(e);
	    }
	}
	
}
