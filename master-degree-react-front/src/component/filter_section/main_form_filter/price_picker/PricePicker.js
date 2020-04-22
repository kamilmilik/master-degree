import React, {Component} from 'react';
import './PricePicker.css';
import {setResult, setSelectedPrice} from "../../../../reducers/actions/actions";
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
                <div className={"ui segment"} id={"price-main-segment"}>
                    <div className={"col-md-12"}>
                        <div className={"mdb-lightbox no-margin"}>
                            <div id={"price-header"}>
                                <h4>Wybierz cene</h4>
                                Wybierz maksymalna cene pakietu telewizyjnego
                            </div>
                            <div className={"ui segment"} id={"price-segment"}>

                                <Slider
                                    max={MAX_PRICE_FILTER_VALUE}
                                    defaultValue={MAX_PRICE_FILTER_VALUE}
                                    value={this.props.criteria.price}
                                    tipFormatter={value => `${value}zl`}
                                    onAfterChange={(e) => this.onPick(e)}
                                    onChange={(e) => this.onPick(e)}
                                    tooltipVisible
                                />
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        )
    }
}

const mapStateToProps = (state) => {
    return {
        result: state.formReducer.result,
        criteria: state.formReducer.criteria,
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
