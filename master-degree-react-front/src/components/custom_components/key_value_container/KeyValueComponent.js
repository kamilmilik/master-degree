import React from "react";
import "./KeyValueComponent.css"

export default function KeyValueComponent({keyComponent, valueComponent}) {
    return (
        <li className={"list-group-item"}>
            <div id={"key-container"}>
                {keyComponent}
            </div>
            <div className={"col-md-12"} id={"value-container"}>
                {valueComponent}
            </div>
        </li>
    );
}