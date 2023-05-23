import {user} from "../../../context/Reducer";
import {useEffect, useState} from "react";
import {get} from "../../../methods";
import TicketCard from "./TicketCard";

const MyTickets = () => {

    const [tickets, setTickets] = useState([]);

    useEffect( () => {
        get('/api/ticket/get/' + user.id)
            .then(result => {
                setTickets(result);
                console.log(result)
            });
    }, [])

    return <>
        {
            tickets &&
            tickets.map(oneTicket => {
                return <TicketCard ticket={oneTicket}></TicketCard>
            })
        }
    </>
}

export default MyTickets;