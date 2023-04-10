package com.example.fishing.util;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.fishing.model.Player;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class PrintUtilTest {
    @Test
    void testPrintResult3() {
        Player player = mock(Player.class);
        when(player.getTotalWins()).thenReturn(1);
        when(player.getName()).thenReturn("Name");
        PrintUtil.printResult(new Player[]{player});
        verify(player).getTotalWins();
        verify(player).getName();
    }
}

