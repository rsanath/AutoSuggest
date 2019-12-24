package com.sanath;

import com.sanath.model.Word;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class SuggestionEngineTest {

    @Test
    public void should_build_suggestion_engine_successfully() {
        ISuggestionEngine engine = new SuggestionEngine();

        List<Word> words = new ArrayList<Word>() {
            {
                add(new Word("hello_world", 10));
                add(new Word("hello", 12));
            }
        };
        engine.build(words);
    }

}
