import React, {Component} from 'react';
import 'react-image-picker/dist/index.css'
import '../../../../App.css';
import './ChannelChooser.css';
import {Button} from "semantic-ui-react";
import {
    setResult,
    setSelectedCategories,
    setSelectedChannels,
    setSelectedChannelsByCategory
} from "../../../../reducers/actions/actions";
import {connect} from "react-redux";
import Tooltip from "@material-ui/core/Tooltip";
import withStyles from "@material-ui/core/styles/withStyles";
import Typography from "@material-ui/core/Typography";

const CLICKED_CATEGORY_BUTTON = "category-button-clicked";
const NOT_CLICKED_CATEGORY_BUTTON = "category-button";
const TooltipMedium = withStyles((theme) => ({
    tooltip: {
        backgroundColor: '#f5f5f9',
        color: 'rgba(0, 0, 0, 0.87)',
        maxWidth: 220,
        fontSize: theme.typography.pxToRem(16),
        border: '1px solid #dadde9',
    },
}))(Tooltip);

class ChannelChooser extends Component {
    constructor(props) {
        super(props);
        this.onChannelClick = this.onChannelClick.bind(this);
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
                selectedArray.push(channel);
                selectedChannelsByCategory[categoryKey] = selectedArray;
            } else {
                const indexOfSelected = selectedChannelsByCategory[categoryKey].indexOf(channel);
                if (isSelectAll) {
                    if (!this.isElementExist(indexOfSelected)) {
                        selectedChannelsByCategory[categoryKey].push(channel);
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

    isAnyChannelInGivenCategorySelected(selectedChannelsByCategory, category) {
        return typeof selectedChannelsByCategory[category] !== 'undefined'
    }

    onChannelClick(channel, categoryWithChannels) {
        const clickedChannelCategoryName = categoryWithChannels.categoryName;
        const mapOfSelectedChannelsByCategory = this.props.selectedChannelsByCategory;
        if (this.isAnyChannelFromGivenCategoryClickedBefore(mapOfSelectedChannelsByCategory, clickedChannelCategoryName)) {
            this.initListAndPushToGivenCategoryFirstSelectedChannel(mapOfSelectedChannelsByCategory, clickedChannelCategoryName, channel);
        } else {
            let arrayOfSelectedChannelsAssociatedToCategory = mapOfSelectedChannelsByCategory[clickedChannelCategoryName];
            const indexOfSelected = arrayOfSelectedChannelsAssociatedToCategory.indexOf(channel);
            if (this.isElementExist(indexOfSelected)) {
                arrayOfSelectedChannelsAssociatedToCategory.splice(indexOfSelected, 1);
            } else {
                arrayOfSelectedChannelsAssociatedToCategory.push(channel);
            }
        }
        console.log(mapOfSelectedChannelsByCategory[clickedChannelCategoryName]);
        if (this.isAllChannelsHasBeenSelectedByUserInCategory(mapOfSelectedChannelsByCategory, clickedChannelCategoryName, categoryWithChannels)) {
            this.onCategoryClick(categoryWithChannels)
        } else {
            this.deleteSelectedCategory(clickedChannelCategoryName);
        }
        this.props.setSelectedChannelsByCategory(mapOfSelectedChannelsByCategory);
        this.props.setSelectedChannels(this.getArrayOfChannelsFromGivenMap(mapOfSelectedChannelsByCategory));
    }

    getArrayOfChannelsFromGivenMap(map) {
        let array = [];
        for (let key in map) {
            let channels = map[key];
            array.push(...channels);
        }
        return array;
    }

    initListAndPushToGivenCategoryFirstSelectedChannel(selectedChannelsByCategoryMap, clickedChannelCategoryName, channel) {
        const arrayOfSelectedChannelsAssociatedToCategory = [];
        arrayOfSelectedChannelsAssociatedToCategory.push(channel);
        selectedChannelsByCategoryMap[clickedChannelCategoryName] = arrayOfSelectedChannelsAssociatedToCategory;
    }

    isElementExist(element) {
        return element !== -1;
    }

    isAnyChannelFromGivenCategoryClickedBefore(selectedChannelsByCategory, clickedChannelCategoryName) {
        return typeof selectedChannelsByCategory[clickedChannelCategoryName] === 'undefined'
    }

    isAllChannelsHasBeenSelectedByUserInCategory(selectedChannelsByCategory, categoryKey, categoryWithChannels) {
        return (selectedChannelsByCategory[categoryKey].length === categoryWithChannels.channels.length)
    }

    deleteSelectedCategory(categoryKey) {
        const selectedCategoriesArray = this.copyElement(this.props.selectedCategories);
        const indexOfSelected = selectedCategoriesArray.indexOf(categoryKey);
        if (this.isElementExist(indexOfSelected)) {
            selectedCategoriesArray.splice(indexOfSelected, 1);
            this.props.setSelectedCategories(selectedCategoriesArray);
        }
    }

    copyElement(elementToCopy) {
        return elementToCopy.slice()
    }

    render() {
        const {values} = this.props;
        const selectedChannelsByCategory = this.props.selectedChannelsByCategory;
        return (
            <div className={"container-fluid"} id={"main-channel-chooser-container"}>
                {
                    values.channelsGroupByCategoryDto.map((categoryWithChannelsDto) => (
                        <div className={"mdb-lightbox no-margin"} id={"category-image-channel-section"}>
                            <ul className={"list-group"}>
                                <li className={"list-group-item"}>
                                    <div className={"category-type-title"}>
                                        <Button basic
                                                id={this.props.selectedCategories.indexOf(categoryWithChannelsDto.categoryName) !== -1 ? CLICKED_CATEGORY_BUTTON : NOT_CLICKED_CATEGORY_BUTTON}
                                                onClick={() => this.onCategoryClick(categoryWithChannelsDto)}
                                        >
                                            {categoryWithChannelsDto.categoryName}
                                        </Button>
                                    </div>
                                    <div className={"col-md-12"} id={"category-channel-list"}>
                                        <div className="mdb-lightbox no-margin">
                                            {
                                                categoryWithChannelsDto.channels.map((channel, i) => {
                                                    let classNameString = '';
                                                    if (!(categoryWithChannelsDto.categoryName in selectedChannelsByCategory)) {
                                                        classNameString = 'channel-image';
                                                    } else {
                                                        if (selectedChannelsByCategory[categoryWithChannelsDto.categoryName].indexOf(channel) !== -1) {
                                                            classNameString = 'channel-image-clicked';
                                                        } else {
                                                            classNameString = 'channel-image';
                                                        }
                                                    }
                                                    return (
                                                        <TooltipMedium
                                                            title={
                                                                <React.Fragment>
                                                                    <Typography
                                                                        color="inherit">{channel.name} </Typography>
                                                                </React.Fragment>}
                                                        >
                                                            <img
                                                                className={classNameString}
                                                                src={channel.imgSrc}
                                                                onClick={() => this.onChannelClick(channel, categoryWithChannelsDto)}
                                                            />
                                                        </TooltipMedium>
                                                    )
                                                })
                                            }
                                        </div>
                                    </div>
                                </li>
                            </ul>
                        </div>
                    ))
                }
            </div>
        );
    }
}

const mapStateToProps = (state) => {
    return {
        selectedChannelsByCategory: state.formReducer.selectedChannelsByCategory,
        channelsGroupByCategoryDto: state.formReducer.channelsGroupByCategoryDto,
        selectedCategories: state.formReducer.selectedCategories,
        result: state.formReducer.result,
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

export default connect(mapStateToProps, mapDispatchToProps)(ChannelChooser)