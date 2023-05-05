// Context/actions.js


export async function loginUser(dispatch, loginPayload) {
    let data = undefined;
    try {
        dispatch({ type: 'REQUEST_LOGIN' });
        const requestOptions = {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(loginPayload)
        };

        let response = await fetch('api/user/authenticate', requestOptions);
        let result =  await response.json();

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