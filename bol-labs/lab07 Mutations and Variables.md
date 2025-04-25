# Lab 7 - Mutations and Variables

One mitigation to prevent SQL injection is to send queries and variables _separated_ to the server. This concept is called parameterized queries, aka prepared statements. In the first exercise we will practice with variables.  
The remainder of the exercise are all about _mutations_.

---

## Exercise 1. Variables

1. Rewrite the `userByFirstName()` query. In playground, use a variable for the value of `namePart`

   Original query:

   ```bash
   query {
     usersByFirstName(namePart: "a") {
       firstname
       email
     }
   }
   ```

2. Rewrite the `blogsByFilter` query to use a variable for the input value. This will require an input type.

   Original query:

   ```bash
   query {
     blogsByFilter(input: { content: "Blog", published: true }) {
       title
       content
       published
     }
   }
   ```

---

## Exercise 2. Mutations: Add Users

Enrich the API allowing adding users. This will involve adding the `Mutation` root type as well as another input object type.

When all is done, test the mutation. Don't forget to re-query all users to see if your mutation has gone through correctly.

<details>
<summary>Test!</summary>

```bash
mutation {
  createUser(input:{
    firstname: "Francis",
    email: "francis@live.com",
    yearOfBirth: 1992
  }) {
    id
    firstname
    email
    yearOfBirth
    blogs {
      title
    }
  }
}
```

</details>

Don't forget the bottom part (selection set)! 🔔 Forgetting the bottom part is the mistake made most often!

Some time left? Retrieve the input values from a variable.

---

## Exercise 3. Create Blogs (if time permits)

Similar to exercise 2, but now for blogs.  
Note that a new blog is in an unpublished state by default!

Next mutation should run fine in Playground:

```bash
mutation {
  createBlog (input: {
    title: "Blog D",
    content: "Content of Blog D",
    creatorID: "u1"
  }) {
    id
    title
    content
    published
    creator {
      id
      firstname
    }
  }
}
```

---

## Exercise 6. Publish Blogs (if time permits)

Further extend the API so that this mutation runs as expected:

```bash
mutation {
  publishBlog(input: {
    id: "b1",
    published: true
  }) {
    id
    title
    published
  }
}
```

---

## Exercise 7. Delete Blog and handle user errors (if time permits)

Further extend the graph so that a blog can be deleted if it has no comments yet.  
Apart from an input type, this time we'll use a separate _output object type_.

**schema**

```bash
deleteBlog(input: DeleteBlogInput!): DeleteBlogPayload!
```

The separate output object type _`DeleteBlogPayload`_ contains:

- _`blog`_ (type Blog, nullable)
- _`errorMessage`_ (type String, nullable)

**scenario 1. blog id exists + blog has no comments**

- **`blog`**: Blog
- **`errorMessage`**: null

**scenario 2. blog id doesn't exist**

- **`blog`**: null
- **`errorMessage`**: "Blog nog found!"

**scenario 3. blog id exists + blog has comments**

- **`blog`**: null
- **`errorMessage`**: "Blog contains comments!"

Test these 3 scenarios by providing a different input variable each time:

```bash
mutation ($input: DeleteBlogInput!) {
  deleteBlog(input: $input) {
    blog {
      id
      title
    }
    errorMessage
  }
}
```

Query Variables:

```json
{
  "input": {
    "id": "b3"
  }
}
```

Good luck!