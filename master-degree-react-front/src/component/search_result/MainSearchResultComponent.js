import React, {Component} from 'react';
import {connect} from "react-redux";
import './MainSearchResultComponent.css'
import ResultComponent from './result_component/ResultComponent';


class MainSearchResultComponent extends Component {
    constructor(props) {
        super(props);
        this.state = {};
    }

    render() {

        let selectedChannelsByCategory = this.props.selectedChannelsByCategory
        return (
            <div className={"container-fluid"} id={"main-search-result-component-content"}>
                <div className={"result-container"}>
                    <div>Filters</div>
                    <div>Okres: {this.props.selectedTerm}</div>
                    <div>Cena: {this.props.selectedPrice[0]}zl - {this.props.selectedPrice[1]}zl </div>
                    <div>Operator:
                        {this.props.selectedOperators.map((operator) => {
                            return operator.name + ", "
                        })}
                    </div>
                    <div>Kanaly:
                        {
                            Object.keys(selectedChannelsByCategory).map(function (key) {
                                let selectedChannels = selectedChannelsByCategory[key];
                                return selectedChannels.map((channel) => {
                                    return channel.name + ", ";
                                })
                            })
                        }
                    </div>
                    <ResultComponent {...this.props} />
                </div>
            </div>

        );
    }
}

const mapStateToProps = (state) => {
    return {
        selectedTerm: state.formReducer.selectedTerm,
        selectedPrice: state.formReducer.selectedPrice,
        selectedOperators: state.formReducer.selectedOperators,
        selectedChannels: state.formReducer.selectedChannels,
        selectedChannelsByCategory: state.formReducer.selectedChannelsByCategory
    };
};

const mapDispatchToProps = (dispatch) => {

    return {}
};

export default connect(mapStateToProps, mapDispatchToProps)(MainSearchResultComponent)