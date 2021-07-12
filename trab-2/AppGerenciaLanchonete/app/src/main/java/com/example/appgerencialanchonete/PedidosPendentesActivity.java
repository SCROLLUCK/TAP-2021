package com.example.appgerencialanchonete;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import dao.MesaDAO;
import dao.PedidoDAO;
import model.Mesa;
import model.Pedido;

public class PedidosPendentesActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private PedidosPendentesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedidos_pendentes);

        recyclerView = findViewById(R.id.pedidosPendentesList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new PedidosPendentesAdapter(this);
        recyclerView.setAdapter(adapter);
    }
}

class PedidosPendentesAdapter extends RecyclerView.Adapter<PedidosPendentesViewHolder> {

    private Context context;
    private ArrayList<Pedido> pedidosPendentes;
    MesaDAO mesasDAO;
    PedidoDAO pedidosDAO;

    public PedidosPendentesAdapter(Context context){
        this.context = context;
        mesasDAO = new MesaDAO(context);
        pedidosDAO = new PedidoDAO(context);
        update();
    }

    public void update(){ pedidosPendentes = pedidosDAO.listarPedidosPendentes(); }

    public PedidosPendentesViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        ConstraintLayout v = (ConstraintLayout) LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.list_item_pedido_pendente, parent, false);
        PedidosPendentesViewHolder vh = new PedidosPendentesViewHolder(v, context);
        return vh;
    }
    public void onBindViewHolder(PedidosPendentesViewHolder holder, int position){
        holder.id = pedidosPendentes.get(position).getId();
        holder.nome.setText(pedidosPendentes.get(position).getNome());
        holder.quantidade.setText(Integer.toString(pedidosPendentes.get(position).getQuantidade()));
        holder.mesaNumero.setText(Integer.toString(pedidosPendentes.get(position).getMesaId()));
    }
    public int getItemCount(){ return pedidosPendentes.size(); }
}

class PedidosPendentesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public Context context;
    public int id;
    public TextView nome, quantidade, mesaNumero;

    public PedidosPendentesViewHolder(ConstraintLayout v, Context context){
        super(v);
        this.context = context;
        nome = v.findViewById(R.id.itemNomePedido);
        quantidade = v.findViewById(R.id.itemQuantidade);
        mesaNumero = v.findViewById(R.id.itemMesaNumero);
        v.setOnClickListener(this);
    }

    public void onClick(View v){
        System.out.println("pedido id>>>"+id);
    }
}
