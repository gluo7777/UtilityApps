package enums;

import java.util.EnumSet;
import java.util.Set;

public enum Operation {
    ADD("add"),
    SUBTRACT("sub"),
    MULTIPLY("mult"),
    DIVISION("div"),
    MODULO("mod")
    ;

    private String value;
    private static Set<Operation> operations = EnumSet.range(ADD,DIVISION);

    Operation(String value) {
        this.value = value;
    }

    public static Set<Operation> getOperations() {
        return operations;
    }
}
