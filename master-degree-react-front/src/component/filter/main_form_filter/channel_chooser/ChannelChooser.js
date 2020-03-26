import React, {Component} from 'react';
import 'react-image-picker/dist/index.css'
import '../../../../App.css';
import './ChannelChooser.css';
import {Button} from "semantic-ui-react";
import {
    setIsLoadingFilteredResult,
    setResult,
    setSelectedCategories,
    setSelectedChannels,
    setSelectedChannelsByCategory
} from "../../../../reducers/actions/actions";
import {connect} from "react-redux";
import ChannelDataService from "../../../../service/ChannelDataService";
import ResultDataService from "../../../../service/ResultDataService";

// TODO refactor, przeniesc czesc funkcjonalnosci moze do jakiejs klasy o nazwie service
class ChannelChooser extends Component {

    constructor(props) {
        super(props);
        this.handleImageClick = this.handleImageClick.bind(this);
        this.handleCategoryClick = this.handleCategoryClick.bind(this)
    }


    handleCategoryClick(channelObject) {
        console.log("Handle category click");
        const selectedCategoriesArray = this.props.selectedCategories.slice();
        const indexOfSelected = selectedCategoriesArray.indexOf(channelObject.categoryName);
        if (indexOfSelected !== -1) {
            selectedCategoriesArray.splice(indexOfSelected, 1);
            this.setAllChannelsFromCategorySelectedOrNotSelected(channelObject, false)
        } else {
            selectedCategoriesArray.push(channelObject.categoryName);
            this.setAllChannelsFromCategorySelectedOrNotSelected(channelObject, true)
        }
        this.props.setSelectedCategories(selectedCategoriesArray);
    }

    setAllChannelsFromCategorySelectedOrNotSelected(channelObject, isSelectAll) {
        const allSelectedOrAllNotSelectedChannels = [];

        const categoryKey = channelObject.categoryName;
        const selectedChannelsByCategory = this.props.selectedChannelsByCategory;
        channelObject.channels.map((channel, i) => {
            if (typeof selectedChannelsByCategory[categoryKey] === 'undefined') {
                const selectedArray = [];
                selectedArray.push(channel);
                allSelectedOrAllNotSelectedChannels.push(channel);
                selectedChannelsByCategory[categoryKey] = selectedArray;
            } else {
                const indexOfSelected = selectedChannelsByCategory[categoryKey].indexOf(channel);
                if (isSelectAll) {
                    if (indexOfSelected === -1) {
                        selectedChannelsByCategory[categoryKey].push(channel);
                        allSelectedOrAllNotSelectedChannels.push(channel);
                    }
                } else {
                    if (indexOfSelected !== -1) {
                        selectedChannelsByCategory[categoryKey].splice(indexOfSelected, 1);
                        allSelectedOrAllNotSelectedChannels.push(channel);
                    }
                }
            }
        });
        if(isSelectAll){
            this.sendAllSelectedChannels(allSelectedOrAllNotSelectedChannels);
        } else {
            this.sendAllNotSelectedChannels(allSelectedOrAllNotSelectedChannels);
        }
        this.props.setSelectedChannelsByCategory(selectedChannelsByCategory);
    }

    handleImageClick(channel, channelObject) {
        console.log("Handle image click");
        const categoryKey = channelObject.categoryName;
        const selectedChannelsByCategory = this.props.selectedChannelsByCategory;
        if (typeof selectedChannelsByCategory[categoryKey] === 'undefined') {
            const selectedArray = [];
            selectedArray.push(channel);
            this.sendSelectedChannel(channel);
            selectedChannelsByCategory[categoryKey] = selectedArray;
        } else {
            let selectedArray = selectedChannelsByCategory[categoryKey];
            const indexOfSelected = selectedArray.indexOf(channel);
            if (indexOfSelected !== -1) {
                selectedArray.splice(indexOfSelected, 1);
                this.sendNotSelectedChannel(channel);
            } else {
                selectedArray.push(channel);
                this.sendSelectedChannel(channel);
            }
        }
        console.log(selectedChannelsByCategory[categoryKey]);
        if (this.isAllChannelsSelectedInCategory(selectedChannelsByCategory, categoryKey, channelObject)) {
            this.handleCategoryClick(channelObject)
        } else {
            this.deleteSelectedCategory(categoryKey);
        }
        this.props.setSelectedChannelsByCategory(selectedChannelsByCategory);
    }


