CREATE TABLE cakes (
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50),
    price DECIMAL(10, 2),
    mainflavour VARCHAR(30),
    dietary VARCHAR(50),
    calories DECIMAL (10,2),
    theme VARCHAR(50),
    size DECIMAL(4, 1)
);
