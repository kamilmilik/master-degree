import React, {Component} from 'react';
import {Form, Button} from 'semantic-ui-react';
import {Step, Stepper} from 'react-form-stepper';
import ImagePicker from "react-image-picker";
import 'react-image-picker/dist/index.css'
import '../../css/OperatorChooser.css';


class OperatorChooser extends Component {

    constructor(props) {
        super(props);
        this.state = {
            operators: [],
            selectedOperators: [],
        };
        this.onPick = this.onPick.bind(this)
    }


    onPick(images) {
        images.map((image) => {
            let selectedOperator = image.value;
            console.log("Selected ");
            console.log(selectedOperator);
        });
        this.setState({selectedOperators: images});
    }

    render() {
        const {values} = this.props;
        return (
            <div className={'container-fluid'} id={"mainOperatorChooserContainer"}>
                <ImagePicker
                    images={values.operators.map((operator, indexOfOperator) => ({
                        src: operator.imgSrc, value: operator
                    }))}
                    onPick={this.onPick}
                    multiple
                />
            </div>

        )
    }
}

export default OperatorChooser;