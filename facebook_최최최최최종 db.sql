drop database facebook;
create database facebook;
use facebook;

create table user(
user_ID varchar(50),
password varchar(50),
user_name varchar(50),
phone_number varchar(20),
birthday date,
address varchar(50),
follower int,
following int,
phone_number2 varchar(50),
primary key(user_ID));
desc user;
select*from user;
insert into user values
("11111","eeeee","Timmy", "010-0104-1234","1999-01-30", "Seoul, Gangnam-gu, Yeoksam-dong 123",5,1,null),
("12222","aaaaa","Jimmy","010-1234-1234", "1996-02-28", "Incheon, Namdong-gu, Ganseok-dong 456",3,2,null),
("13333","abbbb","sunny","010-0293-3827", "1967-03-11", "Daejeon, Yuseong-gu, Bongmyeong-dong 789",2,3,null),
("14444","acccc","carry","010-4926-6632", "2003-04-01", "Busan, Haeundae-gu, Jwadong 101",3,3,null),
("55551","adddd","jessie","010-3847-2047", "2002-05-05", "Gyeongsangbuk-do, Pohang-si, Buk-gu",0,0,null),
("66661","aeeee","sophia","010-0854-2345", "2012-06-06", "Chungcheongnam-do, Cheonan-si, Seo-bukgu",1,1,null),
("77771","bbbbb","tom","010-9262-8865", "2011-07-17", "Gwangju, Seo-gu, Nongseong-dong 890",2,5,null),
("88882","abcab","william","010-2345-9854", "2009-08-28","Daegu, Suseong-gu, Manchon-dong 112",4,3,null),
("99992","ddddd","willson","010-1235-5673", "1986-09-16","Gangwon-do, Chuncheon-si, Namsan-myeon 345",3,2,null),
("10001","eeeee","son","010-3097-1234", "1968-11-13","Jeollabuk-do, Jeonju-si, Deokjin-gu",2,1,null),

("1111","aaab","Aaron","010-4512-1052","2002-09-07","Gyeonggi-do, Gwacheon-si, Munwon-dong 10",2,2,null),
("2222","bbbc","Abigail","010-6622-2620","2002-04-15","Gyeonggi-do, Anyang-si, Dongan-gu",1,3,null),
("3333","cccd","Brandon","010-5556-2623","2000-11-07","Gyeonggi-do, Gunpo-si Sanbon-dong 20",1,1,null),
("4444","ddde","Bradley","010-4442-4000","2001-06-06","Gyeonggi-do, Hwaseong-si, Banwol-dong 30",0,1,null),
("5555","eeef","Bruce","010-8282-5640","2000-05-05","Seoul, Seongdong-gu, Majanf-dong 40",3,1,null),
("6666","fffg","Carter","010-0352-2950","2001-08-15","Seoul, Gangnam-gu, Daechi-dong 50",2,1,null),
("7777","gggh","Elliott","010-0163-3500","1999-10-17","Seoul, Gangdong-gu, Cheonho-dong 60",1,0,null),
("8888","hhhi","Felix","010-7004-4660","1998-07-06","Busan, Haeundae-gu, Bansong-dong 70",2,2,null),
("9999","iiij","Hudson","010-0603-3058","1996-12-30","Daegu, Suseong-gu, Beomeo-dong 80",1,3,null),
("1010","jjjk","Heath","010-0894-4700","1997-04-16","Daejeon, Yuseong-gu, Jinjam-dong 90",2,1,null),

