drop database if exists qlsv;
create database  qlsv;
use  qlsv;
create table Student(
                        id int primary key  auto_increment,
                        name varchar(255),
                        age int,
                        gen bit,
                        address varchar(255)
);
insert into Student(name,age,gen, address) values
                                               ('Chã đẹp chai',19,0,'Hà Lội'),
                                               ('Hùng hx',18,1,'Nghệ An'),
                                               ('Tuấn Anh',90,1,'Hà Lội');
delimiter //
create procedure PROC_GETALL()
begin
select * from Student;
end //;
delimiter //
create procedure PROC_CREATESTUDENT(nameNew varchar(255),ageNew int, genNew bit, addressNew varchar(255))
begin
INSERT INTO Student ( name, age, gen, address) values (nameNew,ageNew,genNew,addressNew);
end //;
delimiter //
create procedure PROC_DELETESTUDENT(idDel int)
begin
delete  from  Student where  id =idDel;
end //;
delimiter //
create procedure PROC_UPDATESTUDENT(idUp int,nameUp varchar(255),ageUp int, genUp bit, addressUp varchar(255))
begin
UPDATE Student set name =nameUp, age=ageUp,gen =genUp,address=addressUp where  id =idUp;
end //;
create procedure PROC_FINDBYID(idSearch int )
begin
select * from Student where id = idSearch;
end //;
create procedure PROC_SEARCHBYNAME(nameSearch varchar(255) )
begin
select * from Student where name like concat('%',nameSearch,'%');
end //
// delimiter ;
create table Accounts(
                         id int primary key auto_increment,
                         username varchar(255) unique ,
                         password varchar(100),
                         role tinyint default(0)
);
insert into Accounts(username, password,role) values
                                                  ('admin123','123456',1),
                                                  ('hung123','123456',0);

--     bảng Product
CREATE table Product(
                        id int primary key  auto_increment,
                        name varchar(255),
                        image_url varchar(255),
                        price float,
                        quantity int,
                        description text,
                        status bit default 1
);
insert into Product (name,image_url,price,quantity) values
                                                        ('sản phẩm 1','https://cdn.hoanghamobile.com/i/productlist/ts/Uploads/2023/03/31/image-removebg-preview.png',1000,50),
                                                        ('sản phẩm 2','https://cdn.hoanghamobile.com/i/productlist/ts/Uploads/2023/02/01/1111.png',1600,50),
                                                        ('sản phẩm 3','https://cdn.hoanghamobile.com/i/productlist/ts/Uploads/2023/02/01/1111.png',1200,50),
                                                        ('sản phẩm 4','https://cdn.hoanghamobile.com/i/productlist/ts/Uploads/2022/09/07/image-removebg-preview-4.png',1400,50),
                                                        ('sản phẩm 5','https://cdn.hoanghamobile.com/i/productlist/ts/Uploads/2023/03/13/c55-1-den.png',1100,50),
                                                        ('sản phẩm 6','https://cdn.hoanghamobile.com/i/productlist/ts/Uploads/2022/07/11/image-removebg-preview-31.png',1800,50);

-- bảng order + cart
create table Orders (
                        orderId int primary key auto_increment,
                        user_id int not null ,
                        total float,
                        createdDate date default(now()),
                        type bit default 1, -- loại : 1 là hoá đơn, 0 là giỏ hàng
                        status tinyint default 1,
                        phone varchar(11),
                        address varchar(255),
                        foreign key (user_id) references Accounts(id)
);
create table  OrderDetail(
                             id int primary key  auto_increment,
                             order_id int not null ,
                             product_id int not null ,
                             product_price float,
                             quantity int
);
delimiter //
create procedure PROC_LOGIN(user varchar(255),pass varchar(100))
begin
SELECT a.*,o.orderId from Accounts a right join orders o on a.id=o.user_id where a.username like user and a.password like pass and o.type=0;
end //;
create procedure PROC_REGISTER(user varchar(255),pass varchar(100))
begin
    declare userIdLast int;
INSERT INTO Accounts(username, password) value (user,pass);
select distinct last_insert_id() into userIdLast from  Accounts;
INSERT INTO Orders(user_id,type) values (userIdLast,0);
end //;
create procedure PROC_FINDBYUSERNAME(user varchar(255))
begin
select * from Accounts where username like user;
end //;
// delimiter

-- tạo các procerdure
delimiter //

# đối tượng hoá đơn
# thêm mới
create procedure PROC_CreateNewOrder(userId int,totalP float,addressP varchar(255),phoneP varchar(11))
begin
insert into Orders(user_id,total,address,phone) values (userId,totalP,addressP,phoneP);
end //;
# chi tiết hoá đơn:
# thêm mới
create procedure PROC_CreateOrderDetail(orderId int , productId int, productPrice float,
                                        productQuantity int)
begin
insert into OrderDetail(order_id, product_id, product_price, quantity)
values (orderId,productId,productPrice,productQuantity);
end //;
# xoá
create procedure PROC_DeleteOrderDetail(orderDetailId int)
begin
delete  from OrderDetail where id = orderDetailId;
end //;
# sửa số lượng
create procedure PROC_ChangeQuantity(idUp int,quantityUp int)
begin
update OrderDetail set quantity=quantityUp where id=idUp;
end //;
# xoá toàn bộ giỏ hàng
create procedure PROC_ClearCartDetail(orderId int)
begin
delete  from OrderDetail where order_id = orderId;
end //;

create procedure PROC_FindAllProduct()
begin
select  * from Product;
end //;
create procedure PROC_FindProductById(proId int)
begin
SELECT * from product where id =proId;
end //;
create procedure PROC_FindListOrderDetail(orderId int)
begin
select  od.*,p.name,p.image_url,p.description from OrderDetail od join product p on p.id = od.product_id where order_id = orderId;
end //;
// delimiter
call PROC_REGISTER('hunggggđggg','123456');