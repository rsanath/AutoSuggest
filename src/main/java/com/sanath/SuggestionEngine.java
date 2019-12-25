package com.sanath;

import com.google.gson.Gson;

import java.util.List;

public class SuggestionEngine {
    private Trie trie;

    public SuggestionEngine(List<Word> words) {
        int cacheLimit = 10;
        String separator = "_";

        trie = new Trie(cacheLimit, separator);
        words.forEach(trie::insert);
    }

    public static SuggestionEngine buildFromJson(String json) {
        Gson gson = new Gson();
        SuggestionEngine engine = new SuggestionEngine();

        engine.trie = gson.fromJson(json, Trie.class);
        return engine;
    }

    private SuggestionEngine() {
    }

    public List<Word> search(String query) {
        return trie.search(query);
    }

    public String getStateAsJson() {
        Gson gson = new Gson();
        return gson.toJson(trie);
    }
}
