## Blog API
***
### Methods
1. POST
   1. `/create` - create a blog
2. GET
   1. `/blogs` - get list of blogs
   2. `/blogs/{Id}` - get blog by ID
3. PUT
   1. `/blogs/{Id}` - update fields of specified blog
4. DELETE
   1. `/blogs/{Id}` - delete specified blog
***
### I. POST
1. Successful Test
    ```HTML
    POST /create HTTP/1.1
    Host: localhost:8080
    Content-Type: application/json
   ```
   ```json
    {
        "title": "Sample Blog",
        "content": "This is the original content",
        "datePublished": "2020-01-01",
        "author": "John Doe"
    }
    ```
   
   *Response if fields and endpoint are correct:*

    ```json
    {
      "message": "Blog created"
    }
    ```

2. Unsuccessful Test

    ```html
    POST /create HTTP/1.1
    Host: localhost:8080
    Content-Type: application/json
   ```
   ```json
    {
        "title": "Sample Blog",
        "content": "This is the second original content",
        "datePublished": "2020-53501",
        "author": "Mary Doe"
    }
    ```
    *Response if one or more fields are invalid:*
    ```json
    {
        "timestamp": "2021-02-09T09:49:33.505+00:00",
        "status": 400,
        "error": "Bad Request",
        "message": "",
        "path": "/create"
    }
    ```
   
***
### II. GET
####  A. Get list of blogs
1. Successful Test

    ```html
    GET /blogs HTTP/1.1
    Host: localhost:8080
    Content-Type: application/json
    ```

   *Response if endpoint is correct:*

    ```json
    [
        {
            "blogId": "496e8fe2-455c-4d4c-b4b4-e07b67dc28d3",
            "title": "Sample Blog",
            "content": "This is the original content",
            "datePublish": "2020-01-01",
            "author": "Mary Doe",
            "deleted": false
        },
        {
            "blogId": "66675dd0-34ee-4357-a549-7c99ef1fd95b",
            "title": "Sample Blog 2",
            "content": "This is the second original content",
            "datePublish": "2020-01-01",
            "author": "John Doe",
            "deleted": false
        }
    ]
    ```

2. Unsuccessful Test

    ```html
    GET /blogs/ HTTP/1.1
    Host: localhost:8080
    Content-Type: application/json
    ```
   *Response if `GET /blogs` is called but blog list is empty:*
    ```json
    {
      "message": "Error: Blog list is empty."
    }
    ```
   
####  B. Get blog by ID
1. Successful Test

    ```html
    GET /blogs/496e8fe2-455c-4d4c-b4b4-e07b67dc28d3 HTTP/1.1
    Host: localhost:8080
    Content-Type: application/json
    ```

   *Response if blog ID is valid:*

    ```json
    {
        "blogId": "496e8fe2-455c-4d4c-b4b4-e07b67dc28d3",
        "title": "Sample Blog",
        "content": "This is the original content",
        "datePublish": "2020-01-01",
        "author": "Mary Doe",
        "deleted": false
    }
    ```

2. Unsuccessful Test

    ```html
    GET /blogs/invalid-blog-id HTTP/1.1
    Host: localhost:8080
    Content-Type: application/json
    ```
   *Response if blog ID is invalid:*
    ```json
    {
    "message": "Error: Blog does not exist"
    }
    ```
   
***
### III. PUT
1. Successful Test
    ```html
    PUT /blogs/496e8fe2-455c-4d4c-b4b4-e07b67dc28d3 HTTP/1.1
    Host: localhost:8080
    Content-Type: application/json  
   ```
   ```json  
    {
        "title": "Sample Blog",
        "content": "This is the edited content",
        "datePublished": "2020-01-01",
        "author": "Mary Doe"
    }
    ```

   *Response if fields and blog ID are valid:*

    ```json
    {
      "message": "Blog #496e8fe2-455c-4d4c-b4b4-e07b67dc28d3 updated"
    }
    ```

   *Running `GET /blogs/{Id}` will show that the blog is updated:*

    ```json
    {
        "blogId": "496e8fe2-455c-4d4c-b4b4-e07b67dc28d3",
        "title": "Sample Blog",
        "content": "This is the edited content",
        "datePublish": "2020-01-01",
        "author": "Mary Doe",
        "deleted": false
    }
   ```

2. Unsuccessful Test

    ```html
    PUT /blogs/496e8fe2-455c-4d4c-b4b4-e07b67dc28d3 HTTP/1.1
    Host: localhost:8080
    Content-Type: application/json
   ```
   ```json
    {
        "title": "Sample Blog",
        "content": "This is the edited content",
        "datePublished": "2020-01-01"
    }
    ```
   *Response if one or more fields are missing:*
    ```json
    {
        "timestamp": "2021-02-09T10:05:40.619+00:00",
        "status": 400,
        "error": "Bad Request",
        "message": "",
        "path": "/blogs/496e8fe2-455c-4d4c-b4b4-e07b67dc28d3"
    }
    ```

***
### IV. DELETE
1. Successful Test
    ```HTML
    DELETE /blogs/496e8fe2-455c-4d4c-b4b4-e07b67dc28d3 HTTP/1.1
    Host: localhost:8080
    Content-Type: application/json
    ```

   *Response if fields and blog ID are valid:*

    ```json
    {
      "message": "Blog #496e8fe2-455c-4d4c-b4b4-e07b67dc28d3 deleted"
    }
    ```
   
    *Running `GET /blogs/{Id}` will show that the deleted field of blog is now true:*
    
    ```json
    {
        "blogId": "496e8fe2-455c-4d4c-b4b4-e07b67dc28d3",
        "title": "Sample Blog",
        "content": "This is the edited content",
        "datePublish": "2020-01-01",
        "author": "Mary Doe",
        "deleted": true
    }
   ```

2. Unsuccessful Test

    ```html
    DELETE /blogs/invalid-blog-id HTTP/1.1
    Host: localhost:8080
    Content-Type: application/json
    ```
   *Response if blog ID is invalid*
    ```json
    {
        "message": "Error: Blog does not exist"
    }
    ```