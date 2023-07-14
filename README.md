# Ticket Reservation Skenderija

Skenderija Ticket Reservation System is a full-stack web application designed to streamline the ticket reservation process. Developed as a team project, this application utilizes a combination of Spring Boot and React technologies to provide an efficient and user-friendly experience.

This comprehensive system incorporates key aspects of the ITIL (Information Technology Infrastructure Library) process, ensuring optimal performance, reliability, and security. The application caters to both administrative and user functionalities, enhancing the overall ticket reservation workflow

[Click here to check the full project documentation](Documentation.pdf)

## Functionalities

### Offer Overview
On the application's webpage, all users can conveniently browse upcoming events at Skenderija. They can access event information, including the date, time, brief description, and ticket prices. Each event will display the number of available seats.

### User Registration and Login
To reserve or purchase tickets for Skenderija events, users need to create an account. The registration process involves filling out a form with their name, last name, email, and password. Once users activate their accounts by confirming their email address, they can log in to the webpage using their email and password. Logged-in users can proceed with ticket reservations or purchases.

### Ticket Reservation
Registered users will have the option to reserve tickets directly through the webpage. They can choose to collect the tickets from available sales locations within a specified time period or download them digitally. If a non-registered user selects an event from the homepage, the reservation option will not be available.

After logging in, users can reserve their desired ticket type, provided that sufficient seats are available. Upon selection, users will be redirected to provide additional information, including the number of tickets and preference for personal collection or download. If they opt for personal collection, they must choose a convenient pickup location.

Once the additional details are provided, users can track the progress of their reservations on the "My Tickets" page. They can download their tickets if they selected the "download" option and verify if the tickets are ready for collection.

### Administrative Functionalities
In addition to registered users, administrators will have privileged access to manage the system. They can create upcoming events while adhering to defined constraints. Administrators will also oversee the creation of tickets designated for personal collection, with each pickup location having its own administrative profile.

When creating a new event, administrators need to provide the event's name, type, description, date, and the number of available tickets for each ticket type. The system will prevent administrators from creating events of the same type at the same time or exceeding the capacity limits. If any limitations are exceeded, the system will promptly notify the administrator.

Administrators can conveniently manage their tasks on the "Tasks" page. This page displays all the tickets that need to be created. If a user requests a new ticket creation, the administrator will receive an email notification. Similarly, when the administrator creates a new ticket and closes the task, the user will receive an email informing them that their ticket is ready for collection.

## Technologies Used

- Spring Boot
- React
- PostgreSQL
