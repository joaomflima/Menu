package com.una.menu.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.una.menu.R;
import com.una.menu.model.Produto;

import java.util.List;

public class ProdutoAdapter extends RecyclerView.Adapter<ProdutoAdapter.MyViewHolder> {

    // Atributos
    private List<Produto> listaProdutos;

    // Construtor
    public ProdutoAdapter(List<Produto> lista ) {
        this.listaProdutos = lista;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemLista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.produtos_lista, parent, false);

        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        /* Versão Fernando
        Produto produto = listaProdutos.get(position);
        holder.nome.setText(produto.getNome());
        holder.descricao.setText(produto.getDescricao());
        holder.preco.setText(produto.getPreco());*/

        // Versão Leo
        Produto produto = listaProdutos.get(position);
        holder.nome.setText(produto.getNome());
        holder.descricao.setText(produto.getDescricao());
        holder.preco.setText(produto.getPreco());
        holder.nomeLanchonete.setText(produto.getNomeLanchonete());


//Carregar imagem por URL
        Picasso.get()
                .load(produto.getImagem())
                .resize(100, 80)
                .into(holder.imageView);



        System.out.println(produto.getNome());
//        holder.nome.setText("TesteNome"); // Apagar
//        holder.descricao.setText("TesteDescricao"); // Apagar
//        holder.preco.setText("2.50"); // Apagar
    }

    @Override
    public int getItemCount() {
        return listaProdutos.size();
//        return 5; // Apagar
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView nome;
        TextView descricao;
        TextView preco;
        //TextView avaliacao;
        ImageView imageView;
        TextView nomeLanchonete;


        public MyViewHolder(View itemView) {
            super(itemView);

           /* Versão Fernando
            nome = itemView.findViewById(R.id.textTitulo);
            descricao = itemView.findViewById(R.id.textGenero);
            preco = itemView.findViewById(R.id.textAno);*/

            // Versão Leo
            nome = itemView.findViewById(R.id.textTitulo);
            descricao = itemView.findViewById(R.id.textDescricao);
            preco = itemView.findViewById(R.id.textPreco);
            //avaliacao = itemView.findViewById(R.id.textAvaliacao);
            imageView = itemView.findViewById(R.id.imageProduto);
            nomeLanchonete = itemView.findViewById(R.id.textAvaliacao);
        }
    }

}
