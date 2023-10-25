import React from 'react';
import './Home.css'; // Import a CSS file for styling

export default function Home() {
  return (
    <div className="home-container">
      <h1>Welcome to Starlight Quest: Knight's Challenge</h1>
      <p>Thank you for playing our game!</p>
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
    </div>
  );
}
