import React, {Component} from 'react';
import './ClearFiltersButton.css';
import {Button, Icon} from "semantic-ui-react";
import {Link} from "react-scroll";
import {connect} from "react-redux";
import {clearFilterElements, setResult} from "../../../../../reducers/actions/actions";

class ClearFiltersButton extends Component {

    constructor(props) {
        super(props);
        this.clearCriteria = this.clearCriteria.bind(this)
    }

    clearCriteria() {
        this.props.clearFilterElements();
    }

    render() {
        return (
            <div id={"clear-filter-button-container"}>
                <Link
                    activeClass="active"
                    to="section1"
                    spy={true}
                    smooth={true}
                    offset={-70}
                    duration={500}
                >
                    <Button id={"clear-button"}
                            icon labelPosition='left'
                            onClick={this.clearCriteria}
                    >
                        Wyczysc filtry
                        <Icon id={"clear-icon"}
                              name='eraser'
                        />
                    </Button>
                </Link>
            </div>
        );
    }
}

const mapStateToProps = (state) => {
    return {
        criteria: state.formReducer.criteria
    };
};

const mapDispatchToProps = (dispatch) => {

    return {
        clearFilterElements: () => {
            dispatch(clearFilterElements())
        },
    }
};

export default connect(mapStateToProps, mapDispatchToProps)(ClearFiltersButton)