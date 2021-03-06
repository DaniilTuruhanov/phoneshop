drop table if exists phone2color;
drop table if exists colors;
drop table if exists stocks;
drop table if exists phones;
drop table if exists orders;
drop table if exists users;
drop table if exists roles;
drop table if exists user2role;
drop table if exists order2phone;

create table colors (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  code VARCHAR(50),
  UNIQUE (code)
);

create table phones (
  id BIGINT AUTO_INCREMENT primary key,
  brand VARCHAR(50) NOT NULL,
  model VARCHAR(254) NOT NULL,
  price FLOAT,
  displaySizeInches FLOAT,
  weightGr SMALLINT,
  lengthMm FLOAT,
  widthMm FLOAT,
  heightMm FLOAT,
  announced DATETIME,
  deviceType VARCHAR(50),
  os VARCHAR(100),
  displayResolution VARCHAR(50),
  pixelDensity SMALLINT,
  displayTechnology VARCHAR(50),
  backCameraMegapixels FLOAT,
  frontCameraMegapixels FLOAT,
  ramGb FLOAT,
  internalStorageGb FLOAT,
  batteryCapacityMah SMALLINT,
  talkTimeHours FLOAT,
  standByTimeHours FLOAT,
  bluetooth VARCHAR(50),
  positioning VARCHAR(100),
  imageUrl VARCHAR(254),
  description VARCHAR(4096),
  CONSTRAINT UC_phone UNIQUE (brand, model)
);

create table phone2color (
  phoneId BIGINT,
  colorId BIGINT,
  CONSTRAINT FK_phone2color_phoneId FOREIGN KEY (phoneId) REFERENCES phones (id) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT FK_phone2color_colorId FOREIGN KEY (colorId) REFERENCES colors (id) ON DELETE CASCADE ON UPDATE CASCADE
);

create table stocks (
  phoneId BIGINT NOT NULL,
  stock SMALLINT NOT NULL,
  reserved SMALLINT ,
  UNIQUE (phoneId),
  CONSTRAINT FK_stocks_phoneId FOREIGN KEY (phoneId) REFERENCES phones (id) ON DELETE CASCADE ON UPDATE CASCADE
);

create table orders (
    number SMALLINT AUTO_INCREMENT,
    firstName VARCHAR(50) NOT NULL,
    lastName VARCHAR(50) NOT NULL,
    address VARCHAR(50),
    phone VARCHAR(25),
    id VARCHAR(50),
    data DATETIME,
    orderDescription VARCHAR(4096),
    delivery FLOAT,
    status VARCHAR(25),
    UNIQUE (id)
);
 
create table order2phone (
    phoneId BIGINT,
    orderId VARCHAR(50),
    quantity SMALLINT NOT NULL,
    CONSTRAINT FK_user2phone_phoneId FOREIGN KEY (phoneId) REFERENCES phones (id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT FK_user2phone_orderId FOREIGN KEY (orderId) REFERENCES orders (id) ON DELETE CASCADE ON UPDATE CASCADE
);

create table users (
    username VARCHAR(50),
    password VARCHAR(100),
    enabled BOOLEAN,
    UNIQUE (username)
);

create table roles (
    id BIGINT AUTO_INCREMENT primary key,
    role VARCHAR(50),
    UNIQUE (id)
);

create table user2role (
    username VARCHAR(50),
    roleId BIGINT,
    CONSTRAINT FK_user2role_userId FOREIGN KEY (username) REFERENCES users (username) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT FK_user2role_roleId FOREIGN KEY (roleId) REFERENCES roles (id) ON DELETE CASCADE ON UPDATE CASCADE
);
