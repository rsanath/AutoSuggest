package com.sanath.model;

import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

public class Node {
    private static final int MAX_CACHE = 10;

    private Letter letter;
    private List<Node> children;
    private TreeSet<Word> possibleWords;

    public Node(Letter letter) {
        this.letter = letter;
        this.possibleWords = new TreeSet<>();
        this.children = new LinkedList<>();
    }

    public void markWordEnd(Word word) {
        letter.setWordEnd(true);
        letter.setWeight(word.getWeight());
        addPossibleWord(word);
    }

    @Override
    public String toString() {
        return letter.getValue() + "";
    }

    public void addPossibleWord(Word word) {
        if (possibleWords.size() >= MAX_CACHE) {
            if (word.getWeight() > possibleWords.first().getWeight()) {
                possibleWords.remove(possibleWords.first());
            }
        }
        possibleWords.add(word);
    }

    public Letter getLetter() {
        return letter;
    }

    public void setLetter(Letter letter) {
        this.letter = letter;
    }

    public TreeSet<Word> getPossibleWords() {
        return possibleWords;
    }

    public void setPossibleWords(TreeSet<Word> possibleWords) {
        this.possibleWords = possibleWords;
    }

    public List<Node> getChildren() {
        return children;
    }

    public void setChildren(List<Node> children) {
        this.children = children;
    }
}
