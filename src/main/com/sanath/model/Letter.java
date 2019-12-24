package com.sanath.model;

public class Letter implements Comparable<Letter> {
    private char value;
    private Integer weight;
    private boolean isWordEnd;


    public Letter(char value, Integer weight, boolean isWordEnd) {
        this.value = value;
        this.weight = weight;
        this.isWordEnd = isWordEnd;
    }

    @Override
    public String toString() {
        return "Letter{" +
            "value=" + value +
            ", weight=" + weight +
            ", isWordEnd=" + isWordEnd +
            '}';
    }

    @Override
    public int compareTo(Letter o) {
        return Integer.compare(this.weight, o.weight);
    }

    public char getValue() {
        return value;
    }

    public void setValue(char value) {
        this.value = value;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public boolean isWordEnd() {
        return isWordEnd;
    }

    public void setWordEnd(boolean wordEnd) {
        isWordEnd = wordEnd;
    }
}
