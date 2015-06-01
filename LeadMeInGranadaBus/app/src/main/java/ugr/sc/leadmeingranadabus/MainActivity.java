package ugr.sc.leadmeingranadabus;


import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Build;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.io.IOException;
import java.lang.Float;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


/**
 * Created by MGautier on 21/05/15.
 */

public class MainActivity extends ActionBarActivity {

    /*
     * Variables internas de la clase
     */

    protected LocationManager locationManager;
    protected String provider;
    protected ListView list; // Listview de paradas cercanas a la posicion actual
    protected BusesListAdapter adapter;
    protected Location location; // Clase para obtener la posicion actual

    private GoogleMap mMap;


    protected List<Parada> sn4 = new ArrayList<>(); // Linea de bus con sus paradas (coordenadas)

    private double latitud_actual, longitud_actual;

    TextView posicion;
    ImageView posicion_icon;
    TextView posicion_descripcion;
    TextView coordenadas;

    /**
     * Método onCreate (modificador)
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initUI();

        initLocationService();

        initMyLocation();

        setLocationUpdates();



        list = (ListView) findViewById(R.id.lista);
        
        sn4.add(new Parada(530, "Sagrada Familia 15 - Iglesia Sta. Micaela", 37.1911681, -3.6248049, "Villarejo"));
        sn4.add(new Parada(531, "Sagrada Familia Rubén Darío", 37.1899412, -3.6254944, "Villarejo"));
        sn4.add(new Parada(358, "Ctra. de Málaga 69", 37.1891579, -3.6235601, "Villarejo"));
        sn4.add(new Parada(356, "Ctra. de Málaga Las Torres", 37.1888379, -3.6198235, "Villarejo"));
        sn4.add(new Parada(354, "Ctra. de Málaga Villarejo 2", 37.1884175, -3.6154771,"Cno de Ronda"));
        sn4.add(new Parada(246, "Cno. de Ronda 193 Estadio de la Juventud", 37.1853691, -3.614931, "Méndez Núñez"));
        sn4.add(new Parada(262, "Cno. de Ronda 157", 37.183294, -3.6138621,"Méndez Núñez"));
        sn4.add(new Parada(260, "Cno. de Ronda 153", 37.1816214, -3.6128799,"Méndez Núñez"));
        sn4.add(new Parada(258, "Cno. de Ronda 125", 37.1785657, -3.6110275,"Méndez Núñez"));
        sn4.add(new Parada(256, "Cno. de Ronda 115", 37.177225, -3.6102397,"Recogidas"));
        sn4.add(new Parada(254, "Cno. de Ronda 97", 37.1739893, -3.6083038,"Recogidas"));
        sn4.add(new Parada(1369, "Cno. de Ronda 83", 37.1722995, -3.6072684,"Recogidas"));
        sn4.add(new Parada(251, "Cno. de Ronda 71", 37.1706042, -3.6062565,"Recogidas"));
        sn4.add(new Parada(522, "Recogidas 35", 37.1715245, -3.6032083,"Acera del Darro"));
        sn4.add(new Parada(524, "Recogidas 3", 37.1730754, -3.6003962,"Acera del Darro"));
        sn4.add(new Parada(74, "Acera del Darro 40 Fuente de las Batallas", 37.1715627, -3.598848,"Río Genil"));
        sn4.add(new Parada(1465, "Tierno Galván Palacio de Congresos", 37.1660541, -3.5981391,"Avda. del Dílar"));
        sn4.add(new Parada(198, "Avda. del Dílar 2", 37.1638357, -3.5979588,"Cocheras Rober"));
        sn4.add(new Parada(197, "Avda. del Dílar 10", 37.1647199, -3.5993587,"Cocheras Rober"));
        sn4.add(new Parada(192, "Avda. del Dílar 14", 37.1598592, -3.5998739,"Cocheras Rober"));
        sn4.add(new Parada(194, "Avda. del Dílar 26", 37.1589323, -3.5999676,"Cocheras Rober"));
        sn4.add(new Parada(191, "Avda. del Dílar 56", 37.1559136, -3.6005017,"Cocheras Rober"));
        sn4.add(new Parada(188, "Avda. del Dílar 94", 37.1536481, -3.6007616,"Cocheras Rober"));
        sn4.add(new Parada(184, "Avda. del Dílar 134", 37.1514956, -3.6012667,"Cocheras Rober"));
        sn4.add(new Parada(186, "Ctra. de Dílar-Rotonda Avda. Ilustración", 37.149405, -3.6020944,"Cocheras Rober"));
        sn4.add(new Parada(352, "Avda. del Dilar-Cocheras Transportes Rober", 37.1509355, -3.6013033,"Cocheras Rober"));

        // Ahora en sentido opuesto

        sn4.add(new Parada(626, "Avda. del Dílar 121", 37.1516095, -3.6011561,"Río Genil"));
        sn4.add(new Parada(185, "Avda. del Dílar 109", 37.1525564, -3.6008784,"Río Genil"));
        sn4.add(new Parada(187, "Avda. del Dílar 89", 37.1563868, -3.6003698,"Río Genil"));
        sn4.add(new Parada(190, "Avda. del Dílar 71", 37.1589158, -3.5998374,"Río Genil"));
        sn4.add(new Parada(589, "Avda. del Dílar 33", 37.1619486, -3.5991531,"Río Genil"));
        sn4.add(new Parada(196, "Avda. del Dílar 9", 37.1637287, -3.5969591,"Río Genil"));
        sn4.add(new Parada(467, "Pablo Picasso 10", 37.1646909, -3.5950188,"Río Genil"));
        sn4.add(new Parada(466, "Pablo Picasso 33", 37.1664451, -3.595035,"Río Genil/Palacio Congresos"));
        sn4.add(new Parada(451, "Poeta Manuel de Góngora 9", 37.1675266, -3.5959576,"Río Genil/Palacio Congresos"));
        sn4.add(new Parada(582, "Acera del Darro 9 Centro Comercial", 37.1712209, -3.5984349,"Recogidas"));
        sn4.add(new Parada(523, "Recogidas 2", 37.1728707, -3.6009097,"Cno de Ronda"));
        sn4.add(new Parada(525, "Recogidas 38", 37.171582, -3.6032722,"Cno de Ronda"));
        sn4.add(new Parada(252, "Cno. de Ronda 82", 37.170528, -3.6059507,"Méndez Núñez"));
        sn4.add(new Parada(253, "Cno. de Ronda 100", 37.1729602, -3.6074259,"Méndez Núñez"));
        sn4.add(new Parada(1384, "Cno. de Ronda 114", 37.1742394, -3.6081856,"Méndez Núñez"));
        sn4.add(new Parada(255, "Cno. de Ronda 130", 37.1760126, -3.6092488,"Méndez Núñez"));
        sn4.add(new Parada(257, "Cno. de Ronda 148 Méndez Núñez", 37.178461, -3.6108168, "Estadio Juventud"));
        sn4.add(new Parada(259, "Cno. de Ronda 172", 37.18073, -3.6121601,"Estadio Juventud"));
        sn4.add(new Parada(261, "Cno. de Ronda 184", 37.1824035, -3.6132144,"Estadio Juventud"));
        sn4.add(new Parada(244, "Cno. de Ronda 194 Estadio de la Juventud", 37.1843564, -3.6143141,"Villarejo"));
        sn4.add(new Parada(245, "Cno. de Ronda 210", 37.1872768, -3.614642,"Villarejo"));
        sn4.add(new Parada(353, "Ctra. de Málaga Villarejo 1", 37.1884173, -3.6152149,"Chana"));
        sn4.add(new Parada(355, "Ctra. de Málaga 54", 37.1888828, -3.6200702,"Chana"));
        sn4.add(new Parada(281, "Circunvalación Encina 4", 37.1893315, -3.6212199,"Chana"));
        sn4.add(new Parada(163, "Avda. de Andalucía Sindicatos", 37.1925353, -3.6217478,"Parque Almunia"));
        sn4.add(new Parada(164, "Avda. de Andalucía Parque Almunia", 37.1932534, -3.6238292,"Diputación"));
        sn4.add(new Parada(166, "Facultad Bellas Artes 1", 37.1946892, -3.6270264,"Diputación"));
        sn4.add(new Parada(168, "Avda. de Andalucía Diputación 1", 37.1956037, -3.6290112,"Circunvalación"));
        sn4.add(new Parada(169, "Avda. de Andalucía 121", 37.1954548, -3.6295664,"Chana"));
        sn4.add(new Parada(167, "Avda. de Andalucía 107", 37.1944045, -3.6269444,"Chana"));
        sn4.add(new Parada(165, "Avda. de Andalucía 91", 37.1931857, -3.624441,"Chana/Sagrada Familia"));

        latitud_actual = location.getLatitude();
        longitud_actual = location.getLongitude();


        int i = 0;


        Location punto_referencia = new Location("punto_referencia");

        punto_referencia.setLatitude(latitud_actual);
        punto_referencia.setLongitude(longitud_actual);


        float[] dos_paradas = new float[2];
        int prox_1 = 0,prox_2 = 0;

        dos_paradas[0] = Float.MAX_VALUE;
        dos_paradas[1] = Float.MAX_VALUE;


        for ( ; i < sn4.size(); i++)
        {
            Location punto_parada = new Location("punto_parada");

            punto_parada.setLatitude(sn4.get(i).getLatitud_parada());
            punto_parada.setLongitude(sn4.get(i).getLongitud_parada());

            float distancia = punto_referencia.distanceTo(punto_parada);


            if(dos_paradas[0] > distancia)
            {
                if(dos_paradas[1] > dos_paradas[0])
                {
                    dos_paradas[1] = dos_paradas[0];
                    prox_2 = prox_1;
                }

                dos_paradas[0] = distancia;
                prox_1 = i;

            }else{
                if(dos_paradas[1] > distancia)
                {
                    dos_paradas[1] = distancia;
                    prox_2 = i;
                }
            }



        }

        List<Parada> cercanas = new ArrayList<>();
        cercanas.add(sn4.get(prox_1));
        cercanas.add(sn4.get(prox_2));
        try{
            adapter = new BusesListAdapter<>(this, cercanas);
            list.setAdapter(adapter);
            setUpMapIfNeeded(latitud_actual, longitud_actual, cercanas);
        }catch (Exception ex)
        {
            ex.printStackTrace();
        }


    }

    /**
     * Método onResume (modificador)
     */

