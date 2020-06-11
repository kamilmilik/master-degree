package com.example.masterdegree.models.model;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ChannelTest {

    @Disabled
    @ParameterizedTest
    @ValueSource(strings = {"TvN 7", "tvn7", "tvn HD", "TVN HD", "tvn hd", "tVn hd", "tvN TVN", "TVN tvn"})
    void shouldNotBeTheSame_whenHdAndNonHdShouldBeDifferent(String channelName) {
        Channel channel = Channel.channel(channelName);

        assertFalse(channel.isTheSame("TVN"));
    }

    @Disabled
    @ParameterizedTest
    @ValueSource(strings = {"TvN", "tvn", "tVn", "tvN", "TVN"})
    void shouldBeTheSame_whenHdAndNonHdShouldBeDifferent(String channelName) {
        Channel channel = Channel.channel(channelName);

        assertTrue(channel.isTheSame("TVN"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"TvN", "tvn", "tVn", "tvN", "TVN", "TVN HD", "tVn Hd", "tvn hd"})
    void shouldBeTheSame_whenHdAndNonHdShouldTheSame(String channelName) {
        Channel channel = Channel.channel(channelName);

        boolean actual = channel.isTheSame("TVN");

        assertTrue(actual);
    }

    @ParameterizedTest
    @ValueSource(strings = {"Tvn H d 4", "tvn h", "polsat HD", "TvN 7", "tvn7", "tvN TVN", "TVN tvn", "tvn", "tvn hds", "TvN 7 HD", "TVN7 HD", "TVN HD TVN"})
    void shouldNotBeTheSame_whenHdAndNonHdShouldBeTheSame(String channelName) {
        Channel channel = Channel.channel(channelName);

        boolean actual = channel.isTheSame("TVN HD");

        assertFalse(actual);
    }
}