package ugr.sc.leadmeingranadabus;


import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

/**
 * Created by MGautier on 26/05/15.
 */

public class About extends ActionBarActivity {

    /**
     * Método onCreate (modificador)
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        inicializaUI();
    }

    /**
     * Método inicializaUI (modificador)
     * Inicializa todos los componentes de la vista
     */

    protected void inicializaUI()
    {

        TextView autor = (TextView) findViewById(R.id.autor);
        TextView nombre_app = (TextView) findViewById(R.id.nombre_app);
        TextView nombre_curso = (TextView) findViewById(R.id.curso);
        TextView fecha = (TextView) findViewById(R.id.fecha);
        TextView version = (TextView) findViewById(R.id.version);


        autor.setText("Autor: Moisés Gautier Gómez");
        nombre_app.setText("Nombre app: Lead Me In Granada - Bus");
        nombre_curso.setText("Curso: Desarrollo de Aplicaciones Móviles Android 6ª Edición");
        fecha.setText("Fecha de creación/modificación: 27/05/2015");
        version.setText("Versión de la aplicación: 1.0");

    }

    /**
     * Método onCreateOptionsMenu (consultor)
     * Obtiene los items de menú de la vista para poder manipurlos posteriormente
     * @param menu listado de items de menú de la vista
     * @return true por defecto
     */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_about, menu);
        return true;
    }

    /**
     * Método onOptionsItemSelected (modificador)
     * @param item listado de items del menú de la vista
     * @return true por defecto, sino devuelve el control sobre la opción seleccionada
     */

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
