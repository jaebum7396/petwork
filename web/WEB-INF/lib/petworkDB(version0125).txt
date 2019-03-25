--테이블 삭제
drop table find;
drop table pet;
drop table parcel_img;
drop table parcel;
drop table protect;
drop table animal;
drop table sharing_img;
drop table sharing_comment;
drop table sharing;
drop table product_category;
drop table free_img;
drop table free_comment;
drop table free;
drop table race;
drop table district;
drop table city;
drop table faq;
drop table doctor;
drop table member;

--시퀀스 삭제
drop sequence seq_dog_no;
drop sequence seq_cat_no;
drop sequence seq_etc_no;
drop sequence seq_parcel_post_no;
drop sequence seq_parcel_img_no;
drop sequence seq_sharing_post_no;
drop sequence seq_sharing_img_no;
drop sequence seq_sharing_comment_no;
drop sequence seq_free_post_no;
drop sequence seq_free_img_no;
drop sequence seq_free_comment_no;
drop sequence seq_protect_post_no;
drop sequence seq_find_post_no;
drop sequence seq_faq_no;
drop sequence seq_doc_no;
drop sequence seq_pet_no;


--시퀀스 생성
create sequence seq_dog_no;
create sequence seq_cat_no;
create sequence seq_etc_no;
create sequence seq_parcel_post_no;
create sequence seq_parcel_img_no;
create sequence seq_sharing_post_no;
create sequence seq_sharing_img_no;
create sequence seq_sharing_comment_no;
create sequence seq_free_post_no;
create sequence seq_free_img_no;
create sequence seq_free_comment_no;
create sequence seq_protect_post_no;
create sequence seq_find_post_no;
create sequence seq_faq_no;
create sequence seq_doc_no;
create sequence seq_pet_no
start with 1 increment BY 1 maxvalue 10000;




--멤버 테이블 생성
create table member(
member_id varchar2(30),
member_pwd varchar2(300) not null,
member_name varchar2(20) not null,
member_birth date not null,
member_gender char(1) check(member_gender in('M','F')),
member_phone char(11) not null,
member_email varchar2(30) unique,
member_address varchar2(500) not null,
member_yn char(1) default 'N' check(member_yn in('Y','N')),
ent_date date default sysdate,
admin_yn char(1) default 'N' check(admin_yn in('Y','N')),
constraint pk_member primary key(member_id)
);
comment on column member.member_id is '회원아이디';
comment on column member.member_pwd is '회원비밀번호';
comment on column member.member_name is '회원이름';
comment on column member.member_birth is '생년월일';
comment on column member.member_gender is '성별(M,F)';
comment on column member.member_email is '이메일';
comment on column member.member_phone is '전화번호';
comment on column member.member_address is '주소';
comment on column member.member_yn is '탈퇴유무(Y,N)';
comment on column member.ent_date is '가입일';
comment on column member.admin_yn is '관리자유무(Y,N)';

--동물대분류 테이블 생성
create table race(
race_code varchar2(10),
race_name varchar2(30),
constraint pk_race_code primary key(race_code)
);
comment on column race.race_code is '종류코드';
comment on column race.race_name is '종류';


--동물중분류 테이블 생성
create table animal (
race_code	varchar(255)	not null,
animal_no	varchar(255)	not null,
animal_name	varchar(255)	unique,
constraint fk_race_code foreign key(race_code) references race(race_code) on delete cascade,
constraint pk_animal_code primary key(race_code,animal_no)
);
comment on column animal.animal_no is '동물번호';
comment on column animal.race_code is '동물코드';
comment on column animal.animal_name is '동물이름';

--펫 테이블 생성
--create table pet(
--member_id varchar2(15),
--pet_no number,
--race_code varchar2(10),
--animal_no varchar2(255),
--pet_birth date not null,
--pet_gender char(1) check(pet_gender in('M','F')),
--pet_name varchar2(20) default '없음',
--pet_yn char(1) default 'N' check(pet_yn in('Y','N')),
--pet_neutering char(1) default'N' check(pet_neutering in('Y','N')),
--constraint pk_pet_no primary key (pet_no),
--constraint fk_pet_member_id foreign key (member_id) references member(member_id),
--constraint fk_pet_race_code foreign key (race_code,animal_no) references animal(race_code,animal_no)
--);
--comment on column pet.member_id is '회원아이디';
--comment on column pet.pet_no is '인식번호';
--comment on column pet.pet_name is '동물이름';
--comment on column pet.pet_birth is '생년월일';
--comment on column pet.pet_gender is '성별(M,F)';
--comment on column pet.race_code is '품종코드';
--comment on column pet.pet_yn is '삭제여부';
--comment on column pet.pet_neutering is '중성화유무(Y,N)';
--comment on column pet.animal_no is '동물번호';

--펫 테이블 수정 2019/01/07
create table pet(
member_id varchar2(15),
pet_no number,
pet_identify_no varchar2(20),
race_code varchar2(10),
animal_no varchar2(255),
pet_birth date not null,
pet_gender char(1) check(pet_gender in('M','F')),
pet_name varchar2(20) default '없음',
pet_yn char(1) default 'N' check(pet_yn in('Y','N')),
pet_neutering char(1) default'N' check(pet_neutering in('Y','N')),
constraint pk_pet_no primary key (pet_no),
constraint fk_pet_member_id foreign key (member_id) references member(member_id) on delete cascade,
constraint fk_pet_race_code foreign key (race_code,animal_no) references animal(race_code,animal_no) on delete cascade
);
comment on column pet.member_id is '회원아이디';
comment on column pet.pet_no is '애완동물번호';
comment on column pet.pet_identify_no is '인식번호';
comment on column pet.pet_name is '동물이름';
comment on column pet.pet_birth is '생년월일';
comment on column pet.pet_gender is '성별(M,F)';
comment on column pet.race_code is '품종코드';
comment on column pet.pet_yn is '삭제여부';
comment on column pet.pet_neutering is '중성화유무(Y,N)';
comment on column pet.animal_no is '동물번호';

--도시DB
create table city(
city_code varchar2(255),
city_name varchar2(255),
constraint pk_city primary key(city_code)
);
comment on column city.city_code is '도시코드';
comment on column city.city_name is '시.도이름';


create table district(
city_code varchar2(255),
district_code varchar2(255),
district_name varchar2(255),
constraint pk_district primary key(district_code,city_code),
constraint fk_district_city foreign key (city_code) references city(city_code) on delete cascade
);
comment on column district.district_code is '구 코드';
comment on column district.district_name is '구 이름';
comment on column district.city_code is '도시코드';


--커뮤니티DB
--분양db 생성
create table parcel(
parcel_post_title varchar2(100) not null,
parcel_post_head varchar2(30),
parcel_post_no number,
parcel_post_date date default sysdate,
member_id varchar2(30),
animal_no varchar2(10),
parcel_post_pet_birth date not null,
parcel_post_address varchar2(500) not null,
parcel_post_pet_gender char(1) check(parcel_post_pet_gender in('M','F')),
parcel_post_pet_neutering char(1) default('N') check(parcel_post_pet_neutering in('Y','N')),
parcel_post_price number not null,
parcel_post_content long not null,
parcel_post_yn char(1) default('N') check(parcel_post_yn in('Y','N')),
parcel_post_vaccination varchar2(255),
constraint fk_parcel_parcel_post_head foreign key (parcel_post_head,animal_no) references animal(race_code,animal_no) on delete cascade,
constraint pk_parcel_post_no primary key (parcel_post_no),
constraint fk_parcel_member_id foreign key (member_id) references member(member_id) on delete cascade
);
comment on column parcel.parcel_post_title is '제목';
comment on column parcel.parcel_post_head is '글머리(품종코드)';
comment on column parcel.parcel_post_no is '게시글번호';
comment on column parcel.parcel_post_title is '제목';
comment on column parcel.parcel_post_date is '작성날짜';
comment on column parcel.member_id is '회원 아이디';
comment on column parcel.animal_no is '동물번호';
comment on column parcel.parcel_post_pet_birth is '동물생일';
comment on column parcel.parcel_post_address is '분약지역';
comment on column parcel.parcel_post_pet_gender is '성별';
comment on column parcel.parcel_post_pet_neutering is '중성화유무';
comment on column parcel.parcel_post_price is '분양가';
comment on column parcel.parcel_post_content is '내용';
comment on column parcel.parcel_post_yn is '분양유무';
comment on column parcel.parcel_post_vaccination is '예방접종';



--분양 사진db
create table parcel_img(
parcel_img_no number,
parcel_img_address long not null,
parcel_post_no number,
parcel_main_img char(1) default ('S') check(parcel_main_img in('M','S')),
constraint pk_parcel_img_no primary key (parcel_img_no,parcel_post_no),
constraint fk_parcel_post_no foreign key (parcel_post_no) references parcel(parcel_post_no) on delete cascade
);
comment on column parcel_img.parcel_img_no is '분양사진번호';
comment on column parcel_img.parcel_img_address is '분양사진경로';
comment on column parcel_img.parcel_post_no is '분양글번호';
comment on column parcel_img.parcel_main_img is '메인사진여부';



--상품 카테고리
create table product_category(
product_category_code varchar2(30),
product_category_name varchar2(255),
constraint pk_product_category_code primary key(product_category_code)
);
comment on column product_category.product_category_code is '상품코드';
comment on column product_category.product_category_name is '상품이름';

--나눔 DB
create table sharing(
sharing_post_title varchar2(100) not null,
sharing_post_no number,
sharing_post_date date default sysdate,
sharing_post_writer varchar2(30),
sharing_post_address varchar2(500) not null,
sharing_post_yn char(1) default('N') check (sharing_post_yn in('Y','N')),
sharing_post_content varchar2(4000) not null,
product_category_code varchar2(30),
race_code varchar2(30),
sharing_post_count number DEFAULT 0,
constraint pk_sharing_post_no primary key (sharing_post_no),
constraint fk_sharing_writer foreign key (sharing_post_writer) references member(member_id),
constraint fk_share_product_category_code foreign key (product_category_code) references product_category(product_category_code),
constraint fk_share_race_code foreign key (race_code) references race(race_code)
);
comment on column sharing.sharing_post_title is '제목';
comment on column sharing.sharing_post_no is '게시글번호';
comment on column sharing.sharing_post_date is '작성날짜';
comment on column sharing.sharing_post_writer is '나눔게시판작성자';
comment on column sharing.sharing_post_address is '주소';
comment on column sharing.sharing_post_yn is '거래여부';
comment on column sharing.sharing_post_content is '내용';
comment on column sharing.product_category_code is '나눔상품코드';
comment on column sharing.race_code is '나눔품종코드';
comment on column sharing.sharing_post_count is '나눔조회수';

--나눔 댓글 DB
create table sharing_comment(
sharing_comment_no number,
sharing_comment_level number default 1,
sharing_comment_ref number,
sharing_comment_writer varchar2(30),
sharing_comment_date date default sysdate,
sharing_comment_content varchar2(4000) not null,
sharing_post_no number,
constraint pk_sharing_comment_no primary key (sharing_comment_no),
constraint fk_sharing_comment_writer foreign key (sharing_comment_writer) references member(member_id) on delete set null,
constraint fk_sharing_post_ref foreign key(sharing_post_no) references sharing(sharing_post_no) on delete cascade,
constraint fk_sharing_comment_ref foreign key (sharing_comment_ref) references sharing_comment(sharing_comment_no) on delete cascade
);
comment on column sharing_comment.sharing_comment_no is '나눔 댓글번호';
comment on column sharing_comment.sharing_comment_writer is '나눔댓글작성자';
COMMENT ON COLUMN sharing_comment.sharing_comment_level IS '나눔게시판댓글 레벨';
COMMENT ON COLUMN sharing_comment.sharing_comment_ref IS '나눔게시판댓글 참조번호';
comment on column sharing_comment.sharing_comment_date is '나눔 작성일';
comment on column sharing_comment.sharing_comment_content is '나눔 내용';
comment on column sharing_comment.sharing_post_no is '나눔 글번호';


--나눔사진DB
create table sharing_img(
sharing_img_no number,
sharing_img_address long not null,
sharing_post_no number,
constraint fk_sharing_img_post_no foreign key (sharing_post_no) references sharing(sharing_post_no),
constraint pk_sharing_img_no primary key (sharing_img_no,sharing_post_no)
);
comment on column sharing_img.sharing_img_no is '나눔사진번호';
comment on column sharing_img.sharing_img_address is '나눔사진경로';
comment on column sharing_img.sharing_post_no is '나눔글번호';


--자유게시판DB
create table free(
free_post_title varchar2(100) not null,
free_post_no number,
free_post_date date default sysdate,
free_post_writer varchar2(30),
free_post_content varchar2(4000) not null,
race_code varchar2(30),
free_post_count number DEFAULT 0,
constraint pk_free_post_no primary key(free_post_no),
constraint fk_free_member_id foreign key (free_post_writer) references member(member_id) on delete cascade,
constraint fk_free_race_code foreign key (race_code) references race(race_code) on delete cascade
);
comment on column free.free_post_title is '자유게시판제목';
comment on column free.free_post_no is '자유게시판번호';
comment on column free.free_post_date is '자유게시판작성일';
comment on column free.free_post_writer is '자유게시판작성자';
comment on column free.free_post_content is '자유게시판내용';
comment on column free.race_code is '품종코드';
comment on column free.free_post_count is '자유게시판조회수';

--자유게시판댓글DB
create table free_comment(
free_post_no number,
free_comment_no number,
free_comment_level number default 1,
free_comment_ref number,
free_comment_writer varchar2(30),
free_comment_date date default sysdate,
free_comment_content long not null,
constraint pk_free_comment_no primary key(free_comment_no),
constraint fk_free_post_no foreign key(free_post_no) references free(free_post_no) on delete cascade,
constraint fk_free_comment_ref foreign key(free_comment_ref) references free_comment(free_comment_no) on delete cascade,
constraint fk_free_comment_writer foreign key (free_comment_writer) references member(member_id) on delete cascade
);
comment on column free_comment.free_post_no is '자유게시판글번호';
comment on column free_comment.free_comment_no is '자유게시판댓글번호';
COMMENT ON COLUMN free_comment.free_comment_level IS '자유게시판댓글 레벨';
comment on column free_comment.free_comment_writer is '자유게시판댓글작성자';
COMMENT ON COLUMN free_comment.free_comment_ref IS '자유게시판댓글 참조번호';
comment on column free_comment.free_comment_date is '자유게시판댓글작성일';
comment on column free_comment.free_comment_content is '자유게시판댓글내용';


--자유사진DB
create table free_img(
free_img_no number,
free_img_address long not null,
free_post_no number,
constraint fk_free_img_post_no foreign key (free_post_no) references free(free_post_no) on delete cascade,
constraint pk_free_img_no primary key (free_img_no,free_post_no)
);
comment on column free_img.free_img_no is '자유사진번호';
comment on column free_img.free_img_address is '자유사진경로';
comment on column free_img.free_post_no is '자유글번호';


--유기동물DB
--보호중 DB 테이블 수정 2019-01-16
create table protect(
protect_post_no number,
member_id varchar2(30),
protect_post_title varchar2(300) not null,
race_code varchar2(10),
animal_no VARCHAR2(255),
protect_post_pet_gender char(1) check(protect_post_pet_gender in('M','F','N')),
protect_post_content long not null,
protect_post_find_address varchar2(4000) not null,
protect_post_find_date date not null,
protect_post_img_address varchar(4000) not null,
protect_post_pet_identify_no varchar2(20),
protect_post_date date default sysdate,
protect_post_give_date date,
protect_post_give_memberId varchar2(30),
protect_post_yn char(1) default 'N' check(protect_post_yn in('Y','N')),
constraint pk_protect_post_no primary key(protect_post_no),
constraint fk_protect_member_id foreign key (member_id) references member(member_id) on delete cascade,
constraint fk_protect_race_code foreign key (race_code,animal_no) references animal(race_code,animal_no) on delete cascade,
constraint fk_protect_give_member_id foreign key (protect_post_give_memberId) references member(member_id) on delete cascade
);
comment on column protect.protect_post_no is '보호중번호';
comment on column protect.member_id is '회원아이디';
comment on column protect.protect_post_title is '보호중제목';
comment on column protect.race_code is '애완동물종류';
comment on column protect.animal_no is '동물번호';
comment on column protect.protect_post_pet_gender is '보호중애완동물성별';
comment on column protect.protect_post_content is '보호중내용';
comment on column protect.protect_post_find_address is '보호중발견장소';
comment on column protect.protect_post_find_date is '보호중발견날짜';
comment on column protect.protect_post_img_address is '보호중사진경로';
comment on column protect.protect_post_date is '보호중작성일';
comment on column protect.protect_post_give_date is '보호중찾아간날짜';
comment on column protect.protect_post_give_memberId is '보호중찾아간회원아이디';
comment on column protect.protect_post_yn is '보호중종료유무';

--찾아주세요 테이블 수정 2019/01/14
create table find(
find_post_no number,
member_id varchar2(30),
pet_no number,
find_post_title varchar2(300) not null,
find_post_content long not null,
find_post_missing_address varchar2(4000) not null,
find_post_missing_date date not null,
find_post_reward char(1) default 'N' check(find_post_reward in('Y','N','C')),
find_post_sum number, 
find_post_img_address varchar(4000) not null,
find_post_date date default sysdate,
find_post_yn char(1) default 'N' check(find_post_yn in('Y','N')),
constraint pk_find_post_no primary key(find_post_no),
constraint fk_find_member_id foreign key (member_id) references member(member_id) on delete cascade,
constraint fk_find_pet_no foreign key (pet_no) references pet(pet_no) on delete cascade
);
comment on column find.find_post_no is '찾아주세요번호';
comment on column find.member_id is '회원아이디';
comment on column find.pet_no is '인식번호';
comment on column find.find_post_title is '찾아주세요제목';
comment on column find.find_post_content is '찾아주세요내용';
comment on column find.find_post_missing_address is '찾아주세요잃어버린장소';
comment on column find.find_post_missing_date is '찾아주세요잃어버린날짜';
comment on column find.find_post_reward is '찾아주세요사례금여부';
comment on column find.find_post_sum is '찾아주세요사례금';
comment on column find.find_post_img_address is '찾아주세요사진경로';
comment on column find.find_post_date is '찾아주세요작성일';
comment on column find.find_post_yn is '찾아주세요찾은유무';


--faq 수정 19-01-18 (head 컬럼 삭제, 내용 길이 수정) 
create table faq(
faq_no number,
faq_title varchar2(255) not null,
faq_content varchar2(4000) not null,
constraint pk_faq_no primary key(faq_no)
);
comment on column faq.faq_no is 'FAQ번호';
comment on column faq.faq_title is 'FAQ제목';
comment on column faq.faq_content is 'FAQ내용';


--전문의DB
create table doctor(
doctor_no number,
doctor_id varchar2(30) not null,
doctor_pwd varchar2(300) not null,
doctor_license varchar2(100) not null,
doctor_name varchar2(30) not null,
doctor_hospital varchar2(100),
doctor_address varchar2(4000) not null,
doctor_phone varchar2(50) not null,
doctor_img long,
doctor_x varchar2(100),
doctor_y varchar2(100),
constraint pk_doctor_no primary key(doctor_no)
);
comment on column doctor.doctor_no is '의사번호';
comment on column doctor.doctor_id is '의사아이디';
comment on column doctor.doctor_pwd is '의사비밀번호';
comment on column doctor.doctor_license is '의사면허';
comment on column doctor.doctor_name is '의사이름';
comment on column doctor.doctor_hospital is '병원이름';
comment on column doctor.doctor_address is '병원주소';
comment on column doctor.doctor_phone is '병원전화';
comment on column doctor.doctor_img is '의사사진';
comment on column doctor.doctor_x is '의사병원좌표x';
comment on column doctor.doctor_y is '의사병원좌표y';



--동물대분류 설정
insert into race values('D','강아지');
insert into race values('C','고양이');
insert into race values('E','기타');


--도.시정보입력
insert into city values('CITY1','서울특별시');
insert into city values('CITY2','부산광역시');
insert into city values('CITY3','광주광역시');
insert into city values('CITY4','대전광역시');
insert into city values('CITY5','대구광역시');
insert into city values('CITY6','울산광역시');
insert into city values('CITY7','인천광역시');
insert into city values('CITY8','경기도');
insert into city values('CITY9','강원도');
insert into city values('CITY10','충청남도');
insert into city values('CITY11','충청북도');
insert into city values('CITY12','경상남도');
insert into city values('CITY13','경상북도');
insert into city values('CITY14','전라남도');
insert into city values('CITY15','전라북도');
insert into city values('CITY16','제주특별자치도');


--서울 구 이름 입력
insert into district values('CITY1','DISTRICT1','강남구');
insert into district values('CITY1','DISTRICT2','강동구');
insert into district values('CITY1','DISTRICT3','강북구');
insert into district values('CITY1','DISTRICT4','강서구');
insert into district values('CITY1','DISTRICT5','관악구');
insert into district values('CITY1','DISTRICT6','광진구');
insert into district values('CITY1','DISTRICT7','구로구');
insert into district values('CITY1','DISTRICT8','금천구');
insert into district values('CITY1','DISTRICT9','노원구');
insert into district values('CITY1','DISTRICT10','도봉구');
insert into district values('CITY1','DISTRICT11','동대문구');
insert into district values('CITY1','DISTRICT12','동작구');
insert into district values('CITY1','DISTRICT13','마포구');
insert into district values('CITY1','DISTRICT14','서대문구');
insert into district values('CITY1','DISTRICT15','서초구');
insert into district values('CITY1','DISTRICT16','성동구');
insert into district values('CITY1','DISTRICT17','성북구');
insert into district values('CITY1','DISTRICT18','송파구');
insert into district values('CITY1','DISTRICT19','양천구');
insert into district values('CITY1','DISTRICT20','영등포구');
insert into district values('CITY1','DISTRICT21','용산구');
insert into district values('CITY1','DISTRICT22','은평구');
insert into district values('CITY1','DISTRICT23','종로구');
insert into district values('CITY1','DISTRICT24','중구');
insert into district values('CITY1','DISTRICT25','중랑구');

--부산 구 입력
insert into district values('CITY2','DISTRICT1','중구');
insert into district values('CITY2','DISTRICT2','서구');
insert into district values('CITY2','DISTRICT3','동구');
insert into district values('CITY2','DISTRICT4','영도구');
insert into district values('CITY2','DISTRICT5','부산진구');
insert into district values('CITY2','DISTRICT6','동래구');
insert into district values('CITY2','DISTRICT7','남구');
insert into district values('CITY2','DISTRICT8','북구');
insert into district values('CITY2','DISTRICT9','해운대구');
insert into district values('CITY2','DISTRICT10','사하구');
insert into district values('CITY2','DISTRICT11','금정구');
insert into district values('CITY2','DISTRICT12','강서구');
insert into district values('CITY2','DISTRICT13','연제구');
insert into district values('CITY2','DISTRICT14','수영구');
insert into district values('CITY2','DISTRICT15','사상구');
insert into district values('CITY2','DISTRICT16','기장군');

