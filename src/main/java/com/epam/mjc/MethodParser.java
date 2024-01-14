package com.epam.mjc;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class MethodParser {

    /**
     * Parses string that represents a method signature and stores all it's members into a {@link MethodSignature} object.
     * signatureString is a java-like method signature with following parts:
     *      1. access modifier - optional, followed by space: ' '
     *      2. return type - followed by space: ' '
     *      3. method name
     *      4. arguments - surrounded with braces: '()' and separated by commas: ','
     * Each argument consists of argument type and argument name, separated by space: ' '.
     * Examples:
     *      accessModifier returnType methodName(argumentType1 argumentName1, argumentType2 argumentName2)
     *      private void log(String value)
     *      Vector3 distort(int x, int y, int z, float magnitude)
     *      public DateTime getCurrentDateTime()
     *
     * @param signatureString source string to parse
     * @return {@link MethodSignature} object filled with parsed values from source string
     */
    public MethodSignature parseFunction(String signatureString) {
        //throw new UnsupportedOperationException("You should implement this method.");
        String accessModifier = null;
        String returnType = null;
        String methodName;

        StringTokenizer st = new StringTokenizer(signatureString);
        String token;
        String methodNameArgs = "";

        while (st.hasMoreTokens()) {
            token = st.nextToken();
            if (token.equals("private") ||
                    token.equals("public") ||
                    token.equals("protected")) {
                accessModifier = token;
                System.out.println("accessModifier is: " + accessModifier);
            } else if (token.contains("(")) {
                if (st.countTokens()>0) {
                    token += st.nextToken("");
                }
                methodNameArgs = token;
                System.out.println("methodName with arguments is: " + methodNameArgs);
            } else {
                returnType = token;
                System.out.println("returnType is: " + returnType);
            }
        }

        int index1 = methodNameArgs.indexOf('(');
        int index2 = methodNameArgs.indexOf(')');
        methodName = methodNameArgs.substring(0, index1);

        String[] argsArr;
        List<MethodSignature.Argument> arguments = new ArrayList<>();

        if (!methodNameArgs.substring(index1+1, index2).isEmpty()) {
            argsArr = methodNameArgs.substring(index1+1, index2).split(", ");
            for (String s: argsArr) {
                String[] argsArr2 = s.split(" ");
                MethodSignature.Argument argument = new MethodSignature.Argument(argsArr2[0], argsArr2[1]);
                arguments.add(argument);
                //System.out.println(s);
            }
        }

        MethodSignature methodSignature = new MethodSignature(methodName, arguments);
        methodSignature.setAccessModifier(accessModifier);
        methodSignature.setReturnType(returnType);

        return methodSignature;
    }
    public static void main(String[] args) {
        MethodSignature methodSignature = new MethodParser().
                parseFunction("private void log(String value)");
    }
}
