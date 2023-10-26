import React from "react";
import { Link } from "react-router-dom";
import './ErrorPage.css';

export default function ErrorPage() {
  return (
    <div className="errorPage-container">
      <div className="error-content">
      <h1>Error!</h1>
      <p>Uh Oh. Something went wrong!</p>
        <Link to="/" className="go-back-button">
          Go Back Home
        </Link>
      </div>
    </div>
  );
};

