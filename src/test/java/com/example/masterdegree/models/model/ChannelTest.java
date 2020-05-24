package com.example.masterdegree.models.model;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ChannelTest {

    @ParameterizedTest
    @ValueSource(strings = {"TvN", "tvn", "tVn", "tvN", "TVN"})
    void shouldBeTheSame(String channelName) {
        Channel channel = Channel.channel(channelName);

        assertTrue(channel.isTheSame("TVN"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"TvN 7", "tvn7", "tvn HD", "TVN HD", "tvn hd", "tVn hd", "tvN TVN", "TVN tvn"})
    void shouldNotBeTheSame(String channelName) {
        Channel channel = Channel.channel(channelName);

        assertFalse(channel.isTheSame("TVN"));
    }

}