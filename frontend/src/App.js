import { useState, useEffect } from "react";
import { BrowserRouter as Router, Route, Routes, Navigate } from "react-router-dom";
import Header from "./components/Header";
import Login from "./components/Login";
import ScoreBoard from "./components/ScoreBoard";
import SignUp from "./components/SignUp";
import ErrorPage from "./components/ErrorPage";
import LogOut from "./components/LogOut";
import Home from "./components/Home";
import GameContainer from "./components/GameContainer";

function App() {
  const [isAuthenticated, setIsAuthenticated] = useState(false);
  const handleAuthentication = (status) => {
    setIsAuthenticated(status);
    // console.log('isAuthenticated updated:', status);
  };
  const [isUserId, setisUserId] = useState(0);
  console.log(isUserId);
  const handleUserId = (userId) => {
    setisUserId(userId);
  }
  const [userData, setUserData] = useState({});
  const handleUserData = (userData) => {
    setUserData(userData);
  }

  return (
    <>
      <Router>
        <Header isAuthenticated={isAuthenticated} />

        <Routes>
          <Route path="/" element={<Home />}></Route>
          <Route path="/game" element={<GameContainer userData={userData} />}></Route>
          <Route path="/login" element={<Login handleUserData={handleUserData} handleAuthentication={handleAuthentication} isAuthenticated={isAuthenticated} handleUserId={handleUserId} isUserId={isUserId} />}></Route>
          <Route path="/logout" element={<LogOut handleUserData={handleUserData} handleAuthentication={handleAuthentication} />}></Route>
          <Route path="/signup" element={<SignUp handleUserData={handleUserData} handleAuthentication={handleAuthentication} isAuthenticated={isAuthenticated} handleUserId={handleUserId} isUserId={isUserId} />}></Route>
          <Route path="/scoreboard" element={<ScoreBoard handleAuthentication={handleAuthentication} isAuthenticated={isAuthenticated} handleUserId={handleUserId} isUserId={isUserId} />}></Route>
          <Route path='/*' element={<ErrorPage />}></Route>
        </Routes>
      </Router>
    </>

  );
}

export default App;
