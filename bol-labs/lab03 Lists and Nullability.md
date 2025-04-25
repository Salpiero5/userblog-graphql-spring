# Lab 3 - Lists and Nullability

## Exercise 1. Retrieve multiple users

The API should also support a way to retrieve all users via a new entry point `users`. The new entrypoint should always return a list (not a null value) and although the list might be empty, it should never contain `null` values.

Adjust your query to now use your new `users` entrypoint.

<details>
<summary>Test!</summary>

```bash
query {
  users {
    id
    firstname
    email
    yearOfBirth
  }
}
```

</details>

Lastly, refactor your static list of users to a `UserRepository`. Use the resolver context to access your repository.

---

## Exercise 2. Nullability Quiz

Quiz time! In each of the next scenarios the data source contains different data.  
Can you predict if GraphQL will return an error or data when this request is sent:

```bash
query {
  users {
    id
    firstname
    email
    yearOfBirth
  }
}
```

1.  ```ts
    const users = [
      {
        id: 'u1',
        firstname: null,
        email: 'jo@infosupport.com',
        yearOfBirth: 1982,
      },
    ];
    ```

    <details>
    <summary>Answer</summary>

    Error is returned. In the schema the field `firstname` is defined as non-nullable:

    </details><br>

1.  ```ts
    const users = [
      {
        id: 'u1',
        firstname: 'Joop',
        email: null,
        yearOfBirth: null,
      },
    ];
    ```

    <details>
    <summary>Answer</summary>

    An array with one user is returned. `null`-values for fields `Email` and `YearOfBirth` are allowed.

    </details><br>

1.  ```ts
    const users = [];
    ```

    <details>
    <summary>Answer</summary>

    Empty array is returned. In the schema the `users` field is defined as `[User!]!`

    - 1st exclamation mark = the array shouldn't contain `null` values
    - 2nd exclamation mark = an array should be returned (a `null` value is not allowed)
    
    </details><br>

1.  ```cs
    const users = null;
    ```

    <details>
    <summary>Answer</summary>

    Error is returned. This is a result of the 2nd exclamation mark in `[User!]!`
    </details><br>

1.  ```ts
    const users = [
      {
        id: 'u1',
        firstname: 'Joop',
        email: 'jo@infosupport.com',
        yearOfBirth: 1982,
      },
      null,
    ];
    ```

    <details>
    <summary>Answer</summary>

    Error is returned. This is a result of the 1st exclamation mark in <code>[User!]!</code>
    </details>