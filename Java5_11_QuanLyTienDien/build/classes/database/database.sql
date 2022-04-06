USE MASTER
GO
IF  EXISTS (SELECT name 
			FROM sys.databases 
			WHERE name = 'QUANLYTIENDIEN')
	DROP DATABASE QUANLYTIENDIEN
GO
create database QUANLYTIENDIEN
go
use QUANLYTIENDIEN
go
--drop database QUANLYTIENDIEN
--tạo bảng HOTIEUTHU
CREATE TABLE HOTIEUTHU(
	maKH char(5) primary key,
	hoTen nvarchar(50) NOT NULL,
	CMND varchar(12) NOT NULL,
	diaChi nvarchar(50) NULL,
	gioiTinh nvarchar(3) NULL,
	ngaySinh date NULL,
	sdt varchar(12) NULL,
	ngayDangKi date NULL,
	loaiDien nvarchar(20) not null
)
go
--thêm dữ liệu vào bảng HOTIEUTHU
insert into HOTIEUTHU
values('KH001',N'Trần Văn Nguyên','021646480815',N'Vĩnh Phúc',N'Nam','12/09/1997','0954755862','06/06/2020',N'Sinh hoạt'),
	  ('KH002',N'Nguyễn Thế Anh','045246480814',N'Thái Bình',N'Nam','11/23/2000','0346864934','05/11/2019',N'Hành chính'),
	  ('KH003',N'Chu Văn An','021346497524',N'Bắc Giang',N'Nam','02/26/1980','0978371315','10/12/2011',N'Kinh doanh'),
	  ('KH004',N'Nguyễn Phương Hằng','031346497525',N'Phú Thọ',N'Nữ','11/15/1981','0974911469','03/17/2015',N'Sinh hoạt'),
	  ('KH005',N'Trần Văn Trà','021346497526',N'Đà Nẵng',N'Nam','04/26/1978','0918614525','12/21/2019',N'Sản xuất'),
	  ('KH006',N'Doãn Chí Bình','111346497527',N'Hà Nội',N'Nam','06/22/1984','0946999935','07/06/2018',N'Hành chính'),
	  ('KH007',N'Nguyễn Thủy Cung','131346497526',N'Vĩnh Long',N'Nữ','03/29/1979','0989048777','02/09/2017',N'Sinh hoạt'),
	  ('KH008',N'Nguyễn Văn Mạnh','141346497526',N'Hà Nam',N'Nam','01/07/1982','0843120976','07/21/2020',N'Kinh doanh'),
	  ('KH009',N'Huỳnh Xuân Anh','153456497526',N'Thái Nguyên',N'Nam','06/10/1974','0976997092','02/18/2018',N'Kinh doanh'),
	  ('KH010',N'Bùi Văn Sơn','141344533352',N'Tuyên Quang',N'Nam','08/11/1965','0343741618','07/07/2019',N'Hành chính'),
	  ('KH011',N'Dương Thị Hiên','441346491234',N'Ninh Bình',N'Nữ','12/28/1980','0987803648','08/03/2020',N'Sản xuất'),
	  ('KH012',N'Nguyễn Hồng Anh','245646497526',N'Lào Cai',N'Nam','10/09/1970','0358078887','06/15/2016',N'Sản xuất'),
	  ('KH013',N'Trần Thị Phượng','789446497526',N'Điện Biên',N'Nam','12/09/1956','0954755858','06/06/2014',N'Sinh hoạt'),
	  ('KH014',N'Phạm Bình Minh','789456123012',N'Hưng Yên',N'Nam','05/04/2000','0346864697','04/11/2020',N'Hành chính'),
	  ('KH015',N'Võ Hoài Linh','657894123789',N'Hà Giang',N'Nam','04/29/1973','0978371436','09/16/2019',N'Kinh doanh')
GO
--tạo bảng THANG
CREATE TABLE THANG(
	maThang char(7) primary key,
	tenThang nvarchar(20) NULL,
)
go
--thêm dữ liệu vào bảng THANG
insert into THANG
values 
	  ('T012021',N'Thang 1/2021'),
	  ('T022021',N'Thang 2/2021'),
	  ('T032021',N'Thang 3/2021'),
	  ('T042021',N'Thang 4/2021'),
	  ('T052020',N'Thang 5/2020'),
	  ('T062020',N'Thang 6/2020'),
	  ('T072020',N'Thang 7/2020'),
	  ('T082020',N'Thang 8/2020'),
	  ('T092020',N'Thang 9/2020'),
      ('T102020',N'Thang 10/2020'),
	  ('T112020',N'Thang 11/2020'),
	  ('T122020',N'Thang 12/2020')
go
--tạo bảng HOADON
CREATE TABLE HOADON(
	maHD char(10) NOT NULL,
	maKH char(5) NOT NULL,
	ldtt int NULL,
	tien money NULL,
	primary key (maHD, maKH),
	constraint HOTIEUTHU_HOADON foreign key (maKH) references HOTIEUTHU (maKH)
)
go
--thêm dữ liệu vào bảng HOADON
insert into HOADON
values('HD001','KH001',null,null),
	  ('HD002','KH002',null,null),
	  ('HD003','KH003',null,null),
	  ('HD004','KH004',null,null),
	  ('HD005','KH005',null,null),
	  ('HD006','KH006',null,null),
	  ('HD007','KH007',null,null),
	  ('HD008','KH008',null,null),
	  ('HD009','KH009',null,null),
	  ('HD010','KH010',null,null),
	  ('HD011','KH011',null,null),
	  ('HD012','KH012',null,null),
	  ('HD013','KH013',null,null),
	  ('HD014','KH014',null,null),
	  ('HD015','KH015',null,null)

