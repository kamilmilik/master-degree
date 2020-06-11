import React, {Component} from 'react';
import './ClearFiltersButton.css';
import {Button, Icon} from "semantic-ui-react";
import {Link} from "react-scroll";
import {connect} from "react-redux";
import {clearFilterElements, setIsClearedFilters} from "../../../../../redux/actions/actions";
import {CLEAR_FILTERS} from "../../../../../lang/pl";

class ClearFiltersButton extends Component {

    constructor(props) {
        super(props);
        this.clearCriteria = this.clearCriteria.bind(this)
    }

    clearCriteria() {
        this.props.clearFilterElements();
        this.props.setIsClearedFilters(true);
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
                        {CLEAR_FILTERS}
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
        criteria: state.reducer.criteria
    };
};

const mapDispatchToProps = (dispatch) => {

    return {
        clearFilterElements: () => {
            dispatch(clearFilterElements())
        },
        setIsClearedFilters: (isClearedFilters) => {
            dispatch(setIsClearedFilters(isClearedFilters))
        },
    }
};

export default connect(mapStateToProps, mapDispatchToProps)(ClearFiltersButton)