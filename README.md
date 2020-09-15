# Read Me First
This challenge can be used to enrollment Subscribers and add dependants under them. This is written using the Spring Boot framework. 
The stack includes the following 
1. Spring Boot <br>
  a. JPA - for persistence<br>
  b. mySql connector for database<br>
  c. modelmapper - to convert beans to entities<br>
  d. lombok - for POJO access helper<br>
  c. swagger - for exposing the restful functions through UI<br>
2. MySql Database<br>
<br>
Functionality: <br>
A Subscriber is the head of household/holds the coverage and holds the subscription, the subscriber sends the information through enrollment channel and enrollment channel would use this API to enroll the subscriber. Subscriber can also have dependents (members). The activation status is an integer which can hold status of where the application is and would provide the latest of the status (It can also be used as 1/0 for true or false)

Solution Approch: <br>
An Entity to be created "Member_Master", both subscriptions and dependents would be considered as part of the entity, the only difference between a subscriber and member would be that for Subscribers, the subscriptionid and memberid would be the same. The Subscription id would be the same for the entire family.
There can be any number of dependents associated to a subscription. Updates can be done to both subscribers and members. Deletes are considered as soft-delete, the membership status would help understand that the subscription or a member is termed/deleted.
<br>
Table Structure:<br>
  `subscription_seq_id` bigint NOT NULL, - This is the auto generated id and is unique for every member.<br>
  `activation_status` varchar(255) NOT NULL, - This holds the status of enrollment.<br>
  `date_of_birth` datetime(6) NOT NULL, - This is holds the date of birth of a meber or subscriber<br>
  `deletion_date` datetime(6) DEFAULT NULL,- This is holds the date on which the record was deleted<br>
  `first_name` varchar(255) NOT NULL, - This is holds the first name of the member or subscriber<br>
  `insert_date_time` datetime(6) DEFAULT NULL, - Auditing column for the time when this record was inserted<br>
  `insert_process` varchar(255) DEFAULT NULL,- Auditing column for the time process/app which inserted this record<br>
  `insert_user` varchar(255) DEFAULT NULL, -  Auditing column for the time who inserted this record<br>
  `last_name` varchar(255) NOT NULL,- This is holds the last name of the member or subscriber<br>
  `member_id` varchar(255) NOT NULL,- This is holds the member id of the member, this would be unique for each member <br>
  `membership_status` varchar(255) NOT NULL, - This would be flipped to N when the record is deleted<br>
  `phone_number` varchar(255) DEFAULT NULL, - This holds the phone number<br>
  `requested_effective_date` datetime(6) NOT NULL, - Effective date for enrollment<br>
  `subscription_id` varchar(255) NOT NULL, - This is unique per family - this id would be same as member id for a subscriber<br>
  `update_date_time` datetime(6) DEFAULT NULL, - Auditing column for the time when this record was updated<br>
  `update_process` varchar(255) DEFAULT NULL,- Auditing column for the time process/app which updated this record<br>
  `update_user` varchar(255) DEFAULT NULL-  Auditing column for the time who updated this record<br>
<br>
<br>
<br>
![Image of Member Controller - Swagger UI](https://github.com/venkats3/pub/blob/master/member-controller.png)
The restful API provides the following functions

HTTP Method: GET<br>
End Point: ​/enrollment​/api​/members​/{subscriptionId} <br>
controllerfunctionname: findMemberById<br>

HTTP Method: GET<br>
End Point: ​/enrollment​/api​/members​/{subscriptionId}​/{memberId}<br>
controllerfunctionname: findMemberById<br>

HTTP Method: POST<br>
End Point: ​/enrollment​/api​/members​/newMember<br>
controllerfunctionname: newMember<br>

HTTP Method: POST<br>
End Point: ​/enrollment​/api​/members​/newSubscriber<br>
controllerfunctionname: newSubscriber<br>

HTTP Method: PUT<br>
End Point: ​/enrollment​/api​/members​/updateMember<br>
controllerfunctionname: updateMember<br>

HTTP Method: PUT<br>
End Point: ​/enrollment​/api​/members​/updateSubscriber<br>
controllerfunctionname: updateSubscriber<br>

HTTP Method: DELETE<br>
End Point: ​/enrollment​/api​/members​/deleteMember​/{subscriptionId}​/{memberId}<br>
controllerfunctionname: deleteMember<br>

HTTP Method: DELETE<br>
End Point: ​/enrollment​/api​/members​/deleteSubscriber​/{subscriptionId}<br>
controllerfunctionname: deleteSubscription<br>
<br>
# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.3.3.RELEASE/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.3.3.RELEASE/maven-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.3.3.RELEASE/reference/htmlsingle/#boot-features-developing-web-applications)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/2.3.3.RELEASE/reference/htmlsingle/#boot-features-jpa-and-spring-data)
* [Spring Boot Actuator](https://docs.spring.io/spring-boot/docs/2.3.3.RELEASE/reference/htmlsingle/#production-ready)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/2.3.3.RELEASE/reference/htmlsingle/#using-boot-devtools)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Building a RESTful Web Service with Spring Boot Actuator](https://spring.io/guides/gs/actuator-service/)

