import React, {Component} from 'react';
import Slider, {createSliderWithTooltip} from 'rc-slider';
import 'rc-slider/assets/index.css';
import '../../css/PricePicker.css';


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
        this.setState({maxPrice: maxPrice});
    }

    render() {
        return (
            <div className={"container-fluid"} id={"mainPricePickerContainer"}>
                <SliderWithTooltip
                    min={0}
                    max={400}
                    defaultValue={200}
                    tipFormatter={value => `${value}zl`}
                    onChange={this.onPick}
                />
            </div>

        )
    }
}

export default PricePicker;