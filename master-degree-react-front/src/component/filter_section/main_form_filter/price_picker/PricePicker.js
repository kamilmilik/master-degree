import React, {Component} from 'react';
import './PricePicker.css';
import { setResult, setSelectedPrice} from "../../../../reducers/actions/actions";
import {connect} from "react-redux";
import {
    DEFAULT_MIN_PRICE_FILTER_VALUE,
    MAX_PRICE_FILTER_VALUE,
    MIN_PRICE_FILTER_VALUE,
    DEFAULT_MAX_PRICE_FILTER_VALUE
} from "../Const";
import 'antd/dist/antd.css';
import {Slider, Switch} from 'antd';

class PricePicker extends Component {

    constructor(props) {
        super(props);
        this.onPick = this.onPick.bind(this)
    }

    onPick(price) {
        this.props.setSelectedPrice(price);
    }

    render() {
        return (
            <div className={"container-fluid"} id={"main-price-picker-container"}>
                <Slider
                    max={MAX_PRICE_FILTER_VALUE}
                    defaultValue={MAX_PRICE_FILTER_VALUE}
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
    }
};

export default connect(mapStateToProps, mapDispatchToProps)(PricePicker)
