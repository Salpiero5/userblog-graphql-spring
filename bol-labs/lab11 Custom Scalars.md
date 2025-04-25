# Lab 11 - Custom Scalars

Our API can be further improved by implementing custom scalars.

First we'll implement a new **`EmailAddress`** scalar. If time permits you can add a second scalar **`Date`** on your own.

---

## Exercise 1. EmailAddress

Currently, it's possible to update users with an invalid email address.

<details>
<summary>Test!</summary>

```bash
mutation {
  updateUser(input: {
    id: 4, email: "abc"
  }) {
    id
    firstname
    email
  }
}
```

</details>

Define a new scalar `EmailAddress` and use it instead of the `String` scalar.

Re-execute the mutation. It should now not succeed.

## Exercise 2. `Date` (if time permits)

There are two date fields in our entities:

- `user.yearOfBirth` (currently an integer)
- `blog.availableDate`

Build a solution where a new `Date` scalar is used. The expected format is 'YYYY-MM-DD'.  

Tip: To test if a given date is a real date you can use this logic:

```ts
const pattern = /^\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$/;

function validDate(input) {
  if (!pattern.test(input)) {
    return false;
  }

  const date = new Date(input);
  return input === date.toISOString().split('T')[0];
}
```