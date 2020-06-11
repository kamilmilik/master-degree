import React from "react";
import "./Container.css"

export default function Container({component, headerTitle, headerDescription, headerDefaultDescription}) {
    return (
        <div className={"ui segment"} id={"container-main-segment"}>
            <div className={"col-md-12"}>
                <div className={"mdb-lightbox no-margin"} id={"main-container"}>
                    <div id={"container-header"}>
                        <h4 id={"header-title"}>{headerTitle}</h4>
                        <div id={"header-description"}>
                            <div className="description">{headerDescription}</div>
                            {
                                typeof headerDefaultDescription !== 'undefined' ?
                                    <div className="description-small">{headerDefaultDescription}</div> : <div/>
                            }
                        </div>
                    </div>
                    {component}
                </div>
            </div>
        </div>
    );
}

