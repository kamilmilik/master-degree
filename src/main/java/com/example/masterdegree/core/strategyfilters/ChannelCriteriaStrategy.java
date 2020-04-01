package com.example.masterdegree.core.strategyfilters;

import com.example.masterdegree.models.dto.ResultTvPackageResponseDto;
import com.example.masterdegree.models.entity.Channel;
import com.example.masterdegree.models.entity.Criteria;
import com.example.masterdegree.models.entity.TvPackage;

import java.util.Iterator;
import java.util.List;

public class ChannelCriteriaStrategy implements CriteriaStrategy {

    private Criteria criteria;

    public ChannelCriteriaStrategy(Criteria criteria) {
        this.criteria = criteria;
    }

    @Override
    public List<ResultTvPackageResponseDto> getFilteredResult(List<ResultTvPackageResponseDto> resultTvPackageResponseDtos) {
        return this.getResultBySelectedChannels(resultTvPackageResponseDtos);
    }

    // TODO KM sprawdzic czy dziala w wiekszej ilosci danych
    // TODO KM refactor
    public List<ResultTvPackageResponseDto> getResultBySelectedChannels(List<ResultTvPackageResponseDto> resultTvPackageResponseDtos) {
        for (Channel selectedChannel : criteria.getChannels()) {
            for (Iterator<ResultTvPackageResponseDto> it = resultTvPackageResponseDtos.iterator(); it.hasNext(); ) {
                ResultTvPackageResponseDto resultTvPackageResponseDto = it.next();
                boolean isFoundSelectedChannel = false;
                for (Channel channel : resultTvPackageResponseDto.getFilteredTvPackageResponseDto().getChannels()) {
                    if (channel.equals(selectedChannel)) {
                        isFoundSelectedChannel = true; // znalazlem kanal w main tv package
                        break;
                    }
                }
                if (!isFoundSelectedChannel) { // nie znalazlem kanalu w main tv package, moze jest w dodatkowych
                    for (TvPackage extraAvailableTvPackage : resultTvPackageResponseDto.getFilteredTvPackageResponseDto().getExtraAvailableTvPackages()) {
                        for (Channel channel : extraAvailableTvPackage.getChannels())
                            if (channel.equals(selectedChannel)) {
                                isFoundSelectedChannel = true; // jest w dodatkowych, nie w main
                                resultTvPackageResponseDto.getFilteredTvPackageResponseDto().getExtraTvPackagesWhichMeetCriteria().add(extraAvailableTvPackage);
                                break;
                            }
                    }
                    if (!isFoundSelectedChannel) { // nie ma w tym main, ani w dodatkowych, usun ten proponowany pakiet glowny z dodatkowymi
                        it.remove();
                    }
                }
            }
        }
        return resultTvPackageResponseDtos;
    }
}