--광주
insert into district values('CITY3','DISTRICT1','동구');
insert into district values('CITY3','DISTRICT2','서구');
insert into district values('CITY3','DISTRICT3','남구');
insert into district values('CITY3','DISTRICT4','북구');
insert into district values('CITY3','DISTRICT5','광산구');

--대전
insert into district values('CITY4','DISTRICT1','대전동구');
insert into district values('CITY4','DISTRICT2','대전중구');
insert into district values('CITY4','DISTRICT3','대전서구');
insert into district values('CITY4','DISTRICT4','유성구');
insert into district values('CITY4','DISTRICT5','대덕구');

--대구
insert into district values('CITY5','DISTRICT1','중구');
insert into district values('CITY5','DISTRICT2','동구');
insert into district values('CITY5','DISTRICT3','서구');
insert into district values('CITY5','DISTRICT4','남구');
insert into district values('CITY5','DISTRICT5','북구');
insert into district values('CITY5','DISTRICT6','수성구');
insert into district values('CITY5','DISTRICT7','달서구');
insert into district values('CITY5','DISTRICT8','달성군');

--울산
insert into district values('CITY6','DISTRICT1','중구');
insert into district values('CITY6','DISTRICT2','남구');
insert into district values('CITY6','DISTRICT3','동구');
insert into district values('CITY6','DISTRICT4','북구');
insert into district values('CITY6','DISTRICT5','울주군');

--인천
insert into district values('CITY7','DISTRICT1','중구');
insert into district values('CITY7','DISTRICT2','동구');
insert into district values('CITY7','DISTRICT3','미추홀구');
insert into district values('CITY7','DISTRICT4','연수구');
insert into district values('CITY7','DISTRICT5','남동구');
insert into district values('CITY7','DISTRICT6','부평구');
insert into district values('CITY7','DISTRICT7','계양구');
insert into district values('CITY7','DISTRICT8','서구');
insert into district values('CITY7','DISTRICT9','강화군');
insert into district values('CITY7','DISTRICT10','옹진군');

--경기도
insert into district values('CITY8','DISTRICT1','수원시');
insert into district values('CITY8','DISTRICT2','성남시');
insert into district values('CITY8','DISTRICT3','용인시');
insert into district values('CITY8','DISTRICT4','안양시');
insert into district values('CITY8','DISTRICT5','안산시');
insert into district values('CITY8','DISTRICT6','과천시');
insert into district values('CITY8','DISTRICT7','광명시');
insert into district values('CITY8','DISTRICT8','광주시');
insert into district values('CITY8','DISTRICT9','군포시');
insert into district values('CITY8','DISTRICT10','부천시');
insert into district values('CITY8','DISTRICT11','시흥시');
insert into district values('CITY8','DISTRICT12','김포시');
insert into district values('CITY8','DISTRICT13','안성시');
insert into district values('CITY8','DISTRICT14','오산시');
insert into district values('CITY8','DISTRICT15','의왕시');
insert into district values('CITY8','DISTRICT16','이천시');
insert into district values('CITY8','DISTRICT17','평택시');
insert into district values('CITY8','DISTRICT18','하남시');
insert into district values('CITY8','DISTRICT19','화성시');
insert into district values('CITY8','DISTRICT20','여주시');
insert into district values('CITY8','DISTRICT21','양평군');
insert into district values('CITY8','DISTRICT22','고양시');
insert into district values('CITY8','DISTRICT23','구리시');
insert into district values('CITY8','DISTRICT24','남양주시');
insert into district values('CITY8','DISTRICT25','동두천시');
insert into district values('CITY8','DISTRICT26','양주시');
insert into district values('CITY8','DISTRICT27','의정부시');
insert into district values('CITY8','DISTRICT28','파주시');
insert into district values('CITY8','DISTRICT29','포천시');
insert into district values('CITY8','DISTRICT30','연천군');
insert into district values('CITY8','DISTRICT31','가평군');

--강원도
insert into district values('CITY9','DISTRICT1','원주시');
insert into district values('CITY9','DISTRICT2','춘천시');
insert into district values('CITY9','DISTRICT3','강릉시');
insert into district values('CITY9','DISTRICT4','동해시');
insert into district values('CITY9','DISTRICT5','속초시');
insert into district values('CITY9','DISTRICT6','삼척시');
insert into district values('CITY9','DISTRICT7','태백시');
insert into district values('CITY9','DISTRICT8','홍천군');
insert into district values('CITY9','DISTRICT9','철원군');
insert into district values('CITY9','DISTRICT10','횡성군');
insert into district values('CITY9','DISTRICT11','평창군');
insert into district values('CITY9','DISTRICT12','정선군');
insert into district values('CITY9','DISTRICT13','영월군');
insert into district values('CITY9','DISTRICT14','인제군');
insert into district values('CITY9','DISTRICT15','고성군');
insert into district values('CITY9','DISTRICT16','양양군');
insert into district values('CITY9','DISTRICT17','화천군');
insert into district values('CITY9','DISTRICT18','양구군');

--충청남도
insert into district values('CITY10','DISTRICT1','천안시');
insert into district values('CITY10','DISTRICT2','공주시');
insert into district values('CITY10','DISTRICT3','보령시');
insert into district values('CITY10','DISTRICT4','아산시');
insert into district values('CITY10','DISTRICT5','서산시');
insert into district values('CITY10','DISTRICT6','논산시');
insert into district values('CITY10','DISTRICT7','계룡시');
insert into district values('CITY10','DISTRICT8','당진시');
insert into district values('CITY10','DISTRICT9','금산군');
insert into district values('CITY10','DISTRICT10','부여군');
insert into district values('CITY10','DISTRICT11','서천군');
insert into district values('CITY10','DISTRICT12','청양군');
insert into district values('CITY10','DISTRICT13','홍성군');
insert into district values('CITY10','DISTRICT14','예산군');
insert into district values('CITY10','DISTRICT15','태안군');

--충청북도
insert into district values('CITY11','DISTRICT1','청주시');
insert into district values('CITY11','DISTRICT2','충주시');
insert into district values('CITY11','DISTRICT3','제천시');
insert into district values('CITY11','DISTRICT4','보은군');
insert into district values('CITY11','DISTRICT5','옥천군');
insert into district values('CITY11','DISTRICT6','영동군');
insert into district values('CITY11','DISTRICT7','증평군');
insert into district values('CITY11','DISTRICT8','진천군');
insert into district values('CITY11','DISTRICT9','괴산군');
insert into district values('CITY11','DISTRICT10','음성군');
insert into district values('CITY11','DISTRICT11','단양군');

--경상남도
insert into district values('CITY12','DISTRICT1','창원시');
insert into district values('CITY12','DISTRICT2','김해시');
insert into district values('CITY12','DISTRICT3','진주시');
insert into district values('CITY12','DISTRICT4','양산시');
insert into district values('CITY12','DISTRICT5','거제시');
insert into district values('CITY12','DISTRICT6','통영시');
insert into district values('CITY12','DISTRICT7','사천시');
insert into district values('CITY12','DISTRICT8','밀양시');
insert into district values('CITY12','DISTRICT9','함안군');
insert into district values('CITY12','DISTRICT10','거창군');
insert into district values('CITY12','DISTRICT11','창녕군');
insert into district values('CITY12','DISTRICT12','고성군');
insert into district values('CITY12','DISTRICT13','하동군');
insert into district values('CITY12','DISTRICT14','합천군');
insert into district values('CITY12','DISTRICT15','남해군');
insert into district values('CITY12','DISTRICT16','함양군');
insert into district values('CITY12','DISTRICT17','산청군');
insert into district values('CITY12','DISTRICT18','의령군');

--경상북도
insert into district values('CITY13','DISTRICT1','포항시');
insert into district values('CITY13','DISTRICT2','경주시');
insert into district values('CITY13','DISTRICT3','김천시');
insert into district values('CITY13','DISTRICT4','안동시');
insert into district values('CITY13','DISTRICT5','구미시');
insert into district values('CITY13','DISTRICT6','영주시');
insert into district values('CITY13','DISTRICT7','영천시');
insert into district values('CITY13','DISTRICT8','상주시');
insert into district values('CITY13','DISTRICT9','문경시');
insert into district values('CITY13','DISTRICT10','경산시');
insert into district values('CITY13','DISTRICT11','군위군');
insert into district values('CITY13','DISTRICT12','의성군');
insert into district values('CITY13','DISTRICT13','청송군');
insert into district values('CITY13','DISTRICT14','영양군');
insert into district values('CITY13','DISTRICT15','영덕군');
insert into district values('CITY13','DISTRICT16','청도군');
insert into district values('CITY13','DISTRICT17','고령군');
insert into district values('CITY13','DISTRICT18','성주군');
insert into district values('CITY13','DISTRICT19','칠곡군');
insert into district values('CITY13','DISTRICT20','예천군');
insert into district values('CITY13','DISTRICT21','봉화군');
insert into district values('CITY13','DISTRICT22','울진군');
insert into district values('CITY13','DISTRICT23','울릉군');


--전라남도
insert into district values('CITY14','DISTRICT1','목포시');
insert into district values('CITY14','DISTRICT2','여수시');
insert into district values('CITY14','DISTRICT3','순천시');
insert into district values('CITY14','DISTRICT4','나주시');
insert into district values('CITY14','DISTRICT5','광양시');
insert into district values('CITY14','DISTRICT6','담양군');
insert into district values('CITY14','DISTRICT7','곡성군');
insert into district values('CITY14','DISTRICT8','구례군');
insert into district values('CITY14','DISTRICT9','고흥군');
insert into district values('CITY14','DISTRICT10','보성군');
insert into district values('CITY14','DISTRICT11','화순군');
insert into district values('CITY14','DISTRICT12','장흥군');
insert into district values('CITY14','DISTRICT13','강진군');
insert into district values('CITY14','DISTRICT14','해남군');
insert into district values('CITY14','DISTRICT15','영암군');
insert into district values('CITY14','DISTRICT16','무안군');
insert into district values('CITY14','DISTRICT17','함평군');
insert into district values('CITY14','DISTRICT18','영광군');
insert into district values('CITY14','DISTRICT19','장성군');
insert into district values('CITY14','DISTRICT20','완도군');
insert into district values('CITY14','DISTRICT21','진도군');
insert into district values('CITY14','DISTRICT22','신안군');


--전라북도
insert into district values('CITY15','DISTRICT1','전주시');
insert into district values('CITY15','DISTRICT2','익산시');
insert into district values('CITY15','DISTRICT3','군산시');
insert into district values('CITY15','DISTRICT4','정읍시');
insert into district values('CITY15','DISTRICT5','김제시');
insert into district values('CITY15','DISTRICT6','남원시');
insert into district values('CITY15','DISTRICT7','완주군');
insert into district values('CITY15','DISTRICT8','고창군');
insert into district values('CITY15','DISTRICT9','부안군');
insert into district values('CITY15','DISTRICT10','임실군');
insert into district values('CITY15','DISTRICT11','순창군');
insert into district values('CITY15','DISTRICT12','진안군');
insert into district values('CITY15','DISTRICT13','무주군');
insert into district values('CITY15','DISTRICT14','장수군');


--제주도
insert into district values('CITY16','DISTRICT1','제주시');
insert into district values('CITY16','DISTRICT2','서귀포시');


--상품 카테고리 데이터
insert into product_category values('P1','식품');
insert into product_category values('P2','위생');
insert into product_category values('P3','장난감');
insert into product_category values('P4','하우스');
insert into product_category values('P5','의류');

--강아지 리스트
insert into animal values((select race_code from race where race_name='강아지'),seq_dog_no.nextval,'골든리트리버');
insert into animal values((select race_code from race where race_name='강아지'),seq_dog_no.nextval,'그레이트피레니즈');
insert into animal values((select race_code from race where race_name='강아지'),seq_dog_no.nextval,'닥스훈트');
insert into animal values((select race_code from race where race_name='강아지'),seq_dog_no.nextval,'달마시안');
insert into animal values((select race_code from race where race_name='강아지'),seq_dog_no.nextval,'도베르만');
insert into animal values((select race_code from race where race_name='강아지'),seq_dog_no.nextval,'래브라도리트리버');
insert into animal values((select race_code from race where race_name='강아지'),seq_dog_no.nextval,'레온베르거');
insert into animal values((select race_code from race where race_name='강아지'),seq_dog_no.nextval,'로트와일러');
insert into animal values((select race_code from race where race_name='강아지'),seq_dog_no.nextval,'말티즈');
insert into animal values((select race_code from race where race_name='강아지'),seq_dog_no.nextval,'미니어처불테리어');
insert into animal values((select race_code from race where race_name='강아지'),seq_dog_no.nextval,'미니어처슈나우저');
insert into animal values((select race_code from race where race_name='강아지'),seq_dog_no.nextval,'미니어처핀셔');
insert into animal values((select race_code from race where race_name='강아지'),seq_dog_no.nextval,'보더콜리');
insert into animal values((select race_code from race where race_name='강아지'),seq_dog_no.nextval,'불도그');
insert into animal values((select race_code from race where race_name='강아지'),seq_dog_no.nextval,'불테리어');
insert into animal values((select race_code from race where race_name='강아지'),seq_dog_no.nextval,'비글');
insert into animal values((select race_code from race where race_name='강아지'),seq_dog_no.nextval,'비숑프리제');
insert into animal values((select race_code from race where race_name='강아지'),seq_dog_no.nextval,'빠삐용');
insert into animal values((select race_code from race where race_name='강아지'),seq_dog_no.nextval,'사모예드');
insert into animal values((select race_code from race where race_name='강아지'),seq_dog_no.nextval,'삽살개');
insert into animal values((select race_code from race where race_name='강아지'),seq_dog_no.nextval,'세인트버나드');
insert into animal values((select race_code from race where race_name='강아지'),seq_dog_no.nextval,'슈나우저');
insert into animal values((select race_code from race where race_name='강아지'),seq_dog_no.nextval,'스피츠');
insert into animal values((select race_code from race where race_name='강아지'),seq_dog_no.nextval,'시바견');
insert into animal values((select race_code from race where race_name='강아지'),seq_dog_no.nextval,'시베리안허스키');
insert into animal values((select race_code from race where race_name='강아지'),seq_dog_no.nextval,'시추');
insert into animal values((select race_code from race where race_name='강아지'),seq_dog_no.nextval,'알래스칸말라뮤트');
insert into animal values((select race_code from race where race_name='강아지'),seq_dog_no.nextval,'요크셔테리어');
insert into animal values((select race_code from race where race_name='강아지'),seq_dog_no.nextval,'웰시코기');
insert into animal values((select race_code from race where race_name='강아지'),seq_dog_no.nextval,'진돗개');
insert into animal values((select race_code from race where race_name='강아지'),seq_dog_no.nextval,'차우차우');
insert into animal values((select race_code from race where race_name='강아지'),seq_dog_no.nextval,'치와와');
insert into animal values((select race_code from race where race_name='강아지'),seq_dog_no.nextval,'코카스파니엘');
insert into animal values((select race_code from race where race_name='강아지'),seq_dog_no.nextval,'포메라니안');
insert into animal values((select race_code from race where race_name='강아지'),seq_dog_no.nextval,'푸들');
insert into animal values((select race_code from race where race_name='강아지'),seq_dog_no.nextval,'풍산개');
insert into animal values((select race_code from race where race_name='강아지'),seq_dog_no.nextval,'핏불테리어');
insert into animal values((select race_code from race where race_name='강아지'),seq_dog_no.nextval,'믹스견');

--고양이 리스트
insert into animal values((select race_code from race where race_name='고양이'),seq_cat_no.nextval,'라이코이');
insert into animal values((select race_code from race where race_name='고양이'),seq_cat_no.nextval,'러시안블루');
insert into animal values((select race_code from race where race_name='고양이'),seq_cat_no.nextval,'맹크스');
insert into animal values((select race_code from race where race_name='고양이'),seq_cat_no.nextval,'먼치킨');
insert into animal values((select race_code from race where race_name='고양이'),seq_cat_no.nextval,'벵갈');
insert into animal values((select race_code from race where race_name='고양이'),seq_cat_no.nextval,'샴');
insert into animal values((select race_code from race where race_name='고양이'),seq_cat_no.nextval,'세렝게티');
insert into animal values((select race_code from race where race_name='고양이'),seq_cat_no.nextval,'스코티시폴드');
insert into animal values((select race_code from race where race_name='고양이'),seq_cat_no.nextval,'스핑크스');
insert into animal values((select race_code from race where race_name='고양이'),seq_cat_no.nextval,'시베리안포레스트');
insert into animal values((select race_code from race where race_name='고양이'),seq_cat_no.nextval,'아비시니안');
insert into animal values((select race_code from race where race_name='고양이'),seq_cat_no.nextval,'아프로디테');
insert into animal values((select race_code from race where race_name='고양이'),seq_cat_no.nextval,'오리엔탈');
insert into animal values((select race_code from race where race_name='고양이'),seq_cat_no.nextval,'우랄렉스');
insert into animal values((select race_code from race where race_name='고양이'),seq_cat_no.nextval,'니벨룽');
insert into animal values((select race_code from race where race_name='고양이'),seq_cat_no.nextval,'메인쿤');
insert into animal values((select race_code from race where race_name='고양이'),seq_cat_no.nextval,'터키시앙고라');
insert into animal values((select race_code from race where race_name='고양이'),seq_cat_no.nextval,'페르시안');
insert into animal values((select race_code from race where race_name='고양이'),seq_cat_no.nextval,'렉돌');
insert into animal values((select race_code from race where race_name='고양이'),seq_cat_no.nextval,'노르웨이숲');
insert into animal values((select race_code from race where race_name='고양이'),seq_cat_no.nextval,'버만');
insert into animal values((select race_code from race where race_name='고양이'),seq_cat_no.nextval,'버마즈');
insert into animal values((select race_code from race where race_name='고양이'),seq_cat_no.nextval,'버밀라');
insert into animal values((select race_code from race where race_name='고양이'),seq_cat_no.nextval,'브리티쉬숏헤어');
insert into animal values((select race_code from race where race_name='고양이'),seq_cat_no.nextval,'사바나캣');
insert into animal values((select race_code from race where race_name='고양이'),seq_cat_no.nextval,'아메리칸밥테일');
insert into animal values((select race_code from race where race_name='고양이'),seq_cat_no.nextval,'아메리칸숏헤어');
insert into animal values((select race_code from race where race_name='고양이'),seq_cat_no.nextval,'엑죠틱숏헤어');
insert into animal values((select race_code from race where race_name='고양이'),seq_cat_no.nextval,'히말라야');
insert into animal values((select race_code from race where race_name='고양이'),seq_cat_no.nextval,'한국고양이');


--기타동물 리스트
insert into animal values((select race_code from race where race_name='기타'),seq_etc_no.nextval,'페럿');
insert into animal values((select race_code from race where race_name='기타'),seq_etc_no.nextval,'햄스터');
insert into animal values((select race_code from race where race_name='기타'),seq_etc_no.nextval,'캥거루쥐');
insert into animal values((select race_code from race where race_name='기타'),seq_etc_no.nextval,'팬더마우스');
insert into animal values((select race_code from race where race_name='기타'),seq_etc_no.nextval,'슈가글라이더');
insert into animal values((select race_code from race where race_name='기타'),seq_etc_no.nextval,'다람쥐');
insert into animal values((select race_code from race where race_name='기타'),seq_etc_no.nextval,'미니피그');
insert into animal values((select race_code from race where race_name='기타'),seq_etc_no.nextval,'저빌');
insert into animal values((select race_code from race where race_name='기타'),seq_etc_no.nextval,'라쿤');
insert into animal values((select race_code from race where race_name='기타'),seq_etc_no.nextval,'기니피그');
insert into animal values((select race_code from race where race_name='기타'),seq_etc_no.nextval,'고슴도치');
insert into animal values((select race_code from race where race_name='기타'),seq_etc_no.nextval,'토끼');
insert into animal values((select race_code from race where race_name='기타'),seq_etc_no.nextval,'사랑앵무');
insert into animal values((select race_code from race where race_name='기타'),seq_etc_no.nextval,'모란앵무');
insert into animal values((select race_code from race where race_name='기타'),seq_etc_no.nextval,'왕관앵무');
insert into animal values((select race_code from race where race_name='기타'),seq_etc_no.nextval,'달팽이');
insert into animal values((select race_code from race where race_name='기타'),seq_etc_no.nextval,'거북이');
insert into animal values((select race_code from race where race_name='기타'),seq_etc_no.nextval,'금붕어');
insert into animal values((select race_code from race where race_name='기타'),seq_etc_no.nextval,'거미');
insert into animal values((select race_code from race where race_name='기타'),seq_etc_no.nextval,'채찍거미');
insert into animal values((select race_code from race where race_name='기타'),seq_etc_no.nextval,'타란튤라');
insert into animal values((select race_code from race where race_name='기타'),seq_etc_no.nextval,'딱정벌레');
insert into animal values((select race_code from race where race_name='기타'),seq_etc_no.nextval,'사슴벌레');
insert into animal values((select race_code from race where race_name='기타'),seq_etc_no.nextval,'하늘소');
insert into animal values((select race_code from race where race_name='기타'),seq_etc_no.nextval,'장수풍뎅이');
insert into animal values((select race_code from race where race_name='기타'),seq_etc_no.nextval,'병아리');
insert into animal values((select race_code from race where race_name='기타'),seq_etc_no.nextval,'비둘기');
insert into animal values((select race_code from race where race_name='기타'),seq_etc_no.nextval,'도마뱀');
insert into animal values((select race_code from race where race_name='기타'),seq_etc_no.nextval,'목도리도마뱀');
insert into animal values((select race_code from race where race_name='기타'),seq_etc_no.nextval,'카멜레온');
insert into animal values((select race_code from race where race_name='기타'),seq_etc_no.nextval,'이구아나');
insert into animal values((select race_code from race where race_name='기타'),seq_etc_no.nextval,'턱수염도마뱀');
insert into animal values((select race_code from race where race_name='기타'),seq_etc_no.nextval,'뱀');
insert into animal values((select race_code from race where race_name='기타'),seq_etc_no.nextval,'구렁이');
insert into animal values((select race_code from race where race_name='기타'),seq_etc_no.nextval,'보아뱀');
insert into animal values((select race_code from race where race_name='기타'),seq_etc_no.nextval,'우파루파');
insert into animal values((select race_code from race where race_name='기타'),seq_etc_no.nextval,'개구리');
insert into animal values((select race_code from race where race_name='기타'),seq_etc_no.nextval,'발톱개구리');
insert into animal values((select race_code from race where race_name='기타'),seq_etc_no.nextval,'뿔개구리');
insert into animal values((select race_code from race where race_name='기타'),seq_etc_no.nextval,'청개구리');

