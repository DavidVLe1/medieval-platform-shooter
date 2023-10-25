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
import AuthContext from "./components/context/AuthContext";

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

  const authValues = {
    isAuthenticated,
    handleAuthentication,
    userData,
    handleUserData,
    isUserId,
    handleUserId
  };

  return (
    <>
    <AuthContext.Provider value={authValues}>
      <Router>
        <Header  />

        <Routes>
          <Route path="/" element={<Home />}></Route>
          {authValues.isAuthenticated ? (
        <>
          <Route path="/game" element={<GameContainer  />} />
          <Route path="/logout" element={<LogOut />} />
          <Route path="/scoreboard" element={<ScoreBoard />} />
        </>
      ) : (
        <>
          <Route path="/login" element={<Login />} />
          <Route path="/signup" element={<SignUp />} />
        </>
      )}
          <Route path='/*' element={<ErrorPage />}></Route>
        </Routes>
      </Router>
      </AuthContext.Provider>
    </>

  );
}

export default App;
