package dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import db.Database;
import model.Opcao;

public class CardapioDAO {

    private Context context;
    private SQLiteDatabase database;
    private static ArrayList<Opcao> cardapio = new ArrayList<Opcao>();

    public CardapioDAO(Context context){
        this.context = context;
        this.database = (new Database(context)).getWritableDatabase();
    }

    public ArrayList<Opcao> listarCardapio(){
        cardapio = new ArrayList<Opcao>();
        String query = "SELECT * FROM cardapio";
        Cursor cursor = database.rawQuery(query,null);
        while(cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String nome = cursor.getString(cursor.getColumnIndex("nome"));
            float preco = cursor.getFloat(cursor.getColumnIndex("preco"));
            Opcao opcao = new Opcao(id,nome,preco);
            cardapio.add(opcao);
        }
        this.cardapio = cardapio;
        return cardapio;
    }

    public boolean adicionarOpcao(Opcao opcao){
        String query = "INSERT INTO cardapio VALUES (null,'"+opcao.getNome()+"', "+opcao.getPreco()+")";
        System.out.println(query);
        database.execSQL(query);
        return true;
    }

    public boolean removerOpcao(Opcao opcao){
        String query = "DELETE FROM cardapio WHERE id="+opcao.getId();
        database.execSQL(query);
        return true;
    }

    public boolean atualizarOpcao(Opcao opcao){
        String query = "UPDATE cardapio SET nome = "+"'"+opcao.getNome()+ "', preco = "+opcao.getPreco()+ " WHERE id="+opcao.getId();
        database.execSQL(query);
        return true;
    }

}
