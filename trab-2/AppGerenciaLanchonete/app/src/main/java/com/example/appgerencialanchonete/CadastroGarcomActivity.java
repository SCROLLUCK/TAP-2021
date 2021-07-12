package com.example.appgerencialanchonete;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.text.ParseException;
import java.util.Date;

import dao.GarcomDAO;
import model.Garcom;

public class CadastroGarcomActivity extends AppCompatActivity {

    private GarcomDAO garcomDAO;
    private String genero = "masculino"; //MAXISTAA!!
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        garcomDAO = new GarcomDAO(this);
        setContentView(R.layout.activity_cadastro_garcom);
        RadioButton masculino = findViewById(R.id.masculino);
        masculino.setChecked(true);
    }
    public void cadastrarGarcom(View view) throws ParseException {

        EditText editNome = findViewById(R.id.editNome);
        EditText editCPF = findViewById(R.id.editPreco);

        String nome = editNome.getText().toString();
        String cpf = editCPF.getText().toString();

        if(nome.equals("") || cpf.equals("")){
            Toast.makeText(this,"Por favor, preencha todos os dados antes de continuar.",Toast.LENGTH_LONG);
            return;

        }
        Garcom garcom = new Garcom(nome,cpf,genero, new Date());
        garcom.toString();
        garcomDAO.adicionarGarcom(garcom);
        finish();

    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();
        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.masculino:
                if (checked) {
                    genero = "masculino";
                    RadioButton feminino = findViewById(R.id.feminino);
                    feminino.setChecked(false);
                    break;
                }
            case R.id.feminino:
                if (checked){
                    genero = "feminino";
                    RadioButton masculino = findViewById(R.id.masculino);
                    masculino.setChecked(false);
                    break;
                }

        }
    }


}