package com.una.menu.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.una.menu.R;
import com.una.menu.adapter.ProdutoAdapter;
import com.una.menu.model.Produto;

import java.util.ArrayList;
import java.util.List;

public class BuscaActivity extends AppCompatActivity {

    private TextView textView_nome;
    private Button button_teste;
    private SearchView searchProduto;
    private RecyclerView recyclerProdutos;
    private List<Produto> listaProduto = new ArrayList<>();
    private String HOST = "https://menu-app.000webhostapp.com/webservice";

    //Configurar adapter
    ProdutoAdapter produtoAdapter = new ProdutoAdapter( listaProduto );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busca);

        textView_nome = findViewById(R.id.textView_nome);
        searchProduto = findViewById(R.id.searchProduto);
        recyclerProdutos = findViewById(R.id.recyclerProdutos);

        String nomeUsuario = getIntent().getExtras().getString("nome_usuario");
        textView_nome.setText(nomeUsuario);

        // Configurar SearchView

        searchProduto.setQueryHint("Buscar Produtos");


        // Lista de produtos
        this.lerProdutos();

        //Configurar RecycleView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerProdutos.setLayoutManager(layoutManager);
        recyclerProdutos.setHasFixedSize(true);
        recyclerProdutos.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
        recyclerProdutos.setAdapter(produtoAdapter);
    }

    private void lerProdutos() {

        String url = HOST + "/readprodutos/read.php";

        Ion.with(getBaseContext())
                .load(url)
                .asJsonArray()
                .setCallback(new FutureCallback<JsonArray>() {
                    @Override
                    public void onCompleted(Exception e, JsonArray result) {

                        for(int i = 0; i < result.size(); i++) {

                            JsonObject obj = result.get(i).getAsJsonObject();

                            //String nome = obj.get("nome").getAsString();
                            //Produto p = new Produto(nome);

                            Produto p = new Produto();
                            p.setId_produto(obj.get("id_produto").getAsString());
                            p.setNome(obj.get("nome").getAsString());

                            listaProduto.add(p);
                        }

                        produtoAdapter.notifyDataSetChanged();
                    }
                });
    }
}
