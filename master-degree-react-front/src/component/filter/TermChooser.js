import React, {Component} from 'react';
import 'react-image-picker/dist/index.css'
import App from "../../App";
import '../../css/App.css';
import '../../css/TermChooser.css';

class TermChooser extends Component {

    constructor(props) {
        super(props);
        this.state = {};
    }


    render() {
        const {values} = this.props;
        return (
            <div className={"container-fluid"} id={"mainTermChooserContainer"}>
                {/*<ul>*/}
                {/*    <li>*/}
                {/*        <a>*/}
                {/*            12*/}
                {/*            <span>miesiecy</span>*/}
                {/*        </a>*/}
                {/*    </li>*/}
                {/*    <li>*/}
                {/*        <a>*/}
                {/*            15*/}
                {/*            <span>miesiecy</span>*/}
                {/*        </a>*/}
                {/*    </li>*/}
                {/*    <li>*/}
                {/*        <a>*/}
                {/*            24*/}
                {/*            <span>miesiece</span>*/}
                {/*        </a>*/}
                {/*    </li>*/}
                {/*</ul>*/}
                <div class="container">
                    <div class="row">
                        <div class="col-sm">
                            <a>
                                12
                                <span>miesiecy</span>
                            </a>
                        </div>
                            <div class="col-sm">
                                <a>
                                    15
                                    <span>miesiecy</span>
                                </a>
                            </div>
                            <div class="col-sm">
                                <a>
                                    24
                                    <span>miesiece</span>
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
                );
                }
                }

                export default TermChooser;
