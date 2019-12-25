package com.sanath.model;

public class Word implements Comparable<Word> {
    private String value;
    private int weight;

    public Word(String value, int weight) {
        this.value = value;
        this.weight = weight;
    }

    @Override
    public int compareTo(Word o) {
        return Integer.compare(this.weight, o.weight);
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
