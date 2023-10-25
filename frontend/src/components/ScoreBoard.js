import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import './ScoreBoard.css'; // Import a CSS file for styling
export default function ScoreBoard(handleAuthentication, isAuthenticated ,handleUserId, isUserId) {
    const [scores,setScores] = useState([]);
    const navigate=useNavigate();

    useEffect(() => {
        fetch('http://localhost:8080/api/leaderboard')
            .then(res => {
                if (res.ok) {
                    return res.json();
                } else if (res.status >= 500) {
                    return res
                        .json()
                        .then(error =>
                            Promise.reject(new Error(error.message))
                        );
                } else {
                    // All other errors
                    return Promise.reject(
                        new Error(`Unexpected status code ${res.status}`)
                    );
                }
            })
            .then(data => {
                // console.log(data);
                setScores(data);
            })
            .catch(error => {
                 console.error(error); // Log for debugging
                navigate('/error', { state: { error } });
            });
    }, []); // This happens every time the component is mounted
    // console.log(platforms);

    console.log(scores)
    return (
        <div className="scoreboard-container">
          <h1>Leaderboard</h1>
          <table>
            <thead>
              <tr>
                <th>Rank</th>
                <th>Player</th>
                <th>Score</th>
              </tr>
            </thead>
            <tbody>
              {scores.map((entry, index) => (
                <tr key={entry.player}>
                  <td>{index + 1}</td>
                  <td>{entry.username}</td>
                  <td>{entry.score}</td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      );
    }