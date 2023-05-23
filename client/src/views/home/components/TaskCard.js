import React from 'react'
import {useEffect, useState} from "react";
import Button from 'react-bootstrap/Button';

const TaskCard = (props) => {
    
    const { task } = props;

  return <>
  {
      task &&
      <div className={`cardBody addCursor`}>
                <div className="row no-gutters">
                <div className="col-md-6">
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
                <div className="col-md-6">
                <Button variant="primary">
                                FINISHED
                </Button>
                </div>
            </div>
        </div>
  }
</>
}
export default TaskCard;