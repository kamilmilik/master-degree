import React, {Component} from 'react';
import 'react-image-picker/dist/index.css'
import './OperatorChooser.css';
import {setSelectedOperators} from "../../../../reducers/actions/actions";
import {connect} from "react-redux";


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
        // this.setState({selectedOperators: images});
        //     this.props.setSelectedOperators(image)
    }

    handleImageClick(operator) {
        const selectedArray = this.props.selectedOperators.slice();
        const indexOfSelected = selectedArray.indexOf(operator);
        if(indexOfSelected !== -1) {
            selectedArray.splice(indexOfSelected, 1);
        } else {
            selectedArray.push(operator);
        }
        // this.setState({selectedOperators: selectedArray})
        this.props.setSelectedOperators(selectedArray);
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
                                        className={this.props.selectedOperators.indexOf(operator) !== -1 ? 'operator-image-clicked' : 'operator-image'}
                                        src={operator.imgSrc}
                                        onClick={() => this.handleImageClick(operator)}
                                    />
                                )
                            })
                        }
                    </div>
                </div>
            </div>

        )
    }
}

const mapStateToProps = (state) => {
    return {
        operators: state.formReducer.operators,
        selectedOperators: state.formReducer.selectedOperators
    }
};

const mapDispatchToProps = (dispatch) => {

    return {
        setSelectedOperators: (selectedOperators) => {
            dispatch(setSelectedOperators(selectedOperators))
        },
    }
};

export default connect(mapStateToProps,mapDispatchToProps)(OperatorChooser)