package com.una.menu.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.una.menu.R;
import com.una.menu.activity.LoginActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class CadastroProdutoFragment extends Fragment {

    // Variaveis que vão receber os objetos da tela.
    EditText editNomeCad, editDescricaoCad, editPrecoCad;
    EditText editLanchoneteCad;
    Spinner editCategoriaCad;
    Button btnCadastrar;
    ProgressBar progressBar;
    String categoria = "";

    //
    private String[] categorias = {"Salgado", "Bebida"};

    // Variaveis para conexão com web service.
    String HOST = "https://menu-app.000webhostapp.com/webservice";
    // String HOST = "http://localhost/webservice";

    public CadastroProdutoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_produto_cadastro, container, false);

        // Recebe os ID's dos objetos da tela;
        editNomeCad         = view.findViewById(R.id.editNomeCad);
        editDescricaoCad     = view.findViewById(R.id.editDescricaoCad);
        editPrecoCad      = view.findViewById(R.id.editPrecoCad);
        editCategoriaCad     = view.findViewById(R.id.editCategoriaCad);
        editLanchoneteCad          = view.findViewById(R.id.editLanchoneteCad);

        btnCadastrar    = view.findViewById(R.id.btnCadastrarProduto);
        progressBar     = view.findViewById(R.id.progressBar);


        ArrayAdapter<String> adapterCategoria = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_spinner_item, categorias);
        Spinner spinner = (Spinner) view.findViewById(R.id.editCategoriaCad);
        adapterCategoria.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapterCategoria);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                // Toast.makeText(this, "Selection: "+ categorias[pos], Toast.LENGTH_SHORT).show();
                categoria = String.valueOf(pos + 1);
                System.out.println("Categoria: " + pos);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //Toast.makeText(MainActivity.this, "Selections cleared.", Toast.LENGTH_SHORT).show();
            }
        });

        // Desativa o ProgressBar
        progressBar.setVisibility(View.GONE);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {

                String nome = editNomeCad.getText().toString();
                String descricao = editDescricaoCad.getText().toString();
                String preco = editPrecoCad.getText().toString();
                //String categoria = editCategoriaCad.getText().toString();
                String lanchonete = editLanchoneteCad.getText().toString();

                String URL = HOST + "/produto/create.php";

                if (nome.isEmpty() || descricao.isEmpty() || preco.isEmpty() || categoria.isEmpty()
                        || lanchonete.isEmpty()) {
                    Toast.makeText(v.getContext(), "Nenhum campo pode estar vazio",
                            Toast.LENGTH_LONG).show();
                } else {

                    // Habilita o ProgressBar
                    progressBar.setVisibility(View.VISIBLE);

                    // DESABILITA o cursor
                    editNomeCad.setCursorVisible(false);
                    editDescricaoCad.setCursorVisible(false);
                    editPrecoCad.setCursorVisible(false);
                    //editCategoriaCad.setCursorVisible(false);
                    editLanchoneteCad.setCursorVisible(false);

                    fechaTeclado(v);

                    Ion.with(v.getContext())
                            .load(URL)
                            .setBodyParameter("nome", nome)
                            .setBodyParameter("descricao", descricao)
                            .setBodyParameter("preco", preco)
                            .setBodyParameter("categoria", categoria)
                            .setBodyParameter("id_lanchonete", lanchonete)
                            .setBodyParameter("id_categoria", categoria)
                            .setBodyParameter("url_imagem", "")
                            .asJsonObject()
                            .setCallback(new FutureCallback<JsonObject>() {
                                @Override
                                public void onCompleted(Exception e, JsonObject result) {

                                    String RETORNO = result.get("CADASTRO").getAsString();

                                    try {
                                        if(RETORNO.equals("EMAIL_ERRO")){
                                            Toast.makeText(v.getContext(), "Este produto já está cadastrado!", Toast.LENGTH_LONG).show();
                                        } else if (RETORNO.equals("SUCESSO")){
                                            Toast.makeText(v.getContext(), "Cadastro realizado com sucesso!", Toast.LENGTH_LONG).show();
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
                                    editDescricaoCad.setCursorVisible(true);
                                    editPrecoCad.setCursorVisible(true);
                                    //editCategoriaCad.setCursorVisible(true);
                                    editLanchoneteCad.setCursorVisible(true);
                                }
                            });

                }

                ProdutosFragment produtosFragment= new ProdutosFragment();
                getFragmentManager().beginTransaction()
                        .replace(R.id.frameContainer, produtosFragment,"findThisFragment")
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

}
