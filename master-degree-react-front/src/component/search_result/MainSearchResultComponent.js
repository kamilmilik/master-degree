import React, {Component} from 'react';
import {connect} from "react-redux";
import './MainSearchResultComponent.css'
import ResultComponent from './result_component/ResultComponent';
import ResultDataService from "../../service/ResultDataService";
import {setIsLoadingFilteredResult, setResult, setSelectedPrice} from "../../reducers/actions/actions";
import PricePickerDataService from "../../service/PricePickerDataService";
import OperatorDataService from "../../service/OperatorDataService";
import {MAX_PRICE_FILTER_VALUE, MIN_PRICE_FILTER_VALUE} from "../filter/main_form_filter/Const";
import {trackPromise} from "react-promise-tracker";
import {LoadingSpinner} from "../loading-spinner/LoadingSpinner";


class MainSearchResultComponent extends Component {
    constructor(props) {
        super(props);
        this.state = {
            isLoadingFilteredResult:true
        };
    }

    componentDidMount() {
        this.sendAllSelectedDataAndAfterGetResult();
    }

    sendAllSelectedDataAndAfterGetResult(){
        const promises = [];
        promises.push(this.sendSelectedRangePrice(this.props.selectedPrice));
        this.props.selectedOperators.map((operator) => {
            promises.push(this.sendSelectedOperator(operator));
        });
        Promise.all(promises).then(values => {
            // this.props.setIsLoadingFilteredResult(true);
            this.getResult();
        });
        // TODO KM dopisac dodatkowe metody wysylajace kanaly i okres
    }

    sendSelectedRangePrice(range) {
        if(range.length === 0){
            range = [MIN_PRICE_FILTER_VALUE, MAX_PRICE_FILTER_VALUE];
            this.props.setSelectedPrice(range);
        }
        return PricePickerDataService.sendSelectedPrice(range)

    }

    sendSelectedOperator(operator) {
        return OperatorDataService.sendSelectedOperator(operator)
    }

    getResult() {
            ResultDataService.retrieveResult().then(response => {
                this.props.setResult(response.data);
                this.props.setIsLoadingFilteredResult(false);
            })
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
                        <div>Okres: {this.props.selectedTerm}</div>
                        <div>Cena: {this.props.selectedPrice[0]}zl - {this.props.selectedPrice[1]}zl</div>
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
                        <ResultComponent  />
                    </div>
                </div>

            );
        }
    }
}

const mapStateToProps = (state) => {
    return {
        selectedTerm: state.formReducer.selectedTerm,
        selectedPrice: state.formReducer.selectedPrice,
        selectedOperators: state.formReducer.selectedOperators,
        selectedChannels: state.formReducer.selectedChannels,
        selectedChannelsByCategory: state.formReducer.selectedChannelsByCategory,
        result: state.formReducer.result,
        isLoadingFilteredResult: state.formReducer.isLoadingFilteredResult
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