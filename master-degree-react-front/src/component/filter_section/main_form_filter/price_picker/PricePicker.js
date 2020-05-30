import React, {Component} from 'react';
import './PricePicker.css';
import {setResult, setSelectedPrice} from "../../../../redux/actions/actions";
import {connect} from "react-redux";
import {MAX_PRICE_FILTER_VALUE} from "../Const";
import 'antd/dist/antd.css';
import {Slider} from 'antd';
import {CHOOSE_PRICE, CHOOSE_PRICE_DESC, CURRENCY} from "../../../../lang/pl";
import Container from "../../../custom_components/container/Container";

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
            <Container
                headerTitle={CHOOSE_PRICE}
                headerDescription={CHOOSE_PRICE_DESC}
                component={this.priceComponent()}
            />
        )
    }

    priceComponent() {
        return (
            <div className={"ui segment"} id={"price-segment"}>

                <Slider
                    max={MAX_PRICE_FILTER_VALUE}
                    defaultValue={MAX_PRICE_FILTER_VALUE}
                    value={this.props.criteria.price}
                    tipFormatter={value => `${value} ${CURRENCY}`}
                    onAfterChange={(e) => this.onPick(e)}
                    onChange={(e) => this.onPick(e)}
                    tooltipVisible
                />
            </div>
        );
    }
}

const mapStateToProps = (state) => {
    return {
        result: state.reducer.result,
        criteria: state.reducer.criteria,
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
