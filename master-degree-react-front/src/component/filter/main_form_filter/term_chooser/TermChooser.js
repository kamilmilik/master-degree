import React, {Component} from 'react';
import 'react-image-picker/dist/index.css'
import '../../../../App.css';
import './TermChooser.css';

class TermChooser extends Component {

    constructor(props) {
        super(props);
        this.state = {};
    }


    render() {
        const {values} = this.props;
        return (
            <div className={"container-fluid"} id={"main-term-chooser-container"}>
                <div className="container">
                    <div className="row">
                        <div id={"term-text"} className="col-sm">
                            <a>
                                12
                                <span>miesiecy</span>
                            </a>
                        </div>
                        <div id={"term-text"} className="col-sm">
                            <a>
                                15
                                <span>miesiecy</span>
                            </a>
                        </div>
                        <div id={"term-text"} className="col-sm">
                            <a>
                                24
                                <span>miesiace</span>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default TermChooser;
