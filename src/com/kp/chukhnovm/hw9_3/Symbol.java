package com.kp.chukhnovm.hw9_3;

public class Symbol {
    private final char ch;
    private int frequency;
    private double relativeFrequency;

    public Symbol(char ch) {
        this.ch = ch;
        this.increaseFrequency();
    }

    public char getCh() {
        return ch;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public double calcRelativeFrequency(int total) {
        if (total > 0 && this.frequency > 0) {
            this.relativeFrequency = ((double)100 * (double)this.getFrequency()) / (double)total;
        }

        return 0;
    }

    public double getRelativeFrequency() {
        return this.relativeFrequency;
    }

    public void increaseFrequency() {
        this.frequency++;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Symbol symbol = (Symbol) o;

        return ch == symbol.ch;
    }

    @Override
    public int hashCode() {
        return ch;
    }

    @Override
    public String toString() {
        return "Symbol{" +
                "ch=" + ch +
                ", frequency=" + frequency +
                '}';
    }
}
