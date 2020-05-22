import React, {Component} from 'react';
import {setResult} from "../../../../redux/actions/actions";
import {connect} from "react-redux";
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
                        <div className={"ui segment"} id={"result-main-segment"}>
                            {
                                this.isEmptyResult() ?
                                    <div id={"no-result-text"}>
                                        <h3>Brak wynikow</h3>
                                        <h5>Zaden pakiet nie spelnia Twoich filtrow<br/>
                                            Popraw filtry</h5>
                                    </div>
                                    :
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
                </div>
            )
        } else {
            return (
                <div></div>
            )
        }
    }

    isEmptyResult() {
        return this.props.result.length === 0;
    }
}


const mapStateToProps = (state) => {
    return {
        result: state.reducer.result
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