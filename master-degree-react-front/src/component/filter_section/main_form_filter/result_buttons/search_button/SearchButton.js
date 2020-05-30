import React, {Component} from 'react';
import './SearchButton.css';
import {Button, Icon} from "semantic-ui-react";
import {Link} from "react-scroll";
import {connect} from "react-redux";
import {trackPromise} from "react-promise-tracker";
import FilteredResultDataService from "../../../../../service/FilteredResultDataService";
import {setIsClearedFilters, setResult} from "../../../../../redux/actions/actions";
import {SEARCH_OFFERS} from "../../../../../lang/pl";

class SearchButton extends Component {

    constructor(props) {
        super(props);
        this.searchButtonAction = this.searchButtonAction.bind(this)
    }

    searchButtonAction() {
        this.props.setIsClearedFilters(false);
        this.retrieveFilteredResultByCriteria();
    }

    retrieveFilteredResultByCriteria() {
        trackPromise( // Loading spinner is associated with it.
            FilteredResultDataService.retrieveFilteredResultByCriteria(this.props.criteria)
                .then(response => {
                    console.log(response)
                    this.props.setResult(response);
                })
        )
    }

    render() {
        return (
            <div id={"search-button-container"}>
                <Link
                    activeClass="active"
                    to="result-container"
                    spy={true}
                    smooth={true}
                    offset={-70}
                    duration={500}
                >
                    <Button positive
                            id={"search-button"}
                            icon labelPosition='right'
                            onClick={this.searchButtonAction}
                    >
                        {SEARCH_OFFERS}
                        <Icon name='search'/>
                    </Button>
                </Link>
            </div>
        );
    }
}

const mapStateToProps = (state) => {
    return {
        result: state.reducer.result,
        criteria: state.reducer.criteria
    };
};

const mapDispatchToProps = (dispatch) => {

    return {
        setResult: (result) => {
            dispatch(setResult(result))
        },
        setIsClearedFilters: (isClearedFilters) => {
            dispatch(setIsClearedFilters(isClearedFilters))
        },
    }
};


export default connect(mapStateToProps, mapDispatchToProps)(SearchButton)