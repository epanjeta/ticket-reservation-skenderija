import {user} from "../../context/Reducer";
import {useEffect, useState} from "react";
import EventCard from "./components/EventCard";
import {get} from "../../methods";


const Home = () => {
    const [events, setEvents] = useState([]);


    useEffect( () => {
        get('api/event/all')
            .then(result => {
                setEvents(result);
            });
    }, [])


    return <>
        {
            events &&
            events.map(oneEvent => {
                return <EventCard event={oneEvent}></EventCard>
            })
        }
    </>
}

export default Home;