# Lab 5 - Use arguments to filter data

## Exercise 1. Filter users by `firstname`

We want to allow filtering users by firstname.

Add a new entry point `usersByFirstName`. It should return a non-nullable array of non-nullable elements of type `User`. Its input argument should be a non-nullable `namePart` of type `String`.

<details>
<summary>Test!</summary>

```gql
query {
  usersByFirstName(namePart: "a") {
    firstname
    email
  }
}
```

</details>

---

## Exercise 2. Find blog by `id`

Implement another entry point `blogById`. When a given `id` is found, the blog is returned, otherwise `null`.

<details>
<summary>Test!</summary>

```bash
query {
  blogById(id: "b2") {
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

</details>

Customize the schema: when no `id` is provided by the client, `"b1"` should be used.

<details>
<summary>Test!</summary>

```bash
query {
  blogById {
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
</details><br>

---

## Exercise 3. Filter blogs on multiple attributes (input type)

Add a more flexible entrypoint `blogsByFilter`. This endpoint should allow filtering blogs by `title`, `content`, and/or `published` fields.

Are these parameters better suited as separate arguments or as a single input object?

Test in Playground.

<details>
<summary>Test!</summary>

```bash
query {
  blogsByFilter(input:{
    title: "A",
    published: true
  }) {
    id
    title
    content
    published
  }
}
```
</details>

---

## Exercise 4. `first` and `last` arguments (if time permits)

Add another entrypoint `usersLimited`. The client must provide _either_ a `first` _or_ a `last` argument.

Furthermore, the value of the provided argument must be between 1 and 10.

Return a clear error message to the client when any of the criteria haven't met.

<details>
<summary>Test!</summary>

```bash
query {
  usersLimited {
    id
    firstname
  }
}
```
</details>

<details>
<summary>Test!</summary>

```bash
query {
  usersLimited (first: 1, last: 2) {
    id
    firstname
  }
}
```
</details>

<details>
<summary>Test!</summary>

```bash
query {
  usersLimited (first: 2) {
    id
    firstname
  }
}
```
</details>

<details>
<summary>Test!</summary>

```bash
query {
  usersLimited (last: 2) {
    id
    firstname
  }
}
```
</details>