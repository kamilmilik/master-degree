import React, {Component} from 'react';
import 'react-image-picker/dist/index.css'
import './OperatorChooser.css';
import {setIsLoadingFilteredResult, setResult, setSelectedOperators} from "../../../../reducers/actions/actions";
import {connect} from "react-redux";
import OperatorDataService from "../../../../service/OperatorDataService";
import ResultDataService from "../../../../service/ResultDataService";
import {trackPromise} from "react-promise-tracker";


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
        const selectedArray = this.props.selectedOperators.slice();
        const indexOfSelected = selectedArray.indexOf(operator);
        if (!this.isOperatorSelected(indexOfSelected)) {
            selectedArray.splice(indexOfSelected, 1);
            this.sendNotSelectedOperator(operator);
        } else {
            selectedArray.push(operator);
            this.sendSelectedOperator(operator)
        }
        this.props.setSelectedOperators(selectedArray);

    }

    isOperatorSelected(indexOfSelected){
        return indexOfSelected === -1
    }

    sendSelectedOperator(operator) {
        this.props.setIsLoadingFilteredResult(true);
        OperatorDataService.sendSelectedOperator(operator)
            .then(response => {
                console.log(response);
                this.getResult();
            })
    }

    sendNotSelectedOperator(operator) {
        this.props.setIsLoadingFilteredResult(true);
        OperatorDataService.sendNotSelectedOperator(operator)
            .then(response => {
                console.log(response);
                this.getResult();
            })
    }

    getResult() {
        ResultDataService.retrieveResult().then(response => {
            this.props.setResult(response.data);
            this.props.setIsLoadingFilteredResult(false);
        })
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
        selectedOperators: state.formReducer.selectedOperators,
        result: state.formReducer.result,
        isLoadingFilteredResult: state.formReducer.isLoadingFilteredResult
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