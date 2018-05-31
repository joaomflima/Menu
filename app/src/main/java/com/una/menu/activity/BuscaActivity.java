package com.una.menu.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.una.menu.R;
import com.una.menu.recyclerView;

public class BuscaActivity extends AppCompatActivity {

    private TextView textView_nome;

    private Button button_teste;

    private ListView listProdutos;
    private String[] produtos = new String[]{"Produto01", "Produto02", "Produto03",
            "Produto04", "Produto05", "Produto06", "Produto07", "Produto08",
            "Produto09"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busca);

        textView_nome = findViewById(R.id.textView_nome);
        listProdutos = findViewById(R.id.listProdutos);

        String nomeUsuario = getIntent().getExtras().getString("nome_usuario");
        textView_nome.setText(nomeUsuario);

        // Criar adaptador para a lista;
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(
                getApplicationContext(),
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                produtos
        );

        // Adiciona o adaptador para a lista;
        listProdutos.setAdapter(adaptador);

        // Adiciona clique na lista;
        listProdutos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String valorSelecionado = listProdutos.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(), valorSelecionado, Toast.LENGTH_LONG).show();
            }
        });

        // Ir para RecyclerView

        button_teste = (Button) findViewById(R.id.button_teste);

        button_teste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent abreRecyclerView = new Intent(getApplicationContext(), recyclerView.class);
                startActivity(abreRecyclerView);
            }
        });
    }
}
