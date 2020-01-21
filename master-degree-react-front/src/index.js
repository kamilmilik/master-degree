import React from 'react';
import ReactDOM from 'react-dom';
import App from './App';
import './index.css';
import reducers from './reducers'
import {applyMiddleware, compose, createStore} from 'redux';
import thunk from "redux-thunk";
import {Provider} from "react-redux";
import {store} from "./reducers/store";


ReactDOM.render(
    <Provider store={store}>
        <App/>
    </Provider>,
    document.getElementById('root')
);
