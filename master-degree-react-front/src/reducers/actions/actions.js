import {
    SET_ALL_CHANNELS,
    SET_ALL_OPERATORS,
    SET_SELECTED_CATEGORIES,
    SET_SELECTED_CHANNELS,
    SET_SELECTED_OPERATORS, SET_SELECTED_PRICE, SET_SELECTED_TERM
} from "./actions-type";

// actions: these are objects that should have two properties, one describing the type of action, and one describing what should be changed in the app state.
export const setAllOperators=(operators)=>{
    return{
        type: SET_ALL_OPERATORS,
        operators
    }
};

export const setAllChannels=(channels)=>{
    return{
        type: SET_ALL_CHANNELS,
        channels
    }
};

export const setSelectedOperators=(selectedOperators)=>{
    return{
        type: SET_SELECTED_OPERATORS,
        selectedOperators
    }
};

export const setSelectedChannels=(selectedChannels)=>{
    return{
        type: SET_SELECTED_CHANNELS,
        selectedChannels: selectedChannels
    }
};

export const setSelectedCategories=(selectedCategories)=>{
    return{
        type: SET_SELECTED_CATEGORIES,
        selectedCategories: selectedCategories
    }
};

export const setSelectedTerm=(selectedTerm)=>{
    return{
        type: SET_SELECTED_TERM,
        selectedTerm: selectedTerm
    }
};

export const setSelectedPrice=(selectedPrice)=>{
    return{
        type: SET_SELECTED_PRICE,
        selectedPrice: selectedPrice
    }
};

