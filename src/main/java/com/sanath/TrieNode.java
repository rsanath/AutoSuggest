package com.sanath;

import java.util.*;

public class TrieNode {
    private Character letter;
    private Map<Character, TrieNode> children;
    private TreeSet<Word> possibleWords;

    public TrieNode(Character letter) {
        this.letter = letter;
        this.children = new HashMap<>();
        this.possibleWords = new TreeSet<>();
    }

    public boolean hasChild(char c) {
        return children.containsKey(c);
    }

    public TrieNode getChild(char c) {
        return children.get(c);
    }

    public List<Word> getPossibleWordList() {
        return new ArrayList<>(possibleWords.descendingSet());
    }

    public Character getLetter() {
        return letter;
    }

    public void setLetter(Character letter) {
        this.letter = letter;
    }

    public Map<Character, TrieNode> getChildren() {
        return children;
    }

    public void setChildren(Map<Character, TrieNode> children) {
        this.children = children;
    }

    public TreeSet<Word> getPossibleWords() {
        return possibleWords;
    }

    public void setPossibleWords(TreeSet<Word> possibleWords) {
        this.possibleWords = possibleWords;
    }
}
