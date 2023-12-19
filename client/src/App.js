import React, { useEffect, useState } from "react";
import "./App.css";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import Login from "./views/login/Login";
import NavScrollExample from "./views/menu/NavScrollExample";
import { AuthProvider } from "./context";
import Home from "./views/home/Home";
import SignUp from "./views/signup/SignUp";
import Event from "./views/home/components/Event";
import CreateEvent from "./views/home/components/CreateEvent";
import ReserveTicket from "./views/home/components/ReserveTicket";
import MyTasks from "./views/home/components/MyTasks";
import MyTickets from "./views/home/components/MyTickets";
import ChangePasswordForm from "./views/home/change password/ChangePassword";
import ForgotPassword from "./views/home/components/ForgotPassword";
import { getAuth } from "./methods";



const App = () => {

  const [tokenValid, setTokenValid] = useState(true);
  const [message, setMessage] = useState("");

  useEffect(() => {
    const storedUser = localStorage.getItem("currentUser");

    // Check if the value exists in local storage
    const token = storedUser ? JSON.parse(storedUser).user : null;
      
    const checkTokenValidity = async () => {
      try {
        // Make a request to your backend to check if the token is valid
        const response = await fetch('/api/jwt/validate-token', {
          method: 'GET',
          headers: {
            'Authorization': `Bearer ${token}`,
          },
        });
  
        if (response.ok) {
          response.json().then(result => {
            if(result.message === "Token is valid") setTokenValid(true)
            else{
              setTokenValid(false)
              setMessage(result.message)
              localStorage.removeItem('currentUser');
            }
            console.log(result)
            console.log(tokenValid)
          })
        }
      } catch (error) {
        console.error('Error checking token validity:', error);
        // Handle error
      }
    };
    if(token != null)
      checkTokenValidity();
  }, []); // Empty dependency array to run the effect only once
  

  return (
    <AuthProvider>
      <div className="App">
        <BrowserRouter>
          <NavScrollExample />
          {tokenValid ? (
            <Routes>
              <Route path="/" element={<Home />} />
              <Route path="/login" element={<Login />} />
              <Route path="/signup" element={<SignUp />} />
              <Route path="/event/:id" element={<Event />} />
              <Route path="/new-event" element={<CreateEvent />} />
              <Route
                path="/reserve/:eventId/:ticketTypeId"
                element={<ReserveTicket />}
              />
              <Route path="/mytickets" element={<MyTickets />} />
              <Route path="/mytasks" element={<MyTasks />} />
              <Route path="/changePassword" element={<ChangePasswordForm />} />
              <Route path="/forgotPassword" element={<ForgotPassword />} />
            </Routes>
          ) : (
            <div className="container mt-3">
              <p>{message}</p>
            </div>
          )}
        </BrowserRouter>
      </div>
    </AuthProvider>
  );
};
export default App;
