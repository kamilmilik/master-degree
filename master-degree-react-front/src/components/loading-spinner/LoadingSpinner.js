import React from 'react';
import {usePromiseTracker} from "react-promise-tracker";
import PulseLoader from "react-spinners/PulseLoader";

export function LoadingSpinner({Component}) {
    const {promiseInProgress} = usePromiseTracker();

    return (
        (promiseInProgress === true) ?
            <div className="spinner">
                <PulseLoader type="ThreeDots" color="#2BAD60" height="100" width="100"/>
            </div>
            :
            <Component/>
    );
};