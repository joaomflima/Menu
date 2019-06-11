package com.una.menu.fragment;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
import com.una.menu.util.MaskEditUtil;


/**
 * A simple {@link Fragment} subclass.
 */
public class CadastroLanchoneteFragment extends Fragment {

    // Variaveis que vão receber os objetos da tela.
    EditText editNomeCad, editTelefoneCad, editCelularCad;
    EditText editEnderecoCad, editCepCad, editCidadeCad, editEstadoCad;
    CheckBox editCartaoCredito, editCartaoDebito, editDinheiro;
    Button btnCadastrar;
    ProgressBar progressBar;

    // Variaveis para conexão com web service.
    final String HOST = "https://menu-app.000webhostapp.com/webservice";
    // String HOST = "http://localhost/webservice";

    public CadastroLanchoneteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_lanchonete_cadastro, container, false);
        Activity currentActivity = getActivity();
        if(currentActivity != null)
            currentActivity.setTitle("Cadastrar Lanchonete");
        // Recebe os ID's dos objetos da tela;
        editNomeCad         = view.findViewById(R.id.editNomeCad);
        editTelefoneCad     = view.findViewById(R.id.editDescricaoCad);
        editTelefoneCad.addTextChangedListener(MaskEditUtil.mask(editTelefoneCad, MaskEditUtil.FORMAT_FONE));
        editCelularCad      = view.findViewById(R.id.editCelularCad);
        editCelularCad.addTextChangedListener(MaskEditUtil.mask(editCelularCad, MaskEditUtil.FORMAT_FONE));
        editEnderecoCad     = view.findViewById(R.id.editLanchoneteCad);
        editCepCad          = view.findViewById(R.id.editCepCad);
        editCepCad.addTextChangedListener(MaskEditUtil.mask(editCepCad, MaskEditUtil.FORMAT_CEP));
        editCidadeCad       = view.findViewById(R.id.editCidadeCad);
        editEstadoCad       = view.findViewById(R.id.editEstadoCad);
        editCartaoCredito   = view.findViewById(R.id.cbCartaoCredito);
        editCartaoDebito    = view.findViewById(R.id.cbCartaoDebito);
        editDinheiro        = view.findViewById(R.id.cbDinheiro);

        btnCadastrar    = view.findViewById(R.id.btnCadastrarLanchonete);
        progressBar     = view.findViewById(R.id.progressBar);

        // Desativa o ProgressBar
        progressBar.setVisibility(View.GONE);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
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

                String URL = HOST + "/lanchonete/create.php";

                if (nome.isEmpty() || telefone.isEmpty() || celular.isEmpty() || endereco.isEmpty()
                        || cep.isEmpty() || cidade.isEmpty() || estado.isEmpty() || id_pagamento.equals("0")) {
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

                    //fechaTeclado(v);

                    Ion.with(v.getContext())
                            .load(URL)
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

                                    String RETORNO = result.get("CADASTRO").getAsString();

                                    try {
                                        switch (RETORNO) {
                                            case "EMAIL_ERRO":
                                                Toast.makeText(v.getContext(), "Esta lanchonete já está cadastrada!", Toast.LENGTH_LONG).show();
                                                break;
                                            case "SUCESSO":
                                                Toast.makeText(v.getContext(), "Cadastro realizado com sucesso!", Toast.LENGTH_LONG).show();
                                                break;
                                            default:
                                                Toast.makeText(v.getContext(), "ERRO DESCONHECIDO", Toast.LENGTH_LONG).show();
                                                break;
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
                if (getFragmentManager() != null) {
                    getFragmentManager().beginTransaction()
                            .replace(R.id.frameContainer, lanchonetesFragment,"findThisFragment")
                            .addToBackStack(null)
                            .commit();
                }
            }
        });

        return view;
    }

    //private void fechaTeclado(View v) {
        //View view = v.findFocus();
        //if (view != null) {
            //InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            //imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        //}
    //}

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
