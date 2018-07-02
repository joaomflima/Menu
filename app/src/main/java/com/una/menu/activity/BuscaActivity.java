package com.una.menu.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

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
    private SearchView searchProduto;
    private RecyclerView recyclerProdutos;
    private List<Produto> listaProduto = new ArrayList<>();
    private String HOST = "https://menu-app.000webhostapp.com/webservice";
    private ProgressBar progressPesquisa;
    private Button button_teste;

    //Configurar adapter
    ProdutoAdapter produtoAdapter = new ProdutoAdapter( listaProduto );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busca);

        textView_nome = findViewById(R.id.textView_nome);
        searchProduto = findViewById(R.id.searchProduto);
        recyclerProdutos = findViewById(R.id.recyclerViewProdutos);
        progressPesquisa = findViewById(R.id.progressPesquisa);

        String nomeUsuario = getIntent().getExtras().getString("nome_usuario");
        textView_nome.setText(nomeUsuario);

        // Configurar SearchView
        searchProduto.setQueryHint("Buscar Produtos");
        searchProduto.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {

                String produto = "%" + query + "%";
                buscaProdutos(produto);

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //System.out.println("newText: "+ newText);
                //System.out.println(produto);
                return false;
            }
        });


        // Lista de produtos
        this.lerProdutos();
        //this.buscaProdutos("%suco%");


        //Configurar RecycleView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerProdutos.setLayoutManager(layoutManager);
        recyclerProdutos.setHasFixedSize(true);
        recyclerProdutos.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
        recyclerProdutos.setAdapter(produtoAdapter);

        // teste menu lateral --------------

        button_teste = findViewById(R.id.button_teste);

        button_teste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent abreMenu = new Intent(BuscaActivity.this, PrincipalActivity.class);
                startActivity(abreMenu);
            }
        });

        // teste menu lateral --------------
    }

    private void buscaProdutos(String produto) {

        fechaTeclado();

        progressPesquisa.setVisibility(View.VISIBLE);

        String url = HOST + "/readprodutos/readpesquisa.php";
        listaProduto.clear();

        if (produto.length() > 0) {

            Ion.with(getBaseContext())
                    .load(url)
                    .setBodyParameter("produto", produto)
                    .asJsonArray()
                    .setCallback(new FutureCallback<JsonArray>() {
                        @Override
                        public void onCompleted(Exception e, JsonArray result) {

                            try {

                                for (int i = 0; i < result.size(); i++) {

                                    JsonObject obj = result.get(i).getAsJsonObject();

                                    //String nome = obj.get("nome").getAsString();
                                    //Produto p = new Produto(nome);

                                    Produto p = new Produto();
                                    p.setId_produto(obj.get("id_produto").getAsString());
                                    p.setNome(obj.get("nome").getAsString());
                                    p.setDescricao(obj.get("descricao").getAsString());
                                    p.setPreco(obj.get("preco").getAsString());

                                    listaProduto.add(p);
                                }
                                produtoAdapter.notifyDataSetChanged();


                            } catch (Exception erro) {

                                Toast.makeText(BuscaActivity.this, "Ops! Erro, " + erro, Toast.LENGTH_LONG).show();
                            }

                            progressPesquisa.setVisibility(View.GONE);

                        }
                    });
        }
    }

    private void lerProdutos() {

        fechaTeclado();

        progressPesquisa.setVisibility(View.VISIBLE);
        String url = HOST + "/readprodutos/read.php";

        Ion.with(getBaseContext())
                .load(url)
                .asJsonArray()
                .setCallback(new FutureCallback<JsonArray>() {
                    @Override
                    public void onCompleted(Exception e, JsonArray result) {


                        try {

                            for(int i = 0; i < result.size(); i++) {

                                JsonObject obj = result.get(i).getAsJsonObject();

                                //String nome = obj.get("nome").getAsString();
                                //Produto p = new Produto(nome);

                                Produto p = new Produto();
                                p.setId_produto(obj.get("id_produto").getAsString());
                                p.setNome(obj.get("nome").getAsString());
                                p.setDescricao(obj.get("descricao").getAsString());
                                p.setPreco(obj.get("preco").getAsString());

                                listaProduto.add(p);
                            }
                            produtoAdapter.notifyDataSetChanged();

                        } catch (Exception erro) {
                            Toast.makeText(BuscaActivity.this, "Ops! Erro, " + erro, Toast.LENGTH_LONG).show();
                        }

                        progressPesquisa.setVisibility(View.GONE);
                    }
                });
    }

    private void fechaTeclado() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
