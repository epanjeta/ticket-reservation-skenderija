import {useNavigate} from "react-router-dom";
import React from 'react'
import './EventCard.css';
import Button from 'react-bootstrap/Button';

const TaskCard = (props) => {

    const {task} = props;
    const navigate = useNavigate();

    const handleSubmit = (e) => {
        const token = JSON.parse(localStorage.getItem("currentUser")).user;
        fetch('/api/task/changeStatus', {
            method: 'PUT',
            body: JSON.stringify(task),
            headers: {
                'Authorization': `Bearer ${token}`,
                'Content-Type': 'application/json',
            }
        }).then((response) =>
            {
                window.location.reload();
            }
        )
    }

    return <>
        {
            task &&
            <div className="cardBody listElement">
                <div className="row no-gutters">
                    <div className="col-md-9">
                        <h5 className="cardTitle">
                            {task.ticket.eventDTO.title}
                        </h5>
                        <p className="typeAndDate">
                            {task.ticket.eventDTO.date}
                        </p>
                        <p className="typeAndDate">
                            {task.ticket.eventDTO.type}
                        </p>
                    </div>
                    {

                            <div className="col-md-3">
                                <Button variant="dark" onClick={e => handleSubmit(e)}>
                                    Finish Task
                                </Button>
                            </div>

                    }
                </div>
            </div>
        }
    </>
}
export default TaskCard;