--성훈
--멤버
insert into member values('lsj12', 'lsj12', '이수지', '96-09-12' ,'F' ,'01012354678', 'lsj12@naver.com', '서울특별시 구로구', default, default, default);  
insert into member values('lsj91', 'lsj91', '이성진', '91-09-12' ,'M' ,'01091159847', 'lsj91@naver.com', '경상남도 창원시', default, default, default);  
insert into member values('kdw12', 'kdw12', '강동원', '81-01-18' ,'M' ,'01051354684', 'kdw12@naver.com', '부산광역시 사하구', default, default, default);  
insert into member values('kimsky12', 'kimsky12', '김하늘', '78-02-21' ,'F' ,'01075468135', 'kimsky12@naver.com', '충청북도 단양군', default, default, default);  
insert into member values('kocute12', 'kocute12', '고창석', '70-10-13' ,'M' ,'01065489732', 'kocute12@naver.com', '부산광역시 남구', default, default, default);  
insert into member values('kmm12', 'kmm12', '김명민', '72-10-08' ,'M' ,'01012457896', 'kmm12@naver.com', '서울특별시 은평구', default, default, default);  
insert into member values('kar12', 'kar12', '고아라', '90-02-11' ,'F' ,'01075481364', 'kar12@naver.com', '경상남도 진주시', default, default, default);  
insert into member values('ysh12', 'ysh12', '유승호', '93-08-17' ,'M' ,'01054681328', 'ysh12@naver.com', '인천광역시 계양구', default, default, default);  
insert into member values('khs12', 'khs12', '구혜선', '84-11-09' ,'F' ,'01054321687', 'khs12@naver.com', '인천광역시 부평구', default, default, default);  
insert into member values('ajh12', 'ajh12', '안재현', '87-07-01' ,'M' ,'01078451687', 'ajh12@naver.com', '서울특별시 은평구', default, default, default);  
insert into member values('lyb12', 'lyb12', '이유비', '90-11-22' ,'F' ,'01064521589', 'lyb12@naver.com', '서울특별시 광진구', default, default, default);  
insert into member values('pbg12', 'pbg12', '박보검', '93-06-16' ,'M' ,'01045123584', 'pbg12@naver.com', '서울특별시 양천구', default, default, default);  
insert into member values('lhl12', 'lhl12', '이혜리', '94-06-09' ,'F' ,'01028659484', 'lhl12@naver.com', '경기도 광주시', default, default, default);  
insert into member values('psj12', 'psj12', '박서준', '88-12-16' ,'M' ,'01057956326', 'psj12@naver.com', '서울특별시 용산구', default, default, default);  
insert into member values('pmy12', 'pmy12', '박민영', '86-03-04' ,'F' ,'01074758484', 'pmy12@naver.com', '서울특별시 중구', default, default, default);  
insert into member values('pso12', 'pso12', '박성웅', '73-01-09' ,'M' ,'01052574684', 'pso12@naver.com', '충청북도 충주시', default, default, default);  
insert into member values('psh12', 'psh12', '박신혜', '90-02-18' ,'F' ,'01054684875', 'psh12@naver.com', '광주광역시 남구', default, default, default);  
insert into member values('hjw12', 'hjw12', '하정우', '78-03-11' ,'M' ,'01051476897', 'hjw12@naver.com', '서울특별시 서초구', default, default, default);  
insert into member values('pby12', 'pby12', '박보영', '90-02-12' ,'F' ,'01014526879', 'pby12@naver.com', '충청북도 증평군', default, default, default);  
insert into member values('hhu12', 'hhu12', '하현우', '81-11-25' ,'M' ,'01094875621', 'hhu12@naver.com', '전라북도 장수군', default, default, default);  

--펫
insert into pet values('lsj12', SEQ_PET_NO.NEXTVAL, '409132903931240', 'D', '23', '18-06-03', 'F', '미분이', default, 'Y');
insert into pet values('lsj12', SEQ_PET_NO.NEXTVAL, '176079757205533', 'D', '23', '18-06-03', 'F', '적분이', default, 'Y');
insert into pet values('lsj91', SEQ_PET_NO.NEXTVAL, '470444063280712', 'C', '30', '17-02-13', 'M', '벨라', default, 'Y');
insert into pet values('kdw12', SEQ_PET_NO.NEXTVAL, '341521015736227', 'C', '30', '16-04-16', 'F', '티거', default, 'Y');
insert into pet values('kimsky12', SEQ_PET_NO.NEXTVAL, '305452850727606', 'C', '30', '18-09-24', 'M', '클로리', default, 'Y');
insert into pet values('kocute12', SEQ_PET_NO.NEXTVAL, '494766714996559', 'C', '30', '19-01-01', 'F', '쉐도우', default, 'Y');
insert into pet values('kmm12', SEQ_PET_NO.NEXTVAL, '972241919377068', 'C', '30', '16-12-25', 'M', '루나', default, 'Y');
insert into pet values('kar12', SEQ_PET_NO.NEXTVAL, '618386657709421', 'C', '8', '17-08-04', 'F', '오레오', default, 'Y');
insert into pet values('ysh12', SEQ_PET_NO.NEXTVAL, '422007670637847', 'C', '23', '18-07-26', 'M', '올리버', default, 'Y');
insert into pet values('khs12', SEQ_PET_NO.NEXTVAL, '618113202990084', 'C', '12', '15-09-13', 'F', '키티', default, 'Y');
insert into pet values('ajh12', SEQ_PET_NO.NEXTVAL, '11617724122503', 'C', '21', '17-01-31', 'M', '루시', default, 'Y');
insert into pet values('lyb12', SEQ_PET_NO.NEXTVAL, '683649567831276', 'C', '5', '17-03-14', 'F', '몰리', default, 'Y');
insert into pet values('pbg12', SEQ_PET_NO.NEXTVAL, '997645893172637', 'C', '7', '18-08-30', 'M', '레오', default, 'Y');
insert into pet values('lhl12', SEQ_PET_NO.NEXTVAL, '269608943805109', 'C', '28', '19-01-08', 'F', '코코', default, 'Y');
insert into pet values('psj12', SEQ_PET_NO.NEXTVAL, '648751049815096', 'C', '18', '18-05-05', 'M', '마루', default, 'Y');
insert into pet values('pmy12', SEQ_PET_NO.NEXTVAL, '425343669399316', 'C', '18', '17-05-03', 'F', '린', default, 'Y');
insert into pet values('pso12', SEQ_PET_NO.NEXTVAL, '818323377128774', 'C', '4', '15-04-03', 'M', '데이지', default, 'Y');
insert into pet values('psh12', SEQ_PET_NO.NEXTVAL, '5601669416187', 'C', '19', '14-02-14', 'F', '오스카', default, 'Y');
insert into pet values('hjw12', SEQ_PET_NO.NEXTVAL, '261114747830280', 'C', '14', '14-02-13', 'M', '마일로', default, 'Y');
insert into pet values('pby12', SEQ_PET_NO.NEXTVAL, '721438108940992', 'C', '17', '15-12-11', 'F', '릴리', default, 'Y');
insert into pet values('hhu12', SEQ_PET_NO.NEXTVAL, '926215398194483', 'C', '13', '16-11-12', 'M', '펠릭스', default, 'Y');
insert into pet values('kimsky12', SEQ_PET_NO.NEXTVAL, '581994387908049', 'C', '18', '17-10-24', 'F', '루시', default, 'Y');
insert into pet values('kar12', SEQ_PET_NO.NEXTVAL, '44436758117223', 'C', '11', '18-11-11', 'M', '심바', default, 'Y');
insert into pet values('pmy12', SEQ_PET_NO.NEXTVAL, '286857504660827', 'C', '7', '18-10-24', 'F', '루나', default, 'Y');
insert into pet values('pby12', SEQ_PET_NO.NEXTVAL, '8396929730672', 'C', '15', '17-11-25', 'M', '레오', default, 'Y');


--현중
insert into member values('user1','12346','김현중','94/11/19','M','01045159411','user1@naver.com','서울특별시 관악구',default,sysdate,default);
insert into pet values('user1', SEQ_PET_NO.NEXTVAL, '855547895157856', 'D', '3', '17-7-06', 'M', '두부', default, 'Y');        
insert into member values('yjkim1119','5556','김용주','88/02/06','M','01092539415','yjkim1119@yhaoo.com','서울특별시 관악구',default,sysdate,default);
insert into pet values('yjkim1119', SEQ_PET_NO.NEXTVAL, '975859654744515', 'D', '7', '13-04-05', 'F', '대한', default, 'Y');        
insert into member values('pt5280','4545','박종인','89/02/06','M','01098876654','pt5280@naver.com','충청남도 아산시',default,sysdate,default);
insert into pet values('pt5280', SEQ_PET_NO.NEXTVAL, '954111235789564', 'D', '12', '14-01-07', 'M', '민국', default, 'Y');        
insert into member values('dkfvn23','1477','김현녀','89/03/04','F','01093672322','dkfvn23@daum.net','충청북도 천안시 서북구',default,sysdate,default);
insert into pet values('dkfvn23', SEQ_PET_NO.NEXTVAL, '879874113964784', 'D', '15', '11-11-08', 'M', '만세', default, 'Y');        
insert into member values('pt598s2','9741','김현남','96/04/03','M','01045451111','pt598s2@gamil.com','경기도 안양시',default,sysdate,default);
insert into pet values('pt598s2', SEQ_PET_NO.NEXTVAL, '666879731254785', 'E', '18', '12-03-06', 'F', '케로', default, 'Y');
insert into pet values('pt598s2', SEQ_PET_NO.NEXTVAL, '180586170353471', 'D', '13', '13-12-07', 'M', '토리', default, 'Y');        
insert into member values('jungbuk11','4567','이정복','90/07/06','M','01079541147','jungbuk@daum.net','경기도 김해시',default,sysdate,default);
insert into pet values('jungbuk11', SEQ_PET_NO.NEXTVAL, '441212002582014', 'C', '8', '14-05-05', 'M', '쌈디', default, 'Y');        
insert into member values('backjoung','4576','백종원','62/04/02','M','01077776661','backjoung@google.com','전라북도 안산시',default,sysdate,default);
insert into pet values('backjoung', SEQ_PET_NO.NEXTVAL, '112547887741599', 'E', '11', '17-04-02', 'M', '봄이', default, 'Y');        
insert into member values('dounghe','4567','김동해','70/01/03','M','01034571223','dounghe@nate.com','서울특별시 동작구',default,sysdate,default);
insert into pet values('dounghe', SEQ_PET_NO.NEXTVAL, '726873323491460', 'D', '21', '16-03-01', 'F', '동해', default, 'Y');        
insert into member values('chanwoong','1234','왕찬웅','55/04/04','M','01077441451','chanwoong@nate.com','서울특별시 강남구 ',default,sysdate,default);
insert into pet values('chanwoong', SEQ_PET_NO.NEXTVAL, '454578787888888', 'C', '13', '17-02-22', 'M', '땅콩', default, 'Y');
insert into pet values('chanwoong', SEQ_PET_NO.NEXTVAL, '454517444444777', 'D', '38', '18-03-13', 'F', '빡구', default, 'Y');     
insert into member values('kang12','1234','강도원','88/08/08','M','01045336698','kang12@dreamwiz.com','충청북도 청주시',default,sysdate,default);
insert into pet values('kang12', SEQ_PET_NO.NEXTVAL, '787946134161616', 'D', '2', '16-07-07', 'M', '도리', default, 'Y');      
insert into member values('sin456','1234','신재민','69/07/08','F','01077320708','sin456@gmail.com','서울특별시 동작구',default,sysdate,default);
insert into pet values('sin456', SEQ_PET_NO.NEXTVAL, '666789456159874', 'C', '6', '19-01-01', 'F', '츄릅', default, 'Y');             
insert into member values('huk789','1234','윤준혁','73/08/03','M','01012347894','huk789@nate.com','서울특별시 강동구',default,sysdate,default);
insert into pet values('huk789', SEQ_PET_NO.NEXTVAL, '789456123589514', 'E', '15', '12-08-07', 'M', '태양', default, 'Y');  
insert into member values('file44','1234','김파일','98/06/04','M','01078949512','file44@gmail.com','충청북도 청주시',default,sysdate,default);
insert into pet values('file44', SEQ_PET_NO.NEXTVAL, '147852369985214', 'C', '5', '14-09-21', 'M', '까무', default, 'Y');        
insert into member values('youna1','1234','이유나','94/09/04','F','01055326789','youna1@nate.com','전라남도 광주시',default,sysdate,default);
insert into pet values('youna1', SEQ_PET_NO.NEXTVAL, '187412569877415', 'D', '6', '15-10-15', 'F', '콩새', default, 'Y');        
insert into member values('nasunghee','1234','나성희','97/03/04','F','01077741590','nasunghee@naver.com','대구광역시 동구',default,sysdate,default);
insert into pet values('nasunghee', SEQ_PET_NO.NEXTVAL, '456129663300124', 'D', '8', '16-07-21', 'M', '단추', default, 'Y');
insert into member values('kungwoo31','1234','박경우','81/06/08','M','01053892992','kungwoo31@naver.com','경상남도 구미시',default,sysdate,default);
insert into pet values('kungwoo31', SEQ_PET_NO.NEXTVAL, '563300251041026', 'C', '9', '17-09-13', 'F', '마카롱', default, 'Y');
insert into member values('mire258','1234','김미리','89/03/04','F','01078789898','mire258@nate.com','부산광역시 영도구',default,sysdate,default);
insert into pet values('mire258', SEQ_PET_NO.NEXTVAL, '445588779960230', 'E', '10', '18-01-02', 'M', '미래', default, 'Y');       
insert into member values('moonsik','1234','한문식','99/02/09','F','01037572312','moonsik@gmail.com','부산광역시 중구',default,sysdate,default);
insert into pet values('moonsik', SEQ_PET_NO.NEXTVAL, '181875310259863', 'D', '11', '07-05-03', 'F', '마테', default, 'Y');     
insert into member values('janghyun','1234','문장현','43/09/27','M','01094686422','janghyun@freechal.com','부산광역시 서구',default,sysdate,default);
insert into pet values('janghyun', SEQ_PET_NO.NEXTVAL, '102140679787601', 'C', '12', '08-03-11', 'M', '방석', default, 'Y');        
insert into member values('bangsee','1234','김방새','98/11/04','M','01078782222','bangsee@freechal.com','부산광역시 동구',default,sysdate,default);
insert into pet values('bangsee', SEQ_PET_NO.NEXTVAL, '867897111111187', 'E', '13', '18-04-04', 'F', '쿠키', default, 'N');
insert into pet values('bangsee', SEQ_PET_NO.NEXTVAL, '445454578787811', 'D', '14', '19-01-08', 'F', '치킨', default, 'N');

--종언
insert into member values('admin', '1234', '관리자', '90-01-27', 'M', '01012345678', 'admin@naver.com', '서울시 동작구', default, sysdate, 'Y');
insert into member values('jaeseok77', '7777', '유재석', '72-08-14', 'M', '01077777777', 'jaeseok77@naver.com', '서울시 강남구', default, sysdate, default);
insert into member values('haha12', '9999', '하하', '79-08-20', 'M', '01099999999', 'pjun1270@gmail.com', '서울시 마포구', default, sysdate, default);
insert into member values('hongchul88', '8888', '노홍철', '79-03-31', 'M', '01088888888', 'hongchul88@naver.com', '서울시 마포구', default, sysdate, default);
insert into member values('junha00', '6666', '정준하', '71-03-18', 'M', '01066666666', 'junha00@daum.net', '서울시 서대문구', default, sysdate, default);
insert into member values('greatPark22', '2222', '박명수', '70-09-27', 'M', '01022222222', 'greatPark11@daum.net', '서울시 서초구', default, sysdate, default);
insert into member values('seho33', '3333', '세호', '82-08-09', 'M', '01033333333', 'seho33@daum.net', '서울시 강서구', default, sysdate, default);
insert into member values('sehyeong44', '4444', '양세형', '85-08-18', 'M', '01044444444', 'sehyeong44@daum.net', '서울시 송파구', default, sysdate, default);
insert into member values('sechan55', '5555', '양세찬', '86-12-08', 'M', '01055555555', 'sechan55@daum.net', '서울시 송파구', default, sysdate, default);
insert into member values('hong', '0000', '홍길동', '00-6-20', 'M', '01023890654', 'hong@iei.or.kr', '서울시 강남구', default, sysdate, default);
insert into member values('jennie', 'aaaa', '제니', '96-01-16', 'F', '01012356798', 'jennie@naver.com', '서울시 노원구', default, sysdate, default);
insert into member values('jisoo', 'bbbb', '지수', '95-01-03', 'F', '01096857324', 'jisoo@naver.com', '서울시 도봉구', default, sysdate, default);
insert into member values('rose', 'cccc', '로제', '97-02-11', 'F', '01087940192', 'rose@naver.com', '서울시 강북구', default, sysdate, default);
insert into member values('lisa', 'dddd', '리사', '97-03-27', 'F', '01056482093', 'lisa@naver.com', '서울시 강동구', default, sysdate, default);
insert into member values('yoona', 'eeee', '윤아', '90-05-30', 'F', '01009451278', 'yoona@naver.com', '서울시 성북구', default, sysdate, default);
insert into member values('sunny', 'ffff', '써니', '89-05-15', 'F', '01084732812', 'sunny@daum.net', '서울시 관악구', default, sysdate, default);
insert into member values('taeyeon', 'gggg', '태연', '89-03-09', 'F', '01055992233', 'taeyeon@daum.net', '서울시 금천구', default, sysdate, default);
insert into member values('tiffany', 'wwww', '티파니', '89-08-01', 'F', '01011335577', 'tiffany@daum.net', '서울시 구로구', default, sysdate, default);
insert into member values('yuri', 'rrrr', '유리', '89-12-05', 'F', '01044668800', 'yuri@daum.net', '서울시 강서구', default, sysdate, default);
insert into member values('jessica', 'xxxx', '제시카', '89-04-18', 'F', '01099331155', 'jessica@daum.net', '서울시 용산구', default, sysdate, default);
insert into pet values('admin', SEQ_PET_NO.NEXTVAL, '417824395335398', 'D', '1', '19-01-07', 'M', '대', default, 'N');
insert into pet values('jaeseok77', SEQ_PET_NO.NEXTVAL, '258542020476001', 'D', '2', '17-05-30', 'F', '중', default, 'Y');
insert into pet values('haha12', SEQ_PET_NO.NEXTVAL, '412555466460461', 'D', '3', '14-02-28', 'F', '소', default, 'Y');
insert into pet values('hongchul88', SEQ_PET_NO.NEXTVAL, '882444385033031', 'D', '4', '18-07-31', 'F', '돌핀', default, 'Y');
insert into pet values('junha00', SEQ_PET_NO.NEXTVAL, '633683536860210', 'D', '5', '18-04-23', 'F', '뚱뚱이', default, 'Y');
insert into pet values('greatPark22', SEQ_PET_NO.NEXTVAL, '737170281938707', 'D', '6', '12-10-15', 'F', '늙은이', default, 'Y');
insert into pet values('seho33', SEQ_PET_NO.NEXTVAL, '266515445129772', 'D', '7', '17-05-15', 'F', '배추', default, 'Y');
insert into pet values('sehyeong44', SEQ_PET_NO.NEXTVAL, '1754853624947139', 'D', '8', '19-01-01', 'F', '얍삽이', default, 'Y');
insert into pet values('sechan55', SEQ_PET_NO.NEXTVAL, '4415911676336229', 'D', '9', '19-01-01', 'F', '쌥쌥이', default, 'Y');
insert into pet values('hong', SEQ_PET_NO.NEXTVAL, '3371661780862751', 'D', '10', '16-11-27', 'F', '똥똥이', default, 'Y');
insert into pet values('jennie', SEQ_PET_NO.NEXTVAL, '3313336693727504', 'D', '11', '15-10-20', 'M', '찡구', default, 'Y');
insert into pet values('jennie', SEQ_PET_NO.NEXTVAL, '553721333254144', 'D', '24', '17-11-20', 'M', '쫑구', default, 'Y');
insert into pet values('jisoo', SEQ_PET_NO.NEXTVAL, '367464966136553', 'D', '12', '18-12-06', 'M', '뚜두', default, 'Y');
insert into pet values('rose', SEQ_PET_NO.NEXTVAL, '824001185943656', 'D', '13', '18-11-16', 'M', '로즈', default, 'Y');
insert into pet values('rose', SEQ_PET_NO.NEXTVAL, '647152736473199', 'D', '26', '19-01-06', 'M', '리본', default, 'Y');
insert into pet values('lisa', SEQ_PET_NO.NEXTVAL, '2175192372380423', 'D', '14', '19-01-05', 'M', '화사', default, 'Y');
insert into pet values('lisa', SEQ_PET_NO.NEXTVAL, '74818287682296', 'D', '25', '19-01-05', 'M', '뭉이', default, 'Y');
insert into pet values('yoona', SEQ_PET_NO.NEXTVAL, '4313972858728596', 'D', '15', '19-01-01', 'M', '일동이', default, 'Y');
insert into pet values('yoona', SEQ_PET_NO.NEXTVAL, '249495710396579', 'D', '16', '19-01-01', 'M', '이동이', default, 'Y');
insert into pet values('sunny', SEQ_PET_NO.NEXTVAL, '59892767107946', 'D', '17', '18-11-13', 'M', '쭈꾸미', default, 'Y');
insert into pet values('sunny', SEQ_PET_NO.NEXTVAL, '4450952352603202', 'D', '18', '19-01-01', 'M', '오찡이', default, 'Y');
insert into pet values('taeyeon', SEQ_PET_NO.NEXTVAL, '325908897357714', 'D', '19', '18-11-12', 'M', '감자', default, 'Y');
insert into pet values('tiffany', SEQ_PET_NO.NEXTVAL, '4647475873295061', 'D', '20', '18-12-26', 'M', '크림', default, 'Y');
insert into pet values('yuri', SEQ_PET_NO.NEXTVAL, '4301955623961307', 'D', '21', '15-07-26', 'M', '포천이', default, 'Y');
insert into pet values('jessica', SEQ_PET_NO.NEXTVAL, '3681292549899475', 'D', '22', '12-02-10', 'M', '참이슬', default, 'Y');
insert into pet values('jessica', SEQ_PET_NO.NEXTVAL, '712049332022859', 'D', '23', '12-02-10', 'M', '처음처럼', default, 'Y');

