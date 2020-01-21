import React, {Component} from 'react';
import ImagePicker from "react-image-picker";
import 'react-image-picker/dist/index.css'
import App from "../../../../App";
import '../../../../App.css';
import './ChannelChooser.css';
import {Button} from "semantic-ui-react";
import {setSelectedCategories, setSelectedChannels} from "../../../../reducers/actions/actions";
import {connect} from "react-redux";
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
        const selectedArray = this.props.selectedCategories.slice();
        const indexOfSelected = selectedArray.indexOf(channelObject.categoryName);
        if(indexOfSelected !== -1) {
            selectedArray.splice(indexOfSelected, 1);
            this.setAllChannelsFromCategorySelected(channelObject, false)
        } else {
            selectedArray.push(channelObject.categoryName);
            this.setAllChannelsFromCategorySelected(channelObject, true)
        }
        // this.setState({selectedCategories: selectedArray})
        this.props.setSelectedCategories(selectedArray);
    }

    setAllChannelsFromCategorySelected(channelObject, isSelectAll) {
        const selectedArray = this.props.selectedChannels.slice();
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
            this.props.setSelectedChannels(selectedArray)
            // this.setState({selectedChannels: selectedArray})
    }

    handleImageClick(channel) {
        const selectedArray = this.props.selectedChannels.slice();
        const indexOfSelected = selectedArray.indexOf(channel);
        if(indexOfSelected !== -1) {
            selectedArray.splice(indexOfSelected, 1);
        } else {
            selectedArray.push(channel);
        }
        // this.setState({selectedChannels: selectedArray})
        this.props.setSelectedChannels(selectedArray);
    }

    render() {
        const {values} = this.props;
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
                                                    return (
                                                        <img
                                                            className={this.props.selectedChannels.indexOf(channel) !== -1 ? 'channel-image-clicked' : 'channel-image'}
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

// export default ChannelChooser;


const mapStateToProps = (state) => {
    return {
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
    }
};

export default connect(mapStateToProps,mapDispatchToProps)(ChannelChooser)