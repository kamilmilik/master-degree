import React, {Component} from 'react';
import {Card, CardContent, Icon, Image} from "semantic-ui-react";
import './CardResult.css';


export default function CardResult({data}) {

    return (
        <Card>
            <CardContent id={"card-result_header"}>
                <Image
                    src={data.operatorImg}
                       floated='right'
                       size='small'
                />
                <Card.Header>
                    {data.operatorName}
                </Card.Header>
                <Card.Meta >
                    {data.mainTPackage.term}
                </Card.Meta>

            </CardContent>
            <CardContent>
                <Card.Description>
                    {data.mainTPackage.name}  {data.mainTPackage.price} {data.mainTPackage.type}
                </Card.Description>

            </CardContent>
            <Card.Content extra>
                <Icon name={'user'}/> 4 Friends
            </Card.Content>
        </Card>
    );
}