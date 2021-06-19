package dao;
import java.sql.*;
import java.util.ArrayList;

import model.Garcom;

public class GarcomDAO extends ConnectionFactory{
  
  public ArrayList<Garcom> listarGarcons(){
	  ArrayList<Garcom> garcons = new ArrayList<Garcom>();
    try{
		Connection conexao = new ConnectionFactory().getConnection();
		PreparedStatement stm = conexao.prepareStatement("SELECT * FROM garcons");
		ResultSet rs = stm.executeQuery();
	  
		while(rs.next()){
			Garcom garcon = new Garcom(rs.getInt("id"),rs.getString("nome"),rs.getString("cpf"),rs.getString("genero"),rs.getDate("nascimento"));
			garcon.toString();
			garcons.add(garcon);
		}
	      
	    rs.close();
	    stm.close();
	    conexao.close();
	    return garcons;
    }catch(SQLException e){ 
    	throw new RuntimeException(e);
    }
  }

  public boolean adicionarGarcom(Garcom novoGarcom){
	try{
	
		Connection conexao = new ConnectionFactory().getConnection();
		String query = "INSERT INTO garcons (nome,cpf,genero,nascimento) VALUES ('"
			+novoGarcom.getNome()+"','"
			+novoGarcom.getCpf()+"','"
			+novoGarcom.getGenero()+"','"
			+novoGarcom.getNascimento()+"')";
		PreparedStatement stm = conexao.prepareStatement(query);
		int rs = stm.executeUpdate();
		return true;
	}catch(SQLException e){ 
		throw new RuntimeException(e);
	}
  }

  public boolean removerGarcom(Garcom garcom){
    try{
    	Connection conexao = new ConnectionFactory().getConnection();
	    Statement stm = conexao.createStatement();
	    String query = "DELETE FROM garcons WHERE id ="+garcom.getId();
	    System.out.println(query);
	    int rs = stm.executeUpdate(query);
	    return true;
    }catch(SQLException e){
    	throw new RuntimeException(e);
    }
  }

  public boolean atualizarGarcom(Garcom garcom){
    try{
		Connection conexao = new ConnectionFactory().getConnection();
	    Statement stm = conexao.createStatement();
	    String query = "UPDATE garcons SET "+ 
		"nome = '"+garcom.getNome()+"', "+
		"cpf = '"+garcom.getCpf()+"', "+
		"genero = '"+garcom.getGenero()+"', "+
		"nascimento = '"+garcom.getNascimento()+"', "+
		"relatorio = '"+garcom.getRelatorio()+"' "+
		"WHERE id ="+garcom.getId();
	    System.out.println(query);
	    stm.executeUpdate(query);
      	return true;
    }catch(SQLException e){
    	throw new RuntimeException(e);
    }
  }
}
