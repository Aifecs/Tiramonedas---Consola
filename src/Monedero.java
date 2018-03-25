/**
 * Representa un monedero, que contiene un dinero inicial y se puede extraer dinero de el.
 */
public class Monedero {
    private static final int SALDO_INICIAL = 1000;
    private int saldo;

    Monedero() {
        saldo = SALDO_INICIAL;
    }

    /**
     * Saca dinero del monedero, solo si hay suficiente.
     * @param dinero dinero que se desea sacar
     * @return True si se pudo sacar el dinero, False si no hubo suficiente dinero para sacarlo.
     */
    public boolean sacarDinero(int dinero) {
        if (dinero <= saldo) {
            saldo -= dinero;
            return true;
        }
        return false;
    }

    /**
     *
     * @return True si hay dinero (dinero > 0), False si no.
     */
    public boolean hayDinero() {
        return saldo > 0;
    }

    /**
     *
     * @return Dinero actual en el monedero.
     */
    public int dineroActual() {
        return saldo;
    }
}
