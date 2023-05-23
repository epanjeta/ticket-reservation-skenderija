import {user} from "../../../context/Reducer";
import {useEffect, useState} from "react";
import {get} from "../../../methods";
import TaskCard from "./TaskCard";


const MyTasks = () => {

    const [tasks, setTasks] = useState([]);


    useEffect( () => {
        get('/api/task/get/' + user.location.id) 
            .then(result => {
                setTasks(result);
                console.log(result)
            });
    }, [])

    return <>
        {
            tasks &&
            tasks.map(oneTask => {
                return <TaskCard task={oneTask}></TaskCard>
            })
        }
    </>
}

export default MyTasks;