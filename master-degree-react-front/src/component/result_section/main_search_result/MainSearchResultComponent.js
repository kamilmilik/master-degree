import React, {Component} from 'react';
import {connect} from "react-redux";
import ResultComponent from './result_component/ResultsComponent';
import "./MainSearchResultComponent.css"

class MainSearchResultComponent extends Component {
    constructor(props) {
        super(props);
        this.state = {};
    }

    render() {
        let selectedChannelsByCategory = this.props.selectedChannelsByCategory;
        return (
            <div className={"container-fluid"} id={"main-search-result-component-content"}>
                <div className={"result-container"}>
                    {/*<div>Filters</div>*/}
                    {/*<div>Okres: {this.props.criteria.term}</div>*/}
                    {/*<div>Cena: {this.props.criteria.price[0]}zl - {this.props.criteria.price[1]}zl</div>*/}
                    {/*<div>Operator:*/}
                    {/*    {this.props.criteria.operatorsId.map(operatorId => {*/}
                    {/*        return operatorId + ", "*/}
                    {/*    })}*/}
                    {/*    /!*{this.props.criteria.operators.map((operator) => {*!/*/}
                    {/*    /!*    return operator.name + ", "*!/*/}
                    {/*    /!*})}*!/*/}
                    {/*</div>*/}
                    {/*<div>Kanaly:*/}
                    {/*    {*/}
                    {/*        Object.keys(selectedChannelsByCategory).map(function (key) {*/}
                    {/*            let selectedChannels = selectedChannelsByCategory[key];*/}
                    {/*            return selectedChannels.map((channel) => {*/}
                    {/*                return channel.name + ", ";*/}
                    {/*            })*/}
                    {/*        })*/}
                    {/*    }*/}
                    {/*</div>*/}
                    {
                        this.props.isClearedFilters ? null : <ResultComponent/>
                    }
                </div>
            </div>
        );
    }
}

const mapStateToProps = (state) => {
    return {
        selectedChannelsByCategory: state.formReducer.selectedChannelsByCategory,
        criteria: state.formReducer.criteria,
        isClearedFilters: state.formReducer.isClearedFilters
    };
};

const mapDispatchToProps = (dispatch) => {

    return {}
};

export default connect(mapStateToProps, mapDispatchToProps)(MainSearchResultComponent)