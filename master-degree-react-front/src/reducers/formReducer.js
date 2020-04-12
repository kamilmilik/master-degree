import {
    FILTER_OPERATOR,
    SET_ALL_OPERATORS,
    SET_ALL_CHANNELS,
    SET_SELECTED_OPERATORS,
    SET_SELECTED_CHANNELS,
    SET_SELECTED_CATEGORIES,
    SET_SELECTED_PRICE,
    SET_SELECTED_TERM,
    SET_IS_LOADING_FILTERED_RESULT,
    SET_SECTION1_REF, SET_SECTION2_REF, SET_SELECTED_CHANNELS_BY_CATEGORY,
    SET_RESULT
} from './actions/actions-type'
import {
    DEFAULT_MIN_PRICE_FILTER_VALUE,
    MAX_PRICE_FILTER_VALUE,
    MIN_PRICE_FILTER_VALUE
} from "../component/filter/main_form_filter/Const";
import React from "react";


const initState = {
    operatorsDto: [],
    channelsGroupByCategoryDto: [],

    selectedChannelsByCategory: {},
    selectedCategories: [],

    result: {},
    criteria: {
        operatorsId: [],
        term: "24",
        price:  MAX_PRICE_FILTER_VALUE,
        channels: []
    },
    isLoadingFilteredResult: true,
};

// reducers: these are functions that implement the behavior of the actions. They change the state of the app, based on the action description and the state change description.
const formReducer = (state = initState, action) => {

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
                    channels: selectedChannels
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
        case SET_IS_LOADING_FILTERED_RESULT: {
            let isLoadingFilteredResult = action.isLoadingFilteredResult;
            return {
                ...state,
                isLoadingFilteredResult: isLoadingFilteredResult
            };
        }


        default:
            return state
    }
};

export default formReducer