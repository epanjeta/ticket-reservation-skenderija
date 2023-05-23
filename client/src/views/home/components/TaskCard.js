import {useNavigate} from "react-router-dom";
import React from 'react'
import './EventCard.css';

const TaskCard = (props) => {

    const {task} = props;
    const navigate = useNavigate();

    const handleSubmit = (e) => {
        fetch('/api/task/changeStatus', {
            method: 'PUT',
            body: JSON.stringify(task),
            headers: {
                'Content-Type': 'application/json',
            }
        }).then((response) =>
            navigate({
                pathname: "/mytickets"
            })
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
                                <button className="button-48" onClick={handleSubmit}>
                                    Finish task
                                </button>
                            </div>

                    }
                </div>
            </div>
        }
    </>
}
export default TaskCard;