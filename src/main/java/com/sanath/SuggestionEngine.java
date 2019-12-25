package com.sanath;

import com.sanath.ds.Trie;
import com.sanath.model.Word;

import java.util.List;

public class SuggestionEngine implements ISuggestionEngine {
    private Trie trie;

    @Override
    public void build(List<Word> wordList) {
        int cacheLimit = 10;
        String separator = "_";

        trie = new Trie(cacheLimit, separator);

        wordList.forEach(trie::insert);
    }

    @Override
    public List<Word> search(String query) {
        return trie.search(query);
    }
}
