CREATE TYPE UserType AS ENUM ('USER', 'INSTRUCTOR', 'ADMIN');

CREATE TABLE users (
            id UUID PRIMARY KEY,
            first_name VARCHAR,
            last_name VARCHAR,
            email VARCHAR,
            phone_number VARCHAR,
            birth_date DATE,
            address VARCHAR,
            user_type UserType,
            created_at TIMESTAMP WITH TIME ZONE,
            updated_at TIMESTAMP WITH TIME ZONE
);

CREATE TABLE lessons (
            id UUID PRIMARY KEY,
            name VARCHAR,
            duration_minutes INTEGER,
            created_at TIMESTAMP WITH TIME ZONE,
            updated_at TIMESTAMP WITH TIME ZONE
);

CREATE TABLE membership (
            id UUID PRIMARY KEY,
            presences INTEGER,
            start_date TIMESTAMP WITH TIME ZONE,
            end_date TIMESTAMP WITH TIME ZONE,
            user_id UUID NOT NULL,
            FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE lesson_instructor_relationship (
            id uuid primary key,
            lesson_id uuid,
            user_id uuid,
            FOREIGN KEY (lesson_id) REFERENCES lessons(id),
            FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE appointments (
            id UUID PRIMARY KEY,
            user_id UUID,
            time_schedule_id UUID,
            created_at TIMESTAMP WITH TIME ZONE,
            FOREIGN KEY (user_id) REFERENCES users(id),
            FOREIGN KEY (time_schedule_id) REFERENCES time_schedule(id)
);

CREATE TABLE time_schedule (
            id UUID PRIMARY KEY,
            start_date TIMESTAMP WITH TIME ZONE,
            lesson_instructor_relationship_id UUID,
            FOREIGN KEY (lesson_instructor_relationship_id) REFERENCES lesson_instructor_relationship(id)
);
