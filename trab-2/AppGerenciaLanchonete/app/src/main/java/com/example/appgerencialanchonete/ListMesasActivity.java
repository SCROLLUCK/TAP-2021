package com.example.appgerencialanchonete;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import dao.MesaDAO;
import model.Mesa;

public class ListMesasActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MesasAdapter adapter;
    private MesaDAO mesasDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mesasDAO = new MesaDAO(this);
        setContentView(R.layout.activity_list_mesas);
        recyclerView = findViewById(R.id.mesasList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new MesasAdapter(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        adapter.update();
        adapter.notifyDataSetChanged();
    }

    public void onResume() {
        super.onResume();
        recyclerView = findViewById(R.id.mesasList);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));

        adapter = new MesasAdapter(this);
        recyclerView.setAdapter(adapter);
    }

    public void adicionarMesa(View view){
        mesasDAO.adicionarMesa();
        onRestart();
    }
}

class MesasAdapter extends RecyclerView.Adapter<MesasViewHolder> {

    private Context context;
    private ArrayList<Mesa> mesas;
    MesaDAO mesasDAO;

    public MesasAdapter(Context context){
        this.context = context;
        mesasDAO = new MesaDAO(context);
        update();
    }

    public void update(){ mesas = mesasDAO.listarMesas(); }

    public MesasViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        ConstraintLayout v = (ConstraintLayout) LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.list_item_mesa, parent, false);
        MesasViewHolder vh = new MesasViewHolder(v, context);
        return vh;
    }
    public void onBindViewHolder(MesasViewHolder holder, int position){
        holder.numero.setText(Integer.toString(mesas.get(position).getId()));
        holder.numeroClientes.setText(Integer.toString(mesas.get(position).getNumeroClientes()));
        holder.ocupada.setText(mesas.get(position).getOcupada()? "Ocupada" : "Disponível");
        String color = mesas.get(position).getOcupada()? "#F44336" : "#FF03DAC5";
        holder.ocupada.setTextColor(Color.parseColor(color));
    }
    public int getItemCount(){ return mesas.size(); }
}

class MesasViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public Context context;
    public TextView numeroClientes, numero, ocupada;

    public MesasViewHolder(ConstraintLayout v, Context context){
        super(v);
        this.context = context;
        numero = v.findViewById(R.id.itemNumero);
        numeroClientes = v.findViewById(R.id.itemNomePedido);
        ocupada = v.findViewById(R.id.itemPreco);
        v.setOnClickListener(this);
    }

    public void onClick(View v){
        Intent intent = new Intent(context,AtualizaMesaActivity.class);
        intent.putExtra("mesa_id", Integer.parseInt(numero.getText().toString()));
        context.startActivity(intent);
    }
}
