
# API仕様書

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
    "code": "success"
}
```

###### ERROR

```

{
    "code": "error",
    "title": [ERROR TITLE]
    "message": [ERROR MESSAGE]
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
{
    "code": "success",
    "todos": [
	{
	    "id": [id],
	    "title": [title],
	    "body": [body],
	    "created_at": [datetime],
	    "updated_at": [datetime]
	},
	{
	    "id": [id],
	    "title": [title],
	    "body": [body],
	    "created_at": [datetime],
	    "updated_at": [datetime]
	},
	{
	    ...
	}, ...
    ]
}
```

#### POST

##### request

- Headers
- Parameters
  - JSON: required

```
{
    "todo": {
	"title": String,
	"body": String
    }
}
```

##### response

```
{
    "code": "success",
    "todo": {
	"id": Long,
	"title": String,
	"body": String
    }
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
    "code": "success",
    "todo": {
	"id": [id],
	"title": [title],
	"body": [body],
	"created_at": [datetime],
	"updated_at": [datetime]
    }
}
```

#### PUT

##### request

- Headers
- Parameters
  - JSON: required

```
{
    "todo": {
	"id": Long,
	"title": String,
	"body": String
    }
}
```

##### response

```
{
    "code": "success",
    "todo": {
	"id": Long,
	"title": String,
	"body": String
    }
}
```

#### PATCH

##### request

- Headers
- Parameters
  - JSON: required

```
{
    "todo": {
	"body": String
    }
}
```

##### response

```
{
    "code": "success",
    "todo": {
	"id": Long,
	"title": String,
	"body": String
    }
}
```

#### DELETE

##### request

- Headers
- Parameters

##### response

```
{
    "code": "success"
}
```

