package io.auxo.ame;

public enum VbrMode {

    OFF(0),
    MT(1),
    RH(2),
    ABR(3),
    MTRH(4),
    MAX_INDICATOR(5);

    int value;

    VbrMode(int mode) {
        this.value = mode;
    }

    public int getValue() {
        return value;
    }
}
