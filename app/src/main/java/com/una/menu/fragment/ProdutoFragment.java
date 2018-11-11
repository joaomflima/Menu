package com.una.menu.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.squareup.picasso.Picasso;
import com.una.menu.R;
import com.una.menu.adapter.AvaliacaoAdapter;
import com.una.menu.adapter.ProdutoAdapter;
import com.una.menu.model.Avaliacao;
import com.una.menu.model.Lanchonete;
import com.una.menu.model.Produto;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProdutoFragment extends Fragment {

//    TextView nome;
    private TextView titulo;
    private TextView descricao;
    private TextView preco;
//    private TextView avaliacao;
    private ImageView imageView;
    private TextView nome_lanchonete;
    private TextView endereco;

    private Context context;
    private RecyclerView recyclerAvaliacao;
    private List<Avaliacao> listaAvaliacao = new ArrayList<>();
    private ProgressBar iconeLoad;
    private String HOST = "https://menu-app.000webhostapp.com/webservice";

    // Configurar Adapter
    AvaliacaoAdapter adapter = new AvaliacaoAdapter(listaAvaliacao);

    public ProdutoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_produto_view, container, false);
        Lanchonete lanchonete = new Lanchonete();

        titulo = view.findViewById(R.id.textTitProd);
        descricao = view.findViewById(R.id.textDescProd);
        preco = view.findViewById(R.id.textPrecoProd);
        imageView = view.findViewById(R.id.imageProd);
//        avaliacao = view.findViewById(R.id.textAvaProd);
        nome_lanchonete = view.findViewById(R.id.textLanchonete);
        recyclerAvaliacao = view.findViewById(R.id.recyclerViewAva);
        endereco = view.findViewById(R.id.textEndLanchonte);


        Bundle bundle = new Bundle();
        bundle = getArguments();

        //titulo.setText(bundle.getString("id_produto"));
        titulo.setText(bundle.getString("nome"));
        descricao.setText(bundle.getString("descricao"));
        preco.setText(bundle.getString("preco"));
//        avaliacao.setText(bundle.getString("avaliacao"));
        nome_lanchonete.setText(bundle.getString("nome_lanchonete"));
        endereco.setText(bundle.getString("end_lanchonete"));

        //Carregar imagem por URL
        Picasso.get()
                .load(bundle.getString("imagem"))
                .resize(100, 80)
                .into(imageView);
        System.out.println(bundle);


        // Configurar RecyclerView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerAvaliacao.setLayoutManager(layoutManager);
        recyclerAvaliacao.setHasFixedSize(true);
        recyclerAvaliacao.addItemDecoration(new DividerItemDecoration(context, LinearLayout.VERTICAL));
        recyclerAvaliacao.setAdapter(adapter);

        String id_produto;
        id_produto = bundle.getString("id_produto");


        pesquisaAvaliacao(id_produto);



        return  view;
    }

    //Declara um atributo para guardar o context.
    @Override
    public void onAttach(Context context) {
        this.context = context;
        super.onAttach(context);
    }

    //CLASSE ION RECUPERAR DADOS
    private void pesquisaAvaliacao(String id_produto) {

//        fechaTeclado();
//            iconeLoad.setVisibility(View.VISIBLE);

        String url = HOST + "/readprodutos/readAvaliacoes.php";
//        listaProduto.clear();

        if (id_produto.length() > 0) {

            Ion.with(context)
                    .load(url)
                    .setBodyParameter("id_produto", id_produto)
                    .asJsonArray()
                    .setCallback(new FutureCallback<JsonArray>() {
                        @Override
                        public void onCompleted(Exception e, JsonArray result) {

                            try {

                                listaAvaliacao.clear();
                                for (int i = 0; i < result.size(); i++) {


                                    JsonObject obj = result.get(i).getAsJsonObject();

                                    //String nome = obj.get("nome").getAsString();
                                    //Produto p = new Produto(nome);

                                    Avaliacao a = new Avaliacao();
                                    a.setCliente(obj.get("cliente_avaliacao").getAsString());
                                    a.setComentario(obj.get("comentario").getAsString());
                                    a.setNota(obj.get("nota").getAsString());



                                    listaAvaliacao.add(a);

//                                    System.out.println(p.getNome());
                                }

                                    /*listaAvaliacao.sort(new Comparator<Produto>() {
                                        @Override
                                        public int compare(Produto o1, Produto o2) {
                                            return o1.getPreco().compareTo(o2.getPreco());

                                        }
                                    });*/

                                adapter.notifyDataSetChanged();


                            } catch (Exception erro) {

                                Toast.makeText(context, "Ops! Erro, " + erro, Toast.LENGTH_LONG).show();
                            }

//                            iconeLoad.setVisibility(View.GONE);


                        }
                    });
        }
    }

}