--수정
--member 테이블
insert into member values('flower99', 'f051299r', '민들레', '99-05-12' ,'F' ,'01022398090', 'flower99@naver.com', '경기도 안양시', default, default, default);  
insert into member values('kimheebibi', 'heebi3942', '김희정', '93-02-13' ,'F' ,'01099823192', 'kimheebibi@gmail.com', '서울시 관악구', default, default, default);  
insert into member values('so2raim', 'im9970b', '임소라', '91-11-20' ,'F' ,'01067203820', 'so2raim@naver.com', '경기도 수원시', default, default, default);  
insert into member values('parksoso', 'pa0so2', '박소정', '90-01-02' ,'F' ,'01080792238', 'parksoso@naver.com', '서울시 강남구', default, default, default);  
insert into member values('noheeju', 'heejjuda', '노희주', '98-06-15' ,'F' ,'01021103102', 'noheeju@naver.com', '서울시 강남구', default, default, default);  
insert into member values('parksung', 'qwert99', '박성주', '87-12-19' ,'M' ,'01089023129', 'parksung@hanmail.net', '경기도 군포시', default, default, default);  
insert into member values('namheesung1', 'hesung123', '남희성', '91-08-16' ,'M' ,'01067133330', 'namheesung1@naver.com', '서울시 강서구', default, default, default);  
insert into member values('hyunbum76', 'hh7676bb', '장현범', '76-07-23' ,'M' ,'01077302889', 'hyunbum76@gmail.com', '부산광역시 남구', default, default, default);  
insert into member values('jungsunmi', 'sunny3', '정선미', '75-06-25' ,'F' ,'01031447688', 'jungsunmi@hanmail.net', '부산광역시 남구', default, default, default);  
insert into member values('soheechu', 'sosohh333', '김소희', '89-05-12' ,'F' ,'01033398887', 'soheechu@nate.com', '부산광역시 동래구', default, default, default);  
insert into member values('do3ob', 'nas7190', '나성오', '88-05-02' ,'M' ,'01066577190', 'do3ob7190@nate.com', '강원도 정선군', default, default, default);  
insert into member values('miniggo', 'sominida9', '유소민', '85-07-09' ,'F' ,'01062308871', 'miniggo6230@gmail.com', '경기도 양평군', default, default, default);  
insert into member values('hy9229', 'h92298182', '유혜주', '99-12-01' ,'F' ,'01092298182', 'hy9229@gmail.com', '경기도 오산시', default, default, default);  
insert into member values('minsungchoi', 'chch123', '최민성', '00-02-10' ,'M' ,'01083203223', 'minsungchoi@nate.com', '제주도 제주시', default, default, default);  
insert into member values('sugurdaldal', 'dalcong8', '조윤희', '01-01-17' ,'F' ,'01058835899', 'sugurdal8@yahoo.com', '경상북도 안동시', default, default, default);  
insert into member values('jangrose24', 'rose9980j', '공장미', '00-11-24' ,'F' ,'01030809980', 'jangrose24@yahoo.com', '경상북도 영덕군', default, default, default);  
insert into member values('bestjung', 'bb3gogo', '최선중', '02-12-31' ,'M' ,'01081703320', 'bestjung@nate.com', '전라북도 완주군', default, default, default);  
insert into member values('xxxsunghoxxx', 'xsungx9704', '윤성호', '97-04-21' ,'M' ,'01026558778', 'xxxsunghoxxx@hanmail.net', '전라남도 나주시', default, default, default);  
insert into member values('lovely7', 'love3love', '김사랑', '92-03-02' ,'F' ,'01039222939', 'lovely7923@naver.com', '충청남도 공주시', default, default, default);  
insert into member values('saltjune', 's09onjune12', '염성준', '90-06-19' ,'M' ,'01042288787', 'saltjune90@gmail.com', '강원도 동해시', default, default, default);  

--pet 테이블 
insert into pet values('saltjune', SEQ_PET_NO.NEXTVAL, '409132903931240', 'D', '20', '19-01-03', 'F', '불리', default, 'Y');
insert into pet values('lovely7', SEQ_PET_NO.NEXTVAL, '409133123998040', 'D', '9', '11-04-13', 'M', '구름', default, 'Y');
insert into pet values('lovely7', SEQ_PET_NO.NEXTVAL, '405294903812943', 'E', '10', '07-08-23', 'M', '뭉게', default, 'N');
insert into pet values('xxxsunghoxxx', SEQ_PET_NO.NEXTVAL, '324139803031949', 'C', '10', '15-05-29', 'F', '조이', default, 'Y');
insert into pet values('saltjune', SEQ_PET_NO.NEXTVAL, '218132966891232', 'D', '17', '09-06-03', 'F', '모모', default, 'N');
insert into pet values('miniggo', SEQ_PET_NO.NEXTVAL, '898138273991235','D', '32', '06-11-11', 'M', '겨울', default, 'N');
insert into pet values('miniggo', SEQ_PET_NO.NEXTVAL, '403132965591266', 'C', '23', '11-12-07', 'M', '가을', default, 'N');
insert into pet values('hy9229', SEQ_PET_NO.NEXTVAL, '328132922491232', 'D', '4', '13-05-13', 'F', '세바스찬', default, 'N');
insert into pet values('jangrose24', SEQ_PET_NO.NEXTVAL, '200132964129120', 'C', '3', '14-03-29', 'M', '미오', default, 'Y');
insert into pet values('jangrose24', SEQ_PET_NO.NEXTVAL, '208132975758200', 'E', '8', '07-06-21', 'F', '미우', default, 'Y');
insert into pet values('parksoso', SEQ_PET_NO.NEXTVAL, '388132986758208', 'C', '29', '19-01-02', 'F', '천둥', default, 'Y');
insert into pet values('parksoso', SEQ_PET_NO.NEXTVAL, '298132450758250', 'E', '21', '16-04-22', 'M', '비', default, 'Y');
insert into pet values('parksoso', SEQ_PET_NO.NEXTVAL, '832140965758240', 'D', '38', '07-07-25', 'M', '번개', default, 'Y');
insert into pet values('flower99', SEQ_PET_NO.NEXTVAL, '908132534758202', 'D', '35', '12-11-14', 'F', '토토', default, 'N');
insert into pet values('flower99', SEQ_PET_NO.NEXTVAL, '108789975758230', 'D', '18', '14-05-09', 'M', '포포', default, 'N');
insert into pet values('kimheebibi', SEQ_PET_NO.NEXTVAL, '228786742758620', 'D', '2', '12-05-13', 'F', '부기', default, 'Y');
insert into pet values('kimheebibi', SEQ_PET_NO.NEXTVAL, '138839978858470', 'D', '1', '12-07-23', 'M', '버키', default, 'N');
insert into pet values('so2raim', SEQ_PET_NO.NEXTVAL, '801322975529230', 'C', '11', '04-03-22', 'F', '초코', default, 'Y');
insert into pet values('noheeju', SEQ_PET_NO.NEXTVAL, '528734976660233', 'C', '19', '09-11-26', 'M', '건빵', default, 'N');
insert into pet values('parksung', SEQ_PET_NO.NEXTVAL, '988324975755902', 'E', '3', '07-12-30', 'F', '둥이', default, 'Y');
insert into pet values('namheesung1', SEQ_PET_NO.NEXTVAL, '302454525255942', 'D', '29', '04-06-19', 'F', '딸기', default, 'N');
insert into pet values('namheesung1', SEQ_PET_NO.NEXTVAL, '950032385754456', 'D', '20', '10-10-01', 'M', '포도', default, 'N');
insert into pet values('hyunbum76', SEQ_PET_NO.NEXTVAL, '888324975757802', 'E', '16', '11-10-30', 'M', '소다', default, 'N');
insert into pet values('do3ob', SEQ_PET_NO.NEXTVAL, '428362425755356', 'C', '18', '16-03-29', 'M', '정국', default, 'Y');
insert into pet values('sugurdaldal', SEQ_PET_NO.NEXTVAL, '198324975754478', 'D', '10', '14-08-20', 'F', '방울', default, 'Y');

--재범
insert into member values('spicionly','1234','양국화','87/03/04','F','01077332342','BeverlyPHicks@dreamiwz.com','서울 특별시 강남구',default,sysdate,default);
insert into pet values('spicionly', SEQ_PET_NO.NEXTVAL, '406627124216516', 'D', '3', '19-01-03', 'M', '다롱이', default, 'N');
insert into member values('hising','1234','주전원','87/02/06','M','01033325642','MildredHSpivey@naver.com','광주 광역시 서구',default,sysdate,default);
insert into pet values('hising', SEQ_PET_NO.NEXTVAL, '784657286236895', 'C', '7', '13-04-05', 'F', '순심이', default, 'Y');
insert into member values('wjdqls1122','1234','최증빈','83/02/06','F','01022332452','wjdqls1122@naver.com','충청남도 아산시',default,sysdate,default);
insert into pet values('wjdqls1122', SEQ_PET_NO.NEXTVAL, '469706165428696', 'E', '12', '14-01-07', 'M', '쫑이', default, 'Y');
insert into pet values('wjdqls1122', SEQ_PET_NO.NEXTVAL, '158416082305422', 'D', '20', '16-07-03', 'F', '뿌야', default, 'Y');
insert into member values('woqjafe2233','1234','주재녀','90/03/04','F','01093672322','woqjafe@dreamwiz.com','충청북도 제천시 독순로',default,sysdate,default);
insert into pet values('woqjafe2233', SEQ_PET_NO.NEXTVAL, '617309592383274', 'C', '15', '11-11-08', 'M', '메리', default, 'Y');
insert into member values('woqjama3322','1234','주재남','91/04/03','M','01023229367','woqjama@gamil.com','충청북도 제천시 독순로',default,sysdate,default);
insert into pet values('woqjama3322', SEQ_PET_NO.NEXTVAL, '941095807461056', 'E', '18', '12-03-06', 'F', '뽀야', default, 'Y');
insert into pet values('woqjama3322', SEQ_PET_NO.NEXTVAL, '180586170207371', 'D', '13', '13-12-07', 'M', '토리', default, 'Y');
insert into member values('crystal4411','1234','박수정','95/03/04','F','01033662312','crystalpark@daum.net','경상남도 김해시',default,sysdate,default);
insert into pet values('crystal4411', SEQ_PET_NO.NEXTVAL, '703938411152020', 'C', '8', '14-05-05', 'F', '보리', default, 'Y');
insert into member values('jong765','1234','김종원','93/02/02','M','01029642111','jong765@hanmail.net','전라북도 군산시',default,sysdate,default);
insert into pet values('jong765', SEQ_PET_NO.NEXTVAL, '660470016375166', 'E', '11', '15-06-02', 'M', '봄이', default, 'Y');
insert into member values('hyunjoong234','1234','이현중','89/06/03','M','01034571223','joong234@nate.com','서울 특별시 관악구',default,sysdate,default);
insert into pet values('hyunjoong234', SEQ_PET_NO.NEXTVAL, '726870974591460', 'D', '21', '16-03-01', 'F', '가을이', default, 'Y');
insert into member values('sunghoon337','1234','김성훈','92/01/04','M','01052823344','sunghoon337@nate.com','서울 특별시 양천구',default,sysdate,default);
insert into pet values('sunghoon337', SEQ_PET_NO.NEXTVAL, '379003203692127', 'C', '13', '17-02-22', 'M', '시월이', default, 'Y');
insert into pet values('sunghoon337', SEQ_PET_NO.NEXTVAL, '372974308063918', 'E', '38', '18-03-13', 'F', '탱이', default, 'Y');
insert into member values('joo52487','1234','주충권','86/03/01','M','01033332345','joo52487@dreamwiz.com','충청북도 충주시',default,sysdate,default);
insert into pet values('joo52487', SEQ_PET_NO.NEXTVAL, '397107825052844', 'D', '4', '10-05-08', 'M', '쁜이', default, 'Y');
insert into member values('kimhyang253','1234','김향원','95/07/08','F','01077320708','kimhyang253@gmail.com','서울 특별시 강동구',default,sysdate,default);
insert into pet values('kimhyang253', SEQ_PET_NO.NEXTVAL, '644529412044889', 'C', '6', '11-10-10', 'F', '절미', default, 'Y');
insert into member values('daechan2222','1234','최대찬','73/08/03','M','01057526937','kangchoi@nate.com','서울 특별시 서대문구',default,sysdate,default);
insert into pet values('daechan2222', SEQ_PET_NO.NEXTVAL, '180989502802106', 'E', '23', '12-08-07', 'M', '딸기', default, 'Y');
insert into pet values('daechan2222', SEQ_PET_NO.NEXTVAL, '435105254897643', 'D', '32', '13-05-06', 'F', '까미', default, 'Y');
insert into member values('bang3372','1234','김병승','98/06/04','M','01088953387','prince3372@gmail.com','충청북도 청주시',default,sysdate,default);
insert into pet values('bang3372', SEQ_PET_NO.NEXTVAL, '562833982165075', 'C', '5', '14-09-21', 'M', '후추', default, 'Y');
insert into member values('park007','1234','박명박','61/09/04','M','01055326789','parkmyung007@nate.com','전라남도 순천시',default,sysdate,default);
insert into pet values('park007', SEQ_PET_NO.NEXTVAL, '167357149373251', 'E', '6', '15-10-15', 'F', '콩이', default, 'Y');
insert into member values('hye333','1234','유근혜','99/03/04','F','01075439999','keunhye333@naver.com','대구광역시 남구',default,sysdate,default);
insert into pet values('hye333', SEQ_PET_NO.NEXTVAL, '463399599083411', 'D', '8', '16-07-21', 'M', '연탄이', default, 'Y');
insert into member values('imf0303','1234','이영삼','81/06/08','M','01053892992','zerothree03@dreamwiz.com','경상남도 창원시',default,sysdate,default);
insert into pet values('imf0303', SEQ_PET_NO.NEXTVAL, '475904089310841', 'C', '9', '17-09-13', 'F', '미키', default, 'Y');
insert into member values('daejoongbig93','1234','박대중','89/03/04','M','01099332211','bigjoong@nate.com','충남 천안시',default,sysdate,default);
insert into pet values('daejoongbig93', SEQ_PET_NO.NEXTVAL, '942325346364423', 'E', '10', '18-01-02', 'M', '짱구', default, 'Y');
insert into member values('minjoo9988','1234','최미애','99/02/09','F','01037572312','minjoo8899@gmail.com','부산 광역시 센텀시티',default,sysdate,default);
insert into pet values('minjoo9988', SEQ_PET_NO.NEXTVAL, '180137511632523', 'D', '11', '07-05-03', 'F', '홀리', default, 'Y');
insert into member values('jaehoon33','1234','박제훈','43/09/27','M','01094686422','jaehoon33@freechal.com','충남 아산시',default,sysdate,default);
insert into pet values('jaehoon33', SEQ_PET_NO.NEXTVAL, '670897377216346', 'C', '12', '08-03-11', 'M', '구름이', default, 'Y');
insert into member values('jong7878','1234','유종필','98/11/04','M','01078782222','jong7878@freechal.com','충청남도 천안시',default,sysdate,default);
insert into pet values('jong7878', SEQ_PET_NO.NEXTVAL, '704995218671098', 'E', '13', '19-01-01', 'F', '똘똘이', default, 'N');
insert into pet values('jong7878', SEQ_PET_NO.NEXTVAL, '296790914881400', 'D', '14', '19-01-01', 'M', '두부', default, 'N');

--충권
--멤버인서트
insert into member values('DRAGON99', 'DRAGONr', '이용', '92-05-17' ,'M' ,'01012322232', 'DRAGON99@naver.com', '경기도 수원시', default, default, default);  
insert into member values('gerrard1', 'rrardege1', '제라드', '74-05-12' ,'M' ,'01056732232', 'gerrard192@naver.com', '경기도 성남시', default, default, default);  
insert into member values('g45dfdd1', 'rdfd3dege1', '박지성', '99-04-22' ,'M' ,'01055932232', 'manu192@naver.com', '경기도 성남시', default, default, default);  
insert into member values('rampard44', '2udnvid', '김램파드', '76-09-12' ,'M' ,'01056732245', 'rampard33@naver.com', '경기도 포천시', default, default, default);  
insert into member values('amoomoo', 'moomoo334', '정성미', '71-02-27' ,'F','01077652232', 'sungmi@naver.com', '경기도 가평시', default, default, default);  
insert into member values('jonryoungeunsu1', 'eunsoo', '김은수', '55-03-22' ,'M' ,'01048847754', 'eunsoo@naver.com', '경기도 가평시', default, default, default);  
insert into member values('infoin23', 'mation11', '한정보', '99-05-12' ,'F' ,'01011411141', 'infogirl1@naver.com', '경기도 포천시', default, default, default);  
insert into member values('zw324f3rg', '1s3g5gc', '이성근', '89-11-12' ,'M' ,'01012342256', 'sungeun1@naver.com', '경기도 오산시', default, default, default);  
insert into member values('78ddhvjdn13', 'luck135q', '한지민', '88-04-22' ,'F' ,'01099872284', 'JIMIN12@naver.com', '경기도 성남시', default, default, default);  
insert into member values('chanhoho1', 'hohochan3', '배찬호', '94-11-12' ,'M' ,'01088673345', 'chanchanchan22@naver.com', '경기도 김포시', default, default, default);  
insert into member values('jihyun11', 'kwonji11', '권지현', '89-04-02' ,'F' ,'01077571123', 'JIHYUN22@naver.com', '경기도 성남시', default, default, default);  
insert into member values('thodknc23', 'ezrandg3', '조재희', '92-05-12' ,'F' ,'01088678343', 'goonSO@naver.com', '경기도 과천시', default, default, default);  
insert into member values('kof114fj', 'notnull112', '한공백', '97-07-11' ,'M' ,'01024575232', 'notnull33@naver.com', '서울특별시 은평구', default, default, default);  
insert into member values('woowool335', 'gloobjd35', '이기쁨', '94-12-12' ,'F' ,'01056732232', 'happy192@naver.com', '경기도 성남시', default, default, default);  
insert into member values('kane128dkn', 'dribble355', '염창운', '98-12-02' ,'M' ,'01098788769', 'changwoon36@naver.com', '경기도 하남시', default, default, default);  
insert into member values('lcrctoeic', 'todd33dn', '한영수', '99-08-15' ,'M' ,'01088789908', 'engmath3592@naver.com', '경기도 성남시', default, default, default);  
insert into member values('ncsver2', 'sc4fnfd2', '유방승', '00-05-11' ,'M' ,'01036522232', 'bangprince345@naver.com', '경기도 시흥시', default, default, default);  
insert into member values('lovo4hf', 'liver3gf', '윤혜은', '99-07-10' ,'F' ,'01038948392', 'hyenddded36@naver.com', '강원도 원주시', default, default, default);  
insert into member values('cheedm2e', 'jlovd32t', '김사랑', '74-09-14' ,'M' ,'01099484632', 'lovelove3392@naver.com', '경기도 평택시', default, default, default);  
insert into member values('dnkcn235', 'acond35r', '왕풍성', '02-02-02' ,'M' ,'0100909302', 'mobal3fg@naver.com', '경기도 성남시', default, default, default);  

--동물테이블
insert into pet values('dnkcn235', SEQ_PET_NO.NEXTVAL, '378447684397345', 'D', '20', '19-01-03', 'F', '제라드', default, 'Y');
insert into pet values('dnkcn235', SEQ_PET_NO.NEXTVAL, '478947684745784', 'C', '4', '18-01-03', 'F', '램파드', default, 'Y');
insert into pet values('lovo4hf', SEQ_PET_NO.NEXTVAL, '124132903931240', 'D', '18', '19-01-01', 'F', '해리', default, 'Y');
insert into pet values('lovo4hf', SEQ_PET_NO.NEXTVAL, '378758938758497', 'D', '20', '19-01-03', 'F', '포터', default, 'Y');
insert into pet values('ncsver2', SEQ_PET_NO.NEXTVAL, '403453290393124', 'D', '4', '17-05-13', 'F', '볼드', default, 'Y');
insert into pet values('ncsver2', SEQ_PET_NO.NEXTVAL, '409899473431240', 'D', '20', '19-01-03', 'F', '모트', default, 'Y');
insert into pet values('woowool335', SEQ_PET_NO.NEXTVAL, '356995838239059', 'D', '11', '16-01-13', 'F', '카카', default, 'Y');
insert into pet values('woowool335', SEQ_PET_NO.NEXTVAL, '948594896894305', 'D', '8', '14-08-03', 'F', '로트', default, 'Y');
insert into pet values('lcrctoeic', SEQ_PET_NO.NEXTVAL, '409132098898240', 'E', '33', '19-01-03', 'F', '내기니', default, 'Y');
insert into pet values('zw324f3rg', SEQ_PET_NO.NEXTVAL, '998767803931240', 'D', '7', '18-10-09', 'M', '믿음', default, 'Y');
insert into pet values('zw324f3rg', SEQ_PET_NO.NEXTVAL, '940132903931240', 'D', '7', '18-10-09', 'F', '희망', default, 'Y');
insert into pet values('zw324f3rg', SEQ_PET_NO.NEXTVAL, '421390093931240', 'D', '7', '18-10-09', 'F', '사랑', default, 'Y');
insert into pet values('jihyun11', SEQ_PET_NO.NEXTVAL, '433231991090240', 'C', '9', '18-11-11', 'F', '빼빼로', default, 'Y');
insert into pet values('jihyun11', SEQ_PET_NO.NEXTVAL, '320931324909140', 'D', '20', '19-01-02', 'F', '루이', default, 'Y');
insert into pet values('78ddhvjdn13', SEQ_PET_NO.NEXTVAL, '403991331229040', 'E', '38', '18-12-31', 'F', '왕굴', default, 'Y');
insert into pet values('amoomoo', SEQ_PET_NO.NEXTVAL, '290391334091240', 'C', '1', '18-01-03', 'M', '아무무', default, 'Y');
insert into pet values('infoin23', SEQ_PET_NO.NEXTVAL, '429030913931240', 'D', '7', '18-07-03', 'M', '티모', default, 'Y');
insert into pet values('infoin23', SEQ_PET_NO.NEXTVAL, '290409113393240', 'D', '19', '19-01-03', 'F', '뎀바바', default, 'Y');
insert into pet values('DRAGON99', SEQ_PET_NO.NEXTVAL, '429030913931240', 'D', '17', '16-01-22', 'F', '카카', default, 'Y');
insert into pet values('DRAGON99', SEQ_PET_NO.NEXTVAL, '409193329031240', 'D', '16', '18-05-01', 'F', '긱스', default, 'Y');
insert into pet values('gerrard1', SEQ_PET_NO.NEXTVAL, '403931209132940', 'D', '16', '19-01-03', 'F', '포그', default, 'Y');
insert into pet values('gerrard1', SEQ_PET_NO.NEXTVAL, '329039409131240', 'D', '25', '19-01-03', 'F', '산구', default, 'Y');
insert into pet values('thodknc23', SEQ_PET_NO.NEXTVAL, '409132903931240', 'D', '15', '19-01-03', 'F', '살라', default, 'Y');
insert into pet values('thodknc23', SEQ_PET_NO.NEXTVAL, '439310913290240', 'D', '34', '19-01-03', 'F', '마네', default, 'Y');
insert into pet values('kof114fj', SEQ_PET_NO.NEXTVAL, '429039901331240', 'D', '36', '19-01-03', 'F', '사네', default, 'Y');

