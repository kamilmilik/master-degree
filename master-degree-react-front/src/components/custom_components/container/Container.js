import React from "react";
import "./Container.css"

export default function Container({component, headerTitle, headerDescription}) {
    return (
        <div className={"ui segment"} id={"container-main-segment"}>
            <div className={"col-md-12"}>
                <div className={"mdb-lightbox no-margin"}>
                    <div id={"container-header"}>
                        <h4>{headerTitle}</h4>
                        {headerDescription}
                    </div>
                    {component}
                </div>
            </div>
        </div>
    );
}

