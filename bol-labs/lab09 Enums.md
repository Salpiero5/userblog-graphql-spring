<<<<<<< HEAD
# Lab 9 - Enums
=======
# Lab 10 - Enums
>>>>>>> 27259b76beb95ead9b6167055a0fed847b59631b

Add a non-`null`able `string` property `Status` to `User`. This property will hold values:

- `r` - Registered (pending activation)
- `a` - Active
- `d` - Disabled
- `e` - Expired

For our Graph API, make sure these values get properly expressed using an enum.

<details>
<summary>Test!</summary>

```gql
query {
  users {
    firstname
    status
    blogs {
      title
    }
  }
}
```

</details>

---

## Exercise 2. Add a query `usersByStatus()`

Extend the API so that clients can query by _user status_:

<details>
<summary>Test!</summary>

```gql
query {
  usersByStatus(status: ACTIVE) {
    firstname
    status
    blogs {
      title
      content
    }
  }
}
```

</details>