import React from 'react'
import Button from 'react-bootstrap/Button';
import jsPDF from 'jspdf'
import './EventCard.css';

const TicketCard = (props) => {

    const {ticket} = props;

    const pdfGenerate = (e) => {
        var doc = new jsPDF();
        var imgData = 'data:image/jpeg;base64,'+ ticket.eventDTO.picture;
        doc.addImage(imgData, 'JPEG', 15, 10, 180, 90);
        doc.text(20, 110, "Ticket id: " + ticket.id);

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
                                
                                <Button variant="dark" onClick={e => pdfGenerate(e)}>
                                    Download
                                </Button>
                            </div> : <div className="col-md-3">
                                
                                <Button variant="dark">
                                    {ticket.status === "PENDING" ?
                                        "Pending" : "Ready for pickup"}
                                </Button>
                            </div>

                    }
                </div>
            </div>
        }
    </>
}

export default TicketCard