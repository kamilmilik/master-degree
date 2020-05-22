import React, {Component} from 'react';
import OperatorDataService from "../../../service/OperatorDataService";
import OperatorChooser from "./operator_chooser/OperatorChooser";
import PricePicker from "./price_picker/PricePicker";
import ChannelDataService from "../../../service/ChannelDataService";
import ChannelChooser from "./channel_chooser/ChannelChooser";
import TermChooser from "./term_chooser/TermChooser";
import {setAllChannels, setAllOperators} from "../../../redux/actions/actions";
import {connect} from "react-redux";
import ButtonsContainer from "./result_buttons/ButtonsContainer";


class MainFormFilter extends Component {
    constructor(props) {
        super(props);
        this.state = {
            operatorsDto: [],
            channelsGroupByCategoryDto: [],
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
                    this.setState({operatorsDto: response.data})
                }
            )
    }

    refreshChannels() {
        ChannelDataService.retrieveAllChannels()
            .then(
                response => {
                    this.setState({channelsGroupByCategoryDto: response.data})
                }
            )
    }

    render() {
        const {operatorsDto, channelsGroupByCategoryDto} = this.state;
        const resDataDto = {operatorsDto, channelsGroupByCategoryDto};

        return (
            <div className={"container-fluid"} id={"main-formm-filter"}>
                <OperatorChooser
                    values={resDataDto}
                />
                <PricePicker/>

                <TermChooser/>

                <ChannelChooser
                    values={resDataDto}
                />

                <ButtonsContainer/>

            </div>

        )
    }
}

const mapStateToProps = (state) => {
    return {
        operatorsDto: state.reducer.operatorsDto,
        channelsGroupByCategoryDto: state.reducer.channelsGroupByCategoryDto
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

export default connect(mapStateToProps, mapDispatchToProps)(MainFormFilter)