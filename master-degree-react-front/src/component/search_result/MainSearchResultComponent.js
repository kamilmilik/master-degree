import React, {Component} from 'react';
import {connect} from "react-redux";
import './MainSearchResultComponent.css'


class MainSearchResultComponent extends Component {
    constructor(props) {
        super(props);
        this.state = {};
    }

    render() {

        let selectedChannelsByCategory = this.props.selectedChannelsByCategory
        return (
            <div className={"container-fluid"} id={"main-search-result-component-content"}>
                <div className={"result-container"}>
                    <div>Filters</div>
                    <div>Okres: {this.props.selectedTerm}</div>
                    <div>Cena: {this.props.selectedPrice}zl</div>
                    <div>Operator:
                        {this.props.selectedOperators.map((operator) => {
                            return operator.name + ", "
                        })}
                    </div>
                    <div>Kanaly:
                        {/*{this.props.selectedChannels.map((channel) =>{*/}
                        {/*    return channel.name + ", "*/}
                        {/*})}*/}

                        {/*{*/}
                        {/*    if(this.props.selectedChannelsByCategory !== 'undefined') {*/}
                        {/*        return ''*/}
                        {/*    } else {*/}
                        {/*            return ''*/}
                        {/*    }*/}
                        {/*}*/}
                        {
                            Object.keys(selectedChannelsByCategory).map(function (key) {
                            let selectedChannels = selectedChannelsByCategory[key];
                            return selectedChannels.map((channel) =>{
                                return channel.name + ", ";
                            })
                            // return channel.name + ", ";
                        })


                        }
                    </div>
                </div>
            </div>

        );
    }
}

const mapStateToProps = (state) => {
    return {
        selectedTerm: state.formReducer.selectedTerm,
        selectedPrice: state.formReducer.selectedPrice,
        selectedOperators: state.formReducer.selectedOperators,
        selectedChannels: state.formReducer.selectedChannels,
        selectedChannelsByCategory: state.formReducer.selectedChannelsByCategory
    };
};

const mapDispatchToProps = (dispatch) => {

    return {}
};

export default connect(mapStateToProps, mapDispatchToProps)(MainSearchResultComponent)