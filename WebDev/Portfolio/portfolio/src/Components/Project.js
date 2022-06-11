import React from "react";
import '../../node_modules/bootstrap/dist/css/bootstrap.min.css';

export default function Project({title, description, imgURL}) {
    return (
        <div className="col project">
            <img src={imgURL}/>
            <h4>{title}</h4>
            <p>{description}</p>
        </div>
    )
}