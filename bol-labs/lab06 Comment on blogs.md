# Lab 6 - Comment on blogs

A user can be the creator of a blog, but users can also comment on published blogs. For this purpose we will extend our data source and our graph.

In a relational database the data may be structured this way:

![ER diagram](img/er-diagram2.png)

In this lab you'll practice what you have learned in previous modules.

---

## Exercise 1. Comment

Let's extend the graph with a new object type `Comment`.

- comments should have an entrypoint of `comments` on `Query`, always returning a non-`null` values
- add a repository to handle the data access of comments. Add a few comments in a static list for now
- a comment in our graph looks as follows:
  | Fieldname   | Type   | Nullable |
  | ----------- | ------ | -------- |
  | id          | `ID`     | no       |
  | content     | `String` | no       |
  | blog        | `Blog`   | no       |
  | commentator | `User`   | no       |
  
Our API should now support queries like:

<details>
<summary>Test!</summary>

```gql
query {
  comments {
    content
    blog {
      title
      creator {
        firstname
      }
    }
    commentator {
      firstname
    }
  }
}
```

</details>

---

## Exercise 2. Query for comments belonging to a Blog

Add a `comments` field to `Blog` for retrieval of comments associated with a blog.

<details>
<summary>Test!</summary>

```gql
query {
  blogs {
    title
    published
    comments {
      content
    }
  }
}
```

</details>

---

## Exercise 3. Query for comments given by a User (if time permits)

Add logic that allows clients to also show comments when showing users. Don't touch the data source.

Test with this query:

```gql
query {
  users {
    firstname
    blogs {
      title
    }
    comments {
      content
    }
  }
}
```