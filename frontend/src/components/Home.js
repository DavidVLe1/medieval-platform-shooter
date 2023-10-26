import React, { useContext } from 'react';
import './Home.css'; // Import a CSS file for styling
import AuthContext from './context/AuthContext';

export default function Home() {
    const { isAuthenticated, userData } = useContext(AuthContext);
    return (
        <div className="home-container text-large">
            <h1>Welcome to Starlight Quest: Knight's Challenge</h1>
            {isAuthenticated ? (
                <>
                <p>Thank you for playing our game {userData.username}!</p>
                </>
            ) : (
                <>
                    <p>Thank you for visiting our game!</p>
                </>
            )}

            <p>Get ready for an epic adventure filled with action and excitement.</p>
            <p>Instructions:</p>
            <ul>
                <li>Use the arrow keys to move your character.</li>
                <li>Press 'X' to perform a special move.</li>
                <li>Collect stars to earn points and advance through the game.</li>
                <li>Avoid bombs and defeat enemies to stay alive.</li>
                <li>Defeat the boss to win!</li>
            </ul>
            <p>Have fun and good luck!</p>
            {isAuthenticated ? (
                <>
                </>
            ) : (
                <>
                    <p>To start your adventure, please <a href="/login">log in</a> or <a href="/signup">sign up</a>.</p>
                </>
            )}

        </div>
    );
}
