package com.una.menu.activity;

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
import android.widget.Toast;

import com.una.menu.Conexao;
import com.una.menu.R;

public class CadastroClienteActivity extends AppCompatActivity {

    // Variaveis que vão receber os objetos da tela.
    EditText editText_nome, editText_email2, editText_senha2;
    Button button_cadastrar, button_cancelar;

    // Variaveis para conexão com web service.
    String url = "";
    String parametros = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_cliente);

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

        button_cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ConnectivityManager connectivityManager = (ConnectivityManager)
                        getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

                if (networkInfo != null && networkInfo.isConnected()) {

                    String nome = editText_nome.getText().toString();
                    String email = editText_email2.getText().toString();
                    String senha = editText_senha2.getText().toString();

                    if (nome.isEmpty() || email.isEmpty() || senha.isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Nenhum campo pode estar vazio!", Toast.LENGTH_LONG).show();
                    } else {
                        url = "http://192.168.0.109/webservice/login/registrar.php";
                        parametros = "nome=" + nome + "&email=" + email + "&senha=" + senha;
                        new SolicitaDados().execute(url);
                    }

                } else {
                    Toast.makeText(getApplicationContext(), "Nenhuma conexão foi detectada!", Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    private class SolicitaDados extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {

            return Conexao.postDados(urls[0], parametros);
        }

        //
        @Override
        protected void onPostExecute(String resultado) {

            //editText_nome.setText(resultado);

            if(resultado.contains("email_erro")) {

                Toast.makeText(getApplicationContext(), "Este email já está cadastrado.", Toast.LENGTH_LONG).show();

            } else if(resultado.contains("registro_ok")) {

                Toast.makeText(getApplicationContext(), "Registro concluído com sucesso!", Toast.LENGTH_LONG).show();
                Intent abreInicio = new Intent(CadastroClienteActivity.this, LoginActivity.class);
                startActivity(abreInicio);

            } else {
                Toast.makeText(getApplicationContext(), "Ocorreu um erro.", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
