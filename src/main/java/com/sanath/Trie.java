package com.sanath;

import com.sanath.model.Letter;
import com.sanath.model.Word;

import java.util.Collections;
import java.util.List;
import java.util.TreeSet;

public class Trie {
    private TrieNode root;
    private int cacheSize;

    public Trie(int cacheSize) {
        root = new TrieNode(null);
        this.cacheSize = cacheSize;
    }

    public void insert(Word word) {
        String[] segments = word.getValue().split("_");
        for (String s : segments) {
            insertSegment(s, word);
        }
    }

    public List<Word> search(String query) {
        TrieNode currNode = root;
        for (int i = 0; i < query.length(); i++) {
            char currChar = query.charAt(i);
            if (currNode.hasChild(currChar)) {
                currNode = currNode.getChild(currChar);
                if (query.length() == (i + 1)) {
                    return currNode.getPossibleWordList();
                }
            } else {
                return Collections.emptyList();
            }
        }
        return Collections.emptyList();
    }

    private void insertSegment(String segment, Word word) {
        TrieNode head = root;
        for (int i = 0; i < segment.toCharArray().length; i++) {
            char ch = segment.charAt(i);

            if (head.hasChild(ch)) {
                head = head.getChild(ch);
            } else {
                TrieNode node = createNode(ch, word);
                head.getChildren().put(ch, node);
                head = node;
            }
            addPossibleWordToNode(word, head);
        }
    }

    private TrieNode createNode(char c, Word word) {
        Letter letter = new Letter(c, word.getWeight());
        TrieNode node = new TrieNode(letter);
        return node;
    }

    private void addPossibleWordToNode(Word word, TrieNode node) {
        TreeSet<Word> possibleWords = node.getPossibleWords();
        if (possibleWords.size() >= cacheSize) {
            if (word.getWeight() > possibleWords.first().getWeight()) {
                possibleWords.remove(possibleWords.first());
            }
        }
        possibleWords.add(word);
    }

    public int getCacheSize() {
        return cacheSize;
    }

    public void setCacheSize(int cacheSize) {
        this.cacheSize = cacheSize;
    }
}
