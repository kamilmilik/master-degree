import React, {Component} from 'react';
import OperatorDataService from "../../../service/OperatorDataService";
import OperatorChooser from "./operator_chooser/OperatorChooser";
import PricePicker from "./price_picker/PricePicker";
import ChannelDataService from "../../../service/ChannelDataService";
import {Link, animateScroll as scroll} from "react-scroll";
import ChannelChooser from "./channel_chooser/ChannelChooser";
import TermChooser from "./term_chooser/TermChooser";
import './MainFormFilter.css';
import SearchButton from "./search/SearchButton";
import {setAllChannels, setAllOperators} from "../../../reducers/actions/actions";
import {connect} from "react-redux";


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
            <div className={"container-fluid"} id={"main-formM-filter"}
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

                {/*<SearchButton*/}
                {/*    values={values}*/}
                {/*/>*/}

            </div>

        )
    }
}

// export default MainFormFilter;

const mapStateToProps = (state) => {
    return {
        // To chyba bierze z tego reducera wpisane z palca dane i daje do items
        operators: state.formReducer.operators,
        channelsObject: state.formReducer.channelsObject
    }
};

const mapDispatchToProps = (dispatch) => {

    return {
        setAllOperators: (operators) => {
            dispatch(setAllOperators(operators))
        },
        setAllChannels: (channels) => {
            dispatch(setAllChannels(channels))
        },
    }
};

export default connect(mapStateToProps,mapDispatchToProps)(MainFormFilter)