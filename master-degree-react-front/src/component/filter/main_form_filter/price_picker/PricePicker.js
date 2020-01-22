import React, {Component} from 'react';
import Slider, {createSliderWithTooltip} from 'rc-slider';
import 'rc-slider/assets/index.css';
import './PricePicker.css';
import {setSelectedPrice} from "../../../../reducers/actions/actions";
import {connect} from "react-redux";
import {DEFAULT_PRICE_FILTER_VALUE, MAX_PRICE_FILTER_VALUE, MIN_PRICE_FILTER_VALUE} from "../Const";


// const Range = createSliderWithTooltip(Slider.Range);
const SliderWithTooltip = createSliderWithTooltip(Slider);

class PricePicker extends Component {

    constructor(props) {
        super(props);
        this.state = {
            maxPrice: ''
        };
        this.onPick = this.onPick.bind(this)
    }


    onPick(maxPrice) {
        // this.setState({maxPrice: maxPrice});
        this.props.setSelectedPrice(maxPrice)
    }

    render() {
        return (
            <div className={"container-fluid"} id={"main-price-picker-container"}>
                <SliderWithTooltip
                    min={MIN_PRICE_FILTER_VALUE}
                    max={MAX_PRICE_FILTER_VALUE}
                    defaultValue={DEFAULT_PRICE_FILTER_VALUE}
                    tipFormatter={value => `${value}zl`}
                    onChange={(e)=>this.onPick(e)}
                />
            </div>

        )
    }
}

const mapStateToProps = (state) => {
    return {
        maxPrice: state.formReducer.selectedPrice
    }
};

const mapDispatchToProps = (dispatch) => {

    return {
        setSelectedPrice: (price) => {
            dispatch(setSelectedPrice(price))
        },
    }
};

export default connect(mapStateToProps,mapDispatchToProps)(PricePicker)