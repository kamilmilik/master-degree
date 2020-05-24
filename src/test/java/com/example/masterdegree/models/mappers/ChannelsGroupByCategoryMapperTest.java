package com.example.masterdegree.models.mappers;

import com.example.masterdegree.models.dto.ChannelDto;
import com.example.masterdegree.models.dto.ChannelsGroupByCategoryResponseDto;
import com.example.masterdegree.models.model.Channel;
import com.example.masterdegree.models.model.ChannelsGroupByCategory;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static com.example.masterdegree.Constants.POLSAT;
import static com.example.masterdegree.Constants.TVN;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

class ChannelsGroupByCategoryMapperTest {

    private static final String ID = "123";
    private static final String CATEGORY = "film";

    private final ChannelsGroupByCategoryMapper channelsGroupByCategoryMapper = new ChannelsGroupByCategoryMapper();

    private static Stream<Arguments> channelsGroupByCategory() {
        List<Channel> channels = asList(Channel.channel(TVN), Channel.channel(POLSAT));
        ChannelsGroupByCategory channelsGroupByCategory = new ChannelsGroupByCategory(ID, CATEGORY, channels);

        List<ChannelDto> channelsDto = asList(new ChannelDto(TVN, null), new ChannelDto(POLSAT, null));
        ChannelsGroupByCategoryResponseDto expected = ChannelsGroupByCategoryResponseDto.newChannelsGroupByCategoryDto(ID, CATEGORY, channelsDto);
        return Stream.of(
                Arguments.of(channelsGroupByCategory, expected)
        );
    }

    @ParameterizedTest
    @MethodSource("channelsGroupByCategory")
    void shouldConvertToDto(ChannelsGroupByCategory channelsGroupByCategory, ChannelsGroupByCategoryResponseDto expected) {
        ChannelsGroupByCategoryResponseDto actual = channelsGroupByCategoryMapper.convertToDto(channelsGroupByCategory);

        assertThat(actual).isEqualTo(expected);
    }

}