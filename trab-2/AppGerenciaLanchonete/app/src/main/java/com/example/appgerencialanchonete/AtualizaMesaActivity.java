package com.example.appgerencialanchonete;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;

import dao.MesaDAO;
import dao.PedidoDAO;
import model.Mesa;
import model.Pedido;

public class AtualizaMesaActivity extends AppCompatActivity {

    private MesaDAO mesaDAO;
    private Mesa mesa;
    private RecyclerView recyclerView;
    private PedidosAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mesaDAO = new MesaDAO(this);
        Intent intent = getIntent();
        Integer mesa_id = intent.getIntExtra("mesa_id", 0);
        setContentView(R.layout.activity_atualiza_mesa);
        mesa = mesaDAO.obterMesa(mesa_id);

        recyclerView = findViewById(R.id.pedidosList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new PedidosAdapter(this, mesa);
        recyclerView.setAdapter(adapter);

        TextView numero_mesa = findViewById(R.id.mesa_id);
        numero_mesa.setText(Integer.toString(mesa.getId()));

        Switch ocupada = (Switch) findViewById(R.id.ocupada);
        ocupada.setChecked(mesa.getOcupada());

        EditText numeroClientes = findViewById(R.id.numeroClientes);
        numeroClientes.setText(Integer.toString(mesa.getNumeroClientes()));

        numeroClientes.addTextChangedListener(new TextWatcher() {
            private String num;
            private boolean textChanged = false;

            @Override
            public void onTextChanged(CharSequence currentDigits, int start, int before, int count) {
                System.out.println(currentDigits.toString());
                num = currentDigits.toString();
                if(!num.equals("")) mesa.setNumeroClientes(Integer.parseInt(num));
                if(!num.equals("0")){
                    mesa.setOcupada(true);
                    mesaDAO.atualizarMesa(mesa);
                }
                mesaDAO.atualizarMesa(mesa);
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void afterTextChanged(Editable s) { }
        });

        ocupada.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mesa.setOcupada(isChecked);
                mesaDAO.atualizarMesa(mesa);
            }
        });

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        adapter.update();
        adapter.notifyDataSetChanged();
    }

    public void onResume() {
        super.onResume();
        recyclerView = findViewById(R.id.pedidosList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mesa = mesaDAO.obterMesa(mesa.getId());
        adapter = new PedidosAdapter(this,mesa);
        recyclerView.setAdapter(adapter);
    }

    public void adicionarPedido(View view){
        Intent intent = new Intent(this,AdicionaPedidoActivity.class);
        intent.putExtra("mesa_id",mesa.getId());
        startActivity(intent);
    }
}

class PedidosAdapter extends RecyclerView.Adapter<PedidosViewHolder> {

    private Context context;
    private ArrayList<Pedido> pedidos;
    MesaDAO mesasDAO;
    PedidoDAO pedidosDAO;
    Mesa mesa;


    public PedidosAdapter(Context context, Mesa mesaa){
        this.context = context;
        mesasDAO = new MesaDAO(context);
        pedidosDAO = new PedidoDAO(context);
        this.mesa = mesasDAO.obterMesa(mesaa.getId());
        update();
    }

    public void update(){ pedidos = pedidosDAO.listarPedidos(mesa); }

    public PedidosViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        ConstraintLayout v = (ConstraintLayout) LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.list_item_pedido, parent, false);
        PedidosViewHolder vh = new PedidosViewHolder(v, context);
        return vh;
    }
    public void onBindViewHolder(PedidosViewHolder holder, int position){
        holder.id = pedidos.get(position).getId();
        holder.nome.setText(pedidos.get(position).getNome());
        holder.quantidade.setText(Integer.toString(pedidos.get(position).getQuantidade()));
        holder.preco.setText(Float.toString(pedidos.get(position).getPreco()));
    }
    public int getItemCount(){ return pedidos.size(); }
}

class PedidosViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public Context context;
    public int id;
    public TextView nome, quantidade, preco;

    public PedidosViewHolder(ConstraintLayout v, Context context){
        super(v);
        this.context = context;
        nome = v.findViewById(R.id.itemNomePedido);
        quantidade = v.findViewById(R.id.itemQuantidade);
        preco = v.findViewById(R.id.itemPreco);
        v.setOnClickListener(this);
    }

    public void onClick(View v){
        System.out.println("pedido id>>>"+id);
    }
}