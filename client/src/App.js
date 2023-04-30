import React, {useEffect, useState} from 'react';
import './App.css';
import {BrowserRouter, Route, Routes} from 'react-router-dom'
import Login from "./views/login/Login";
import NavScrollExample from "./views/menu/NavScrollExample";
import {AuthProvider} from "./context";
import Home from "./views/home/Home";
import SignUp from "./views/signup/SignUp";

const App = () => {

    const [groups, setGroups] = useState([]);
    const [loading, setLoading] = useState(false);

    useEffect(() => {
        setLoading(true);

        fetch('api/user/all')
            .then(response => response.json())
            .then(data => {
                setGroups(data);
                setLoading(false);
            })
    }, []);

    if (loading) {
        return <p>Loading...</p>;
    }

    const renderNotImplemented = () => {
        return <div>Not implemented</div>
    }

    return (
        <AuthProvider>
            <div className="App">
                <BrowserRouter>
                    <NavScrollExample/>
                    <Routes>
                        <Route path="/" element={<Home/>}/>
                        <Route path="/login" element={<Login/>}/>
                        <Route path="/signup" element={<SignUp/>}/>
                    </Routes>
                </BrowserRouter>
            </div>
        </AuthProvider>
    );
}

export default App;