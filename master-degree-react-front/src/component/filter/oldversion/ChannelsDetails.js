import React, { Component } from 'react';
import { Form, Button } from 'semantic-ui-react';
import { throws } from 'assert';
import {Step, Stepper} from "react-form-stepper";

class ChannelsDetails extends Component{
    saveAndContinue = (e) => {
        e.preventDefault();
        this.props.nextStep();
    };

    back = (e) => {
        e.preventDefault(); // to stop the form from reloading each time we submit which is the default behaviour in forms.
        this.props.prevStep();
    };

    render(){
        const {values} = this.props;
        return(
            <div>
                <Stepper activeStep={values.step}>
                    <Step label="Children Step 1" />
                    <Step label="Children Step 2" />
                    <Step label="Children Step 3" />
                </Stepper>

                <Form color='blue' >
                    <h1 className="ui centered">Channels</h1>
                    <Form.Field>
                        <label>Age</label>
                        <input placeholder='channel'
                               onChange={this.props.handleChange('channelObject')}
                               defaultValue={values.channelObject}
                        />
                    </Form.Field>

                    <Button onClick={this.back}>Back</Button>
                    <Button onClick={this.saveAndContinue}>Save And Continue </Button>
                </Form>
            </div>
        )
    }
}

export default ChannelsDetails;