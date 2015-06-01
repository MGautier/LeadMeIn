package ugr.sc.leadmeingranadabus;

/**
 * Created by MGautier on 21/05/15.
 */
public class Parada {

    /**
     * Variables internas
     */

    private int num_parada;
    private String nombre_parada;
    private double latitud_parada;
    private double longitud_parada;
    private String sentido_parada;

    /**
     * Constructor de la clase
     * @param num_parada Número de la parada de la línea de bus
     * @param nombre_parada Nombre de la parada de la línea de bus
     * @param latitud_parada Latitud (en grados) de la posición de la parada de la línea de bus
     * @param longitud_parada Longitud (en grados) de la posición de la parada de la línea de bus
     * @param sentido_parada Sentido de la línea para la parada de bus
     */

    Parada(int num_parada, String nombre_parada, double latitud_parada, double longitud_parada,
           String sentido_parada)
    {
        if(num_parada > 0)
        {
            this.num_parada = num_parada;
        }
        else
        {
            this.num_parada = 0;
        }

        if(!nombre_parada.isEmpty())
        {
            this.nombre_parada = nombre_parada;
        }
        else
        {
            this.nombre_parada = "Parada";
        }

        this.latitud_parada = latitud_parada;
        this.longitud_parada = longitud_parada;
        this.sentido_parada = sentido_parada;
    }

    /**
     * Método getNum_parada (consultor)
     * @return Devuelve el número de la parada
     */

    public int getNum_parada()
    {
        return this.num_parada;
    }

    /**
     * Método setNum_parada (modificador)
     * @param num_parada Número nuevo de la parada de la línea de bus
     */

    public void setNum_parada(int num_parada)
    {
        if(this.num_parada != num_parada && num_parada > 0)
        {
            this.num_parada = num_parada;
        }
    }

    /**
     * Método getNombre_parada (consultor)
     * @return Devuelve el nombre de la parada de la línea de bus
     */

    public String getNombre_parada()
    {
        return this.nombre_parada;
    }

    /**
     * Método setNombre_parada (modificador)
     * @param nombre_parada Nombre de la nueva parada de la línea de bus
     */

    public void setNombre_parada(String nombre_parada)
    {
        if(!nombre_parada.equals(this.nombre_parada))
        {
            this.nombre_parada = nombre_parada;
        }
    }

    /**
     * Método getLatitud_parada (consultor)
     * @return Devuelve la latitud (en grados) de la posición de la parada
     */

    public double getLatitud_parada()
    {
        return this.latitud_parada;
    }

    /**
     * Método setLatitud_parada (modificador)
     * @param latitud_parada Latitud (en grados) nueva para la posición de la parada
     */

    public void setLatitud_parada(double latitud_parada)
    {
        if(this.latitud_parada != latitud_parada)
        {
            this.latitud_parada = latitud_parada;
        }
    }

    /**
     * Método getLongitud_parada (consultor)
     * @return Devuelve la longitud (en grados) de la posición de la parada
     */

    public double getLongitud_parada()
    {
        return this.longitud_parada;
    }

    /**
     * Método setLongitud_parada (modificador)
     * @param longitud_parada Longitud (en grados) nueva para la posición de la parada
     */

    public void setLongitud_parada(double longitud_parada)
    {
        if(this.longitud_parada != longitud_parada)
        {
            this.longitud_parada = longitud_parada;
        }
    }

    /**
     * Metódo isParada (consultor)
     * @return Comprueba si el objeto esta inicializado (true) o es nulo (false)
     */

    public boolean isParada()
    {
        if(this == null)
        {
            return false;
        }else{
            return true;
        }

    }

    /**
     * Método isBefoere (consultor)
     * @param b objeto de la clase Parada
     * @return Devuelve true si las coordenadas del objeto que invoca al metodo son menores
     * que las coordenadas del objeto pasadas cómo parámetro
     */

    public boolean isBefore(Parada b)
    {
        if(this.latitud_parada < b.getLatitud_parada() &&
                this.longitud_parada < b.getLongitud_parada())
        {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Método isAfter (consultor)
     * @param b objeto de la clase Parada
     * @return Devuelve true si las coordenadas del objeto que invoca al metodo son mayores
     * que las coordenadas del objeto pasadas cómo parámetro
     */

    public boolean isAfter(Parada b)
    {
        if(this.latitud_parada > b.getLatitud_parada() &&
                this.longitud_parada > b.getLongitud_parada())
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Método getSentido_parada (consultor)
     * @return Devuelve el sentido de la línea para la parada de bus
     */

    public String getSentido_parada()
    {
        return this.sentido_parada;
    }

    /**
     * Método setSentido_parada (modificador)
     * @param sentido_parada Sentido nuevo para la parada de bus de la línea
     */

    public void setSentido_parada(String sentido_parada)
    {
        if(!this.sentido_parada.equals(sentido_parada))
        {
            this.sentido_parada = sentido_parada;
        }
    }
}