("111","aaazx","aaron","010-6444-7585", "2002-02-14","Seoul, Mapo-gu, Sungsan-dong 37",3,2,null),
("222","bbbzx","adam","010-2710-0579", "1970-07-05","Gyeongsangbuk-do, Gumi-si, Jisan-dong 588",4,1,null),
("333","ccczx","bryce","010-4809-0732", "2008-09-21","Gwangju, Dong-gu, Duam-dong 323",1,1,null),
("444","dddzx","brian","010-5537-2581", "1998-11-29","Busan, Saga-gu, Gaejong-dong 99",2,1,null),
("555","eeezx","dylan","010-4041-3769", "1981-10-02","Daegu, Dong-gu, Dongho-dong 370",4,2,null),
("666","fffzx","elliott","010-4856-8396", "2003-08-07","Seoul, Songbuk-gu, Majang-dong 241",1,4,null),
("777","gggzx","finn","010-9501-5003", "1977-02-21","Incheon, Michuhoel-gu, Gyengwon-dong 31",1,3,null),
("888","hhhzx","henry","010-3026-0177", "2011-01-10","Seoul, Songpa-gu, bubwon-ro 29",2,3,null),
("999","iiizx","ishmael","010-5523-6080", "2004-08-09","Gyeonggi, Buchon-si, Bugwang-ro 64",1,2,null),
("101","jjjzx","kaden","010-9733-3448", "1992-04-15","Seoul, Seocho-gu, Narutae-ro 9",1,1,null),

("11","aazx","adela","010-6432-7521", "2001-01-13","Seoul, Mapo-gu, Sugeyo-dong 41",3,2,null),
("22","bbzx","ann","010-2640-0509", "1969-06-04","Gyeongsangbuk-do, Gumi-si, Dosng-dong 921",4,1,null),
("33","cczx","bianca","010-4149-0732", "2007-08-20","Gwangju, Dong-gu, Shingang-dong 21",1,1,null),
("44","ddzx","bono","010-5013-2362", "1997-10-28","Busan, Saga-gu, Gaejong-dong 99",2,1,null),
("55","eezx","crrystal","010-4294-3392", "1980-09-01","Daegu, Dong-gu, Dongho-dong 370",4,2,null),
("66","ffzx","doreen","010-4392-8010", "2002-07-06","Seoul, Gangnam-gu, onegoeng-dong 11",1,4,null),
("77","ggzx","emily","010-9038-5123", "1976-01-20","Incheon, Soraepo-gu, Ingu-dong 521",1,3,null),
("88","hhzx","eva","010-3999-0322", "2010-01-09","Seoul, Mapo-gu, Ahyen-dong 35",2,3,null),
("99","iizx","grace","010-5302-6973", "2003-07-08","Gyeonggi, Buchon-si, Sujung-ro 122",1,2,null),
("01","jjzx","helen","010-3233-3401", "1991-03-14","Seoul, Seodaemun-gu, Narutae-ro 7",1,1,null);

drop table user_image;
## 이미지 미정
create table user_image(
user_image_ID varchar(50),
user_ID varchar(50),
image int,
primary key (user_image_ID),
foreign key (user_ID) references user(user_ID));

drop table birth;
create table birth (
    user_ID varchar(50),
    age int,
    primary key (user_ID),
    foreign key (user_ID) references user(user_ID)
);

INSERT INTO birth (user_ID, age)
VALUES
    ("11111", 24),
    ("12222", 27),
    ("13333", 56),
    ("14444", 20),
    ("55551", 21),
    ("66661", 11),
    ("77771", 12),
    ("88882", 14),
    ("99992", 37),
    ("10001", 55),
    ("1111", 22),
    ("2222", 22),
    ("3333", 24),
    ("4444", 23),
    ("5555", 24),
    ("6666", 23),
    ("7777", 25),
    ("8888", 26),
    ("9999", 28),
    ("1010", 27),
    ("111",22),
    ("222",53),
    ("333",15),
    ("444",25),
    ("555",42),
    ("666",21),
    ("777",46),
    ("888",12),
    ("999",20),
    ("101",31),
    
    ("11",21),
    ("22",52),
    ("33",14),
    ("44",24),
    ("55",41),
    ("66",20),
    ("77",45),
    ("88",11),
    ("99",19),
    ("01",30);


create table post(
post_ID varchar(50),
post_date datetime,
body_text varchar(500),
author_ID varchar(50),
primary key (post_ID),
foreign key (author_ID) references user(user_ID)
);
desc post;

