package dao;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;

import db.Database;
import model.Garcom;

public class GarcomDAO{

    private Context context;
    private SQLiteDatabase database;
    private static ArrayList<Garcom> garcons = new ArrayList<Garcom>();
    public GarcomDAO(Context context){
        this.context = context;
        this.database = (new Database(context)).getWritableDatabase();
    }

    public ArrayList<Garcom> listarGarcons(){
        ArrayList<Garcom> garcons = new ArrayList<Garcom>();
        String query = "SELECT * FROM garcons";

        Cursor cursor = database.rawQuery(query,null);
        while(cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String nome = cursor.getString(cursor.getColumnIndex("nome"));
            String cpf = cursor.getString(cursor.getColumnIndex("CPF"));
            String genero = cursor.getString(cursor.getColumnIndex("genero"));
            Garcom garcom = new Garcom(id,nome,cpf,genero,new Date());
            garcom.toString();
            garcons.add(garcom);
        }
        this.garcons = garcons;
        return garcons;
    }

    public boolean adicionarGarcom(Garcom novoGarcom){
        String query = "INSERT INTO garcons VALUES (null,'"
                +novoGarcom.getNome()+"','"
                +novoGarcom.getCpf()+"','"
                +novoGarcom.getGenero()+"','"
                +novoGarcom.getNascimento()+"')";
        database.execSQL(query);
        Toast.makeText(context, "Garçom cadastrado com sucesso!",Toast.LENGTH_SHORT).show();
        return true;
    }

    public boolean removerGarcom(Garcom garcom){
        String query = "DELETE FROM garcons WHERE id ="+garcom.getId();
        database.execSQL(query);
        Toast.makeText(context, "Garçom removido com sucesso!",Toast.LENGTH_SHORT).show();
        return true;
    }

    public boolean atualizarGarcom(Garcom garcom){


        String query = "UPDATE garcons SET "+
                "nome = '"+garcom.getNome()+"', "+
                "cpf = '"+garcom.getCpf()+"', "+
                "genero = '"+garcom.getGenero()+"', "+
                "nascimento = '"+garcom.getNascimento()+"', "+
                "relatorio = '"+garcom.getRelatorio()+"' "+
                "WHERE id ="+garcom.getId();
        database.execSQL(query);
        Toast.makeText(context, "Garçom atualizado com sucesso!",Toast.LENGTH_SHORT).show();
        return true;
    }
}
