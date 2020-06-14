package com.example.masterdegree.models.model;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ChannelTest {

    private static Stream<Arguments> channelsTheSame() {
        return Stream.of(
                Arguments.of("TVN hd", "TVN HD"),
                Arguments.of("tvN Hd", "TVN HD"),
                Arguments.of("tvn", "tvn hd"),
                Arguments.of("TVN", "tvn hd"),
                Arguments.of("tvn HD", "tvn hd"),
                Arguments.of("DW", "DW HD")
        );
    }

    private static Stream<Arguments> channelsDifference() {
        return Stream.of(
                Arguments.of("TVN", "TVN H d 4"),
                Arguments.of("TVN", "tvn h"),
                Arguments.of("TVN", "polsat HD"),
                Arguments.of("TVN", "TvN 7"),
                Arguments.of("TVN", "tvn7"),
                Arguments.of("TVN", "tvN TVN"),
                Arguments.of("TVN", "TVN tvn"),
                Arguments.of("TVN", "tvn hds"),
                Arguments.of("TVN", "TvN 7 HD"),
                Arguments.of("TVN", "TVN7 HD"),
                Arguments.of("TVN", "TVN HD TVN"),
                Arguments.of("TVN HD", "TVN"),
                Arguments.of("TVN HD", "tvn"),
                Arguments.of("TVN HD", "tvn hd super"),
                Arguments.of("TVN HD", "tvn super hd"),
                Arguments.of("DW HD", "DW")
        );
    }

    private static Stream<Arguments> channelsTheSameHdIsTheSameAsNonHd() {
        return Stream.of(
                Arguments.of("TVN", "TVN HD"),
                Arguments.of("tvn", "TVN HD"),
                Arguments.of("tvn", "tvn HD"),
                Arguments.of("tvn hd", "tvn"),
                Arguments.of("tvn hd", "tvn HD"),
                Arguments.of("tvn hd", "TVN"),
                Arguments.of("tvn hd", "TVNHD"),
                Arguments.of("tvn hd", "TVN  HD"),
                Arguments.of("tvn  hd", "TVNHD"),
                Arguments.of("tvnhd", "tvn hd"),
                Arguments.of("tvn hd", "tvnhd"),
                Arguments.of("t v n h d", "tvnhd"),
                Arguments.of("tvnhd", "t v n h d"),
                Arguments.of("tvn 2hd", "t v n 2h d"),
                Arguments.of("polsat sport premium 1 ", "polsat sport premium 1 HD")
        );
    }

    private static Stream<Arguments> channelsDifferenceHdIsTheSameAsNonHd() {
        return Stream.of(
                Arguments.of("Polsat", "PolSat2"),
                Arguments.of("Polsat", "PolSat 2"),
                Arguments.of("Polsat", "PolSat sport"),
                Arguments.of("tvn", "tvn24"),
                Arguments.of("tvvn", "tvn"),
                Arguments.of("tv24n", "tvn"),
                Arguments.of("tvn 2", "tvn"),
                Arguments.of("tvn2", "tvn"),
                Arguments.of("tvn", "tvn2"),
                Arguments.of("tvn", "tvn 2"),
                Arguments.of("tvn", "tvn hd 2"),
                Arguments.of("tvnhd2", "tvn "),
                Arguments.of("tvnhd 2", "tvn "),
                Arguments.of("tvn2", "tvn "),
                Arguments.of("tvn", "tvn 2"),
                Arguments.of("tvn", "jvm"),
                Arguments.of("tvn hd", "tvn hd2"),
                Arguments.of("tvn hd", "tvn hd 2"),
                Arguments.of("tvn", "Polsat")
        );
    }

    @Disabled
    @ParameterizedTest
    @ValueSource(strings = {"TvN 7", "tvn7", "tvn HD", "TVN HD", "tVn hd", "tvN TVN", "TVN tvn"})
    void shouldNotBeTheSame_whenHdAndNonHdShouldBeDifferent(String channelName) {
        Channel channel = Channel.channel(channelName);

        assertFalse(channel.isTheSame("TVN"));
    }

    @Disabled
    @ParameterizedTest
    @ValueSource(strings = {"TvN", "tvn", "tVn", "tvN", "TVN"})
    @MethodSource("channelsTheSame")
    void shouldBeTheSame_whenHdAndNonHdShouldBeDifferent(String channelName) {
        Channel channel = Channel.channel(channelName);

        assertTrue(channel.isTheSame("TVN"));
    }

    @Disabled
    @ParameterizedTest
    @MethodSource("channelsTheSame")
    void shouldBeTheSame_whenHdAndNonHdShouldTheSame(String channelName, String searchedChannelName) {
        Channel channel = Channel.channel(channelName);

        boolean actual = channel.isTheSame(searchedChannelName);

        assertTrue(actual);
    }

    @Disabled
    @ParameterizedTest
    @MethodSource("channelsDifference")
    void shouldNotBeTheSame_whenHdAndNonHdShouldBeTheSame(String channelName, String searchedChannelName) {
        Channel channel = Channel.channel(channelName);

        boolean actual = channel.isTheSame(searchedChannelName);

        assertFalse(actual);
    }

    @ParameterizedTest
    @MethodSource("channelsTheSameHdIsTheSameAsNonHd")
    void shouldBeTheSame_whenNoDifferenceWithChannelsNonHdAndHd(String channelName, String searchedChannelName) {
        Channel channel = Channel.channel(channelName);

        boolean actual = channel.isTheSame(searchedChannelName);

        assertTrue(actual);
    }

    @ParameterizedTest
    @MethodSource("channelsDifferenceHdIsTheSameAsNonHd")
    void shouldNotBeTheSame_whenNoDifferenceWithChannelsNonHdAndHd(String channelName, String searchedChannelName) {
        Channel channel = Channel.channel(channelName);

        boolean actual = channel.isTheSame(searchedChannelName);

        assertFalse(actual);
    }

}