import React, {Component} from 'react';
import 'react-image-picker/dist/index.css'
import '../../../../App.css';
import './TermChooser.css';
import {setSelectedTerm} from "../../../../redux/actions/actions";
import {connect} from "react-redux";
import {
    NUMBER_OF_MONTHS_VALUE_1,
    NUMBER_OF_MONTHS_VALUE_2,
    NUMBER_OF_MONTHS_VALUE_3,
    NUMBER_OF_MONTHS_VALUE_NONE
} from "../Const";
import {CHOOSE_TERM, CHOOSE_TERM_DESC, LACK, MONTHS, REQUIREMENT_WITH_LACK} from "../../../../lang/pl";

export const TERM_CLICKED = "term-text-clicked";
export const TERM_NOT_CLICKED = "term-text";

class TermChooser extends Component {

    constructor(props) {
        super(props);
        this.handleTermClick = this.handleTermClick.bind(this);
    }

    handleTermClick(term) {
        this.props.setSelectedTerm(term)
    }

    render() {
        let terms = [NUMBER_OF_MONTHS_VALUE_NONE, NUMBER_OF_MONTHS_VALUE_1, NUMBER_OF_MONTHS_VALUE_2, NUMBER_OF_MONTHS_VALUE_3]
        return (
            <div className={"container-fluid"} id={"main-term-chooser-container"}>
                <div className={"ui segment"} id={"term-main-segment"}>
                    <div className={"col-md-12"}>
                        <div className="mdb-lightbox no-margin">
                            <div id={"term-header"}>
                                <h4>{CHOOSE_TERM}</h4>
                                {CHOOSE_TERM_DESC}
                            </div>
                            <div className={"ui segment"} id={"term-segment"}>
                                <div className={"container"}>
                                    <div className={"row"}>
                                        <div className={"center-row"}>
                                            {
                                                terms.map((termValue) => {
                                                    let classNameString = parseInt(this.props.criteria.term, 10) === termValue ? TERM_CLICKED : TERM_NOT_CLICKED;
                                                    let termTextValue = this.isNoTermRequirements(termValue) ? `${LACK}` : termValue.toString()
                                                    let termText = this.isNoTermRequirements(termValue) ? `${REQUIREMENT_WITH_LACK}` : `${MONTHS}`
                                                    let style = this.isTheLastElementOfTerm(terms, termValue) ? {borderRight: "unset"} : {borderRight: "1px solid rgba(34, 36, 38, .15)"}
                                                    return (
                                                        <div id={classNameString} className={"col-sm"}
                                                             onClick={() => this.handleTermClick(termValue)}
                                                             style={style}
                                                        >
                                                            <a>
                                                                {termTextValue}
                                                                <span>{termText}</span>
                                                            </a>
                                                        </div>
                                                    )
                                                })
                                            }
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }

    isNoTermRequirements(termValue) {
        return termValue === NUMBER_OF_MONTHS_VALUE_NONE;
    }

    isTheLastElementOfTerm(terms, termValue) {
        return terms[terms.length - 1] === termValue;
    }
}

const mapStateToProps = (state) => {
    return {
        criteria: state.reducer.criteria
    }
};

const mapDispatchToProps = (dispatch) => {

    return {
        setSelectedTerm: (term) => {
            dispatch(setSelectedTerm(term))
        },
    }
};

export default connect(mapStateToProps, mapDispatchToProps)(TermChooser)
