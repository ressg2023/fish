package com.example.fishing.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class RankUtilTest {
    @Test
    void testGetCardNumber() {
        assertEquals("10", RankUtil.getCardNumber(10));
        assertEquals("A", RankUtil.getCardNumber(14));
        assertEquals("J", RankUtil.getCardNumber(11));
        assertEquals("Q", RankUtil.getCardNumber(12));
        assertEquals("K", RankUtil.getCardNumber(13));
    }
}