insert into post values
("99999", NOW()-INTERVAL 1 DAY, "Just had the most amazing meal! Grateful for good food and even better company.","11111"),
("99998", NOW()-INTERVAL 10 DAY, "Rainy days are my favorite. ☔ Cozy blankets, hot coffee, and a good book make the perfect combo. What's your rainy day ritual","11111"),
("99997", NOW()-INTERVAL 11 DAY, "Excited to start a new project! Stay tuned for updates.","13333"),
("99996", NOW()-INTERVAL 12 DAY, "Weekend vibes in full swing! Whether it's a movie night or a spontaneous adventure, make the most of your days off. ","14444"),
("99995", NOW()-INTERVAL 2 DAY, "Reflecting on the past year. Grateful for the lessons, growth, and the people who made it special. Here's to the next chapter!","66661"),
("99994", NOW()-INTERVAL 9 DAY, "Sunset walks are my therapy. Nature has a way of calming the mind and soothing the soul. What's your go-to stress reliever?","66661"),
("99993", NOW()-INTERVAL 8 DAY, "Trying a new recipe today!Cooking can be therapeutic. What's your favorite dish to make at home? ","66661"),
("99992", NOW()-INTERVAL 4 DAY, "Chasing dreams and setting goals. Every step forward is progress. What's one thing you're working towards right now?","77771"),
("99991", NOW()-INTERVAL 11 DAY, "Random acts of kindness can brighten someone's day.️ Whether it's a smile or a small gesture, spread positivity wherever you go!","10001"),
("99990", NOW()-INTERVAL 111 DAY, "Finding joy in the little things. A good cup of tea, a warm hug, or a beautiful sunset—what brings a smile to your face?","10001"),

("999", NOW()-INTERVAL 5 DAY, "No matter how much I think about it, love for cats has to be one-sided.","111"),
("998", NOW()-INTERVAL 13 DAY, "It snowed. It's my first snow of the year.","111"),
("997", NOW()-INTERVAL 18 DAY, "I went to the hospital for a cold, but I got a flu and a COVID shot.","222"),
("996", NOW()-INTERVAL 6 DAY, "Finally, Sujin's food. It tasted like tteokbokki for the first time in my life.","333"),
("995", NOW()-INTERVAL 112 DAY, "I feel good because the weather is nice today.","555"),
("994", NOW()-INTERVAL 120 DAY, "The exam is finally over. I went to the East Sea to celebrate the end of the exam.","555"),
("993", NOW()-INTERVAL 3 DAY, "It's Christmas soon. Where are you going?","666"),
("992", NOW()-INTERVAL 17 DAY, "The weather is cool, so I came to the Han River to play. Chicken is really good.","777"),
("991", NOW()-INTERVAL 101 DAY, "I bought some clothes, but I'm really upset that it came with a hole in it!","777"),
("990", NOW()-INTERVAL 62 DAY, "Please recommend hip hop songs.","999"),

("99", NOW()-INTERVAL 14 DAY, "No matter how much I think about it, love for cats has to be one-sided.","11"),
("98", NOW()-INTERVAL 122 DAY, "It snowed. It's my first snow of the year.","11"),
("97", NOW()-INTERVAL 19 DAY, "I went to the hospital for a cold, but I got a flu and a COVID shot.","22"),
("96", NOW()-INTERVAL 15 DAY, "Finally, Sujin's food. It tasted like tteokbokki for the first time in my life.","33"),
("95", NOW()-INTERVAL 121 DAY, "I feel good because the weather is nice today.","55"),
("94", NOW()-INTERVAL 119 DAY, "The exam is finally over. I went to the East Sea to celebrate the end of the exam.","55"),
("93", NOW()-INTERVAL 22 DAY, "It's Christmas soon. Where are you going?","66"),
("92", NOW()-INTERVAL 16 DAY, "The weather is cool, so I came to the Han River to play. Chicken is really good.","77"),
("91", NOW()-INTERVAL 100 DAY, "I bought some clothes, but I'm really upset that it came with a hole in it!","77"),
("90", NOW()-INTERVAL 61 DAY, "Please recommend hip hop songs.","99");


create table post_likes(
user_ID varchar(50),
post_ID varchar(50),

primary key(user_ID, post_ID)
);
desc post_likes;

