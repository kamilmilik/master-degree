import React, {Component} from 'react';
import {setResult} from "../../../../reducers/actions/actions";
import {connect} from "react-redux";
import CardResult from "./card_result/CardResult";
import HorizontalCardResult from "./card_result/HorizontalCardResult";
import "./ResultsComponent.css";

class ResultsComponent extends Component {

    constructor(props) {
        super(props);
    }

    render() {
        if (this.props.result.length >= 0) {
            return (
                <div className={'container-fluid'} id={"main-result-list-container"}>
                    <div className={"col-md-12"} id={"result-list"}>
                        {
                            this.props.result.map((resultTvPackage) => {
                                return (
                                    <HorizontalCardResult
                                        data={resultTvPackage}
                                    />
                                )
                            })
                        }
                    </div>
                </div>
            )
        } else {
            return (
                <div></div>
            )
        }
    }

}


const mapStateToProps = (state) => {
    return {
        result: state.formReducer.result
    }
};

const mapDispatchToProps = (dispatch) => {

    return {
        setResult: (result) => {
            dispatch(setResult(result))
        },
    }
};

export default connect(mapStateToProps, mapDispatchToProps)(ResultsComponent)