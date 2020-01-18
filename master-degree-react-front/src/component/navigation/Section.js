import React from "react";
import '../../css/Navigation.css';


export default function Section({Component, dark, id}) {




    return (
        <div className={"section" + (dark ? " section-dark" : "")}>
            <div className="section-content" id={id}>
                <Component/>
            </div>
        </div>
    );
}