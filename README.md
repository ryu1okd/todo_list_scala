# TODO LIST API specification document

## Health Check

> /health_check

#### GET

##### request

- Headers
- Parameters

##### response

###### SUCCESS

```
{
    "status": "ok"
}
```

## Todo

> /todos

#### GET

##### request

- Headers
- Parameters
    - title : String
    - body : String
  
##### response

```
  [
    {
      "id": [id],
      "title": [title],
      "body": [body],
      "status": [status],
      "created_at": [datetime],
      "updated_at": [datetime]
    }, 
    {
      "id": [id],
      "title": [title],
      "body": [body],
      "status": [status],
      "created_at": [datetime],
      "updated_at": [datetime]
    }, 
    {
        ...
    }, ...
  ]
```

#### POST

##### request

- Headers
- Parameters
  - JSON: required
  
```
{
  "title": String,
  "body": String
}
```

##### response

```
{
  "id": Long,
  "title": String,
  "body": String
  "status": Int,
  "created_at": DateTime,
  "updated_at": DateTime
}
```

## Todo Detail

> /todos/[id]

#### GET

##### request

- Headers
- Parameters
  
##### response

```
{
  "id": [id],
  "title": [title],
  "body": [body],
  "status": [status],
  "created_at": [datetime],
  "updated_at": [datetime]
}
```

#### PUT

##### request

- Headers
- Parameters
  - JSON: required
  
```
{
  "id": Long,
  "title": String,
  "body": String,
  "status": Int,
  "created_at": DateTime,
  "updated_at": DateTime
}
```

##### response

```
{
  "id": Long,
  "title": String,
  "body": String,
  "status": Int,
  "created_at": DateTime,
  "updated_at": DateTime
}
```

#### PATCH

##### request

- Headers
- Parameters
  - JSON: required
  
```
{
  "body": [body],
  "status": [status]
}
```

##### response

```
{
  "id": Long,
  "title": String,
  "body": String,
  "status": Int,
  "created_at": DateTime,
  "updated_at": DateTime
}
```

#### DELETE

##### request

- Headers
- Parameters

##### response

```
{
  "status": "ok"
}
```

