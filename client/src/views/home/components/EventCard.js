import './EventCard.css';
import {useNavigate} from "react-router-dom";
import {Image} from "react-native";

const EventCard = (props) => {
    const { event } = props;
    const navigate = useNavigate();

    const handleOnClick = () => {
        navigate(`/event/${event.id}`);
        window.location.reload();
    }

    return (
        <div className={`cardBody addCursor`} onClick={handleOnClick}>
            <div className="row no-gutters">
                <div className="col-md-6">
                    <img src={`data:image/png;base64,${event.picture}`} className="card-img" alt="..." />
                </div>
                <div className="col-md-6">
                    <h5 className="cardTitle">
                        {event.title}
                    </h5>
                    <p className="typeAndDate">
                        {event.type}
                    </p>
                    <p className="typeAndDate">
                        {event.date}
                    </p>
                    <p className="cardText overflowHidden">
                        {event.description}
                    </p>
                </div>
            </div>
        </div>
    )
}

export default EventCard;

