CREATE TABLE IF NOT EXISTS todo_item (
    id UUID PRIMARY KEY,
    text TEXT NOT NULL,
    date_created date NOT NULL
)