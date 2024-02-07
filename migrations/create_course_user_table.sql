CREATE TABLE course_user (
    course_id    int REFERENCES courses (id) ON UPDATE CASCADE ON DELETE CASCADE,
    user_id int REFERENCES users (id) ON UPDATE CASCADE,
    CONSTRAINT course_user_pkey PRIMARY KEY (course_id, user_id)
);

alter table course_user drop constraint course_user_pkey;

alter table course_user add column course_user_id BIGSERIAL PRIMARY KEY;