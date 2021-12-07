# documentation

## Desk Clerk
| Description | Link example | Params | Method |
|-------------|--------------|--------|:------:|
| Get all reservations for hotel | `/api/deskClerk/allReservations?hotel_id=1` | hotel_id - integer, required = true | `GET` |
| Delete reservation in hotel | `/api/deskClerk/allReservations/{reservation_id}/delete?hotel_id=0`  `/api/deskClerk/allReservations/1/delete?hotel_id=0` | hotel_id - integer, required = true  reservation_id - integer, required = true | `POST` |