--관리자
insert into member values('admin1', 'admin1', '주재범', '89-06-06' ,'M' ,'01050550366', 'jaebum2000@dreamwiz.com', '서울특별시 관악구', default, default, 'Y');  
insert into member values('admin2', 'admin2', '박종언', '90-01-27' ,'M' ,'01056461991', 'pjun127@naver.com', '서울특별시 동작구', default, default, 'Y');  
insert into member values('admin3', 'admin3', '이충권', '92-09-24' ,'M' ,'01041030372', 'cr7ck@naver.com', '경기도 화성시', default, default, 'Y');  
insert into member values('admin4', 'admin4', '이성훈', '93-04-16' ,'M' ,'01052539848', 'lsh930416@naver.com', '서울특별시 강서구', default, default, 'Y');  
insert into member values('admin5', 'admin5', '김현중', '94-11-19' ,'M' ,'01045159411', 'xorhsdlwl@naver.com', '서울특별시 관악구', default, default, 'Y');  
insert into member values('admin6', 'admin6', '김수정', '95-05-14' ,'F' ,'01026596829', 'soojeong2659@naver.com', '경기도 의왕시', default, default, 'Y');  
select * from doctor;









--분양 데이터 입력
--1
insert into parcel values('골든 리트리버 분양합니다~','D',seq_parcel_post_no.nextval,default,'flower99','1','19-01-03','서울특별시 강북구','M','Y',200000,'저희 아이가 이번에 새끼를 낳아서 분양해요 순종 인증 가능합니다, 부모 둘다 골든리트리버구요 완전 작고 귀여워요~',default,'종합백신,코로나장염,켄넬코프,광견병,인플루엔자,심장사상충');
insert into parcel_img values(seq_parcel_img_no.nextval,'GoldRitriber1.jpg',1,'M');
insert into parcel_img values(seq_parcel_img_no.nextval,'GoldRitriber2.jpg',1,'S');
insert into parcel_img values(seq_parcel_img_no.nextval,'GoldRitriber3.jpg',1,'S');
insert into parcel_img values(seq_parcel_img_no.nextval,'GoldRitriber4.jpg',1,'S');
insert into parcel_img values(seq_parcel_img_no.nextval,'GoldRitriber5.jpg',1,'S');
--2
insert into parcel values('러시안 블루 분양해요!','C',seq_parcel_post_no.nextval,default,'kungwoo31','2','18-10-03','부산광역시 해운대구','F','N',250000,'러시안블루에요 블루블랙 색 털이 매우 매력적이고 사람한테 잘 안기는 개냥이에요 ㅋㅋㅋ',default,'종합백신');
insert into parcel_img values(seq_parcel_img_no.nextval,'rb5.jpg',2,'M');
insert into parcel_img values(seq_parcel_img_no.nextval,'rb1.jpg',2,'S');
insert into parcel_img values(seq_parcel_img_no.nextval,'rb2.jpg',2,'S');
insert into parcel_img values(seq_parcel_img_no.nextval,'rb3.jpg',2,'S');
insert into parcel_img values(seq_parcel_img_no.nextval,'rb4.jpg',2,'S');
--3
insert into parcel values('페럿 분양해요!','E',seq_parcel_post_no.nextval,default,'admin1','1','18-10-03','서울특별시 관악구','F','N',150000,'제가 기르던 페럿이에요 사정이생겨서 분양합니다 책임지고 예뻐해주실 분 찾아요',default,'종합백신');
insert into parcel_img values(seq_parcel_img_no.nextval,'parrot2.jpg',3,'M');
insert into parcel_img values(seq_parcel_img_no.nextval,'parrot1.jpg',3,'S');
insert into parcel_img values(seq_parcel_img_no.nextval,'parrot3.jpg',3,'S');
insert into parcel_img values(seq_parcel_img_no.nextval,'parrot4.jpg',3,'S');
insert into parcel_img values(seq_parcel_img_no.nextval,'parrot5.jpg',3,'S');
--4
insert into parcel values('인형같은 렉돌!','C',seq_parcel_post_no.nextval,default,'admin4','19','19-01-03','서울특별시 강서구','M','N',150000,'진짜 인형같은 렉돌 분양합니다~',default,'종합백신');
insert into parcel_img values(seq_parcel_img_no.nextval,'lekdol4.jpg',4,'M');
insert into parcel_img values(seq_parcel_img_no.nextval,'lekdol1.jpg',4,'S');
insert into parcel_img values(seq_parcel_img_no.nextval,'lekdol2.jpg',4,'S');
insert into parcel_img values(seq_parcel_img_no.nextval,'lekdol3.jpg',4,'S');
insert into parcel_img values(seq_parcel_img_no.nextval,'lekdol5.jpg',4,'S');
--5
insert into parcel values('포메라니안 분양해요','D',seq_parcel_post_no.nextval,default,'jaeseok77','34','19-01-01','광주광역시 광산구','M','N',100000,'포메라니안 분양해요 완전 귀엽고 예쁜 아이에요 책임분양 원해요',default,'종합백신,광견병,인플루엔자,심장사상충');
insert into parcel_img values(seq_parcel_img_no.nextval,'pome1.jpg',5,'M');
insert into parcel_img values(seq_parcel_img_no.nextval,'pome2.jpg',5,'S');
insert into parcel_img values(seq_parcel_img_no.nextval,'pome4.jpg',5,'S');
insert into parcel_img values(seq_parcel_img_no.nextval,'pome6.jpg',5,'S');
insert into parcel_img values(seq_parcel_img_no.nextval,'pome7.jpg',5,'S');
--6
insert into parcel values('아비시니안 책임분양합니다','C',seq_parcel_post_no.nextval,default,'daejoongbig93','11','18-12-11','서울특별시 강남구','M','N',300000,'아비시니안 고양이에요 제가 분양샵을 운영하는데 이번에 너무나 귀여운 아이가 2마리나 와서 글 올려봅니다~ 한마리당 가격이구 문의 많이 주세요~',default,'종합백신');
insert into parcel_img values(seq_parcel_img_no.nextval,'abisinian1.jpg',6,'M');
insert into parcel_img values(seq_parcel_img_no.nextval,'abisinian2.jpg',6,'S');
insert into parcel_img values(seq_parcel_img_no.nextval,'abisinian3.jpg',6,'S');
insert into parcel_img values(seq_parcel_img_no.nextval,'abisinian4.jpg',6,'S');
insert into parcel_img values(seq_parcel_img_no.nextval,'abisinian5.jpg',6,'S');
--7
insert into parcel values('아메리칸 숏헤어','C',seq_parcel_post_no.nextval,default,'daejoongbig93','27','18-11-11','서울특별시 강남구','F','N',200000,'아메리칸 숏헤어라는 단모종이에요 털이 짧고 매력적인 아이입니다 ^^',default,'종합백신');
insert into parcel_img values(seq_parcel_img_no.nextval,'americanshorthair1.jpg',7,'M');
insert into parcel_img values(seq_parcel_img_no.nextval,'americanshorthair2.jpg',7,'S');
insert into parcel_img values(seq_parcel_img_no.nextval,'americanshorthair3.jpg',7,'S');
insert into parcel_img values(seq_parcel_img_no.nextval,'americanshorthair4.jpg',7,'S');
insert into parcel_img values(seq_parcel_img_no.nextval,'americanshorthair5.jpg',7,'S');
--8
insert into parcel values('강남구 뱅갈 분양','C',seq_parcel_post_no.nextval,default,'daejoongbig93','5','18-10-13','서울특별시 강남구','F','N',220000,'벵갈 고양이에요 호랑이무늬가 아주멋진 아이죠 ㅋㅋㅋ',default,'종합백신');
insert into parcel_img values(seq_parcel_img_no.nextval,'bangal1.jpg',8,'M');
insert into parcel_img values(seq_parcel_img_no.nextval,'bangal2.jpg',8,'S');
insert into parcel_img values(seq_parcel_img_no.nextval,'bangal3.jpg',8,'S');
insert into parcel_img values(seq_parcel_img_no.nextval,'bangal4.jpg',8,'S');
insert into parcel_img values(seq_parcel_img_no.nextval,'bangal5.jpg',8,'S');
--9
insert into parcel values('저희 아이좀 데려가주세요 ㅜㅜ','D',seq_parcel_post_no.nextval,default,'minsungchoi','17','19-01-02','제주도 서귀포시','M','N',120000,'비숑프리제 제가 어머니몰래 샀는데 집에서 못키우게 해서 지금 분양샵에 가있어요 책임감 강하신분께 하고싶어서 글 남겨봅니다 ㅠㅠ',default,'종합백신');
insert into parcel_img values(seq_parcel_img_no.nextval,'bisyong1.jpg',9,'M');
insert into parcel_img values(seq_parcel_img_no.nextval,'bisyong2.jpg',9,'S');
insert into parcel_img values(seq_parcel_img_no.nextval,'bisyong3.jpg',9,'S');
insert into parcel_img values(seq_parcel_img_no.nextval,'bisyong4.jpg',9,'S');
insert into parcel_img values(seq_parcel_img_no.nextval,'bisyong5.jpg',9,'S');
--10
insert into parcel values('카멜레온 분양합니다','E',seq_parcel_post_no.nextval,default,'g45dfdd1','30','15-12-02','경기도 광주시','F','N',50000,'제가 기르던 카멜레온입니다. 사정상 이사를 가면서 못키우게 됐는데 데려다 키우실분 구해요',default,'종합백신');
insert into parcel_img values(seq_parcel_img_no.nextval,'camel1.jpg',10,'M');
insert into parcel_img values(seq_parcel_img_no.nextval,'camel2.jpg',10,'S');
insert into parcel_img values(seq_parcel_img_no.nextval,'camel3.jpg',10,'S');
--11
insert into parcel values('희귀애완동물 왕관앵무!','E',seq_parcel_post_no.nextval,default,'ncsver2','15','16-01-05','경기도 시흥시','M','N',100000,'왕관앵무라고 아시나요? 멋진 깃털을 가진 아름다운 앵무새입니다 처음엔 어색해도 키우다보면 잘 따르고 좋아요 연락 많이 주세요~',default,'종합백신');
insert into parcel_img values(seq_parcel_img_no.nextval,'crownangmu1.jpg',11,'M');
insert into parcel_img values(seq_parcel_img_no.nextval,'crownangmu2.jpg',11,'S');
insert into parcel_img values(seq_parcel_img_no.nextval,'crownangmu3.jpg',11,'S');
insert into parcel_img values(seq_parcel_img_no.nextval,'crownangmu4.jpg',11,'S');
insert into parcel_img values(seq_parcel_img_no.nextval,'crownangmu5.jpg',11,'S');
--12
insert into parcel values('닥스훈트 분양하실분?','D',seq_parcel_post_no.nextval,default,'jennie','3','19-01-02','서울특별시 강남구','F','N',200000,'분양받은지 얼마안됐는데 제가 외국에 갈일이있어서 분양하려고요 연락 주세요^^',default,'종합백신');
insert into parcel_img values(seq_parcel_img_no.nextval,'darks1.jpg',12,'M');
insert into parcel_img values(seq_parcel_img_no.nextval,'darks2.jpg',12,'S');
insert into parcel_img values(seq_parcel_img_no.nextval,'darks3.jpg',12,'S');
insert into parcel_img values(seq_parcel_img_no.nextval,'darks4.jpg',12,'S');
insert into parcel_img values(seq_parcel_img_no.nextval,'darks5.jpg',12,'S');
--13
insert into parcel values('제가 기르던 햄스터 데려가실분 있나요?','E',seq_parcel_post_no.nextval,default,'kungwoo31','2','18-01-02','경상남도 구미시','M','Y',100000,'부모님 반대로 급하게 분양원합니다',default,'종합백신,인플루엔자');
insert into parcel_img values(seq_parcel_img_no.nextval,'ham1.jpg',13,'M');
insert into parcel_img values(seq_parcel_img_no.nextval,'ham2.jpg',13,'S');
insert into parcel_img values(seq_parcel_img_no.nextval,'ham3.jpg',13,'S');
insert into parcel_img values(seq_parcel_img_no.nextval,'ham4.jpg',13,'S');
insert into parcel_img values(seq_parcel_img_no.nextval,'ham5.jpg',13,'S');
--14
insert into parcel values('희귀한 레드이구아나!!','E',seq_parcel_post_no.nextval,default,'pt5280','31','18-01-02','충청남도 아산시','F','Y',1000000,'정말 드문 레드 이구아나입니다 희귀종인 만큼 가격이 좀 비싸요 ㅠㅠ',default,'종합백신,인플루엔자');
insert into parcel_img values(seq_parcel_img_no.nextval,'iguana1.png',14,'M');
insert into parcel_img values(seq_parcel_img_no.nextval,'iguana2.png',14,'S');
insert into parcel_img values(seq_parcel_img_no.nextval,'iguana4.jpg',14,'S');
--15
insert into parcel values('웰시코기 분양샵 최저가!','D',seq_parcel_post_no.nextval,default,'pby12','29','19-01-02','충청북도 증평군','M','N',150000,'제가 분양샵을 운영하는데 너무 귀여운 아이가 들어와서 여기 홍보하려구 글 올려봐여 증평에서 최저가로 판매하고 있구 예방접종도 다 했습니다! 전화 많이주세요~',default,'종합백신,코로나장염,켄넬코프,광견병,인플루엔자,심장사상충');
insert into parcel_img values(seq_parcel_img_no.nextval,'kogi1.jpg',15,'M');
insert into parcel_img values(seq_parcel_img_no.nextval,'kogi2.jpg',15,'S');
insert into parcel_img values(seq_parcel_img_no.nextval,'kogi3.jpg',15,'S');
insert into parcel_img values(seq_parcel_img_no.nextval,'kogi4.jpg',15,'S');
insert into parcel_img values(seq_parcel_img_no.nextval,'kogi5.jpg',15,'S');
--16
insert into parcel values('코카스파니엘 분양샵 최저가!','D',seq_parcel_post_no.nextval,default,'pby12','33','19-01-02','충청북도 증평군','F','N',150000,'제가 분양샵을 운영하는데 너무 귀여운 아이가 들어와서 여기 홍보하려구 글 올려봐여 증평에서 최저가로 판매하고 있구 예방접종도 다 했습니다! 전화 많이주세요~',default,'종합백신,코로나장염,켄넬코프,광견병,인플루엔자,심장사상충');
insert into parcel_img values(seq_parcel_img_no.nextval,'koka1.jpg',16,'M');
insert into parcel_img values(seq_parcel_img_no.nextval,'koka2.jpg',16,'S');
insert into parcel_img values(seq_parcel_img_no.nextval,'koka3.jpg',16,'S');
insert into parcel_img values(seq_parcel_img_no.nextval,'koka4.jpg',16,'S');
insert into parcel_img values(seq_parcel_img_no.nextval,'koka5.jpg',16,'S');
--17
insert into parcel values('든든한 말라뮤트 어때요?','D',seq_parcel_post_no.nextval,default,'khs12','27','18-12-02','인천광역시 부평구','F','N',400000,'어릴땐 쪼꼬미 크면 반전매력인 말라뮤트 어때요? 덩치 커도 귀엽고 멋진 모습!',default,'종합백신,코로나장염,광견병,인플루엔자,심장사상충');
insert into parcel_img values(seq_parcel_img_no.nextval,'malamute1.jpg',17,'M');
insert into parcel_img values(seq_parcel_img_no.nextval,'malamute2.jpg',17,'S');
insert into parcel_img values(seq_parcel_img_no.nextval,'malamute3.jpg',17,'S');
insert into parcel_img values(seq_parcel_img_no.nextval,'malamute4.jpg',17,'S');
insert into parcel_img values(seq_parcel_img_no.nextval,'malamute5.jpg',17,'S');
--18
insert into parcel values('맹크스라고 아시나요?','C',seq_parcel_post_no.nextval,default,'mire258','3','18-12-02','부산광역시 영도구','F','Y',200000,'맹크스라는 고양이에요 짧은 꼬리가 매력적인 아이랍니다',default,'종합백신,인플루엔자,심장사상충');
insert into parcel_img values(seq_parcel_img_no.nextval,'manx1.jpg',18,'M');
insert into parcel_img values(seq_parcel_img_no.nextval,'manx2.jpeg',18,'S');
insert into parcel_img values(seq_parcel_img_no.nextval,'manx3.jpg',18,'S');
insert into parcel_img values(seq_parcel_img_no.nextval,'manx4.jpg',18,'S');
insert into parcel_img values(seq_parcel_img_no.nextval,'manx5.jpg',18,'S');
--19
insert into parcel values('너무 예쁜 흰색 먼치킨이에요','C',seq_parcel_post_no.nextval,default,'mire258','4','18-12-23','부산광역시 영도구','M','N',200000,'닥스훈트나 웰시 코기처럼 팔다리가 짧고 허리가 긴 고양이 종이에요 종종걸음이 너무 귀엽겠죠?ㅎㅎ',default,'종합백신,인플루엔자,심장사상충');
insert into parcel_img values(seq_parcel_img_no.nextval,'munchikin4.jpg',19,'M');
insert into parcel_img values(seq_parcel_img_no.nextval,'munchikin1.jpg',19,'S');
insert into parcel_img values(seq_parcel_img_no.nextval,'munchikin2.jpg',19,'S');
insert into parcel_img values(seq_parcel_img_no.nextval,'munchikin3.jpg',19,'S');
insert into parcel_img values(seq_parcel_img_no.nextval,'munchikin5.jpg',19,'S');
--20
insert into parcel values('노르웨이 숲 분양해요','C',seq_parcel_post_no.nextval,default,'tiffany','20','19-01-03','서울특별시 구로구','M','N',200000,'노르웨이의 숲에서 자연발생해서 노르웨이 숲! 줄여서 놀숲이라고도 하죠 장모종이구 털이 그만큼 멋진 아이에요 대형묘로 덩치도 있는 아이구요',default,'종합백신,인플루엔자');
insert into parcel_img values(seq_parcel_img_no.nextval,'Norwaysub2.jpg',20,'M');
insert into parcel_img values(seq_parcel_img_no.nextval,'Norwaysub1.jpg',20,'S');
insert into parcel_img values(seq_parcel_img_no.nextval,'Norwaysub3.jpg',20,'S');
insert into parcel_img values(seq_parcel_img_no.nextval,'Norwaysub4.jpg',20,'S');
insert into parcel_img values(seq_parcel_img_no.nextval,'Norwaysub5.jpg',20,'S');
--21
insert into parcel values('페르시안 구경하고 가세요 ㅎㅎ','C',seq_parcel_post_no.nextval,default,'psh12','18','19-01-03','서울특별시 은평구','F','Y',300000,'조용하고 성격도 순한 편인 페르시안 새끼 데려가세요~',default,'종합백신,인플루엔자');
insert into parcel_img values(seq_parcel_img_no.nextval,'persian1.jpg',21,'M');
insert into parcel_img values(seq_parcel_img_no.nextval,'persian2.jpg',21,'S');
insert into parcel_img values(seq_parcel_img_no.nextval,'persian3.jpg',21,'S');
insert into parcel_img values(seq_parcel_img_no.nextval,'persian4.jpg',21,'S');
insert into parcel_img values(seq_parcel_img_no.nextval,'persian5.jpg',21,'S');
--22
insert into parcel values('푸들 데려가실분 찾아요 ㅎㅎ','D',seq_parcel_post_no.nextval,default,'dnkcn235','35','19-01-03','경기도 성남시','M','N',300000,'푸들 딱보면 너무 귀엽지 않습니까? 제 이름처럼 풍성한 털을 가진 푸들 들여가세요~',default,'종합백신,인플루엔자');
insert into parcel_img values(seq_parcel_img_no.nextval,'poodle1.jpg',22,'M');
insert into parcel_img values(seq_parcel_img_no.nextval,'poodle2.jpg',22,'S');
insert into parcel_img values(seq_parcel_img_no.nextval,'poodle3.jpg',22,'S');
insert into parcel_img values(seq_parcel_img_no.nextval,'poodle4.jpg',22,'S');
insert into parcel_img values(seq_parcel_img_no.nextval,'poodle5.jpg',22,'S');
--23
insert into parcel values('래브라도 리트리버!','D',seq_parcel_post_no.nextval,default,'pso12','6','18-12-03','충청북도 충주시','M','N',300000,'충성심 강한 종 하면 리트리버 아니겠습니까? 래브라도 리트리버 지금 데려가면 엄청 말 잘 듣는 예쁜아이가 될겁니다. 후회하지말고 지금바로 연락주세요!',default,'종합백신,인플루엔자');
insert into parcel_img values(seq_parcel_img_no.nextval,'rabrado1.png',23,'M');
insert into parcel_img values(seq_parcel_img_no.nextval,'rabrado2.png',23,'S');
insert into parcel_img values(seq_parcel_img_no.nextval,'rabrado3.png',23,'S');
insert into parcel_img values(seq_parcel_img_no.nextval,'rabrado4.png',23,'S');
insert into parcel_img values(seq_parcel_img_no.nextval,'rabrado5.png',23,'S');
--24
insert into parcel values('대형견 전문 분양 사모예드','D',seq_parcel_post_no.nextval,default,'nasunghee','19','18-12-03','대구광역시 동구','M','Y',500000,'덩치큰 사모예드 데리고 다니면 어딜가나 시선집중! 멋진 털을가진 녀석입니다 ㅎㅎ',default,'종합백신,인플루엔자');
insert into parcel_img values(seq_parcel_img_no.nextval,'samoyed4.png',24,'M');
insert into parcel_img values(seq_parcel_img_no.nextval,'samoyed1.png',24,'S');
insert into parcel_img values(seq_parcel_img_no.nextval,'samoyed2.png',24,'S');
insert into parcel_img values(seq_parcel_img_no.nextval,'samoyed3.png',24,'S');
insert into parcel_img values(seq_parcel_img_no.nextval,'samoyed5.png',24,'S');
--25
insert into parcel values('스코티쉬 폴드라고 아주 귀여워요 ㅠㅠ','C',seq_parcel_post_no.nextval,default,'cheedm2e','8','19-01-05','경기도 평택시','M','Y',100000,'저희 아이가 이번에 낳은 새끼에요 정말 귀여운데 데려가실분 있음 싸게 드려요~',default,'종합백신,인플루엔자');
insert into parcel_img values(seq_parcel_img_no.nextval,'scotish1.jpg',25,'M');
insert into parcel_img values(seq_parcel_img_no.nextval,'scotish2.jpg',25,'S');
insert into parcel_img values(seq_parcel_img_no.nextval,'scotish3.jpg',25,'S');
insert into parcel_img values(seq_parcel_img_no.nextval,'scotish4.jpg',25,'S');
insert into parcel_img values(seq_parcel_img_no.nextval,'scotish5.jpg',25,'S');
--26
insert into parcel values('샴고양이 분양합니다','C',seq_parcel_post_no.nextval,default,'jonryoungeunsu1','6','19-01-05','경기도 가평시','F','N',100000,'샴은 다 아시죠? 근데 이가격 실화냐?! 얼른 데려가세요!',default,'종합백신,인플루엔자');
insert into parcel_img values(seq_parcel_img_no.nextval,'sham1.jpg',26,'M');
insert into parcel_img values(seq_parcel_img_no.nextval,'sham2.jpg',26,'S');
insert into parcel_img values(seq_parcel_img_no.nextval,'sham3.jpg',26,'S');
insert into parcel_img values(seq_parcel_img_no.nextval,'sham4.jpg',26,'S');
insert into parcel_img values(seq_parcel_img_no.nextval,'sham5.jpg',26,'S');
--27
insert into parcel values('순종 시바견!','D',seq_parcel_post_no.nextval,default,'file44','24','19-01-05','충청북도 청주시','M','N',600000,'순종 시바견이에요 순종 인증 가능하고 혈통 확실합니다. 쿨거래 원해요',default,'종합백신,코로나장염,광견병,인플루엔자,심장사상충');
insert into parcel_img values(seq_parcel_img_no.nextval,'siba1.jpg',27,'M');
insert into parcel_img values(seq_parcel_img_no.nextval,'siba2.jpg',27,'S');
insert into parcel_img values(seq_parcel_img_no.nextval,'siba3.jpg',27,'S');
insert into parcel_img values(seq_parcel_img_no.nextval,'siba4.jpg',27,'S');
insert into parcel_img values(seq_parcel_img_no.nextval,'siba5.jpg',27,'S');
--28
insert into parcel values('시베리안 허스키 분양해요^^','D',seq_parcel_post_no.nextval,default,'nasunghee','25','19-01-10','대구광역시 동구','M','N',300000,'안녕하세요 대형견 전문분양업체입니다 ^^ 이번엔 대형견은 아니지만 그래도 유명한 시베리안 허스키 데려왔어요 좋은 주인분 만났으면 좋겠어요~',default,'종합백신,코로나장염,광견병,인플루엔자,심장사상충');
insert into parcel_img values(seq_parcel_img_no.nextval,'siberian1.jpg',28,'M');
insert into parcel_img values(seq_parcel_img_no.nextval,'siberian2.jpg',28,'S');
insert into parcel_img values(seq_parcel_img_no.nextval,'siberian3.jpg',28,'S');
insert into parcel_img values(seq_parcel_img_no.nextval,'siberian4.jpg',28,'S');
insert into parcel_img values(seq_parcel_img_no.nextval,'siberian5.jpg',28,'S');
--29
insert into parcel values('저희가 대형만 하는줄 알았죠?','D',seq_parcel_post_no.nextval,default,'nasunghee','26','19-01-14','대구광역시 동구','F','Y',200000,'대형견 전문이지 소형견을 안하는건 아니에요 ㅋㅋㅋㅋㅋ 이번엔 소형견! 시추에요 전현무씨 많이 닮았네요 ㅋㅋㅋㅋ',default,'종합백신,코로나장염,광견병,인플루엔자,심장사상충');
insert into parcel_img values(seq_parcel_img_no.nextval,'sithu1.jpg',29,'M');
insert into parcel_img values(seq_parcel_img_no.nextval,'sithu2.jpg',29,'S');
insert into parcel_img values(seq_parcel_img_no.nextval,'sithu3.jpg',29,'S');
insert into parcel_img values(seq_parcel_img_no.nextval,'sithu4.jpg',29,'S');
insert into parcel_img values(seq_parcel_img_no.nextval,'sithu5.jpg',29,'S');
--30
insert into parcel values('이번엔 뭘까~요?','D',seq_parcel_post_no.nextval,default,'nasunghee','23','19-01-08','대구광역시 동구','M','N',200000,'스피츠에요! 말썽꾸러기지만 그게 또 매력이죠 ㅎㅎ',default,'종합백신,코로나장염,광견병,인플루엔자,심장사상충');
insert into parcel_img values(seq_parcel_img_no.nextval,'sp1.jpg',30,'M');
insert into parcel_img values(seq_parcel_img_no.nextval,'sp2.jpg',30,'S');
insert into parcel_img values(seq_parcel_img_no.nextval,'sp3.jpg',30,'S');
insert into parcel_img values(seq_parcel_img_no.nextval,'sp4.jpg',30,'S');
insert into parcel_img values(seq_parcel_img_no.nextval,'sp5.jpg',30,'S');
--31
insert into parcel values('털없는 스핑크스!','C',seq_parcel_post_no.nextval,default,'huk789','9','19-01-01','서울특별시 강동구','M','N',500000,'털이 없는 스핑크스라는 고양이에요 털이없어서 호불호 갈리지만 전 그게 너무 매력이더라구요 ㅎㅎ',default,'종합백신');
insert into parcel_img values(seq_parcel_img_no.nextval,'spinx1.jpg',31,'M');
insert into parcel_img values(seq_parcel_img_no.nextval,'spinx2.jpg',31,'S');
insert into parcel_img values(seq_parcel_img_no.nextval,'spinx3.jpg',31,'S');
insert into parcel_img values(seq_parcel_img_no.nextval,'spinx4.jpg',31,'S');
insert into parcel_img values(seq_parcel_img_no.nextval,'spinx5.jpg',31,'S');
--32
insert into parcel values('제가 키우던 아이에요','D',seq_parcel_post_no.nextval,default,'zw324f3rg','23','18-04-16','경기도 오산시','F','N',200000,'제가 사정이생겨서 더 키우지 못하게 됐어요 예방접종은 최대한 다해놨구 일지도 같이드릴테니 잘 돌봐줄분 찾고싶어요 ㅠㅠ 이름은 흰둥이에요',default,'종합백신,코로나장염,광견병,인플루엔자,심장사상충');
insert into parcel_img values(seq_parcel_img_no.nextval,'spits1.jpg',32,'M');
insert into parcel_img values(seq_parcel_img_no.nextval,'spits2.jpg',32,'S');
insert into parcel_img values(seq_parcel_img_no.nextval,'spits3.jpg',32,'S');
insert into parcel_img values(seq_parcel_img_no.nextval,'spits4.jpg',32,'S');
insert into parcel_img values(seq_parcel_img_no.nextval,'spits5.jpg',32,'S');
--33
insert into parcel values('마! 자신있나!','E',seq_parcel_post_no.nextval,default,'moonsik','21','14-01-16','부산광역시 중구','M','N',100000,'타란튤라라고 아냐? 남자의 펫이지 잘못하면 주인도 죽는다는 그 거미! 자신있음 드루와 봐라',default,'종합백신');
insert into parcel_img values(seq_parcel_img_no.nextval,'taran1.jpg',33,'M');
insert into parcel_img values(seq_parcel_img_no.nextval,'taran2.jpg',33,'S');
insert into parcel_img values(seq_parcel_img_no.nextval,'taran3.jpg',33,'S');
insert into parcel_img values(seq_parcel_img_no.nextval,'taran4.jpg',33,'S');
insert into parcel_img values(seq_parcel_img_no.nextval,'taran5.jpg',33,'S');
--34
insert into parcel values('장수의 상징 거북이 키워보실분?','E',seq_parcel_post_no.nextval,default,'saltjune','17','10-03-15','강원도 동해시','F','N',100000,'장수의 상징 거북이 분양해요!',default,'종합백신');
insert into parcel_img values(seq_parcel_img_no.nextval,'turtle1.png',34,'M');
insert into parcel_img values(seq_parcel_img_no.nextval,'turtle2.png',34,'S');
insert into parcel_img values(seq_parcel_img_no.nextval,'turtle3.png',34,'S');
insert into parcel_img values(seq_parcel_img_no.nextval,'turtle4.jpg',34,'S');
insert into parcel_img values(seq_parcel_img_no.nextval,'turtle5.jpg',34,'S');
--35
insert into parcel values('웰시코기 데려가실분 찾아요 ㅋㅋㅋ','D',seq_parcel_post_no.nextval,default,'do3ob','29','18-12-12','강원도 정선군','F','N',400000,'나래코기만큼 뒤태가 섹시한 웰시코기 분양해요 ㅎㅎ',default,'종합백신,코로나장염,광견병,인플루엔자,심장사상충');
insert into parcel_img values(seq_parcel_img_no.nextval,'welsi1.jpg',35,'M');
insert into parcel_img values(seq_parcel_img_no.nextval,'welsi2.jpg',35,'S');
insert into parcel_img values(seq_parcel_img_no.nextval,'welsi3.jpg',35,'S');
insert into parcel_img values(seq_parcel_img_no.nextval,'welsi4.jpg',35,'S');
insert into parcel_img values(seq_parcel_img_no.nextval,'welsi5.jpg',35,'S');
--36
insert into parcel values('요크셔테리어 분양합니다.','D',seq_parcel_post_no.nextval,default,'jaehoon33','28','19-01-12','충청남도 아산시','M','Y',360000,'예방접종 다 맞힌 요크셔테리어에요 까만 털이 매력적이고 무척 귀여워요 ㅎㅎ',default,'종합백신,코로나장염,광견병,인플루엔자,심장사상충');
insert into parcel_img values(seq_parcel_img_no.nextval,'york1.jpg',36,'M');
insert into parcel_img values(seq_parcel_img_no.nextval,'york2.jpg',36,'S');
insert into parcel_img values(seq_parcel_img_no.nextval,'york3.jpg',36,'S');
insert into parcel_img values(seq_parcel_img_no.nextval,'york4.jpg',36,'S');
insert into parcel_img values(seq_parcel_img_no.nextval,'york5.jpg',36,'S');
--37
insert into parcel values('저희 고양이 데려다 키우실분 구해요','C',seq_parcel_post_no.nextval,default,'khs12','30','16-05-12','경기도 화성시','M','N',100000,'제가 학교주변에서 주운 길냥이에요 사람한테 잘 안기는 개냥이구 교육도 잘 받아서 키우기 쉬우실거에요 제가 사정이 생겨서 더 못키우게 됐는데 책임있게 분양받으실분 찾아요~',default,'종합백신');
insert into parcel_img values(seq_parcel_img_no.nextval,'legi5.jpg',37,'M');
insert into parcel_img values(seq_parcel_img_no.nextval,'legi6.jpg',37,'S');
insert into parcel_img values(seq_parcel_img_no.nextval,'legi2.jpg',37,'S');
insert into parcel_img values(seq_parcel_img_no.nextval,'legi3.jpg',37,'S');
insert into parcel_img values(seq_parcel_img_no.nextval,'legi4.jpg',37,'S');


