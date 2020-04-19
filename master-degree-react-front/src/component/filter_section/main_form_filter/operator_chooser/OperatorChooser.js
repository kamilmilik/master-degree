import React, {Component} from 'react';
import 'react-image-picker/dist/index.css'
import './OperatorChooser.css';
import {setIsLoadingFilteredResult, setResult, setSelectedOperators} from "../../../../reducers/actions/actions";
import {connect} from "react-redux";
import Typography from "@material-ui/core/Typography";
import {Image, Text, View} from "react-native-web";
import Checkbox from "@material-ui/core/Checkbox";
import withStyles from "@material-ui/core/styles/withStyles";
import Tooltip from "@material-ui/core/Tooltip";
import {Card, Icon} from "semantic-ui-react";

export const OPERATOR_IMAGE_CLICKED = "operator-image-clicked";
export const OPERATOR_IMAGE_NOT_CLICKED = "operator-image";
const TooltipMedium = withStyles((theme) => ({
    tooltip: {
        backgroundColor: '#f5f5f9',
        color: 'rgba(0, 0, 0, 0.87)',
        maxWidth: 220,
        fontSize: theme.typography.pxToRem(16),
        border: '1px solid #dadde9',
    },
}))(Tooltip);

class OperatorChooser extends Component {

    constructor(props) {
        super(props);
        this.state = {
            isImageHoverArray: [{}]
        }
        this.handleImageClick = this.handleImageClick.bind(this)
        this.onImageHoverAction = this.onImageHoverAction.bind(this)
    }

    handleImageClick(operatorId) {
        const selectedArray = this.props.criteria.operatorsId.slice();
        const indexOfSelected = selectedArray.indexOf(operatorId);
        if (!this.isOperatorSelected(indexOfSelected)) {
            selectedArray.splice(indexOfSelected, 1);
        } else {
            selectedArray.push(operatorId);
        }
        this.props.setSelectedOperators(selectedArray);
    }

    isOperatorSelected(indexOfSelected) {
        return indexOfSelected === -1
    }

    render() {
        const {values} = this.props;
        return (
            <div className={'container-fluid'} id={"main-operator-chooser-container"}>
                <div className={"ui segment"} id={"operator-main-segment"}>
                    <div className={"col-md-12"} id={"operators-list"}>
                        <div className="mdb-lightbox no-margin">
                            <div id={"operator-header"}>
                                <h4>Wybierz operatora</h4>
                                Wybierajac operatora wybierasz dostawce pakietu telewizyjnego
                            </div>
                            <div id={"operator-grid"}>
                                <div className={"ui segment"} id={"operator-segment"}>
                                {
                                    values.operatorsDto.map((operator) => {
                                        let classNameString = this.props.criteria.operatorsId.indexOf(operator.id) !== -1 ? OPERATOR_IMAGE_CLICKED : OPERATOR_IMAGE_NOT_CLICKED;
                                        return (
                                            <div className={"operator-container"}>
                                                <div className={classNameString}
                                                     onClick={() => this.handleImageClick(operator.id)}
                                                     onMouseOver={() => this.onImageHoverAction(operator, true)}
                                                     onMouseOut={() => this.onImageHoverAction(operator, false)}
                                                >
                                                    <TooltipMedium title={<React.Fragment><Typography
                                                        color="inherit">{operator.name} </Typography></React.Fragment>}>
                                                        <div className={"operator-image-container"}>
                                                            <Image
                                                                size={"medium"}
                                                                style={{width: 70, height: 70}}
                                                                source={{uri: operator.imgSrc}}
                                                                resizeMode="contain"
                                                            />
                                                            {this.checkBoxHover(classNameString, operator)}
                                                        </div>
                                                    </TooltipMedium>
                                                </div>
                                            </div>
                                        )
                                    })
                                }
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        )
    }

    checkBoxHover(classNameString, channel) {
        return (
            (classNameString === OPERATOR_IMAGE_CLICKED ||
                (classNameString === OPERATOR_IMAGE_NOT_CLICKED && this.state.isImageHoverArray.indexOf(channel) !== -1)) ?
                <div
                    className={"overlay-operator-content"}>
                    <Checkbox
                        size="small"
                        disabled={true}
                        defaultChecked
                        color="primary"
                    />
                </div> : null
        )
    }

    onImageHoverAction(channel, isHover) {
        if (isHover) {
            this.setState({
                isImageHoverArray: [...this.state.isImageHoverArray, channel],
            })
        } else {
            this.setState({
                isImageHoverArray: this.state.isImageHoverArray.filter(item => item !== channel)
            })
        }
    }

}

const mapStateToProps = (state) => {
    return {
        operatorsDto: state.formReducer.operatorsDto,
        result: state.formReducer.result,
        criteria: state.formReducer.criteria
    }
};

const mapDispatchToProps = (dispatch) => {


    return {
        setSelectedOperators: (selectedOperators) => {
            dispatch(setSelectedOperators(selectedOperators))
        },
        setResult: (result) => {
            dispatch(setResult(result))
        },
    }
};

export default connect(mapStateToProps, mapDispatchToProps)(OperatorChooser)