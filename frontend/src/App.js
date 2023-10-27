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
  };
  const [isUserId, setisUserId] = useState(0);
  const handleUserId = (userId) => {
    setisUserId(userId);
  }
  const [userData, setUserData] = useState({});
  const handleUserData = (userData) => {
    setUserData(userData);
  }

 // Load data from localStorage on component mount
 useEffect(() => {
  const isAuthenticatedFromStorage = localStorage.getItem("isAuthenticated") === "true";
  const isUserIdFromStorage = parseInt(localStorage.getItem("isUserId"), 10) || 0;
  const userDataFromStorage = JSON.parse(localStorage.getItem("userData")) || {};

  setIsAuthenticated(isAuthenticatedFromStorage);
  setisUserId(isUserIdFromStorage);
  setUserData(userDataFromStorage);
}, []);

// Save data to localStorage when data changes
useEffect(() => {
  localStorage.setItem("isAuthenticated", isAuthenticated.toString());
  localStorage.setItem("isUserId", isUserId.toString());
  localStorage.setItem("userData", JSON.stringify(userData));
}, [isAuthenticated, isUserId, userData]);

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