    @Override
    protected void onResume()
    {
        super.onResume();
    }

    /**
     * Método initUI (modificador)
     * Inicializa los contenidos de las vistas
     */

    public void initUI() {


        posicion_icon = (ImageView) findViewById(R.id.posicion_icon);

        if(Build.VERSION.SDK_INT < 22)
        {
            posicion_icon.setImageDrawable(getApplicationContext().getResources().getDrawable(R.mipmap.posicion));
        }
        else
        {
            posicion_icon.setImageDrawable(getApplicationContext().getDrawable(R.mipmap.posicion));
        }

        posicion_descripcion = (TextView) findViewById(R.id.posicion_descripcion);



        coordenadas = (TextView) findViewById(R.id.coordenadas);

    }

    /**
     * Método initLocationService (modificador)
     * Inicializa los servicios de localización de la aplicación
     */

    public void initLocationService(){
        // Obtener en la variable locationManager una instancia del servicio LOCATION_SERVICE

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        // Crear y establecer criterios de elección del proveedor de servicios de localización

        Criteria criteria = new Criteria();

        criteria.setAccuracy(Criteria.ACCURACY_COARSE);

        criteria.setPowerRequirement(Criteria.POWER_LOW);

        criteria.setAltitudeRequired(false);
        criteria.setBearingRequired(false);
        criteria.setSpeedRequired(false);

        criteria.setCostAllowed(true);


        // Obtener en la variable provider el mejor proveedor de servicios de localización
        // según los criterios establecidos

        provider = locationManager.getBestProvider(criteria,true);


    }

