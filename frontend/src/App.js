import { useState, useEffect } from "react";
import { BrowserRouter as Router, Route, Routes, Navigate } from "react-router-dom";
import PhaserGame from "./components/PhaserGame";
import Header from "./components/Header";
import Login from "./components/Login";
import ScoreBoard from "./components/ScoreBoard";
import SignUp from "./components/SignUp";
import ErrorPage from "./components/ErrorPage";
import LogOut from "./components/LogOut";
import Home from "./components/Home";

function App() {
  const [isAuthenticated, setIsAuthenticated]=useState(false);
  const handleAuthentication = (status) => {
    setIsAuthenticated(status);
    // console.log('isAuthenticated updated:', status);
  };
  const[isUserId, setisUserId]= useState(0);
  const handleUserId=(userId)=>{
    setisUserId(userId);
  }

  const[leaderboardScores,setLeaderboardScores]=useState([]);
  const handleLeaderboardScores = (leaderboardScores)=>{
    setLeaderboardScores(leaderboardScores);
  }

  return (
    <>
      <Router>
        <Header isAuthenticated={isAuthenticated} />
        <div>
          <h1>Starlight Quest: Knight's Challenge</h1>
          <br></br>
        </div>
        <Routes>
          <Route path="/" element={<Home/>}></Route>
          <Route path="/game" element={<PhaserGame leaderboardScores={leaderboardScores} handleLeaderboardScores={handleLeaderboardScores}/>}></Route>
          <Route path="/login" element={<Login handleAuthentication={handleAuthentication} isAuthenticated={isAuthenticated} handleUserId={handleUserId} isUserId={isUserId} />}></Route>
          <Route path="/logout" element={<LogOut handleAuthentication={handleAuthentication} handleLeaderboardScores={handleLeaderboardScores}/>}></Route>
          <Route path="/signup" element={<SignUp handleAuthentication={handleAuthentication} isAuthenticated={isAuthenticated} handleUserId={handleUserId} isUserId={isUserId}/>}></Route>
          <Route path="/scoreboard" element={<ScoreBoard handleAuthentication={handleAuthentication} isAuthenticated={isAuthenticated} handleUserId={handleUserId} isUserId={isUserId} leaderboardScores={leaderboardScores} handleLeaderboardScores={handleLeaderboardScores}/>}></Route>
          <Route path='/*' element={<ErrorPage/>}></Route>
        </Routes>
      </Router>
    </>

  );
}

export default App;
