import React, {Component} from 'react';
import {setResult, setSelectedOperators} from "../../../reducers/actions/actions";
import {connect} from "react-redux";
import CardResult from "../card_result/CardResult";
import FilteredResultDataService from "../../../service/FilteredResultDataService";


class ResultComponent extends Component {

    constructor(props) {
        super(props);
    }

    render() {
        console.log("Props " +  + " " + this.props.result);
        if (this.props.result.length >= 0) {
            return (
                <div className={'container-fluid'} id={"main-result-list-container"}>
                    <div className={"col-md-12"} id={"result-list"}>
                        <div className="mdb-lightbox no-margin">
                            {
                                this.props.result.map((resultTvPackage) => {
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