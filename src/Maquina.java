/**
 * Representa una maquina de numeros aleatorios
 */
public class Maquina {
    /**
     * Son los premios por cantidad de asteriscos donde x[0] es por un asterisco.
     */
    private static final int[] PREMIOS_ASTERISCOS = {50, 300, 500};
    private int[] tablero;
    private int apuesta;
    private int cantidadNumeros = 3;

    Maquina() {
        if(cantidadNumeros <= 1) {
            //throw new Exception("Error. La maquina debe tener al menos dos numeros");
        }
        tablero = new int[cantidadNumeros];
        apuesta = 0;
    }

    /**
     * Inserta dinero en la maquina para luego tirar.
     * @param apuesta cantidad a insertar
     */
    public void apostar(int apuesta) {
        this.apuesta = apuesta;
    }

    /**
     * Realiza la jugada, simulando que se tira de la palanca.
     * @return El premio correspondiente al resultado.
     */
    public int tirar() {
        for (int i=0; i<tablero.length; i++) {
            tablero[i] = (int) (Math.random() * 10);
        }
        int premio = revisarPremio();
        apuesta = 0;
        return premio;
    }

    /**
     * Revisa el premio que corresponde al tablero actual
     * @return El premio obtenido o calculado.
     */
    private int revisarPremio() {
        int cantAsteriscos = 0;

        for (int i=0; i<tablero.length; i++) {
            if(tablero[i] == 0) {
                cantAsteriscos++;
            }
        }

        if(cantAsteriscos == 0) {
            boolean numerosIguales = true;
            for (int i = 0; i < tablero.length - 1; i++) {
                if (tablero[i] != tablero[i + 1]) {
                    numerosIguales = false;
                    break;
                }
            }
            if(numerosIguales) return tablero[0] * apuesta;
        } else {
            if(PREMIOS_ASTERISCOS.length  >= cantAsteriscos) {
                return PREMIOS_ASTERISCOS[cantAsteriscos - 1];
            } else {
                return cantAsteriscos * 300;
            }

        }
        return 0;
    }

    /**
     * Obtiene la representacion visual del tablero.
     * @return String con forma de tablero.
     */
    public String getTableroStr() {
        String tablero = "\t\t";
        for (int i=0; i<this.tablero.length; i++) {
            tablero += "+---";
        }
        tablero += "+\n\t\t| ";
        for (int i=0; i<this.tablero.length; i++) {
            tablero += (this.tablero[i] == 0 ? "*" : this.tablero[i]) + " | ";
        }
        tablero += "\n\t\t";
        for (int i=0; i<this.tablero.length; i++) {
            tablero += "+---";
        }
        tablero += "+";
        return  tablero;
    }
}
