import React, {Component} from 'react';
import ImagePicker from "react-image-picker";
import 'react-image-picker/dist/index.css'
import App from "../../../../App";
import '../../../../App.css';
import './ChannelChooser.css';
import {Button} from "semantic-ui-react";

const imageList = ['https://pbs.twimg.com/profile_images/1016326195221352450/KCcdUN0v.jpg', 'https://pbs.twimg.com/profile_images/1016326195221352450/KCcdUN0v.jpg']

function getImage() {
    return {active: false};
}

class ChannelChooser extends Component {

    constructor(props) {
        super(props);
        this.state = {
            channelsObject: [],
            selectedChannels: [],
            selectedCategories: [],
            imageList: [],
            active: [],
        };
        this.handleImageClick = this.handleImageClick.bind(this)
        this.handleCategoryClick = this.handleCategoryClick.bind(this)
    }


    handleCategoryClick(channelObject) {
        const selectedArray = this.state.selectedCategories.slice();
        const indexOfSelected = selectedArray.indexOf(channelObject.categoryName);
        if(indexOfSelected !== -1) {
            selectedArray.splice(indexOfSelected, 1);
            this.setAllChannelsFromCategorySelected(channelObject, false)
        } else {
            selectedArray.push(channelObject.categoryName);
            this.setAllChannelsFromCategorySelected(channelObject, true)
        }
        this.setState({selectedCategories: selectedArray})
    }

    setAllChannelsFromCategorySelected(channelObject, isSelectAll) {
        const selectedArray = this.state.selectedChannels.slice();
        channelObject.channels.map((channel, i) => {
            const indexOfSelected = selectedArray.indexOf(channel);
            if(isSelectAll){
                if(indexOfSelected === -1) {
                    selectedArray.push(channel);
                }
            } else {
                if(indexOfSelected !== -1) {
                    selectedArray.splice(indexOfSelected, 1);
                }
            }
        });
            this.setState({selectedChannels: selectedArray})
    }

    handleImageClick(channel) {
        const selectedArray = this.state.selectedChannels.slice();
        const indexOfSelected = selectedArray.indexOf(channel);
        if(indexOfSelected !== -1) {
            selectedArray.splice(indexOfSelected, 1);
        } else {
            selectedArray.push(channel);
        }
        this.setState({selectedChannels: selectedArray})
    }

    render() {
        const {values} = this.props;
        return (
            <div className={"container-fluid"} id={"mainChannelChooserContainer"}>

                {
                    values.channelsObject.map((channelObject) => (

                        <div className={"mdb-lightbox no-margin"} id={"categoryImageChannelSection"}>
                            <ul className={"list-group"}>
                                <li className={"list-group-item"}>
                                    <div className={"category-type-title"}>
                                        <Button basic id={this.state.selectedCategories.indexOf(channelObject.categoryName) !== -1 ? "category-button-clicked" : "category-button"}
                                        onClick={() => this.handleCategoryClick(channelObject)}>
                                            {channelObject.categoryName}
                                        </Button>
                                    </div>
                                    <div className={"col-md-12"} id={"category-channel-list"}>
                                        <div className="mdb-lightbox no-margin">

                                            {
                                                channelObject.channels.map((channel, i) => {
                                                    return (
                                                        <img
                                                            className={this.state.selectedChannels.indexOf(channel) !== -1 ? 'channelsImageClicked' : 'channelsImage'}
                                                            src={channel.imgSrc}
                                                            onClick={() => this.handleImageClick(channel)}
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

export default ChannelChooser;
