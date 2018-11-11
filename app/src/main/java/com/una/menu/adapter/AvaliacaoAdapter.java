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
import com.una.menu.model.Avaliacao;
import com.una.menu.model.Produto;

import java.util.List;

public class AvaliacaoAdapter extends RecyclerView.Adapter<AvaliacaoAdapter.MyViewHolder> {

    // Atributos
//    private List<Produto> listaProdutos;
//    private Context context;

    private List<Avaliacao> listaAvaliacao;

//    // Construtor
//    public AvaliacaoAdapter(List<Produto> lista )
//    {
////        this.context = context;
//        this.listaProdutos = lista;
//    }

    public AvaliacaoAdapter(List<Avaliacao> lista) {
        this.listaAvaliacao = lista;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemLista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.lista_avaliacoes, parent, false);

        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        //Uri url = taskSnapshot.getDownloadUrl();
        /*Produto produto = listaProdutos.get(position);
        holder.nome.setText(produto.getNome());
        holder.descricao.setText(produto.getDescricao());
        holder.preco.setText(produto.getPreco());
        holder.avaliacao.setText(produto.getAvaliacao());
        holder.nomeLanchonete.setText(produto.getNomeLanchonete());*/

        Avaliacao avaliacao = listaAvaliacao.get(position);
        holder.usuario_avaliacao.setText(avaliacao.getCliente());
        holder.comentario.setText(avaliacao.getComentario());
        holder.nota.setText(avaliacao.getNota());

    }

    @Override
    public int getItemCount() {
        return listaAvaliacao.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {


        TextView nota;
        TextView comentario;
        TextView usuario_avaliacao;


        public MyViewHolder(View itemView) {
            super(itemView);

            /*nome = itemView.findViewById(R.id.textTitulo);
            descricao = itemView.findViewById(R.id.textDescricao);
            preco = itemView.findViewById(R.id.textPreco);
            avaliacao = itemView.findViewById(R.id.textAvaliacao);
            imageView = itemView.findViewById(R.id.imageProduto);
            nomeLanchonete = itemView.findViewById(R.id.textLanchonete);*/

            nota = itemView.findViewById(R.id.textNotaAva);
            comentario = itemView.findViewById(R.id.textComentarioAva);
            usuario_avaliacao = itemView.findViewById(R.id.textUsuarioAva);
        }
    }

}
