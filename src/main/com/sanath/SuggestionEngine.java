package com.sanath;

import com.sanath.model.Letter;
import com.sanath.model.Node;
import com.sanath.model.Word;

import java.util.Collections;
import java.util.List;

public class SuggestionEngine implements ISuggestionEngine {
    private Node root;

    @Override
    public void build(List<Word> wordList) {
        root = new Node(null);
        wordList.forEach(this::insertWord);
    }

    @Override
    public List<Word> search(String query) {
        Node currNode = root;
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

    private void insertWord(Word word) {
        String[] segments = word.getValue().split("_");
        for (String s : segments)
            insert(s, word, root, 0);
    }

    /**
     * Recursively traverse and insert character in the tree.
     * Also, register the words that are possible at the given position in tree.
     *
     * @param word,  the word to be added to the tree
     * @param root,  the current node in the tree
     * @param index, the index of the word that should be inserted
     */
    private void insert(String segment, Word word, Node root, int index) {
        if (segment.length() == index) return;

        boolean isSegmentEnd = segment.length() == (index + 1);
        char currentChar = segment.charAt(index);

        if (root.getChildren().containsKey(currentChar)) {
            Node node = root.getChildren().get(currentChar);
            node.addPossibleWord(word);
            if (currentChar == node.getLetter().getValue()) {
                if (isSegmentEnd) node.markWordEnd(word);
                insert(segment, word, node, ++index);
            }
        } else {
            Letter letter = new Letter(
                currentChar,
                isSegmentEnd ? word.getWeight() : null,
                isSegmentEnd);
            Node currentNode = new Node(letter);
            currentNode.addPossibleWord(word);
            root.getChildren().put(currentChar, currentNode);
            insert(segment, word, currentNode, ++index);
        }
    }
}
