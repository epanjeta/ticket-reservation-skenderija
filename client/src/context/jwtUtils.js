import { jwtDecode } from "jwt-decode";

export function decodeJwt(){
    const token = JSON.parse(localStorage.getItem("currentUser")).user;

    if (token) {

      let decodedToken = jwtDecode(token)
      const decodedUserType = decodedToken?.userType || null;
      const decodedUserName = decodedToken?.username || null;
      const decodedUserId = decodedToken?.userId || null;
      const decodedLocationId = decodedToken?.locationId || null;
      var user = {
        "userType":decodedUserType,
        "username":decodedUserName,
        "id":decodedUserId,
        "location":{
            "id":decodedLocationId
        }
      }
      
    }
    else{
        var user = {
            "userType":null,
            "username":null,
            "id":null,
            "location":{
                "id":null
            }
          }
    }
    return user;
}