    /**
     * Método initMyLocation (modificador)
     * Obtiene la última posición gps conocida por la aplicación y la almacena
     */

    public void initMyLocation(){

        // Instanciar la variable location con la última ubicación conocida
        location = locationManager.getLastKnownLocation(provider);
        updateWithNewLocation(location);

    }

    /**
     * Método setLocationUpdates (modificador)
     *
     */

    public void setLocationUpdates(){
        // Registrar el listener locationListener en locationManager para que la
        // localización de la aplicación sea sensible a los movimientos del usuario
        locationManager.requestLocationUpdates(provider, 2000, 10, locationListener);
    }

    /**
     * Variable locationListener
     */

    private final LocationListener locationListener = new LocationListener() {
        // Implementar los métodos del LocationListener

        public void onLocationChanged(Location location)
        {
            updateWithNewLocation(location);
        }

        public void onProviderDisabled(String provider)
        {
            updateWithNewLocation(null);
        }

        public void onProviderEnabled(String provider)
        {

        }

        public void onStatusChanged(String provider, int status, Bundle extras)
        {

        }

    };

    /**
     * Método updateWithNewLocation (modificador)
     * @param location geolocalización de la aplicación mediante los sensores del dispositivo
     */

    private void updateWithNewLocation(Location location) {
        String latLongString;

        if (location != null) {
            double lat = location.getLatitude();
            double lng = location.getLongitude();
            latLongString = "Latitud:" + lat + " Longitud:" + lng;
        } else {
            latLongString = "No se encuentra la posicion";
        }
        coordenadas.setText(latLongString);
    }

