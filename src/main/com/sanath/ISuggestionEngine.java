package com.sanath;

import com.sanath.model.Word;

import java.util.List;

public interface ISuggestionEngine {
    void build(List<Word> input);

    List<Word> search(String query);
}
