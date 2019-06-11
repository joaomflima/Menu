package com.una.menu.fragment;


import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.SearchView;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.una.menu.R;
import com.una.menu.RecyclerItemClickListener;
import com.una.menu.adapter.ProdutoAdapter;
import com.una.menu.model.Produto;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class BuscaFragment extends Fragment {

    private Context context;

    private final List<Produto> listaProduto = new ArrayList<>();
    private ProgressBar iconeLoad;
    private final String HOST = "https://menu-app.000webhostapp.com/webservice";

    // Configurar Adapter
    final ProdutoAdapter adapter = new ProdutoAdapter(listaProduto);


    public BuscaFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_busca, container, false);

        final Activity currentActivity = getActivity();
        if(currentActivity != null)
            currentActivity.setTitle("Buscar Produtos");
        // -------------------------- INICIO DO CÓDIGO ------------------------

        // Variaveis
        SearchView searchProdutos = view.findViewById(R.id.buscaProdutos);
        RecyclerView recyclerProdutos = view.findViewById(R.id.recyclerViewProdutos);
        iconeLoad = view.findViewById(R.id.progressBar2);

        iconeLoad.setVisibility(View.VISIBLE);

        // Lista Produtos
        this.buscaAutomaticaProdutos();
//        this.setaProdutoManual();

        // Configura SearchView
        searchProdutos.setQueryHint("Pesquisar Produtos");
        searchProdutos.setOnQueryTextListener(new android.support.v7.widget.SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                if(query.length() == 0)
                    buscaAutomaticaProdutos();
                else {
                    String produto = "%" + query + "%";
                    pesquisaProdutos(produto);
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //System.out.println("newText: "+ newText);
                //System.out.println(produto);
                return false;
            }
        });

        // Configurar RecyclerView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerProdutos.setLayoutManager(layoutManager);
        recyclerProdutos.setHasFixedSize(true);
        recyclerProdutos.addItemDecoration(new DividerItemDecoration(context,LinearLayout.VERTICAL));
        recyclerProdutos.setAdapter(adapter);

        //Evento de clique
        recyclerProdutos.addOnItemTouchListener(
                new RecyclerItemClickListener(
                        context,
                        recyclerProdutos,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                Produto produto = listaProduto.get(position);
                                Toast.makeText(context, "Produto selecionado: " + produto.getNome(),
                                        Toast.LENGTH_SHORT).show();

                                //Passagem de parametros para a view ProdutoFragment
                                Bundle bundle = new Bundle();
                                bundle.putString("id_produto", produto.getId_produto());
                                bundle.putString("descricao", produto.getDescricao());
                                bundle.putString("nome", produto.getNome());
                                bundle.putString("avaliacao", produto.getAvaliacao());
                                bundle.putString("imagem", produto.getImagem());
                                bundle.putString("preco", produto.getPreco());
                                bundle.putString("nome_lanchonete",produto.getNomeLanchonete());
                                bundle.putString("end_lanchonete",produto.getEndLanchonete());

                                ProdutoFragment produtoFragment = new ProdutoFragment();
                                produtoFragment.setArguments(bundle);

                                if(getActivity() != null) {
                                    FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                                    fragmentTransaction.replace(R.id.frameContainer, produtoFragment);
                                    fragmentTransaction.commit();
                                }
                            }

                            @Override
                            public void onLongItemClick(View view, int position) {
                                Toast.makeText(context,"Clique longo", Toast.LENGTH_SHORT).show();

                            }

                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            }
                        }
                )
        );

        // -------------------------- FIM DO CÓDIGO ------------------------
        return  view;

    }

    private void buscaAutomaticaProdutos() {

//        fechaTeclado();
        iconeLoad.setVisibility(View.VISIBLE);

        String url = HOST + "/readprodutos/read_2.php";

        listaProduto.clear();

        Ion.with(context)
                .load(url)
                .asJsonArray()
                .setCallback(new FutureCallback<JsonArray>() {
                    @Override
                    public void onCompleted(Exception e, JsonArray result) {


                        try {

                            for(int i = 0; i < result.size(); i++) {

                                JsonObject obj = result.get(i).getAsJsonObject();

                                //String nome = obj.get("nome").getAsString();
                                //Produto p = new Produto(nome);

                                Produto p = new Produto();
                                p.setId_produto(obj.get("id_produto").getAsString());
                                p.setNome(obj.get("nome").getAsString());
                                p.setDescricao(obj.get("descricao").getAsString());
                                p.setPreco(obj.get("preco").getAsString());
                                p.setImagem();
                                p.setNomeLanchonete(obj.get("nome_lanchonete").getAsString());
                                p.setEndLanchonete(obj.get("end_lanchonete").getAsString());

                                listaProduto.add(p);
                            }

                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                                listaProduto.sort(new Comparator<Produto>() {
                                    @Override
                                    public int compare(Produto o1, Produto o2) {
                                        return o1.getPreco().compareTo(o2.getPreco());
    //                                    return -o1.getPreco().compareTo( o2.getPreco());

                                    }
                                });
                            }

                            adapter.notifyDataSetChanged();


                        } catch (Exception erro) {
                            Toast.makeText(context, "Ops! Erro, " + erro, Toast.LENGTH_LONG).show();
                        }

                        iconeLoad.setVisibility(View.GONE);
                    }
                });
    }

    private void pesquisaProdutos(String produto) {

//        fechaTeclado();
        iconeLoad.setVisibility(View.VISIBLE);

        String url = HOST + "/readprodutos/readpesquisa_2.php";
//        listaProduto.clear();

        if (produto.length() > 0) {

            Ion.with(context)
                    .load(url)
                    .setBodyParameter("produto", produto)
                    .asJsonArray()
                    .setCallback(new FutureCallback<JsonArray>() {
                        @Override
                        public void onCompleted(Exception e, JsonArray result) {

                            try {

                                listaProduto.clear();
                                for (int i = 0; i < result.size(); i++) {


                                    JsonObject obj = result.get(i).getAsJsonObject();

                                    //String nome = obj.get("nome").getAsString();
                                    //Produto p = new Produto(nome);

                                    Produto p = new Produto();
                                    p.setId_produto(obj.get("id_produto").getAsString());
                                    p.setNome(obj.get("nome").getAsString());
                                    p.setDescricao(obj.get("descricao").getAsString());
                                    p.setPreco(obj.get("preco").getAsString());
                                    p.setImagem();
                                    p.setNomeLanchonete(obj.get("nome_lanchonete").getAsString());
                                    p.setEndLanchonete(obj.get("end_lanchonete").getAsString());

                                    listaProduto.add(p);

//                                    System.out.println(p.getNome());
                                }

                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                                    listaProduto.sort(new Comparator<Produto>() {
                                        @Override
                                        public int compare(Produto o1, Produto o2) {
                                            return o1.getPreco().compareTo(o2.getPreco());

                                        }
                                    });
                                }

                                adapter.notifyDataSetChanged();


                            } catch (Exception erro) {

                                Toast.makeText(context, "Ops! Erro, " + erro, Toast.LENGTH_LONG).show();
                            }

                            iconeLoad.setVisibility(View.GONE);

                            System.out.println("---------INICIO----------\n");
                            for (int i = 0; i < listaProduto.size(); i++) {
                                System.out.println(listaProduto.get(i).getNome());
                            }
                            System.out.println("---------FIM----------\n");

                        }
                    });
        }
    }

    //Declara um atributo para guardar o context.
    @Override
    public void onAttach(Context context) {
        this.context = context;
        super.onAttach(context);
    }


    /* private void fechaTeclado() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }*/
}
