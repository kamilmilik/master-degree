import React, {Component} from 'react';
import './SearchButton.css';
import {Button, Icon} from "semantic-ui-react";
import {Link} from "react-scroll";
import {connect} from "react-redux";

class SearchButton extends Component {

    constructor(props) {
        super(props);
        this.state = {};
    }

    render() {
        const {values} = this.props;
        return (
            <div className={"container-fluid"} id={"main-search-container"}>
                <div id={"button-container"}>
                        <Link
                            activeClass="active"
                            to="result-container"
                            spy={true}
                            smooth={true}
                            offset={-70}
                            duration={500}
                        >
                    <Button
                        icon labelPosition='right'
                    >
                        Wyszukaj oferty
                        <Icon name='search'/>
                    </Button>
                        </Link>
                </div>
            </div>
        );
    }
}

const mapStateToProps = (state) => {
    return {
        section1Ref: state.formReducer.section1Ref,
        section2Ref: state.formReducer.section2Ref,
    };
};

const mapDispatchToProps = (dispatch) => {

    return {}
};

export default connect(mapStateToProps, mapDispatchToProps)(SearchButton)