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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.una.menu.R;

public class LoginActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 255;
    // Variaveis que vão receber os objetos da tela.
    EditText editEmailLog, editSenhaLog;
    Button btnLogar, btnLogarGoogle;
    TextView textCadastrar, textSemLog;
    ProgressBar progressLogin;
    GoogleSignInClient mGoogleSignInClient;
    // Variaveis para conexão com web service.
    final String HOST = "https://menu-app.000webhostapp.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("475087318850-risdmndu2abpf0nadmaoo617kgr7q6us.apps.googleusercontent.com")
                .requestEmail()
                .build();
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(LoginActivity.this);
        if (account != null) {
            Intent abreInicio = new Intent(LoginActivity.this, PrincipalActivity.class);
            abreInicio.putExtra("id_usuario", account.getId());
            abreInicio.putExtra("nome_usuario", account.getDisplayName());
            startActivity(abreInicio);
        }
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        // Recebe os ID's dos objetos da tela -----------------------------
        editEmailLog = findViewById(R.id.editEmailLog);
        editSenhaLog = findViewById(R.id.editSenhaLog);
        btnLogar = findViewById(R.id.btnLogar);
        btnLogarGoogle = findViewById(R.id.btnGoogleLogin);
        textCadastrar = findViewById(R.id.btnCadastrar);
        textSemLog = findViewById(R.id.btnSemLogar);
        progressLogin = findViewById(R.id.progressLogin);

        // Desativa o ProgressBar
        progressLogin.setVisibility(View.GONE);

        //Habitita o cursor;
        //editText_email1.setCursorVisible(true);
        //editText_senha1.setCursorVisible(true);

        // Abre a tela de cadastro -----------------------------------------
        textCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent abreCadastro = new Intent(LoginActivity.this, CadastroClienteActivity.class);
                startActivity(abreCadastro);
            }
        });


        // Inicia o app sem cadastro ----------------------------------------
        textSemLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent abreSemLogar = new Intent(LoginActivity.this, PrincipalActivity.class);

                abreSemLogar.putExtra("id_usuario", 0);
                abreSemLogar.putExtra("nome_usuario", "Visitante");
                startActivity(abreSemLogar);

            }
        });

        //
        btnLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = editEmailLog.getText().toString();
                String senha = editSenhaLog.getText().toString();

                String URL = HOST + "/webservice/login/logar.php";

                fechaTeclado();

                if (email.isEmpty() || senha.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Nenhum campo pode estar vazio",
                            Toast.LENGTH_LONG).show();
                } else {

                    // Ativa o ProgressBar
                    progressLogin.setVisibility(View.VISIBLE);

                    // Desabilita o Cursor;
                    editEmailLog.setCursorVisible(false);
                    editSenhaLog.setCursorVisible(false);

                    Ion.with(LoginActivity.this)
                            .load(URL)
                            .setBodyParameter("email_app", email)
                            .setBodyParameter("senha_app", senha)
                            .asJsonArray()
                            .setCallback(new FutureCallback<JsonArray>() {
                                @Override
                                public void onCompleted(Exception e, JsonArray result) {

                                    try {

                                        for (int i = 0; i < result.size(); i++) {

                                            JsonObject obj = result.get(i).getAsJsonObject();

                                            String RETORNO = obj.get("LOGIN").getAsString();

                                            if (RETORNO.equals("OK")) {

                                                String id = obj.get("id").getAsString();
                                                String nome = obj.get("nome").getAsString();
                                                String data_nascimento = obj.get("data_nascimento").getAsString();
                                                String sexo = obj.get("sexo").getAsString();
                                                String email = obj.get("email").getAsString();

                                                System.out.println(id);
                                                System.out.println(nome);
                                                System.out.println(data_nascimento);
                                                System.out.println(sexo);
                                                System.out.println(email);

                                                Intent abreInicio = new Intent(LoginActivity.this, PrincipalActivity.class);
                                                abreInicio.putExtra("id_usuario", id);
                                                abreInicio.putExtra("nome_usuario", nome);
                                                startActivity(abreInicio);

                                                // Desativa o ProgressBar
                                                progressLogin.setVisibility(View.GONE);

                                                // Habilita o cursor
                                                editEmailLog.setCursorVisible(true);
                                                editSenhaLog.setCursorVisible(true);

                                            } else {

                                                // Desativa o ProgressBar
                                                progressLogin.setVisibility(View.GONE);

                                                // Habilita o cursor
                                                editEmailLog.setCursorVisible(true);
                                                editSenhaLog.setCursorVisible(true);

                                                Toast.makeText(LoginActivity.this, "Email ou senha estão incorretos",
                                                        Toast.LENGTH_LONG).show();
                                            }

                                        }

                                    } catch (Exception erro) {

                                        // Desativa o ProgressBar
                                        progressLogin.setVisibility(View.GONE);

                                        // Habilita o cursor
                                        editEmailLog.setCursorVisible(true);
                                        editSenhaLog.setCursorVisible(true);

                                        Toast.makeText(LoginActivity.this, "Ops! Erro, " + erro, Toast.LENGTH_LONG).show();
                                    }
                                }
                            });

                }

            }
        });
        btnLogarGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(LoginActivity.this);
                if (account != null) {
                    Intent abreInicio = new Intent(LoginActivity.this, PrincipalActivity.class);
                    abreInicio.putExtra("id_usuario", account.getId());
                    abreInicio.putExtra("nome_usuario", account.getDisplayName());
                    startActivity(abreInicio);
                } else {
                    signIn();
                }
            }
        });
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                if(account != null) {
                    Intent abreInicio = new Intent(LoginActivity.this, PrincipalActivity.class);
                    abreInicio.putExtra("id_usuario", account.getId());
                    abreInicio.putExtra("nome_usuario", account.getDisplayName());
                    startActivity(abreInicio);
                }
            } catch (ApiException e) {
                e.printStackTrace();
            }
        }
    }
    private void fechaTeclado() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }



    // Desativa o ProgressBar
    //progressLogin.setVisibility(View.GONE);


                /*String[] dados = resultado.split(",");
                Intent abreInicio = new Intent(LoginActivity.this, BuscaActivity.class);
                abreInicio.putExtra("id_usuario", dados[1]);
                abreInicio.putExtra("nome_usuario", dados[2]);
                startActivity(abreInicio);*/


//if (email.isEmpty() || senha.isEmpty()) {
    //Toast.makeText(getApplicationContext(), "Nenhum campo pode estar vazio!", Toast.LENGTH_LONG).show();
    //url = "http://192.168.0.109/webservice/login/logar.php";
    //url = "https://menu-app.000webhostapp.com/webservice/login/logar.php";
    // Ativa o ProgressBar
    //progressLogin.setVisibility(View.VISIBLE);
    // Desabilita o Cursor;
    //editText_senha1.setCursorVisible(false);
    //editText_email1.setCursorVisible(false);
    //fechaTeclado();

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

