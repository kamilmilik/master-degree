import {
    ADD_TO_CART,
    REMOVE_ITEM,
    SUB_QUANTITY,
    ADD_QUANTITY,
    ADD_SHIPPING,
    ADD_ORDER_STATE,
    CLEAR_ADDED_ITEMS,
    SET_IS_ADMIN,
    SET_IS_LOGIN,
    FILTER_OPERATOR,
    SET_ALL_OPERATORS,
    SET_ALL_CHANNELS,
    SET_SELECTED_OPERATORS, SET_SELECTED_CHANNELS, SET_SELECTED_CATEGORIES, SET_SELECTED_PRICE, SET_SELECTED_TERM
} from './actions/actions-type'
import {DEFAULT_PRICE_FILTER_VALUE} from "../component/filter/main_form_filter/Const";


const initState = {
    operators: [],
    channelsObject: [],
    selectedChannels: [],
    selectedCategories: [],
    selectedOperators: [],
    selectedPrice: DEFAULT_PRICE_FILTER_VALUE,
    selectedTerm: ''
};

// reducers: these are functions that implement the behavior of the actions. They change the state of the app, based on the action description and the state change description.
const formReducer = (state = initState, action) => {

    switch (action.type) {
        case SET_ALL_OPERATORS: {
            let operators = action.operators;
            return {
                ...state,
                operators: operators
            };
        }
        case SET_ALL_CHANNELS: {
            let channels = action.channelsObject;
            return {
                ...state,
                channelsObject: channels
            };
        }
        case SET_SELECTED_CHANNELS: {
            let selectedChannels = action.selectedChannels;
            return {
                ...state,
                selectedChannels: selectedChannels
            };
        }
        case SET_SELECTED_CATEGORIES: {
            let selectedCategories = action.selectedCategories;
            return {
                ...state,
                selectedCategories: selectedCategories
            };
        }
        case SET_SELECTED_PRICE: {
            let selectedPrice = action.selectedPrice;
            return {
                ...state,
                selectedPrice: selectedPrice
            };
        }
        case SET_SELECTED_TERM: {
            let selectedTerm = action.selectedTerm;
            return {
                ...state,
                selectedTerm: selectedTerm
            };
        }
        case SET_SELECTED_OPERATORS: {
            let operators = action.selectedOperators;
            return {
                ...state,
                selectedOperators: operators
            };
        }

        default:
            return state
    }
};

export default formReducer