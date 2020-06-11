import React, {Component} from 'react';
import {Button} from "semantic-ui-react";
import {connect} from "react-redux";
import {
    setResult,
    setSelectedCategories,
    setSelectedChannels,
    setSelectedChannelsByCategory
} from "../../../../../redux/actions/actions";
const CLICKED_CATEGORY_BUTTON = "category-button-clicked";
const NOT_CLICKED_CATEGORY_BUTTON = "category-button";
class CategoryButton extends Component{
    constructor(props) {
        super(props);
        this.onCategoryClick = this.onCategoryClick.bind(this)
    }

    onCategoryClick(categoryWithChannels) {
        const selectedCategoriesArray = this.copyElement(this.props.selectedCategories);
        const indexOfSelected = selectedCategoriesArray.indexOf(categoryWithChannels.categoryName);
        if (this.isElementExist(indexOfSelected)) {
            selectedCategoriesArray.splice(indexOfSelected, 1);
            this.setAllChannelsFromCategorySelectedOrNotSelected(categoryWithChannels, false)
        } else {
            selectedCategoriesArray.push(categoryWithChannels.categoryName);
            this.setAllChannelsFromCategorySelectedOrNotSelected(categoryWithChannels, true)
        }
        this.props.setSelectedCategories(selectedCategoriesArray);
    }

    setAllChannelsFromCategorySelectedOrNotSelected(categoryWithChannels, isSelectAll) {
        const categoryKey = categoryWithChannels.categoryName;
        const selectedChannelsByCategory = this.props.selectedChannelsByCategory;
        categoryWithChannels.channels.map((channel, i) => {
            if (!this.isAnyChannelInGivenCategorySelected(selectedChannelsByCategory, categoryKey)) {
                const selectedArray = [];
                selectedArray.push(channel.name);
                selectedChannelsByCategory[categoryKey] = selectedArray;
            } else {
                const indexOfSelected = selectedChannelsByCategory[categoryKey].indexOf(channel.name);
                if (isSelectAll) {
                    if (!this.isElementExist(indexOfSelected)) {
                        selectedChannelsByCategory[categoryKey].push(channel.name);
                    }
                } else {
                    if (this.isElementExist(indexOfSelected)) {
                        selectedChannelsByCategory[categoryKey].splice(indexOfSelected, 1);
                    }
                }
            }
        });
        this.props.setSelectedChannelsByCategory(selectedChannelsByCategory);
        this.props.setSelectedChannels(this.getArrayOfChannelsFromGivenMap(selectedChannelsByCategory));
    }

    render() {
        return (
            <div>
                <Button basic
                        id={this.props.selectedCategories.indexOf(this.props.categoryWithChannelsDto.categoryName) !== -1 ? CLICKED_CATEGORY_BUTTON : NOT_CLICKED_CATEGORY_BUTTON}
                        onClick={() => this.onCategoryClick(this.props.categoryWithChannelsDto)}
                >
                    {this.props.categoryWithChannelsDto.categoryName}
                </Button>
            </div>
        );
    }
}

const mapStateToProps = (state) => {
    return {
        selectedChannelsByCategory: state.reducer.selectedChannelsByCategory,
        channelsGroupByCategoryDto: state.reducer.channelsGroupByCategoryDto,
        selectedCategories: state.reducer.selectedCategories,
        result: state.reducer.result,
    }
};

const mapDispatchToProps = (dispatch) => {
    return {
        setSelectedCategories: (selectedCategories) => {
            dispatch(setSelectedCategories(selectedCategories))
        },
        setSelectedChannelsByCategory: (selectedChannelsByCategory) => {
            dispatch(setSelectedChannelsByCategory(selectedChannelsByCategory))
        },
        setResult: (result) => {
            dispatch(setResult(result))
        },
        setSelectedChannels: (selectedChannels) => {
            dispatch(setSelectedChannels(selectedChannels))
        },
    }
};

export default connect(mapStateToProps, mapDispatchToProps)(CategoryButton)