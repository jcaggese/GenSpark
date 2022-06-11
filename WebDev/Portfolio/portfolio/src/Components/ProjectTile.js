import React from "react"
import Project from "./Project"
import '../../node_modules/bootstrap/dist/css/bootstrap.min.css';

export default function ProjectTile() {
    return (
        <div className="project-tile">
            <h2>Projects</h2>
            <div className="container">
                <div className="row">
                    <Project title="Humans Vs. Goblins" description="Basic Game created in Java with GUI created with JavaFX" imgURL="images/HvG.png" link="https://github.com/jcaggese/GenSpark/tree/main/HumansVsGoblinsGUI"/>
                    <Project title="Boarding Pass" description="Boarding Pass Form created in Java with JavaFX" imgURL="images/BoardingPass.png" link="https://github.com/jcaggese/Boarding-Pass"/>
                    <Project title="Hangman Game" description="Hangman Game created using functional methods in Java" imgURL="images/Hangman.png" link="https://github.com/jcaggese/GenSpark/tree/main/HangmanFunctional"/>
                </div>
                <div className="row">
                    <Project title="RSA Cryptography Project" description="Java implementation of the RSA cryptographic algorithm" imgURL="images/EndBackground.jpg" link="https://github.com/WarrenDevonshire/CryptoProject3"/>
                    <Project title="Othello AI" description="Othello game created in Java with Swing. Uses an adversarial minimax algorithm" imgURL="images/EndBackground.jpg" link="https://github.com/jcaggese/Othello-AI"/>
                </div>
            </div>
        </div>
    )
}