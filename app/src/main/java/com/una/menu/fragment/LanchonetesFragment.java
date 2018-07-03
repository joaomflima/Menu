package com.una.menu.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.una.menu.R;
import com.una.menu.adapter.LanchoneteAdapter;
import com.una.menu.model.Lanchonete;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class LanchonetesFragment extends Fragment {

    // private TextView textView_nome;
    private RecyclerView recyclerLanchonetes;
    private List<Lanchonete> listaLanchonete = new ArrayList<>();
    private LanchoneteAdapter lanchoneteAdapter;
    private String HOST = "https://menu-app.000webhostapp.com/api";
    // private String HOST = "http://localhost/webservice";
    private ProgressBar progressBar;

    private TextView textLanchonete;
    private Button btnCadastrar;

    public LanchonetesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_lanchonetes, container, false);

        textLanchonete = view.findViewById(R.id.textLanchonete);
        btnCadastrar = view.findViewById(R.id.btnCadastrar);

        //Configurar adapter
        lanchoneteAdapter = new LanchoneteAdapter(getContext(), listaLanchonete);

        // textView_nome = findViewById(R.id.textView_nome);
        recyclerLanchonetes = view.findViewById(R.id.recyclerLanchonetes);
        // btnCadastrarLanchonete = findViewById(R.id.btnEditarLanchonete);
        progressBar = view.findViewById(R.id.progressBar);

        // String nomeUsuario = getIntent().getExtras().getString("nome_usuario");
        // textView_nome.setText(nomeUsuario);

        // Lista de lanconetes
        this.lerLanchonetes();

        // Configurar RecycleView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        // final LinearLayoutManager layoutManager = new LinearLayoutManager(LanchonetesActivity.this);
        recyclerLanchonetes.setLayoutManager(layoutManager);
        recyclerLanchonetes.setHasFixedSize(true);
        // recyclerLanchonetes.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
        recyclerLanchonetes.setAdapter(lanchoneteAdapter);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CadastroLanchoneteFragment cadastroLanchoneteFragment= new CadastroLanchoneteFragment();
                getFragmentManager().beginTransaction()
                        .replace(R.id.frameContainer, cadastroLanchoneteFragment,"findThisFragment")
                        .addToBackStack(null)
                        .commit();

            }
        });

        return view;
    }

    private void lerLanchonetes() {

        fechaTeclado();

        progressBar.setVisibility(View.VISIBLE);

        String url = HOST + "/lanchonete/read.php";

        Ion.with(this.getContext())
                .load(url)
                .asJsonArray()
                .setCallback(new FutureCallback<JsonArray>() {
                    @Override
                    public void onCompleted(Exception e, JsonArray result) {


                        try {

                            for(int i = 0; i < result.size(); i++) {

                                JsonObject obj = result.get(i).getAsJsonObject();

                                //String nome = obj.get("nome").getAsString();
                                //Lanchonete l = new Lanchonete(nome);

                                Lanchonete l = new Lanchonete();
                                l.setId_lanchonete(obj.get("id_lanchonete").getAsString());
                                l.setNome(obj.get("nome").getAsString());
                                l.setTelefone(obj.get("telefone").getAsString());
                                l.setCelular(obj.get("celular").getAsString());
                                l.setCep(obj.get("cep").getAsString());
                                l.setEndereco(obj.get("endereco").getAsString());
                                l.setCidade(obj.get("cidade").getAsString());
                                l.setEstado(obj.get("estado").getAsString());
                                l.setId_pagamento(obj.get("id_pagamento").getAsString());
                                l.setId_cliente(obj.get("id_cliente").getAsString());

                                listaLanchonete.add(l);
                            }
                            lanchoneteAdapter.notifyDataSetChanged();

                        } catch (Exception erro) {
                            // Toast.makeText(LanchonetesFragment.this, "Ops! Erro, " + erro, Toast.LENGTH_LONG).show();
                        }

                        progressBar.setVisibility(View.GONE);
                    }
                });

    }

    private void fechaTeclado() {
        View view = this.getView();
        if (view != null) {
            // InputMethodManager imm = (InputMethodManager)view.getSystemService(Context.INPUT_METHOD_SERVICE);
            // imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

}
