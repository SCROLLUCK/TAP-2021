package com.example.appgerencialanchonete;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.text.ParseException;
import java.util.Date;

import dao.CardapioDAO;
import model.Garcom;
import model.Opcao;

public class CadastroOpcaoActivity extends AppCompatActivity {

    private CardapioDAO cardapioDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.cardapioDAO = new CardapioDAO(this);
        setContentView(R.layout.activity_cadastro_opcao);
    }

    public void cadastrarOpcao(View view) throws ParseException {

        EditText editNome = findViewById(R.id.editNome);
        EditText editPreco = findViewById(R.id.editPreco);
        String nome = editNome.getText().toString();
        Float preco =  Float.parseFloat(editPreco.getText().toString());
        Opcao opcao = new Opcao(-1,nome,preco);
        opcao.toString();
        cardapioDAO.adicionarOpcao(opcao);
        finish();
    }
}