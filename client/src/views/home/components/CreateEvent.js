import {useNavigate} from "react-router-dom";
import {useState} from "react";


import Form from 'react-bootstrap/Form';
import Button from 'react-bootstrap/Button';
import TimePicker from "react-bootstrap-time-picker";

const CreateEvent = () => {

    const [name, setName] = useState('')
    const [type, setType] = useState('')
    const [description, setDescription] = useState('')
    const [date, setDate] = useState('')
    const [time, setTime] = useState(null)
    const [image, setImage] = useState(null)

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
                    navigate({
                        pathname: "/"
                    })
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
            <Form className="mx-3 my-3">

                <Form.Group className="mb-4">
                        <Form.Label>Event Name</Form.Label>
                        <Form.Control type="text"
                        onChange = {e => setName(e.target.value)} />
                </Form.Group>

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

                <Form.Group className="mb-4">
                    <Form.Label>Event Description</Form.Label>
                    <Form.Control as="textarea" rows={3} 
                    onChange = {e => setDescription(e.target.value)}/>
                </Form.Group>

                <Form.Group className="mb-4">
                    <Form.Label>Select Date</Form.Label>
                    <Form.Control type="date" name="dob" placeholder="Date of Birth" 
                    onChange = {e => setDate(e.target.value)}/>
                </Form.Group>


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