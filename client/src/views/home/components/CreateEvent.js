import {useNavigate} from "react-router-dom";
import {useState} from "react";
import './EventCard.css';

import Form from 'react-bootstrap/Form';
import Button from 'react-bootstrap/Button';
import TimePicker from "react-bootstrap-time-picker";

import Col from 'react-bootstrap/Col';
import Row from 'react-bootstrap/Row';

const CreateEvent = () => {

    const [name, setName] = useState('')
    const [type, setType] = useState('')
    const [description, setDescription] = useState('')
    const [date, setDate] = useState('')
    const [time, setTime] = useState(0)
    const [image, setImage] = useState(null)

    const [parter, setParter] = useState(1000);
    const [vip, setVip] = useState(100);
    const [backstage, setBackstage] = useState(50);

    const [parterPrice, setParterPrice] = useState(50);
    const [vipPrice, setVipPrice] = useState(50);
    const [backstagePrice, setBackstagePrice] = useState(50);

    const navigate = useNavigate();

    function validateStates  ()  {
        let errors = "";
        if (name === "") errors = errors.concat("Please provide event name\n");
        if (type === "") errors = errors.concat("Please provide event type\n");
        if (description === "") errors = errors.concat("Please provide event description\n");
        if (date === "") errors = errors.concat("Please provide event date\n");
        if (time === null) errors = errors.concat("Please provide event time\n");
        if (image == null) errors = errors.concat("Please provide an image for event\n")
        return errors;
    }

    const handleSubmit = (e) => {

        let errors =  validateStates();
        if (errors === "") {

            const imagedata = new FormData()
            imagedata.append('image', image)

            let availableBody = {
                "date":date
            }

            fetch('/api/event/available', {
                method: 'POST',
                body: JSON.stringify(availableBody),
                headers: {
                    'Content-Type': 'application/json',
                }
            }).then(
                response => response.json()
                .then( data => {
                        if (data.available == false) alert("This date is not available!");
                        else{
                            fetch('/api/image/upload', {
                                method: 'POST',
                                body: imagedata,
                            }).then(response => response.json()
                                .then(data => {
                                    let imageDbId = data;
                                    const imageDbIdInt = parseInt(imageDbId)
                                    let payload = {
                                        "title": name,
                                        "description": description,
                                        "date": date,
                                        "type": type,
                                        "seconds": time,
                                        "pictureId": imageDbIdInt,
                                        "tickets": [
                                            {
                                                "availableTickets": parter,
                                                "totalTickets": parter,
                                                "ticketTypeDTO": {
                                                    "ticketType": "PARTER",
                                                    "ticketPrice": parterPrice
                                                },
                                            },
                                            {
                                                "availableTickets": vip,
                                                "totalTickets": vip,
                                                "ticketTypeDTO": {
                                                    "ticketType": "VIP",
                                                    "ticketPrice": vipPrice
                                                }
                                            },
                                            {
                                                "availableTickets": backstage,
                                                "totalTickets": backstage,
                                                "ticketTypeDTO": {
                                                    "ticketType": "BACKSTAGE",
                                                    "ticketPrice": backstagePrice
                                                },
                                            }
                                        ]
                                    }
                                    fetch('/api/event/create', {
                                        method: 'POST',
                                        body: JSON.stringify(payload),
                                        headers: {
                                            'Content-Type': 'application/json',
                                        }
                                    }).then(response2 => response2.json()
                                        .then(data2 => {
                                            navigate({
                                                pathname: "/"
                                            })
                                        }))
                                }))
                        }
                    }
                )
            )

            
                }
                else
                    {
                        alert(errors);
                    }
                }


                    const handleTime = (selectedTime) => {
                        setTime(selectedTime)
                    }

                    return <>{

                        <div className="container mt-3">

                            <h1>Add a New Event</h1>

                            <Form className="mx-3 my-3">

                                <Row>
                                    <Col>
                                        <Form.Group className="mb-4">
                                            <Form.Label>Event Name</Form.Label>
                                            <Form.Control type="text"
                                                          onChange={e => setName(e.target.value)}/>
                                        </Form.Group>
                                    </Col>
                                    <Col>
                                        <Form.Group className="mb-4">
                                            <Form.Label>Event Type</Form.Label>
                                            <Form.Select aria-label="Default select example"
                                                         onChange={e => setType(e.target.value)}>
                                                <option>Pick Event Type</option>
                                                <option value="KONCERT">Koncert</option>
                                                <option value="SAJAM">Sajam</option>
                                                <option value="SEMINAR">Seminar</option>
                                            </Form.Select>
                                        </Form.Group>
                                    </Col>
                                </Row>

                                <Form.Group className="mb-4">
                                    <Form.Label>Event Description</Form.Label>
                                    <Form.Control as="textarea" rows={3}
                                                  onChange={e => setDescription(e.target.value)}/>
                                </Form.Group>
                                <Row>
                                    <Col>
                                        <Form.Group className="mb-4">
                                            <Form.Label>Select Date</Form.Label>
                                            <Form.Control type="date"
                                                          onChange={e => setDate(e.target.value)}/>
                                        </Form.Group>
                                    </Col>
                                    <Col>
                                        <Form.Group className="mb-4">
                                            <Form.Label>Select Time</Form.Label>
                                            <TimePicker
                                                format={24}
                                                start="00:00"
                                                end="24:00"
                                                step={30}
                                                onChange={handleTime}
                                                value={time}
                                            />
                                        </Form.Group>
                                    </Col>
                                </Row>
                                <Row>
                                    <Col>
                                        <Form.Group className="mb-4">
                                            <Form.Label>Select Number of Parter Tickets</Form.Label>
                                            <Form.Control type="number"
                                                          value={parter}
                                                          onChange={e => setParter(e.target.value)}/>
                                        </Form.Group>
                                    </Col>
                                    <Col>
                                        <Form.Group className="mb-4">
                                            <Form.Label>Select Number of VIP Tickets</Form.Label>
                                            <Form.Control type="number"
                                                          value={vip}
                                                          onChange={e => setVip(e.target.value)}/>
                                        </Form.Group>
                                    </Col>
                                    <Col>
                                        <Form.Group className="mb-4">
                                            <Form.Label>Select Number of Backstage Tickets</Form.Label>
                                            <Form.Control type="number"
                                                          value={backstage}
                                                          onChange={e => setBackstage(e.target.value)}/>
                                        </Form.Group>
                                    </Col>
                                </Row>

                                <Row>
                                    <Col>
                                        <Form.Group className="mb-4">
                                            <Form.Label>Select Price of Parter Tickets</Form.Label>
                                            <Form.Control type="number"
                                                          value={parterPrice}
                                                          onChange={e => setParterPrice(e.target.value)}/>
                                        </Form.Group>
                                    </Col>
                                    <Col>
                                        <Form.Group className="mb-4">
                                            <Form.Label>Select Price of VIP Tickets</Form.Label>
                                            <Form.Control type="number"
                                                          value={vipPrice}
                                                          onChange={e => setVipPrice(e.target.value)}/>
                                        </Form.Group>
                                    </Col>
                                    <Col>
                                        <Form.Group className="mb-4">
                                            <Form.Label>Select Price of Backstage Tickets</Form.Label>
                                            <Form.Control type="number"
                                                          value={backstagePrice}
                                                          onChange={e => setBackstagePrice(e.target.value)}/>
                                        </Form.Group>
                                    </Col>
                                </Row>

                                <Form.Group className="mb-4">
                                    <Form.Label>Image</Form.Label>
                                    <Form.Control type="file"
                                                  onChange={e => setImage(e.target.files[0])}/>
                                </Form.Group>

                                <Button variant="dark" onClick={e => handleSubmit(e)}>
                                    Add
                                </Button>
                            </Form>
                        </div>


                    }</>
                }

            export default CreateEvent;