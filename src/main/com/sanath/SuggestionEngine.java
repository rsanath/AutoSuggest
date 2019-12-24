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
        wordList.forEach(word -> {
            String[] segments = word.getValue().split("_");
            for (String segment : segments) {
                insert(segment, word, root, 0);
            }
        });
    }

    @Override
    public List<Word> search(String query) {
        Node current = root;
        for (int i = 0; i < query.length(); i++) {
            char c = query.charAt(i);
            if (current.getChildren().containsKey(c)) {
                current = current.getChildren().get(c);
                if (query.length() == (i + 1)) {
                    return current.getPossibleWordList();
                }
            } else {
                return Collections.emptyList();
            }
        }
        return Collections.emptyList();
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

        boolean isWordEnd = segment.length() == (index + 1);
        char currentChar = segment.charAt(index);

        if (root.getChildren().containsKey(currentChar)) {
            Node node = root.getChildren().get(currentChar);
            node.addPossibleWord(word);
            if (currentChar == node.getLetter().getValue()) {
                if (isWordEnd) node.markWordEnd(word);
                insert(segment, word, node, ++index);
            }
        } else {
            Node currentNode = createNode(word, root, isWordEnd, currentChar);
            insert(segment, word, currentNode, ++index);
        }
    }

    private Node createNode(Word word, Node root, boolean isWordEnd, char currentChar) {
        Letter letter = new Letter(
            currentChar,
            isWordEnd ? word.getWeight() : null,
            isWordEnd);
        Node currentNode = new Node(letter);
        currentNode.addPossibleWord(word);
        root.getChildren().put(currentChar, currentNode);
        return currentNode;
    }
}
