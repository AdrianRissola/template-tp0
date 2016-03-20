package ar.fiuba.tdd.template.tp0;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class Parser {
    private static ArrayList<String> cuantificadores = new ArrayList<>(Arrays.asList(Constants.SIGNOSUMA, Constants
            .SIGNOPREGUNTA, Constants.ASTERISCO));

    public List<Expression> parse(String regex) {
        validarRegex(regex);
        ArrayList<Expression> expressions = new ArrayList<>();
        return parseString(regex, expressions);
    }


    private String getCuantificador(String expression) {
        String cuantificador;
        if (expression.length() >= 2) {
            cuantificador = String.valueOf(expression.charAt(1));
            if (!esCuantificador(cuantificador)) {
                cuantificador = Constants.VACIO;
            }
        } else {
            cuantificador = Constants.VACIO;
        }
        return cuantificador;
    }


    private String getLiteralExpression(int currentOffset, String regex) {
        String stringLiteral;
        String character = String.valueOf(regex.charAt(currentOffset));
        if (currentOffset < regex.length() - 1) {
            stringLiteral = regex.substring(currentOffset, currentOffset + 2);
        } else {
            stringLiteral = character;
        }
        return stringLiteral;
    }

    private boolean esCuantificador(String character) {
        return cuantificadores.contains(character);
    }

    private int parseStringLiteral(String literal, List<Expression> expressions) {
        String stringLiteral = String.valueOf(literal.charAt(0));
        String cuantificador = getCuantificador(literal);
        Cuantificador cuantificadorNew = new Cuantificador(cuantificador);
        Expression expression = new Expression(stringLiteral, cuantificadorNew);
        expressions.add(expression);
        return expression.getExpression().length() - 1;
    }

    private String getSet(String expression) {
        int ultimoChar = expression.indexOf(Constants.CORCHETESFIN) + 1;
        if ((expression.length()) > ultimoChar) {
            String cuantificador = String.valueOf(expression.charAt(ultimoChar));
            if (esCuantificador(cuantificador)) {
                ultimoChar++;
            }
        }
        return expression.substring(0, ultimoChar);

    }


    private int parseSet(String string, List<Expression> expressions) {
        String cuantificador = String.valueOf(string.charAt(string.length() - 1));
        int end = string.length() - 1;
        int size = end;
        if (esCuantificador(cuantificador)) {
            end--;
            size++;
        } else {
            cuantificador = Constants.VACIO;
        }
        String expression = string.substring(1, end);

        Cuantificador cuantificadorNew = new Cuantificador(cuantificador);
        expressions.add(new Expression(expression, cuantificadorNew));

        return size;
    }


    private void validarRegex(String regex) throws PatternSyntaxException {
        Pattern.compile(regex);
    }


    private List<Expression> parseString(String regex, List<Expression> expressions) {
        boolean escapear = false;
        for (int i = 0; i < regex.length(); i++) {
            String character = String.valueOf(regex.charAt(i));
            if (escapear || (!character.equals(Constants.CORCHETESINI))
                    && (!character.equals(Constants.ESCAPE))) {
                escapear = false;
                String litExpression = getLiteralExpression(i, regex);
                i += parseStringLiteral(litExpression, expressions);
            } else {
                escapear = character.equals(Constants.ESCAPE);
                if (!escapear) {
                    String set = getSet(regex.substring(i));
                    i += parseSet(set, expressions);
                }
            }
        }
        return expressions;
    }
}