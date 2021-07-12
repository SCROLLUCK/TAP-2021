package dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import db.Database;
import model.Mesa;
import model.Pedido;

public class PedidoDAO {

    private Context context;
    private SQLiteDatabase database;
    private static ArrayList<Pedido> pedidos = new ArrayList<Pedido>();
    private static ArrayList<Pedido> pedidosPendentes = new ArrayList<Pedido>();

    public PedidoDAO(Context context){
        this.context = context;
        this.database = (new Database(context)).getWritableDatabase();
    }

    public ArrayList<Pedido> listarPedidos(Mesa mesa){
        System.out.println("MESAAAAAAAAAA>>>>"+mesa.getId());
        pedidos = new ArrayList<Pedido>();
        String query = "SELECT *, pedidos.id as \"PEDIDOID\", cardapio.id as \"CARDAPIOID\", mesas.id as \"MESAID\" "
                + "FROM mesas, pedidos, cardapio "
                + "WHERE pedidos.pedido_numero = cardapio.id AND pedidos.mesa_numero = mesas.id AND pedidos.status != 'pago' AND mesas.id ="+mesa.getId();

        Cursor cursor = database.rawQuery(query,null);
        while(cursor.moveToNext()){
            int mesaid = cursor.getInt(cursor.getColumnIndex("MESAID"));
            int pedidoid = cursor.getInt(cursor.getColumnIndex("PEDIDOID"));
            String nome = cursor.getString(cursor.getColumnIndex("nome"));
            int cardapioid = cursor.getInt(cursor.getColumnIndex("CARDAPIOID"));
            float preco = cursor.getFloat(cursor.getColumnIndex("preco"));
            int quantidate = cursor.getInt(cursor.getColumnIndex("quantidade"));
            String status = cursor.getString(cursor.getColumnIndex("status"));
            Pedido pedido = new Pedido(mesaid,pedidoid,nome,cardapioid,preco,quantidate,status);
            pedido.toString();
            pedidos.add(pedido);
        }

        return pedidos;
    }

    public ArrayList<Pedido> listarPedidosPendentes(){
        pedidosPendentes = new ArrayList<Pedido>();
        String query = "SELECT *, pedidos.id as \"PEDIDOID\", cardapio.id as \"CARDAPIOID\", mesas.id as \"MESAID\" "
                + "FROM mesas, pedidos, cardapio "
                + "WHERE pedidos.pedido_numero = cardapio.id AND pedidos.mesa_numero = mesas.id AND pedidos.status != 'pago' AND pedidos.status != 'entregue'";
        Cursor cursor = database.rawQuery(query,null);
        while(cursor.moveToNext()){
            int mesaid = cursor.getInt(cursor.getColumnIndex("MESAID"));
            int pedidoid = cursor.getInt(cursor.getColumnIndex("PEDIDOID"));
            String nome = cursor.getString(cursor.getColumnIndex("nome"));
            int cardapioid = cursor.getInt(cursor.getColumnIndex("CARDAPIOID"));
            float preco = cursor.getFloat(cursor.getColumnIndex("preco"));
            int quantidate = cursor.getInt(cursor.getColumnIndex("quantidade"));
            String status = cursor.getString(cursor.getColumnIndex("status"));
            Pedido pedido = new Pedido(mesaid,pedidoid,nome,cardapioid,preco,quantidate,status);
            pedido.toString();
            pedidosPendentes.add(pedido);
        }
        return pedidosPendentes;
    }

    public boolean adicionarPedido(Mesa mesa, Pedido pedido){
        String query = "INSERT INTO pedidos VALUES(null,"
                +pedido.getNumero()+", "
                +mesa.getId()+", "
                +pedido.getQuantidade()+",'pendente')";
        database.execSQL(query);
        return true;
    }

    public boolean removerPedido(Mesa mesa, Pedido pedido){
        String query = "DELETE FROM pedidos WHERE id="+pedido.getId();
        database.execSQL(query);
        return true;
    }

    public boolean entregarPedido(Pedido pedido){
        String query = "UPDATE pedidos SET status='entregue' WHERE id="+pedido.getId();
        database.execSQL(query);
        return true;
    }

    public boolean pagarPedido(Pedido pedido){
        String query = "UPDATE pedidos SET status='pago' WHERE id="+pedido.getId();
        database.execSQL(query);
        return true;
    }
}
