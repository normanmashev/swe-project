# documentation

## Desk Clerk
| Description | Link example | Params | Method |
|-------------|--------------|----------|:------:|
| Get all reservations for hotel | `/api/deskClerk/allReservations?hotel_id=1` | hotel_id - integer, required = true | `GET` |
| Delete reservation in hotel | `/api/deskClerk/allReservations/{reservation_id}/delete?hotel_id=0`  `/api/deskClerk/allReservations/1/delete?hotel_id=0` | hotel_id - integer, required = true  reservation_id - integer, required = true | `POST` |
| Create reservation for a guest in specific hotel between some dates | `http://localhost:8080/api/deskClerk/allReservations/create?username=justadlet&hotel_id=0&check_in_date=2021-12-13&check_out_date=2021-12-18&room_type_id=0` | username - String  hotel_id - integer  check_in_date - String, format: `yyyy-MM-dd`  check_out_date - String, format: `yyyy-MM-dd`  room_type_id - integer, type of room in hotel_id | `Post` |
