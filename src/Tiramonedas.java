import java.util.Calendar;
import java.util.Scanner;

public class Tiramonedas {
    private Maquina maquina;
    private Monedero monedero;
    private Scanner lector;

    private static final String[] MENSAJE_HORARIO = {"Buenos dias", "Buenas tardes", "Buenas noches"};

    public Tiramonedas() {
        maquina = new Maquina();
        monedero = new Monedero();
        lector = new Scanner(System.in);
    }

    public void iniciar() {
        saludar();
        jugar();
        despedirse();
    }

    private void saludar() {
        System.out.println("\n" +
                "$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n" +
                "$$$ Bienvenido al Tragamonedas $$$\n" +
                "$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n");
    }

    private void jugar() {
        while (monedero.hayDinero()) {
            System.out.println("Su saldo actual es $" + monedero.dineroActual() + ". ¿Cuánto desea apostar?");
            int apuesta;
            try {
                apuesta = Integer.parseInt(lector.nextLine());
                if(apuesta < 0) {
                    throw new Exception("La entrada ingresada es invalida, debe ser un numero mayor que cero.");
                }
            } catch (Exception e) {
                System.out.println("Error, debe escribir un numero mayor que cero.");
                continue;
            }
            if(apuesta == 0) {
                return;
            }
            if(monedero.sacarDinero(apuesta)) {
                maquina.apostar(apuesta);
                int premio = maquina.tirar();
                System.out.println(maquina.getTableroStr());
                if(premio > 0) {
                    System.out.println("Usted obtiene $" + premio + "!");
                }
            } else {
                System.out.println("No tiene saldo suficiente para apostar $" + apuesta +
                        ". Por favor, ingrese un valor menor que su saldo actual.");
            }
        }
    }

    private void despedirse() {
        float limiteDia = 11.59f;
        float limiteTarde = 19.59f;
        float limiteNoche = 5.59f;

        Calendar calendar = Calendar.getInstance();

        float hora = calendar.get(Calendar.HOUR_OF_DAY) + calendar.get(Calendar.MINUTE) / 100.0f;
        int mensajeHorario = (hora > limiteNoche && hora <= limiteDia) ? 0 : (hora > limiteDia && hora <= limiteTarde) ? 1 : 2;

        System.out.println(MENSAJE_HORARIO[mensajeHorario] + ", gracias por jugar. Su saldo final es de $" + monedero.dineroActual());
    }
}
