import React, {Component} from 'react';
import 'react-image-picker/dist/index.css'
import App from "../../App";
import '../../css/App.css';
import '../../css/TermChooser.css';

class TermChooser extends Component {

    constructor(props) {
        super(props);
        this.state = {
        };
    }




    render() {
        const {values} = this.props;
        return (
               <div className={"mainTermChooserContainer"}>
                   <ul>
                       <li>
                           <a>
                               12
                               <span>miesiecy</span>
                           </a>
                       </li>
                       <li>
                           <a>
                               15
                               <span>miesiecy</span>
                           </a>
                       </li>
                       <li>
                           <a>
                               24
                               <span>miesiece</span>
                           </a>
                       </li>
                   </ul>
               </div>
        );
    }
}

export default TermChooser;
