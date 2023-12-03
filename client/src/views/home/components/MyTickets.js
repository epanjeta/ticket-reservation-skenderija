import {user} from "../../../context/Reducer";
import {useEffect, useState} from "react";
import {get} from "../../../methods";
import TicketCard from "./TicketCard";
import './EventCard.css';

const MyTickets = () => {

    const [tickets, setTickets] = useState([]);

    useEffect( () => {
        get('/api/ticket/get/' + user.id)
            .then(result => {
                setTickets(result);
                console.log(result)
            });
    }, [])

    return <div className="listBody">
        {
            tickets &&
            tickets.map(oneTicket => {
                return <TicketCard ticket={oneTicket}></TicketCard>
            })
        }
        {
            (!tickets || tickets.length === 0) && <div className="noAvailable">
                There are no tickets available at the moment
            </div>
        }
    </div>
}

export default MyTickets;