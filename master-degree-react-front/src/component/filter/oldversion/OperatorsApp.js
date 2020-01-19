import React, {Component} from 'react';
import ListOperatorsComponent from "./ListOperatorsComponent";

class OperatorsApp extends Component {
    render() {
        return (
            <div>
                <h1>Operators Application</h1>
                <ListOperatorsComponent/>
            </div>
        )
    }
}

export default OperatorsApp