package com.una.menu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.una.menu.R;
import com.una.menu.fragment.BuscaFragment;
import com.una.menu.fragment.LanchonetesFragment;
import com.una.menu.fragment.ProdutosViewFragment;

public class PrincipalActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);


        // Meus Códigos Inicio ----------------------------------------------------------------------------------------------------------------

        // Seta título para Activity
        this.setTitle("Menu");

        // Variaveis
        // Variaveis

        // Chama o Fragment de Busca
        BuscaFragment buscaFragment = new BuscaFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameContainer, buscaFragment);
        fragmentTransaction.commit();

        // Recebe id e nome do Usuario
        //if(getIntent().getExtras() != null) {
            //int idUsuario = getIntent().getExtras().getInt("id_usuario");
            //String nomeUsuario = getIntent().getExtras().getString("nome_usuario");
        //}


        // Meus Códigos FIM ----------------------------------------------------------------------------------------------------------------


        Toolbar toolbar = findViewById(R.id.toolbar);
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
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
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
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        /*if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } */

        if (id == R.id.nav_lanchonete) {

            /*Intent abreCadastroLanch = new Intent(getApplicationContext(), CadLanchActivity.class);
            startActivity(abreCadastroLanch);*/

            LanchonetesFragment lanchonetesFragment = new LanchonetesFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frameContainer, lanchonetesFragment);
            fragmentTransaction.commit();

        } else if (id == R.id.nav_produto) {

            /*Intent abreCadastroLanch = new Intent(getApplicationContext(), CadLanchActivity.class);
            startActivity(abreCadastroLanch);*/

            ProdutosViewFragment produtosViewFragment = new ProdutosViewFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frameContainer, produtosViewFragment);
            fragmentTransaction.commit();

        } else if (id == R.id.nav_busca) {

            BuscaFragment buscaFragment = new BuscaFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frameContainer, buscaFragment);
            fragmentTransaction.commit();
        } else if(id == R.id.nav_logoff){
            GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestIdToken("475087318850-risdmndu2abpf0nadmaoo617kgr7q6us.apps.googleusercontent.com")
                    .requestEmail()
                    .build();
            GoogleSignInClient mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
            mGoogleSignInClient.signOut();
            Intent abreInicio = new Intent(this, LoginActivity.class);
            startActivity(abreInicio);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
