import React from 'react';
import './HorizontalCardResult.css';
import {Image} from "react-native-web";
import {Button, Icon} from "semantic-ui-react";
import {
    CHANNELS,
    CHECK,
    CURRENCY,
    EXTRA_AVAILABLE_TV_PACKAGES,
    EXTRA_PACKAGES,
    FROM_EXTRA_TV_PACKAGES,
    FROM_MAIN_TV_PACKAGE,
    MONTH_SHORTCUT,
    MONTHS,
    NO_EXTRA_AVAILABLE_TV_PACKAGES
} from "../../../../../lang/pl";


export default function HorizontalCardResult({data}) {
    let priceSumOfTvPackagesWhichMeetCriteria = 0;
    let numberOfChannelsFromMeetCriteriaPackages = 0;
    data.filteredTvPackage.extraTvPackagesWhichMeetCriteria.map((tvPackage) => {
        priceSumOfTvPackagesWhichMeetCriteria += tvPackage.price;
        numberOfChannelsFromMeetCriteriaPackages += tvPackage.channels.length;
    })
    let price = (data.filteredTvPackage.price + priceSumOfTvPackagesWhichMeetCriteria).toFixed(2)
    let hasMeetCriteriaExtraTvPackages = data.filteredTvPackage.extraTvPackagesWhichMeetCriteria.length !== 0;
    let hasExtraTvPackages = data.filteredTvPackage.extraAvailableTvPackages.length !== 0;
    return (
        <div id={"card-row"} className="row">
            <div id={"card-column-price"} className="column">
                <div className="card">
                    <div className="grid-container-price">
                        <div className="grid-item-price">
                            <div id={"price-term-result-container"}>
                                <h1 id={"price-result-text"}>
                                    {price}<span> {CURRENCY}/{MONTH_SHORTCUT}</span>
                                </h1>
                                <div id={"term-info-result-container"}>
                                    czas {data.filteredTvPackage.term} {MONTHS}
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div id={"card-column-operator"} className="column">
                <div className="card">
                    <div className="grid-container-operator-package">
                        <div className="grid-item-operator-package">
                            <div className={"result-operator-image-container"}>
                                <Image
                                    size={"medium"}
                                    style={{width: 70, height: 35}}
                                    source={{uri: data.operatorImg}}
                                    resizeMode="contain"
                                />
                            </div>
                            <div className={"extra-package-container"}>
                                <h5 id={"extra-package-name-text"}
                                    style={!hasMeetCriteriaExtraTvPackages ? {margin: "unset"} : {}}
                                >
                                    {data.filteredTvPackage.name}
                                </h5>
                                {hasMeetCriteriaExtraTvPackages ? <span>{EXTRA_PACKAGES}:</span> : null}
                                <h6 id={"extra-packages-text"}>
                                    {
                                        hasMeetCriteriaExtraTvPackages ? (
                                            data.filteredTvPackage.extraTvPackagesWhichMeetCriteria.map((extraTvPackageWhichMeetCriteria, index) => {
                                                return (
                                                    <div>
                                                        <span>+ {extraTvPackageWhichMeetCriteria.name}</span>
                                                    </div>
                                                )
                                            })

                                        ) : (
                                            <span> </span>
                                        )
                                    }
                                </h6>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div id={"card-column-channels"} className="column">
                <div className="card">
                    <div className="grid-container-channels">
                        <div className="grid-item-channels">
                            <div id={"channels-result-container"}>
                                <div>
                                    <h1 id={"channels-result-text"}>
                                        {data.filteredTvPackage.channels.length}<span> {CHANNELS}</span>
                                    </h1>
                                </div>
                                <div>
                                    <span>{FROM_MAIN_TV_PACKAGE}:</span>
                                </div>
                            </div>
                            {
                                hasMeetCriteriaExtraTvPackages ?
                                    <div id={"channels-result-container"}>
                                        <div><h2 id={"add-extra-channels-length-text"}>+</h2></div>
                                        <div>
                                            <h1 id={"channels-result-text"}>
                                                {numberOfChannelsFromMeetCriteriaPackages}<span> {CHANNELS}</span>
                                            </h1>
                                        </div>
                                        <div>
                                            <span>{FROM_EXTRA_TV_PACKAGES}</span>
                                        </div>
                                    </div> : null
                            }
                        </div>
                    </div>
                </div>
            </div>

            <div id={"card-column-extra-packages"} className="column">
                <div className="card">
                    <div className="grid-container-extra-packages">
                        <div className="grid-item-extra-packages">
                            {hasExtraTvPackages ?
                                <div id={"extra-package-main-container"}>
                                    <h5 id={"extra-packages-info-text"}>{EXTRA_AVAILABLE_TV_PACKAGES}:</h5>
                                    {
                                        data.filteredTvPackage.extraAvailableTvPackages.map((availableTvPackage) => {
                                            return (
                                                <div id={"extra-package-container"}>
                                                    <div id={"result-extra-package-image-container"}>
                                                        <Image
                                                            size={"small"}
                                                            style={{width: 30}}
                                                            source={{uri: availableTvPackage.imgSrc}}
                                                            resizeMode="contain"
                                                        />
                                                    </div>
                                                    <div id={"result-extra-package-text-container"}>
                                                        <div>{availableTvPackage.name} | <b>{availableTvPackage.price} zl</b>
                                                        </div>
                                                    </div>
                                                </div>
                                            )
                                        })
                                    }
                                </div>
                                :
                                <h5 id={"extra-packages-info-text"}>{NO_EXTRA_AVAILABLE_TV_PACKAGES}</h5>
                            }
                        </div>
                    </div>
                </div>
            </div>

            <div id={"card-column-link"} className="column">
                <div className="card">
                    <div className="grid-container-link">
                        <div className="grid-item-link">
                            <div id={"link-result-container"}>
                                <a href={data.filteredTvPackage.link}>
                                    <Button id={"link-button"}>
                                        <Icon id={"link-button-icon"} name='arrow alternate circle right outline'/>
                                        <div id={"link-button-text"}>{CHECK}</div>
                                    </Button>
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    );
}