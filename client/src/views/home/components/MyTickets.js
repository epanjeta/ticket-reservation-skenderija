import {user} from "../../../context/Reducer";
import {useEffect, useState} from "react";
import {getAuth} from "../../../methods";
import TicketCard from "./TicketCard";
import './EventCard.css';

const MyTickets = () => {

    const [tickets, setTickets] = useState([]);

    useEffect( () => {
        const token = JSON.parse(localStorage.getItem("currentUser")).user;
        getAuth('/api/ticket/get/' + user.id, {
                method: 'GET',
                headers: {
                    'Authorization': `Bearer ${token}`,
                    'Content-Type': 'application/json',
                }
            })
            .then(result => {
                setTickets(result);
                console.log(result)
            });
    }, [])


    return (
        <>
          {user.userType === "USER" ? (
            <div className="listBody">
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
          ) : (
            <div className="container mt-3">
              <p>Oops, looks like you don't have access to this page.</p>
            </div>
          )}
        </>
      );
}

export default MyTickets;