import KeyValueComponent from "../key_value_container/KeyValueComponent";
import React from "react";

export default function ListContainer({elements}) {
    return (
        <div>
            {
                elements.map(element => (
                    <div className={"mdb-lightbox no-margin"} id={"category-image-channel-section"}>
                        <ul className={"ul-channel-content"}>
                            <KeyValueComponent
                                keyComponent={this.keyComponent(element)}
                                valueComponent={this.valueComponent(element, selectedChannelsByCategory)}
                            />
                        </ul>
                    </div>
                ))
            }
        </div>
    );
}