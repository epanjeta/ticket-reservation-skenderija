import React, { useState } from 'react'
import {FormControl, FormGroup, FormLabel, Button, } from 'react-bootstrap'
import './Login.css'
import {loginUser, useAuthDispatch} from "../../context";
import {useNavigate} from "react-router-dom";
import {
    MDBBtn,
    MDBContainer,
    MDBRow,
    MDBCol,
    MDBIcon,
    MDBInput
}
    from 'mdb-react-ui-kit';
import mainLogo from'../../icons/image-removebg-preview.png';

const Login = (props) => {
    const [email, setEmail] = useState('')
    const [password, setPassword] = useState('');
    const navigate = useNavigate();
    const dispatch = useAuthDispatch()
    const handleSubmit = (e) => {
        e.preventDefault()
        loginUser(dispatch, {email, password}).then(() => {
            navigate('/');
            window.location.reload();
        })
    }

    return (
        /*<div className="Login">
            <form onSubmit={handleSubmit}>
                <FormGroup controlId="email" bsSize="large">
                    <FormLabel>User Name</FormLabel>
                    <FormControl
                        autoFocus
                        type="text"
                        value={userName}
                        onChange={e => setUserName(e.target.value)}
                    />
                </FormGroup>
                <FormGroup controlId="password" bsSize="large">
                    <FormLabel>Password</FormLabel>
                    <FormControl
                        value={password}
                        onChange={e => setPassword(e.target.value)}
                        type="password"
                    />
                </FormGroup>
                <Button block bsSize="large"
                        type="submit">Login</Button>
            </form>
        </div>*/
    <MDBContainer fluid>
        <MDBRow>
            <MDBCol sm='6'>
                <div className='d-flex flex-row ps-5 pt-5'>
                    <img  src={mainLogo} height={50} width={50} alt="fireSpot"/>
                    <span className="h1 fw-bold mb-0">SKENDERIJA EVENTS</span>
                </div>
                <div className='d-flex flex-column justify-content-center h-custom-2 w-75 pt-4'>
                    <MDBInput
                        wrapperClass='mb-4 mx-5 w-100'
                        label='Email address'
                        id='formControlLg'
                        type='email'
                        size="lg"
                        onChange={e => setEmail(e.target.value)}
                    />
                    <MDBInput
                        wrapperClass='mb-4 mx-5 w-100'
                        label='Password'
                        id='formControlLg'
                        type='password'
                        size="lg"
                        onChange={e => setPassword(e.target.value)}
                    />
                    <MDBBtn
                        className="mb-4 px-5 mx-5 w-100"
                        color='info'
                        size='lg'
                        onClick={handleSubmit}
                    >
                        Login
                    </MDBBtn>
                    <p className='ms-5'>Don't have an account? <a href="/signup" className="link-info">Sign up here</a></p>
                </div>

            </MDBCol>
            <MDBCol sm='6' className='d-none d-sm-block px-0'>
                <img src="https://static.klix.ba/media/images/vijesti/210201055.3_xxl.jpg?v=2"
                     alt="Login image" className="w-100" style={{objectFit: 'cover', objectPosition: 'left'}} />
            </MDBCol>
        </MDBRow>

    </MDBContainer>
    )}
export default Login;