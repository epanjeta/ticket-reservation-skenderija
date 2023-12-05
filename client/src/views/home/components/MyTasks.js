import {user} from "../../../context/Reducer";
import {useEffect, useState} from "react";
import {get, getAuth} from "../../../methods";
import TaskCard from "./TaskCard";
import './EventCard.css';


const MyTasks = () => {

    const [tasks, setTasks] = useState([]);


    useEffect( () => {
        const token = JSON.parse(localStorage.getItem("currentUser")).user;
        getAuth('/api/task/get/' + user.location.id, {
                method: 'GET',
                headers: {
                    'Authorization': `Bearer ${token}`,
                    'Content-Type': 'application/json',
                }
            }) 
            .then(result => {
                setTasks(result);
                console.log(result)
            });
    }, [])

    return (
        <>
          {user.userType === "ADMIN" ? (
              <div className="listBody">
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
          ) : (
            <div className="container mt-3">
              <p>Oops, looks like you don't have access to this page.</p>
            </div>
          )}
        </>
      );

}

export default MyTasks;