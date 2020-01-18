import React, {Component} from 'react';
import ImagePicker from "react-image-picker";
import 'react-image-picker/dist/index.css'
import App from "../../App";
import '../../css/App.css';
import '../../css/ChannelChooser.css';

const imageList = ['https://pbs.twimg.com/profile_images/1016326195221352450/KCcdUN0v.jpg', 'https://pbs.twimg.com/profile_images/1016326195221352450/KCcdUN0v.jpg']

function getImage() {
    return {active: false};
}

class ChannelChooser extends Component {

    constructor(props) {
        super(props);
        this.state = {
            channelsObject: [],
            selectedChannel: [],
            imageList: [],
            active: [],
        };
        this.onPick = this.onPick.bind(this);
        this.handleClick = this.handleClick.bind(this)
    }


    handleClick(channel) {

        // const currentState = this.state.active;
        // const newActive = this.state.active.slice(); //copy the array
        // newActive.push({
        //     key: channel.imgSrc,
        //     value: !currentState
        // }); //execute the manipulations
        // this.setState({active: newActive}) //set the new state

        const selectedChannelTemp = this.state.selectedChannel.slice();
        const indexOfChannel = selectedChannelTemp.indexOf(channel);
        if (indexOfChannel !== -1) {
            selectedChannelTemp.splice(indexOfChannel, 1);
        } else {
            selectedChannelTemp.push(channel);
        }
        this.setState({selectedChannel: selectedChannelTemp})
        // const currentState = this.state.active;
        // this.setState({ active: !currentState });
    }

    onPick(images) {
        // images.map((image) => {
        //     let selectedChannel = image.value;
        //     console.log("Selected ");
        //     console.log(selectedChannel);
        // });
        // this.setState({selectedChannel: images});
    }


    render() {
        const {values} = this.props;
        return (
            <div className={"mainChannelChooserContainer"}
                style={{
                // zoom: 0.08,
                // // display: 'flex',
                // alignItems: 'center'
            }}>
                {
                    // values.channelsObject.map((channelObject)=>{
                    //     channelObject.channels.map((channel) =>{
                    //         return(
                    //             <ImagePicker
                    //                 images={{src: channel.imgSrc, value: channel}}
                    //                 onPick={this.onPick}
                    //                 multiple
                    //             />
                    //         )
                    //     })
                    // })
                    // values.channelsObject.map((channelObject) => {
                    //
                    //                 return  (
                    //                     <div>
                    //                         <ImagePicker
                    //                             images={channelObject.channels.map((channel, indexOfOperator) => ({
                    //                                 src: channel.imgSrc, value: channel
                    //                             }))}
                    //                             onPick={this.onPick}
                    //                             multiple
                    //                         />
                    //                     </div>
                    //                 )
                    //             })
                }

                {
                    values.channelsObject.map((channelObject) => (
                        <div className={"categoryImageChannelSection"}>
                            <div className={"channelCategoryTitle"}>
                                <p>{channelObject.categoryName}</p>
                            </div>

                            <div className={"channelImageList"}>
                                {
                                    channelObject.channels.map((channel, i) => {
                                        return (
                                            <img
                                                className={this.state.selectedChannel.indexOf(channel) !== -1 ? 'channelsImageClicked' : 'channelsImage'}
                                                // <img className={this.state.active ? 'channelsImageClicked': ''}
                                                src={channel.imgSrc}
                                                onClick={() => this.handleClick(channel)}
                                            />
                                        )
                                    })
                                }
                            </div>
                        </div>
                    ))
                }

            </div>
        );
    }
}

export default ChannelChooser;
