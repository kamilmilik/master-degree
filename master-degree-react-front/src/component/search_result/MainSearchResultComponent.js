import React, {Component} from 'react';
import {connect} from "react-redux";
import './MainSearchResultComponent.css'
import ResultComponent from './result_component/ResultComponent';
import {setIsLoadingFilteredResult, setResult, setSelectedPrice} from "../../reducers/actions/actions";
import PricePickerDataService from "../../service/PricePickerDataService";
import OperatorDataService from "../../service/OperatorDataService";
import {MAX_PRICE_FILTER_VALUE, MIN_PRICE_FILTER_VALUE} from "../filter/main_form_filter/Const";
import {trackPromise} from "react-promise-tracker";
import {LoadingSpinner} from "../loading-spinner/LoadingSpinner";
import FilteredResultDataService from "../../service/FilteredResultDataService";


class MainSearchResultComponent extends Component {
    constructor(props) {
        super(props);
        this.state = {
            isLoadingFilteredResult:true
        };
    }

    retrieveFilteredResultByCriteria(criteria){
        this.props.setIsLoadingFilteredResult(true);
        FilteredResultDataService.retrieveFilteredResultByCriteria(criteria)
            .then(response =>{
                this.props.setIsLoadingFilteredResult(false);
                //         this.props.setResult(response.data);
            } )
    }

    render() {
        this.retrieveFilteredResultByCriteria(this.props.criteria);

        let selectedChannelsByCategory = this.props.selectedChannelsByCategory;
        if(this.props.isLoadingFilteredResult){
            return (
                <LoadingSpinner/>
            )
        } else {
            return (
                <div className={"container-fluid"} id={"main-search-result-component-content"}>
                    <div className={"result-container"}>
                        <div>Filters</div>
                        <div>Okres: {this.props.criteria.term}</div>
                        <div>Cena: {this.props.criteria.price[0]}zl - {this.props.criteria.price[1]}zl</div>
                        <div>Operator:
                            {this.props.criteria.operators.map((operator) => {
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
                        <ResultComponent  />
                    </div>
                </div>

            );
        }
    }
}

const mapStateToProps = (state) => {
    return {
        // selectedTerm: state.formReducer.selectedTerm,
        // selectedPrice: state.formReducer.selectedPrice,
        // selectedOperators: state.formReducer.selectedOperators,
        // selectedChannels: state.formReducer.selectedChannels,
        selectedChannelsByCategory: state.formReducer.selectedChannelsByCategory,
        result: state.formReducer.result,
        isLoadingFilteredResult: state.formReducer.isLoadingFilteredResult,
        criteria: state.formReducer.criteria
    };
};

const mapDispatchToProps = (dispatch) => {

    return {
        setSelectedPrice: (price) => {
            dispatch(setSelectedPrice(price))
        },
        setResult: (result) => {
            dispatch(setResult(result))
        },
        setIsLoadingFilteredResult: (isLoadingFilteredResult) => {
            dispatch(setIsLoadingFilteredResult(isLoadingFilteredResult))
        },
    }
};

export default connect(mapStateToProps, mapDispatchToProps)(MainSearchResultComponent)