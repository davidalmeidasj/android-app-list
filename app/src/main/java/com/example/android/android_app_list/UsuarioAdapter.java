package com.example.android.android_app_list;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class UsuarioAdapter extends ArrayAdapter<UsuarioModel> implements View.OnClickListener{

    private ArrayList<UsuarioModel> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView txtName;
        TextView txtType;
        TextView txtVersion;
        TextView info;
    }

    public UsuarioAdapter(ArrayList<UsuarioModel> data, Context context) {
        super(context, R.layout.row_item, data);
        this.dataSet = data;
        this.mContext=context;

    }

    @Override
    public void onClick(View v) {

    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        UsuarioModel usuarioModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.row_item, parent, false);
            viewHolder.txtName = (TextView) convertView.findViewById(R.id.nome);
            viewHolder.txtType = (TextView) convertView.findViewById(R.id.tipo);
            viewHolder.txtVersion = (TextView) convertView.findViewById(R.id.numeroDaVersao);
            viewHolder.info = (TextView) convertView.findViewById(R.id.item_info);

            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        result.startAnimation(animation);
        lastPosition = position;

        viewHolder.txtName.setText(usuarioModel.getNome());
        viewHolder.txtType.setText(usuarioModel.getTipo());
        viewHolder.txtVersion.setText(usuarioModel.getNumeroDaVersao());
        viewHolder.info.setText(usuarioModel.getLancamento());
        // Return the completed view to render on screen
        return convertView;
    }
}
