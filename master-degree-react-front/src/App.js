import React, {Component} from 'react';
import './App.css';
import MainFormFilter from "./component/filter/main_form_filter/MainFormFilter";
import Navbar from "./component/navigation/Navbar";
import Section from "./component/Section/Section";
import MainSearchResultComponent from "./component/search_result/MainSearchResultComponent";


class App extends Component {

    constructor(props) {
        super(props);
    }

    render() {
        return (
            <div className="App">
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