--닥터 인서트구문
insert into doctor values(seq_doc_no.nextval,'doctor1','1234','doced24','김병승','bmw동물병원','서울특별시 관악구 남부순환로 16','02-3442-1234','doctor1.jpg', '126.9617404', '37.477391');
insert into doctor values(seq_doc_no.nextval,'doctor2','1234','doffed24','유병승','kh동물병원','서울특별시 강남구 봉은사로2길 56','02-9385-7454','doctor2.jpg', '127.0238748', '37.5032047');
insert into doctor values(seq_doc_no.nextval,'doctor3','1234','doffed19','박병승','평화누리동물병원','서울특별시 강북구 노해로1길 18-1','02-9847-3323','doctor3.jpg', '127.020602', '37.635649');
insert into doctor values(seq_doc_no.nextval,'doctor4','1234','docvcf4','박재범','해운대동물병원','부산광역시 금정구 공해4길 7','051-8474-3323','doctor5.jpg', '129.055414', '35.244374');
insert into doctor values(seq_doc_no.nextval,'doctor5','1234','dof5fgf4','주재범','광안리동물병원','부산광역시 부산진구 동평로 128','051-8434-4534','doctor4.jpg', '129.054644', '35.1712774');
insert into doctor values(seq_doc_no.nextval,'doctor6','1234','ddffdccgf4','임재범','서면동물병원','부산광역시 부산진구 동성로 137','051-4564-5434','doctor6.jpg', '129.0621284', '35.1608109');
insert into doctor values(seq_doc_no.nextval,'doctor7','1234','d436ccgf4','김성훈','광주동물병원','광주광역시 동구 제봉로 210','062-4355-5235','doctor8.jpg', '126.9125028', '35.1545712');
insert into doctor values(seq_doc_no.nextval,'doctor8','1234','d436ccgf4','이성훈','동물사랑병원','광주광역시 북구 모룡대길 68','062-4112-7985','doctor7.jpg', '126.8907857', '35.2181578');
insert into doctor values(seq_doc_no.nextval,'doctor9','1234','d436ccgf4','박성훈','사랑동물병원','광주광역시 북구 모룡대길 68','062-4234-8579','doctor9.jpg', '126.8898654', '35.216411');
insert into doctor values(seq_doc_no.nextval,'doctor10','1234','d4cccgf4','박수정','희망동물병원','대전광역시 서구 둔산로 100','042-7887-1451','doctor11.jpg', '127.3820864', '36.3503423');
insert into doctor values(seq_doc_no.nextval,'doctor11','1234','d432dccgf4','김수정','믿음동물병원','대전광역시 유성구 유성대로 1422','042-8421-1797','doctor10.jpg', '127.3712566', '36.405097');
insert into doctor values(seq_doc_no.nextval,'doctor12','1234','d436c3dijf4','정수정','소망동물병원','대전광역시 중구 사정공원로 160','042-1787-9989','doctor12.jpg', '127.4084162', '36.2994418');
insert into doctor values(seq_doc_no.nextval,'doctor13','1234','d3dcdijf4','강병오','대구동물병원','대구광역시 남구 대명로 29','053-1787-9989','doctor14.jpg', '128.5588987', '35.8389539');
insert into doctor values(seq_doc_no.nextval,'doctor14','1234','dasdvckd8','김병오','허준동물병원','대구광역시 달서구 공원순환로 201','053-1487-8521','doctor13.jpg', '128.5565522', '35.8446585');
insert into doctor values(seq_doc_no.nextval,'doctor15','1234','d37dasd3dijf4','천병오','개냥이동물병원','대구광역시 북구 연암로 40','053-7417-8751','doctor15.jpg', '128.5978285', '35.8926195');
insert into doctor values(seq_doc_no.nextval,'doctor16','1234','d4f3dijf4','김종언','냥멍동물병원','울산광역시 남구 봉월로 137','052-8457-8751','doctor17.jpg', '129.303963', '35.542106');
insert into doctor values(seq_doc_no.nextval,'doctor17','1234','d376hh3dijf4','박종언','멍멍동물병원','울산광역시 남구 월평로 87','053-9852-8741','doctor16.jpg', '129.3127574', '35.5432939');
insert into doctor values(seq_doc_no.nextval,'doctor18','1234','d37d235ijf4','한종언','중성화전문병원','울산광역시 중구 종가로 559-30','053-7542-1957','doctor18.jpg', '129.3188408', '35.564299');
insert into doctor values(seq_doc_no.nextval,'doctor19','1234','d3d35ijf4','박충권','동물전문병원','인천광역시 남구 독배로 500','032-8542-9563','doctor20.jpg', '126.6415156', '37.4620335');
insert into doctor values(seq_doc_no.nextval,'doctor20','1234','d9fkijf4','이충권','동물사랑병원','인천광역시 남동구 용천로 208','032-9852-1515','doctor19.jpg', '126.7121856', '37.468181');
insert into doctor values(seq_doc_no.nextval,'doctor21','1234','d6ghjijf4','박충권','군견전문병원','인천광역시 부평구 무네미로 461','032-5412-1857','doctor21.jpg', '126.7484107', '37.4780224');
insert into doctor values(seq_doc_no.nextval,'doctor22','1234','d3d5ijf4','서현중','다고쳐병원','경기도 수원시 영통구19번길 9','031-1985-8852','doctor23.jpg', '126.9911385', '37.2758838');
insert into doctor values(seq_doc_no.nextval,'doctor23','1234','d4d1234jf4','박현중','화타동물병원','경기도 평택시 원평로83번길 17','031-5412-1857','doctor24.jpg', '127.0792257', '36.9917571');
insert into doctor values(seq_doc_no.nextval,'doctor24','1234','d4d22356jf4','김현중','파블로프병원','경기도 오산시 경기대로 341','031-1985-8775','doctor22.jpg', '127.0705441', '37.1552404');
insert into doctor values(seq_doc_no.nextval,'doctor25','1234','d4dxd6jf4','강대찬','대찬동물병원','강원도 강릉시 강릉대로 369-13','033-8512-7952','doctor25.jpg', '128.9035297', '37.7686952');
insert into doctor values(seq_doc_no.nextval,'doctor26','1234','ddf356jf4','참대찬','슈바이처병원','강원도 원주시 소초면 의관리 산1','033-1985-8775','doctor27.jpg', '127.9603384', '37.4315256');
insert into doctor values(seq_doc_no.nextval,'doctor27','1234','d4dz12sd6jf4','함대찬','죽은자의소생병원','강원도 춘천시 춘천3로','033-1994-1452','doctor26.jpg', '127.7261741', '37.873726');
insert into doctor values(seq_doc_no.nextval,'doctor28','1234','d4dz1ff4','차현재','코딩동물병원','충청남도 공주시 연수원길 73-26','041-8452-9852','doctor29.jpg', '127.1432624', '36.4883015');
insert into doctor values(seq_doc_no.nextval,'doctor29','1234','d4d3dd6jf4','김현재','느낌동물병원','충청남도 천안시 서북구 광장로 239','041-7541-8852','doctor28.jpg', '127.1075588', '36.7997902');
insert into doctor values(seq_doc_no.nextval,'doctor30','1234','d4dz11jf4','박현재','천생동물병원','충청남도 홍성군 홍성읍 충절로 723','041-1994-1452','doctor30.jpg', '126.6938644', '36.5843162');
insert into doctor values(seq_doc_no.nextval,'doctor31','1234','d43d1jf4','박총명','무한동물병원','충청북도 제천시 제천북로 141','043-1854-9524','doctor31.jpg','128.189396','37.159627');
insert into doctor values(seq_doc_no.nextval,'doctor32','1234','d4dz11jf4','참총명','개잘하는동물병원','충청북도 충주시 봉현로 170','043-2787-1945','doctor33.jpg','127.923402','36.977026');
insert into doctor values(seq_doc_no.nextval,'doctor33','1234','d4dz11jf4','김총명','멍멍병원','충청북도 단양군 단양읍 중앙1로','043-6662-2869','doctor32.jpg','128.367040','36.986185');
insert into doctor values(seq_doc_no.nextval,'doctor34','1234','dodfced24','이예원','경남동물병원','경상남도 사천시 문선1길 4','055-3442-1234','doctor34.jpg','128.0780831','34.9384442');
insert into doctor values(seq_doc_no.nextval,'doctor35','1234','dofdfcfed24','신예원','진주동물병원','경상남도 진주시 초전북로 104','055-9385-7454','doctor36.jpg','128.1167038','35.2086785');
insert into doctor values(seq_doc_no.nextval,'doctor36','1234','dofcvcfed19','김예원','평화누리동물병원','경상남도 창원시 반송로 149','055-9847-3323','doctor35.jpg','128.6690645','35.2364298');
insert into doctor values(seq_doc_no.nextval,'doctor37','1234','dodfced24','박대균','대균동물병원','경상북도 경주시 보문로 446','055-3442-1234','doctor37.jpg','129.2847931','35.8436506');
insert into doctor values(seq_doc_no.nextval,'doctor38','1234','dofdfcfed24','김대균','다고쳐병원','경상북도 경산시 원효로 60','055-9385-7454','doctor39.jpg','128.7419222','35.8226673');
insert into doctor values(seq_doc_no.nextval,'doctor39','1234','doffed19','정대균','아름다운동물병원','경상북도 구미시 인동31길 14','055-9847-3323','doctor38.jpg','128.4290375','36.0976193');
insert into doctor values(seq_doc_no.nextval,'doctor40','1234','dsdf32ed24','최정빈','정빈동물병원','전라남도 나주시 북망문길 2','061-3442-1234','doctor41.jpg','126.7151597','35.0363476');
insert into doctor values(seq_doc_no.nextval,'doctor41','1234','dofdd5fcd24','김정빈','애완동물병원','전라남도 나주시 산포면 다도로 7','061-9385-7454','doctor40.jpg','126.823687','35.0065384');
insert into doctor values(seq_doc_no.nextval,'doctor42','1234','dofefe239','박정빈','정성동물병원','전라남도 화순군 화순읍 대교로 7','061-9847-3323','doctor42.jpg','126.9780978','35.0634299');
insert into doctor values(seq_doc_no.nextval,'doctor43','1234','d332ed24','이정하','정하동물병원','전라북도 정읍시 충정로 93','063-8542-9852','doctor43.jpg','126.8647474','35.5613412');
insert into doctor values(seq_doc_no.nextval,'doctor44','1234','dofdd5fcd24','송정하','동물케어병원','전라북도 전주시 완산구 내원당길 29','061-8412-7454','doctor44.jpg','127.171741','35.798169');
insert into doctor values(seq_doc_no.nextval,'doctor45','1234','do12e239','박정하','마미손동물병원','전라북도 김제시 백산면 씨앗길 118','061-8452-3323','doctor45.jpg','126.892844','35.839257');
insert into doctor values(seq_doc_no.nextval,'doctor46','1234','d332ed24','한유나','유나동물병원','제주특별자치도 제주시 선사로 12','063-8542-9852','doctor46.jpg','126.5850828','33.5226085');
insert into doctor values(seq_doc_no.nextval,'doctor47','1234','dofdd5fcd24','박유나','유니병원','제주특별자치도 제주시 일주서로 78','063-8122-7454','doctor48.jpg','126.4874186,','33.4526369');
insert into doctor values(seq_doc_no.nextval,'doctor48','1234','do12e239','김유나','유앤미동물병원','제주특별자치도 김제시 백산면 씨앗길 118','063-8422-3323','doctor47.jpg','126.4981816','33.4876526');