insert into post_likes values
("11111","99999"),
("12222","99999"),
("13333","99999"),
("14444","99999"),
("15555","99999"),
("77771","99999"),
("11111","99995"),
("12222","99995"),
("13333","99995"),
("14444","99995"),
("15555","99995"),
("11111","99993"),
("12222","99993"),
("13333","99993"),
("11111","99992"),
("12222","99992"),

("777","993"),
("111","996"),
("111","997"),
("222","999"),
("101","995"),
("999","995"),
("666","992"),
("666","991"),
("888","990"),
("333","998"),

("77","93"),
("11","96"),
("11","97"),
("22","99"),
("01","95"),
("99","95"),
("66","92"),
("66","91"),
("88","90"),
("33","98");

create table follower(
user_ID varchar(50),
follower_ID varchar(50),
primary key (user_ID, follower_ID),
foreign key (user_ID) references user(user_ID),
foreign key (follower_ID) references user(user_ID)
);
desc follower;

insert into follower values
("11111","12222"),
("11111","13333"),
("11111","14444"),
("11111","55551"),
("11111","66661"),
("12222","77771"),
("12222","13333"),
("12222","14444"),
("13333","99992"),
("13333","66661"),
("14444","77771"),
("14444","11111"),
("14444","88882"),
("66661","77771"),
("77771","88882"),
("77771","99992"),
("88882","77771"),
("88882","13333"),
("88882","99992"),
("88882","10001"),
("99992","77771"),
("99992","88882"),
("99992","14444"),
("10001","12222"),
("10001","14444"),

("1111", "2222"),
("1111", "3333"),
("2222", "1111"),
("3333", "1111"),
("5555", "8888"),
("5555", "2222"),
("5555", "9999"),
("6666", "2222"),
("6666", "5555"),
("7777", "6666"),
("8888", "1010"),
("8888", "9999"),
("9999", "4444"),
("1010", "9999"),
("1010", "8888"),

("111","222"),
("111","333"),
("111","444"),
("222","111"),
("222","555"),
("222","777"),
("222","888"),
("333","111"),
("444","555"),
("444","666"),
("555","666"),
("555","777"),
("555","999"),
("555","101"),
("666","777"),
("777","666"),
("888","666"),
("888","999"),
("999","888"),
("101","888"),

("11","22"),
("11","33"),
("11","44"),
("22","11"),
("22","55"),
("22","77"),
("22","88"),
("33","11"),
("44","55"),
("44","66"),
("55","66"),
("55","77"),
("55","99"),
("55","01"),
("66","77"),
("77","66"),
("88","66"),
("88","99"),
("99","88"),
("01","88");

create table following(
user_ID varchar(50),
following_ID varchar(50),
primary key (user_ID, following_ID),
foreign key (user_ID) references user(user_ID),
foreign key (following_ID) references user(user_ID)
);
desc following;

insert into following values
("12222","11111"),
("12222","10001"),
("13333","11111"),
("13333","12222"),
("13333","88882"),
("14444","11111"),
("14444","12222"),
("14444","99992"),
("14444","10001"),
("66661","13333"),
("77771","12222"),
("77771","14444"),
("77771","66661"),
("77771","88882"),
("77771","99992"),
("88882","14444"),
("88882","77771"),
("88882","99992"),
("99992","13333"),
("99992","77771"),
("10001","88882"),
("11111","14444"),

("1111", "2222"),
("1111", "3333"),
("2222", "1111"),
("2222", "5555"),
("2222", "6666"),
("3333", "1111"),
("4444", "9999"),
("5555", "6666"),
("6666", "7777"),
("8888", "5555"),
("8888", "1010"),
("9999", "5555"),
("9999", "8888"),
("9999", "1010"),
("1010", "8888"),

("111","222"),
("111","333"),
("222","111"),
("333","111"),
("444","111"),
("555","222"),
("555","444"),
("666","444"),
("666","555"),
("666","777"),
("666","888"),
("777","222"),
("777", "555"),
("777","666"),
("888","222"),
("888","999"),
("888","101"),
("999","555"),
("999","888"),
("101","555"),

