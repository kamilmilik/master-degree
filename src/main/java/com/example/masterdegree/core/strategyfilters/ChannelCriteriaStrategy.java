package com.example.masterdegree.core.strategyfilters;

import com.example.masterdegree.models.dto.ResultTvPackageResponseDto;
import com.example.masterdegree.models.entity.Channel;
import com.example.masterdegree.models.entity.Criteria;
import com.example.masterdegree.models.entity.TvPackage;
import com.example.masterdegree.models.model.ResultTvPackage;
import com.example.masterdegree.models.model.ResultTvPackages;

import java.util.Iterator;
import java.util.List;

public class ChannelCriteriaStrategy implements CriteriaStrategy {

    private Criteria criteria;

    public ChannelCriteriaStrategy(Criteria criteria) {
        this.criteria = criteria;
    }

    @Override
    public List<ResultTvPackage> getFilteredResult(List<ResultTvPackage> resultTvPackages) {
        return this.getResultBySelectedChannels(resultTvPackages);
    }

    // TODO KM sprawdzic czy dziala w wiekszej ilosci danych
    // TODO KM refactor
    public List<ResultTvPackage> getResultBySelectedChannels(List<ResultTvPackage> resultTvPackages) {
        for (Channel selectedChannel : criteria.getChannels()) {
            for (Iterator<ResultTvPackage> it = resultTvPackages.iterator(); it.hasNext(); ) {
                ResultTvPackage resultTvPackage = it.next();
                boolean isFoundSelectedChannel = false;
                for (Channel channel : resultTvPackage.getFilteredTvPackage().getChannels()) {
                    if (channel.equals(selectedChannel)) {
                        isFoundSelectedChannel = true; // znalazlem kanal w main tv package
                        break;
                    }
                }
                if (!isFoundSelectedChannel) { // nie znalazlem kanalu w main tv package, moze jest w dodatkowych
                    for (TvPackage extraAvailableTvPackage : resultTvPackage.getFilteredTvPackage().getExtraAvailableTvPackages()) {
                        for (Channel channel : extraAvailableTvPackage.getChannels())
                            if (channel.equals(selectedChannel)) {
                                isFoundSelectedChannel = true; // jest w dodatkowych, nie w main
                                resultTvPackage.getFilteredTvPackage().getExtraTvPackagesWhichMeetCriteria().add(extraAvailableTvPackage);
                                break;
                            }
                    }
                    if (!isFoundSelectedChannel) { // nie ma w tym main, ani w dodatkowych, usun ten proponowany pakiet glowny z dodatkowymi
                        it.remove();
                    }
                }
            }
        }
        return resultTvPackages;
    }
}
