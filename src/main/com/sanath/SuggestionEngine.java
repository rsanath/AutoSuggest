package com.sanath;

import com.sanath.model.Letter;
import com.sanath.model.Node;
import com.sanath.model.Word;

import java.util.List;

public class SuggestionEngine implements ISuggestionEngine {
    private Node root;

    @Override
    public void build(List<Word> wordList) {
        root = new Node(null);
        wordList.forEach(word -> insert(word, root, 0));
    }

    @Override
    public List<Word> search(String query) {
        return null;
    }

    /**
     * Recursively traverse and insert character in the tree.
     * Also, register the words that are possible at the given position in tree.
     *
     * @param word,  the word to be added to the tree
     * @param root,  the current node in the tree
     * @param index, the index of the word that should be inserted
     */
    private void insert(Word word, Node root, int index) {
        if (word.getValue().length() == index) return;

        boolean isWordEnd = word.getValue().length() == (index + 1);
        char currentChar = word.getValue().charAt(index);

        for (Node node : root.getChildren()) {
            if (node.getLetter().isWordEnd()) {
                node.addPossibleWord(word);
            }
            if (currentChar == node.getLetter().getValue()) {
                if (isWordEnd) {
                    node.markWordEnd(word);
                }
                insert(word, node, ++index);
                return;
            }

        }

        Letter letter = new Letter(
            currentChar,
            isWordEnd ? word.getWeight() : null,
            isWordEnd);
        Node currentNode = new Node(letter);
        currentNode.addPossibleWord(word);
        root.getChildren().add(currentNode);

        insert(word, currentNode, ++index);
    }
}