("11","22"),
("11","33"),
("22","11"),
("33","11"),
("44","11"),
("55","22"),
("55","44"),
("66","44"),
("66","55"),
("66","77"),
("66","88"),
("77","22"),
("77","55"),
("77","66"),
("88","22"),
("88","99"),
("88","01"),
("99","55"),
("99","88"),
("01","55");


drop table organization;

create table organization(
org_ID varchar(50),
org_name varchar(50),
org_address varchar(50),
org_number varchar(20),
primary key (org_ID));
desc organization;

insert into organization values
("45670","ABC Innovations Ltd","123 Main Street, Ste 101, Cityville, ST","555-1234-5678"),
("45671","Quantum Solutions Inc.","456 Tech Ave, Floor 5, Technocity, ST","555-2345-6789"),
("45672","Apex Dynamics Technologies","567 Future Blvd, Ste 301, Innovation City, ST","555-3456-7890"),
("45673","Pinnacle Quantum Labs","890 Summit Ave, Lab Building, Discoveryville, ST","555-3456-7877"),
("45674","Fusion Dynamics Corporation","678 Harmony Sq, Floor 2, Progressville, ST","555-1234-3230"),
("45675","Synergy Nexus Enterprises","789 Innovation Ln, Office 201, Progressville, ST","555-1212-9990"),
("45676","Harmony Academy","112 Horizon St, Floor 4, Progress City, ST","555-7837-9393"),
("45677","Evergreen Crest School","456 Tech Ave, Floor 5, Technocity, ST","121-1212-1212"),
("45678","Radiant Star University","101 Visionary Plaza, Tower 3, Futuricity, ST","888-202-222"),
("45679","Crestwood High School","234 Quantum St, Lab Wing, Sciencetown, ST","121-1212-4948"),

("450","GS Global","3780 S Las Vegas Blvd, Floor 5 Las Vegas, NV","888-8352-5008"),
("451","Union","390 Congress St, Tower 2, Portland, ME","356-7834-0093"),
("452","Shinhan", "150-29 Northern Blvd, Floor 2, Queens, NY","301-602-7700"),
("453","Cambridge University","66 Field St, Cambridge, MA","650-723-2300"),
("454","Stanford University","450 Jane Stanford Way, Tower 7, Stanford, CA","656-1234-2506"),
("455","Chicago University","325 Jane Chicago Way, Tower 9, Chicago, CA","926-1325-3306"),
("456","SONOS","171 Camino Real, Ste 102, Mountain View, CA","650-336-8993"),
("457","Vincent High School","3290 Middlefield Rd, Palo Alto, CA","650-424-8155"),
("458","RYken High School","22600 Camp Calvert Rd, Leonardtown, MD","301-475-2814"),
("459","Radiant University","203 Arbury Rd, Lab Academy, Cambridge, UK","122-491-1500");

drop table belong;

create table belong(
user_ID varchar(50),
org_ID varchar(50),
primary key(user_ID, org_ID),
foreign key (user_ID) references user(user_ID),
foreign key (org_ID) references organization(org_ID)
);
desc belong;

insert into belong values
("11111","45670"),
("12222","45670"),
("13333","45670"),
("14444","45678"),
("55551","45678"),
("66661","45677"),
("77771","45677"),
("88882","45677"),
("99992","45674"),
("10001","45674"),

("111","459"),
("222","450"),
("333","457"),
("444","453"),
("555","452"),
("666","455"),
("777","456"),
("888","458"),
("999","455"),
("101","451"),

("11","459"),
("22","450"),
("33","457"),
("44","453"),
("55","452"),
("66","455"),
("77","456"),
("88","458"),
("99","455"),
("01","451");

drop table groups_;

create table groups_(
group_ID varchar(50),
group_name varchar(20),
group_content varchar(500),
primary key (group_id)
);
desc groups_;

