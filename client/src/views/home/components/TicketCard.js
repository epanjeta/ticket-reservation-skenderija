import React from 'react'
import {useEffect, useState} from "react";
import {get} from "../../../methods";
import Button from 'react-bootstrap/Button';
import jsPDF from 'jspdf'

const TicketCard = (props) => {

    const { ticket } = props;

    const pdfGenerate = (e) => {
        var doc = new jsPDF();
        doc.text(20, 20, ticket.eventDTO.title);
        doc.text(20, 40, ticket.eventDTO.date);
        doc.text(20, 50, ticket.eventDTO.type);
        
        doc.save('ticket.pdf');
    }

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
                <Button variant="primary" onClick={e => pdfGenerate(e)}>
                                {ticket.status}
                </Button>
                </div>
            </div>
        </div>
  }
</>
}

export default TicketCard