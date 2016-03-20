
package ar.fiuba.tdd.template.tp0;

import java.util.Random;

public class Cuantificador {

    private static Random RANDOM = new Random();
    private String cuantificador;


    public Cuantificador(String cuantificador) {
        this.cuantificador = cuantificador;
    }

    public String getCuantificador() {
        return cuantificador;
    }

    public Integer getRepetitions() {

        if (cuantificador.equals(Constants.SIGNOPREGUNTA)) {
            return RANDOM.nextInt(2);
        } else if (cuantificador.equals(Constants.ASTERISCO)) {
            return RANDOM.nextInt(9) + 1;
        } else if (cuantificador.equals(Constants.SIGNOSUMA)) {
            return RANDOM.nextInt(10);
        } else {
            return 1;
        }
    }


}