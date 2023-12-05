// Context/reducer.js

import React from "react";
import { decodeJwt } from "./jwtUtils";

export let user = localStorage.getItem("currentUser")
    ? decodeJwt()
    : undefined;

export const initialState = {
    userDetails: "" || user,
    loading: false,
    errorMessage: null
};

export const AuthReducer = (initialState, action) => {
    console.info(action)
    switch (action.type) {
        case "REQUEST_LOGIN":
            return {
                ...initialState,
                loading: true
            };
        case "LOGIN_SUCCESS":
            return {
                ...initialState,
                user: action.payload.user,
                loading: false
            };
        case "LOGOUT":
            return {
                ...initialState,
                user: undefined,
            };

        case "LOGIN_ERROR":
            return {
                ...initialState,
                loading: false,
                errorMessage: action.error
            };

        default:
            throw new Error(`Unhandled action type: ${action.type}`);
    }
};