package dao;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import db.Database;
import model.Mesa;
import model.Pedido;

public class MesaDAO{

    private Context context;
    private SQLiteDatabase database;
    private static ArrayList<Mesa> mesas = new ArrayList<Mesa>();
    public MesaDAO(Context context){
        this.context = context;
        this.database = (new Database(context)).getWritableDatabase();
    }

    public ArrayList<Mesa> listarMesas(){
        mesas = new ArrayList<Mesa>();

        String query = "SELECT * FROM mesas";
        Cursor cursor = database.rawQuery(query, null);
        while(cursor.moveToNext()){
            ArrayList<Pedido> pedidos = new ArrayList<Pedido>();
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            int numeroClientes = cursor.getInt(cursor.getColumnIndex("numeroClientes"));
            boolean ocupada = cursor.getInt(cursor.getColumnIndex("ocupada")) > 0;
            Mesa mesa = new Mesa(id,pedidos, numeroClientes,ocupada);
            mesa.toString();
            mesas.add(mesa);
        }

        return mesas;
    }
    
    public Mesa obterMesa(int mesa_id){
        String query = "SELECT * FROM mesas WHERE mesas.id ="+mesa_id;
        Cursor cursor = database.rawQuery(query, null);
        Mesa mesa = null;
        while(cursor.moveToNext()){
            ArrayList<Pedido> pedidos = new ArrayList<Pedido>();
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            int numeroClientes = cursor.getInt(cursor.getColumnIndex("numeroClientes"));
            boolean ocupada = cursor.getInt(cursor.getColumnIndex("ocupada")) > 0;
            mesa = new Mesa(id,pedidos, numeroClientes,ocupada);
            mesa.toString();
            
        }
        return mesa;
    }

    public boolean atualizarMesa(Mesa mesa){
        String query = "UPDATE mesas SET "+
                "numeroClientes = "+mesa.getNumeroClientes()+", "+
                "ocupada = "+mesa.getOcupada()+" "+
                "WHERE id = "+mesa.getId();
        database.execSQL(query);
        return true;
    }

    public boolean adicionarMesa(){
        String query = "INSERT INTO mesas VALUES (null,0,false)";
        database.execSQL(query);
        return true;
    }

}