--find,protect 데이터
INSERT INTO FIND VALUES(SEQ_FIND_POST_NO.NEXTVAL, 'jaeseok77', 50, '중이 찾아주세요ㅠㅠ', '잠깐 문 열린사이에 저희 중이가 집을 나갔어요ㅠㅠ 바로 어제 찍은 사진입니다',
'서울특별시 구로구 구로동 1281', '19/01/28', 'Y', 150, 'pet_01.jpg', DEFAULT, DEFAULT);
INSERT INTO FIND VALUES(SEQ_FIND_POST_NO.NEXTVAL, 'haha12', 51, '소를 찾습니다', '갔을만한 곳은 다 찾아봤는데 없어요ㅜㅜ 중앙교회 근처 놀이터에서 잃어버렸어요... 오른쪽 발등에 점이 있어요',
'서울특별시 강남구 도곡동 957-14', '19/01/28', 'C', 0, 'pet_02.jpg', DEFAULT, DEFAULT);
INSERT INTO FIND VALUES(SEQ_FIND_POST_NO.NEXTVAL, 'rose', 63, '금정구 서동에서 시추 보신분 있나요?', '저희 리본이 좀 찾아주세요ㅠㅠ 노견이라 너무 걱정됩니다.. 사례 꼭 해드리겠습니다',
'부산광역시 금정구 서동 302-1811', '19/01/28', 'Y', 200, 'pet_03.jpg', DEFAULT, DEFAULT);
INSERT INTO FIND VALUES(SEQ_FIND_POST_NO.NEXTVAL, 'lsj12', 2, '스피츠 암컷 찾아주세요...', '날도 추운데 적분이가 없어졌어요ㅠ.ㅠ 꼬리에 분홍 염색을 했어요',
'대구광역시 중구 서성로1가 109-1', '19/01/28', 'N', 0, 'pet_04.jpg', DEFAULT, DEFAULT);
INSERT INTO FIND VALUES(SEQ_FIND_POST_NO.NEXTVAL, 'mire258', 44, '기니피그 보신분?ㅠ', '미래 좀 찾아주세요ㅠㅠㅠ다른 애들보다 많이 통통해요..',
'경기도 가평군 가평읍 이화리 140-8', '19/01/28', 'C', 0, 'pet_05.jpg', DEFAULT, DEFAULT);
INSERT INTO FIND VALUES(SEQ_FIND_POST_NO.NEXTVAL, 'kimsky12', 22, '페르시안 찾습니다', '15일 저녁 6시경 석촌중학교 근처 음식점에서 올림픽공원쪽으로 뛰어갔다고 합니다.',
'서울특별시 송파구 석촌동 223-9', '19/01/28', 'Y', 120, 'pet_06.jpg', DEFAULT, DEFAULT);
INSERT INTO FIND VALUES(SEQ_FIND_POST_NO.NEXTVAL, 'sunny', 68, '길잃은 비숑보신분..?', '미용한 상태이고, 왼쪽 뒷다리에 상처가 있습니다ㅠㅠ 쭈꾸미좀 찾아주세요 꼭꼭이요ㅠ..',
'제주특별자치도 제주시 이도2동 314-4', '19/01/28', 'Y', 200, 'pet_07.jpg', DEFAULT, DEFAULT);
INSERT INTO FIND VALUES(SEQ_FIND_POST_NO.NEXTVAL, 'sechan55', 57, '말티즈 찾습니다', '곰돌이 모양 모자가 달려있는 옷을 입고있어요',
'대전광역시 동구 구도동 51', '19/01/28', 'C', 0, 'pet_08.jpg', DEFAULT, DEFAULT);
INSERT INTO FIND VALUES(SEQ_FIND_POST_NO.NEXTVAL, 'thodknc23', 148, '포메 찾습니다', '빨간색 패딩을 입고있어요 겁이 많아서 아무나 안따라가요.. 꼭 사례해드리겠습니다',
'충청남도 계룡시 금암동 72-6', '19/01/28', 'Y', 200, 'pet_09.jpg', DEFAULT, DEFAULT);
INSERT INTO FIND VALUES(SEQ_FIND_POST_NO.NEXTVAL, 'kof114fj', 149, '풍산개를 찾습니다', '이제 7개월된 아기입니다ㅠㅠ 사람을 너무 좋아해요 보신분 꼭 연락주세요',
'충청남도 계룡시 금암동 72-6', '19/01/28', 'N', 0, 'pet_10.jpg', DEFAULT, DEFAULT);
INSERT INTO FIND VALUES(SEQ_FIND_POST_NO.NEXTVAL, 'gerrard1', 145, '암컷 비글 찾습니다', '잠깐 문열린사이에 애가 뛰쳐나갔어요. 암컷 비글이고 5년된 가족입니다ㅠㅠ꼭연락주세요',
'충청북도 괴산군 문광면 광덕리 281-2', '19/01/28', 'Y', 100, 'pet_11.jpg', DEFAULT, DEFAULT);
INSERT INTO FIND VALUES(SEQ_FIND_POST_NO.NEXTVAL, 'amoomoo', 140, '고양이 찾습니다', '라이코이 수컷입니다. 이제 1년된 애기에요ㅠㅠ 노란색니트 재질 옷을 입고있습니다',
'대구광역시 남구 대명동 3019-30', '19/01/28', 'N', 0, 'pet_12.jpg', DEFAULT, DEFAULT);
INSERT INTO FIND VALUES(SEQ_FIND_POST_NO.NEXTVAL, 'jihyun11', 137, '스핑크스 찾아요', '스핑크스 찾습니다. 사례는 꼭 해드리겠습니다.',
'광주광역시 남구 양림동 505', '19/01/28', 'Y', 350, 'pet_13.jpg', DEFAULT, DEFAULT);
INSERT INTO FIND VALUES(SEQ_FIND_POST_NO.NEXTVAL, 'lisa', 65, '뭉이를 찾습니다', '시베리안 허스키이고 입마개를 하고있어요 삼덕공원쪽에서 잃어버렸습니다..',
'부산광역시 강서구 생곡동 1598-1', '19/01/28', 'C', 0, 'pet_14.jpg', DEFAULT, DEFAULT);
INSERT INTO FIND VALUES(SEQ_FIND_POST_NO.NEXTVAL, 'bangsee', 47, '앵무새 찾습니다', '사랑앵무 종이고 정말 똑똑해요ㅠㅠ 가족같은 아이입니다.',
'서울특별시 강동구 천호동 460-4', '19/01/28', 'C', 0, 'pet_15.jpg', DEFAULT, DEFAULT);
INSERT INTO FIND VALUES(SEQ_FIND_POST_NO.NEXTVAL, 'kocute12', 6, '코숏 찾아주세요ㅠㅠ', '창문 열고 애가 탈출했어요ㅜㅜ 겁이 많은 아이라 너무걱정되요.. 찾아만주신다면 사례 꼭 할게요',
'강원도 강릉시 교동 1817-1', '19/01/28', 'Y', 100, 'pet_16.jpg', DEFAULT, DEFAULT);
INSERT INTO FIND VALUES(SEQ_FIND_POST_NO.NEXTVAL, 'kar12', 8, '스코티시폴드 찾습니다', '어제밤에 애가 집을 나갔어요. 꼬리쪽에 털상태가 좋지 못해요..',
'강원도 강릉시 강동면 모전리 373-2', '19/01/28', 'N', 0, 'pet_17.jpg', DEFAULT, DEFAULT);
INSERT INTO FIND VALUES(SEQ_FIND_POST_NO.NEXTVAL, 'backjoung', 33, '고슴도치 찾아요', '저희 고슴도치 좀 찾아주세요ㅠㅠ 근처에서 멀리 못갔을텐데 다 찾아도 없어요 보신분 꼭 연락주세요',
'전라북도 고창군 상하면 용대리 1009', '19/01/28', 'Y', 50, 'pet_18.jpg', DEFAULT, DEFAULT);
INSERT INTO FIND VALUES(SEQ_FIND_POST_NO.NEXTVAL, 'sunny', 69, '빠삐용 오찡이를 찾습니다', '빨간 줄무늬 올인원 입고 분홍색 하네스 착용하고있습니다ㅠㅠ 산책중에 줄을 놓쳤는데 멀리 달아나버렸어요',
'경기도 가평군 가평읍 이화리 133', '19/01/28', 'C', 0, 'pet_19.jpg', DEFAULT, DEFAULT);
INSERT INTO FIND VALUES(SEQ_FIND_POST_NO.NEXTVAL, 'parksung', 94, '캥거루쥐 둥이를 찾아주세요', '캥거루쥐 둥이 찾습니다ㅠㅠ 너무 작아서 발에 치이지 않을까 걱정이에요',
'인천광역시 강화군 양도면 조산리 754', '19/01/28', 'C', 0, 'pet_20.jpg', DEFAULT, DEFAULT);

INSERT INTO PROTECT VALUES(SEQ_PROTECT_POST_NO.NEXTVAL, 'cheedm2e', '라쿤 보호중이에요', 'E', '9', 'N', '라쿤 보호중입니다. 저희집 앞 계단에 있었던거보니 집이 이 근처같아요.', '서울특별시 관악구 남현동 산 100-14', '19/01/28', 'pet_21.jpg', NULL, DEFAULT, DEFAULT, DEFAULT, DEFAULT);
INSERT INTO PROTECT VALUES(SEQ_PROTECT_POST_NO.NEXTVAL, 'lcrctoeic', '우파루파 잃어버리신분?', 'E', '36', 'F', '암컷 우파루파 보호중이에요ㅠㅠ 주인분 연락주세요', '광주광역시 광산구 왕동 180-3', '19/01/28', 'pet_22.jpg', NULL, DEFAULT, DEFAULT, DEFAULT, DEFAULT);
INSERT INTO PROTECT VALUES(SEQ_PROTECT_POST_NO.NEXTVAL, 'woowool335', '삼평동 토끼 찾아가세요', 'E', '12', 'M', '삼평동 시민공원에서 발견했어요. 수컷 토끼 같습니다.', '경기도 성남시 분당구 삼평동 421', '19/01/28', 'pet_23.jpg', NULL, DEFAULT, DEFAULT, DEFAULT, DEFAULT);
INSERT INTO PROTECT VALUES(SEQ_PROTECT_POST_NO.NEXTVAL, 'thodknc23', '미니피그 잃어버리신분 있나요?', 'E', '7', 'N', '집앞에서 미니피그를 발견했습니다. 성별은 모르겠고 목걸이가 있는데 전화번호가 없어요ㅠㅠ', '대구광역시 남구 대명동 1488-16', '19/01/28', 'pet_24.jpg', NULL, DEFAULT, DEFAULT, DEFAULT, DEFAULT);
INSERT INTO PROTECT VALUES(SEQ_PROTECT_POST_NO.NEXTVAL, 'chanhoho1', '햄스터를 주웠습니다', 'E', '2', 'F', '햄스터 발견했어요! 주인분 연락주세요!', '대전광역시 대덕구 석봉동 483', '19/01/28', 'pet_25.jpg', NULL, DEFAULT, DEFAULT, DEFAULT, DEFAULT);
INSERT INTO PROTECT VALUES(SEQ_PROTECT_POST_NO.NEXTVAL, 'zw324f3rg', '코숏 보호중입니다.', 'C', '30', 'N', '코숏 보호중입니다. 오래동안 보호하기 힘든 상황이라 최대한 빠르게 연락주세요!', '울산광역시 남구 달동 1297-5', '19/01/28', 'pet_26.jpg', NULL, DEFAULT, DEFAULT, DEFAULT, DEFAULT);
INSERT INTO PROTECT VALUES(SEQ_PROTECT_POST_NO.NEXTVAL, 'amoomoo', '엑죠틱 숏헤어 잃어버리신분?', 'C', '28', 'F', '엑죠틱 숏헤어 같아요! 주인분 연락주세요!', '울산광역시 중구 학성동 189-13', '19/01/28', 'pet_27.jpg', NULL, DEFAULT, DEFAULT, DEFAULT, DEFAULT);
INSERT INTO PROTECT VALUES(SEQ_PROTECT_POST_NO.NEXTVAL, 'g45dfdd1', '노르웨이숲 수컷 보호중!', 'C', '20', 'M', '노르웨이 숲 발견해서 집에 데려왔는데 수컷같아요ㅠㅠ목걸이도 없어요', '인천광역시 동구 금곡동 13-6', '19/01/28', 'pet_28.jpg', NULL, DEFAULT, DEFAULT, DEFAULT, DEFAULT);
INSERT INTO PROTECT VALUES(SEQ_PROTECT_POST_NO.NEXTVAL, 'DRAGON99', '렉돌 보호중입니다!', 'C', '19', 'N', '렉돌 보호중이에요. 아직 어린거같은데 집앞에서 떨고있어서 데리고왔어요~', '경기도 부천시 고강동 239-3', '19/01/28', 'pet_29.jpg', NULL, DEFAULT, DEFAULT, DEFAULT, DEFAULT);
INSERT INTO PROTECT VALUES(SEQ_PROTECT_POST_NO.NEXTVAL, 'jaehoon33', '샴 고양이 새끼 보호중입니다.', 'C', '6', 'M', '샴고양이 새끼 잃어버리신분 연락주세요.', '경기도 수원시 장안구 송죽동 500-18', '19/01/28', 'pet_30.jpg', NULL, DEFAULT, DEFAULT, DEFAULT, DEFAULT);
INSERT INTO PROTECT VALUES(SEQ_PROTECT_POST_NO.NEXTVAL, 'junha00', '권선구 믹스견 보호중!', 'D', '38', 'N', '믹스견이고 성별은 모르겠는데 사람을 아주 잘따라요!', '경기도 수원시 권선구 대황교동 59-4', '19/01/28', 'pet_31.jpg', NULL, DEFAULT, DEFAULT, DEFAULT, DEFAULT);
INSERT INTO PROTECT VALUES(SEQ_PROTECT_POST_NO.NEXTVAL, 'haha12', '검정패딩 입은 핏불 보호중!', 'D', '37', 'F', '핏불 거리에서 방황하는거 데리고왔어요.. 입마개는 차고있는데 집에 소형견이 있어서 최대한 빨리 데려가주세요ㅠㅠ', '경상북도 포항시 남구 장흥동 921', '19/01/28', 'pet_32.jpg', NULL, DEFAULT, DEFAULT, DEFAULT, DEFAULT);
INSERT INTO PROTECT VALUES(SEQ_PROTECT_POST_NO.NEXTVAL, 'nasunghee', '호계동 뱅뱅사거리 근처에서 푸들 발견했습니다', 'D', '35', 'M', '뱅뱅사거리 앞 신호등에서 푸들을 발견했어요. 주인분 연락주세요!', '경기도 안양시 동안구 호계동 843-3', '19/01/28', 'pet_33.jpg', NULL, DEFAULT, DEFAULT, DEFAULT, DEFAULT);
INSERT INTO PROTECT VALUES(SEQ_PROTECT_POST_NO.NEXTVAL, 'dounghe', '요키 찾아가세요~', 'D', '28', 'F', '요크셔테리어 암컷 보호중입니다. 하네스 착용중인거 보니 산책중에 잃어버리신것 같아요~ 주인분 연락주세요!', '전라남도 곡성군 목사동면 범계리 118-1', '19/01/28', 'pet_34.jpg', NULL, DEFAULT, DEFAULT, DEFAULT, DEFAULT);
INSERT INTO PROTECT VALUES(SEQ_PROTECT_POST_NO.NEXTVAL, 'file44', '개포동 치와와 주인찾습니다.', 'D', '32', 'M', '사람을 잘따르고 애교가 많아요ㅠㅠ상태가 깨끗한거보니 집에서 나온지 별로 안된거같아요.', '서울특별시 강남구 개포동 1238', '19/01/28', 'pet_35.jpg', NULL, DEFAULT, DEFAULT, DEFAULT, DEFAULT);
INSERT INTO PROTECT VALUES(SEQ_PROTECT_POST_NO.NEXTVAL, 'kocute12', '금암동 포메 보호중이에요~', 'D', '15', 'N', '목걸이에 인식번호가 있네요. 주인분 연락주세요!', '충청남도 계룡시 금암동 72-6', '19/01/28', 'pet_36.jpg', '439310913290240', DEFAULT, DEFAULT, DEFAULT, DEFAULT);
INSERT INTO PROTECT VALUES(SEQ_PROTECT_POST_NO.NEXTVAL, 'moonsik', '빠삐용 주인분 찾아가세요!!!', 'D', '18', 'M', '수컷같고 빨간줄무늬 옷 입었어요~ 최대한 빨리 연락주세요ㅠㅠ', '경기도 가평군 가평읍 읍내리 326', '19/01/28', 'pet_37.jpg', '4450952352603202', DEFAULT, DEFAULT, DEFAULT, DEFAULT);



--free1 --공지
insert into free values('Petwork 공지사항', SEQ_FREE_POST_NO.nextval, default,'admin1', '안녕하세요 Petwork 애견커뮤니티 입니다 국내 최대 전국 종합 네트워크 시스템의 유기견보호센터 사이트가 오픈되었습니다.', 'D', default);
insert into free_img values(seq_free_img_no.nextval, 'freenotice.JPG', 1);
--sharing1 --공지
insert into sharing values('Petwork 공지사항', SEQ_sharing_POST_NO.nextval, sysdate,'admin1', 'Petwork',default,'안녕하세요 Petwork 애견커뮤니티 입니다 국내 최대 전국 종합 네트워크 시스템의 유기견보호센터 사이트가 오픈되었습니다.', 'P1','D', default);
insert into sharing_img values(seq_sharing_img_no.nextval, 'sharingnotice.JPG', 1);

--free 2
insert into free values('뽀뽀', SEQ_FREE_POST_NO.nextval,default, 'jaeseok77', '신상 립스틱 산 기념으로 최가한테 뽀뽀 했어요~ 색깔 이쁘나용? ㅋㅋㅋㅋㅋㅋ 싫다고~ 이빨을~~ ㅠㅠ', 'D',default);
insert into free_img values(seq_free_img_no.nextval, 'free1-1.jpg',2);
insert into free_img values(seq_free_img_no.nextval, 'free1-2.jpg',2);

--free3
insert into free values('가끔씩?!', SEQ_FREE_POST_NO.nextval,default, 'jennie', '전에 이 공간을 화단처럼(?) 쓴거 같은데 어느순간 탄이에게는 뒷간처럼 되어버린.. 땅파기도 하구요ㅎㅎㅎ', 'D',default);
insert into free_img values(seq_free_img_no.nextval, 'free2-1.jpg',3);
insert into free_img values(seq_free_img_no.nextval, 'free2-2.jpg',3);
--free4
insert into free values('피오나공주 같은 우리단비', SEQ_FREE_POST_NO.nextval,default, 'junha00', '얼굴로 산책햇어요ㅋㅋ', 'D',default);
insert into free_img values(seq_free_img_no.nextval, 'free3-1.jpg',4);
insert into free_img values(seq_free_img_no.nextval, 'free3-2.jpg',4);
--free5
insert into free values('최가랑~ 보자기~ 놀이하기~', SEQ_FREE_POST_NO.nextval,default, 'hong', '언니가 어디서 이상한 보자기를 들고와선.... 못살게 해요~ ㅋㅋㅋㅋㅋ', 'D',default);
insert into free_img values(seq_free_img_no.nextval, 'free4-1.jpg',5);
insert into free_img values(seq_free_img_no.nextval, 'free4-2.jpg',5);

--free6
insert into free values('나 쌕쉬해~~~~?', SEQ_FREE_POST_NO.nextval,default, 'sechan55', '남친구해여~~~♡', 'D',default);
insert into free_img values(seq_free_img_no.nextval, 'free5-1.jpg',6);

--free7
insert into free values('먹을때의 자세', SEQ_FREE_POST_NO.nextval,default, 'yuri', '요즘 살도 찌고 꼬리는 흔들면서 입에서 껌은 놓지를 않네요!', 'D',default);
insert into free_img values(seq_free_img_no.nextval, 'free6-1.jpg',7);
insert into free_img values(seq_free_img_no.nextval, 'free6-2.jpg',7);

--free8
insert into free values('개정색', SEQ_FREE_POST_NO.nextval,default, 'jessica', '주인 지금 나랑 장난하냐? ㅎㅎㅎ 표정이 진짜 ㅎㅎㅎ', 'D',default);
insert into free_img values(seq_free_img_no.nextval, 'free7-1.jpg',8);
insert into free_img values(seq_free_img_no.nextval, 'free7-2.jpg',8);

--free9
insert into free values('하놔......', SEQ_FREE_POST_NO.nextval,default, 'yoona', '내 얼굴 자꾸 이렇게 만들기 있기? 없기!!!', 'D',default);
insert into free_img values(seq_free_img_no.nextval, 'free8-1.jpg',9);

--free10
insert into free values('역시...', SEQ_FREE_POST_NO.nextval,default, 'sunny', '유난히 발을 좋아하는...꼬물양', 'D',default);
insert into free_img values(seq_free_img_no.nextval, 'free9-1.jpg',10);

--free11
insert into free values('출생의 비밀....', SEQ_FREE_POST_NO.nextval,default, 'lsj12', '엄마 미안... 나 실은 오징어였어...', 'D',default);
insert into free_img values(seq_free_img_no.nextval, 'free10-1.jpg',11);

--free12
insert into free values('세수하고 가실께요~', SEQ_FREE_POST_NO.nextval,default, 'lsj91', '응? 설마.... 목욕??!!!', 'D',default);
insert into free_img values(seq_free_img_no.nextval, 'free11-1.jpg',12);

--free13
insert into free values('개토끼♡', SEQ_FREE_POST_NO.nextval,default, 'kdw12', '토끼인가?? Dog인가??ㅋㅋ 우리개딸♡♡♡', 'D',default);
insert into free_img values(seq_free_img_no.nextval, 'free12-1.jpg',13);

--free14
insert into free values('어디선가 쳐다보는데....', SEQ_FREE_POST_NO.nextval,default, 'kimsky12', '어디선가 시선이 느껴져서 봤더니...거울로 쳐다보길래 이름 불렀는데 웃어줌...졸귀... 넘 귀여운거 아니냐!!!  >_<', 'D',default);
insert into free_img values(seq_free_img_no.nextval, 'free13-1.jpg',14);

--free15
insert into free values('망연자실...', SEQ_FREE_POST_NO.nextval,default, 'kocute12', '본능대로 움직였지만..이걸 어쩌지... ㅎㅎㅎ', 'D',default);
insert into free_img values(seq_free_img_no.nextval, 'free14-1.jpg',15);
insert into free_img values(seq_free_img_no.nextval, 'free14-2.jpg',15);

--free16
insert into free values('언제가유?', SEQ_FREE_POST_NO.nextval,default, 'kmm12', '주인은 동네사람들과 얘기하시느라 안가네요. "대체 언제가나욤"', 'D',default);
insert into free_img values(seq_free_img_no.nextval, 'free15-1.jpg',16);

--free17 --C
insert into free values('야옹 소리를 내는것이', SEQ_FREE_POST_NO.nextval,default, 'kar12', '어디에선가 보니 고양이들이 인간에게만 야옹 소리를 낸다는 글이 있더라구요~ 이글을 보는데 왜 저에 기분이 좋은걸까요?!ㅋㅋㅋ 가끔 야옹 소리를 내는 고양이들이 있었는데 그냥 경계심이 덜하고 밥달라는 신호로만 생각을 했었거든요~ 그리고 대화 하는거 같아서 좋기도 했었지만 이글을 보니 고양이들이 더 귀엽게 느껴진다고 해야될지.. 제가 미쳤나 봅니다ㅋㅋㅋ 저에겐 탄이만 으로도 벅찬데 말이지용', 'C',default);
insert into free_img values(seq_free_img_no.nextval, 'free16-1.jpg',17);

--free18 --C
insert into free values('고양이에게 베개를 줘봤어요', SEQ_FREE_POST_NO.nextval,default, 'ysh12', '어디에선가 보니 고양이들이 인간에게만 야옹 소리를 낸다는 글이 있더라구요~ 이글을 보는데 왜 저에 기분이 좋은걸까요?!ㅋㅋㅋ 가끔 야옹 소리를 내는 고양이들이 있었는데 그냥 경계심이 덜하고 밥달라는 신호로만 생각을 했었거든요~ 그리고 대화 하는거 같아서 좋기도 했었지만 이글을 보니 고양이들이 더 귀엽게 느껴진다고 해야될지.. 제가 미쳤나 봅니다ㅋㅋㅋ 저에겐 탄이만 으로도 벅찬데 말이지용', 'C',default);
insert into free_img values(seq_free_img_no.nextval, 'free17-1.jpg',18);
insert into free_img values(seq_free_img_no.nextval, 'free17-2.jpg',18);

