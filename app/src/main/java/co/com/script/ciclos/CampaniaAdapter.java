package co.com.script.ciclos;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import co.com.script.ciclos.models.Campania;

/**
 * Created by Cristóbal Romero
 * <https://github.com/Cromeror>
 *
 * @version 1.0
 */

public class CampaniaAdapter extends RecyclerView.Adapter<CampaniaAdapter.CampaniaViewHolder> {

    private List<Campania> campanias;

    public CampaniaAdapter(List<Campania> campanias) {
        this.campanias = campanias;
    }

    @Override
    public CampaniaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.detail_card, parent, false);
        return new CampaniaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(CampaniaViewHolder holder, int position) {
        Campania contacto = campanias.get(position);
        holder.nombre.setText(contacto.getNombre());
        holder.descripcion.setText(contacto.getDescripcion());
    }

    @Override
    public int getItemCount() {
        return campanias.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    static class CampaniaViewHolder extends RecyclerView.ViewHolder {
        TextView nombre;
        TextView descripcion;


        CampaniaViewHolder(View itemView) {
            super(itemView);
            nombre = (TextView) itemView.findViewById(R.id.title);
            descripcion = (TextView) itemView.findViewById(R.id.subtitle);
        }
    }

}
