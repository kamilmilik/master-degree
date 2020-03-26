import React from "react";
import PulseLoader from "react-spinners/PulseLoader";

export const LoadingSpinner = (props) => {
    return (
        <div>
            <PulseLoader type="ThreeDots" color="#2BAD60" height="100" width="100" />
        </div>
    );
};
