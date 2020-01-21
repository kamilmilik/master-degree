import {combineReducers, createStore} from 'redux';

import formReducer from "../reducers/formReducer";

const rootReducer = combineReducers({
    // reducer: formReducer
    formReducer
});

export default rootReducer;
