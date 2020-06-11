import React, {Component} from 'react';
import './App.css';
import MainFormFilter from "./components/filter_section/main_form_filter/MainFormFilter";
import NavbarComponent from "./components/navigation/NavbarComponent";
import Section from "./components/section/Section";
import ResultSection from "./components/result_section/ResultSection";

class App extends Component {

    constructor(props) {
        super(props);
    }


    render() {
        return (
            <div className="App">
                <NavbarComponent/>
                <div className={"container-fluid"} id={"main-container"}>
                    <Section
                        Component={MainFormFilter}
                        id={"section1"}
                    />
                    <Section
                        Component={ResultSection}
                        id={"section2"}/>
                </div>
            </div>

        )
    }
}

export default App;