grammar Scheduling;

ID : [a-zA-Z]+ ;             // match lower-case identifiers

NUM : [0-9]+ ;

WS : [ \t\r\n]+ -> skip ; // skip spaces, tabs, newlines

command : (create|set|allocate)+;

create : 'create' entity;

allocate : 'allocate' room course class_num allocate_property;

class_num : 'class' NUM;

allocate_property : schedule_day schedule_hour;

entity : schedule schedule_property
|constraint|room room_property|facility|course course_property|lecturer;

schedule : 'schedule' ID;

schedule_property : schedule_day schedule_hour schedule_start;

schedule_day : 'day' NUM;

schedule_hour : 'hour' NUM;

schedule_start : 'start' NUM;

constraint : 'constraint' ID;

room : 'room' ID;

room_property : room_capacity facility*;

room_capacity : 'capacity' NUM;

facility : 'facility' ID 'count' NUM;

//create_course : 'create' course course_property;

//create_lecturer : 'create' lecturer lecturer_property;

set : 'set' entity;

course : 'course' ID;

course_property : course_year course_duration course_class course_capacity facility* lecturer+;

course_capacity : 'capacity' NUM;

course_year : 'year' NUM;

course_duration : 'duration' NUM;

course_class : 'class' NUM;

lecturer : 'lecturer' ID lecturer_property;

lecturer_property : course*;

property : 'property' ID;
