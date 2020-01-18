import {combineReducers, createStore} from 'redux';

import formReducer from "../reducers/formReducer";

const rootReducer = combineReducers({
    // reducer: formReducer
    form: formReducer
});

const store = createStore(rootReducer);


export default rootReducer;
