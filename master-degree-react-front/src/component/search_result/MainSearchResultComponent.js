import React, {Component} from 'react';
import {connect} from "react-redux";
import './MainSearchResultComponent.css'


class MainSearchResultComponent extends Component {
    constructor(props) {
        super(props);
        this.state = {};
    }

    render() {

        return (
            <div className={"container-fluid"} id={"main-search-result-component-content"}>
                <div id={"result-container"}>
                    <div>Filters</div>
                    <div>Okres: {this.props.selectedTerm}</div>
                    <div>Cena: {this.props.selectedPrice}</div>
                    <div>Operator:
                        {this.props.selectedOperators.map((operator) => {
                                return  operator.name + ", "
                        })}
                    </div>
                    <div>Kanaly:
                        {this.props.selectedChannels.map((channel) =>{
                            return channel.name + ", "
                        })}
                    </div>
                </div>
            </div>

        )
    }
}

const mapStateToProps = (state) => {
    return {
        selectedTerm: state.formReducer.selectedTerm,
        selectedPrice: state.formReducer.selectedPrice,
        selectedOperators: state.formReducer.selectedOperators,
        selectedChannels: state.formReducer.selectedChannels
    };
};

const mapDispatchToProps = (dispatch) => {

    return {}
};

export default connect(mapStateToProps, mapDispatchToProps)(MainSearchResultComponent)