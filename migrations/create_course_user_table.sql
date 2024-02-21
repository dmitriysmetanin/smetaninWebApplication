CREATE TABLE course_user (
    course_id    int REFERENCES courses (id) ON UPDATE CASCADE ON DELETE CASCADE,
    user_id int REFERENCES users (id) ON UPDATE CASCADE,
    course_user_id BIGSERIAL PRIMARY KEY
);

