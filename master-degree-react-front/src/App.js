import React, {Component} from 'react';
import './App.css';
import MainFormFilter from "./component/filter/main_form_filter/MainFormFilter";
import Navbar from "./component/navigation/Navbar";
import Section from "./component/section/Section";
import MainSearchResultComponent from "./component/search_result/MainSearchResultComponent";
import { usePromiseTracker } from 'react-promise-tracker';
import Loader from 'react-promise-loader';

class App extends Component {

    constructor(props) {
        super(props);
    }

    render() {
        return (
            <div className="App">
                <Loader promiseTracker={usePromiseTracker} />
                <Navbar/>
                <div className={"container-fluid"} id={"main-container"}>
                    <Section
                        Component={MainFormFilter}
                        id={"section1"}
                    />
                    <Section
                        Component={MainSearchResultComponent}
                        id={"section2"}/>
                </div>
            </div>

        )
    }
}

export default App;
