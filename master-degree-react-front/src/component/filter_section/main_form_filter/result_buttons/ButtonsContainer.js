import React, {Component} from "react";
import {Button} from "semantic-ui-react";
import ClearFiltersButton from "./clear_filters_button/ClearFiltersButton";
import SearchButton from "./search_button/SearchButton";
import './ButtonsContainer.css'

class ButtonsContainer extends Component {
    render() {
        return (
            <div id={"button-group-container"}>
                <Button.Group id={"button-group"}>
                    <ClearFiltersButton/>
                    <SearchButton/>
                </Button.Group>
            </div>
        )
    }
}

export default ButtonsContainer;