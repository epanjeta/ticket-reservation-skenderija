import {user} from "../../context/Reducer";
import {useEffect, useState} from "react";
import EventCard from "./components/EventCard";


const Home = () => {
    const [events, setEvents] = useState([]);


    useEffect( () => {
        fetch('api/event/all')
            .then(response => response.json())
            .then(result => {
                setEvents(result)
            })
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