import React, {Component} from 'react';
import {connect} from "react-redux";
import './MainSearchResultComponent.css'
import ResultComponent from './result_component/ResultComponent';
import {setIsLoadingFilteredResult, setResult, setSelectedPrice} from "../../reducers/actions/actions";
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
                // TODO KM blad gdy: szybkie przelaczanie miedzy operatorami ui czasami sie nie odswieza, raczej response ok
                // Czasami jak sa dwa szybkie requesty to jeden serwer zwrocil i drugi przetwarza a ui zamiast znaczka przetwarzania pokazuje stary widok, tymczasem serwer nie zwrocil jeszcze drguiego response
                this.props.setResult(response);
                this.props.setIsLoadingFilteredResult(false);
            } )
    }

    componentDidMount() {
        this.retrieveFilteredResultByCriteria(this.props.criteria);
    }

    componentDidUpdate(prevProps, prevState, snapshot) {
        if (this.isStateCriteriaValueChanged(prevProps)) {
            this.retrieveFilteredResultByCriteria(this.props.criteria);
        }
    }

    isStateCriteriaValueChanged(prevProps) {
        return prevProps.criteria !== this.props.criteria
    }

    render() {
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
                            {this.props.criteria.operatorsId.map(operatorId => {
                                return operatorId + ", "
                            })}
                            {/*{this.props.criteria.operators.map((operator) => {*/}
                            {/*    return operator.name + ", "*/}
                            {/*})}*/}
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