--free19 --C
insert into free values('이게 무슨 그림일까요', SEQ_FREE_POST_NO.nextval,default, 'ysh12', '과연 이게 뭘 그린걸까요?', 'C',default);
insert into free_img values(seq_free_img_no.nextval, 'free18-1.jpg',19);
insert into free_img values(seq_free_img_no.nextval, 'free18-2.jpg',19);

--free20 --C
insert into free values('새언니에 냥이들', SEQ_FREE_POST_NO.nextval,default, 'khs12', '과연 이게 뭘 그린걸까요?', 'C',default);
insert into free_img values(seq_free_img_no.nextval, 'free19-1.jpg',20);
insert into free_img values(seq_free_img_no.nextval, 'free19-2.jpg',20);
insert into free_img values(seq_free_img_no.nextval, 'free19-3.jpg',20);

--free21 --C
insert into free values('봉구누나 봉숙이', SEQ_FREE_POST_NO.nextval,default, 'ajh12', '부럽다.. 난 출근해야는데 ㅜ', 'C',default);
insert into free_img values(seq_free_img_no.nextval, 'free20-1.jpg',21);
insert into free_img values(seq_free_img_no.nextval, 'free20-1.jpg',21);

--free22 --C
insert into free values('꽃길만 걷자??', SEQ_FREE_POST_NO.nextval,default, 'lyb12', '고양이에게는 츄르길만 걷자~ ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ', 'C',default);
insert into free_img values(seq_free_img_no.nextval, 'free21-1.jpg',22);
insert into free_img values(seq_free_img_no.nextval, 'free21-2.jpg',22);

--free23 --C
insert into free values('미친 고양이 라떼아트 실력', SEQ_FREE_POST_NO.nextval,default, 'lhl12', '인스타로 알게된 라떼아트 장인에게 냥사진 보내 응모했더니 선정이되서 라떼아트를 그려주셨습니다 합성이 아닐까라 생각될정도의 실력에 ㅎㄷㄷ 하네요', 'C',default);
insert into free_img values(seq_free_img_no.nextval, 'free22-1.png',23);
insert into free_img values(seq_free_img_no.nextval, 'free22-2.jpg',23);

--free24 --C
insert into free values('여행짐 싸는 법 아세요?', SEQ_FREE_POST_NO.nextval,default, 'pmy12', '가방->고양이->무한루프....ㅠㅠ', 'C',default);
insert into free_img values(seq_free_img_no.nextval, 'free23-1.jpg',24);

--free25 --C
insert into free values('잡혀라 이좌식아~~', SEQ_FREE_POST_NO.nextval,default, 'psh12', '내가 널 꼭~~ 표정이 열일하는~ ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ', 'C',default);
insert into free_img values(seq_free_img_no.nextval, 'free24-1.jpg',25);
insert into free_img values(seq_free_img_no.nextval, 'free24-2.jpg',25);
insert into free_img values(seq_free_img_no.nextval, 'free24-3.jpg',25);

--free26 --C
insert into free values('썬탠중', SEQ_FREE_POST_NO.nextval,default, 'psh12', '역시 검은 차 위에가 따뜻하다냥냥냥~~♥', 'C',default);
insert into free_img values(seq_free_img_no.nextval, 'free25-1.jpg',26);
insert into free_img values(seq_free_img_no.nextval, 'free25-2.jpg',26);

--free27 --C
insert into free values('전기장판 꺼낸 첫날', SEQ_FREE_POST_NO.nextval,default, 'pby12', '이게 사람이여 고양이여... 그나저나 불좀꺼주지 집사야?? ㅎ', 'C',default);
insert into free_img values(seq_free_img_no.nextval, 'free26-1.jpg',27);
insert into free_img values(seq_free_img_no.nextval, 'free26-2.jpg',27);

--free28 --C
insert into free values('퇴근하자마자 일진들이 둘러싸고', SEQ_FREE_POST_NO.nextval,default, 'backjoung', '여어 츄르셔틀, 돈 좀 벌어왔냥? 주머니에 있는거 다 꺼내봐라냥', 'C',default);
insert into free_img values(seq_free_img_no.nextval, 'free27-1.jpg',28);

--free29 --C
insert into free values('월요병 야옹이~~', SEQ_FREE_POST_NO.nextval,default, 'pt598s2', '암것도 하기 싫구냥냥냥냥~~ 무기력 하다냥냥냥냥냥~', 'C',default);
insert into free_img values(seq_free_img_no.nextval, 'free28-1.jpg',29);

--free30 --D
insert into free values('귀요미 군밤장수....?', SEQ_FREE_POST_NO.nextval,default, 'pt5280', '자는녀석 깨워서 모자씌웠더니 응? 몬데!! 하는 표정으로 바라보다 한숨한번~~ 사람보다 리얼한 한숨소리에 왠지 내가 철부지가 된듯한 느낌적인 느낌....', 'D',default);
insert into free_img values(seq_free_img_no.nextval, 'free29-1.jpg',30);
insert into free_img values(seq_free_img_no.nextval, 'free29-2.jpg',30);

--free31 --D
insert into free values('몸져 누운 댕댕이', SEQ_FREE_POST_NO.nextval,default, 'chanwoong', '고민이 많은지 몸져 누웠습니다. ^^(물론 설정샷입니다)', 'D',default);
insert into free_img values(seq_free_img_no.nextval, 'free30-1.jpg',31);

--free32 --C
insert into free values('냥편치 맛좀 볼래?', SEQ_FREE_POST_NO.nextval,default, 'kang12', '어금니 꽉 깨물어 냥펀치 들어간다!!', 'C',default);
insert into free_img values(seq_free_img_no.nextval, 'free31-1.jpg',32);

--free33 --E
insert into free values('패럿은 사랑이에요', SEQ_FREE_POST_NO.nextval,default, 'huk789', '키우는 보람이있음', 'E',default);
insert into free_img values(seq_free_img_no.nextval, 'free32-1.jpg',33);
insert into free_img values(seq_free_img_no.nextval, 'free32-2.jpg',33);

--free34 --E
insert into free values('애완동물 귀엽다', SEQ_FREE_POST_NO.nextval,default, 'youna1', '귀요미~~~~', 'E',default);
insert into free_img values(seq_free_img_no.nextval, 'free33-1.png',34);

--free35 --E
insert into free values('내꾸 어때?', SEQ_FREE_POST_NO.nextval,default, 'kungwoo31', '탐 내지 마세요 다 내꺼야~', 'E',default);
insert into free_img values(seq_free_img_no.nextval, 'free34-1.png',35);

--free36 --E
insert into free values('토꺵이~', SEQ_FREE_POST_NO.nextval,default, 'moonsik', '토실토실 토깽이~', 'E',default);
insert into free_img values(seq_free_img_no.nextval, 'free35-1.png',36);

--free37 --E
insert into free values('힘을 내~~~', SEQ_FREE_POST_NO.nextval,default, 'bangsee', '내꺼 힘 쌔죠??', 'E',default);
insert into free_img values(seq_free_img_no.nextval, 'free36-1.png',37);

--free38 --E
insert into free values('4마리 출산!!!', SEQ_FREE_POST_NO.nextval,default, 'jaeseok77', '고생 고생 고생 했어!!!!', 'E',default);
insert into free_img values(seq_free_img_no.nextval, 'free37-1.jpg',38);

--free39 --E
insert into free values('힘을 내요 고슴도취!!', SEQ_FREE_POST_NO.nextval,default, 'hongchul88', '역쉬는 역쉬야~ 사랑해~', 'E',default);
insert into free_img values(seq_free_img_no.nextval, 'free38-1.jpg',39);

--free40 --E
insert into free values('꼬리가 썩어서 절단했어요ㅠㅠ', SEQ_FREE_POST_NO.nextval,default, 'greatPark22', '아프지마 마뱀이~', 'E',default);
insert into free_img values(seq_free_img_no.nextval, 'free39-1.jpg',40);

--free41 --E
insert into free values('병아리 집이 아주 넣어지고 있어요', SEQ_FREE_POST_NO.nextval,default, 'sehyeong44', '보금자리를 더 크게 크게~', 'E',default);
insert into free_img values(seq_free_img_no.nextval, 'free40-1.jpg',41);

--free42 --E
insert into free values('다람쥐 우디를 소개합니다', SEQ_FREE_POST_NO.nextval,default, 'psj12', '우리 우디 어떄요?', 'E',default);
insert into free_img values(seq_free_img_no.nextval, 'free41-1.jpg',42);

--free43 --E
insert into free values('카라멜 완성체급 한쌍', SEQ_FREE_POST_NO.nextval,default, 'pso12', '그레이스풀 카멜레온 이에요~', 'E',default);
insert into free_img values(seq_free_img_no.nextval, 'free42-1.jpg',43);

--free44 --E
insert into free values('애완뱀을 찾습니다', SEQ_FREE_POST_NO.nextval,default, 'hhu12', '짱구보는데 뱀이 너무 귀여워서!!!!', 'E',default);
insert into free_img values(seq_free_img_no.nextval, 'free43-1.jpg',44);

--free45 --E
insert into free values('개구리만화 보니까..', SEQ_FREE_POST_NO.nextval,default, 'yjkim1119', '키우는 개구리 더 잘해줘야 겠네요', 'E',default);
insert into free_img values(seq_free_img_no.nextval, 'free44-1.jpg',45);

--free46 --E
insert into free values('색다른 애완견 어때요?', SEQ_FREE_POST_NO.nextval,default, 'dkfvn23', '캥거루 쥐라고 하는데 귀요미죠?', 'E',default);
insert into free_img values(seq_free_img_no.nextval, 'free45-1.jpg',46);

--free47 --E
insert into free values('집 토끼 귀엽죠?', SEQ_FREE_POST_NO.nextval,default, 'dounghe', '집순이 집토끼 이뻐이뻐~', 'E',default);
insert into free_img values(seq_free_img_no.nextval, 'free46-1.jpg',47);

--free48 --E
insert into free values('내집 햄순이', SEQ_FREE_POST_NO.nextval,default, 'jungbuk11', '바맄ㅋㅋㅋㅋㅋ 대지야', 'E',default);
insert into free_img values(seq_free_img_no.nextval, 'free47-1.jpg',48);


---------------------------------------------------------------------------------------------------
--sharing2 --D
insert into sharing values('애견 목받침', seq_sharing_post_no.nextval, sysdate, 'nasunghee', '안산시 고잔동', default, '고잔신도시입니다','P2','D',default);  
insert into sharing_img values(seq_sharing_img_no.nextval, 'sharing1-1.jpg', 2);

--sharing3 --D
insert into sharing values('애견용품 새상품', seq_sharing_post_no.nextval, sysdate, 'file44', '안산시 고잔동', default, '포장지만 개봉','P2','D',default);  
insert into sharing_img values(seq_sharing_img_no.nextval, 'sharing2-1.jpg', 3);

--sharing4 --D
insert into sharing values('애견 패스나눔', seq_sharing_post_no.nextval, sysdate, 'sin456', '서울시 동작구', default, '애기떄 잠깐쓰고 보관중이었습니다~','P2','D',default);  
insert into sharing_img values(seq_sharing_img_no.nextval, 'sharing3-1.jpg', 4);

--sharing5 --D
insert into sharing values('주뻬 비스켓 간식', seq_sharing_post_no.nextval, sysdate, 'mire258', '서울시 마포구', default, '비싸게 주고 샀는데... 필요하신분 말해주세요','P1','D',default);  
insert into sharing_img values(seq_sharing_img_no.nextval, 'sharing4-1.jpg', 5);

--sharing6 --D
insert into sharing values('내추럴EX 입니다', seq_sharing_post_no.nextval, sysdate, 'janghyun', '서울시 성동구', default, '중소형견 간식입니다','P1','D',default);  
insert into sharing_img values(seq_sharing_img_no.nextval, 'sharing5-1.jpg', 6);

--sharing7 --D
insert into sharing values('리큅 식품 건조기 나눔 합니다', seq_sharing_post_no.nextval, sysdate, 'sechan55', '서울시 독산동', default, '건조기 드려요~','P2','D',default);  
insert into sharing_img values(seq_sharing_img_no.nextval, 'sharing6-1.jpg', 7);

--sharing8 --D
insert into sharing values('애견 통조리 신상품!!!', seq_sharing_post_no.nextval, sysdate, 'seho33', '대전', default, '진짜 새거입니다.','P1','D',default);  
insert into sharing_img values(seq_sharing_img_no.nextval, 'sharing7-1.jpg', 8);

--sharing9 --D
insert into sharing values('애견 건간식입니다', seq_sharing_post_no.nextval, sysdate, 'junha00', '부산', default, '애견 홍삼첨유되어 있는 건강식입니다','P1','D',default);  
insert into sharing_img values(seq_sharing_img_no.nextval, 'sharing8-1.jpg', 9);

--sharing10 --D
insert into sharing values('도그포즈 생리팬티', seq_sharing_post_no.nextval, sysdate, 'jennie', '부산', default, '애견 생리팬티 새거입니다','P2','D',default);  
insert into sharing_img values(seq_sharing_img_no.nextval, 'sharing9-1.jpg', 10);

--sharing11 --D
insert into sharing values('숫강아지 토일렛 브라운 2개 생겨서 하나 나눕니다', seq_sharing_post_no.nextval, sysdate, 'rose', '서울', default, '청결 저희 강아지도 좋아해요','P2','D',default);  
insert into sharing_img values(seq_sharing_img_no.nextval, 'sharing10-1.jpg', 11);

--sharing12 --D
insert into sharing values('배변 유도제 박스로 나눕니다', seq_sharing_post_no.nextval, sysdate, 'taeyeon', '서울', default, '저희 강아지는 이제 바이바이라서 올려요..','P2','D',default);  
insert into sharing_img values(seq_sharing_img_no.nextval, 'sharing11-1.jpg', 12);

--sharing13 --D
insert into sharing values('고급 틴닝 숱가위', seq_sharing_post_no.nextval, sysdate, 'yoona', '대구', default, '1+1이라서 하나만 있으면 될것 같아서 나눠요~','P2','D',default);  
insert into sharing_img values(seq_sharing_img_no.nextval, 'sharing12-1.jpg', 13);

--sharing14 --D
insert into sharing values('강아지 슈즈 나눠요~', seq_sharing_post_no.nextval, sysdate, 'yoona', '제주도', default, '한번밖에 안 신었어요','P5','D',default);  
insert into sharing_img values(seq_sharing_img_no.nextval, 'sharing13-1.jpg', 14);

--sharing15 --D
insert into sharing values('애견 양말용 주걱!!!!', seq_sharing_post_no.nextval, sysdate, 'yuri', '제주도', default, '정말 편해요~~','P5','D',default);  
insert into sharing_img values(seq_sharing_img_no.nextval, 'sharing14-1.jpg', 15);

--sharing16 --D
insert into sharing values('귀요미 양말~~', seq_sharing_post_no.nextval, sysdate, 'hyunbum76', '서울', default, '분홍색이라서 여자들이 어울릴것 같아요','P5','D',default);  
insert into sharing_img values(seq_sharing_img_no.nextval, 'sharing15-1.jpg', 16);

--sharing17 --D
insert into sharing values('추운날 우리 애견도 따스하게~', seq_sharing_post_no.nextval, sysdate, 'flower99', '서울', default, '두텁고 아주 좋아요~','P5','D',default);  
insert into sharing_img values(seq_sharing_img_no.nextval, 'sharing16-1.jpg', 17);

--sharing18 --D
insert into sharing values('초코브라운의 목줄', seq_sharing_post_no.nextval, sysdate, 'parksung', '청주', default, '길고 튼튼해요~','P3','D',default);  
insert into sharing_img values(seq_sharing_img_no.nextval, 'sharing17-1.jpg', 18);

--sharing19 --D
insert into sharing values('가죽 목줄!!', seq_sharing_post_no.nextval, sysdate, 'so2raim', '서울', default, '저희애는....ㅠㅠ','P3','D',default);  
insert into sharing_img values(seq_sharing_img_no.nextval, 'sharing18-1.jpg', 19);

--sharing20 --D
insert into sharing values('푹신푹신 패드 나눠요', seq_sharing_post_no.nextval, sysdate, 'so2raim', '전남', default, '내가 자고 싶을 만큼 푹신해요~','P4','D',default);  
insert into sharing_img values(seq_sharing_img_no.nextval, 'sharing19-1.jpg', 20);

--sharing21 --D
insert into sharing values('애견 울타리~~~', seq_sharing_post_no.nextval, sysdate, 'soheechu', '충주', default, '우리 애들은 이제 커서~;;;','P4','D',default);  
insert into sharing_img values(seq_sharing_img_no.nextval, 'sharing20-1.jpg', 21);

--sharing22 --C
insert into sharing values('양이 사료~~ 나눠요', seq_sharing_post_no.nextval, sysdate, 'joo52487', '서울', default, '우리 애들은 이제 커서~;;;','P1','C',default);  
insert into sharing_img values(seq_sharing_img_no.nextval, 'sharing21-1.jpg', 22);

--sharing23 --C
insert into sharing values('고양이 인형', seq_sharing_post_no.nextval, sysdate, 'bang3372', '서울', default, '애기들이 제일 좋아하는 고양이!!','P3','C',default);  
insert into sharing_img values(seq_sharing_img_no.nextval, 'sharing22-1.jpg', 23);

--sharing24 --C
insert into sharing values('고양이기저귀 나눔 해용', seq_sharing_post_no.nextval, sysdate, 'daejoongbig93', '서울', default, '새거에요~!!!','P2','C',default);  
insert into sharing_img values(seq_sharing_img_no.nextval, 'sharing23-1.jpg', 24);

--sharing25 --C
insert into sharing values('길냥이 사료 무료 나눔 해주실분...', seq_sharing_post_no.nextval, sysdate, 'DRAGON99', '대전', default, '사료가 부족해서..ㅠㅠ','P1','C',default);  
insert into sharing_img values(seq_sharing_img_no.nextval, 'sharing24-1.jpg', 25);

--sharing26 --C
insert into sharing values('고양이용 배변처리 모래 무향', seq_sharing_post_no.nextval, sysdate, 'hye333', '부산', default, '무향이라서 좋아요~','P2','C',default);  
insert into sharing_img values(seq_sharing_img_no.nextval, 'sharing25-1.jpg', 26);

--sharing27 --C
insert into sharing values('원목캣타워 무료 나눔합니다^^', seq_sharing_post_no.nextval, sysdate, 'jaehoon33', '제주도', default, '캣타워 깨끗합니다','P4','C',default);  
insert into sharing_img values(seq_sharing_img_no.nextval, 'sharing26-1.jpg', 27);

--sharing28 --C
insert into sharing values('정품 청의엑소시스트 캣시 고양이 인형', seq_sharing_post_no.nextval, sysdate, 'amoomoo', '제주도', default, '중대형 교신 환영 합니다~','P3','C',default);  
insert into sharing_img values(seq_sharing_img_no.nextval, 'sharing27-1.jpg', 28);

--sharing29 --E
insert into sharing values('햄스터 사료무료드림', seq_sharing_post_no.nextval, sysdate, 'zw324f3rg', '서울', default, '안 뜯은 사료에요~','P1','E',default);  
insert into sharing_img values(seq_sharing_img_no.nextval, 'sharing28-1.jpg', 29);

--sharing30 --E
insert into sharing values('기니피그 햄스터 먹이통', seq_sharing_post_no.nextval, sysdate, 'chanhoho1', '서울', default, '완전히 깔끔해요','P2','E',default);  
insert into sharing_img values(seq_sharing_img_no.nextval, 'sharing29-1.jpg', 30);

--sharing31 --E
insert into sharing values('햄스터 톱밥 나눠요', seq_sharing_post_no.nextval, sysdate, 'chanhoho1', '부산', default, '깔끔~! ^^','P2','E',default);  
insert into sharing_img values(seq_sharing_img_no.nextval, 'sharing30-1.jpg', 31);

--sharing32 --E
insert into sharing values('햄스터 오렌지2층 햄스터집', seq_sharing_post_no.nextval, sysdate, 'chanhoho1', '부산', default, '2층짜리라서 햄스터가 엄청 좋아해요','P4','E',default);  
insert into sharing_img values(seq_sharing_img_no.nextval, 'sharing31-1.jpg', 32);

--sharing33 --E
insert into sharing values('사료 소분 세트 나눕니다', seq_sharing_post_no.nextval, sysdate, 'chanhoho1', '충주', default, '사료 많이 많이^^','P1','E',default);  
insert into sharing_img values(seq_sharing_img_no.nextval, 'sharing32-1.jpg', 33);



--faq
INSERT INTO FAQ VALUES(SEQ_FAQ_NO.NEXTVAL ,'비밀번호를 잃어버렸어요.' ,'가입하신 이메일을 통해서 인증을 하시면 6자리 숫자가 메일로 발송되고, 번호 인증을 통해 비밀번호를 바꿀 수 있습니다.');
INSERT INTO FAQ VALUES(SEQ_FAQ_NO.NEXTVAL ,'실시간 정보공유는 어디에서 할 수 있나요?' ,'advice 탭에서 상담하기 버튼을 누르시면 수의사와 상담도 진행할 수 있고, 사용자들과 실시간 정보 공유를 할 수 있습니다.');
INSERT INTO FAQ VALUES(SEQ_FAQ_NO.NEXTVAL ,'반려동물을 잃어버렸어요 어떻게 해야하나요?' ,'찾아주세요 게시판에 글 등록을 해주시면 보호중입니다 게시판에 있는 게시글중 유사한 글이 있을 경우 알림으로 알려드립니다. 빨리 찾길 바래요');
INSERT INTO FAQ VALUES(SEQ_FAQ_NO.NEXTVAL ,'아이디를 변경할 수 있나요?' ,' 본인 명의로 가입된 계정은 아이디가 변경이 불가하며 비밀번호만 변경하실 수 있습니다. 회원님께서 아이디를 변경하시고 싶다면 탈퇴 후 재가입을 해주셔야 합니다.');
INSERT INTO FAQ VALUES(SEQ_FAQ_NO.NEXTVAL ,'분양받고 싶은데 어떻게 해야하나요?' ,'분양게시판에 가시면 다양한 동물친구들을 만나보실 수 있어요 회원간 분양이기 때문에 거래에 유의해주세요!');
update doctor set doctor_pwd='1ARVn2Auq2/WAqx2gNrL+q3RNjAzXpUfCXrzkA6d4Xa22yhRLy4AC50E+6UTPoscbo31nbOoq51gvkuXzJ6B2w==';

update member set member_pwd='v0AMKhy8LFU2rsQgGIZO21c47Wm/VIigF6GurSH4iK4FoTwvMN4ASkpvpO7euMjWL3lxKk+EiefspAjlezbCUA==';
update member set member_email='jaebum6937@gmail.com' where member_id='admin1';

commit;
