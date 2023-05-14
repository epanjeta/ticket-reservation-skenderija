import {useNavigate} from "react-router-dom";
import {useState} from "react";


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
    const [time, setTime] = useState(null)
    const [image, setImage] = useState(null)

    const [parter, setParter] = useState(1000);
    const [vip, setVip] = useState(100);
    const [backstage, setBackstage] = useState(50);

    const navigate = useNavigate();

    const validateStates = () => {
        let errors = "";
        if(name === "") errors = errors.concat("Please provide event name\n");
        if(type === "") errors = errors.concat("Please provide event type\n");
        if(description === "") errors = errors.concat("Please provide event description\n");
        if(date === "") errors = errors.concat("Please provide event date\n");
        if(time === null) errors = errors.concat("Please provide event time\n");
        if(image == null) errors =errors.concat("Please provide an image for event\n")
        return errors;
    }

    const handleSubmit = (e) => {

        let errors = validateStates();
        if(errors === ""){

            const imagedata = new FormData()
            imagedata.append('image', image)

            fetch('/api/image/upload', {
                method: 'POST',
                body: imagedata,
            }).then(response => response.json()
            .then(data => {
                console.log("Id nove slike je ");
                let imageDbId = data;
                console.log(imageDbId);
                const imageDbIdInt = parseInt(imageDbId)
                let payload = {
                    "title": name,
                    "description": description,
                    "date": date,
                    "type": type,
                    "seconds": time.toString(),
                    "imageId": imageDbIdInt.toString()
                }
                fetch('/api/event/create', {
                    method: 'POST',
                    body: JSON.stringify(payload),
                    headers: {
                        'Content-Type': 'application/json',
                    }
                }).then(response2 => response2.json()
                .then(data2 => {
                    const eventId = data2.id;
                    let availableticketsdata = {
                        "eventId":eventId,
                        "parterTickets":parter,
                        "vipTickets":vip,
                        "backstageTickets":backstage
                    }
                    fetch('/api/availabletickets/create', {
                        method: 'POST',
                        body: JSON.stringify(availableticketsdata),
                        headers: {
                            'Content-Type': 'application/json',
                        }
                    }).then(
                        navigate({
                            pathname: "/"
                        })
                    )
                }))
            }))
        }
        else{
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
                        onChange = {e => setName(e.target.value)} />
                </Form.Group>
                </Col>
                <Col>
                <Form.Group className="mb-4">
                    <Form.Label>Event Type</Form.Label>
                    <Form.Select aria-label="Default select example"
                            onChange = {e => setType(e.target.value)}>
                                <option>Pick Event Type</option>
                                <option value="Koncert">Koncert</option>
                                <option value="Sajam">Sajam</option>
                                <option value="Seminar">Seminar</option>
                    </Form.Select>
                </Form.Group>
                </Col>
                </Row>

                <Form.Group className="mb-4">
                    <Form.Label>Event Description</Form.Label>
                    <Form.Control as="textarea" rows={3} 
                    onChange = {e => setDescription(e.target.value)}/>
                </Form.Group>
                <Row>
                <Col>
                <Form.Group className="mb-4">
                    <Form.Label>Select Date</Form.Label>
                    <Form.Control type="date" name="dob"
                    onChange = {e => setDate(e.target.value)}/>
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
                    onChange = {e => setParter(e.target.value)}/>
                </Form.Group>
                </Col>
                <Col>
                <Form.Group className="mb-4">
                    <Form.Label>Select Number of VIP Tickets</Form.Label>
                    <Form.Control type="number"
                    value={vip}
                    onChange = {e => setVip(e.target.value)}/>
                </Form.Group>
                </Col>
                <Col>
                <Form.Group className="mb-4">
                    <Form.Label>Select Number of Backstage Tickets</Form.Label>
                    <Form.Control type="number"
                    value={backstage}
                    onChange = {e => setBackstage(e.target.value)}/>
                </Form.Group>
                </Col>
                </Row>

                <Form.Group  className="mb-4">
                    <Form.Label>Image</Form.Label>
                    <Form.Control type="file" 
                    onChange = {e => setImage(e.target.files[0])}/>
                </Form.Group>
                
                <Button variant="primary" onClick={e => handleSubmit(e)}>
                    Add
                </Button>
            </Form>
        </div>

        
    }</>
}

export default CreateEvent;