package com.una.menu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.una.menu.adapter.ProdutoAdapter;
import com.una.menu.model.Produto;

import java.util.ArrayList;
import java.util.List;

public class recyclerView extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Produto> listaProduto = new ArrayList<>();
    private String HOST = "http://192.168.0.109/WebService";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        recyclerView = findViewById(R.id.recyclerView);

        // Lista Produtos
        this.criarProdutos();
        this.lerProdutos();

        //Configurar adapter
        ProdutoAdapter produtoAdapter = new ProdutoAdapter( listaProduto );

        //Configurar RecycleView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
        recyclerView.setAdapter(produtoAdapter);
    }

    public void criarProdutos(){
        Produto produto = new Produto("Pão de Queijo", "Comum", "R$1,50");
        listaProduto.add(produto);

        produto = new Produto("Coxinha", "Recheio de frango com catupiri", "R$2,50");
        listaProduto.add(produto);

        produto = new Produto("Risoles", "Presunto e Queijo, Espinafre com Mortadela", "R$3,50");
        listaProduto.add(produto);

        produto = new Produto("Croquetes ", "Recheio de frango com catupiri", "R$2,50");
        listaProduto.add(produto);

        produto = new Produto("Coxinha", "Recheio de frango com catupiri", "R$2,50");
        listaProduto.add(produto);

        produto = new Produto("Risoles", "Presunto e Queijo, Espinafre com Mortadela", "R$3,50");
        listaProduto.add(produto);

        produto = new Produto("Croquetes ", "Recheio de frango com catupiri", "R$2,50");
        listaProduto.add(produto);

        produto = new Produto("Coxinha", "Recheio de frango com catupiri", "R$2,50");
        listaProduto.add(produto);

        produto = new Produto("Risoles", "Presunto e Queijo, Espinafre com Mortadela", "R$3,50");
        listaProduto.add(produto);

        produto = new Produto("Croquetes ", "Recheio de frango com catupiri", "R$2,50");
        listaProduto.add(produto);

        produto = new Produto("Coxinha", "Recheio de frango com catupiri", "R$2,50");
        listaProduto.add(produto);

        produto = new Produto("Risoles", "Presunto e Queijo, Espinafre com Mortadela", "R$3,50");
        listaProduto.add(produto);

        produto = new Produto("Croquetes ", "Recheio de frango com catupiri", "R$2,50");
        listaProduto.add(produto);
    }

    private void lerProdutos() {

        String url = HOST + "/readProdutos/read.php";

        Produto produto = new Produto("Pão de Queijo", "Comum", "R$1,50");
        listaProduto.add(produto);

        produto = new Produto("Coxinha", "Recheio de frango com catupiri", "R$2,50");
        listaProduto.add(produto);

        Ion.with(getBaseContext())
                .load(url)
                .asJsonArray()
                .setCallback(new FutureCallback<JsonArray>() {
                    @Override
                    public void onCompleted(Exception e, JsonArray result) {

                        for(int i = 0; i < result.size(); i++) {

                            JsonObject obj = result.get(i).getAsJsonObject();

                            String nome = obj.get("nome").getAsString();

                            Produto p = new Produto();

                            p.setNome(obj.get("nome").getAsString());
                            p.setDescricao(obj.get("descricao").getAsString());
                            p.setNome(obj.get("nome").getAsString());

                            System.out.println(p);
                            System.out.println(obj.get("descricao").getAsString());

                            listaProduto.add(p);
                        }
                    }
                });
    }
}
