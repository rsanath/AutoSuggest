package com.sanath.model;

import java.util.*;

public class Node {
    private static final int MAX_CACHE = 10;

    private Letter letter;
    private Map<Character, Node> children;
    private TreeSet<Word> possibleWords;

    public Node(Letter letter) {
        this.letter = letter;
        this.possibleWords = new TreeSet<>();
        this.children = new HashMap<>();
    }

    public void markWordEnd(Word word) {
        letter.setWordEnd(true);
        letter.setWeight(word.getWeight());
        addPossibleWord(word);
    }

    public boolean hasChild(char c) {
        return children.containsKey(c);
    }

    public Node getChild(char c) {
        return children.get(c);
    }

    @Override
    public String toString() {
        return "Node{" +
            "letter=" + letter +
            ", children=" + children +
            ", possibleWords=" + possibleWords +
            '}';
    }

    public void addPossibleWord(Word word) {
        if (possibleWords.size() >= MAX_CACHE) {
            if (word.getWeight() > possibleWords.first().getWeight()) {
                possibleWords.remove(possibleWords.first());
            }
        }
        possibleWords.add(word);
    }

    public List<Word> getPossibleWordList() {
        return new ArrayList<>(possibleWords);
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

    public Map<Character, Node> getChildren() {
        return children;
    }

    public void setChildren(Map<Character, Node> children) {
        this.children = children;
    }
}
