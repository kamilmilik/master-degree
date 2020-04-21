import React, {Component} from 'react';
import './SearchButton.css';
import {Button, Icon} from "semantic-ui-react";
import {Link} from "react-scroll";
import {connect} from "react-redux";

class SearchButton extends Component {

    constructor(props) {
        super(props);
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
    return {};
};

const mapDispatchToProps = (dispatch) => {

    return {}
};

export default connect(mapStateToProps, mapDispatchToProps)(SearchButton)