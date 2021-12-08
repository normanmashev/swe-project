# documentation
По дефлоту все параметры/аргументы required. Если написано required = false, то опционально

## Main page controller
| Description | Link example | Params | Method | What returns |
|-------------|--------------|----------|:------:|------------|
| Get list of all hotels | `/` | | `GET` | List of all **Hotel** object |

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


## Room Type Controller
| Description | Link example | Params | Method | What returns |
|-------------|--------------|----------|:------:|------------|
|  Add a new room type in specific hotel | `/api/roomtype/add/?size={}&capacity={}&hotel_id={}`  Example: `/api/roomtype/add/?size=10&capacity=2&hotel_id=4` | int size,  int capacity,  int hotel_id  | `POST` | Created room type id or throws if no such hotel with hotel_id |
| Get all room types for specific hotel | `/api/roomtype/get/all/{id}`  Example: `/api/roomtype/get/all/4` | int id - hotel_id | `GET` | List of all Room_Type in hotel or throws if no such hotel with hotel id |
| Edit room type in specific hotel | `/api/roomtype/edit/{id}?size={}&capacity={}&hotel_id={}`  Example: `/api/roomtype/edit/4?size=10&capacity=11&hotel_id=12` | int id - room type id,  int size - new room type size,  int capacity - new room type capacity,  int hotel_id - new hotel id | `POST` | Empty or throws if no such roomType with id or throws if no such hotel with hotel id exists |
| Delete room type | `/api/roomtype/delete/{id}`  Example: `/api/roomtype/delete/7` | int id - room type id | `POST` | empty if success or throws if no such room type with id |


## Filter Controller
| Description | Link example | Params | Method | What returns |
|-------------|--------------|----------|:------:|------------|
| Get **Room** which is not occupied between some specific dates | `/api/filter/?checkin_date={yyyy-MM-dd}&checkout_date={yyyy-MM-dd}&room_type_id=7`  Example: `/api/filter/?checkin_date=2021-12-13&checkout_date=2021-12-18&room_type_id=7` | String checkin_date - format should be `yyyy-MM-dd`,  String checkout_date - format should be `yyyy-MM-dd`, int room_type_id - id of needed room type that wants to live in | `POST` | Throws parseException if date format is not correct. Returns empty if there is no room that is not occupied in that dates. Returns object **Room** |

## Working Hours Controller
| Description | Link example | Params | Method | What returns |
|-------------|--------------|----------|:------:|------------|
| Get a list of all **WorkingHours** of all employees | `/api/workinghours/schedules` | | `GET` | List of all **WorkingHours** |
| Get a list of all **WorkingHours** for specific employee | `/api/workinghours/schedule?employee_id={}` Example: `/api/workinghours/schedule?employee_id=3` | int employee_id | `GET` | List of **WorkingHours** for user with id = employee_id or throws if no such employee with emlpoyee_id | 
| Edit (Not ready yet) | `api/workinghours/edit` | hzhzhz | `POST` | hzhzhz |
