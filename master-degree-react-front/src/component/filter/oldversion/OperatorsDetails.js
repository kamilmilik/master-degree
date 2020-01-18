import React, {Component} from 'react';
import {Form, Button} from 'semantic-ui-react';
import {Step, Stepper} from 'react-form-stepper';
import ImagePicker from "react-image-picker";
import 'react-image-picker/dist/index.css'

class OperatorsDetails extends Component {

    constructor(props) {
        super(props);
        this.state = {
            operators: [],
            selectedOperators: [],
            validationError: ''
        };
        this.onPick = this.onPick.bind(this)
    }


    saveAndContinue = (e) => {
        e.preventDefault();
        this.props.nextStep()
    };

    onPick(images) {
        images.map((image) => {
            let selectedOperator = image.value;
            console.log("Selected ");
            console.log(selectedOperator);
        });
            this.setState({selectedOperators: images});
    }

    render() {
        const {values} = this.props;
        return (
            <div>
                <Stepper activeStep={values.step}>
                    <Step label="Children Step 1"/>
                    <Step label="Children Step 2"/>
                    <Step label="Children Step 3"/>
                </Stepper>

                <Form>
                    <h1 className="ui centered">Operators</h1>
                    <Form.Field>
                        <label>First Name</label>
                        <input
                            placeholder='id'
                            onChange={this.props.handleChange('id')}
                            defaultValue={values.id} // it saves value
                        />
                    </Form.Field>
                    <select value={this.state.selectedOperator}
                            onChange={(e) => this.setState({
                                selectedOperator: e.target.value,
                                validationError: e.target.value === "" ? "Select" : ""
                            })}>
                        {values.operators.map((operator) => <option key={operator.name}
                                                                    value={operator.name}>{operator.name}</option>)}
                    </select>
                    <div style={{color: 'red', marginTop: '5px'}}>
                        {this.state.validationError}
                    </div>
                    <div className={'image-select'} >
                        <ImagePicker
                            images={values.operators.map((operator, indexOfOperator) => ({
                                src: operator.imgSrc, value: operator
                            }))}
                            onPick={this.onPick}
                            multiple
                        />
                    </div>


                    <Button onClick={this.saveAndContinue}>Save And Continue </Button>
                </Form>
            </div>
        )
    }
}

export default OperatorsDetails;