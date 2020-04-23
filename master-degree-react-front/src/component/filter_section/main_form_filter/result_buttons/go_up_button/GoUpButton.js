import React, {Component} from "react";
import {Link} from "react-scroll";
import {Button, Icon} from "semantic-ui-react";
import './GoUpButton.css';
import {connect} from "react-redux";
import App from "../../../../../App";

class GoUpButton extends Component {

    render() {
        return(
            <div >
                <Link
                    activeClass="active"
                    to="section1"
                    spy={true}
                    smooth={true}
                    offset={-70}
                    duration={500}
                >
                    <Button basic
                            id={"go-up-button"}
                            icon labelPosition='left'
                    >
                        Wroc do filtrow
                        <Icon  name='arrow up'/>
                    </Button>
                </Link>
            </div>
        )
    }
}

export default GoUpButton;