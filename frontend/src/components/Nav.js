import { NavLink } from "react-router-dom";
import AuthContext from "./context/AuthContext";
import { useContext } from "react";
import "./Nav.css";
export default function Nav() {
    const {isAuthenticated}=useContext(AuthContext);



    return (
        
        <div className="container-fluid navbar-custom" >
            <nav className="navbar navbar-expand-lg "  >
                <NavLink className="navbar-brand vt323-font custom-link"  to="/" activeclassname="active">
                    Starlight Quest: Knight's Challenge
                </NavLink>
                <button
                    className="navbar-toggler"
                    type="button"
                    data-toggle="collapse"
                    data-target="#navbarNav"
                    aria-controls="navbarNav"
                    aria-expanded="false"
                    aria-label="Toggle navigation"
                >
                    <span className="navbar-toggler-icon"></span>
                </button>
                <div className="collapse navbar-collapse" id="navbarNav">
                    <ul className="navbar-nav ml-auto">
                        {isAuthenticated ? (
                            <>
                                <li className="nav-item">
                                    <NavLink className="nav-link vt323-font" to="/">
                                        Home
                                    </NavLink>
                                </li>

                                <li className="nav-item">
                                    <NavLink className="nav-link vt323-font" to="/logout">
                                        Logout
                                    </NavLink>
                                </li>
                                <li className="nav-item">
                                    <NavLink className="nav-link vt323-font" to="/game">
                                        Game
                                    </NavLink>
                                </li>
                                <li className="nav-item">
                                    <NavLink className="nav-link vt323-font" to="/scoreboard">
                                        Leaderboard
                                    </NavLink>
                                </li>
                            </>
                        ) : (
                            <>
                                <li className="nav-item">
                                    <NavLink className="nav-link vt323-font" to="/signup">
                                        Sign Up
                                    </NavLink>
                                </li>
                                <li className="nav-item">
                                    <NavLink className="nav-link vt323-font" to="/login">
                                        Log In
                                    </NavLink>
                                </li>
                            </>
                        )}
                    </ul>
                </div>
            </nav>
        </div>

    );
}