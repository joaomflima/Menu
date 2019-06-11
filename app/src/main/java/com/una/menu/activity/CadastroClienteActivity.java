package com.una.menu.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.una.menu.R;

public class CadastroClienteActivity extends AppCompatActivity {

    // Variaveis que vão receber os objetos da tela.
    EditText editNomeCad, editEmailCad, editSenhaCad, editSenhaConf;
    Button btnCadastrar, btnCancelar;
    ProgressBar progressCad;

    // Variaveis para conexão com web service.
    final String HOST = "https://menu-app.000webhostapp.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_cliente);
        setTitle("Cadastro Usuário");
        // Recebe os ID's dos objetos da tela;
        editNomeCad = findViewById(R.id.editNomeCad);
        editEmailCad = findViewById(R.id.editEmailCad);
        editSenhaCad = findViewById(R.id.editSenhaCad);
        editSenhaConf = findViewById(R.id.editSenhaConf);
        btnCadastrar = findViewById(R.id.btnCadastrar);
        btnCancelar = findViewById(R.id.btnCancelar);
        progressCad = findViewById(R.id.progressCad);

        // Desativa o ProgressBar
        progressCad.setVisibility(View.GONE);

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nome = editNomeCad.getText().toString();
                String email = editEmailCad.getText().toString();
                String senha = editSenhaCad.getText().toString();
                String senhaConf = editSenhaConf.getText().toString();

                String URL = HOST + "/webservice/login/cadastrar.php";

                if (nome.isEmpty() || email.isEmpty() || senha.isEmpty()) {
                    Toast.makeText(CadastroClienteActivity.this, "Nenhum campo pode estar vazio",
                            Toast.LENGTH_LONG).show();
                } else {

                    if(senha.equals(senhaConf)) {

                        // Habilita o ProgressBar
                        progressCad.setVisibility(View.VISIBLE);

                        // DESABILITA o cursor
                        editEmailCad.setCursorVisible(false);
                        editSenhaCad.setCursorVisible(false);
                        editSenhaCad.setCursorVisible(false);
                        editSenhaConf.setCursorVisible(false);

                        fechaTeclado();

                        Ion.with(CadastroClienteActivity.this)
                                .load(URL)
                                .setBodyParameter("nome_app", nome)
                                .setBodyParameter("email_app", email)
                                .setBodyParameter("senha_app", senha)
                                .asJsonObject()
                                .setCallback(new FutureCallback<JsonObject>() {
                                    @Override
                                    public void onCompleted(Exception e, JsonObject result) {

                                        String RETORNO = result.get("CADASTRO").getAsString();

                                        try {
                                            switch (RETORNO) {
                                                case "EMAIL_ERRO":
                                                    Toast.makeText(CadastroClienteActivity.this, "Este email já está cadastrado!", Toast.LENGTH_LONG).show();
                                                    break;
                                                case "SUCESSO":
                                                    Toast.makeText(CadastroClienteActivity.this, "Cadastro realizado com sucesso!", Toast.LENGTH_LONG).show();
                                                    Intent abreLogin = new Intent(CadastroClienteActivity.this, LoginActivity.class);
                                                    startActivity(abreLogin);
                                                    break;
                                                default:
                                                    Toast.makeText(CadastroClienteActivity.this, "ERRO DESCONHECIDO", Toast.LENGTH_LONG).show();
                                                    break;
                                            }

                                        } catch (Exception erro) {
                                            Toast.makeText(CadastroClienteActivity.this, "Ops! Falha na conexão, " + erro, Toast.LENGTH_LONG).show();
                                        }

                                        // Desativa o ProgressBar
                                        progressCad.setVisibility(View.GONE);
                                        // Habilita o cursor
                                        editEmailCad.setCursorVisible(true);
                                        editSenhaCad.setCursorVisible(true);
                                        editSenhaCad.setCursorVisible(true);
                                        editSenhaConf.setCursorVisible(true);
                                    }
                                });

                    } else {
                        Toast.makeText(CadastroClienteActivity.this, "As senhas não conferem", Toast.LENGTH_LONG).show();
                    }

                }

            }
        });
    }

    private void fechaTeclado() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }


}
