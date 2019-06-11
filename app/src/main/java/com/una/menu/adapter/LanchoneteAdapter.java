package com.una.menu.adapter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.una.menu.R;
import com.una.menu.fragment.ViewLanchoneteFragment;
import com.una.menu.model.Lanchonete;

import java.util.List;

// import com.una.menu.activity.EdicaoLanchoneteActivity;

public class LanchoneteAdapter extends RecyclerView.Adapter<LanchoneteAdapter.MyViewHolder> {

    private final List<Lanchonete> listaLanchonetes;

    // Construtor
    public LanchoneteAdapter(List<Lanchonete> lista ) {
        // Atributos
        this.listaLanchonetes = lista;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.lanchonetes_lista, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Lanchonete lanchonete = listaLanchonetes.get(position);

        holder.nome.setText(lanchonete.getNome());
        holder.telefone.setText(lanchonete.getTelefone());
        holder.celular.setText(lanchonete.getCelular());
        // holder.cep.setText(lanchonete.getCep());
        holder.endereco.setText(lanchonete.getEndereco());
        holder.cidade.setText(lanchonete.getCidade());
        holder.estado.setText(lanchonete.getEstado());
        // holder.id_pagamento.setText(lanchonete.getId_pagamento());
        // holder.id_cliente.setText(lanchonete.getId_cliente());

    }

    @Override
    public int getItemCount() {
        return listaLanchonetes.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        final TextView nome;
        final TextView telefone;
        final TextView celular;
        // TextView cep;
        final TextView endereco;
        final TextView cidade;
        final TextView estado;
        // holder.id_pagamento.setText(lanchonete.getId_pagamento());
        // holder.id_cliente.setText(lanchonete.getId_cliente());

        public MyViewHolder(final  View itemView) {
            super(itemView);

            nome = itemView.findViewById(R.id.textNome);
            telefone = itemView.findViewById(R.id.textTelefone);
            celular = itemView.findViewById(R.id.textCelular);
            // cep = itemView.findViewById(R.id.textCep);
            endereco = itemView.findViewById(R.id.textEndereco);
            cidade = itemView.findViewById(R.id.textCidade);
            estado = itemView.findViewById(R.id.textEstado);

            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    if (listaLanchonetes.size() > 0) {

                        Lanchonete lanchonete = listaLanchonetes.get(getLayoutPosition());

                        Bundle bundle = new Bundle();
                        bundle.putSerializable("LANCHONETE", lanchonete);

                        AppCompatActivity activity = (AppCompatActivity) v.getContext();
                        ViewLanchoneteFragment viewLanchoneteFragment= new ViewLanchoneteFragment();
                        viewLanchoneteFragment.setArguments(bundle);
                        activity.getSupportFragmentManager().beginTransaction()
                                .replace(R.id.frameContainer, viewLanchoneteFragment)
                                .addToBackStack(null)
                                .commit();


                    }

                }

            });

        }

    }

}
