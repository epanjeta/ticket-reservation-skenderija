import {user} from "../../../context/Reducer";
import {useEffect, useState} from "react";
import {get} from "../../../methods";
import TaskCard from "./TaskCard";
import './EventCard.css';


const MyTasks = () => {

    const [tasks, setTasks] = useState([]);


    useEffect( () => {
        get('/api/task/get/' + user.location.id) 
            .then(result => {
                setTasks(result);
                console.log(result)
            });
    }, [])

    return <div className="listBody">
        {
            tasks &&
            tasks.map(oneTask => {
                return <TaskCard task={oneTask}></TaskCard>
            })
        }
        {
            (!tasks || tasks.length === 0) && <div className="noAvailable">
                    There are no task available at the moment
            </div>
        }
    </div>
}

export default MyTasks;