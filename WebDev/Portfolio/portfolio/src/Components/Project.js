import React from "react";
import '../../node_modules/bootstrap/dist/css/bootstrap.min.css';

export default function Project({title, description, imgURL, link}) {
    return (
        <div className="col project">
            <img src={imgURL}/>
            <h4>{title}</h4>
            <p className="proj-desc">{description}</p>
            <p><a href={link} className="proj-link">Github Link🔗</a></p>
        </div>
    )
}