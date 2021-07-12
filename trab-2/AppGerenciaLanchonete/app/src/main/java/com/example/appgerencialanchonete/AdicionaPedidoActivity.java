package com.example.appgerencialanchonete;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import dao.CardapioDAO;
import dao.MesaDAO;
import dao.PedidoDAO;
import model.Mesa;
import model.Opcao;
import model.Pedido;

public class AdicionaPedidoActivity extends AppCompatActivity {

    private CardapioDAO cardapioDAO;
    private ArrayList<Opcao> cardapio;
    private PedidoDAO pedidoDAO;
    private Pedido pedido;
    private EditText quantidade;
    private int mesa_id;
    private MesaDAO mesaDAO;
    private Mesa mesa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adiciona_pedido);


        pedidoDAO = new PedidoDAO(this);
        cardapioDAO = new CardapioDAO(this);
        mesaDAO = new MesaDAO(this);
        cardapio = cardapioDAO.listarCardapio();
        quantidade = findViewById(R.id.quantidade);
        quantidade.setText("0");

        Intent intent = getIntent();
        mesa_id = intent.getIntExtra("mesa_id",0);
        mesa = mesaDAO.obterMesa(mesa_id);
        Log.i("LOG",mesa.toString());

        final List<String> list = new ArrayList<String>();
        for(int i=0; i< cardapio.size(); i++){
            list.add(cardapio.get(i).showSelect());
        }

        final Spinner listCardapio = (Spinner) findViewById(R.id.cardapio);
        ArrayAdapter<String> adp1 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        adp1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        listCardapio.setAdapter(adp1);

        listCardapio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
                // TODO Auto-generated method stub
                int QTD = 0;
                if(!quantidade.getText().toString().equals("")){
                    QTD = Integer.parseInt(quantidade.getText().toString());
                }
                pedido = new Pedido(mesa.getId(),-1,cardapio.get(position).getNome(),cardapio.get(position).getId(),cardapio.get(position).getPreco(),QTD,"pendente");
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });
    }

    public void SalvarPedido(View view){
        pedido.setQuantidade(Integer.parseInt(quantidade.getText().toString()));
        pedidoDAO.adicionarPedido(mesa,pedido);
        finish();
    }
}