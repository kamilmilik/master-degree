import React, {Component} from 'react';
import {Form, Button} from 'semantic-ui-react';
import {Step, Stepper} from 'react-form-stepper';
import ImagePicker from "react-image-picker";
import 'react-image-picker/dist/index.css'
import './OperatorChooser.css';


class OperatorChooser extends Component {

    constructor(props) {
        super(props);
        this.state = {
            operators: [],
            selectedOperators: [],
        };
        this.onPick = this.onPick.bind(this)
        this.handleImageClick = this.handleImageClick.bind(this)
    }


    onPick(images) {
        images.map((image) => {
            let selectedOperator = image.value;
            console.log("Selected ");
            console.log(selectedOperator);
        });
        this.setState({selectedOperators: images});
    }

    handleImageClick(operator) {
        const selectedArray = this.state.selectedOperators.slice();
        const indexOfSelected = selectedArray.indexOf(operator);
        if(indexOfSelected !== -1) {
            selectedArray.splice(indexOfSelected, 1);
        } else {
            selectedArray.push(operator);
        }
        this.setState({selectedOperators: selectedArray})
    }

    render() {
        const {values} = this.props;
        return (
            <div className={'container-fluid'} id={"main-operator-chooser-container"}>
                <div className={"col-md-12"} id={"operators-list"}>
                    <div className="mdb-lightbox no-margin">

                        {
                            values.operators.map((operator) => {
                                return (
                                    <img
                                        className={this.state.selectedOperators.indexOf(operator) !== -1 ? 'operator-image-clicked' : 'operator-image'}
                                        src={operator.imgSrc}
                                        onClick={() => this.handleImageClick(operator)}
                                    />
                                )
                            })
                        }
                    </div>
                </div>

                {/*<ImagePicker*/}
                {/*    images={values.operators.map((operator, indexOfOperator) => ({*/}
                {/*        src: operator.imgSrc, value: operator*/}
                {/*    }))}*/}
                {/*    onPick={this.onPick}*/}
                {/*    multiple*/}
                {/*/>*/}
            </div>

        )
    }
}

export default OperatorChooser;