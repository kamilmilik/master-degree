import React, {Component} from 'react';
import './SearchButton.css';
import {Button, Icon} from "semantic-ui-react";

class SearchButton extends Component {

    constructor(props) {
        super(props);
        this.state = {

        };
    }


    render() {
        const {values} = this.props;
        return (
            <div className={"container-fluid"} id={"main-search-container"}>
                <div id={"button-container"}>
                    <Button icon labelPosition='right'>
                        Wyszukaj oferty
                        <Icon name='search' />
                    </Button>
                </div>
            </div>
        );
    }
}

export default SearchButton;