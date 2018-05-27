package com.una.menu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class TelaInicial extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial);


        TextView textView_nome = findViewById(R.id.textView_nome);

        String nomeUsuario = getIntent().getExtras().getString("nome_usuario");

        textView_nome.setText(nomeUsuario);
    }
}
