import React, { Component } from 'react';
import logo from './logo.svg';
import './css/App.css';
import OperatorsApp from "./component/filter/oldversion/OperatorsApp";

import Container from "semantic-ui-react/dist/commonjs/elements/Container";
import MainFormFilter2 from "./component/filter/oldversion/MainFormFilter2";
import MainFormFilter from "./component/filter/MainFormFilter";
import Navbar from "./component/navigation/Navbar";
import Section from "./component/navigation/Section";
import dummyText from "./component/navigation/DummyText";


class App extends Component {
  render() {
    return (
      // <div className="container">
      //   <OperatorsApp />
      // </div>
        <div className="App">
            <Navbar />
                <Container className={"mainContainer"} >
                    <MainFormFilter />
                </Container>
            {/*<Section*/}
            {/*    Component={MainFormFilter}*/}
            {/*    dark={true}*/}
            {/*    id="section1"*/}
            {/*>*/}
            {/*</Section>*/}
            {/*<Section*/}
            {/*    title="Section 2"*/}
            {/*    subtitle={dummyText}*/}
            {/*    dark={false}*/}
            {/*    id="section2"*/}
            {/*/>*/}
        </div>

    )
  }
}

export default App;
