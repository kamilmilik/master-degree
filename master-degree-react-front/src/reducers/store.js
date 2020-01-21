import { applyMiddleware, compose, createStore } from "redux";

import reducers from "./index";
import thunk from "redux-thunk";

export const store = createStore(reducers, compose(applyMiddleware(thunk)));


export default store;