insert into groups_ values
('00000', 'Tech Enthusiasts', 'Discussing the latest trends and innovations in the tech world.'),
('11111', 'Fitness Fanatics', 'A community dedicated to health and fitness. Share your workout routines and tips!'),
('22222', 'Travel Explorers', 'Discover the world with fellow travelers. Share your travel stories and recommendations.'),
('33333', 'Book Lovers Club', 'For those who love reading! Discuss your favorite books and discover new ones.'),
('44444', 'Photography Passion', 'A space for photography enthusiasts to share their work and exchange tips.'),
('55555', 'Cooking Masters', 'From recipes to kitchen hacks, join the cooking conversation and showcase your culinary creations.'),
('66666', 'Creative Writers', 'Express your creativity through writing. Share your stories, poems, and more.'),
('77777', 'Gaming Zone', 'Connect with gamers worldwide. Discuss games, strategies, and find gaming buddies.'),
('88888', 'Pet Lovers United', 'For all the pet parents out there! Share adorable pictures and discuss pet care tips.'),
('99999', 'Music Melodies', 'A community for music lovers. Discuss your favorite genres, artists, and latest releases.'),

('550', 'Drama Review', 'A collection of drama highlights.'),
('551', 'Movie Review', 'Film plot reviews and ratings.'),
('552', 'Football Community', 'EPL Review.'),
('553', 'Eating Show', 'A variety of food broadcasts.'),
('554', 'Dance Upload', 'Updated various dance cover videos.'),
('545', 'Entertainment', 'A place to share funny videos on variety shows and YouTube.'),
('556', 'Playlist Sharing', 'Upload your favorite song or a place on your playlist.'),
('557', 'News', 'Places where issues and news releases are posted every day.'),
('558', 'Coding Community', 'Where to post coding tips or videos.'),
('559', 'Singer Fan Community', 'A place to share videos or posts of your favorite singers.');

-- 오류 해결
create table follow_group( 
user_ID varchar(50),
group_ID varchar(50),
primary key (user_ID, group_ID),
foreign key (user_ID) references user(user_ID),
foreign key (group_ID) references groups_(group_ID));
desc follow_group;

insert into follow_group values
("12222",'00000'),
("77771",'00000'),
("88882",'00000'),
("12222",'22222'),
("77771",'22222'),
("88882",'22222'),
("88882",'33333'),
("88882",'44444'),
("77771",'66666'),
("12222",'99999'),

("111",'550'),
("222",'550'),
("444",'551'),
("444",'552'),
("555",'553'),
("666",'554'),
("888",'556'),
("888",'557'),
("999",'556'),
("101",'559'),

("11",'550'),
("22",'550'),
("44",'551'),
("44",'552'),
("55",'553'),
("66",'554'),
("88",'556'),
("88",'557'),
("99",'556'),
("01",'559');

drop table comment;

create table comment(
comment_ID varchar(50),
user_ID varchar(50),
comment_content varchar(250),
comment_date DATETIME,
post_ID varchar(50),

primary key (comment_ID),
foreign key (user_ID) references user(user_ID),
foreign key (post_ID) references post(post_ID)
);
desc comment;

insert into comment values
("10000", "88882", 'Great post! I completely agree with your perspective.', NOW(), "99995"),
("10001", "77771", 'This is fascinating. Thanks for sharing!', NOW() - INTERVAL 1 DAY, "99995"),
("10002", "13333", 'I have a question about the topic. Can you elaborate more?', NOW(), "99995"),
("10003", "12222", 'Awesome content! Looking forward to more.', NOW() - INTERVAL 3 DAY, "99995"),
("10004", "88882", 'I resonate with this. It\'s important to spread awareness.', NOW() - INTERVAL 3 DAY, "99995"),
("10005", "77771", 'Impressive work! How did you come up with this idea?', NOW() - INTERVAL 3 DAY, "99992"),
("10006", "55551", 'I shared this with my friends. It is worth the read!', NOW() - INTERVAL 3 DAY, "99992"),
("10007", "88882", 'Any recommendations for further reading on this topic?', NOW() - INTERVAL 2 DAY, "99992"),
("10008", "14444", 'This made my day! Thank you for spreading positivity.', NOW() - INTERVAL 2 DAY, "99992"),
("10009", "88882", 'I\'m curious about your process. Can you share more details?', NOW() - INTERVAL 1 DAY, "99999"),

