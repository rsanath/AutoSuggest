package com.sanath;

import com.sanath.model.Word;

import java.util.List;

public class SuggestionEngine implements ISuggestionEngine {
    private Trie trie;

    @Override
    public void build(List<Word> wordList) {
        trie = new Trie(10);
        wordList.forEach(trie::insert);
    }

    @Override
    public List<Word> search(String query) {
        return trie.search(query);
    }
}
