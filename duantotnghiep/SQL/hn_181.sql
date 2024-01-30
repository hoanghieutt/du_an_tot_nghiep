use master
go
if EXISTS ( select name
                from sys.databases
                where name = N'hn_181_datn' )
    drop database hn_181_datn
create database hn_181_datn
go
use hn_181_datn
go

create table [roles] (
	[id] int primary key identity(1, 1),
	[name] nvarchar(max) not null,
	[status] int default 0,
	[created_date] date,
	[updated_date] date
)
go

create table [admins] (
	[id] int primary key identity(1, 1),
	[name] nvarchar(max),
	[email] nvarchar(max),
	[phone] nvarchar(10),
	[avatar] nvarchar(max),
	[address] nvarchar(max),
	[password] nvarchar(max),
	[status] int default 0,
	[created_date] date,
	[updated_date] date,
	[role_id] int,
	foreign key (role_id) references [roles](id),
);
go

create table [brands] (
	[id] int primary key identity(1, 1),
	[name] nvarchar(max) not null,
	[status] int default 0,
	[description] nvarchar(max),
	[created_date] date,
	[updated_date] date
);
go

create table [colors] (
	[id] int primary key identity(1, 1),
	[name] nvarchar(max) not null,
	[status] int default 0,
	[description] nvarchar(max),
	[created_date] date,
	[updated_date] date
);
go

create table [categories] (
	[id] int primary key identity(1, 1),
	[name] nvarchar(max) not null,
	[status] int default 0,
	[description] nvarchar(max),
	[created_date] date,
	[updated_date] date
);
go

create table [materials] (
	[id] int primary key identity(1, 1),
	[name] nvarchar(max) not null,
	[status] int default 0,
	[description] nvarchar(max),
	[created_date] date,
	[updated_date] date
);
go

create table [sizes] (
	[id] int primary key identity(1, 1),
	[name] nvarchar(max) not null,
	[status] int default 0,
	[shirt_length] int,
	[shirt_width] int,
	[sleeve_length] int,
	[shoulder_length] int,
	[description] nvarchar(max),
	[created_date] date,
	[updated_date] date
);
go

create table [discounts] (
	[id] int primary key identity(1, 1),
	[name] nvarchar(max) not null,
	[status] int default 0,
	[description] nvarchar(max),
	[discount] float,
	[start_date] date,
	[end_date] date
);
go

create table [products] (
	[id] int primary key identity(1, 1),
	[name] nvarchar(max),
	[status] int default 0,
	[description] nvarchar(max),
	[created_date] date,
	[updated_date] date,
	[category_id] int,
	[brand_id] int,
	[material_id] int,
	[discount_id] int,
	foreign key (category_id) references [categories](id),
	foreign key (brand_id) references [brands](id),
	foreign key (material_id) references [materials](id),
	foreign key (discount_id) references [discounts](id),
);
go

create table [product_details] (
	[id] int primary key identity(1, 1),
	[quantity] int,
	[cost] float,
	[price] float,
	[weight] float,
	[status] int default 0,
	[created_date] date,
	[updated_date] date,
	[product_id] int,
	[size_id] int,
	[color_id] int,
	foreign key (product_id) references [products](id),
	foreign key (size_id) references [sizes](id),
	foreign key (color_id) references [colors](id),
);
go

create table [images] (
	[id] int primary key identity(1, 1),
	[product_id] int,
	[name] nvarchar(max),
	[url_image] nvarchar(max),
	[status] int,
	[created_date] date,
	[updated_date] date,
	foreign key (product_id) references [products](id),
);
go

create table [customers] (
	[id] int primary key identity(1, 1),
	[name] nvarchar(max),
	[phone] nvarchar(10),
	[avatar] nvarchar(max),
	[password] nvarchar(max),
	[status] int,
	[created_date] date,
	[updated_date] date,
);
go

create table [addresses] (
	[id] int primary key identity(1, 1),
	[customer_id] int,
	[ward] nvarchar(max),
	[district] nvarchar(max),
	[city] nvarchar(max),
	[full_address] nvarchar(max),
	[status] int,
	[created_date] date,
	[updated_date] date,
	foreign key (customer_id) references [customers](id),
);
go


create table [evaluates] (
	[id] int primary key identity(1, 1),
	[star] int,
	[content] nvarchar(max),
	[created_date] date,
	[updated_date] date,
	[customer_id] int,
	[product_detail_id] int,
	foreign key (customer_id) references [customers](id),
	foreign key (product_detail_id) references [product_details](id),
);
go

create table [carts] (
	[id] int primary key identity(1, 1),
	[status] int,
	[created_date] date,
	[updated_date] date,
	[customer_id] int,
	foreign key (customer_id) references [customers](id),
);
go

create table [cart_details] (
	[id] int primary key identity(1, 1),
	[quantity] int,
	[price] float,
	[status] int,
	[created_date] date,
	[updated_date] date,
	[cart_id] int,
	[product_detail_id] int,
	foreign key (cart_id) references [carts](id),
	foreign key (product_detail_id) references [product_details](id),
);
go

create table [transactions] (
	[id] int primary key identity(1, 1),
	[name] nvarchar(max),
	[description] nvarchar(max),
	[status] int,
	[created_date] date,
	[updated_date] date,
);
go

create table [orders] (
	[id] int primary key identity(1, 1),
	[phone] nvarchar(10),
	[username] nvarchar(max),
	[total_price] float,
	[ship_cost] float,
	[weight] float,
	[note] nvarchar(max),
	[shopping] nvarchar(max),
	[address] nvarchar(max),
	[status] int,
	[confirm_date] date,
	[ship_date] date,
	[success_date] date,
	[created_date] date,
	[updated_date] date,
	[admin_id] int,
	[customer_id] int,
	[transaction_id] int,
	foreign key (admin_id) references [admins](id),
	foreign key (customer_id) references [customers](id),
	foreign key (transaction_id) references [transactions](id),
);
go

create table [order_details] (
	[id] int primary key identity(1, 1),
	[quantity] int,
	[price] float,
	[status] int,
	[created_date] date,
	[updated_date] date,
	[order_id] int,
	[product_detail_id] int,
	foreign key (order_id) references [orders](id),
	foreign key (product_detail_id) references [product_details](id),
);
go