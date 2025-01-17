// ForgotPassword.js
import React, { useState } from "react";
import "./forgotPassword.css"; // Import your CSS file
import { post } from "../../../methods";
import { useNavigate } from "react-router";

const ForgotPassword = () => {
  const [email, setEmail] = useState("");
  const navigate = useNavigate();

  const handleEmailChange = (e) => {
    setEmail(e.target.value);
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    console.log(`Password reset requested for email: ${email}`);
    var requestBody = {
      email: email,
    };
    post("/api/user/changeForgottenPassword", requestBody);
    navigate("/login");
  };

  return (
    <div>
      <h2 className="heading">Forgot Your Password?</h2>
      <p className="text">
        Enter your email address and we'll send you a new password.
      </p>
      <form className="form" onSubmit={handleSubmit}>
        <label className="label">
          Email:
          <input
            className="input"
            type="email"
            value={email}
            onChange={handleEmailChange}
          />
        </label>
        <button className="buttonFP" type="submit">
          Reset Password
        </button>
      </form>
    </div>
  );
};

export default ForgotPassword;
