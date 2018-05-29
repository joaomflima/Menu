package com.una.menu;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class TelaLogin extends AppCompatActivity {

    // Variaveis que vão receber os objetos da tela.
    EditText editText_email1, editText_senha1;
    Button button_logar;
    TextView textView_cadastro, textView_semLogar;

    // Variaveis para conexão com web service.
    String url = "";
    String parametros = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_login);

        // Recebe os ID's dos objetos da tela -----------------------------
        editText_email1 = findViewById(R.id.editText_email1);
        editText_senha1 = findViewById(R.id.editText_senha1);
        button_logar = findViewById(R.id.button_logar);
        textView_cadastro = findViewById(R.id.textView_cadastro);
        textView_semLogar = findViewById(R.id.textView_semLogar);


        // Abre a tela de cadastro -----------------------------------------
        textView_cadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent abreCadastro = new Intent(TelaLogin.this, TelaCadastro.class);
                startActivity(abreCadastro);
            }
        });


        // Inicia o app sem cadastro ----------------------------------------
        textView_semLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent abreSemLogar = new Intent(TelaLogin.this, TelaInicial.class);

                abreSemLogar.putExtra("nome_usuario", "Visitante");
                startActivity(abreSemLogar);

            }
        });



        //
        button_logar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConnectivityManager connectivityManager = (ConnectivityManager)
                        getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

                if (networkInfo != null && networkInfo.isConnected()) {

                    String email = editText_email1.getText().toString();
                    String senha = editText_senha1.getText().toString();

                    if (email.isEmpty() || senha.isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Nenhum campo pode estar vazio!", Toast.LENGTH_LONG).show();
                    } else {
                        url = "http://192.168.0.109/webservice/login/logar.php";
                        parametros = "email=" + email + "&senha=" + senha;
                        new SolicitaDados().execute(url);
                    }

                } else {
                    Toast.makeText(getApplicationContext(), "Nenhuma conexão foi detectada!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
        //
    private class SolicitaDados extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {

            return Conexao.postDados(urls[0], parametros);
        }

        //
        @Override
        protected void onPostExecute(String resultado) {
            //textView.SetText(resultado);
            //editText_email1.setText(resultado);
            //editText_email1.setText(resultado);
            //editText_email1.setText(dados[0] + " - " + dados[1] + " - " + dados[2]);


            if(resultado.contains("login_ok")) {

                String[] dados = resultado.split(",");
                Intent abreInicio = new Intent(TelaLogin.this, TelaInicial.class);
                abreInicio.putExtra("id_usuario", dados[1]);
                abreInicio.putExtra("nome_usuario", dados[2]);
                startActivity(abreInicio);

            } else {
                Toast.makeText(getApplicationContext(), "Email ou Senha estão incorretos!", Toast.LENGTH_LONG).show();
            }
        }
    }
    /*
    @Override
    protected void onPause() {
        super.OnPause();
        finish();
    }
    */
}

/*
https://www.flextool.com.br/tabela_cores.html  --> Site com código de cores.

http://rogerdudler.github.io/git-guide/index.pt_BR.html   -- Tutorial Git

https://try.github.io/levels/1/challenges/13
 */

