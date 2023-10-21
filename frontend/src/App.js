import { useState, useEffect } from "react";
import { BrowserRouter as Router, Route, Routes, Navigate } from "react-router-dom";
import PhaserGame from "./components/PhaserGame";

function App() {
  return (
    <>
      <div>
        <h1>My React + Phaser Game</h1>
        <PhaserGame />
      </div>
      {/* <Router>

      </Router> */}
    </>

  );
}

export default App;
