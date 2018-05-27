package com.una.menu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class TelaCadastro extends AppCompatActivity {

    // Variaveis que vão receber os objetos da tela.
    EditText editText_nome, editText_email2, editText_senha2;
    Button button_cadastrar, button_cancelar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro);

        // Recebe os ID's dos objetos da tela;
        editText_nome = findViewById(R.id.editText_nome);
        editText_email2 = findViewById(R.id.editText_email2);
        editText_senha2 = findViewById(R.id.editText_senha2);
        button_cadastrar = findViewById(R.id.button_cadastrar);
        button_cancelar = findViewById(R.id.button_cancelar);

        button_cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
