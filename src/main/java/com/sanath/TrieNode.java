package com.sanath;

import com.sanath.model.Letter;
import com.sanath.model.Word;

import java.util.*;

public class TrieNode {
    private Letter letter;
    private Map<Character, TrieNode> children;
    private TreeSet<Word> possibleWords;

    public TrieNode(Letter letter) {
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
        return new ArrayList<>(possibleWords);
    }

    public Letter getLetter() {
        return letter;
    }

    public void setLetter(Letter letter) {
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
