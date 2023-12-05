export const get = async (url, params) => {
    if (params) {
        const response = await fetch(url + '?' + new URLSearchParams(params))
        return await response.json()
    }
    const response = await fetch(url + '?' + new URLSearchParams(params))
    return await response.json()
}

export const getAuth = async (url, params) => {
    const token = JSON.parse(localStorage.getItem("currentUser")).user;
    if (params) {
        const response = await fetch(url + '?' + new URLSearchParams(params), {
            method: 'GET',
            headers: {
                'Authorization': `Bearer ${token}`,
                'Content-Type': 'application/json',
            }
        })
        return await response.json()
    }
    const response = await fetch(url + '?' + new URLSearchParams(params), {
        method: 'GET',
        headers: {
            'Authorization': `Bearer ${token}`,
            'Content-Type': 'application/json',
        }
    })
    return await response.json()
}

export const post = async (url, body, params) => {
    if (body) {
        return executeRequest(url, params, {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(body)
        })
    }

    return executeRequest(url, params, {
        method: 'POST',
        headers: {'Content-Type': 'application/json'}
    })
}
export const postAuth = async (url, body, params) => {
    const token = JSON.parse(localStorage.getItem("currentUser")).user;
    if (body) {
        return executeRequest(url, params, {
            method: 'POST',
            headers: {
                'Authorization': `Bearer ${token}`,
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(body)
        })
    }

    return executeRequest(url, params, {
        method: 'POST',
        headers: {
            'Authorization': `Bearer ${token}`,
            'Content-Type': 'application/json'
        }
    })
}

export const put = async (url, body, params) => {
    if (body) {
        return executeRequest(url, params, {
            method: 'PUT',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(body)
        })
    }

    return executeRequest(url, params, {
        method: 'PUT',
        headers: {'Content-Type': 'application/json'}
    })
}

export const putAuth = async (url, body, params) => {
    const token = JSON.parse(localStorage.getItem("currentUser")).user;
    if (body) {
        return executeRequest(url, params, {
            method: 'PUT',
            headers: {
                'Authorization': `Bearer ${token}`,
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(body)
        })
    }

    return executeRequest(url, params, {
        method: 'PUT',
        headers: {
            'Authorization': `Bearer ${token}`,
            'Content-Type': 'application/json'
        }
    })
}

const executeRequest = async (url, params, requestOptions) => {
    if (params) {
        let response = await fetch(url + '?' + new URLSearchParams(params), requestOptions);
        return  await response.json();
    }

    let response = await fetch(url, requestOptions);
    return  await response.json();
}