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

import dao.CardapioDAO;
import model.Opcao;

public class ListCardapioActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CardapioAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_cardapio);

        recyclerView = findViewById(R.id.cardapioList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new CardapioAdapter(this);
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

        recyclerView = findViewById(R.id.cardapioList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CardapioAdapter(this);
        recyclerView.setAdapter(adapter);
    }

    public void adicionarOpcao(View view){
        Intent intent = new Intent(this,CadastroOpcaoActivity.class);
        startActivity(intent);
    }
}


class CardapioAdapter extends RecyclerView.Adapter<CardapioViewHolder> {
    private Context context;
    private ArrayList<Opcao> cardapio;
    CardapioDAO cardapioDAO;

    public CardapioAdapter(Context context){
        this.context = context;
        cardapioDAO = new CardapioDAO(context);
        update();
    }

    public void update(){ cardapio = cardapioDAO.listarCardapio(); }

    public CardapioViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        ConstraintLayout v = (ConstraintLayout) LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.list_item_cardapio, parent, false);
        CardapioViewHolder vh = new CardapioViewHolder(v, context);
        return vh;
    }
    public void onBindViewHolder(CardapioViewHolder holder, int position){
        holder.nome.setText(cardapio.get(position).getNome());
        holder.preco.setText(Float.toString(cardapio.get(position).getPreco()));
        holder.id = cardapio.get(position).getId();
    }
    public int getItemCount(){ return cardapio.size(); }
}

class CardapioViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public Context context;
    public TextView nome, preco;
    public int id;

    public CardapioViewHolder(ConstraintLayout v, Context context){
        super(v);
        this.context = context;
        nome = v.findViewById(R.id.itemNomePedido);
        preco = v.findViewById(R.id.itemPreco);
        v.setOnClickListener(this);
    }

    public void onClick(View v){
        Toast.makeText(context,"Olá "+this.nome.getText().toString(), Toast.LENGTH_SHORT).show();
    }
}