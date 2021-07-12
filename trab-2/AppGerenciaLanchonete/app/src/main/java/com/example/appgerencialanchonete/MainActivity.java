package com.example.appgerencialanchonete;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void gerenciarGarcons(View view){
        Intent intent = new Intent(this,ListGarconsActivity.class);
        startActivity(intent);
    }

    public void gerenciarMesas(View view){
        Intent intent = new Intent(this,ListMesasActivity.class);
        startActivity(intent);
    }

    public void gerenciarCardapio(View view){
        Intent intent = new Intent(this,ListCardapioActivity.class);
        startActivity(intent);
    }

    public void gerenciarPedidos(View view){
        Intent intent = new Intent(this,PedidosPendentesActivity.class);
        startActivity(intent);
    }
}