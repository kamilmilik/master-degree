import React, {Component} from 'react';
import 'react-image-picker/dist/index.css'
import '../../../../App.css';
import './TermChooser.css';
import {setSelectedPrice, setSelectedTerm} from "../../../../reducers/actions/actions";
import {connect} from "react-redux";
import {NUMBER_OF_MONTHS_VALUE_1, NUMBER_OF_MONTHS_VALUE_2, NUMBER_OF_MONTHS_VALUE_3} from "../Const";

class TermChooser extends Component {

    constructor(props) {
        super(props);
        this.handleTermClick = this.handleTermClick.bind(this);
    }

    handleTermClick(term) {
        this.props.setSelectedTerm(term)
    }

    render() {
        const {values} = this.props;
        return (
            <div className={"container-fluid"} id={"main-term-chooser-container"}>
                <div className={"ui segment"} id={"term-main-segment"}>
                    <div className={"col-md-12"}>
                        <div className="mdb-lightbox no-margin">
                            <div id={"term-header"}>
                                <h4>Wybierz okres</h4>
                                Wybierz przez jaki okres ma trwac umowa
                            </div>
                            <div className={"ui segment"} id={"term-segment"}>
                                <div className="container">
                                    <div className="row">
                                        {/*wysrodkowac*/}
                                        <div className={"center-row"}>
                                        <div id={"term-text"} className="col-sm"
                                             onClick={() => this.handleTermClick(12)}>
                                            <a>
                                                brak
                                                <span>wymagan</span>
                                            </a>
                                        </div>
                                        <div id={"term-text"} className="col-sm"
                                             onClick={() => this.handleTermClick(12)}>
                                            <a>
                                                {NUMBER_OF_MONTHS_VALUE_1}
                                                <span>miesiecy</span>
                                            </a>
                                        </div>
                                        <div id={"term-text"} className="col-sm"
                                             onClick={() => this.handleTermClick(15)}>
                                            <a>
                                                {NUMBER_OF_MONTHS_VALUE_2}
                                                <span>miesiecy</span>
                                            </a>
                                        </div>
                                        <div id={"term-text"} className="col-sm"
                                             onClick={() => this.handleTermClick(24)}>
                                            <a>
                                                {NUMBER_OF_MONTHS_VALUE_3}
                                                <span>miesiace</span>
                                            </a>
                                        </div>
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
}

const mapStateToProps = (state) => {
    return {}
};

const mapDispatchToProps = (dispatch) => {

    return {
        setSelectedTerm: (term) => {
            dispatch(setSelectedTerm(term))
        },
    }
};

export default connect(mapStateToProps, mapDispatchToProps)(TermChooser)

// , bezterminowa opcja wybrania okresu trwania