import React, {Component} from 'react';
import OperatorDataService from "../../../service/OperatorDataService";


class ListOperatorsComponent extends Component {

    constructor(props) {
        super(props);
        this.state = {
            operators: [],
            message: null
        };
        this.refreshOperators = this.refreshOperators.bind(this)
    }

    componentDidMount() {
        this.refreshOperators()
    }

    refreshOperators(){
        OperatorDataService.retrieveAllOperators()
            .then(
                response => {
                    console.log(response);
                    this.setState({operators: response.data})
                }
            )
    }

    render() {
        return (
            <div className="container">
                <h3>All Courses</h3>
                <div className="container">
                    <table className="table">
                        <thead>
                        <tr>
                            <th>Id</th>
                            <th>Name</th>
                        </tr>
                        </thead>
                        <tbody>
                        {
                            this.state.operators.map(
                                operator =>
                                    <tr key={JSON.stringify(operator.id.timestamp)}>
                                        <td>{JSON.stringify(operator.id.timestamp)}</td>
                                        <td>{operator.name}</td>
                                    </tr>
                            )
                        }
                        </tbody>
                    </table>
                </div>
            </div>
        )
    }
}
export default ListOperatorsComponent