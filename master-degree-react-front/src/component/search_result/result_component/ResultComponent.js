import React, {Component} from 'react';
import {setResult, setSelectedOperators} from "../../../reducers/actions/actions";
import {connect} from "react-redux";
import CardResult from "../card_result/CardResult";


class ResultComponent extends Component {

    constructor(props) {
        super(props);
    }

    // TODO Problem, nie wybrany operator, nie wybrane kanaly, zakres ceny caly, co pokazac jako result? Czy kazdy pakiet podstawowy z wszystkimi mozliwymi opcjami dodatkowymi(milion resultow) czy wszystkie pakiety podstawowe plus dodatkowe jako osobne resulty? Czy wyswietlic komunikat: Wybrales zbyt ogolne filtry prosze zawezic filtry?
    render() {
        console.log("Props " +  + " " + this.props.result);
        if (!(typeof this.props.result.resultTvPackages === 'undefined')) {
            return (
                <div className={'container-fluid'} id={"main-result-list-container"}>
                    <div className={"col-md-12"} id={"result-list"}>
                        <div className="mdb-lightbox no-margin">
                            {
                                this.props.result.resultTvPackages.map((resultTvPackage) => {
                                    return (
                                        <div> Zwracam: {resultTvPackage.operatorName}
                                            <CardResult
                                                data = {resultTvPackage}
                                            />
                                        </div>

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

export default connect(mapStateToProps, mapDispatchToProps)(ResultComponent)