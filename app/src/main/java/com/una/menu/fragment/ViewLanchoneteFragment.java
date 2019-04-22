package com.una.menu.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.una.menu.R;
import com.una.menu.model.Lanchonete;


/**
 * A simple {@link Fragment} subclass.
 */
public class ViewLanchoneteFragment extends Fragment {

    // Variaveis que vão receber os objetos da tela.
    EditText editNomeCad, editTelefoneCad, editCelularCad;
    EditText editEnderecoCad, editCepCad, editCidadeCad, editEstadoCad;
    CheckBox editCartaoCredito, editCartaoDebito, editDinheiro;
    Button btnEditar, btnExcluir;
    ProgressBar progressBar;

    // Variaveis para conexão com web service.
    String HOST = "https://menu-app.000webhostapp.com/webservice";
    // String HOST = "http://localhost/webservice";

    public ViewLanchoneteFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_lanchonete_view, container, false);

        Bundle bundle = getArguments();
        Lanchonete lanchonete = new Lanchonete();

        if(bundle != null) {
            lanchonete = (Lanchonete) bundle.getSerializable("LANCHONETE");
        }

        // Recebe os ID's dos objetos da tela;
        editNomeCad         = view.findViewById(R.id.editNomeCad);
        editTelefoneCad     = view.findViewById(R.id.editTelefoneCad);
        editCelularCad      = view.findViewById(R.id.editCelularCad);
        editEnderecoCad     = view.findViewById(R.id.editLanchoneteCad);
        editCepCad          = view.findViewById(R.id.editCepCad);
        editCidadeCad       = view.findViewById(R.id.editCidadeCad);
        editEstadoCad       = view.findViewById(R.id.editEstadoCad);
        editCartaoCredito   = view.findViewById(R.id.cbCartaoCredito);
        editCartaoDebito    = view.findViewById(R.id.cbCartaoDebito);
        editDinheiro        = view.findViewById(R.id.cbDinheiro);

        btnEditar    = view.findViewById(R.id.btnEditarLanchonete);
        btnExcluir   = view.findViewById(R.id.btnExcluirLanchonete);
        progressBar  = view.findViewById(R.id.progressBar);

        // Desativa o ProgressBar
        progressBar.setVisibility(View.GONE);

        // Preenche os campos da tela
        editNomeCad.setText(lanchonete.getNome());
        editTelefoneCad.setText(lanchonete.getTelefone());
        editCelularCad.setText(lanchonete.getCelular());
        editEnderecoCad.setText(lanchonete.getEndereco());
        editCepCad.setText(lanchonete.getCep());
        editCidadeCad.setText(lanchonete.getCidade());
        editEstadoCad.setText(lanchonete.getEstado());
        editCartaoCredito.setChecked(true);
        editCartaoDebito.setChecked(true);
        editDinheiro.setChecked(true);

        final String id_lanchonete = lanchonete.getId_lanchonete();

        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {

                String nome = editNomeCad.getText().toString();
                String telefone = editTelefoneCad.getText().toString();
                String celular = editCelularCad.getText().toString();
                String endereco = editEnderecoCad.getText().toString();
                String cep = editCepCad.getText().toString();
                String cidade = editCidadeCad.getText().toString();
                String estado = editEstadoCad.getText().toString();
                String id_pagamento = verificarPagamento();
                String id_cliente = "1";

                String URL = HOST + "/lanchonete/update.php";

                if (nome.isEmpty() || telefone.isEmpty() || celular.isEmpty() || endereco.isEmpty()
                        || cep.isEmpty() || cidade.isEmpty() || estado.isEmpty() || id_pagamento == "0") {
                    Toast.makeText(v.getContext(), "Nenhum campo pode estar vazio",
                            Toast.LENGTH_LONG).show();
                } else {

                    // Habilita o ProgressBar
                    progressBar.setVisibility(View.VISIBLE);

                    // DESABILITA o cursor
                    editNomeCad.setCursorVisible(false);
                    editTelefoneCad.setCursorVisible(false);
                    editCelularCad.setCursorVisible(false);
                    editEnderecoCad.setCursorVisible(false);
                    editCepCad.setCursorVisible(false);
                    editCidadeCad.setCursorVisible(false);
                    editEstadoCad.setCursorVisible(false);
                    editCartaoCredito.setCursorVisible(false);
                    editCartaoDebito.setCursorVisible(false);
                    editDinheiro.setCursorVisible(false);

                    fechaTeclado(v);

                    Ion.with(v.getContext())
                            .load(URL)
                            .setBodyParameter("id_lanchonete", id_lanchonete)
                            .setBodyParameter("nome", nome)
                            .setBodyParameter("telefone", telefone)
                            .setBodyParameter("celular", celular)
                            .setBodyParameter("endereco", endereco)
                            .setBodyParameter("cep", cep)
                            .setBodyParameter("cidade", cidade)
                            .setBodyParameter("estado", estado)
                            .setBodyParameter("id_pagamento", id_pagamento)
                            .setBodyParameter("id_cliente", id_cliente)
                            .asJsonObject()
                            .setCallback(new FutureCallback<JsonObject>() {
                                @Override
                                public void onCompleted(Exception e, JsonObject result) {

                                    String RETORNO = result.get("ATUALIZACAO").getAsString();

                                    try {
                                        if (RETORNO.equals("SUCESSO")){
                                            Toast.makeText(v.getContext(), "Atualização realizada com sucesso!", Toast.LENGTH_LONG).show();
                                        } else {
                                            Toast.makeText(v.getContext(), "ERRO DESCONHECIDO", Toast.LENGTH_LONG).show();
                                        }

                                    } catch (Exception erro) {
                                        Toast.makeText(v.getContext(), "Ops! Falha na conexão, " + erro, Toast.LENGTH_LONG).show();
                                    }

                                    // Desativa o ProgressBar
                                    progressBar.setVisibility(View.GONE);

                                    // Habilita o cursor
                                    editNomeCad.setCursorVisible(true);
                                    editTelefoneCad.setCursorVisible(true);
                                    editCelularCad.setCursorVisible(true);
                                    editEnderecoCad.setCursorVisible(true);
                                    editCepCad.setCursorVisible(true);
                                    editCidadeCad.setCursorVisible(true);
                                    editEstadoCad.setCursorVisible(true);
                                    editCartaoCredito.setCursorVisible(true);
                                    editCartaoDebito.setCursorVisible(true);
                                    editDinheiro.setCursorVisible(true);
                                }
                            });

                }

                LanchonetesFragment lanchonetesFragment= new LanchonetesFragment();
                getFragmentManager().beginTransaction()
                        .replace(R.id.frameContainer, lanchonetesFragment,"findThisFragment")
                        .addToBackStack(null)
                        .commit();

            }
        });

        btnExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {

                String URL = HOST + "/lanchonete/delete.php";

                fechaTeclado(v);

                Ion.with(v.getContext())
                        .load(URL)
                        .setBodyParameter("id_lanchonete", id_lanchonete)
                        .asJsonObject()
                        .setCallback(new FutureCallback<JsonObject>() {
                            @Override
                            public void onCompleted(Exception e, JsonObject result) {

                                String RETORNO = result.get("EXCLUSAO").getAsString();

                                try {
                                    if (RETORNO.equals("SUCESSO")) {
                                        Toast.makeText(v.getContext(), "Exclusão realizada com sucesso!", Toast.LENGTH_LONG).show();
                                    } else {
                                        Toast.makeText(v.getContext(), "ERRO DESCONHECIDO", Toast.LENGTH_LONG).show();
                                    }

                                } catch (Exception erro) {
                                    Toast.makeText(v.getContext(), "Ops! Falha na conexão, " + erro, Toast.LENGTH_LONG).show();
                                }

                                // Desativa o ProgressBar
                                progressBar.setVisibility(View.GONE);

                            }
                        });


                LanchonetesFragment lanchonetesFragment = new LanchonetesFragment();
                getFragmentManager().beginTransaction()
                        .replace(R.id.frameContainer, lanchonetesFragment, "findThisFragment")
                        .addToBackStack(null)
                        .commit();

            }
        });

        return view;
    }

    private void fechaTeclado(View v) {
        View view = v.findFocus();
        if (view != null) {
            //InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            //imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    private String verificarPagamento() {
        String formaPagamento = "0";

        if(editCartaoCredito.isChecked() && editCartaoDebito.isChecked() && editDinheiro.isChecked()) {
            formaPagamento = "1";
        } else if(editDinheiro.isChecked() && editCartaoCredito.isChecked()) {
            formaPagamento = "2";
        } else if(editDinheiro.isChecked() && editCartaoDebito.isChecked()) {
            formaPagamento = "3";
        } else if(editDinheiro.isChecked()) {
            formaPagamento = "4";
        } else if(editCartaoCredito.isChecked() && editCartaoDebito.isChecked()) {
            formaPagamento = "5";
        } else if(editCartaoCredito.isChecked()) {
            formaPagamento = "6";
        } else if(editCartaoDebito.isChecked()) {
            formaPagamento = "7";
        }

        return formaPagamento;
    }

}
