ALTER TABLE course_user ADD CONSTRAINT unique_course_user_relation UNIQUE (course_id, user_id);