import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import PhaserGame from './PhaserGame'; // Import your Phaser game component

export default function GameContainer() {
    const [enemies, setEnemies] = useState([]);
    const navigate = useNavigate();
    useEffect(() => {
        fetch('http://localhost:8080/api/enemy')
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
                setEnemies(data);
            })
            .catch(error => {
                console.error(error); // Log for debugging
                navigate('/error', { state: { error } });
            });
    }, []); // This happens every time the component is mounted
    // console.log(platforms
    return (
        <>
            {enemies.length > 0 ? <PhaserGame enemies={enemies} /> : <p>Loading...</p>}
        </>
    );

}