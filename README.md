# documentation
По дефлоту все параметры/аргументы required. Если написано required = false, то опционально


## Reservation Controller
| Description | Link example | Params | Method | What returns |
|-------------|--------------|----------|:------:|------------|
| Get all reservations for hotel | `/api/reservation/allReservations?hotel_id={}` Example: `/api/reservation/allReservations?hotel_id=1` | int hotel_id  | `GET` | List of Reservations |
| Delete reservation in hotel | `/api/reservation/{reservation_id}/delete?hotel_id=0`  `/api/reservation/1/delete?hotel_id=0` | hotel_id - required integer  reservation_id - required integer | `POST` | Nothing | 
| Create reservation for a guest in specific hotel between some dates | `/api/reservation/create?username={}&hotel_id={}&check_in_date={}&check_out_date={}&room_type_id={}`  Example: `/api/reservation/create?username=justadlet&hotel_id=0&check_in_date=2021-12-13&check_out_date=2021-12-18&room_type_id=0` | username - String  hotel_id - required integer  check_in_date - required String, format: `yyyy-MM-dd`  check_out_date - required String, format: `yyyy-MM-dd`  room_type_id - required integer, type of room in hotel_id | `Post` | id of created reservation in the response body|
| Edit reservation in some specific hotel | `/api/reservation/{reservation_id}/edit?hotel_id=0&check_in_date=2021-12-14&check_out_date=2021-12-18&room_type_id=2`    `/api/reservation/3/edit?hotel_id=0&check_in_date=2021-12-14&check_out_date=2021-12-18&room_type_id=2` | reservation_id - required integer  hotel_id - required integer  check_in_date - notRequired string, format: "yyyy-MM-dd"  check_out_date - notRequired string, format: "yyyy-MM-dd"  room_type_id - notRequired integer | `POST` | status |

## Hotel Controller
| Description | Link example | Params | Method | What returns |
|-------------|--------------|----------|:------:|------------|
| Get a list of all hotels | `/api/hotel/get/all` | | `GET` | list of all hotels |
| Get specific hotel | `/api/hotel/get/{id}`  Example: `/api/hotel/get/2`(отель с айдишкой 2) | int id required| `GET` | one Hotel object |
| Edit a specific hotel | `/api/hotel/edit/{id}?name={}&address={}&phones={}&phones={}&phones{}`  Example: `/api/hotel/edit/2?name=Rixos&address=Moscow&phones=123&phones=345`| int id,  String name,  String address,  List\[String\] phones | `POST` | Empty or throws exception if no such hotel with id |
| Delete a specific hotel | `/api/hotel/delete/{id}`  Example: `/api/hotel/delete/2` | int id | `POST` | Empty or throws if no such hotel with id |
| Add a new hotel | `/api/hotel/add?name={}&address={}&phones={}&phones={}&phones={}`  Example: `/api/hotel/add?name=Moscow Taganskaya&address=Metro Taganskaya&phones=123&phones=321&phones=421` | String name,  String address,  List\[String\] phones | `POST` | id of new created hotel |

## Login Controller
| Description | Link example | Params | Method | What returns |
|-------------|--------------|----------|:------:|------------|
| Signin a user | - `/signin?username=justadlet&password=helloworld`  - `/signin?username={}&password={}` | String username  String password | `POST` | **guest** or **{role}**(For example: manager) or **Incorrect Password** or **Incorrect Username**|  
| Signup a user | - `/signup?username=justadlet&password=helloworld&name=Adlet&surname=Zeineken&address=Moscow&home_phone=8 707 912 03 76&mobile_phone=8 901 507 91 25&identification_type=PASSPORT`  - `/signup?username={}&password={}&name={}&surname={}&address={}&home_phone={}&mobile_phone={}&identification_type=PASSPORT` | String username  String password String name String surname  String address  String home_phone String mobile_phone  String identification_type - **PASSPORT** or **DRIVING_LICENSE**  String number| `POST` | **Success** or **Username is already taken** |  
| Signup a manager | - `/signup/manager?username=justadlet&password=helloworld&name=Adlet&surname=Zeineken&hotelName=Rixos&address=Moscow&phones=8 707 912 03 76&phones=8 901 507 91 25`  - `/signup/manager?username={}&password={}&name={}&surname={}&hotelName={}&address={}&phones={}&phones={}` | String username  String password  String name  String surname  String hotelName  String address  List\[String\] phones | `POST` | **Success** or **Username is already taken** | 
| Signup an employee | - `/signup/employee?username=justadlet&password=helloworld&name=Adlet&surname=Zeineken&hotelName=Rixos&address=Moscow&phones=8 707 912 03 76&phones=8 901 507 91 25`  - `/signup/manager?username={}&password={}&name={}&surname={}&hotelName={}&address={}&phones={}&phones={}` | String username  String password  String name  String surname  String hotelName  String address  List\[String\] phones | `POST` | **Success** or **Username is already taken** |  
