package ar.fiuba.tdd.template.tp0;

import java.util.ArrayList;
import java.util.List;

public class RegExGenerator {
    // TODO: Uncomment this field
    private int maxLength;

    public RegExGenerator(int maxLength) {
        this.maxLength = maxLength;
    }

    // TODO: Uncomment parameters
    public List<String> generate(String regEx, int numberOfResults) {
        Parser parser = new Parser();
        List<Expression> expressions = parser.parse(regEx);
        List<String> generatedStrings = new ArrayList<>();
        for (int i = 0; i < numberOfResults; ++i) {
            generatedStrings.add(generate(expressions));
        }
        return generatedStrings;
    }

    private String generate(List<Expression> expressions) {
        StringBuilder generatedString = new StringBuilder();
        for (Expression expression : expressions) {
            generatedString.append(expression.generate(maxLength));
        }
        return generatedString.toString();
    }
}