go
--Tạo bảng CHISODIEN
CREATE TABLE CHISODIEN(
	maKH char(5) NOT NULL,
	maThang char(7) NOT NULL,
	chisocu int NULL,
	chisomoi int NULL,
	primary key(maKH, maThang),
	constraint HOTIEUTHU_CHISODIEN foreign key (maKH) references HOTIEUTHU (maKH),
	constraint THANG_CHISODIEN foreign key (maThang) references THANG (maThang)
)
go
--Thêm dữ liệu vào bảng CHISODIEN
insert into CHISODIEN
values('KH001','T122020',3598,3700),
	  ('KH002','T112020',4633,4906),
	  ('KH003','T102020',1200,1250),
	  ('KH004','T082020',4001,5678),
	  ('KH005','T092020',3942,4507),
	  ('KH006','T052020',6045,7320),
	  ('KH007','T062020',5238,5500),
	  ('KH008','T012021',4675,4750),
	  ('KH009','T032021',5165,5616),
	  ('KH010','T042021',7496,8412),
	  ('KH011','T022021',156,560),
	  ('KH012','T112020',8104,8915),
	  ('KH013','T012021',3365,3652),
	  ('KH014','T122020',4512,4909),
	  ('KH015','T102020',1500,1569)
go
--tạo bảng THONGKE
CREATE TABLE THONGKE(
	maKH char(5 ) NOT NULL,
	maThang char(7) NOT NULL,
	maHD char(10) NOT NULL,
	payment bit default 0,
	primary key (maKH, maThang, maHD),
	constraint HOTIEUTHU_THONGKE foreign key (maKH) references HOTIEUTHU (maKH),
	constraint THANG_THONGKE foreign key (maThang) references THANG (maThang),
	constraint HOADON_THONGKE foreign key (maHD, maKH) references HOADON (maHD, maKH)
)
go
--thêm dữ liệu vào bảng THONGKE
insert into THONGKE
values('KH001','T122020','HD001',0),
	  ('KH002','T112020','HD002',0),
	  ('KH003','T102020','HD003',0),
	  ('KH004','T082020','HD004',1),
	  ('KH005','T092020','HD005',0),
	  ('KH006','T052020','HD006',0),
	  ('KH007','T062020','HD007',1),
	  ('KH008','T012021','HD008',0),
	  ('KH009','T032021','HD009',0),
	  ('KH010','T042021','HD010',0),
	  ('KH011','T022021','HD011',1),
	  ('KH012','T112020','HD012',0),
	  ('KH013','T012021','HD013',0),
	  ('KH014','T122020','HD014',0),
	  ('KH015','T102020','HD015',0)
	  
go
--tạo bảng TAIKHOAN
CREATE TABLE TAIKHOAN(
	tai_khoan varchar(15) primary key,
	mat_khau varchar(15) NOT NULL,
	cauhoi nvarchar(50) null,
	traloi nvarchar(50) null
)
go
--thêm dữ liệu vào bảng TAIKHOAN
insert TAIKHOAN
values('admin','123',null,null)
go
create function getCSC(@maKH char(5), @maThang char(7))
returns int
as 
	begin
		declare @CSC int;
		select @CSC = chisocu from CHISODIEN
			where maKH = @maKH 
				AND maThang = @maThang;
		return @CSC;
	end;
go

create function getCSM(@maKH char(5), @maThang char(7))
returns int
as 
	begin
		declare @CSM int;
		select @CSM = chisomoi from CHISODIEN
			where maKH = @maKH 
				AND maThang = @maThang;
		return @CSM;
	end;
go

create function getLDTT(@maKH char(5), @maThang char(7)) --get luong dien tieu thu
returns int
as 
	begin
		return dbo.getcsm(@maKH, @maThang) - dbo.getcsc(@maKH, @maThang);
	end;
go

create function getMoney(@maKH char(5), @loaiDien nvarchar(20), @maThang char(7)) --tra ve so tien cua hoadon
returns money
as
	begin 
		declare @tien money;
		if(@loaiDien = N'Sinh hoạt')
			select @tien = (select dbo.getLDTT(@maKH, @maThang) * 2014);
		if(@loaiDien = N'Sản xuất')
			select @tien = (select dbo.getLDTT(@maKH, @maThang)) * 1611;
		if(@loaiDien = N'Kinh doanh')
			select @tien = (select dbo.getLDTT(@maKH, @maThang)) * 2442;
		if(@loaiDien = N'Hành chính')
			select @tien = (select dbo.getLDTT(@maKH, @maThang)) * 1659;
		return @tien;
	end;
go
	
create proc updateHoaDon(@maKH char(5), @maThang char(7)) --TAO THU TUC UPDATE DU LIEU
as 
	update hoadon
	set ldtt = (select dbo.getLDTT(@maKH, @maThang)),
		tien = (select dbo.getMoney(@maKH,(select loaiDien from HOTIEUTHU where maKH = @maKH),@maThang))
	where maKH = @maKH
go
--gọi thủ tục update data cho lượng tiêu thụ và tiền 
updateHoaDon 'KH001','T122020' 
go
updateHoaDon 'KH002','T112020'
go
updateHoaDon 'KH003','T102020'
go
updateHoaDon 'KH004','T082020'
go
updateHoaDon 'KH005','T092020'
go
updateHoaDon 'KH006','T052020'
go
updateHoaDon 'KH007','T062020'
go
updateHoaDon 'KH008','T012021'
go
updateHoaDon 'KH009','T032021'
go
updateHoaDon 'KH010','T042021'
go
updateHoaDon 'KH011','T022021'
go
updateHoaDon 'KH012','T112020'
go
updateHoaDon 'kh013','T012021'
go
updateHoaDon 'kh014','T122020'
go
updateHoaDon 'kh015','T102020'
go