use lglgschame;
create table if not exists `user`(
	`id` int(11) not null auto_increment comment '用户id',
	`user_name` varchar(255)  not null comment '用户名',
	`password` varchar(255) not null comment '密码',
	`create_time` datetime default null comment '创建时间',
	primary key(`id`),
	unique key `idx_user_name` (`user_name`) using btree
) engine=Innodb  auto_increment=4 default charset=utf8 comment='用户信息表';

create table if not exists `sys_log`(
	`id` int(11) not null auto_increment,
	`user_id` int(11) default null comment '用户id',
	`module` varchar(255) default null comment '所属操作模块',
	`data` varchar(5000) character set utf8mb4 default null comment '操作数据',
	`memo` varchar(500) character set utf8mb4 default null comment '备注',
	`create_time` datetime default null comment '创建时间',
	primary key(`id`)
)engine=Innodb auto_increment=3 default charset=utf8 comment='日志记录';