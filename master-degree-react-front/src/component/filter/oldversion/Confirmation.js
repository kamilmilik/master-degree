import React, { Component } from 'react';
import { Button, List } from 'semantic-ui-react';
import {Step, Stepper} from "react-form-stepper";

class Confirmation extends Component{
    saveAndContinue = (e) => {
        e.preventDefault();
        this.props.nextStep();
    };

    back = (e) => {
        e.preventDefault();
        this.props.prevStep();
    };

    render(){
        const {values: { step, id, operator, channelObject, price, term }} = this.props;

        return(
            <div>
                <Stepper activeStep={3}>
                    <Step label="Children Step 1" />
                    <Step label="Children Step 2" />
                    <Step label="Children Step 3" />
                </Stepper>
                <h1 className="ui centered">Confirm your Details</h1>
                <p>Click Confirm if the following details have been correctly entered</p>
                <List>
                    <List.Item>
                        <List.Icon name='users' />
                        <List.Content>id operator: {id}</List.Content>
                    </List.Item>
                    <List.Item>
                        <List.Icon name='users' />
                        <List.Content>Operator: {operator}</List.Content>
                    </List.Item>

                    <List.Item>
                        <List.Icon name='users' />
                        <List.Content>Channel: {channelObject}</List.Content>
                    </List.Item>
                    <List.Item>
                        <List.Icon name='users' />
                        <List.Content>Price: {price}, Term: {term}</List.Content>
                    </List.Item>
                </List>

                <Button onClick={this.back}>Back</Button>
                <Button onClick={this.saveAndContinue}>Confirm</Button>
            </div>
        )
    }
}

export default Confirmation;