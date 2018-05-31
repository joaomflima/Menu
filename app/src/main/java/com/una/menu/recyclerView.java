package com.una.menu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.una.menu.adapter.AdapterProdutos;
import com.una.menu.model.Produto;

import java.util.ArrayList;
import java.util.List;

public class recyclerView extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Produto> listaProduto = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        recyclerView = findViewById(R.id.recyclerView);

        // Lista Produtos
        this.criarProdutos();

        //Configurar adapter
        AdapterProdutos adapterProdutos = new AdapterProdutos( listaProduto );

        //Configurar RecycleView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
        recyclerView.setAdapter(adapterProdutos);
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
}
