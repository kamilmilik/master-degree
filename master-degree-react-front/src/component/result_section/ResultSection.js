import React, {Component} from "react";
import {setResult} from "../../reducers/actions/actions";
import {connect} from "react-redux";
import {trackPromise} from "react-promise-tracker";
import FilteredResultDataService from "../../service/FilteredResultDataService";
import {LoadingSpinner} from "../loading-spinner/LoadingSpinner";
import MainSearchResultComponent from "./main_search_result/MainSearchResultComponent";

class ResultSection extends Component {

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