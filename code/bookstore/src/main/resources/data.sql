 DROP TABLE IF EXISTS users_roles;
        DROP TABLE IF EXISTS users;
        DROP TABLE IF EXISTS roles;
        DROP TABLE IF EXISTS author;
		DROP TABLE IF EXISTS book;
		
        
        
        CREATE TABLE users (
          id INT AUTO_INCREMENT  PRIMARY KEY,
          username VARCHAR(250) NOT NULL,
          password VARCHAR(250) NOT NULL,
          enabled BOOLEAN
        );

        -- admin with password admin123
        -- manager with password manager123
        -- guest with password guest123
        
        INSERT INTO users (username, password, enabled) VALUES
          ('admin', '$2a$10$VwbKuM65qx6jItBrEs06o.oET5CqwJE1pzZjARzmSrU.zAetJ4h8u', true),
          ('manager', '$2a$10$64hVCPy15hXYZws6BgVDO.d7eOrIt/zIGf9cljC/me.iIxa6/r8rm', true),
          ('guest', '$2a$10$0e8yEvfbckDJphI6.srJwO6z.uJ6hKbKC5wWfQ7mP4uN3jyQtFeSq', true);

        CREATE TABLE roles (
          id INT AUTO_INCREMENT  PRIMARY KEY,
          name VARCHAR(250) NOT NULL
        );

        INSERT INTO roles (name) VALUES
          ('USER'),
          ('ADMIN'),
          ('MANAGER');


        CREATE TABLE users_roles (
          user_id INTEGER NOT NULL,
          role_id INTEGER NOT NULL,
          FOREIGN KEY(user_id) REFERENCES users(id),
          FOREIGN KEY(role_id) REFERENCES roles(id),
          PRIMARY KEY (user_id, role_id)
        );

        INSERT INTO users_roles (user_id, role_id) VALUES
          (1, 2),
          (2, 1),
          (2, 2),
          (2, 3),
          (3, 1);
          
          
         CREATE TABLE book (
          isbn VARCHAR(13)  PRIMARY KEY,
          title VARCHAR(250) NOT NULL,
          year int NOT NULL,
          price DECIMAL(9,2) NOT NULL,
          genre VARCHAR(50) NOT NULL
        );  
        
         CREATE TABLE author (
          id INT AUTO_INCREMENT  PRIMARY KEY,
          name VARCHAR(250) NOT NULL,
          birthday TIMESTAMP NOT NULL,
          isbn VARCHAR(13) NOT NULL
        ); 
        
        INSERT INTO book (isbn, title, year, price, genre) VALUES
          ('1234567890123', 'Home alone',1999, 30, 'adventure'),
          ('1234567890124', 'far far',2000, 25,'comedy')
          ;
        
          INSERT INTO author ( name, birthday, isbn) VALUES
          ('Jones','1979-12-31' ,'1234567890123');
          
    
          INSERT INTO author ( name, birthday, isbn) VALUES
          ('Williams','1981-12-31' ,'1234567890124');
          