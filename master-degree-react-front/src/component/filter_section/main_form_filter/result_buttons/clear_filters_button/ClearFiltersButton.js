import React, {Component} from 'react';
import './ClearFiltersButton.css';
import {Button, Icon} from "semantic-ui-react";
import {Link} from "react-scroll";
import {connect} from "react-redux";

class ClearFiltersButton extends Component {

    constructor(props) {
        super(props);
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
    };
};

const mapDispatchToProps = (dispatch) => {

    return {}
};

export default connect(mapStateToProps, mapDispatchToProps)(ClearFiltersButton)