import React, {Component} from 'react';
import './SearchButton.css';
import {Button, Icon} from "semantic-ui-react";
import {Link} from "react-scroll";
import {connect} from "react-redux";
import {trackPromise} from "react-promise-tracker";
import FilteredResultDataService from "../../../../../service/FilteredResultDataService";
import {setResult} from "../../../../../reducers/actions/actions";

class SearchButton extends Component {

    constructor(props) {
        super(props);
        this.retrieveFilteredResultByCriteria = this.retrieveFilteredResultByCriteria.bind(this)
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
                            onClick={this.retrieveFilteredResultByCriteria}
                    >
                        Wyszukaj oferty
                        <Icon name='search'/>
                    </Button>
                </Link>
            </div>
        );
    }
}

const mapStateToProps = (state) => {
    return {
        result: state.formReducer.result,
        criteria: state.formReducer.criteria
    };
};

const mapDispatchToProps = (dispatch) => {

    return {
        setResult: (result) => {
            dispatch(setResult(result))
        },
    }
};


export default connect(mapStateToProps, mapDispatchToProps)(SearchButton)