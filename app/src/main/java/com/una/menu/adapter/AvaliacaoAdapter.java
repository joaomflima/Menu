package com.una.menu.adapter;

import android.content.Context;
import android.net.Uri;
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

public class AvaliacaoAdapter extends RecyclerView.Adapter<AvaliacaoAdapter.MyViewHolder> {

    // Atributos
    private List<Produto> listaProdutos;
//    private Context context;

    // Construtor
    public AvaliacaoAdapter(  List<Produto> lista )
    {
//        this.context = context;
        this.listaProdutos = lista;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemLista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_produto, parent, false);

        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        //Uri url = taskSnapshot.getDownloadUrl();
        Produto produto = listaProdutos.get(position);
        holder.nome.setText(produto.getNome());
        holder.descricao.setText(produto.getDescricao());
        holder.preco.setText(produto.getPreco());
        holder.avaliacao.setText(produto.getAvaliacao());

        //Carregar imagem por URL
        Picasso.get()
                .load(produto.getImagem())
                .resize(160, 70)
                .into(holder.imageView);


        /*Picasso.with(this)
                .load(produto.getImagem())
                .into(holder.imageView);*/
        //holder.imagem.setImageResource(produto.getImagem());
        //holder.imagem.setImageURI(url);

//        if(produto.getNome().contains("Coxinha")){
//            holder.imagem = R.drawable.coxinha;
//        }


    }

    @Override
    public int getItemCount() {
        return listaProdutos.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView nome;
        TextView descricao;
        TextView preco;
        TextView avaliacao;
        ImageView imageView;

        public MyViewHolder(View itemView) {
            super(itemView);

            nome = itemView.findViewById(R.id.textTitulo);
            descricao = itemView.findViewById(R.id.textDescricao);
            preco = itemView.findViewById(R.id.textPreco);
            avaliacao = itemView.findViewById(R.id.textAvaliacao);
            imageView = itemView.findViewById(R.id.imageProduto);
        }
    }

}
