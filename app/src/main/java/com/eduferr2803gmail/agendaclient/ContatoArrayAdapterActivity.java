package com.eduferr2803gmail.agendaclient;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.eduferr2803gmail.agendaclient.dominio.entidades.Contato;

/**
 * Created by Eduferr on 21/05/2017.
 */

public class ContatoArrayAdapterActivity extends ArrayAdapter<Contato> {

    private int resource = 0;
    private LayoutInflater inflater;
    private Context context;

    public ContatoArrayAdapterActivity(Context context, int resource) {
        super(context, resource);
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.resource = resource;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = null;
        ViewHolder viewHolder = null;

        if (convertView == null) {
            viewHolder = new ViewHolder();

            view = inflater.inflate(resource, parent, false);

            viewHolder.txtCor = (TextView) view.findViewById(R.id.txtCor);
            viewHolder.txtNome = (TextView) view.findViewById(R.id.txtNome);
            viewHolder.txtTelefone = (TextView) view.findViewById(R.id.txtTelefone);

            view.setTag(viewHolder);

            convertView = view;

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            view = convertView;
        }

        Contato contato = getItem(position);

        if (contato.getNome().toUpperCase().startsWith("A"))
            viewHolder.txtCor.setBackgroundColor(context.getResources().getColor(R.color.cor1));
        else if (contato.getNome().toUpperCase().startsWith("B"))
            viewHolder.txtCor.setBackgroundColor(context.getResources().getColor(R.color.cor2));
        else if (contato.getNome().toUpperCase().startsWith("C"))
            viewHolder.txtCor.setBackgroundColor(context.getResources().getColor(R.color.cor3));
        else if (contato.getNome().toUpperCase().startsWith("D"))
            viewHolder.txtCor.setBackgroundColor(context.getResources().getColor(R.color.cor4));
        else if (contato.getNome().toUpperCase().startsWith("E"))
            viewHolder.txtCor.setBackgroundColor(context.getResources().getColor(R.color.cor5));
        else if (contato.getNome().toUpperCase().startsWith("F"))
            viewHolder.txtCor.setBackgroundColor(context.getResources().getColor(R.color.cor6));
        else if (contato.getNome().toUpperCase().startsWith("G"))
            viewHolder.txtCor.setBackgroundColor(context.getResources().getColor(R.color.cor7));
        else if (contato.getNome().toUpperCase().startsWith("H"))
            viewHolder.txtCor.setBackgroundColor(context.getResources().getColor(R.color.cor8));
        else if (contato.getNome().toUpperCase().startsWith("I"))
            viewHolder.txtCor.setBackgroundColor(context.getResources().getColor(R.color.cor9));
        else if (contato.getNome().toUpperCase().startsWith("J"))
            viewHolder.txtCor.setBackgroundColor(context.getResources().getColor(R.color.cor10));
        else if (contato.getNome().toUpperCase().startsWith("K"))
            viewHolder.txtCor.setBackgroundColor(context.getResources().getColor(R.color.cor11));
        else if (contato.getNome().toUpperCase().startsWith("L"))
            viewHolder.txtCor.setBackgroundColor(context.getResources().getColor(R.color.cor12));
        else if (contato.getNome().toUpperCase().startsWith("M"))
            viewHolder.txtCor.setBackgroundColor(context.getResources().getColor(R.color.cor13));
        else if (contato.getNome().toUpperCase().startsWith("N"))
            viewHolder.txtCor.setBackgroundColor(context.getResources().getColor(R.color.cor14));
        else if (contato.getNome().toUpperCase().startsWith("O"))
            viewHolder.txtCor.setBackgroundColor(context.getResources().getColor(R.color.cor15));
        else if (contato.getNome().toUpperCase().startsWith("P"))
            viewHolder.txtCor.setBackgroundColor(context.getResources().getColor(R.color.cor16));
        else if (contato.getNome().toUpperCase().startsWith("Q"))
            viewHolder.txtCor.setBackgroundColor(context.getResources().getColor(R.color.cor17));
        else if (contato.getNome().toUpperCase().startsWith("R"))
            viewHolder.txtCor.setBackgroundColor(context.getResources().getColor(R.color.cor18));
        else if (contato.getNome().toUpperCase().startsWith("S"))
            viewHolder.txtCor.setBackgroundColor(context.getResources().getColor(R.color.cor19));
        else if (contato.getNome().toUpperCase().startsWith("T"))
            viewHolder.txtCor.setBackgroundColor(context.getResources().getColor(R.color.cor20));
        else if (contato.getNome().toUpperCase().startsWith("U"))
            viewHolder.txtCor.setBackgroundColor(context.getResources().getColor(R.color.cor21));
        else if (contato.getNome().toUpperCase().startsWith("V"))
            viewHolder.txtCor.setBackgroundColor(context.getResources().getColor(R.color.cor22));
        else if (contato.getNome().toUpperCase().startsWith("W"))
            viewHolder.txtCor.setBackgroundColor(context.getResources().getColor(R.color.cor23));
        else if (contato.getNome().toUpperCase().startsWith("X"))
            viewHolder.txtCor.setBackgroundColor(context.getResources().getColor(R.color.cor24));
        else if (contato.getNome().toUpperCase().startsWith("Y"))
            viewHolder.txtCor.setBackgroundColor(context.getResources().getColor(R.color.cor25));
        else if (contato.getNome().toUpperCase().startsWith("Z"))
            viewHolder.txtCor.setBackgroundColor(context.getResources().getColor(R.color.cor26));

        viewHolder.txtNome.setText(contato.getNome());
        viewHolder.txtTelefone.setText(contato.getTelefone());

        return view;

    }

    static class ViewHolder
    {
        TextView txtCor;
        TextView txtNome;
        TextView txtTelefone;
    }

}
