package ugr.sc.leadmeingranadabus;

import android.app.Activity;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;



import java.util.List;

/**
 * Created by MGautier on 21/05/15.
 */

public class BusesListAdapter<T> extends ArrayAdapter{

    /*
     * Variables internas de la clase
     */

    private final Activity context;
    private List<T> paradas;

    /**
     * Constructor de la clase
     * @param context Contexto de la Actividad desde dónde se invoca
     * @param object Lista de objetos que contienen los elementos a manipular
     */

    public BusesListAdapter(Activity context, List<T> object) {
        super(context, R.layout.listimageview, object);
        this.context = context;
        paradas = object;

    }

    /**
     * Método getView (modificador)
     * @param position Posición del índice dentro del listView
     * @param view Vista de la listView
     * @param parent Vista padre del listView
     * @return Devuelve el resultado de crear los items del listView a la vista padre
     */

    public View getView(int position, View view, ViewGroup parent)
    {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.listimageview, null, true);

        ImageView bus_icon = (ImageView) rowView.findViewById(R.id.busicon);
        TextView nombre_parada = (TextView) rowView.findViewById(R.id.nombre_parada);
        TextView numero_parada = (TextView) rowView.findViewById(R.id.numero_parada);
        TextView sentido_parada = (TextView) rowView.findViewById(R.id.sentido_parada);

        Parada movimiento = (Parada) paradas.get(position);

        if(movimiento != null)
        {
            if(Build.VERSION.SDK_INT < 22)
            {
                bus_icon.setImageDrawable(context.getResources().getDrawable(R.mipmap.bus_dos));
            }
            else
            {
                bus_icon.setImageDrawable(context.getDrawable(R.mipmap.bus_dos));
            }

            nombre_parada.setText(movimiento.getNombre_parada());
            numero_parada.setText(""+movimiento.getNum_parada());
            sentido_parada.setText(" Hacia: "+movimiento.getSentido_parada());


        }

        return rowView;
    }
}
