package com.example.fishing.component;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.jline.utils.AttributedString;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {FishPromptProvider.class})
@ExtendWith(SpringExtension.class)
class FishPromptProviderTest {
    @Autowired
    private FishPromptProvider fishPromptProvider;

    @Test
    void testGetPrompt() {
        AttributedString actualPrompt = fishPromptProvider.getPrompt();
        assertEquals(5, actualPrompt.length());
        AttributedString toAttributedStringResult = actualPrompt.toAttributedString();
        assertEquals(actualPrompt, toAttributedStringResult);
        assertEquals("Fish:", toAttributedStringResult.toAnsi());
    }
}

