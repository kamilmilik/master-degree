import React, {Component} from 'react';
import 'react-image-picker/dist/index.css'
import '../../../../App.css';
import './ChannelChooser.css';
import {Button} from "semantic-ui-react";
import {
    setSelectedCategories,
    setSelectedChannels,
    setSelectedChannelsByCategory
} from "../../../../reducers/actions/actions";
import {connect} from "react-redux";

// TODO refactor, przeniesc czesc funkcjonalnosci moze do jakiejs klasy o nazwie service
class ChannelChooser extends Component {

    constructor(props) {
        super(props);
        this.state = {
            channelsObject: [],
            selectedChannels: [],
            selectedCategories: [],
        };
        this.handleImageClick = this.handleImageClick.bind(this);
        this.handleCategoryClick = this.handleCategoryClick.bind(this)
    }


    handleCategoryClick(channelObject) {
        const selectedCategoriesArray = this.props.selectedCategories.slice();
        const indexOfSelected = selectedCategoriesArray.indexOf(channelObject.categoryName);
        if(indexOfSelected !== -1) {
            selectedCategoriesArray.splice(indexOfSelected, 1);
            this.setAllChannelsFromCategorySelected(channelObject, false)
        } else {
            selectedCategoriesArray.push(channelObject.categoryName);
            this.setAllChannelsFromCategorySelected(channelObject, true)
        }
        // this.setState({selectedCategories: selectedArray})
        this.props.setSelectedCategories(selectedCategoriesArray);
    }

    setAllChannelsFromCategorySelected(channelObject, isSelectAll) {
        const categoryKey = channelObject.categoryName;
        const selectedChannelsByCategory = this.props.selectedChannelsByCategory;
        channelObject.channels.map((channel, i) => {
            if(typeof selectedChannelsByCategory[categoryKey] === 'undefined'){
                const selectedArray = [];
                selectedArray.push(channel);
                selectedChannelsByCategory[categoryKey] = selectedArray;
            }else {
                const indexOfSelected = selectedChannelsByCategory[categoryKey].indexOf(channel);
                if(isSelectAll){
                    if(indexOfSelected === -1) {
                        selectedChannelsByCategory[categoryKey].push(channel);
                    }
                } else {
                    if(indexOfSelected !== -1) {
                        selectedChannelsByCategory[categoryKey].splice(indexOfSelected, 1);
                    }
                }
            }
        });
        this.props.setSelectedChannelsByCategory(selectedChannelsByCategory);
    }

    handleImageClick(channel, channelObject) {
        const categoryKey = channelObject.categoryName;
        const selectedChannelsByCategory = this.props.selectedChannelsByCategory;
        if(typeof selectedChannelsByCategory[categoryKey] === 'undefined'){
            const selectedArray = [];
            selectedArray.push(channel);
            selectedChannelsByCategory[categoryKey] = selectedArray;
        } else {
            let selectedArray = selectedChannelsByCategory[categoryKey];
            const indexOfSelected = selectedArray.indexOf(channel);
            if(indexOfSelected !== -1) {
                selectedArray.splice(indexOfSelected, 1);
            } else {
                selectedArray.push(channel);
            }
        }
        console.log(selectedChannelsByCategory[categoryKey]);
        if(this.isAllChannelsSelectedInCategory(selectedChannelsByCategory, categoryKey, channelObject)){
            this.handleCategoryClick(channelObject)
        } else {
            this.deleteSelectedCategory(categoryKey);
        }
        this.props.setSelectedChannelsByCategory(selectedChannelsByCategory);
    }

    isAllChannelsSelectedInCategory(selectedChannelsByCategory, categoryKey, channelObject){
        return (selectedChannelsByCategory[categoryKey].length === channelObject.channels.length)
    }

    deleteSelectedCategory(categoryKey){
        const selectedCategoriesArray = this.props.selectedCategories.slice();
        const indexOfSelected = selectedCategoriesArray.indexOf(categoryKey);
        if(indexOfSelected !== -1){
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
                                        <Button basic id={this.props.selectedCategories.indexOf(channelObject.categoryName) !== -1 ? "category-button-clicked" : "category-button"}
                                        onClick={() => this.handleCategoryClick(channelObject)}>
                                            {channelObject.categoryName}
                                        </Button>
                                    </div>
                                    <div className={"col-md-12"} id={"category-channel-list"}>
                                        <div className="mdb-lightbox no-margin">

                                            {
                                                channelObject.channels.map((channel, i) => {
                                                    let classNameString = '';
                                                    if(!(channelObject.categoryName in selectedChannelsByCategory)){
                                                        classNameString = 'channel-image';
                                                    }else {
                                                        if(selectedChannelsByCategory[channelObject.categoryName].indexOf(channel) !== -1) {
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
        selectedChannels: state.formReducer.selectedChannels
    }
};

const mapDispatchToProps = (dispatch) => {

    return {
        setSelectedCategories: (selectedCategories) => {
            dispatch(setSelectedCategories(selectedCategories))
        },
        setSelectedChannels: (selectedChannels) => {
            dispatch(setSelectedChannels(selectedChannels))
        },
        setSelectedChannelsByCategory: (selectedChannelsByCategory) => {
            dispatch(setSelectedChannelsByCategory(selectedChannelsByCategory))
        },
    }
};

export default connect(mapStateToProps,mapDispatchToProps)(ChannelChooser)