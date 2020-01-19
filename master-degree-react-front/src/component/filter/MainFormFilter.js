import React, {Component} from 'react';
import OperatorDataService from "../../service/OperatorDataService";
import OperatorChooser from "./OperatorChooser";
import PricePicker from "./PricePicker";
import ChannelDataService from "../../service/ChannelDataService";
import {Link, animateScroll as scroll} from "react-scroll";
import ChannelChooser from "./ChannelChooser";
import TermChooser from "./TermChooser";
import '../../css/MainFormFilter.css';


class MainFormFilter extends Component {
    constructor(props) {
        super(props);
        this.state = {
            operators: [],
            channelsObject: [],
        };
        this.refreshOperators = this.refreshOperators.bind(this);
        this.refreshChannels = this.refreshChannels.bind(this);

    }

    componentDidMount() {
        this.refreshChannels();
        this.refreshOperators();
    }

    refreshOperators() {
        OperatorDataService.retrieveAllOperators()
            .then(
                response => {
                    console.log(response);
                    this.setState({operators: response.data})
                }
            )
    }

    refreshChannels() {
        ChannelDataService.retrieveAllChannels()
            .then(
                response => {
                    console.log(response);
                    this.setState({channelsObject: response.data})
                }
            )
    }

    handleChange = input => event => {
        this.setState({[input]: event.target.value})
    };

    render() {
        const {operators, channelsObject} = this.state;
        const values = {operators, channelsObject};

        return (
            <div className={"container-fluid"} id={"mainFormFilter"}
                // style={{
                //     backgroundColor: 'blue',
                // }}
            >
                <OperatorChooser
                    values={values}
                />
                <PricePicker

                />

                <ChannelChooser
                    values={values}
                />

                <TermChooser
                    values={{values}}
                />

            </div>

        )
    }
}

export default MainFormFilter;