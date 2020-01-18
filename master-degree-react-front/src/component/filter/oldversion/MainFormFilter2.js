import React, { Component } from 'react';
import Confirmation from './Confirmation';
import Success from './Success';
import OperatorsDetails from "./OperatorsDetails";
import ChannelsDetails from "./ChannelsDetails";
import OperatorDataService from "../../../service/OperatorDataService";

class MainFormFilter2 extends Component {
    constructor(props) {
        super(props);
        this.state = {
            step: 1,
            id: '',
            operators: [],
            channelObject: '',
            price: '',
            term: ''
        };
        this.refreshOperators = this.refreshOperators.bind(this)
    }

    componentDidMount() {
        this.refreshOperators()
    }

    refreshOperators(){
        OperatorDataService.retrieveAllOperators()
            .then(
                response => {
                    console.log(response);
                    this.setState({operators: response.data})
                }
            )
    }


    nextStep = () => {
        const {step} = this.state;
        this.setState({
            step: step + 1
        })
    };

    prevStep = () => {
        const {step} = this.state;
        this.setState({
            step: step - 1
        })
    };

    handleChange = input => event => {
        this.setState({[input]: event.target.value})
    };

    render(){
        const { step, id, operators, channelObject, price, term } = this.state;
        const values = { step, id, operators, channelObject, price, term };
        switch(step) {
            case 1:
                return <OperatorsDetails
                    nextStep={this.nextStep}
                    handleChange={this.handleChange}
                    values={values}
                />;
            case 2:
                return <ChannelsDetails
                    nextStep={this.nextStep}
                    prevStep={this.prevStep}
                    handleChange={this.handleChange}
                    values={values}
                />;
            case 3:
                return <Confirmation
                    nextStep={this.nextStep}
                    prevStep={this.prevStep}
                    values={values}
                />;
            case 4:
                return <Success />
        }
    }
}

export default MainFormFilter2;