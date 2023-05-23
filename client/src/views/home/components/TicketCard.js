import React from 'react'
import Button from 'react-bootstrap/Button';
import jsPDF from 'jspdf'
import './EventCard.css';

const TicketCard = (props) => {

    const {ticket} = props;

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
            <div className="cardBody listElement">
                <div className="row no-gutters">
                    <div className="col-md-9">
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
                    {
                        ticket.status === "DOWNLOAD" ?
                            <div className="col-md-3">
                                <button className="button-48" onClick={e => pdfGenerate(e)}>
                                    Download
                                </button>
                            </div> : <div className="col-md-3">
                                <button disabled={true} className="button-48" onClick={e => pdfGenerate(e)}>
                                    {ticket.status === "PENDING" ?
                                        "Pending" : "Ready for pickup"}
                                </button>
                            </div>

                    }
                </div>
            </div>
        }
    </>
}

export default TicketCard