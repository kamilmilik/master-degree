import React, {Component} from 'react';
import './App.css';
import MainFormFilter from "./component/filter_section/main_form_filter/MainFormFilter";
import NavbarComponent from "./component/navigation/NavbarComponent";
import Section from "./component/section/Section";
import ResultSection from "./component/result_section/ResultSection";

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