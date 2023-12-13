import React, { useState } from "react";
import "./changePassword.css";
import { post } from "../../../methods";
import { user } from "../../../context/Reducer";

const ChangePasswordForm = () => {
  const [currentPassword, setCurrentPassword] = useState("");
  const [newPassword, setNewPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");

  const handleSubmit = async (e) => {
    e.preventDefault();

    // Add your logic to handle password change here
    // console.log("Current Password:", currentPassword);
    // console.log("New Password:", newPassword);
    // console.log("Confirm Password:", confirmPassword);
    if (newPassword != confirmPassword) {
      alert("Confirm correct password");
    } else {
      console.log(user.id); //provjera
      console.log(currentPassword);
      const result = await fetch("/api/user/checkPassword/" + user.id, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          // Add any additional headers if needed (e.g., authorization token)
        },
        body: currentPassword,
      });
      console.log(result.ok); //provjera
      if (result.ok) {
        const result2 = await fetch("/api/user/changePassword/" + user.id, {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
            // Add any additional headers if needed (e.g., authorization token)
          },
          body: newPassword,
        });
        if (result2.ok) {
          alert("You have successfully changed your password!");
        } else {
          alert(result2.statusText);
        }
      } else {
        alert("Input correct current password.");
      }
    }

    // Reset the form after handling the change
    setCurrentPassword("");
    setNewPassword("");
    setConfirmPassword("");
  };

  return (
    <form onSubmit={handleSubmit} className="formChange">
      <div className="divChange">
        <label htmlFor="currentPassword" className="labelChange">
          Current Password:
        </label>
        <input
          type="password"
          id="currentPassword"
          value={currentPassword}
          onChange={(e) => setCurrentPassword(e.target.value)}
          required
          className="inputChange"
        />
      </div>
      <div className="divChange">
        <label htmlFor="newPassword" className="labelChange">
          New Password:
        </label>
        <input
          type="password"
          id="newPassword"
          value={newPassword}
          onChange={(e) => setNewPassword(e.target.value)}
          required
          className="inputChange"
        />
      </div>
      <div className="divChange">
        <label htmlFor="confirmPassword" className="labelChange">
          Confirm Password:
        </label>
        <input
          type="password"
          id="confirmPassword"
          value={confirmPassword}
          onChange={(e) => setConfirmPassword(e.target.value)}
          required
          className="inputChange"
        />
      </div>
      <button type="submit" className="buttonChange">
        Change Password
      </button>
    </form>
  );
};

export default ChangePasswordForm;
