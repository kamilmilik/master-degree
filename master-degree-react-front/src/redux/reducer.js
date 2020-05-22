import {
    FILTER_OPERATOR,
    SET_ALL_OPERATORS,
    SET_ALL_CHANNELS,
    SET_SELECTED_OPERATORS,
    SET_SELECTED_CHANNELS,
    SET_SELECTED_CATEGORIES,
    SET_SELECTED_PRICE,
    SET_SELECTED_TERM,
    SET_SELECTED_CHANNELS_BY_CATEGORY,
    SET_RESULT, CLEAR_FILTER_ELEMENTS, SET_IS_CLEARED_FILTERS
} from './actions/actions-type'
import {
    DEFAULT_MIN_PRICE_FILTER_VALUE,
    MAX_PRICE_FILTER_VALUE,
    MIN_PRICE_FILTER_VALUE, NUMBER_OF_MONTHS_VALUE_3
} from "../component/filter_section/main_form_filter/Const";
import React from "react";


const initState = {
    operatorsDto: [],
    channelsGroupByCategoryDto: [],

    selectedChannelsByCategory: {},
    selectedCategories: [],

    result: {},
    criteria: {
        operatorsId: [],
        term: NUMBER_OF_MONTHS_VALUE_3.toString(),
        price: MAX_PRICE_FILTER_VALUE,
        channelsName: []
    },
    isClearedFilters: false
};

// reducers: these are functions that implement the behavior of the actions. They change the state of the app, based on the action description and the state change description.
const reducer = (state = initState, action) => {

    switch (action.type) {
        case SET_ALL_OPERATORS: {
            let operators = action.operatorsDto;
            return {
                ...state,
                operatorsDto: operators
            };
        }
        case SET_ALL_CHANNELS: {
            let channels = action.channelsGroupByCategoryDto;
            return {
                ...state,
                channelsGroupByCategoryDto: channels,
            };
        }
        case SET_SELECTED_CHANNELS: {
            let selectedChannels = action.selectedChannels;
            return {
                ...state,
                criteria: {
                    ...state.criteria,
                    channelsName: selectedChannels
                }
            };
        }
        case SET_SELECTED_CHANNELS_BY_CATEGORY: {
            let selectedChannelsByCategory = Object.assign({}, action.selectedChannelsByCategory);
            return {
                ...state,
                selectedChannelsByCategory: selectedChannelsByCategory,
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
                criteria: {
                    ...state.criteria,
                    price: selectedPrice
                }
            };
        }
        case SET_SELECTED_TERM: {
            let selectedTerm = action.selectedTerm;
            return {
                ...state,
                criteria: {
                    ...state.criteria,
                    term: selectedTerm
                }
            };
        }
        case SET_SELECTED_OPERATORS: {
            let operators = action.selectedOperators;
            return {
                ...state,
                criteria: {
                    ...state.criteria,
                    operatorsId: operators
                }

            };
        }
        case SET_RESULT: {
            let result = action.result;
            return {
                ...state,
                result: result
            };
        }
        case CLEAR_FILTER_ELEMENTS: {
            let criteria = {
                operatorsId: [],
                term: NUMBER_OF_MONTHS_VALUE_3.toString(),
                price: MAX_PRICE_FILTER_VALUE,
                channelsName: []
            }
            let selectedCategories = [];
            let selectedChannelsByCategory = {}
            return {
                ...state,
                criteria: Object.assign({}, criteria),
                selectedCategories: Object.assign([], selectedCategories),
                selectedChannelsByCategory: Object.assign({}, selectedChannelsByCategory),
            };
        }
        case SET_IS_CLEARED_FILTERS:{
            let isClearedFilters = action.isClearedFilters;
            return {
                ...state,
                isClearedFilters: isClearedFilters
            };
        }
        default:
            return state
    }
};

export default reducer
