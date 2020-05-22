import React from 'react';
import ReactDOM from 'react-dom';
import App from './App';
import './index.css';
import reducers from './redux'
import {applyMiddleware, compose, createStore} from 'redux';
import thunk from "redux-thunk";
import {Provider} from "react-redux";
import {store} from "./redux/store";


ReactDOM.render(
    <Provider store={store}>
        <App/>
    </Provider>,
    document.getElementById('root')
);
