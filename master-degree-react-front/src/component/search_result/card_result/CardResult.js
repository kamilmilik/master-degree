import React from 'react';
import {Card, CardContent, Feed, Icon, Image} from "semantic-ui-react";
import './CardResult.css';


export default function CardResult({data}) {
    let priceSumOfTvPackagesWhichMeetCriteria = 0;
    data.filteredTvPackage.extraTvPackagesWhichMeetCriteria.map((tvPackage) => {
        priceSumOfTvPackagesWhichMeetCriteria += tvPackage.price;
    })
    return (
        <Card>
            <CardContent id={"card-result-header"}>
                <Image
                    src={data.operatorImg}
                    floated='right'
                    size='small'
                />
                <Card.Header>
                    {data.operatorName}
                </Card.Header>
                <Card.Meta>
                    {data.filteredTvPackage.term}
                </Card.Meta>

            </CardContent>
            <CardContent>
                <Card.Description>
                    {data.filteredTvPackage.name}
                    {
                        + data.filteredTvPackage.extraTvPackagesWhichMeetCriteria.length !== 0 ? (
                            data.filteredTvPackage.extraTvPackagesWhichMeetCriteria.map((extraTvPackageWhichMeetCriteria, index) => {
                                return (
                                    <span>{(index ? ', ' : ' ') + extraTvPackageWhichMeetCriteria.name + " "}</span>
                                )
                            })

                        ) : (
                            <span> </span>
                        )
                    }

                    {data.filteredTvPackage.price + priceSumOfTvPackagesWhichMeetCriteria} zl {data.filteredTvPackage.type}
                </Card.Description>

            </CardContent>
            <Card.Content>
                <Card.Content
                    id={"card-result-extra-name"}
                    textAlign={"center"}>
                    <Icon name={'plus'}/> Dodatkowe dostepne pakiety:
                </Card.Content>
                {
                    data.filteredTvPackage.extraAvailableTvPackages.map((availableTvPackage) => {
                        return (
                            <Card.Content id={"card-result-extra-package"}>
                                <Feed>
                                    <Feed.Event>
                                        <Feed.Label image={availableTvPackage.imgSrc}/>
                                        <Feed.Content>
                                            <Feed.Meta>
                                                <Icon
                                                    name={"money bill alternate outline"}/> Cena: {availableTvPackage.price} zl</Feed.Meta>
                                            <Feed.Summary>
                                                <Icon name={"tv"}/> {availableTvPackage.name}
                                            </Feed.Summary>
                                        </Feed.Content>
                                    </Feed.Event>
                                </Feed>
                                <Feed>
                                    <Feed.Event>
                                        <Feed.Content>

                                            <Icon name={"film"}/> Kanaly:
                                            {
                                                availableTvPackage.channels.map((channel, index) => {
                                                    return (
                                                        <span>{(index ? ', ' : '') + channel.name}</span>
                                                    )
                                                })
                                            }
                                        </Feed.Content>
                                    </Feed.Event>
                                </Feed>
                            </Card.Content>

                        )
                    })
                }

            </Card.Content>
        </Card>
    );


}