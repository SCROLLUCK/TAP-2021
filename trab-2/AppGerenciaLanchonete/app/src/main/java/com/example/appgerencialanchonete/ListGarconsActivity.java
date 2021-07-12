package com.example.appgerencialanchonete;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;

import dao.GarcomDAO;
import model.Garcom;

public class ListGarconsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private GarconsAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_garcons);

        recyclerView = findViewById(R.id.garconsList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new GarconsAdapter(this);
        recyclerView.setAdapter(adapter);

    }

    protected void onRestart() {
        super.onRestart();
        adapter.update();
        adapter.notifyDataSetChanged();
    }

    public void onResume() {
        super.onResume();
        adapter.update();
        adapter.notifyDataSetChanged();

        recyclerView = findViewById(R.id.garconsList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new GarconsAdapter(this);
        recyclerView.setAdapter(adapter);
    }

    public void adicionarGarcom(View view){
        Intent intent = new Intent(this,CadastroGarcomActivity.class);
        startActivity(intent);
    }

}
class GarconsAdapter extends RecyclerView.Adapter<GarconsViewHolder> {
    private Context context;
    private ArrayList<Garcom> garcons;
    GarcomDAO garconsDAO;

    public GarconsAdapter(Context context){
        this.context = context;
        garconsDAO = new GarcomDAO(context);
        update();
    }

    public void update(){ garcons = garconsDAO.listarGarcons(); }

    public GarconsViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        ConstraintLayout v = (ConstraintLayout) LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        GarconsViewHolder vh = new GarconsViewHolder(v, context);
        return vh;
    }
    public void onBindViewHolder(GarconsViewHolder holder, int position){
        holder.nome.setText(garcons.get(position).getNome());
        holder.cpf.setText(garcons.get(position).getCpf());
        holder.id = garcons.get(position).getId();
    }
    public int getItemCount(){ return garcons.size(); }
}

class GarconsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public Context context;
    public TextView nome, cpf;
    private Garcom garcom;
    public int id;

    public GarconsViewHolder(ConstraintLayout v, Context context){
        super(v);
        this.context = context;
        nome = v.findViewById(R.id.itemNumero);
        cpf = v.findViewById(R.id.itemNomePedido);
        garcom = new Garcom(this.id,this.nome.getText().toString(),this.cpf.getText().toString(),"masculino", new Date());
        v.setOnClickListener(this);
    }

    public void onClick(View v){
        Toast.makeText(context,"Olá "+this.nome.getText().toString(), Toast.LENGTH_SHORT).show();
    }
}

