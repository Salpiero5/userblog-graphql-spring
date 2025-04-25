# Lab 4 - Nest Objects

A blogging app with only users doesn't make sense. Let's add some blogs!

---

## Exercise 1. Blog

Extend the graph with a new object type `Blog`.

- The `Blog` type looks as follows:

  | Fieldname | Type    | Nullable |
  | --------- | ------- | -------- |
  | id        | ID      | no       |
  | title     | String  | no       |
  | content   | String  | no       |
  | published | Boolean | no       |

  <br>

- Add an entry point in the root `Query` type:

  | Fieldname | Type | Nullable |
  | --------- | ---- | -------- |
  | blogs     | Blog | no       |

Set up another repository, `BlogRepository`, that keeps track of `Blog`s.

When you've added all the necessary parts, you should be able to query blogs in the playground!

<details>
<summary>Test!</summary>

```bash
query {
  blogs {
    id
    title
    content
    published
  }
}
```

</details>

---

## Exercise 2. Nest User in Blog

Both objects `User` and `Blog` are related. The tables in a relational database might look like:

![ER diagram](img/er-diagram1.png)

However our graph won't be a one-on-one copy of the database structure. Instead, we'll structure types and name fields in such a way that clients can query intuitively.

1. Add a `string` property `UserId` to `Blog`
1. Assign each blog to a user.
1. Now for the GraphQL part. Add a non-`null`able `creator` field to type `Blog` of type `User`. Use the `UserRepository` to find the associated user.

Whenever the client decides to show fields of the **`creator`**, the resolver function is called.

If all went well, you should now be able to query the associated user for each blog using the `creator` field.

<details>
<summary>Test!</summary>

```gql
query {
  blogs {
    title
    content
    published
    creator {
      firstname
      email
      yearOfBirth
    }
  }
}
```

</details>

---

## Exercise 3. Nest blogs in `User`

In the previous exercise details of a creator can be fetched when querying blogs.

Now implement logic that allows clients to query the other way around:  
_blogs can be fetched when querying users_.


<details>
<summary>Test!</summary>

```gql
query {
  users {
    firstname
    blogs {
      id
      title
    }
  }
}
```

</details><br>

---

🔔 We've build a very flexible graph which even allow for silly queries like:

```gql
query {
  users {
    firstname
    blogs {
      title
      creator {
        firstname
        blogs {
          title
        }
      }
    }
  }
}
```

With a lot of data and a lot of levels in a query, the GraphQL Server could become overloaded and less performant.  
Hackers could use this type of queries for a Denial of Service (DoS) attack.

Many GraphQL libraries support features to limit the allowed number of levels.  

More info on _DoS prevention_:  
https://cheatsheetseries.owasp.org/cheatsheets/GraphQL_Cheat_Sheet.html