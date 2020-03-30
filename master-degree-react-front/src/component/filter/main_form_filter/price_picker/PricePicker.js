import React, {Component} from 'react';
import './PricePicker.css';
import {setIsLoadingFilteredResult, setResult, setSelectedPrice} from "../../../../reducers/actions/actions";
import {connect} from "react-redux";
import {
    DEFAULT_MIN_PRICE_FILTER_VALUE,
    MAX_PRICE_FILTER_VALUE,
    MIN_PRICE_FILTER_VALUE,
    DEFAULT_MAX_PRICE_FILTER_VALUE
} from "../Const";
import 'antd/dist/antd.css';
import {Slider, Switch} from 'antd';
import PricePickerDataService from "../../../../service/PricePickerDataService";
import FilteredResultDataService from "../../../../service/FilteredResultDataService";


class PricePicker extends Component {

    constructor(props) {
        super(props);
        this.onPick = this.onPick.bind(this)
    }

    onPick(range) {
        this.props.setSelectedPrice(range);
        this.sendSelectedRangePrice(range);
    }

    sendSelectedRangePrice(range) {
        this.props.setIsLoadingFilteredResult(true);
        PricePickerDataService.sendSelectedPrice(range)
            .then(response => {
                this.getResult();
            })
    }

    getResult() {
    }

    render() {
        return (
            <div className={"container-fluid"} id={"main-price-picker-container"}>
                <Slider
                    range
                    min={MIN_PRICE_FILTER_VALUE}
                    max={MAX_PRICE_FILTER_VALUE}
                    defaultValue={[MIN_PRICE_FILTER_VALUE, MAX_PRICE_FILTER_VALUE]}
                    tipFormatter={value => `${value}zl`}
                    onAfterChange={(e) => this.onPick(e)}
                    tooltipVisible
                />

            </div>

        )
    }
}

const mapStateToProps = (state) => {
    return {
        result: state.formReducer.result,
        isLoadingFilteredResult: state.formReducer.isLoadingFilteredResult
    }
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

export default connect(mapStateToProps, mapDispatchToProps)(PricePicker)
