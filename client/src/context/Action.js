// Context/actions.js


import {put} from "../methods";

export async function loginUser(dispatch, loginPayload) {
    let data = undefined;
    try {
        dispatch({ type: 'REQUEST_LOGIN' });
        let result = put('api/user/authenticate', loginPayload)

        data = result;
        console.info(data)
        if (!result.errors) {
            dispatch({ type: 'LOGIN_SUCCESS', payload: {user: result} });
            localStorage.setItem('currentUser', JSON.stringify( {user: result}));
        } else {
            dispatch({ type: 'LOGIN_ERROR', error: result.errors[0] });
        }

    } catch (error) {
        dispatch({ type: 'LOGIN_ERROR', error: error });
    }

    return data;
}

export async function logout(dispatch) {
    dispatch({ type: 'LOGOUT' });
    localStorage.removeItem('currentUser');
    localStorage.removeItem('token');
}

export function getUser(dispatch) {

}