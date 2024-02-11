select courses.*
from course_user
join courses on
    course_user.course_id = courses.id
where user_id = 17
;