package ar.fiuba.tdd.template.tp0;

import java.util.Random;


public class Expression {

    private Cuantificador cuantificador;

    private String expression;

    public Expression(String expression, Cuantificador cuantificador) {
        this.expression = expression;
        this.cuantificador = cuantificador;
    }


    public String generate(int maxLength) {
        final String generatedString = generarString();

        final int limit;
        int repetitions = cuantificador.getRepetitions();
        if ((repetitions * expression.length()) > maxLength) {
            limit = maxLength / repetitions;
        } else {
            limit = cuantificador.getRepetitions();
        }
        return getCollect(generatedString, limit);
    }

    private String generarString() {
        String resultado;
        if (expression.length() > 1) {
            Random random = new Random();
            int charPosition = random.nextInt(expression.length());
            resultado = String.valueOf(expression.charAt(charPosition));
        } else {
            resultado = expression.replace(Constants.PUNTO, createRandomString());
        }
        return resultado;
    }

    private String getCollect(String string, int cantidad) {

        StringBuilder stringGen = new StringBuilder();
        for (int i = 0; i < cantidad; i++) {
            stringGen.append(string);
        }
        return stringGen.toString();

    }


    public String getExpression() {
        return expression + cuantificador.getCuantificador();
    }


    private String createRandomString() {
        Random random = new Random();
        int randomInt = random.nextInt(256);
        return String.valueOf((char) randomInt);
    }
}