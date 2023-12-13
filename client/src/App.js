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

const App = () => {
  return (
    <AuthProvider>
      <div className="App">
        <BrowserRouter>
          <NavScrollExample />
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
          </Routes>
        </BrowserRouter>
      </div>
    </AuthProvider>
  );
};

export default App;
