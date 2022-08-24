package com.epam.mjc;

import java.util.ArrayList;
import java.util.List;

public class MethodParser {

    /**
     * Parses string that represents a method signature and stores all it's members into a {@link MethodSignature} object.
     * signatureString is a java-like method signature with following parts:
     * 1. access modifier - optional, followed by space: ' '
     * 2. return type - followed by space: ' '
     * 3. method name
     * 4. arguments - surrounded with braces: '()' and separated by commas: ','
     * Each argument consists of argument type and argument name, separated by space: ' '.
     * Examples:
     * accessModifier returnType methodName(argumentType1 argumentName1, argumentType2 argumentName2)
     * private void log(String value)
     * Vector3 distort(int x, int y, int z, float magnitude)
     * public DateTime getCurrentDateTime()
     *
     * @param signatureString source string to parse
     * @return {@link MethodSignature} object filled with parsed values from source string
     */
    public MethodSignature parseFunction(String signatureString) {
        int firstIndex = signatureString.indexOf('(');
        int lastIndex = signatureString.lastIndexOf(')');
        if (firstIndex != 0 && lastIndex != 0) {
            String part1 = signatureString.substring(0, firstIndex);
            String part2 = signatureString.substring(firstIndex + 1, lastIndex);
            String[] arrPart1 = part1.split(" ");
            String[] arrPart2 = part2.split(", ");

            int ln1 = arrPart1.length;
            int ln2 = arrPart2.length;
            String accessModifier = "";
            String returnType = "";
            String methodName = "";

            if (ln1 >= 2) {
                accessModifier = ln1 == 3 ? arrPart1[0] : "";
                returnType = arrPart1[ln1 - 2];
                methodName = arrPart1[ln1 - 1];
            }

            List<MethodSignature.Argument> arguments = new ArrayList<>();
            if (ln2 > 0) {
                for (String str : arrPart2) {
                    String[] arrArguments = str.split(" ");
                    if (arrArguments.length == 2) {
                        MethodSignature.Argument argument = new MethodSignature.Argument(arrArguments[0], arrArguments[1]);
                        arguments.add(argument);
                    }
                }
            }

            MethodSignature methodSignature = new MethodSignature(methodName, arguments);
            if (!returnType.isEmpty()) {
                methodSignature.setReturnType(returnType);
            }
            if (!accessModifier.isEmpty()) {
                methodSignature.setAccessModifier(accessModifier);
            }
            return methodSignature;
        }

        throw new UnsupportedOperationException("You should implement this method.");

    }
}
