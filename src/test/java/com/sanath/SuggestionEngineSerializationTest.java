package com.sanath;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class SuggestionEngineSerializationTest {

    @Test
    public void should_persist_and_load_from_json() {
        List<Word> words = new ArrayList<Word>() {
            {
                add(new Word("hello_world", 10));
                add(new Word("hello", 12));
                add(new Word("hel", 1));
                add(new Word("iel", 1));
            }
        };
        SuggestionEngine engine = new SuggestionEngine(words);

        String json = engine.getStateAsJson();
        Assert.assertNotNull(json);
    }

    @Test
    public void should_build_engine_from_json() {
        String json = "{\"root\":{\"children\":{\"w\":{\"letter\":\"w\",\"children\":{\"o\":{\"letter\":\"o\",\"children\":{\"r\":{\"letter\":\"r\",\"children\":{\"l\":{\"letter\":\"l\",\"children\":{\"d\":{\"letter\":\"d\",\"children\":{},\"possibleWords\":[{\"value\":\"hello_world\",\"weight\":10}]}},\"possibleWords\":[{\"value\":\"hello_world\",\"weight\":10}]}},\"possibleWords\":[{\"value\":\"hello_world\",\"weight\":10}]}},\"possibleWords\":[{\"value\":\"hello_world\",\"weight\":10}]}},\"possibleWords\":[{\"value\":\"hello_world\",\"weight\":10}]},\"h\":{\"letter\":\"h\",\"children\":{\"e\":{\"letter\":\"e\",\"children\":{\"l\":{\"letter\":\"l\",\"children\":{\"l\":{\"letter\":\"l\",\"children\":{\"o\":{\"letter\":\"o\",\"children\":{},\"possibleWords\":[{\"value\":\"hello_world\",\"weight\":10},{\"value\":\"hello\",\"weight\":12}]}},\"possibleWords\":[{\"value\":\"hello_world\",\"weight\":10},{\"value\":\"hello\",\"weight\":12}]}},\"possibleWords\":[{\"value\":\"hel\",\"weight\":1},{\"value\":\"hello_world\",\"weight\":10},{\"value\":\"hello\",\"weight\":12}]}},\"possibleWords\":[{\"value\":\"hel\",\"weight\":1},{\"value\":\"hello_world\",\"weight\":10},{\"value\":\"hello\",\"weight\":12}]}},\"possibleWords\":[{\"value\":\"hel\",\"weight\":1},{\"value\":\"hello_world\",\"weight\":10},{\"value\":\"hello\",\"weight\":12}]},\"i\":{\"letter\":\"i\",\"children\":{\"e\":{\"letter\":\"e\",\"children\":{\"l\":{\"letter\":\"l\",\"children\":{},\"possibleWords\":[{\"value\":\"iel\",\"weight\":1}]}},\"possibleWords\":[{\"value\":\"iel\",\"weight\":1}]}},\"possibleWords\":[{\"value\":\"iel\",\"weight\":1}]}},\"possibleWords\":[]},\"cacheSize\":10,\"separator\":\"_\"}\n";
        SuggestionEngine engine = SuggestionEngine.buildFromJson(json);

        List<Word> result = engine.search("h");
        Assert.assertEquals(3, result.size());
    }


}
