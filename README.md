# documentation

## Reservation Controller
| Description | Link example | Params | Method | What returns |
|-------------|--------------|----------|:------:|------------|
| Get all reservations for hotel | `/api/reservation/allReservations?hotel_id=1` | hotel_id - required integer | `GET` | List of Reservations |
| Delete reservation in hotel | `/api/reservation/{reservation_id}/delete?hotel_id=0`  `/api/reservation/1/delete?hotel_id=0` | hotel_id - required integer  reservation_id - required integer | `POST` | Nothing | 
| Create reservation for a guest in specific hotel between some dates | `/api/reservation/create?username=justadlet&hotel_id=0&check_in_date=2021-12-13&check_out_date=2021-12-18&room_type_id=0` | username - String  hotel_id - required integer  check_in_date - required String, format: `yyyy-MM-dd`  check_out_date - required String, format: `yyyy-MM-dd`  room_type_id - required integer, type of room in hotel_id | `Post` | id of created reservation in the response body|
| Edit reservation in some specific hotel | `/api/reservation/{reservation_id}/edit?hotel_id=0&check_in_date=2021-12-14&check_out_date=2021-12-18&room_type_id=2`    `/api/reservation/3/edit?hotel_id=0&check_in_date=2021-12-14&check_out_date=2021-12-18&room_type_id=2` | reservation_id - required integer  hotel_id - required integer  check_in_date - notRequired string, format: "yyyy-MM-dd"  check_out_date - notRequired string, format: "yyyy-MM-dd"  room_type_id - notRequired integer | `POST` | status |
