import {useNavigate} from "react-router-dom";
import {useState, useEffect} from "react";
import {useParams} from "react-router-dom";
import {user} from "../../../context/Reducer";

import Form from 'react-bootstrap/Form';
import Button from 'react-bootstrap/Button';

import Col from 'react-bootstrap/Col';
import Row from 'react-bootstrap/Row';

const ReserveTicket = () => {

    const params = useParams();
    const navigate = useNavigate();
    const [ticketType, setTicketType] = useState({});

    const [amount, setAmount] = useState(1);


    const [deliveryMethod, setDeliveryMethod] = useState('pickup');
    const [location, setLocation] = useState('');
    const [isLocationEnabled, setIsLocationEnabled] = useState(true);

    const [status, setStatus] = useState('PENDING');


    useEffect(() => {
        if (params && params.ticketTypeId) {
            fetch('/api/ticket/getbytype/' + params.ticketTypeId)
                .then(response => response.json())
                .then(result => {
                    setTicketType(result);
                    console.log(ticketType.ticketTypeDTO.ticketType)
                })
            
        }
    }, [params])

    const handleDeliveryMethodChange = (event) => {
        const method = event.target.value;
        setDeliveryMethod(method);

        if (method === 'pickup') {
            setStatus('PENDING')
        setIsLocationEnabled(true);
        } else {
            setStatus('DOWNLOAD')
            setLocation('')
        setIsLocationEnabled(false);
        }
    };

    const handleLocationChange = (event) => {
        setLocation(event.target.value);
    };
    
    const validateStates = (e) => {
        let errors = "";
        if(status === "PENDING" && location === "") errors = errors.concat("Please choose a location\n");
        if(amount > ticketType.availableTickets) errors = errors.concat("Not enough tickets available, please lower the amount\n");
        return errors;
    }

    const handleSubmit = (e) => {
        let errors =  validateStates();
        if (errors === "") {
            let payload = {
                "eventDTO":{
                    "id":params.eventId
                },
                "status":status,
                "type":params.ticketTypeId,
                "amount":amount,
                "userDTO":user.id,
                "location":{
                    "id":location
                }
            }
            console.log(payload);
            fetch('/api/ticket/add', {
                method: 'POST',
                body: JSON.stringify(payload),
                headers: {
                    'Content-Type': 'application/json',
                }
            }).then(
                navigate({
                    pathname: "/mytickets"
                })
            )
        }
        else{
            alert(errors);
        }
    }

    return <>
        { 
            ticketType && ticketType.ticketTypeDTO &&
            <div className="container">
                <h1 style={{ textAlign: 'left', marginLeft: '10px', marginTop: '20px' }}>Ticket type: {ticketType.ticketTypeDTO.ticketType}</h1>
                <h2 style={{ textAlign: 'left', marginLeft: '10px' }}>Ticket Price: {ticketType.ticketTypeDTO.ticketPrice} BAM</h2>
                <h2 style={{ textAlign: 'left', marginLeft: '10px' }}>Availability: {ticketType.availableTickets}/{ticketType.totalTickets}</h2>
                <Form>
                    
                    <Form.Group as={Row}>
                        <Form.Label column sm={2}>
                            Number of tickets:
                        </Form.Label>
                        <Col sm={10}>
                            <Form.Control
                            type="number"
                            min={1}
                            max={ticketType.availableTickets}
                            value={amount}
                            onChange={e => setAmount(e.target.value)}
                            />
                        </Col>
                    </Form.Group>
                    
                <br></br>
                    
            <Form.Group as={Row}>
          <Col sm={2}>
            <Form.Check
              type="radio"
              id="pickup"
              value="pickup"
              checked={deliveryMethod === 'pickup'}
              onChange={handleDeliveryMethodChange}
            />
          </Col>
          <Col sm={10}>
            <Form.Control
              as="select"
              value={location}
              onChange={handleLocationChange}
              disabled={!isLocationEnabled}
            >
              <option value="">Select a location</option>
              <option value="1">Sarajevo</option>
              <option value="2">Tuzla</option>
              <option value="3">Mostar</option>
            </Form.Control>
          </Col>
        </Form.Group>
        <br></br>
        <Form.Group as={Row}>
          <Col sm={2}>
            <Form.Check
              type="radio"
              id="download"
              //label="Download"
              value="download"
              checked={deliveryMethod === 'download'}
              onChange={handleDeliveryMethodChange}
            />
          </Col>
          <Col sm={10} className="d-flex justify-content-start">
            <Form.Label>
                Download
            </Form.Label>
          </Col>
        </Form.Group>
                
        <Button variant="primary" onClick={e => handleSubmit(e)}>
                                Next
        </Button>
                </Form>
            </div>
            
        }
    </>
}

export default ReserveTicket;