("100", "111", 'I hope you get better soon.', NOW() - INTERVAL 7 DAY, "997"),
("101", "111", 'Lets go with me next time!', NOW() - INTERVAL 4 DAY, "996"),
("102", "222", 'Cats are the loveliest thing in the world.', NOW(), "999"),
("103", "333", 'I hope it snows more soon. I love the snow.', NOW() - INTERVAL 12 DAY, "998"),
("104", "555", 'I heard that the cold is so strong these days. Be careful..', NOW() - INTERVAL 8 DAY, "997"),
("105", "666", 'It must have been great to see Han River.?', NOW() - INTERVAL 2 DAY, "992"),
("106", "666", 'It must have been really frustrating!', NOW() - INTERVAL 1 DAY, "991"),
("107", "888", 'I love the song trip.', NOW() - INTERVAL 20 DAY, "990"),
("108", "444", 'You did a great job. What are you planning to do now?', NOW() - INTERVAL 141 DAY, "996"),
("109", "999", 'It\'s a wonderful weather!', NOW() - INTERVAL 100 DAY, "995"),

("00", "11", 'I hope you get better soon.', NOW() - INTERVAL 7 DAY, "97"),
("01", "11", 'Lets go with me next time!', NOW() - INTERVAL 4 DAY, "96"),
("02", "22", 'Cats are the loveliest thing in the world.', NOW(), "99"),
("03", "33", 'I hope it snows more soon. I love the snow.', NOW() - INTERVAL 12 DAY, "98"),
("04", "55", 'I heard that the cold is so strong these days. Be careful..', NOW() - INTERVAL 8 DAY, "997"),
("05", "66", 'It must have been great to see Han River.?', NOW() - INTERVAL 2 DAY, "92"),
("06", "66", 'It must have been really frustrating!', NOW() - INTERVAL 1 DAY, "91"),
("07", "88", 'I love the song trip.', NOW() - INTERVAL 20 DAY, "90"),
("08", "44", 'You did a great job. What are you planning to do now?', NOW() - INTERVAL 141 DAY, "96"),
("09", "99", 'It\'s a wonderful weather!', NOW() - INTERVAL 100 DAY, "95");

drop table comment_likes;

create table comment_likes(
user_ID varchar(50),
comment_ID varchar(50),

primary key(user_ID, comment_ID),
foreign key(comment_ID) references comment(comment_ID),
foreign key(user_ID) references user(user_ID)
);
desc comment_likes;

insert into comment_likes values
("10001","10000"),
("99992","10000"),
("88882","10000"),
("77771","10000"),
("11111","10000"),
("10001","10001"),
("99992","10001"),
("99992","10002"),
("10001","10003"),
("10001","10004"),

("111","102"),
("111","103"),
("222","100"),
("222","104"),
("333","101"),
("333","108"),
("555","109"),
("777","105"),
("777","106"),
("999","107"),

("11","02"),
("11","03"),
("22","00"),
("22","04"),
("33","01"),
("33","08"),
("55","09"),
("77","05"),
("77","06"),
("99","07");

drop table comment_tag;

create table comment_tag(
comment_ID varchar(50),
user_ID varchar(50),
tagged_ID varchar(50),
primary key (user_ID, tagged_ID,comment_ID),
foreign key (user_ID) references user(user_ID),
foreign key (tagged_ID) references user(user_ID),
foreign key (comment_ID) references comment(comment_ID)
);

desc comment_tag;

insert into comment_tag values
("10000", "88882","55551"),
("10000", "88882","12222"),
("10001", "77771","88882"),
("10001", "77771","55551"),
("10005", "77771","12222"),
("10008", "14444","55551"),
("10008", "14444","88882"),
("10009", "88882","55551"),
("10002", "13333","12222"),
("10009", "88882","12222"),

("100", "111","222"),
("100", "111","333"),
("109", "999","555"),
("109", "999","888"),
("105", "666","444"),
("105", "666","555"),
("105", "666","777"),
("107", "888","999"),
("107", "888","101"),
("108", "444","111"),

("00", "11","22"),
("00", "11","33"),
("09", "99","55"),
("09", "99","88"),
("05", "66","44"),
("05", "66","55"),
("05", "66","77"),
("07", "88","99"),
("07", "88","01"),
("08", "44","11");
