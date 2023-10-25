import React from "react";
import { Link } from "react-router-dom";

export default function ErrorPage() {
  return (
    <div className="error-page-background">
      <div className="error-content">
        <Link to="/" className="go-back-button">
        <h1>Error!</h1>
          Go Back Home
        </Link>
      </div>
    </div>
  );
};

