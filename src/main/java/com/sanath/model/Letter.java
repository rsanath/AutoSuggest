package com.sanath.model;

public class Letter implements Comparable<Letter> {
    private char value;
    private Integer weight;


    public Letter(char value, Integer weight) {
        this.value = value;
        this.weight = weight;
    }

    public boolean isWordEnd() {
        return weight != null;
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
}
