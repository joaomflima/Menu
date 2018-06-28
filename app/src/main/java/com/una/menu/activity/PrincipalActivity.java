package com.una.menu.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
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

public class PrincipalActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    // Variaveis
    private SearchView searchProduto;
    private RecyclerView recyclerProdutos;
    private ProgressBar progressLoad;
    private List<Produto> listaProduto = new ArrayList<>();
    private String HOST = "https://menu-app.000webhostapp.com/webservice";


    // Configurar Adapter
    ProdutoAdapter adapter = new ProdutoAdapter(listaProduto);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        //----------------------------------------------------------------------------------------------------------------
        //----------------------------------------------------------------------------------------------------------------

        // Variaveis
        recyclerProdutos = findViewById(R.id.recyclerView);
        searchProduto = findViewById(R.id.searchView5);
        progressLoad = findViewById(R.id.progressProdutos);

        // Lista Produtos
        this.lerProdutos();

        // Configurar RecyclerView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerProdutos.setLayoutManager(layoutManager);
        recyclerProdutos.setHasFixedSize(true);
        recyclerProdutos.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
        recyclerProdutos.setAdapter(adapter);

        progressLoad.setVisibility(View.VISIBLE);

        this.setTitle("Teste Titulo");

        // Configura SearchView
        searchProduto.setQueryHint("Pesquisar Produtos");
        searchProduto.setOnQueryTextListener(new android.support.v7.widget.SearchView.OnQueryTextListener() {

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

        //----------------------------------------------------------------------------------------------------------------
        //----------------------------------------------------------------------------------------------------------------


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /* BOTÃO FLUTUANTE
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        */
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }
    //----------------------------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------------------------

    // Lista Produtos quando a Activity é iniciada
    private void lerProdutos() {

//        fechaTeclado();

        progressLoad.setVisibility(View.VISIBLE);
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
                            adapter.notifyDataSetChanged();


                        } catch (Exception erro) {
                            Toast.makeText(getApplicationContext(), "Ops! Erro, " + erro, Toast.LENGTH_LONG).show();
                        }

                        progressLoad.setVisibility(View.GONE);
                    }
                });
    }

    private void buscaProdutos(String produto) {

//        fechaTeclado();

        progressLoad.setVisibility(View.VISIBLE);

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
                                adapter.notifyDataSetChanged();


                            } catch (Exception erro) {

                                Toast.makeText(getApplicationContext(), "Ops! Erro, " + erro, Toast.LENGTH_LONG).show();
                            }

                            progressLoad.setVisibility(View.GONE);

                        }
                    });
        }
    }
    //----------------------------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------------------------

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
