# TODO List API

- Scala 2.12.4
- Akka-http 10.1.0-RC1
- Slick 3.2.1
- MySQL 5.7

# TODO LIST API specification document

## Health Check

` GET /health_check ` 

##### request

- Headers
- Parameters

##### response

```
status: 200 OK
---
{
    "status": "ok"
}
```

## Todo

### GET ALL TODO LIST

` GET /todos`

##### request

- Headers
- Parameters

##### response

```
status: 200 OK
---
  [
    {
      "id": 1,
      "title": "TITLE of 1",
      "body": "BODY of 1",
      "status": 0,
      "created_at": "2018-01-16T09:12:31Z",
      "updated_at": "2018-01-16T09:12:31Z"
    }, 
    {
      "id": 2,
      "title": "TITLE of 2",
      "body": "BODY of 2",
      "status": 0,
      "created_at": "2018-01-16T09:12:31Z",
      "updated_at": "2018-01-16T09:12:31Z"
    }, 
    {
        ...
    }, ...
  ]
```

### Search TODO LIst

` GET /todos?q=TITLE&status=0`

#### request 

- Headers
- Parameters

Name | Type | Description 
---- | ---- | ----------- 
q | String | search word. contain 'title' or 'body'
status | Int | Todo status (0:open, 1:close)

##### response

```
status: 200 OK
---
  [
    {
      "id": 1,
      "title": "TITLE of 1",
      "body": "BODY of 1",
      "status": 0,
      "created_at": "2018-01-16T09:12:31Z",
      "updated_at": "2018-01-16T09:12:31Z"
    }, 
    {
      "id": 2,
      "title": "TITLE of 2",
      "body": "BODY of 2",
      "status": 0,
      "created_at": "2018-01-16T09:12:31Z",
      "updated_at": "2018-01-16T09:12:31Z"
    }, 
    {
        ...
    }, ...
  ]
```

### Create new Todo

`POST /todos`

##### request

- Headers
- Parameters

Name | Type | Description 
---- | ---- | ----------- 
title | String | **required** Todo title
body  | String | **required** Todo body
status | Integer | **required** Todo status (0:open, 1:close)

  
```
{
  "title": String,
  "body": String
  "status": Int
}
```

##### response

```
status: 201 created
---
{
  "id": 3,
  "title": "NEW TITLE",
  "body": "NEW BODY",
  "status": 0,
  "created_at": "2018-01-14T09:12:31Z",
  "updated_at": "2018-01-14T09:12:31Z"
}
```

### Get Todo detail

`GET /todos/:id `

##### request

- Headers
- Parameters
  
##### response

```
status: 200 ok
---
{
  "id": 3,
  "title": "NEW TITLE",
  "body": "NEW BODY",
  "status": 0,
  "created_at": "2018-01-14T09:12:31Z",
  "updated_at": "2018-01-14T09:12:31Z"
}
```

### Update Todo 

`PUT /todos/:id `

##### request

- Headers
- Parameters
  
Name | Type | Description 
---- | ---- | ----------- 
id | Long | **required** Todo id 
title | String | **required** Todo title
body  | String | **required** Todo body
status | Integer | **required** Todo status (0:open, 1:close)
  
```
{
  "id": 3,
  "title": "UPDATED TITLE",
  "body": "UPDATED BODY",
  "status": 0,
}
```

##### response

```
status: 200 ok ( 201 created)
--- 
{
  "id": Long,
  "title": String,
  "body": String,
  "status": Int,
  "created_at": DateTime,
  "updated_at": DateTime
}
```

#### DELETE Todo

`DELETE /todos/:id`

##### request

- Headers
- Parameters

##### response

```
status: 200 ok
---
{
  "status": "ok"
}
```

