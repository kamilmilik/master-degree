import React, {Component} from 'react';
import 'react-image-picker/dist/index.css'
import './OperatorChooser.css';
import {setIsLoadingFilteredResult, setResult, setSelectedOperators} from "../../../../reducers/actions/actions";
import {connect} from "react-redux";
import OperatorDataService from "../../../../service/OperatorDataService";
import {trackPromise} from "react-promise-tracker";
import FilteredResultDataService from "../../../../service/FilteredResultDataService";


class OperatorChooser extends Component {

    constructor(props) {
        super(props);
        this.onPick = this.onPick.bind(this);
        this.handleImageClick = this.handleImageClick.bind(this)
    }



    onPick(images) {
        images.map((image) => {
            let selectedOperator = image.value;
            console.log("Selected ");
            console.log(selectedOperator);
        });
    }

    handleImageClick(operator) {
        const selectedArray = this.props.criteria.operators.slice();
        const indexOfSelected = selectedArray.indexOf(operator);
        if (!this.isOperatorSelected(indexOfSelected)) {
            selectedArray.splice(indexOfSelected, 1);
        } else {
            selectedArray.push(operator);
        }
        this.props.setSelectedOperators(selectedArray);
    }

    isOperatorSelected(indexOfSelected){
        return indexOfSelected === -1
    }

    render() {
        const {values} = this.props;
        this.props.criteria.operators.map((operator) => {
            console.log("Operator: sdf " + operator);
        });
        console.log("test");
        return (
            <div className={'container-fluid'} id={"main-operator-chooser-container"}>
                <div className={"col-md-12"} id={"operators-list"}>
                    <div className="mdb-lightbox no-margin">
                        {
                            values.operators.map((operator) => {
                                return (
                                    <img
                                        className={this.props.criteria.operators.indexOf(operator) !== -1 ? 'operator-image-clicked' : 'operator-image'}
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
        result: state.formReducer.result,
        isLoadingFilteredResult: state.formReducer.isLoadingFilteredResult,
        criteria: state.formReducer.criteria
    }
};

const mapDispatchToProps = (dispatch) => {


    return {
        setSelectedOperators: (selectedOperators) => {
            dispatch(setSelectedOperators(selectedOperators))
        },
        setResult: (result) => {
            dispatch(setResult(result))
        },
        setIsLoadingFilteredResult: (isLoadingFilteredResult) => {
            dispatch(setIsLoadingFilteredResult(isLoadingFilteredResult))
        },
    }
};

export default connect(mapStateToProps, mapDispatchToProps)(OperatorChooser)