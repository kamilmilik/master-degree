import React from "react";
import './Section.css';


export default function Section({Component, dark, id, ref}) {

    return (
        <div className={"section" + (dark ? " section-dark" : "")}>
            <div className="section-content" id={id} ref={ref}>
                <Component/>
            </div>
        </div>
    );
}