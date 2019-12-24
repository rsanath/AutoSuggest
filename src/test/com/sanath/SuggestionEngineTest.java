package com.sanath;

import com.sanath.model.Word;
import org.junit.Assert;
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
                add(new Word("hel", 1));
                add(new Word("iel", 1));
            }
        };
        engine.build(words);
    }

    @Test
    public void show_search() {
        ISuggestionEngine engine = new SuggestionEngine();
        List<Word> words = new ArrayList<Word>() {
            {
                add(new Word("hello_world", 10));
                add(new Word("hello", 12));
                add(new Word("hel", 1));
                add(new Word("iel", 1));
            }
        };
        engine.build(words);

        List<Word> result = engine.search("hel");
        Assert.assertEquals(3, result.size());

        result = engine.search("i");
        Assert.assertEquals(1, result.size());
    }

    @Test
    public void show_search_when_no_words_found() {
        ISuggestionEngine engine = new SuggestionEngine();
        List<Word> words = new ArrayList<Word>() {
            {
                add(new Word("hello_world", 10));
                add(new Word("hello", 12));
                add(new Word("hel", 1));
                add(new Word("iel", 1));
            }
        };
        engine.build(words);

        List<Word> result = engine.search("a");
        Assert.assertEquals(0, result.size());
    }

    @Test
    public void show_search_when_search_second_half() {
        ISuggestionEngine engine = new SuggestionEngine();
        List<Word> words = new ArrayList<Word>() {
            {
                add(new Word("hello_world", 10));
                add(new Word("hello", 12));
                add(new Word("hel", 1));
                add(new Word("iel", 1));
            }
        };
        engine.build(words);

        List<Word> result = engine.search("wor");
        String possibleWord = result.get(0).getValue();

        Assert.assertEquals("hello_world", possibleWord);
        Assert.assertEquals(1, result.size());
    }

}
