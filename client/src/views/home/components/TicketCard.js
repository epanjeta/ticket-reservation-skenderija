import React from 'react'
import {useEffect, useState} from "react";
import {get} from "../../../methods";
import Button from 'react-bootstrap/Button';

const TicketCard = (props) => {

    const { ticket } = props;

  return <>
  {
      ticket &&
      <div className={`cardBody addCursor`}>
                <div className="row no-gutters">
                <div className="col-md-6">
                    <h5 className="cardTitle">
                        {ticket.eventDTO.title}
                    </h5>
                    <p className="typeAndDate">
                        {ticket.eventDTO.date}
                    </p>
                    <p className="typeAndDate">
                        {ticket.eventDTO.type}
                    </p>
                </div>
                <div className="col-md-6">
                <Button variant="primary">
                                {ticket.status}
                </Button>
                </div>
            </div>
        </div>
  }
</>
}

export default TicketCard