import React, {Component} from "react";
import {setResult} from "../../reducers/actions/actions";
import {connect} from "react-redux";
import {trackPromise} from "react-promise-tracker";
import FilteredResultDataService from "../../service/FilteredResultDataService";
import {LoadingSpinner} from "../loading-spinner/LoadingSpinner";
import MainSearchResultComponent from "./main_search_result/MainSearchResultComponent";

class ResultSection extends Component {
    retrieveFilteredResultByCriteria(criteria) {
        // TODO KM blad gdy: szybkie przelaczanie miedzy operatorami ui czasami sie nie odswieza, raczej response ok
        // Czasami jak sa dwa szybkie requesty to jeden serwer zwrocil i drugi przetwarza a ui zamiast znaczka przetwarzania pokazuje stary widok, tymczasem serwer nie zwrocil jeszcze drguiego response
        trackPromise( // Loading spinner is associated with it.
            FilteredResultDataService.retrieveFilteredResultByCriteria(criteria)
                .then(response => {
                    console.log(response)
                    this.props.setResult(response);
                })
        )
    }

    componentDidMount() {
        this.retrieveFilteredResultByCriteria(this.props.criteria);
    }

    componentDidUpdate(prevProps, prevState, snapshot) {
        this.onCriteriaChange(prevProps);
    }

    onCriteriaChange(prevProps) {
        if (this.isCriteriaStateChanged(prevProps)) {
            this.retrieveFilteredResultByCriteria(this.props.criteria);
        }
    }

    isCriteriaStateChanged(prevProps) {
        return prevProps.criteria !== this.props.criteria
    }

    render() {
        return (
            <LoadingSpinner Component={MainSearchResultComponent}/>
        );
    }
}

const mapStateToProps = (state) => {
    return {
        result: state.formReducer.result,
        criteria: state.formReducer.criteria
    };
};

const mapDispatchToProps = (dispatch) => {

    return {
        setResult: (result) => {
            dispatch(setResult(result))
        },
    }
};


export default connect(mapStateToProps, mapDispatchToProps)(ResultSection)