    sendSelectedChannel(channel) {
        ChannelDataService.sendSelectedChannel(channel)
            .then(response => {
                this.props.setIsLoadingFilteredResult(true);
                this.getResult();
            });
    }

    sendNotSelectedChannel(channel) {
        ChannelDataService.sendNotSelectedChannel(channel)
            .then(response => {
                this.props.setIsLoadingFilteredResult(true);
                this.getResult();
            });
    }

    sendAllSelectedChannels(channels) {
        this.props.setIsLoadingFilteredResult(true);
        ChannelDataService.sendAllSelectedChannels(channels)
            .then(response => {
                this.getResult();
            });
    }

    sendAllNotSelectedChannels(channels) {
        this.props.setIsLoadingFilteredResult(true);
        ChannelDataService.sendAllNotSelectedChannels(channels)
            .then(response => {
                this.getResult();
            });
    }

    getResult() {
        ResultDataService.retrieveResult().then(response => {
            this.props.setIsLoadingFilteredResult(false);
            this.props.setResult(response.data);
        });
    }

    isAllChannelsSelectedInCategory(selectedChannelsByCategory, categoryKey, channelObject) {
        return (selectedChannelsByCategory[categoryKey].length === channelObject.channels.length)
    }

    deleteSelectedCategory(categoryKey) {
        const selectedCategoriesArray = this.props.selectedCategories.slice();
        const indexOfSelected = selectedCategoriesArray.indexOf(categoryKey);
        if (indexOfSelected !== -1) {
            selectedCategoriesArray.splice(indexOfSelected, 1);
            this.props.setSelectedCategories(selectedCategoriesArray);
        }
    }

    render() {
        const {values} = this.props;
        const selectedChannelsByCategory = this.props.selectedChannelsByCategory;

        return (
            <div className={"container-fluid"} id={"main-channel-chooser-container"}>

                {
                    values.channelsObject.map((channelObject) => (

                        <div className={"mdb-lightbox no-margin"} id={"category-image-channel-section"}>
                            <ul className={"list-group"}>
                                <li className={"list-group-item"}>
                                    <div className={"category-type-title"}>
                                        <Button basic
                                                id={this.props.selectedCategories.indexOf(channelObject.categoryName) !== -1 ? "category-button-clicked" : "category-button"}
                                                onClick={() => this.handleCategoryClick(channelObject)}
                                        >
                                            {channelObject.categoryName}
                                        </Button>
                                    </div>
                                    <div className={"col-md-12"} id={"category-channel-list"}>
                                        <div className="mdb-lightbox no-margin">

                                            {
                                                channelObject.channels.map((channel, i) => {
                                                    let classNameString = '';
                                                    if (!(channelObject.categoryName in selectedChannelsByCategory)) {
                                                        classNameString = 'channel-image';
                                                    } else {
                                                        if (selectedChannelsByCategory[channelObject.categoryName].indexOf(channel) !== -1) {
                                                            classNameString = 'channel-image-clicked';
                                                        } else {
                                                            classNameString = 'channel-image';
                                                        }
                                                    }
                                                    return (
                                                        <img
                                                            className={classNameString}
                                                            src={channel.imgSrc}
                                                            onClick={() => this.handleImageClick(channel, channelObject)}
                                                        />
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
        channelsObject: state.formReducer.channelsObject,
        selectedCategories: state.formReducer.selectedCategories,
        selectedChannels: state.formReducer.selectedChannels,
        result: state.formReducer.result,
        isLoadingFilteredResult: state.formReducer.isLoadingFilteredResult
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
        setIsLoadingFilteredResult: (isLoadingFilteredResult) => {
            dispatch(setIsLoadingFilteredResult(isLoadingFilteredResult))
        },
    }
};

export default connect(mapStateToProps, mapDispatchToProps)(ChannelChooser)