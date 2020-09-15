# Read Me
This challenge can be used to enrollment Subscribers and add dependants under them. This is written using the Spring Boot framework. 
The stack includes the following 
1. **Spring Boot** <br>
  a. JPA - for persistence<br>
  b. mySql connector for database<br>
  c. modelmapper - to convert beans to entities<br>
  d. lombok - for POJO access helper<br>
  c. swagger - for exposing the restful functions through UI<br>
2. **MySql Database**<br>
<br>
**Functionality:**<br>
A Subscriber is the head of household/holds the coverage and holds the subscription, the subscriber sends the information through enrollment channel and enrollment channel would use this API to enroll the subscriber. Subscriber can also have dependents (members). The activation status is an integer which can hold status of where the application is and would provide the latest of the status (It can also be used as 1/0 for true or false)

**Solution Approch:** <br>
An Entity to be created "Member_Master", both subscriptions and dependents would be considered as part of the entity, the only difference between a subscriber and member would be that for Subscribers, the subscriptionid and memberid would be the same. The Subscription id would be the same for the entire family.
There can be any number of dependents associated to a subscription. Updates can be done to both subscribers and members. Deletes are considered as soft-delete, the membership status would help understand that the subscription or a member is termed/deleted.
<br>
**Table Structure:**<br>
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
![Image of Member Controller - Swagger UI](https://github.com/venkats3/pub/blob/master/appScreenShots/member-controller.png)
The restful API provides the following functions

### findMemberById
HTTP Method: GET<br>
End Point: ​/enrollment​/api​/v1​/members​/{subscriptionId}<br>
controller function name: findMemberById<br>

### findMemberById
HTTP Method: GET<br>
End Point: /enrollment​/api​/v1​/members​/{subscriptionId}​/{memberId}<br>
controller function name: findMemberById <br>

### newMember
HTTP Method: POST<br>
End Point: /enrollment​/api​/v1​/members​/newMember<br>
controller function name: newMember<br>

### newSubscriber
HTTP Method: POST<br>
End Point: /enrollment​/api​/v1​/members​/newSubscriber<br>
controller function name: newSubscriber<br>

###  updateMember
HTTP Method: PUT<br>
End Point: /enrollment​/api​/v1​/members​/updateMember<br>
controller function name: updateMember<br>

### updateSubscriber
HTTP Method: PUT<br>
End Point: /enrollment​/api​/v1​/members​/updateSubscriber<br>
controller function name: updateSubscriber<br>

### deleteMember
HTTP Method: DELETE<br>
End Point: /enrollment​/api​/v1​/members​/deleteMember​/{subscriptionId}​/{memberId}<br>
controller function name: deleteMember<br>

### deleteSubscription
HTTP Method: DELETE<br>
End Point: /enrollment​/api​/v1​/members​/deleteSubscriber​/{subscriptionId}<br>
controller function name: deleteSubscription<br>
<br>

# Getting Started

# Building and Running
1. Download the source file from [GitHub](https://github.com/venkats3/pub)
2. Logon to MySql database run the [Script](https://github.com/venkats3/pub/blob/master/table_schema.sql%3B)
3. mvn clean install - This should generate demo-0.0.1-SNAPSHOT.jar (under target Spring-Workspace\enrollment-rest\membership-service\target\demo-0.0.1-SNAPSHOT.jar)
4. Run the app java -jar demo-0.0.1-SNAPSHOT.jar
5. go to [link to Swagger](http://localhost:8080/enrollment/swagger-ui/#/) to access the restful services created.

![Image of Member Controller - Swagger UI](https://github.com/venkats3/pub/blob/master/appScreenShots/maven-install-output.png)


# Screenshots of Functions

## Add New Subscriber
![Image of New Subs](https://github.com/venkats3/pub/blob/master/appScreenShots/00-NewSubscriberResponse.png)
## Add New Member
![Image of New Memb](https://github.com/venkats3/pub/blob/master/appScreenShots/01-NewMemberResponse.png)

## Find a Subscriber
![Image of Find Subs](https://github.com/venkats3/pub/blob/master/appScreenShots/03-FindSubscriberResponse.png)

## Find a Member
![Image of Find Memb](https://github.com/venkats3/pub/blob/master/appScreenShots/04-FindMemberReponse.png)

## Update a Subscriber
![Image of Update Subs](https://github.com/venkats3/pub/blob/master/appScreenShots/05-UpdateSubscriber.png)

## Update a Member
![Image of Update Memb](https://github.com/venkats3/pub/blob/master/appScreenShots/06-UpdateMember.png)

## Delete a Subscriber
![Image of delete Subs](https://github.com/venkats3/pub/blob/master/appScreenShots/07-DeleteSubscriber.png)

## Delete a Member
![Image of delete Subs](https://github.com/venkats3/pub/blob/master/appScreenShots/08-DeleteMember.png)


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

