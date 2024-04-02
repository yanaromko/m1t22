package jm.task.core.jdbc.util;

public final class SqlString {
    public static final String CREATE_USERS_TABLE_MySQL;
    public static final String CREATE_USERS_TABLE_PostgreSQL;
    public static final String DROP_USERS_TABLE_SQL;


    static {

        // MySQL
        CREATE_USERS_TABLE_MySQL = """
                CREATE TABLE IF NOT EXISTS users
                (
                    id        INT PRIMARY KEY AUTO_INCREMENT,
                    name      VARCHAR(45) NOT NULL,
                    last_name VARCHAR(45) NOT NULL,
                    age       TINYINT     NOT NULL
                )
                """;

        // PostgreSQL
        CREATE_USERS_TABLE_PostgreSQL = """
                CREATE TABLE IF NOT EXISTS users
                (
                    id        BIGSERIAL   PRIMARY KEY,
                    name      VARCHAR(45) NOT NULL,
                    last_name VARCHAR(45) NOT NULL,
                    age       SMALLINT    NOT NULL
                )
                """;
        // Generic SQL
        DROP_USERS_TABLE_SQL = """
                DROP TABLE IF EXISTS users
                """;
    }

    private SqlString() {
    }
}

