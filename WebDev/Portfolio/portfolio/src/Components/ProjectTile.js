import React from "react"
import Project from "./Project"
import '../../node_modules/bootstrap/dist/css/bootstrap.min.css';

export default function ProjectTile() {
    return (
        <div className="project-tile">
            <h2>Projects</h2>
            <div className="container">
                <div className="row">
                    <Project title="Project" description="description" imgURL="images/Head.jpg"/>
                    <Project title="Project" description="description" imgURL="images/Head.jpg"/>
                    <Project title="Project" description="description" imgURL="images/Head.jpg"/>
                </div>
                <div className="row">
                    <Project title="Project" description="description" imgURL="images/Head.jpg"/>
                    <Project title="Project" description="description" imgURL="images/Head.jpg"/>
                    <Project title="Project" description="description" imgURL="images/Head.jpg"/>
                </div>
            </div>
        </div>
    )
}