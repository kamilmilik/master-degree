import {combineReducers, createStore} from 'redux';

import formReducer from "../reducers/formReducer";

const rootReducer = combineReducers({
    formReducer
});

export default rootReducer;
