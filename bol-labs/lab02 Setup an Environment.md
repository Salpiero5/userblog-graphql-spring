# Lab 2 - Setup an Environment

In the rhe rest of this course's labs, we'll build a new Blogging application. With all labs completed, its queryable data will include users, blogs and comments. This first lab will focus on defining an endpoint for user #1's data.

Let's kickoff!

---

Create a Node.js project and set it up as a GraphQL Server. Our GraphQL API should have one entrypoint that returns the first user of a list of users. A few hints:

- You'll need the `@apollo/server` package
- Define a list of users somewhere. It's ok to make this a static list somewhere. In a later lab, we'll refine how we retrieve data.
- Our root `Query` type should have one field to query, `firstUser`
- `User` itself looks as follows:
  | Fieldname   | Type   | Nullable |
  | ----------- | ------ | -------- |
  | id          | ID     | no       |
  | firstname   | String | no       |
  | email       | String | yes      |
  | yearOfBirth | Int    | yes      |

Running a basic query should return the data of the first user in your dataset.

<details>
<summary>Test!</summary>

```gql
query {
  firstUser {
    id
    firstname
    email
    yearOfBirth
  }
}
```

</details>

&nbsp;

Feel free to play around a bit more in the playground. Check out the auto-generated docs and the schema.