    /**
     * Método onCreateOptionsMenu (modificador)
     * @param menu listado de items de la vista menu del action bar
     * @return Devuelve true por defecto
     */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     * Método onOptionsItemSelected (modificador)
     * @param item listado de items del menu
     * @return Devuelve true por defecto
     */

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.


        int ABOUT = 1;
        switch (item.getItemId())
        {
            case R.id.action_about_me:
                Intent intent = new Intent(this,About.class);

                startActivityForResult(intent,ABOUT);
                break;
            default:
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Método setUpMapIfNeeded (modificador)
     * @param latitud latitud (en grados) de las coordenadas de la posición del usuario
     * @param longitud longitud (en grados) de las coordenadas de la posición del usuario
     * @param cercanas Listado de paradas más cercanas a la posición del usuario
     */

    private void setUpMapIfNeeded(double latitud, double longitud, List cercanas) {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();

            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap(latitud,longitud,cercanas);
            }
        }
    }

    /**
     * Método setUpMap (modificador)
     * @param latitud latitud (en grados) de las coordenadas de la posición del usuario
     * @param longitud longitud (en grados) de las coordenadas de la posición del usuario
     * @param cercanas Listado de paradas más cercanas a la posición del usuario
     */

    private void setUpMap(double latitud, double longitud, List<Parada> cercanas) {

        //mMap.addMarker(new MarkerOptions().position(new LatLng(latitude, longitude)).title("Marker").snippet("Snippet"));

        // Enable MyLocation Layer of Google Map
        mMap.setMyLocationEnabled(true);

        // set map type
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        // Create a LatLng object for the current location
        LatLng latLng = new LatLng(latitud, longitud);

        String calle,numero,pais,cod_pais,estado,cod_postal,localidad,provincia;

        Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(latitud,longitud,1);
            Address obj = addresses.get(0);
            calle = obj.getAddressLine(0);
            pais = obj.getCountryName();
            cod_pais = obj.getCountryCode();
            estado = obj.getAdminArea();
            cod_postal = obj.getPostalCode();
            provincia = obj.getSubAdminArea();
            localidad = obj.getLocality();
            numero = obj.getSubThoroughfare();

            posicion_descripcion.setText(calle);

            // Show the current location in Google Map
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,15));
            mMap.animateCamera(CameraUpdateFactory.zoomTo(15));

            // Añadir al marcador el pais/ciudad como title/snippet

            mMap.addMarker(new MarkerOptions()
                    .position(new LatLng(latitud, longitud))
                    .title("Tú posicion")
                    .draggable(true)
                    .snippet(calle + " " + cod_postal + " " + localidad));
            mMap.addMarker(new MarkerOptions()
                    .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_bus_black_24dp))
                    .position(new LatLng(cercanas.get(0).getLatitud_parada(),cercanas.get(0).getLongitud_parada()))
                    .anchor(0.0f, 1.0f)
                    .title(cercanas.get(0).getNombre_parada())
                    .snippet(""+cercanas.get(0).getNum_parada()+" Hacia: "+cercanas.get(0).getSentido_parada())
            );
            mMap.addMarker(new MarkerOptions()
                            .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_bus_black_24dp))
                            .position(new LatLng(cercanas.get(1).getLatitud_parada(), cercanas.get(1).getLongitud_parada()))
                            .anchor(0.0f, 1.0f)
                            .title(cercanas.get(1).getNombre_parada())
                            .snippet("" + cercanas.get(1).getNum_parada()+" Hacia: "+cercanas.get(1).getSentido_parada())
            );


        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();
        }


    }


}
